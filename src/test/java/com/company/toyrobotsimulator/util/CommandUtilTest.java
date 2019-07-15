package com.company.toyrobotsimulator.util;

import com.company.exception.InvalidCommandException;
import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandUtilTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testSingleInputCommandProcessingSuccess(){
        String input = "PLACE 1,0,NORTH";

        List<Command> commandList = CommandUtil.processInputString(input);
        assertThat(commandList.get(0).getCommandAction()).isEqualTo(CommandAction.PLACE);
    }

    @Test
    public void testMultipleInputCommandProcessingSuccess(){
        String input = "MOVE PLACE 1,0,NORTH  LEFT RIGHT REPORT";

        List<Command> commandList = CommandUtil.processInputString(input);
        assertThat(commandList.get(0).getCommandAction()).isEqualTo(CommandAction.MOVE);
        assertThat(commandList.get(1).getCommandAction()).isEqualTo(CommandAction.PLACE);
        assertThat(commandList.get(2).getCommandAction()).isEqualTo(CommandAction.LEFT);
        assertThat(commandList.get(3).getCommandAction()).isEqualTo(CommandAction.RIGHT);
        assertThat(commandList.get(4).getCommandAction()).isEqualTo(CommandAction.REPORT);
    }

    @Test
    public void testLowercaseProcessingSuccess(){
        String input = "move place 1,0,NORTH  left right report";

        List<Command> commandList = CommandUtil.processInputString(input);
        assertThat(commandList.get(0).getCommandAction()).isEqualTo(CommandAction.MOVE);
        assertThat(commandList.get(1).getCommandAction()).isEqualTo(CommandAction.PLACE);
        assertThat(commandList.get(2).getCommandAction()).isEqualTo(CommandAction.LEFT);
        assertThat(commandList.get(3).getCommandAction()).isEqualTo(CommandAction.RIGHT);
        assertThat(commandList.get(4).getCommandAction()).isEqualTo(CommandAction.REPORT);
    }

    @Test
    public void testNullCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Command cannot be empty");
        String input = null;
        CommandUtil.processInputString(input);
    }

    @Test
    public void testEmptyCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Command cannot be empty");
        String input = "";
        CommandUtil.processInputString(input);
    }

    @Test
    public void testInvalidCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Command [MOVEE] does not Exist");
        String input = "MOVEE";
        CommandUtil.processInputString(input);
    }
    @Test
    public void testInvalidDirectionInCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Invalid facing [NOrtr] in PLACE command");
        String input = "PLACE 1,0,NOrtr";
        CommandUtil.processInputString(input);
    }

    @Test
    public void testInvalidPositionCoordinatesInCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Invalid position coordinates [For input string: \"X\"] in PLACE command");
        String input = "PLACE 1,X,NORTH";
        CommandUtil.processInputString(input);
    }
    @Test
    public void testMissingPlaceCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Command [1,1,NORTH] does not Exist");
        String input = "1,1,NORTH";
        CommandUtil.processInputString(input);
    }
    @Test
    public void testSpacesInPLACEArgumentsSucceeds(){
        String input = "PLACE 1, 1, NORTH     REPORT";
        List<Command> commandList = CommandUtil.processInputString(input);
        assertThat(commandList.get(0).getCommandAction()).isEqualTo(CommandAction.PLACE);
        assertThat(commandList.get(1).getCommandAction()).isEqualTo(CommandAction.REPORT);

        input = "    PLACE   1  , 1  , NORTH     REPORT";
        commandList = CommandUtil.processInputString(input);
        assertThat(commandList.get(0).getCommandAction()).isEqualTo(CommandAction.PLACE);
        assertThat(commandList.get(1).getCommandAction()).isEqualTo(CommandAction.REPORT);
    }
    @Test
    public void testMissingDirectionInPlaceCommandThrowsInvalidCommandException(){
        expectedEx.expect(InvalidCommandException.class);
        expectedEx.expectMessage("Wrong no of arguments for PLACE, found 1,1");
        String input = "REPORT PLACE 1,1 MOVE";
        CommandUtil.processInputString(input);
    }
}
