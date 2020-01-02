package space.kappes.FiveModding.commands.utility;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;

public class CommandSources extends Command {

    public CommandSources() {
        super(new String[]{"sources", "source", "discord"}, CommandCategory.UTILITY, "", "Links to all the sources used to create this bot");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        return send(
                new EmbedBuilder()
                .setTitle("Creator and support discords and general sources. Visit them and support them :slight_smile:")
                .setDescription(" Comunista_CZ (English / Czech)\n" +
                        "Discord - https://discord.gg/xAuaR7R\n" +
                        "Twitch - https://twitch.tv/Comunista_CZ\n" +
                        "Youtube - https://www.youtube.com/user/AdamProGamingCZ/videos?view\n" +
                        "\n" +
                        "Gabz (English/French)\n" +
                        "Discord - https://discord.gg/q2M6JVN\n" +
                        "\n" +
                        "MrBrown (Danish/English) \n" +
                        "Twitch - https://www.twitch.tv/mrbrown1999\n" +
                        "Discord - https://discord.gg/XbPcyQX \n" +
                        "\n" +
                        "dons (English)\n" +
                        "Discord: https://discordapp.com/invite/4XHPw8W\n" +
                        "Twitch: https://twitch.tv/donsbro\n" +
                        "\n" +
                        " Breze (Swedish / English)\n" +
                        "Twitch - https://www.twitch.tv/brezedc \n" +
                        "Discord - https://discord.gg/gZmZKRP \n" +
                        "\n" +
                        "Tobiii (English)\n" +
                        "Twitch - https://www.twitch.tv/Tobiii\n" +
                        "Discord - https://discord.gg/t4CFXBn\n" +
                        "Youtube - https://www.youtube.com/user/TobiiCommentary\n" +
                        "\n" +
                        "Slb2k11/Yuri (English / German)\n" +
                        "Twitch - https://www.twitch.tv/slb2k11\n" +
                        "Discord - https://discord.gg/rWpZMWg\n" +
                        "\n" +
                        "GiZz (English)\n" +
                        "Discord - https://discord.gg/tqbWkzT\n" +
                        "\n" +
                        "Dekurwinator (English / Polish)\n" +
                        "Discord - https://discord.gg/UfpdnsB \n" +
                        "website - https://dekurwinator-mods.bitrix24.site/\n" +
                        "\n" +
                        "CodeWalker (dexyfex) (English)\n" +
                        "Discord - https://discord.gg/mzPgRZZ\n" +
                        "\n" +
                        "Tarkayne Mapping\n" +
                        "Discord - https://discord.gg/wH99bBQ\n" +
                        "Twitch - https://twitch.tv/Tarkayne")
        );
    }
}
