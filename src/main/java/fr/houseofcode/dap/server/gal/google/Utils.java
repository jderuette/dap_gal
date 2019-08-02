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

/**
 *
 * @author Alex
 *
 */
public class Utils {

    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger();

    /** The default JsonFactory.*/
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**
     * 
     */
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "\\dap\\tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = new ArrayList<String>();
    /**
     * 
     */
    //    private static final String CREDENTIALS_FILE_PATH ;

    /**
     * Creates an authorized Credential object.
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */

    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String userKeyName1)
            throws IOException {

        GoogleAuthorizationCodeFlow flow = getFlow(HTTP_TRANSPORT);
        return flow.loadCredential(userKeyName1);

        //        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //        return new AuthorizationCodeInstalledApp(getFlow(httpTransport), receiver).authorize("user");
    }

    public static GoogleAuthorizationCodeFlow getFlow(final NetHttpTransport httpTransport) throws IOException {
        LOG.debug("Début du Utils.getCredentials");
        SCOPES.add(CalendarScopes.CALENDAR_READONLY);
        SCOPES.add(GmailScopes.GMAIL_READONLY);

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
