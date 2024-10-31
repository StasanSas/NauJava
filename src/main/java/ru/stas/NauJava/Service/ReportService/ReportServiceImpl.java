package ru.stas.NauJava.Service.ReportService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stas.NauJava.Dao.ProductRepository;
import ru.stas.NauJava.Dao.ReportRepository;
import ru.stas.NauJava.Dao.UserRepository;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Entity.Report;
import ru.stas.NauJava.Entity.StatusReport;
import ru.stas.NauJava.Service.ReportService.ResourceTreads.ResourceCountingAmountUsers;
import ru.stas.NauJava.Service.ReportService.ResourceTreads.ResourseFoundingProducts;
import ru.stas.NauJava.Service.ReportService.Treads.TreadForCountingUsers;
import ru.stas.NauJava.Service.ReportService.Treads.TreadForFoundingProducts;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

record DataAboutReport(ResourceCountingAmountUsers resourceCountingUsers,
                       ResourseFoundingProducts resourceFoundingProducts,
                       Long startTimeWork){

}

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository,
                             ProductRepository productRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String getContentReport(Long id) {
        var report = reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get().getContent();
        } else {
            throw new EntityNotFoundException("Отчет с указанным ID не найден");
        }
    }

    @Override
    public Long createReport() {
        var report = new Report();
        report.setContent();
        report.setStatus(StatusReport.CREATED);

        var savedReport = reportRepository.save(report);
        return savedReport.getId();
    }

    @Override
    public void formationReport(Long id) {
        var future = CompletableFuture.supplyAsync(() -> {
            var startTime = System.currentTimeMillis();
            var resourceCountingUsers = new ResourceCountingAmountUsers();
            var thread1 = new TreadForCountingUsers(resourceCountingUsers, userRepository);

            var resourceFoundingProducts = new ResourseFoundingProducts();
            var thread2 = new TreadForFoundingProducts(resourceFoundingProducts, productRepository);

            thread1.start();
            thread2.start();

            try {
                thread1.join();
            } catch (InterruptedException e) {
                extracted(e);
            }
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return new DataAboutReport(resourceCountingUsers, resourceFoundingProducts, startTime);
        });
        future.thenAccept(result -> {
            var optionalReport = reportRepository.findById(id);
            if (optionalReport.isEmpty()) {
                throw new EntityNotFoundException("Отчет с указанным ID не найден");
            }
            var report = optionalReport.get();
            report.setStatus(StatusReport.COMPLETED);
            report.setAmountUsers(result.resourceCountingUsers().getAmount());
            report.setProducts(new HashSet<>(result.resourceFoundingProducts().getProducts()));
            report.setTimeOfCalcAmountUsers(result.resourceCountingUsers().getTime());
            report.setTimeOfFoundProducts(result.resourceFoundingProducts().getTime());
            report.setTimeOfFormationReport(System.currentTimeMillis() - result.startTimeWork());
            report.setContent();
            reportRepository.save(report);
        });
        future.exceptionally(ex -> {
            System.out.println("При выполнении произошла ошибка: " +
                    ex.getMessage());
            return null;
        });
    }

    private static void extracted(InterruptedException e) {
        throw new RuntimeException(e);
    }
}
