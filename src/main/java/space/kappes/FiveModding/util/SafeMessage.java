package space.kappes.FiveModding.util;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.RestAction;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;


public class SafeMessage {
    public static void sendMessage(TextChannel textChannel, Message message) {
        if (hasPermissions(textChannel) && message.getContentRaw().toCharArray().length < 1999)
            send(textChannel, message).queue();
    }

    public static void sendMessage(TextChannel textChannel, Message message, int deleteTime) {
        try {
            if (hasPermissions(textChannel) && message.getContentRaw().toCharArray().length < 1999)
                send(textChannel, message).queue(msg -> msg.delete().queueAfter(deleteTime, TimeUnit.SECONDS));
        } catch (Exception e) {
            // Ignored
        }
    }


    public static Message sendMessageBlocking(TextChannel textChannel, Message message) {
        if (hasPermissions(textChannel) && message.getContentRaw().toCharArray().length < 1999)
            return send(textChannel, message).complete();
        return null;
    }

    public static void sendMessage(TextChannel textChannel, String message) {
        if (hasPermissions(textChannel) && message.toCharArray().length < 1999)
            send(textChannel, new MessageBuilder().setContent(message).build()).queue();
    }

    public static void sendMessage(TextChannel textChannel, String message, int deleteTime) {
        if (hasPermissions(textChannel) && hasDeletePermission(textChannel) && message.toCharArray().length < 1999)
            send(textChannel, new MessageBuilder().setContent(message).build()).queue(msg -> msg.delete().queueAfter(deleteTime, TimeUnit.SECONDS));
        else if (hasPermissions(textChannel))
            send(textChannel, new MessageBuilder().setContent(message).build()).queue();

    }

    public static void sendMessage(TextChannel textChannel, MessageEmbed build) {
        if (hasPermissions(textChannel))
            send(textChannel, build).queue();
    }

    public static void sendMessage(TextChannel textChannel, MessageEmbed build, int deleteTime) {
        if (hasPermissions(textChannel) && hasDeletePermission(textChannel))
            send(textChannel, build).queue(msg -> msg.delete().queueAfter(deleteTime, TimeUnit.SECONDS));
        else if (hasPermissions(textChannel))
            send(textChannel, build).queue();
    }

    public static Message sendMessageBlocking(TextChannel textChannel, String message) {
        if (hasPermissions(textChannel) && message.toCharArray().length < 1999)
            return send(textChannel, new MessageBuilder().setContent(message).build()).complete();
        return null;
    }

    public static Message sendMessageBlocking(TextChannel textChannel, MessageEmbed message) {
        if (hasPermissions(textChannel))
            return send(textChannel, message).complete();
        return null;
    }

    public static void sendMessage(PrivateChannel privateChannel, Message message) {
        if (message.getContentRaw().toCharArray().length < 1999)
            send(privateChannel, message).queue();
    }

    public static Message sendMessageBlocking(PrivateChannel privateChannel, Message message) {
        if (message.getContentRaw().toCharArray().length < 1999)
            return send(privateChannel, message).complete();
        return null;
    }

    public static void sendMessage(PrivateChannel privateChannel, String message) {
        if (message.toCharArray().length < 1999)
            send(privateChannel, new MessageBuilder().setContent(message).build()).queue();
    }

    public static void sendMessage(PrivateChannel privateChannel, MessageEmbed build) {
        send(privateChannel, build).queue();
    }

    public static Message sendMessageBlocking(PrivateChannel privateChannel, String message) {
        if (message.toCharArray().length < 1999)
            return send(privateChannel, new MessageBuilder().setContent(message).build()).complete();
        return null;
    }

    public static Message sendMessageBlocking(PrivateChannel privateChannel, MessageEmbed message) {
        return send(privateChannel, message).complete();
    }


    private static boolean hasPermissions(TextChannel channel) {
        return channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_WRITE) && channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_READ);
    }

    private static boolean hasEmbedPermissions(TextChannel channel) {
        return !channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_EMBED_LINKS);
    }

    private static RestAction<Message> send(TextChannel textChannel, Message msg) {
        if (hasEmbedPermissions(textChannel) && !msg.getEmbeds().isEmpty())
            return textChannel.sendMessage(formatEmbed(msg.getEmbeds().get(0)));
        else
            return textChannel.sendMessage(msg);
    }

    private static RestAction<Message> send(TextChannel textChannel, MessageEmbed msg) {
        if (hasEmbedPermissions(textChannel))
            return textChannel.sendMessage(formatEmbed(msg));
        else
            return textChannel.sendMessage(msg);
    }

    private static RestAction<Message> send(PrivateChannel privateChannel, Message msg) {
        if (!msg.getEmbeds().isEmpty())
            try {
                return privateChannel.sendMessage(formatEmbed(msg.getEmbeds().get(0)));
            } catch (Exception e) {
                return null;
            }
        else
            try {
                return privateChannel.sendMessage(msg);
            } catch (Exception e) {
                return null;
            }
    }

    private static RestAction<Message> send(PrivateChannel privateChannel, MessageEmbed msg) {
        try {
            return privateChannel.sendMessage(formatEmbed(msg));
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatEmbed(MessageEmbed embed) {
        StringBuilder string = new StringBuilder();
        if (embed.getTitle() != null)
            string.append("**__").append(embed.getTitle()).append("__**").append("\n");
        if (embed.getDescription() != null)
            string.append(embed.getDescription());
        embed.getFields().forEach(field -> string.append("**__").append(field.getName()).append("__**\n").append(field.getValue()).append("\n"));
        if (embed.getFooter() != null)
            string.append("\n").append("_").append(embed.getFooter().getText()).append("_");
        String out = string.toString();
        if (string.length() > 1024)
            out = "This message is longer than 1024 chars, please give me `MESSAGE_EMBED_LINKS` permission and try again";
        return out;
    }

    private static boolean hasDeletePermission(TextChannel channel) {
        return channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_MANAGE);
    }

    public static void sendFile(TextChannel channel, Message message, InputStream image) {
        if (hasPermissions(channel))
            channel.sendFile(image, "file.png").content(message.getContentDisplay()).queue();
    }

    public static Message sendFileBlocking(TextChannel channel, Message message, InputStream image) {
        if (hasPermissions(channel))
            return channel.sendFile(image, "file.png").content(message.getContentDisplay()).complete();
        return null;
    }

}
