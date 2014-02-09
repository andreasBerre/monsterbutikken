package no.borber.monsterbutikken;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
public class ItemsController {

    @RequestMapping(value = "/monstre")
    @ResponseBody
    public List<MonsterJson> getAvailableMonsters(){
        List<MonsterJson> list = new ArrayList<MonsterJson>();
        list.add(new MonsterJson("Ao (skilpadde)",  100000));
        list.add(new MonsterJson("Bakeneko",  120000));
        list.add(new MonsterJson("Basilisk",  175000));
        list.add(new MonsterJson("Det erymanthiske villsvin",  100));
        list.add(new MonsterJson("Griff",  100));
        list.add(new MonsterJson("Hamløper",  100));
        list.add(new MonsterJson("Hippogriff",  100));
        list.add(new MonsterJson("Hydra",  100));
        list.add(new MonsterJson("Kentaur",  100));
        list.add(new MonsterJson("Kerberos",  100));
        list.add(new MonsterJson("Kraken",  100));
        list.add(new MonsterJson("Mannbjørn",  100));
        list.add(new MonsterJson("Mantikora",  100));
        list.add(new MonsterJson("Margyge",  100));
        list.add(new MonsterJson("Marmæle",  100));
        list.add(new MonsterJson("Minotauros",  100));
        list.add(new MonsterJson("Nekomusume",  100));
        list.add(new MonsterJson("Rokk",  100));
        list.add(new MonsterJson("Seljordsormen",  100));
        list.add(new MonsterJson("Sfinks",  100));
        list.add(new MonsterJson("Sirene",  100));
        list.add(new MonsterJson("Sjøorm",  100));
        list.add(new MonsterJson("Succubus",  100));
        list.add(new MonsterJson("Valravn",  100));
        list.add(new MonsterJson("Vampyr",  100));
        list.add(new MonsterJson("Varulv",  100));
        Collections.sort(list);
        return list;
    }
}
