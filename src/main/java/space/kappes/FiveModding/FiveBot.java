package space.kappes.FiveModding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import space.kappes.FiveModding.event.EventManager;
import space.kappes.FiveModding.net.ShardManagerHandler;

import java.io.File;
import java.io.IOException;

public class FiveBot {

    private static FiveBot instance;
    private final Logger logger = LogManager.getLogger(getClass());
    private ShardManagerHandler shardManagerHandler;
    private EventManager eventManager;

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
            eventManager = new EventManager();

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
        shardManagerHandler.close("Shutdownhook triggered");
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
}
