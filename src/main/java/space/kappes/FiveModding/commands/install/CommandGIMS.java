package space.kappes.FiveModding.commands.install;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;

public class CommandGIMS extends Command {

    public CommandGIMS() {
        super(new String[]{"gims","3dsmax","gimsevo","evo"}, CommandCategory.INSTALL, "", "Get installation Instructions for GIMS EVO for 3ds Max");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        return send(new EmbedBuilder()
            .setDescription("There a 2 Version of GIMS EVO. Below you find install instructions and download links for your version of 3DS Max. You don't have 3DS Max ,yet? You can download a free version for your learning process at the [Autodeks Website](https://www.autodesk.com/education/home).Do note it is not legal to use anything created with this version commercially.")
            .addField("**3DS Max 9 to 2016**","[Download](https://www.gta5-mods.com/tools/gims-evo-with-gta-v-support) then extract the rar and drag the scripts directory to your 3DS Max Folder finally open the utilities tab in 3DS Max and press on the door like Icon [Image](https://i.imgur.com/SmmO75V.png).",false)
            .addField("**3DS max 2017 to 2020**", "[Download](https://cdn.discordapp.com/attachments/633562202548338688/659905764013506570/GIMS_2017_to_2020.zip) then extract the rar and drag the scripts directory to your 3DS Max Folder after that press WIN+R enter `%localappdata%` and drag the GIMS folder there finally open the utilities tab in 3DS Max and press on the door like Icon [Image](https://i.imgur.com/SmmO75V.png).",false)
            .addField("**All Version**", String.format("Use Codewalkers(%scodewalker) RPF explorer to find the files asked in the pop-up and save them in a save folder and then select said folder after pressing Ok. Note if you delete that folder GIMS stops working!", FiveBot.DEFAULT_PREFIX), false)
            .setImage("https://i2.wp.com/openiv.com/imgaes/GIMSEvo_logo.png")
        );
    }
}
