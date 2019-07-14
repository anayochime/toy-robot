package com.company.toyrobotsimulator.model;

import com.company.toyrobotsimulator.model.grid.GridItem;

import java.util.Objects;
import java.util.Optional;

public class Robot implements GridItem {

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
}
