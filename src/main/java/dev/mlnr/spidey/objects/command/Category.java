package dev.mlnr.spidey.objects.command;

public enum Category
{
    MODERATION("\uD83D\uDD28 Moderation"),
    MISC("\uD83D\uDCA1 Miscellaneous"),
    UTILITY("\uD83D\uDEE0 Utility"),
    INFORMATIVE("\u2139 Informative"),
    NSFW("\uD83D\uDD1E NSFW"),
    FUN("\uD83D\uDE03 Fun"),
    MUSIC("\uD83C\uDFB6 Music"),
    SETTINGS("\u2699\uFE0F Settings");

    private final String friendlyName;

    Category(final String friendlyName)
    {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName()
    {
        return friendlyName;
    }
}