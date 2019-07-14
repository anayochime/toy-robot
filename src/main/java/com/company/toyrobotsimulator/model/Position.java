package com.company.toyrobotsimulator.model;

public class Position {
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private Direction facing;

    public Position(int xCoordinate, int yCoordinate, Direction facing) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.facing = facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public Direction getFacing() {
        return facing;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s", xCoordinate, yCoordinate,facing.name());
    }


}
