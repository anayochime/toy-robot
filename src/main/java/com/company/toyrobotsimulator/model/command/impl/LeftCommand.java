package com.company.toyrobotsimulator.model.command.impl;

import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.grid.GridItem;

public class LeftCommand implements Command {
    @Override
    public CommandAction getCommandAction() {
        return CommandAction.LEFT;
    }

    @Override
    public String action(GridItem gridItem) {
        return null;
    }
}