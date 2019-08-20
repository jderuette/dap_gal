package fr.houseofcode.dap.server.gal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
/**
 * 
 * @author Alex
 */
@RestController
public class HelloController {
    /**
     *
     * @return
     */
    @RequestMapping("/")
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode) manquant.
    public String index() {
        return "Bienvenue sur Spring Boot!";
    }
}
