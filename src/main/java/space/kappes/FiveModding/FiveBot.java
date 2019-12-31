package space.kappes.FiveModding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.kappes.FiveModding.core.EventRegistry;
import space.kappes.FiveModding.core.JDABot;
import space.kappes.FiveModding.event.EventManager;
import space.kappes.FiveModding.io.config.Config;
import space.kappes.FiveModding.io.config.ConfigManager;
import space.kappes.FiveModding.net.ShardManagerHandler;
import space.kappes.FiveModding.net.command.NetCommandManager;
import space.kappes.FiveModding.net.command.NetCommandRegistry;

import java.io.File;
import java.io.IOException;

public class FiveBot {

    public static final String DEFAULT_PREFIX = "fv!";
    private static FiveBot instance;
    private final Logger logger = LogManager.getLogger(getClass());
    private ShardManagerHandler shardManagerHandler;
    private EventManager eventManager;
    private NetCommandManager netCommandManager;
    private JDABot fivebot;
    private Config config;
    private ConfigManager configManager;

    private FiveBot(String[] args) {
        instance = this;
        logger.info("Launching FiveBot...");
        registerShutdownHook();
        createDirectories();
        initialize();
    }

    private void createDirectories() {
        new File("logs").mkdirs();
    }

    private void initialize() {
        try {
            config = new Config();
            configManager = new ConfigManager(config);
            eventManager = new EventManager();
            new EventRegistry(eventManager);
            netCommandManager = new NetCommandManager();
            new NetCommandRegistry(netCommandManager);
            eventManager.addListener(netCommandManager);
            fivebot = new JDABot();
            shardManagerHandler = new ShardManagerHandler();
        } catch (IOException e) {
            logger.error("Error while initializing", e);
            System.exit(1);
        }
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                shutdown();
            }catch (Exception e) {
                logger.error("Error while the shutdown...", e);
            }
        }));
    }

    public void shutdown() throws IOException {
        shardManagerHandler.close("ShutdownHook triggered");
    }

    public static void main(String[] args) {
        if (instance != null)
            throw new RuntimeException("Bot can't be instanced more than once!");
        else
            new FiveBot(args);
    }

    public static FiveBot getInstance() {
        return instance;
    }

    public ShardManagerHandler getShardManagerHandler() {
        return shardManagerHandler;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public Config getConfig() {
        return config;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public JDABot getFiveBot() {
        return fivebot;
    }

    public static boolean isOwner(long id) {
        return id == 153507094933274624L;
    }
}
