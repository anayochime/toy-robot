package com.company.toyrobotsimulator.model.command.impl;

import com.company.toyrobotsimulator.model.Position;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.NavigableCommand;
import com.company.toyrobotsimulator.model.grid.GridItem;

public class MoveCommand implements NavigableCommand {

    private final Position position;


    public MoveCommand(Position position) {
        this.position = position;
    }

    @Override
    public CommandAction getCommandAction() {
        return CommandAction.MOVE;
    }

    @Override
    public String action(GridItem gridItem) {
        return null;
    }

    @Override
    public Position getNewPosition() {
        return position;
    }

}
