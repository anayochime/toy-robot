package com.company.toyrobotsimulator.model.command;

import com.company.toyrobotsimulator.model.Position;

public interface NavigableCommand extends Command {
    /**
     * Returns the next position it intends to effect
     * @return
     */
    Position getNewPosition();
}
