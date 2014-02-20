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
     * @return Map bestående av String:monsternavn og handlekurvlinjen for det aktuelle monsteret
     */
    @RequestMapping(value = "/handlekurv/",  method=RequestMethod.GET)
    @ResponseBody()
    public Map<String, Handlekurvlinje> getHandlekurv(){
        return null;
    }

    /**
     * Fjerner et monster fra en kundes handlekurv. Om dette gjør at antall for handlekurvlinjen blir null fjernes
     * linjen fra handlekurven.
     *
     * @param monsternavn navnet på monsteret som skal fjernes
     */
    @RequestMapping(value = "/handlekurv/fjern/{monsternavn}",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void fjernMonster(@PathVariable String monsternavn){}

    /**
     * Legger til ett monster i en kundes handlekurv. Om handlekurvlinjen finnes fra før økes antallet, hvis ikke lages
     * det en ny linje.
     *
     * @param monsternavn navnet på monsteret som skal legges til
     */
    @RequestMapping(value = "/handlekurv/leggTil/{monsternavn}",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void leggTilMonster(@PathVariable String monsternavn){}

    /**
     * Genererer en ny ordre, og tømmer kundes handlekurv.
     */
    @RequestMapping(value = "/handlekurv/bekreftOrdre",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void bekreftOrdre(){}

    /**
     * Kalkulerer sum av handlekurvLinjer (antall * pris) i kundes nåværende handlekurv
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
