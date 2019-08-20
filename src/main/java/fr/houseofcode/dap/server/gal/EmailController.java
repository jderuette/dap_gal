package fr.houseofcode.dap.server.gal;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.gal.google.GmailService;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
/**
 *
 * @author Alex
 */
@RestController
public class EmailController {

    @Autowired
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (d'attribut) manquant.
    //TODO gal by Djer |POO| Pour respecter le CamelCase, il devrait y avoir un "S" (majuscule) : gService
    private GmailService gservice;

    @RequestMapping("/email/nbunread")
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode, de paramètres de méthode et des exceptions) manquant.
    //TODO gal by Djer |POO| Nom de paramètre ("toto") pas très parlant
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. LE parmatre "toto" pourrait être final. En effat tu ne va pas modifier la valeur du paramètre dans ta méthode (ce qui est une BONNE pratique). Du coup la majorité fdes paramètres (de méthode) sont "final" en Java.
    public Integer displayUnreadEmail(@RequestParam("userKey") String toto)
            throws IOException, GeneralSecurityException {

        return gservice.getNbUnreadEmails(toto);
    }
}
