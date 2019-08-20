package fr.houseofcode.dap.server.gal.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
/**
 *
 * @author Alex
 */
public class Utils {

    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger();

    /** The default JsonFactory.*/
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** */
    //TODO gal by Djer |file.separator| Il faut utiliser "file.separator" plutot que directement "\\" comme séparateur de fichiers pour que ton code puisse fonction sur tous les système (ici il ne fonctionen QUE sous Windows)
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "\\dap\\tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = new ArrayList<String>();
    //private static final String CREDENTIALS_FILE_PATH ;

    /**
     * Creates an authorized Credential object.
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de paramètre de méthode) manquant.
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre userKeyName1 pourrait être final
    public static Credential getCredentials(final NetHttpTransport httpTransport, String userKeyName1)
            throws IOException {

        GoogleAuthorizationCodeFlow flow = getFlow(httpTransport);
        return flow.loadCredential(userKeyName1);

        //        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //        return new AuthorizationCodeInstalledApp(getFlow(httpTransport), receiver).authorize("user");
    }

    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode, paramètre de méthode et return) manquant.
    public static GoogleAuthorizationCodeFlow getFlow(final NetHttpTransport httpTransport) throws IOException {
        LOG.debug("Début du Utils.getCredentials");
        //TODO gal by Djer |POO| (optionnel) A chaque fois que "getFlow()" sera apeler 2 nouveaux crédentials seront ajoutés dnas la liste. Ca n'es pas trop grave car Google le gère, mais ca serait mieux de l'éviter. LA bonne solution serait de faire els "add" dans le constructeur, mais il faudra alors supprimer tous les statiques (et faire de l'injection de dépendance)
        SCOPES.add(CalendarScopes.CALENDAR_READONLY);
        SCOPES.add(GmailScopes.GMAIL_READONLY);

        //TODO gal by Djer |POO| Devrait être en constante en haut de la classe pour faciliter les modifications.
        File appClientSecretFile = new File(System.getProperty("user.home") + "\\dap\\credentials.json");
        // Load client secrets.
        //        InputStream in = CalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        //        if (in == null) {
        //            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);}
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(new FileInputStream(appClientSecretFile), Charset.forName("UTF-8")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline").build();
        return flow;
    }

}
