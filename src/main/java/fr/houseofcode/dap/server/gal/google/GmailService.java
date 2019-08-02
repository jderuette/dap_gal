package fr.houseofcode.dap.server.gal.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 *
 * @author Alex
 *
 */
@Service
public class GmailService {

    /**
     *
     * @return
     * @throws GeneralSecurityException 
     * @throws IOException 
     */
    public Integer index(String userKeyName6) throws IOException, GeneralSecurityException {
        return getNbUnreadEmails(userKeyName6);
    }

    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger();

    //    /** Singleton.*/
    //    private static GmailService instance;
    //    
    //    /**The default constructor. Private because is a singleton.*/
    //    private GmailService() {
    //    }
    //    
    //    /**
    //     * Create unique instance of GmailService (Singleton).
    //     * @return unique instance of GmailService (cf. Singleton).
    //     */
    //    public static synchronized GmailService getInstance() {
    //        if (instance == null) {
    //            instance = new GmailService();
    //        }
    //        return instance;
    //    }

    /** The internal application name.*/
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    /** The default JsonFactory.*/
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Allow the secured access to Gmail.
     * @return An instance GmailService with secured transport.
     * @throws GeneralSecurityException.
     * @throws IOException.
     */
    private Gmail getService(String userKeyName2) throws GeneralSecurityException, IOException {

        LOG.debug("Début du GmailService.getService");

        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY,
                Utils.getCredentials(httpTransport, userKeyName2)).setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    /**
     * Display Gmail Labels (Afficher les labels de la boite Gmail).
     * @return Gmail Labels (Les labels de la boite Gmail).
     * @throws IOException.
     * @throws GeneralSecurityException.
     */
    public String getLabels(String userKeyName3) throws IOException, GeneralSecurityException {

        LOG.info("Récupération des labels Gmail de l'utilisateur");

        String user = "me";
        String allLabels1 = "";

        ListLabelsResponse listResponse = getService(userKeyName3).users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.isEmpty()) {
            allLabels1 = "Pas de Labels trouvés";
        } else {
            allLabels1 = "Labels : ";
            for (Label label : labels) {
                allLabels1 = allLabels1 + "\n" + label.getName();
            }
        }
        LOG.debug("Nombre de labels Gmail : " + labels.size());

        return allLabels1;
    }

    /**
     * Display unread emails (Afficher les emails non lus de la boite GMAIL).
     * @param userKey 
     * @return Unread emails (les emails non lus de la boite GMAIL).
     * @throws IOException.
     * @throws GeneralSecurityException.
     */
    public Integer getNbUnreadEmails(String userKeyName4) throws IOException, GeneralSecurityException {

        LOG.info("Récupération du nombre d'emails de l'utilisateur");

        String user = "me";
        Integer result = 0;

        ListMessagesResponse allMessages = getService(userKeyName4).users().messages().list(user)
                .setQ("in:inbox is:unread").execute();
        List<Message> messages = allMessages.getMessages();
        if (messages != null) { // si la liste n'est pas nul
            if (!messages.isEmpty()) { // si la liste de message n'est pas vide (! = n'est pas)
                result = messages.size();
            }
        }
        return result;

    }
}
