package space.kappes.FiveModding.util;

import net.dv8tion.jda.api.EmbedBuilder;

public abstract class EmbedUtil extends SafeMessage {

    public static EmbedBuilder success(String title, String description) {
        return new EmbedBuilder().setDescription(description).setTitle(":white_check_mark: " + title).setColor(Colors.GREEN);
    }

    public static EmbedBuilder error(String title, String description) {
        return new EmbedBuilder().setDescription(description).setTitle(":x: " + title).setColor(Colors.RED);
    }

    public static EmbedBuilder info(String title, String description) {
        return new EmbedBuilder().setDescription(description).setTitle(":information_source: " + title).setColor(Colors.BLUE);
    }

}

