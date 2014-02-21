package no.borber.monsterShop.monsterTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonsterTypesRepo {

    private final static Map<String,MonsterTypeJson> monstre = new HashMap<>();

    static  {
        monstre.put("Ao (skilpadde)", new MonsterTypeJson("Ao (skilpadde)", 100000));
        monstre.put("Bakeneko", new MonsterTypeJson("Bakeneko", 120000));
        monstre.put("Basilisk", new MonsterTypeJson("Basilisk", 175000));
        monstre.put("Det erymanthiske villsvin", new MonsterTypeJson("Det erymanthiske villsvin", 100));
        monstre.put("Griff", new MonsterTypeJson("Griff", 100));
        monstre.put("Hamløper", new MonsterTypeJson("Hamløper", 100));
        monstre.put("Hippogriff", new MonsterTypeJson("Hippogriff", 100));
        monstre.put("Hydra", new MonsterTypeJson("Hydra", 100));
        monstre.put("Kentaur", new MonsterTypeJson("Kentaur", 100));
        monstre.put("Kerberos", new MonsterTypeJson("Kerberos", 100));
        monstre.put("Kraken", new MonsterTypeJson("Kraken", 100));
        monstre.put("Mannbjørn", new MonsterTypeJson("Mannbjørn", 100));
        monstre.put("Mantikora", new MonsterTypeJson("Mantikora", 100));
        monstre.put("Margyge", new MonsterTypeJson("Margyge", 100));
        monstre.put("Marmæle", new MonsterTypeJson("Marmæle", 100));
        monstre.put("Minotauros", new MonsterTypeJson("Minotauros", 100));
        monstre.put("Nekomusume", new MonsterTypeJson("Nekomusume", 100));
        monstre.put("Rokk", new MonsterTypeJson("Rokk", 100));
        monstre.put("Seljordsormen", new MonsterTypeJson("Seljordsormen", 100));
        monstre.put("Sfinks", new MonsterTypeJson("Sfinks", 100));
        monstre.put("Sirene", new MonsterTypeJson("Sirene", 100));
        monstre.put("Sjøorm", new MonsterTypeJson("Sjøorm", 100));
        monstre.put("Succubus", new MonsterTypeJson("Succubus", 100));
        monstre.put("Valravn", new MonsterTypeJson("Valravn", 100));
        monstre.put("Vampyr", new MonsterTypeJson("Vampyr", 100));
        monstre.put("Varulv", new MonsterTypeJson("Varulv", 100));
    }

    public static Set<MonsterTypeJson> getMonsterTypes() {
        return new HashSet<>(monstre.values());
    }

    public static MonsterTypeJson getMonsterType(String monsterType) {
        return monstre.get(monsterType);
    }
}
