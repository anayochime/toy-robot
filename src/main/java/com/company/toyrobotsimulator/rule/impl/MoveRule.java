package com.company.toyrobotsimulator.rule.impl;

import com.company.toyrobotsimulator.model.Position;
import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.impl.MoveCommand;
import com.company.toyrobotsimulator.model.grid.Grid;
import com.company.toyrobotsimulator.model.grid.GridItem;
import com.company.toyrobotsimulator.rule.Decision;
import com.company.toyrobotsimulator.rule.RuleResult;

import static com.company.toyrobotsimulator.model.Direction.*;
import static com.company.toyrobotsimulator.model.grid.Grid.DEFAULT_STRIDE_SIZE;

public class MoveRule extends NavigationRule  {
    public MoveRule(Grid grid) {
        super(grid);
    }

    @Override
    public RuleResult execute(Command command, GridItem gridItem) {
        if (command.getCommandAction() == CommandAction.MOVE) {
            Position movePosition = getNewPosition(gridItem.getGridPosition());
            MoveCommand moveCommand = new MoveCommand(movePosition);
            String result = placeOnGrid(moveCommand, gridItem);
            return new RuleResult(Decision.END, result);
        } else {
            return new RuleResult(Decision.CONTINUE, "");
        }
    }

    private Position getNewPosition(Position oldPosition) {

        switch (oldPosition.getFacing()) {

            case NORTH:
                return new Position(oldPosition.getXCoordinate(), oldPosition.getYCoordinate() + DEFAULT_STRIDE_SIZE, NORTH);
            case EAST:
                return new Position(oldPosition.getXCoordinate() + DEFAULT_STRIDE_SIZE, oldPosition.getYCoordinate(), EAST);
            case SOUTH:
                return new Position(oldPosition.getXCoordinate(), oldPosition.getYCoordinate() - DEFAULT_STRIDE_SIZE, SOUTH);
            case WEST:
                return new Position(oldPosition.getXCoordinate() - DEFAULT_STRIDE_SIZE, oldPosition.getYCoordinate(), WEST);
            default:
                throw new RuntimeException("Invalid Direction");
        }
    }
}