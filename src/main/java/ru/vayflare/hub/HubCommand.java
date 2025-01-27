package ru.vayflare.hub;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Adds a command for an easier return to the lobby.
 */
public class HubCommand implements SimpleCommand {

    /**
     * The proxy server instance used by this command.
     */
    private final ProxyServer server;

    /**
     * Constructs a new instance of the HubCommand class.
     *
     * @param server The proxy server instance.
     */
    public HubCommand(ProxyServer server) {
        this.server = server;
    }

    /**
     * Executes the command to connect a player to the lobby server.
     *
     * @param invocation The invocation context for the command.
     */
    @Override
    public void execute(final Invocation invocation) {
        // You Player?
        if (!(invocation.source() instanceof  Player player)) {
            invocation.source().sendMessage(Component.text("Only players can use this command.", NamedTextColor.RED));
            return;
        }
        // Is there a "lobby" server?
        RegisteredServer toConnect = this.server.getServer("lobby").orElse(null);
        if (toConnect == null) {
            player.sendMessage(Component.text("Lobby server not found.", NamedTextColor.RED));
            return;
        }
        // Is your server == “lobby”?
        if (player.getCurrentServer().isPresent() && player.getCurrentServer().get().getServerInfo().getName().equals("lobby")) {
            player.sendMessage(Component.text("You are already connected to the lobby server.", NamedTextColor.RED));
            return;
        }
        // Connect to server "lobby"
        player.createConnectionRequest(toConnect).connect().thenAccept(result -> {
            if (result.isSuccessful()) {
                player.sendMessage(Component.text("Teleported to lobby server.", NamedTextColor.GREEN));
            } else {
                player.sendMessage(Component.text("Failed to teleport to lobby server.", NamedTextColor.RED));
            }
        });
    }

    /**
     * Checks if the invocation source has permission to execute this command.
     * Currently, this command allows execution by anyone.
     *
     * @param invocation The invocation context for the command.
     * @return Always true, indicating that anyone can execute this command.
     */
    @Override
    public boolean hasPermission(final Invocation invocation) {
        return true;
    }

    /**
     * Provides suggestions for the command. Currently returns an empty list.
     *
     * @param invocation The invocation context for the command.
     * @return An empty list of suggestions.
     */
    @Override
    public List<String> suggest(final Invocation invocation) {
        return List.of();
    }

    /**
     * Provides asynchronous suggestions for the command. Currently returns an empty list.
     *
     * @param invocation The invocation context for the command.
     * @return A completed future with an empty list of suggestions.
     */
    @Override
    public CompletableFuture<List<String>> suggestAsync(final Invocation invocation) {
        return CompletableFuture.completedFuture(List.of());
    }
}