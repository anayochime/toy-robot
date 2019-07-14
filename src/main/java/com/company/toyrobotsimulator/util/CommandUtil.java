package com.company.toyrobotsimulator.util;

import com.company.exception.InvalidCommandException;
import com.company.toyrobotsimulator.model.Direction;
import com.company.toyrobotsimulator.model.Position;
import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import com.company.toyrobotsimulator.model.command.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandUtil {
    /**
     * Converts input string of commands into a list of commands to be executed by the simulator
     *
     * @param input commands to be executed by the simulator
     * @return
     */
    public static List<Command> processInputString(String input) {
        if(Objects.isNull(input) || input.isEmpty()){
            throw new InvalidCommandException("Command cannot be empty");
        }
        String[] inputCommands = input.split(" ");

        List<Command> commands = new ArrayList<>();
        int startingCommandIndex = 0;
        processInputCommands(inputCommands, startingCommandIndex, commands);
        return commands;
    }

    private static void processInputCommands(String[] inputCommands, int nextInputCommandIndex,
                                             List<Command> commands) {
        //Ignore any spaces
        if (inputCommands[nextInputCommandIndex].isEmpty()) {
            nextInputCommandIndex++;
        } else {
            //Verify if the input is a valid command
            CommandAction commandAction;
            try {
                commandAction = CommandAction.valueOf(inputCommands[nextInputCommandIndex]);
            }catch(IllegalArgumentException ex){
                throw new InvalidCommandException(String.format("Command [%s] does not Exist", inputCommands[nextInputCommandIndex]));
            }

            //Check if the new command is a PLACE so as to retrieve its args for processing else process as normal command
            String[] commandArgs;
            if (commandAction == CommandAction.PLACE) {
                commandArgs = inputCommands[nextInputCommandIndex + 1].split(",");
                commands.add(createCommand(commandAction, commandArgs));
                nextInputCommandIndex = nextInputCommandIndex + 2;
            } else {
                commands.add(createCommand(commandAction, null));
                nextInputCommandIndex++;
            }
        }

        if (inputCommands.length > nextInputCommandIndex) {
            processInputCommands(inputCommands, nextInputCommandIndex, commands);
        }
    }

    private static Command createCommand(CommandAction commandAction, String[] commandArgs) {
        switch (commandAction) {
            case PLACE:
                int x = Integer.parseInt(commandArgs[0]);
                int y = Integer.parseInt(commandArgs[1]);
                Direction facing = Direction.valueOf(commandArgs[2]);
                return new PlaceCommand(new Position(x, y, facing));
            case MOVE:
                return new MoveCommand(null);
            case LEFT:
                return new LeftCommand();
            case RIGHT:
                return new RightCommand();
            case REPORT:
                return new ReportCommand();
            default:
                throw new InvalidCommandException(String.format("Command %s does not Exist", commandAction));
        }
    }
}
