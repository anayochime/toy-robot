package com.company.toyrobotsimulator.rule.impl;

import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.GridItemCommand;
import com.company.toyrobotsimulator.model.grid.GridItem;
import com.company.toyrobotsimulator.rule.Decision;
import com.company.toyrobotsimulator.rule.Rule;
import com.company.toyrobotsimulator.rule.RuleResult;

public class GridItemRule implements Rule {

    @Override
    public RuleResult execute(Command command, GridItem gridItem) {
        if(command instanceof GridItemCommand){
            String result = command.action(gridItem);
            return new RuleResult(Decision.END, result);
        }else{
            return new RuleResult(Decision.CONTINUE, null);
        }
    }
}