package no.borber.monsterShop.restApi;

import no.borber.monsterShop.application.monster.MonsterTypeJson;
import no.borber.monsterShop.application.monster.MonsterTypesRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class MonsterTypeController {

    @RequestMapping(value = "monsterTypes")
    public Set<MonsterTypeJson> getMonsterTypes(){
        return MonsterTypesRepo.getMonsterTypes();
    }

    @RequestMapping(value = "monsterTypes/{monsterType}")
    public MonsterTypeJson getMonsterTypes(String monstertype){
        return MonsterTypesRepo.getMonsterType(monstertype);
    }
}
