package space.kappes.FiveModding.command.impl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.command.CommandEvent;

public class CommandEventImpl implements CommandEvent {

    private final String guildPrefix;
    private String invoke;
    private final MessageReceivedEvent messageReceivedEvent;
    private String argsAsString;

    public CommandEventImpl(String guildPrefix, String invoke, MessageReceivedEvent messageReceivedEvent, String argsAsString) {
        this.guildPrefix = guildPrefix;
        this.invoke = invoke;
        this.messageReceivedEvent = messageReceivedEvent;
        this.argsAsString = argsAsString;
    }


    @Override
    public String getPrefix() {
        return guildPrefix;
    }

    @Override
    public String getInvoke() {
        return invoke;
    }

    @Override
    public MessageReceivedEvent getExecutionEvent() {
        return messageReceivedEvent;
    }

    @Override
    public User getAuthor() {
        return messageReceivedEvent.getAuthor();
    }

    @Override
    public Guild getGuild() {
        return messageReceivedEvent.getGuild();
    }

    @Override
    public Member getMember() {
        return messageReceivedEvent.getMember();
    }

    @Override
    public Message getMessage() {
        return messageReceivedEvent.getMessage();
    }

    @Override
    public TextChannel getTextChannel() {
        return messageReceivedEvent.getTextChannel();
    }

    @Override
    public JDA getJDA() {
        return messageReceivedEvent.getJDA();
    }

    @Override
    public long getMessageId() {
        return messageReceivedEvent.getMessageIdLong();
    }

    @Override
    public PrivateChannel getPrivateChannel() {
        return getAuthor().openPrivateChannel().complete();
    }

    @Override
    public String getArgsAsString() {
        return argsAsString;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    public void setArgsAsString(String argsAsString) {
        this.argsAsString = argsAsString;
    }
}
