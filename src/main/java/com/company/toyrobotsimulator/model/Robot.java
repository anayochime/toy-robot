package com.company.toyrobotsimulator.model;

import com.company.toyrobotsimulator.model.grid.MovableGridItem;

import java.util.Objects;
import java.util.Optional;

public class Robot implements MovableGridItem {

    private boolean onGrid;
    private Position position;

    public Optional<String> getPositionReport() {
        if (Objects.nonNull(position)) {
            return Optional.of(position.toString());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Position getGridPosition() {
        return position;
    }
    @Override
    public void setGridPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isOnGrid() {
        return onGrid;
    }

    @Override
    public void setOnGrid(boolean onGrid) {
        this.onGrid = onGrid;
    }

    @Override
    public void turnRight(){
        int directionOrdinal = position.getFacing().ordinal();

        directionOrdinal++;

        if(directionOrdinal == 4){
            directionOrdinal = 0;
        }

        position.setFacing(Direction.values()[directionOrdinal]);
    }

    @Override
    public void turnLeft() {
        int directionOrdinal = position.getFacing().ordinal();

        directionOrdinal--;

        if(directionOrdinal == -1){
            directionOrdinal = 3;
        }

        position.setFacing(Direction.values()[directionOrdinal]);
    }
}
