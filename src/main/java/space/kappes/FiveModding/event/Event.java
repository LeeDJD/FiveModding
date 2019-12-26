package space.kappes.FiveModding.event;

import java.time.LocalDateTime;

public abstract class Event {

    private final LocalDateTime localDateTime = LocalDateTime.now();

    public Event() {
    }
}
