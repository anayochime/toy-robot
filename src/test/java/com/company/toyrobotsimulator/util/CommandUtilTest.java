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
}
