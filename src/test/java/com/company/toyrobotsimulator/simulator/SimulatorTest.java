package com.company.toyrobotsimulator.simulator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulatorTest {

    @Test
    public void testRobotIsSuccessfullyPlaced(){
        Simulator simulator = new Simulator(5, 5);
        String input = "PLACE 1,1,SOUTH";
        simulator.processInput(input);

        assertThat(simulator.getRobot().getPositionReport().get()).isEqualTo("1,1,SOUTH");
    }
}
