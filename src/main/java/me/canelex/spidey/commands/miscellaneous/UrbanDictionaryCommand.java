package me.canelex.spidey.commands.miscellaneous;

import me.canelex.jda.api.entities.Message;
import me.canelex.spidey.objects.command.Category;
import me.canelex.spidey.objects.command.ICommand;
import me.canelex.spidey.objects.json.UrbanDictionary;
import me.canelex.spidey.utils.Emojis;
import me.canelex.spidey.utils.Utils;

@SuppressWarnings("unused")
public class UrbanDictionaryCommand implements ICommand
{
	@Override
	public final void action(final String[] args, final Message message)
	{
		final var term = message.getContentRaw().substring(5);
		final var channel = message.getChannel();
		final var ud = new UrbanDictionary(term);
		if (!ud.exists())
		{
			Utils.sendMessage(channel, ":no_entry: Query not found.");
			return;
		}
		final var result = String.format("Urban Dictionary \n\n"
						+ "Definition for **%s**: \n"
						+ "```\n"
						+ "%s\n"
						+ "```\n"
						+ "**example**: \n"
						+ "%s" + "\n\n"
						+ "_by %s (" + Emojis.LIKE + "%s  " + Emojis.DISLIKE + "%s)_"
				, ud.getWord(), ud.getDefinition(), ud.getExample(),
				ud.getAuthor(), ud.getLikes(), ud.getDislikes());
		Utils.sendMessage(channel, result);
	}

	@Override
	public final String getDescription() { return "Returns the definition(s) of a phrase"; }
	@Override
	public final String getInvoke() { return "ud"; }
	@Override
	public final Category getCategory() { return Category.MISC; }
	@Override
	public final String getUsage() { return "s!ud <phrase>"; }
}