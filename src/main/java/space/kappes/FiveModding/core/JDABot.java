package space.kappes.FiveModding.core;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.command.CommandManager;
import space.kappes.FiveModding.commands.CommandHelp;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class JDABot {

    private final Logger logger = LogManager.getLogger(getClass());
    private int totalShards;
    private ShardManager shardManager;
    private CommandManager commandManager;
    private boolean connected = true;

    public JDABot() {
        initialize();
    }

    private void initialize() {
        commandManager = new CommandManager();
        registerCommands();
    }

    private void registerCommands() {
        commandManager.registerCommand(new CommandHelp());
    }

    public void connect(int totalShards, Integer[] shardIds) throws LoginException {
        this.totalShards = shardIds.length;
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder()
                .setToken(FiveBot.getInstance().getConfigManager().getBotToken())
                .setShardsTotal(totalShards)
                .setShards(Arrays.asList(shardIds))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.watching("the boot sequence..."));
        registerEvents(builder);
        shardManager = builder.build();
        connected = true;
    }

    private void registerEvents(DefaultShardManagerBuilder builder) {
        builder.addEventListeners(
                commandManager,
                new ReadyListener()
        );

    }

    public int getTotalShards() {
        return totalShards;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
