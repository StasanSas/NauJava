package ru.stas.NauJava;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;


import ru.stas.NauJava.Entity.Product;

@Configuration
public class Config {

    @Autowired
    private CommandProcessor commandProcessor;

    @Bean
    public CommandLineRunner commandLineScanner(){
        return args -> {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду. '-' для выхода.");
                while (true)
                {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("-".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}


