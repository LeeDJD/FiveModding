package space.kappes.FiveModding.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventManager {

    private final Logger logger = LogManager.getLogger(getClass());
    private final List<ListenerAdapter> listenerAdapterList;

    public EventManager() {
        this.listenerAdapterList = new ArrayList<>();
    }

    public void addListener(ListenerAdapter listenerAdapter){
        listenerAdapterList.add(listenerAdapter);
    }

    public void addListeners(ListenerAdapter... listenerAdapters){
        listenerAdapterList.addAll(Arrays.asList(listenerAdapters));
    }

    public void call(Event event){
        listenerAdapterList.forEach(listenerAdapter -> {
            try {
                new Thread(() -> listenerAdapter.onEvent(event), "EventExecutor").start();
            } catch (Exception e){
                logger.error("[EventManager] Unhandled exception in event listener", e);
            }
        });
    }

}
