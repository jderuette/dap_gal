package fr.houseofcode.dap.server.gal.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
/**
 *
 * @author Alex
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (d'attribut) manquant.
    private Integer id;
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (d'attribut) manquant.
    private String name;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre id pourrait être final
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre name pourrait être final
    public void setName(String name) {
        this.name = name;
    }

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
    //    private List<GoogleAccount> googleAccounts;
}
