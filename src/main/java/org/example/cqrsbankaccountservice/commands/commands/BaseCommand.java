package org.example.cqrsbankaccountservice.commands.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter
    protected T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
