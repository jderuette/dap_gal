package fr.houseofcode.dap.server.gal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 *
 */

@RestController
public class HelloController {
    /**
     * 
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "Bienvenue sur Spring Boot!";
    }
}
