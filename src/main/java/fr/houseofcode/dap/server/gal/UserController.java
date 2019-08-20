package fr.houseofcode.dap.server.gal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.gal.data.AppUser;
import fr.houseofcode.dap.server.gal.data.AppUserRepository;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
/**
 *
 * @author Alex
 */
@RestController
public class UserController {

    @Autowired
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (d'attribut) manquant.
    private AppUserRepository appUserRepo;

    @RequestMapping("user/all")
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode et de paramètres de méthode) manquant.
    public Iterable<AppUser> displayAllUsers() {

        return appUserRepo.findAll();

    }

    @RequestMapping("user/add")
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode et de paramètres de méthode) manquant.
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre name pourrait être final
    public void addUser(@RequestParam String name) {

        AppUser entity = new AppUser();
        entity.setName(name);

        appUserRepo.save(entity);

    }

}