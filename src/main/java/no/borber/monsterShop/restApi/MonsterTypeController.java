package no.borber.monsterShop.restApi;

import no.borber.monsterShop.readLayer.monster.MonsterType;
import no.borber.monsterShop.readLayer.monster.MonsterTypesRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class MonsterTypeController {

    @RequestMapping(value = "monsterTypes")
    public Set<MonsterType> getMonsterTypes(){
        return MonsterTypesRepo.getMonsterTypes();
    }

    @RequestMapping(value = "monsterTypes/{monsterType}")
    public MonsterType getMonsterTypes(String monstertype){
        return MonsterTypesRepo.getMonsterType(monstertype);
    }
}
