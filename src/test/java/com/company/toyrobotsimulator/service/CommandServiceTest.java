package com.company.toyrobotsimulator.service;

import com.company.toyrobotsimulator.simulator.Simulator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandServiceTest {

    @Mock
    private Simulator simulator;

    private CommandService commandService;

    @Before
    public void setUp() {
        commandService = new CommandServiceImpl(simulator);
    }

    @Test
    public void testInputCommandAreProcessedSuccessfully(){
        String inputStrings = "MOVE PLACE 1,1,WEST";

        given(simulator.processInput(inputStrings)).willReturn(Collections.singletonList("1,1,WEST"));

        String result = commandService.processInput(inputStrings);

        assertThat(result).isEqualTo("1,1,WEST");
        verify(simulator, times(1)).processInput(inputStrings);
    }
}
