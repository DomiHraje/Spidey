package dev.mlnr.spidey.objects.messages;

import net.dv8tion.jda.api.entities.Message;

import java.time.Instant;

public class MessageData
{
    private final long messageId;
    private final long authorId;
    private final Instant creation;
    private final String content;
    private final long channelId;
    private final long guildId;
    private final String jumpUrl;

    public MessageData(final Message message)
    {
        this.messageId = message.getIdLong();
        this.authorId = message.getAuthor().getIdLong();
        this.creation = message.getTimeCreated().toInstant();
        this.content = message.getContentRaw();
        this.channelId = message.getTextChannel().getIdLong();
        this.guildId = message.getGuild().getIdLong();
        this.jumpUrl = message.getJumpUrl();
    }

    public long getId()
    {
        return this.messageId;
    }

    public long getAuthorId()
    {
        return this.authorId;
    }

    public Instant getCreation()
    {
        return this.creation;
    }

    public String getContent()
    {
        return this.content;
    }

    public long getChannelId()
    {
        return this.channelId;
    }

    public long getGuildId()
    {
        return this.guildId;
    }

    public String getJumpUrl()
    {
        return this.jumpUrl;
    }
}