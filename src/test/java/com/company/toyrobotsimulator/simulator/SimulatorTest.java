package com.company.toyrobotsimulator.simulator;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulatorTest {

    @Test
    public void testRobotIsSuccessfullyPlaced(){
        Simulator simulator = new Simulator(5, 5);
        String input = "PLACE 1,1,SOUTH";
        simulator.processInput(input);

        assertThat(simulator.getRobot().getPositionReport().get()).isEqualTo("1,1,SOUTH");
    }

    @Test
    public void testRobotNonPLACECommandSucceeds(){
        Simulator simulator = new Simulator(5, 5);
        String input = "MOVE REPORT PLACE 0,0,NORTH LEFT REPORT";
        simulator.processInput(input);
        assertThat(simulator.getRobot().getPositionReport().get()).isEqualTo("0,0,WEST");

        input = "RIGHT MOVE MOVE REPORT";
        simulator.processInput(input);
        assertThat(simulator.getRobot().getPositionReport().get()).isEqualTo("0,2,NORTH");

        input = "RIGHT MOVE MOVE";
        simulator.processInput(input);
        assertThat(simulator.getRobot().getPositionReport().get()).isEqualTo("2,2,EAST");

        input = "MOVE PLACE 0,0,NORTH LEFT REPORT";
        simulator.processInput(input);
        assertThat(simulator.getRobot().getPositionReport().get()).isEqualTo("0,0,WEST");
    }

    @Test
    public void testReportCommandSuccess(){
        Simulator simulator = new Simulator(5, 5);
        String input = "MOVE REPORT PLACE 0,0,NORTH REPORT LEFT REPORT";
        simulator.processInput(input);
        input = "REPORT REPORT";
        List<String> results = simulator.processInput(input);
        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0)).isEqualTo("Output:0,0,WEST");
        assertThat(results.get(1)).isEqualTo("Output:0,0,WEST");
    }
}
