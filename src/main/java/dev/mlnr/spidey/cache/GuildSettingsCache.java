package dev.mlnr.spidey.cache;

import dev.mlnr.spidey.Core;
import dev.mlnr.spidey.DatabaseManager;
import dev.mlnr.spidey.objects.guild.GuildSettings;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GuildSettingsCache
{
    private static final Map<Long, GuildSettings> GUILD_SETTINGS_CACHE = new HashMap<>();

    private GuildSettingsCache() {}

    // getters

    public static long getLogChannelId(final long guildId)
    {
        return getGuildSettings(guildId).getLogChannelId();
    }

    public static long getJoinRoleId(final long guildId)
    {
        return getGuildSettings(guildId).getJoinRoleId();
    }

    public static String getPrefix(final long guildId)
    {
        return getGuildSettings(guildId).getPrefix();
    }

    public static long getDJRoleId(final long guildId)
    {
        return getGuildSettings(guildId).getDjRoleId();
    }

    public static boolean isSegmentSkippingEnabled(final long guildId)
    {
        return getGuildSettings(guildId).isSegmentSkippingEnabled();
    }

    public static boolean isSnipingEnabled(final long guildId)
    {
        return getGuildSettings(guildId).isSnipingEnabled();
    }

    public static boolean isVip(final long guildId)
    {
        return getGuildSettings(guildId).isVip();
    }

    // setters

    public static void setLogChannelId(final long guildId, final long logChannelId)
    {
        getGuildSettings(guildId).setLogChannelId(logChannelId);
    }

    public static void setJoinRoleId(final long guildId, final long joinRoleId)
    {
        getGuildSettings(guildId).setJoinRoleId(joinRoleId);
    }

    public static void setPrefix(final long guildId, final String prefix)
    {
        getGuildSettings(guildId).setPrefix(prefix);
    }

    public static void setDJRoleId(final long guildId, final long djRoleId)
    {
        getGuildSettings(guildId).setDjRoleId(djRoleId);
    }

    public static void setSegmentSkippingEnabled(final long guildId, final boolean segmentSkippingEnabled)
    {
        getGuildSettings(guildId).setSegmentSkippingEnabled(segmentSkippingEnabled);
    }

    public static void setSnipingEnabled(final long guildId, final boolean snipingEnabled)
    {
        getGuildSettings(guildId).setSnipingEnabled(snipingEnabled);
    }

    public static void setVip(final long guildId, final boolean vip)
    {
        getGuildSettings(guildId).setVip(vip);
    }

    // removals

    public static void removeLogChannel(final long guildId)
    {
        setLogChannelId(guildId, 0);
    }

    public static void removeJoinRole(final long guildId)
    {
        setJoinRoleId(guildId, 0);
    }

    public static void removeDJRole(final long guildId)
    {
        setDJRoleId(guildId, 0);
    }

    // misc helpers

    public static TextChannel getLogChannel(final long guildId)
    {
        final var logChannelId = getLogChannelId(guildId);
        return logChannelId == 0 ? null : Core.getJDA().getTextChannelById(logChannelId);
    }

    public static Role getJoinRole(final long guildId)
    {
        final var joinRoleId = getJoinRoleId(guildId);
        return joinRoleId == 0 ? null : Core.getJDA().getRoleById(joinRoleId);
    }

    public static Role getDJRole(final long guildId)
    {
        final var djRoleId = getDJRoleId(guildId);
        return djRoleId == 0 ? null : Core.getJDA().getRoleById(djRoleId);
    }

    // other

    public static void remove(final long guildId)
    {
        GUILD_SETTINGS_CACHE.remove(guildId);
    }

    private static GuildSettings getGuildSettings(final long guildId)
    {
        return Objects.requireNonNullElseGet(GUILD_SETTINGS_CACHE.get(guildId), () ->
        {
           final var settings = DatabaseManager.retrieveGuildSettings(guildId);
           GUILD_SETTINGS_CACHE.put(guildId, settings);
           return settings;
        });
    }
}