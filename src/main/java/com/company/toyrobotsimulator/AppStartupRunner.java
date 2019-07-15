package com.company.toyrobotsimulator;

import com.company.exception.InvalidCommandException;
import com.company.toyrobotsimulator.simulator.Simulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Profile("!test")
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private Simulator simulator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands Below ");
        do {
            String input = scanner.nextLine();

            try {
                List<String> results = simulator.processInput(input);
                String printout = String.join(" ", results);
                System.out.println(printout);
                System.out.println();
            }catch(InvalidCommandException ex){
                System.out.println(ex.getMessage());
                System.out.println();
            }
        } while (true);
    }
}