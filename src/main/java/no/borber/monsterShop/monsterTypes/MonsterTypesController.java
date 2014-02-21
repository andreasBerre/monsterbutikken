package no.borber.monsterShop.monsterTypes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
public class MonsterTypesController {

    @RequestMapping(value = "monsterTypes")
    @ResponseBody
    public Set<MonsterTypeJson> getMonsterTypes(){
        return MonsterTypesRepo.getMonsterTypes();
    }
}
