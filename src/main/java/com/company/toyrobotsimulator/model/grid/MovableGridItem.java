package com.company.toyrobotsimulator.model.grid;

public interface MovableGridItem extends GridItem {
    /**
     * Performs directional change towards the right on the grid item
     */
    void turnRight();

    /**
     * Performs directional change towards the left on the grid item
     */
    void turnLeft();
}
