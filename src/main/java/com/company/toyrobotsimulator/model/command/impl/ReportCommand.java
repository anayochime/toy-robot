package com.company.toyrobotsimulator.model.command.impl;

import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.GridItemCommand;
import com.company.toyrobotsimulator.model.grid.GridItem;

public class ReportCommand implements GridItemCommand {
    @Override
    public CommandAction getCommandAction() {
        return CommandAction.REPORT;
    }

    @Override
    public String action(GridItem gridItem) {
        return "Output:" + gridItem.getPositionReport().orElseGet(null);
    }
}
