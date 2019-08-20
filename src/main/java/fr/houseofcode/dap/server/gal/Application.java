package fr.houseofcode.dap.server.gal;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de classe) manquant.
@SpringBootApplication
/**
 *
 * @author Alex
 */
public class Application {

    //TODO gal by Djer |Audit Code| (re)-active PMD et Checkstyle. Commentaire Javadoc (de méthode et de paramètres de méthode) manquant.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //TODO gal by Djer |Spring| Cette méthode n'est plus utile (elle sert dans le "tuto" pour vérifer que Srping Boot fonctionne. Elle affiche tout ce qui est "dans le conteneur IOC de Spring"). Tu peux la supprimer.
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
