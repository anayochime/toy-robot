package com.company.toyrobotsimulator.model.grid;

import com.company.toyrobotsimulator.model.Position;

import java.util.Optional;

public interface GridItem {
    Position getGridPosition();
    void setGridPosition(Position position);

    boolean isOnGrid();

    void setOnGrid(boolean onGrid);

    Optional<String> getPositionReport();
}
