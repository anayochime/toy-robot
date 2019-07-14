package com.company.toyrobotsimulator.model.command.impl;

import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.grid.GridItem;

public class RightCommand implements Command {
    @Override
    public CommandAction getCommandAction() {
        return CommandAction.RIGHT;
    }

    @Override
    public String action(GridItem gridItem) {
        return null;
    }
}
