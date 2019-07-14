package com.company.toyrobotsimulator.model.grid;

import com.company.toyrobotsimulator.model.Position;

public class Grid {
    private final int xCoordinate;
    private final int yCoordinate;
    public static final int DEFAULT_STRIDE_SIZE = 1;

    public Grid(int gridXCoordinate, int gridYCoordinate) {
        this.xCoordinate = gridXCoordinate;
        this.yCoordinate = gridYCoordinate;
    }

    public boolean isWithinGrid(Position position){
        return position.getXCoordinate() < xCoordinate
                && position.getYCoordinate() < yCoordinate
                && position.getXCoordinate() >= 0
                && position.getYCoordinate() >= 0;
    }
}
