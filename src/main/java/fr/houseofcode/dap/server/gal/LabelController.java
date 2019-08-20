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
public class LabelController {

    @Autowired
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (d'attribut) manquant.
    private GmailService labservice;

    @RequestMapping("/email/labels")
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode et de paramètres de méthode) manquant.
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre userKey3 pourrai être final
    public String displayUnreadEmail(@RequestParam("userKey") String userKey3)
            throws IOException, GeneralSecurityException {

        return labservice.getLabels(userKey3);
    }
}
