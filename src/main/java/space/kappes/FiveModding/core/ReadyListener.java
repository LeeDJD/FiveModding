package space.kappes.FiveModding.core;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.event.impl.bot.AllShardsInitializedEvent;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class ReadyListener extends ListenerAdapter {

    private static int initializedShardCount = 0;
    private final static Timer timer = new Timer();
    private HashMap<OnlineStatus, Activity> gameMap = new HashMap<>();

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        initializedShardCount++;
        if (FiveBot.getInstance().getFiveBot().getTotalShards() == initializedShardCount) {
            FiveBot.getInstance().getEventManager().call(new AllShardsInitializedEvent());
            initializedShardCount = 0;
        }
        gameMap.put(OnlineStatus.IDLE, Activity.watching("out for YMAP houses"));
        gameMap.put(OnlineStatus.ONLINE, Activity.listening("fv!help"));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                Object key = gameMap.keySet().toArray()[random.nextInt(0, gameMap.size() - 1)];
                FiveBot.getInstance().getFiveBot().getShardManager().setPresence((OnlineStatus) key, gameMap.get(key));
            }
        }, 0, 30000);
    }

    public static Timer getTimer() {
        return timer;
    }
}
