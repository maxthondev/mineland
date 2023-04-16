package me.mineland.proxy.controllers;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public enum ReportController {
    CHEATING("Cheating"),
    FLY("Fly"),
    KILL_AURA("Kill Aura"),
    SPEED("Speed"),
    ANTI_KNOCKBACK("Anti Knockback"),
    REACH("Reach"),
    MENSAGEM_OFENSIVA("Mensagem Ofensiva", true),
    NICK_IMPROPRIO("Nick impróprio", true),
    SKIN_IMPROPRIA("Skin imprópria", true);

    private String name;
    private boolean requiresProof;

    ReportController(String name) {
        this(name, false);
    }

    ReportController(String name, boolean requiresProof) {
        this.name = name;
        this.requiresProof = requiresProof;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRequiresProof() {
        return this.requiresProof;
    }

    public int getProofId() {
        return 1 + this.name.split(" ").length;
    }

    public static final TextComponent REASONS;
    private static final ReportController[] VALUES;

    static {
        VALUES = values();
        REASONS = new TextComponent("");
        for (BaseComponent component : TextComponent.fromLegacyText(" \n §7Por qual motivo deseja reportar este jogador?\n ")) {
            REASONS.addExtra(component);
        }
        for (ReportController types : VALUES) {
            for (BaseComponent component : TextComponent.fromLegacyText("§f\n " + types.getName())) {
                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Clique para reportar o jogador por " + types.getName() + ".")));
                component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/report {player} " + types.getName() + (types.isRequiresProof() ? " <prova>" : "")));
                REASONS.addExtra(component);
            }
        }
    }

    public static ReportController fromName(String name) {
        for (ReportController type : VALUES) {
            if (type.getName().toLowerCase().startsWith(name.toLowerCase())) {
                return type;
            }
        }

        return null;
    }

    public static ReportController fromValue(String name) {
        for (ReportController type : VALUES) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }
}
