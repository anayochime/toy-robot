package com.company.toyrobotsimulator.simulator;

import com.company.toyrobotsimulator.model.Robot;
import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.grid.Grid;
import com.company.toyrobotsimulator.rule.Decision;
import com.company.toyrobotsimulator.rule.Rule;
import com.company.toyrobotsimulator.rule.RuleResult;
import com.company.toyrobotsimulator.rule.impl.PlaceRule;
import com.company.toyrobotsimulator.util.CommandUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Simulator {
    private final Grid grid;
    private Robot robot;
    private Set<Rule> rules;

    public Simulator(int gridXCoordinate, int gridYCoordinate) {
        this.grid = new Grid(gridXCoordinate, gridYCoordinate);
        robot = new Robot();
        rules = new HashSet<>();
        rules.add(new PlaceRule(grid));
    }

    /**
     * Takes a sequence of input string of commands and executes them, returning any results in a list
     * @param input
     * @return output of the executed command that generates output
     */
    public List<String> processInput(String input) {
        List<Command> commands = CommandUtil.processInputString(input);

        return commands.stream()
                .filter(command -> command.getCommandAction() == CommandAction.PLACE || robot.isOnGrid())
                .map(this::executeCommand)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<String> executeCommand(Command command) {
        for (Rule rule : rules) {
            RuleResult ruleResult = rule.execute(command, robot);
            if (ruleResult.getDecision() == Decision.END) {
                return Optional.ofNullable(ruleResult.getOutput());
            }
        }
        return Optional.empty();
    }

    public Robot getRobot() {
        return robot;
    }
}
