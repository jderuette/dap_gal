package fr.houseofcode.dap.server.gal;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.gal.google.GmailService;

/**
 * @author Alex
 *
 */
@RestController
public class EmailController {

    @Autowired
    private GmailService gservice;

    @RequestMapping("/email/nbunread")
    public Integer displayUnreadEmail(@RequestParam("userKey") String toto)
            throws IOException, GeneralSecurityException {

        return gservice.getNbUnreadEmails(toto);
    }
}
