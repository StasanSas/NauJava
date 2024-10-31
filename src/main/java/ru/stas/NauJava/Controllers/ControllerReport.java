package ru.stas.NauJava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stas.NauJava.Dao.ReportRepository;
import ru.stas.NauJava.Entity.Product;
import ru.stas.NauJava.Service.ReportService.ReportServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ControllerReport {

    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/create")
    public String createReportAndStartFormation(Model model) {
        var reportId = reportService.createReport();
        reportService.formationReport(reportId);
        model.addAttribute("id", reportId);
        return "id";
    }

    @GetMapping("/")
    public String viewReport(@RequestParam Long id, Model model) {
        var report = reportRepository.findById(id).get();
        model.addAttribute("status", report.getStatus());
        model.addAttribute("amountUsers", report.getAmountUsers());
        model.addAttribute("timeCalcUsers", report.getTimeOfCalcAmountUsers());
        model.addAttribute("timeFoundProduct", report.getTimeOfFoundProducts());
        model.addAttribute("timeFormationReport", report.getTimeOfFormationReport());
        model.addAttribute("products", report.getProducts());
        return "report";
    }
}
