package no.borber.monsterShop.monsterTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonsterTypesRepo {

    private final static Map<String,MonsterTypeJson> monsterTypes = new HashMap<>();

    static  {
        monsterTypes.put("Ao (skilpadde)", new MonsterTypeJson("Ao (skilpadde)", 100000));
        monsterTypes.put("Bakeneko", new MonsterTypeJson("Bakeneko", 120000));
        monsterTypes.put("Basilisk", new MonsterTypeJson("Basilisk", 175000));
        monsterTypes.put("Det erymanthiske villsvin", new MonsterTypeJson("Det erymanthiske villsvin", 25000));
        monsterTypes.put("Griff", new MonsterTypeJson("Griff", 12000));
        monsterTypes.put("Hamløper", new MonsterTypeJson("Hamløper", 8000));
        monsterTypes.put("Hippogriff", new MonsterTypeJson("Hippogriff", 128000));
        monsterTypes.put("Hydra", new MonsterTypeJson("Hydra", 38000));
        monsterTypes.put("Kentaur", new MonsterTypeJson("Kentaur", 76000));
        monsterTypes.put("Kerberos", new MonsterTypeJson("Kerberos", 31000));
        monsterTypes.put("Kraken", new MonsterTypeJson("Kraken", 2800));
        monsterTypes.put("Mannbjørn", new MonsterTypeJson("Mannbjørn", 49000));
        monsterTypes.put("Mantikora", new MonsterTypeJson("Mantikora", 21000));
        monsterTypes.put("Margyge", new MonsterTypeJson("Margyge", 73000));
        monsterTypes.put("Marmæle", new MonsterTypeJson("Marmæle", 149000));
        monsterTypes.put("Minotauros", new MonsterTypeJson("Minotauros", 28000));
        monsterTypes.put("Nekomusume", new MonsterTypeJson("Nekomusume", 62000));
        monsterTypes.put("Rokk", new MonsterTypeJson("Rokk", 12000));
        monsterTypes.put("Seljordsormen", new MonsterTypeJson("Seljordsormen", 56000));
        monsterTypes.put("Sfinks", new MonsterTypeJson("Sfinks", 39000));
        monsterTypes.put("Sirene", new MonsterTypeJson("Sirene", 12900));
        monsterTypes.put("Sjøorm", new MonsterTypeJson("Sjøorm", 240000));
        monsterTypes.put("Succubus", new MonsterTypeJson("Succubus", 84000));
        monsterTypes.put("Valravn", new MonsterTypeJson("Valravn", 92300));
        monsterTypes.put("Vampyr", new MonsterTypeJson("Vampyr", 420000));
        monsterTypes.put("Varulv", new MonsterTypeJson("Varulv", 69000));
    }

    public static Set<MonsterTypeJson> getMonsterTypes() {
        return new HashSet<>(monsterTypes.values());
    }

    public static MonsterTypeJson getMonsterType(String monsterType) {
        return monsterTypes.get(monsterType);
    }
}
