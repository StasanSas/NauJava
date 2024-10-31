package ru.stas.NauJava.Service.ReportService.Treads;

import ru.stas.NauJava.Dao.UserRepository;
import ru.stas.NauJava.Service.ReportService.ResourceTreads.ResourceCountingAmountUsers;

public class TreadForCountingUsers extends Thread {
    private final ResourceCountingAmountUsers resource;
    private final UserRepository userRepository;

    public TreadForCountingUsers(ResourceCountingAmountUsers resource,
                                 UserRepository userRepository) {
        this.resource = resource;
        this.userRepository = userRepository;
    }

    @Override
    public void run() {
        var startTime = System.currentTimeMillis();
        var amountUsers = userRepository.getAmountOfUsers();
        var elapsed = (System.currentTimeMillis() - startTime);
        resource.setTime(elapsed);
        resource.setAmount(amountUsers);
    }
}

