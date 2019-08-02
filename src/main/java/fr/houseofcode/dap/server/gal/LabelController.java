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
public class LabelController {

    @Autowired
    private GmailService labservice;

    @RequestMapping("/email/labels")
    public String displayUnreadEmail(@RequestParam("userKey") String userKey3)
            throws IOException, GeneralSecurityException {

        return labservice.getLabels(userKey3);
    }
}
