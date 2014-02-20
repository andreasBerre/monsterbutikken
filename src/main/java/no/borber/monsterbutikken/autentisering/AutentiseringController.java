package no.borber.monsterbutikken.autentisering;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AutentiseringController {

    @Resource
    private HttpServletRequest httpRequest;

    @RequestMapping(value="autentisering/logginn/{kundenavn}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void loggInn( @PathVariable String kundenavn){
        httpRequest.getSession().setAttribute("kundenavn", kundenavn);
    }

    @RequestMapping(value="autentisering/loggut", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void loggUt(){
        httpRequest.getSession().removeAttribute("kundenavn");
    }

    @RequestMapping(value="autentisering/innloggetKunde", method = RequestMethod.GET)
    @ResponseBody
    public Kunde getInnloggetBruker(){
        return new Kunde((String) httpRequest.getSession().getAttribute("kundenavn"));
    }
}
