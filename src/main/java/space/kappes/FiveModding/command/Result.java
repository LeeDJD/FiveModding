package space.kappes.FiveModding.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;

public class Result {

    private String string = null;
    private EmbedBuilder embedBuilder = null;
    private Message message = null;

    public Result(String string) {
        this.string = string;
    }

    public Result(EmbedBuilder embedBuilder) {
        this.embedBuilder = embedBuilder;
    }

    public Result(Message message) {
        this.message = message;
    }

    public Message build() {
        Message msg;
        if (string != null) {
            msg = new MessageBuilder(string).build();
        } else if (embedBuilder != null) {
            msg = new MessageBuilder().setEmbed(embedBuilder.build()).build();
        } else if (message != null) {
            msg = message;
        } else
            throw new RuntimeException("Message returnable cannot be null.");
        return msg;
    }
}

