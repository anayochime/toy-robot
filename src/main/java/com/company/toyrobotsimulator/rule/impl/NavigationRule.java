package com.company.toyrobotsimulator.rule.impl;

import com.company.toyrobotsimulator.model.command.NavigableCommand;
import com.company.toyrobotsimulator.model.grid.Grid;
import com.company.toyrobotsimulator.model.grid.GridItem;
import com.company.toyrobotsimulator.rule.Rule;

public abstract class NavigationRule implements Rule {

    private final Grid grid;

    public NavigationRule(Grid grid) {
        this.grid = grid;
    }

    protected String placeOnGrid(NavigableCommand command, GridItem gridItem) {
        if(grid.isWithinGrid(command.getNewPosition())){
            return command.action(gridItem);
        }
        return null;
    }


}
