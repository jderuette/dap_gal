package fr.houseofcode.dap.server.gal;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.gal.google.CalendarService;

/**
 *
 * @author Alex
 */
@RestController
public class CalendarController {

    @Autowired
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (d'attribut) manquant.
    private CalendarService calService;

    @RequestMapping("/calendar/nextevents")
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode et paramètre deméthode) manquant.
    public String displayNextEvents(@RequestParam("userKey") String userKey2)
            throws IOException, GeneralSecurityException {

        return calService.getNextEvents(userKey2);
    }
}
