package com.company.toyrobotsimulator.util;

import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.command.CommandAction;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandUtilTest {

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
}
