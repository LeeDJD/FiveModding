package space.kappes.FiveModding.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandEvent {

    String getPrefix();

    String getInvoke();

    MessageReceivedEvent getExecutionEvent();

    User getAuthor();

    Guild getGuild();

    Member getMember();

    Message getMessage();

    TextChannel getTextChannel();

    JDA getJDA();

    long getMessageId();

    PrivateChannel getPrivateChannel();

    String getArgsAsString();


}
