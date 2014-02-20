package no.borber.monsterbutikken.monstre;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonsterRepo {

    private final static Map<String,MonsterJson> monstre = new HashMap<>();

    static  {
        monstre.put("Ao (skilpadde)", new MonsterJson("Ao (skilpadde)", 100000));
        monstre.put("Bakeneko", new MonsterJson("Bakeneko", 120000));
        monstre.put("Basilisk", new MonsterJson("Basilisk", 175000));
        monstre.put("Det erymanthiske villsvin", new MonsterJson("Det erymanthiske villsvin", 100));
        monstre.put("Griff", new MonsterJson("Griff", 100));
        monstre.put("Hamløper", new MonsterJson("Hamløper", 100));
        monstre.put("Hippogriff", new MonsterJson("Hippogriff", 100));
        monstre.put("Hydra", new MonsterJson("Hydra", 100));
        monstre.put("Kentaur", new MonsterJson("Kentaur", 100));
        monstre.put("Kerberos", new MonsterJson("Kerberos", 100));
        monstre.put("Kraken", new MonsterJson("Kraken", 100));
        monstre.put("Mannbjørn", new MonsterJson("Mannbjørn", 100));
        monstre.put("Mantikora", new MonsterJson("Mantikora", 100));
        monstre.put("Margyge", new MonsterJson("Margyge", 100));
        monstre.put("Marmæle", new MonsterJson("Marmæle", 100));
        monstre.put("Minotauros", new MonsterJson("Minotauros", 100));
        monstre.put("Nekomusume", new MonsterJson("Nekomusume", 100));
        monstre.put("Rokk", new MonsterJson("Rokk", 100));
        monstre.put("Seljordsormen", new MonsterJson("Seljordsormen", 100));
        monstre.put("Sfinks", new MonsterJson("Sfinks", 100));
        monstre.put("Sirene", new MonsterJson("Sirene", 100));
        monstre.put("Sjøorm", new MonsterJson("Sjøorm", 100));
        monstre.put("Succubus", new MonsterJson("Succubus", 100));
        monstre.put("Valravn", new MonsterJson("Valravn", 100));
        monstre.put("Vampyr", new MonsterJson("Vampyr", 100));
        monstre.put("Varulv", new MonsterJson("Varulv", 100));
    }

    public static Set<MonsterJson> getMonstre() {
        return new HashSet<>(monstre.values());
    }

    public static MonsterJson getMonster(String monsterNavn) {
        return monstre.get(monsterNavn);
    }
}
