package com.company.toyrobotsimulator.model.grid;

import com.company.toyrobotsimulator.model.Position;

public interface GridItem {

    void setGridPosition(Position position);

    boolean isOnGrid();

    void setOnGrid(boolean onGrid);
}
