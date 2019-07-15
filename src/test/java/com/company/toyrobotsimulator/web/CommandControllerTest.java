package com.company.toyrobotsimulator.web;

import com.company.toyrobotsimulator.model.web.InputCommand;
import com.company.toyrobotsimulator.service.CommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CommandController.class)
public class CommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandService commandService;

    private String targetUrl = "/toy-robot/rest/command";

    @Test
    public void postCommand_Succeeds() throws Exception {
        String input = "MOVE PLACE 1,0,NORTH REPORT";
        InputCommand command = new InputCommand(input);

        given(commandService.processInput(input)).willReturn("1,0,NORTH");

        String requestBody = new ObjectMapper().writeValueAsString(command);

        mockMvc.perform(MockMvcRequestBuilders.post(targetUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.report").value("1,0,NORTH"));
    }
}