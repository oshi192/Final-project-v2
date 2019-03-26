package controller;

import controller.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandMannager {
    private static Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("login", new CommandLogin());
        commands.put("logout", new CommandLogout());
        commands.put("", new CommandHomePage());
        commands.put("/taxi", new CommandHomePage());
        commands.put("registration", new CommandRegister());
        commands.put("taxis", new CommandAdminTaxis());
        commands.put("history", new CommandUserHistory());
        commands.put("order", new CommandOrderATaxi());
        commands.put("car-types", new CommandCarTypes());
        commands.put("discounts", new CommandDiscount());
    }

    private CommandMannager() {
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }

}
