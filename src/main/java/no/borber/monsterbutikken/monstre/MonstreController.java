package no.borber.monsterbutikken.monstre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
public class MonstreController {

    @RequestMapping(value = "/monstre")
    @ResponseBody
    public Set<MonsterJson> getMonstre(){
        return MonsterRepo.getMonstre();
    }
}
