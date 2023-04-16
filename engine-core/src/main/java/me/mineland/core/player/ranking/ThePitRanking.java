package me.mineland.core.player.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public enum ThePitRanking {

    Initial("§e", "I✯", "-", 1000),
    Level1("§e", "1✯", "I", 2000),
    Level2("§e", "2✯", "II", 3000),
    Level3("§e", "3✯", "III", 3500),
    Level4("§e", "4✯", "IV", 4000),
    Level5("§e", "5✯", "V", 4200),
    Level6("§e", "6✯", "VI", 4700),
    Level7("§e", "7✯", "VII", 5100),
    Level8("§e", "8✯", "VIII", 5700),
    Level9("§e", "9✯", "IX", 6250),
    Level10("§e", "10✯", "X", 6250),
    Level11("§e", "11✯", "XI", 6250),
    Level12("§e", "12✯", "XII", 6250);

    private String color, symbol, name;

    @Getter
    private int experience;

    public static ThePitRanking getRanking(String ligaNome) {
        ThePitRanking liga = ThePitRanking.Initial;

        for (ThePitRanking rank : values()) {
            if (rank.getName().equalsIgnoreCase(ligaNome)) {
                liga = rank;
                break;
            }
        }
        return liga;
    }

    public static ThePitRanking getRanking(int xp) {
        if (xp >= Level3.getExperience()) return Level4;
        if (xp < Level1.getExperience()) return Initial;

        ThePitRanking liga = Initial;
        for (ThePitRanking rank : values()) {
            if (xp <= rank.experience && xp > rank.getMin()) {
                liga = rank;
            }
        }
        return liga;
    }

    public int getMin() {
        int min = 0;

        if (this.ordinal() > 0) min = ThePitRanking.values()[this.ordinal() - 1].getExperience();

        return min;
    }

    public ThePitRanking getPreviousThePitRanking() {
        return this == Initial ? Initial : ThePitRanking.values()[ordinal() - 1];
    }

    public ThePitRanking getNextThePitRanking() {
        return this == Level3 ? Level3 : ThePitRanking.values()[ordinal() + 1];
    }

}
