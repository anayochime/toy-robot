package com.company.toyrobotsimulator.rule.impl;

import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.NavigableCommand;
import com.company.toyrobotsimulator.model.grid.Grid;
import com.company.toyrobotsimulator.model.grid.GridItem;
import com.company.toyrobotsimulator.rule.Decision;
import com.company.toyrobotsimulator.rule.RuleResult;

public class PlaceRule extends NavigationRule {

    public PlaceRule(Grid grid) {
        super(grid);
    }

    @Override
    public RuleResult execute(Command command, GridItem gridItem) {
        if (command.getCommandAction() == CommandAction.PLACE) {
            String result = placeOnGrid((NavigableCommand)command, gridItem);
            return new RuleResult(Decision.END, result);
        } else {
            return new RuleResult(Decision.CONTINUE, "");
        }
    }
}
