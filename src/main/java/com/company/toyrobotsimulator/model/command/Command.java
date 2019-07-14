package com.company.toyrobotsimulator.model.command;

import com.company.toyrobotsimulator.model.grid.GridItem;

public interface Command {
    /**
     * Retrieves the CommandAction for the command
     * @return CommandAction
     */
    CommandAction getCommandAction();

    /**
     * Action performed on the given grid item
     * @param gridItem
     */
    String action(GridItem gridItem);
}
