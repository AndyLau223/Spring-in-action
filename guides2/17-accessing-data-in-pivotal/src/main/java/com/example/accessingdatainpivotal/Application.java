package com.example.accessingdatainpivotal;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;


import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;


/**
 * @EnableGemfireRepositories by default scans the current package for any itnerfaces that
 * extend one of Spring Data's repository interfaces. You can use its
 * `basePackageClasses = MyRepository.class` to safely tell Spring Data for Apache Geode
 * to scan a different root package by type for application-specific Repository extensions.
 *
 * @ClientCacheApplication to craete a "client" cache instance, which has the ability to
 * connect to and communicate with a cluster of servers. However, to keep things simple,
 * the client stores data locally by using a `LOCAL` client region, without the need
 * to setup or run any servers.
 */
@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApplication")
@EnableEntityDefinedRegions(
        basePackageClasses = Person.class,
        clientRegionShortcut = ClientRegionShortcut.LOCAL
)
public class Application {

    @Bean
    ApplicationRunner run(PersonRepository personRepository) {
        return args -> {
            Person alice = new Person("Adult Alice", 40);
            Person bob = new Person("Baby Bob", 1);
            Person carol = new Person("Teen Carol", 13);

            System.out.println("Before accessing data in Apache Geode...");

            asList(alice,bob,carol).forEach(
                    person -> System.out.println("\t" + person)
            );

            personRepository.save(alice);
            personRepository.save(bob);
            personRepository.save(carol);

            System.out.println("Lookup each person by name...");

            asList(alice.getName(), bob.getName(), carol.getName())
                    .forEach(name -> System.out.println("\t" + personRepository.findByName(name)));

            System.out.println("Query adults (over 18):");
            stream(personRepository.findByAgeGreaterThan(18).spliterator(), false)
                    .forEach(person -> System.out.println("\t" + person));

            System.out.println("Query babies (less than 5):");
            stream(personRepository.findByAgeLessThan(5).spliterator(),false)
                    .forEach(person -> System.out.println("\t" + person));

            System.out.println("Query teens (between 12 and 20):");

            stream(personRepository.findByAgeGreaterThanAndAgeLessThan(12, 20).spliterator(), false)
                    .forEach(person -> System.out.println("\t" + person));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
