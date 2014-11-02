package no.borber.monsterShop.readLayer.monster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonsterTypesRepo {

    private final static Map<String,MonsterType> monsterTypes = new HashMap<>();

    static  {
        monsterTypes.put("Giant squid", new MonsterType("Giant squid", 100000));
        monsterTypes.put("Bakeneko", new MonsterType("Bakeneko", 120000));
        monsterTypes.put("Basilisk", new MonsterType("Basilisk", 175000));
        monsterTypes.put("Det erymanthiske villsvin", new MonsterType("Det erymanthiske villsvin", 25000));
        monsterTypes.put("Griff", new MonsterType("Griff", 12000));
        monsterTypes.put("Hamloper", new MonsterType("Hamloper", 8000));
        monsterTypes.put("Hippogriff", new MonsterType("Hippogriff", 128000));
        monsterTypes.put("Hydra", new MonsterType("Hydra", 38000));
        monsterTypes.put("Kentaur", new MonsterType("Kentaur", 76000));
        monsterTypes.put("Kerberos", new MonsterType("Kerberos", 31000));
        monsterTypes.put("Kraken", new MonsterType("Kraken", 2800));
        monsterTypes.put("Mannbjorn", new MonsterType("Mannbjorn", 49000));
        monsterTypes.put("Mantikora", new MonsterType("Mantikora", 21000));
        monsterTypes.put("Margyge", new MonsterType("Margyge", 73000));
        monsterTypes.put("Marmale", new MonsterType("Marmale", 149000));
        monsterTypes.put("Minotauros", new MonsterType("Minotauros", 28000));
        monsterTypes.put("Nekomusume", new MonsterType("Nekomusume", 62000));
        monsterTypes.put("Rokk", new MonsterType("Rokk", 12000));
        monsterTypes.put("Seljordsormen", new MonsterType("Seljordsormen", 56000));
        monsterTypes.put("Sfinks", new MonsterType("Sfinks", 39000));
        monsterTypes.put("Sirene", new MonsterType("Sirene", 12900));
        monsterTypes.put("Sjoorm", new MonsterType("Sjoorm", 240000));
        monsterTypes.put("Succubus", new MonsterType("Succubus", 84000));
        monsterTypes.put("Valravn", new MonsterType("Valravn", 92300));
        monsterTypes.put("Vampyr", new MonsterType("Vampyr", 420000));
        monsterTypes.put("Varulv", new MonsterType("Varulv", 69000));
    }

    public static Set<MonsterType> getMonsterTypes() {
        return new HashSet<>(monsterTypes.values());
    }

    public static MonsterType getMonsterType(String monsterType) {
        return monsterTypes.get(monsterType);
    }
}
