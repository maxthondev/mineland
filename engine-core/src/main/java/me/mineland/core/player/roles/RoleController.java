package me.mineland.core.player.roles;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

public enum RoleController {

    //Cargos sujetivos a equipe.
    COMITÊ("Comitê", "§4[Comitê] ", "§4", "§4Comitê", "*"),
    GERÊNCIA("Gerência", "§4[Gerência] ", "§4", "§4Gerência", "role.gerencia"),
    CONSELHO("Conselho", "§3[Conselho] ", "§3", "§4Conselho","role.conselho"),
    COORDENAÇÃO("Coordenação", "§e[Coordenação] ", "§e", "§eCoordenação","role.coord"),
    DETECTOR("Detector", "§5[Detector] ", "§5", "§5Detector","role.detector"),
    MODERAÇÃO("Moderação", "§2[Moderação] ", "§2", "§2Moderação","role.moderador"),
    SUPORTE("Suporte", "§9[Suporte] ", "§9", "§8Suporte","role.suporte"),

    //Cargos de Influencer
    YOUTUBER("YouTuber", "§c[YouTuber] ", "§c", "§cYouTuber", "role.youtuber"),

    //Cargos de vip's
    BETA("Beta", "§1[Beta] ", "§1", "§1Beta", "role.beta"),

    //Cargo padrão ao jogador inicial.
    MEMBRO("Membro", "§7", "§7", "§7Membro", "");

    public String name, prefix, coloredName, coloredTag, permission;

    RoleController(String name, String prefix, String coloredName, String coloredTag, String permission){
        this.name = name;
        this.prefix = prefix;
        this.coloredName = coloredName;
        this.coloredTag = coloredTag;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColoredName() {
        return coloredName;
    }

    public String getColoredTag() {
        return coloredTag;
    }

    public String getPermission() {
        return permission;
    }

    public boolean hasRolePermission(Player player) {
        return permission.isEmpty() || player.hasPermission(permission);
    }

    public boolean hasProxyRolePermission(ProxiedPlayer proxiedPlayer) {
        return permission.isEmpty() || proxiedPlayer.hasPermission(permission);
    }

    public static RoleController getRolePlayer(Player player) {
        for (RoleController role : values()) {
            if (role.hasRolePermission(player)) {
                return role;
            }
        }
        return MEMBRO;
    }

    public static RoleController getProxyRolePlayer(ProxiedPlayer proxiedPlayer) {
        for (RoleController role : values()) {
            if (role.hasProxyRolePermission(proxiedPlayer)) {
                return role;
            }
        }
        return MEMBRO;
    }

}
