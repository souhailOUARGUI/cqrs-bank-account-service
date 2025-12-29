package org.example.cqrsbankaccountservice.commands.controllers;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.cqrsbankaccountservice.commands.commands.CreateAccountCommand;
import org.example.cqrsbankaccountservice.commands.dtos.CreateAccountRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {
    private final CommandGateway commandGateway; // Bus de commande [14]

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
        // Envoi de la commande dans le bus [15]
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()
        ));
    }

    // Gestion des erreurs [16]
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }
}
