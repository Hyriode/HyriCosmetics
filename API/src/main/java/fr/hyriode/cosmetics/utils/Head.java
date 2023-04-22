package fr.hyriode.cosmetics.utils;

import com.avaje.ebeaninternal.server.lib.util.NotFoundException;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public enum Head {

    WHITE_MINUS("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNlNGI1MzNlNGJhMmRmZjdjMGZhOTBmNjdlOGJlZjM2NDI4YjZjYjA2YzQ1MjYyNjMxYjBiMjVkYjg1YiJ9fX0="),
    WHITE_PLUS("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBiNTVmNzQ2ODFjNjgyODNhMWMxY2U1MWYxYzgzYjUyZTI5NzFjOTFlZTM0ZWZjYjU5OGRmMzk5MGE3ZTcifX19"),

    SNOWMAN_HEAD("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU4ZDIwNmY2MWU2ZGU4YTc5ZDBjYjBiY2Q5OGFjZWQ0NjRjYmZlZmM5MjFiNDE2MGEyNTI4MjE2MzExMmEifX19"),
    SNOWMAN_BODY("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRkNmZlMjY3YTQxOGRjYzdmMzdhOGY3Njg1NWI1MzI4YjEzMDM4OTdiMzQyYTEwN2NmMTYyZjE0ZmUzZCJ9fX0="),

    REAPER("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJkYTY3MmE3M2YxY2UzMmE1MjUyYTBjZmMzY2RhZWUxNTA3YjdhZTQ5OGViOTU5OTc3NjI5ZDA4N2Y0NTJhZCJ9fX0="),
    GHOST("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWUxZTc2MzQwYzY4NmNjY2ZmZWQ1OGZjNzY4ZGUwZjI0MWExNWI1NzU1M2Y2MjJhMzc2NmEwMGRmYmI0NzRmYiJ9fX0="),

    BB_8("NDJkMjcxZjgyNjc0OTE2ZGNiNDdiMzcyYTU3MmY2MWU3NDcyZmUwNjNiMmM5MjQ2YWVlYjdjNTdhODgzNSJ9fX0="),
    BB_8_BODY("Yzg1Njk4MWNjZWM4YTQ5Mjg5OGUwY2I0ZDk0NDNkMjE0NTlkNWI2NWRmYThkYTQ2MmQxNzY5N2ExNzhmOGUifX19"),

    TWITCH("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI1ODA0ODliMmQ0NGU1ZDlhOWIzZjgzNmVmMjE5ZjAzMTI5OTJkNDBiMTRkOTlmNTZjNWFmMDVjNDBmNzE1In19fQ=="),
    YOUTUBE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI0ZTgyNWIyNWZmNzkzOGI1OGFmNTJmNThlNGQ1NGVjNTE0M2JkNWExYjQ4NGYxZmY5ZGQ2YmEwYWNhOWRjYiJ9fX0="),
    TIKTOK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmNmMjEwNWJiNzM3NjM4ODMzMDMzZGQ4MjQ0MDcxZTc1ODcwZTJlMTFjMjYxN2U1NDJlODkyNGZiMmI5MDE4MCJ9fX0="),
    HYRIODE("ewogICJ0aW1lc3RhbXAiIDogMTY4MjA4MDE1MTExNiwKICAicHJvZmlsZUlkIiA6ICIyYzA3NmUzNDU0N2M0OWU2OTMwYzQzZDE2MDZmYjI1ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJsUmVzdSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81MmRjOWU4YjJmZDQ0YmY4NTRkMjM1YzQ4MGQ2MmMwN2FkOWExMTQyZmZjY2Q5MWMzMjA1MDhjMDE5YzRmZjlkIgogICAgfQogIH0KfQ=="),

    BALLOON_COLOR_YELLOW("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTdmMzgxYTIwYTljNjQwNDI4MDc3MDcwY2M3YmQ5NWQ2ODg1OTJkMTEwNGNjYmNkNzEzNjQ5YTQ5ZTQxZWJmYiJ9fX0="),
    BALLOON_COLOR_LIGHT_BLUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UyOGE0OWNmMjNhNTM0NTM1YmU3MzZiOTA3NzJiMDg5NDE3MjI0YzgyZTIwMTE0NGRhNzEzZWE0ZWExNjdmNSJ9fX0="),
    BALLOON_COLOR_DARK_BLUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg2OGU2YTVjNGE0NDVkNjBhMzA1MGI1YmVjMWQzN2FmMWIyNTk0Mzc0NWQyZDQ3OTgwMGM4NDM2NDg4MDY1YSJ9fX0="),
    BALLOON_COLOR_BLUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjAzMzM4ZTFlOWFlNzdjYjI4YTE5NTc5MGZjYmMwNjAxYzY1ODg4MzBjYTQyOWFmMTkyMDVjM2UwNjQyYmVkNyJ9fX0="),
    BALLOON_COLOR_LIME("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2EyZGYzMTViNDM1ODNiMTg5NjIzMWI3N2JhZTFhNTA3ZGJkN2U0M2FkODZjMWNmYmUzYjJiOGVmMzQzMGU5ZSJ9fX0="),
    BALLOON_COLOR_GREEN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTI2ZWM3Y2QzYjZhZTI0OTk5NzEzN2MxYjk0ODY3YzY2ZTk3NDk5ZGEwNzFiZjUwYWRmZDM3MDM0MTMyZmEwMyJ9fX0="),
    BALLOON_COLOR_ORANGE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVmMTYyZGVmODQ1YWEzZGM3ZDQ2Y2QwOGE3YmY5NWJiZGZkMzJkMzgxMjE1YWE0MWJmZmFkNTIyNDI5ODcyOCJ9fX0="),
    BALLOON_COLOR_PINK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY4NTUyMmVlODE1ZDExMDU4N2ZmZmM3NDExM2Y0MTlkOTI5NTk4ZTI0NjNiOGNlOWQzOWNhYTlmYjZmZjVhYiJ9fX0="),
    BALLOON_COLOR_PURPLE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYzODdmYzI0Njg5M2Q5MmE2ZGQ5ZWExYjUyZGNkNTgxZTk5MWVlZWUyZTI2M2IyN2ZmZjFiY2YxYjE1NGViNyJ9fX0="),
    BALLOON_COLOR_RED("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJkZDExZGEwNDI1MmY3NmI2OTM0YmMyNjYxMmY1NGYyNjRmMzBlZWQ3NGRmODk5NDEyMDllMTkxYmViYzBhMiJ9fX0="),
    BALLOON_COLOR_GRAY("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWIwMDhhNDA1OGZiMjAzYTUwYjYyMzkyMjU4YzZiNWQ3Nzg2NDA2MmU3NTdhOWU2NjYzNzEyNGZmZTU3OTQ3NCJ9fX0="),

    BEACH("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTNjMmYxYzVkMmM4ZjBlMzM3MzBjMTRkY2ExYzFkMWUxYWJkODU5NmIwODM5ZDY3MzhkMThmNDY0MzJiNmZhNiJ9fX0="),

    JACK_O_LANTERN_YELLOW("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRhOTg5ZmFiNTUzZjM3OTE5MjYzOGY3MjU4OTMxM2Q5MTNmYzBiMzVlMGNjZjdiYWQwNzFkODcyNjA3ZWUzMSJ9fX0="),
    JACK_O_LANTERN_LIGHT_BLUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhjZTRkMjY4OTU2YmY0NzA4NjNiMGM4NTk1ZGM1ZjA2OTRkNjA0YmNiYzg1OWU2ZTY2MThmYTMwMTJkMGUwYyJ9fX0="),
    JACK_O_LANTERN_BLUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjM4NzI2NWQxMzE3Mjg0Nzk1MGVhNTA2YmEwZmYyYjA0Yjc5OTk4Mjg1MjVhMDMyOTA1N2E5ODk4NWRiMjVmYyJ9fX0="),
    JACK_O_LANTERN_LIME("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY3MDk5NTQ1MGJkYjcxMDlmYTViZmU2ZDEzZmVlZGUzMjRmNDM5NWZjOTAwOTAwMGM4MGVlNTU0ZmFmYTYzNyJ9fX0="),
    JACK_O_LANTERN_GREEN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQ3YzMyMzY3NmVkMWQyZTUyNjIyYTA2ZjJmNmJhNTE4MTJlNmM4NmE2NjNhZjUyYjBiNDY1NDlhNWU1ZmI1MSJ9fX0="),
    JACK_O_LANTERN_ORANGE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMyMWNlNDcwZjIzMmI5ZTczZmNlMDkxY2U4NDhkZTkzODYwYTAzMzgxZDMxMDBjNDJiMzk2YzAyNTRiZTBlZiJ9fX0="),
    JACK_O_LANTERN_PINK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTUyZjI4YWMyMmUwOWM1Yzc1NWJlNWVmOWI4N2NiZmI3ZjI4N2ZkZDg3Y2Y5Y2E1OGNlYTMyM2RiZmYyY2VjIn19fQ=="),
    JACK_O_LANTERN_PURPLE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQzYTFjMTU1ZmRlZWNlNmVmNzA0ZWQ1NzJkODU0ZGJkNjIxYzRmMDc5OTkwYzViYWY5MzE5ZWMyZTA2ZTI2YiJ9fX0="),
    JACK_O_LANTERN_RED("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWI3NzE5OGI2YzcwMDM2ZmU0YWMzNTk2MWFlMDcxZGMzY2RkMDE3NmVhMzdmMDkxMzU4MmEwMTVlMGIzNzNjMCJ9fX0="),
    JACK_O_LANTERN_GRAY("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODFiM2QxMmQxNmM5MDZlMjllODI1ZjU0M2JiYjQ2OGE2MTJjMzgzYjUzM2Q0Zjc4MjY4MDYzNjBiN2FlYzNiYiJ9fX0="),
    JACK_O_LANTERN_CYAN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIzYjBjN2YyMTNmZDUxZGQ4ODhlYTJjZmNjZGQ3YTI4YjI0N2E5ZDQ4ZmM1NDk3YjkzNDNmYmNhZGE3NjMyMyJ9fX0="),
    JACK_O_LANTERN_MAGENTA("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ5ZTA3N2ZmYWFlNWJiYjk1Mjk5MzAyZDY0ZDkzMjNkNDYyYzRkZjFmMjhjYTI0NjZlOTFmYWE4NmM0M2MxNSJ9fX0="),
    JACK_O_LANTERN_BLACK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M4NjZlNDIzOTU1MTQyOGY5YzgxNGQ5ZGMxMWU3MDAyZTA0M2I4N2Y1OTBmMWZkY2I0ZmY2YmI0OTdiOGUxOCJ9fX0="),
    JACK_O_LANTERN_WHITE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVkYTNhMWRjNGYyMzU5YzliMWU0M2Q5MjUzMTk2ZTZhYjVhMmQyNDg0N2EwMzg1N2NkN2I4M2Y2ODU0YWU1ZCJ9fX0="),
    JACK_O_LANTERN_BROWN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI3MDQyOTM0YjJhNGIwNDZmODdlZDA5MDVmZDA5MmE4OTNhZmZjNWRiZWUwOTU1NjQzNjliY2M2YmVhYWJiIn19fQ=="),

    AMONG_US("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWIzNWFhYWU3MWEzNzA4MzcyYTU0ODViMzY4YmZkZTc2ZmZiMzQ1YjBkMmFkYWFmNWVlODQyODNmZmQxZTNmMSJ9fX0="),
    MOON_FULL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAwYTFhN2JiMDdmZGI0ZTZhODZlMzQxODE2ZTg4NDNkZGFmN2NmMzcxM2EzNjY2ZDc0YjcyZjk4NjE5ZjA2MyJ9fX0="),
    MOON_HALF("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNjN2JjNzE3ZDUyMmI0MWVkYjYzNDE3ZGNlNjk3ZmY0ZmRiNzU0YWRiOTVkYzllNjQ0M2E1ZDUzYmMzNzdkMCJ9fX0="),
    MOON_NEW("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDNlNWU5ZGIyOWJjYTQ1Zjg3ZGYwMzE3MmZkNzEzNTU5MDM0MDMwYjQxMWFjZDI4MjExMDc4YTdmMWU5YWEyNCJ9fX0="),
    SUN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjdkODQ1YTM1YTU2Njg0YTc0MmZlYzMzOWY1MDI3MmNjMTQ1YmVlMzYzYmVjODFhODBkNmVmMWY3NDhlYTYxYiJ9fX0="),
    PRESENT_WHITE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ3YjM3ZTY3YTg5MTU5YmY0YWNjNGE0NGQ0MzI4ZjRlZmMwMTgxNjA1MTQyMjg4ZTVlZWQxYWI4YWVkOTEzYyJ9fX0="),
    PRESENT_GREEN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjhkYzNjODQ0OTFjNGZmMTAzZGU2ZWEwMTg4MGUyZDgzNDMyY2Q0ODc4Yjg0ZDZiNDMyNTNjYzUxZmQ5NzhlNyJ9fX0="),
    PRESENT_BLUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWI4NWUyOWYyOWVjOWE5MGU0ODJlYTViODM5MWJjYjQ1NjBiYmFlMGRjZDE1ZDdjZTFkODYwMTZiNDM1NmU5OCJ9fX0="),
    PRESENT_PURPLE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTllMzlkOTFjMDRjMzBjYzFmNTMwZmVlNzk4ZWVkMjc5ZGRlNjBmOTVjMmUxZDE1NWMwZmRkMzYxZDA5NjJlZCJ9fX0="),
    PRESENT_YELLOW("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJkOTg3OTJkZDkyZDk3MTk4OTQzNDFhYzkwMTJhNTg0YzQ0Mjg1NThmZDJjNzEyZjc4ZTVmMGQ0ZGE4NTQ3MCJ9fX0="),
    RUBIKS_CUBE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGYzMzNjNzNjODQ4OWE5Y2EzNWQ1NjAzMTMwOTE4Yjg3NjA5ODRlYjlkMzAyOGUzMGU3NDI0N2RmZjg3M2JmZSJ9fX0="),
    TOTEM("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRhYjlkNmQ5ZjZkMjMxNGEyYmZmZjk4ZmRiODBmY2I0Y2UwYzhlZjc4ODEzMWE5YzZjMjJlY2M3MGU4M2Q3OSJ9fX0=")

    ;

    private final String texture;

    Head(String texture) {
        this.texture = texture;
    }

    public static Head getByName(String name) {
        for (Head head : Head.values()) {
            if (head.name().equals(name)) {
                return head;
            }
        }
        throw new NotFoundException("Head with name " + name + " not found");
    }

    public String getTexture() {
        return this.texture;
    }

    public ItemStack asItem() {
        return this.asItemBuilder().build();
    }

    public ItemBuilder asItemBuilder() {
        return ItemBuilder.asHead().withHeadTexture(this.texture);
    }
}
