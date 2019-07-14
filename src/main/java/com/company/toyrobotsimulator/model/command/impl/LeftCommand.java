package com.company.toyrobotsimulator.model.command.impl;

import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.GridItemCommand;
import com.company.toyrobotsimulator.model.grid.GridItem;
import com.company.toyrobotsimulator.model.grid.MovableGridItem;

public class LeftCommand implements GridItemCommand {
    @Override
    public CommandAction getCommandAction() {
        return CommandAction.LEFT;
    }

    @Override
    public String action(GridItem gridItem) {
        if(gridItem instanceof MovableGridItem){
            MovableGridItem movableGridItem = (MovableGridItem)gridItem;
            movableGridItem.turnLeft();
            return null;
        }else{
            throw new RuntimeException("Unsupported GridItem");
        }
    }
}
