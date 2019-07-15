package com.company.toyrobotsimulator.config;

import com.company.toyrobotsimulator.simulator.Simulator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${grid.XCoordinate}")
    private int gridXCoordinate;
    @Value("${grid.YCoordinate}")
    private int gridYCoordinate;

    @Bean
    public Simulator simulator(){
        return new Simulator(gridXCoordinate,gridYCoordinate);
    }
}