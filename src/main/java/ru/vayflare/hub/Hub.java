package ru.vayflare.hub;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(id = "hub", name = "Hub", version = "1.0", description = "Just a plugin on /hub.", authors = {"Vayflare"})
public class Hub {

    /**
     * The logger instance used for logging messages.
     */
    @Inject
    public Logger logger;

    /**
     * The proxy server instance used by this plugin.
     */
    @Inject
    public ProxyServer server;

    /**
     * Handles the proxy initialization event by registering the /hub command.
     *
     * @param event The proxy initialization event.
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = server.getCommandManager();
        CommandMeta commandMeta = commandManager.metaBuilder("hub")
                .aliases("lobby")
                .plugin(this)
                .build();
        SimpleCommand command = new HubCommand(server);
        try {
            commandManager.register(commandMeta, command);
            logger.info("Command /hub registration is successful");
        } catch (Exception e) {
            logger.error("Command /hub registration is failed");
        }
    }
}