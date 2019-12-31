package space.kappes.FiveModding.core;

import space.kappes.FiveModding.event.EventManager;
import space.kappes.FiveModding.net.listeners.LoginHandler;

public class EventRegistry {

    public EventRegistry(EventManager eventManager) {
        eventManager.addListeners(
                new ConnectionListener(),
                new LoginHandler()
        );
    }

}
