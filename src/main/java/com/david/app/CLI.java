package com.david.app;

import com.github.rvesse.airline.annotations.Cli;

@Cli(name = "basic",
        description = "Provides a basic example CLI",
        defaultCommand = Calculator.class,
        commands = {Calculator.class})
public class CLI{
    public static void main(String... args) {
        com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(CLI.class);
        // Analiza los argumentos de la CLI y devuelve una instancia del comando correspondiente
        Runnable cmd = cli.parse(args);
        cmd.run();
    }
}
