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
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
/**
 *
 * @author Alex
 */
@Service
public class CalendarService {

    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();

    //    /** Singleton.*/
    //     private static CalendarService instance;
    //    
    //    /**The default constructor. Private because is a singleton.*/
    //    private CalendarService() {
    //    }
    //    
    //    /**
    //     * Create unique instance of CalendarService (Singleton).
    //     * @return unique instance of CalendarService (cf. Singleton).
    //     */
    //    public static synchronized CalendarService getInstance() {
    //        if (instance == null) {
    //            instance = new CalendarService();
    //        }
    //        return instance;
    //    }*/

    /** The internal application name.*/
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /** The default JsonFactory.*/
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Allow the secured access to Calendar.
     * @return An instance CalendarService with secured transport.
     * @throws IOException .
     * @throws GeneralSecurityException .
     */
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre userKeyName7 pourrait être final
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de paramètres de méthode) manquant.
    private Calendar getService(String userKeyName7) throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY,
                Utils.getCredentials(httpTransport, userKeyName7)).setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    /**
     * Display next event (Afficher le prochain évènement du calendar).
     * @return Next event (le prochain évènement du calendar).
     * @throws IOException .
     * @throws GeneralSecurityException .
     */
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Le paramètre userKeyName7 pourrait être final
    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de paramètres de méthode) manquant.
    public String getNextEvents(String userKeyName8) throws IOException, GeneralSecurityException {

        //TODO gal by Djer |Log4J| Contextuzalise tes messages de log (e pour l'utilsiateur : " + userKeyName8)
        LOG.info("Récupération du prochain évènement de l'utilisateur");

        String nextEvent1 = "";

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKeyName8).events().list("primary").setMaxResults(1).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            nextEvent1 = "Pas de nouvel évènement";
        } else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                //TODO gal by Djer |POO| Un espace entre le "sumary" et le "start" rendrait plus lisible.
                nextEvent1 = "Votre prochain évènement : " + event.getSummary() + start;
            }
        }
        return nextEvent1;
    }
}
