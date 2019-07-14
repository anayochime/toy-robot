package com.company.toyrobotsimulator.model.command.impl;

import com.company.toyrobotsimulator.model.Position;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.NavigableCommand;
import com.company.toyrobotsimulator.model.grid.GridItem;

public class PlaceCommand implements NavigableCommand {
    private final Position position;

    public PlaceCommand(Position position) {
        this.position = position;
    }

    @Override
    public CommandAction getCommandAction() {
        return CommandAction.PLACE;
    }

    @Override
    public String action(GridItem gridItem) {
        gridItem.setGridPosition(position);
        gridItem.setOnGrid(true);
        return null;
    }

    @Override
    public Position getNewPosition() {
        return position;
    }
}
