package com.company.toyrobotsimulator.service;

import com.company.toyrobotsimulator.simulator.Simulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandServiceImpl implements CommandService {

    private final Simulator simulator;

    @Autowired
    public CommandServiceImpl(Simulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public String processInput(String input) {
        List<String> result = simulator.processInput(input);
        return String.join(" ", result);
    }
}
