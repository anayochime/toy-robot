package com.company.toyrobotsimulator.web;

import com.company.toyrobotsimulator.model.web.CommandResult;
import com.company.toyrobotsimulator.model.web.InputCommand;
import com.company.toyrobotsimulator.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toy-robot/rest/command")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CommandResult processCommand(@RequestBody InputCommand inputCommand){
        String report = commandService.processInput(inputCommand.getInput());
        return new CommandResult(report);
    }
}
