package dev.mlnr.spidey.utils;

import dev.mlnr.spidey.utils.requests.API;
import dev.mlnr.spidey.utils.requests.Requester;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.data.DataObject;

import java.awt.*;

public class KSoftAPIHelper
{
    private KSoftAPIHelper() {}

    public static MessageEmbed getImage(final String query, final Member author, final boolean nsfw)
    {
        final var eb = Utils.createEmbedBuilder(author.getUser());
        final var json = getImageJson(query);
        eb.setColor(nsfw ? Color.PINK : Color.GREEN);
        eb.setAuthor(json.getString("title"), json.getString("source"));
        eb.setImage(json.getString("image_url"));
        eb.setDescription("A random post from [r/" + query + "](https://reddit.com/r/" + query + ")");
        return eb.build();
    }

    private static DataObject getImageJson(final String query)
    {
        return Requester.executeRequest("https://api.ksoft.si/images/rand-reddit/" + query + "?span=month", API.KSOFT);
    }
}