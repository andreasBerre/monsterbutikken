package no.borber.monsterbutikken.handlekurv;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class HandlekurvController {

    @Resource
    private HttpServletRequest httpRequest;

    /**
     * Henter nåværende tilstand for en kundes handlekurv.
     *
     * @return Map bestående av String:monsternavn og ordrelinjen for det aktuelle monsteret
     */
    @RequestMapping(value = "/handlekurv/",  method=RequestMethod.GET)
    @ResponseBody()
    public Map<String, Ordrelinje> getHandlekurv(){
        return null;
    }

    /**
     * Fjerner et monster fra en kundes handlekurv.
     *
     * @param monsternavn navnet på monsteret som skal fjernes
     */
    @RequestMapping(value = "/handlekurv/fjern/{monsternavn}",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void fjernMonster(@PathVariable String monsternavn){}

    /**
     * Legger til et monster i en kundes handlekurv
     *
     * @param monsterNavn navnet på monsteret som skal legges til
     */
    @RequestMapping(value = "/handlekurv/leggTil/{monsterNavn}",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void leggTilMonster(@PathVariable String monsterNavn){}

    /**
     * Genererer en ny ordre, og tømmer kundes handlekurv.
     */
    @RequestMapping(value = "/handlekurv/bekreftOrdre",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void bekreftOrdre(){}


    /**
     * Kalkulerer sum av handlekurvLinjer i kundes nåværende handlekurv
     */
    @RequestMapping(value = "/handlekurv/sum",  method=RequestMethod.GET)
    @ResponseBody
    public HandlekurvSum handlekurvSum(){
        return null;
    }

    private String getInnloggetKunde() {
        return (String)httpRequest.getSession().getAttribute("kundenavn");
    }
}
