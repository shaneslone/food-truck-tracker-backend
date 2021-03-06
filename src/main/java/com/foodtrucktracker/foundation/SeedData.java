package com.foodtrucktracker.foundation;

import com.foodtrucktracker.foundation.models.*;
import com.foodtrucktracker.foundation.services.MenuItemService;
import com.foodtrucktracker.foundation.services.RoleService;
import com.foodtrucktracker.foundation.services.TruckService;
import com.foodtrucktracker.foundation.services.UserService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Locale;

import static com.foodtrucktracker.foundation.constants.RoleValues.*;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    TruckService truckService;

    @Autowired
    MenuItemService menuItemService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role(ADMIN.getRoleName());
        Role r2 = new Role(DINER.getRoleName());
        Role r3 = new Role(OPERATOR.getRoleName());

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local",
                "43.6034958,-110.7363361");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1 = userService.save(u1);

        // data, user
        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.local",
                "43.6034958,-110.7363361");
        u2.getRoles()
            .add(new UserRoles(u2,
                r3));
        u2 = userService.save(u2);

        // user
        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local",
                "43.6034958,-110.7363361");
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        u3 = userService.save(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda",
                "43.6034958,-110.7363361");
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));
        u4 = userService.save(u4);

        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda",
                "43.6034958,-110.7363361");
        u5.getRoles()
            .add(new UserRoles(u5,
                r3));
        u5 = userService.save(u5);

        Truck t1 = new Truck("Pink Taco",
                "https://pinktaco.com/wp-content/uploads/2020/07/pink-taco-truck-ca.jpg",
                "Mexican",
                "44.6034958,-110.7363361",
                new Date(),
                u1);
        t1.getReviews().add(new DinerTruckReview(u1, t1, 5));
        t1.getReviews().add(new DinerTruckReview(u2, t1, 4));
        t1 = truckService.save(t1);

        Truck t2 = new Truck("Sal's Pizza",
                "https://2bwrl244x46j3rrorox6yc6b-wpengine.netdna-ssl.com/wp-content/uploads/2020/08/thumbnail_Sals_Mobile_024.jpg",
                "Pizza",
                "43.6034958,-112.7363361",
                new Date(),
                u2);
        t2 = truckService.save(t2);

        MenuItem m1 = new MenuItem(t1,
                "Taco",
                "It's a crunchy taco!",
                1.99);
        m1.getItemPhotos().add(new MenuItemPhoto(m1, "https://www.tacobell.com/_static/web/images/loyalty/side-overlay-2022-E01-Rewards-Doritos-Locos-Tacos-W.png"));
        m1.getCustomerRatings().add(new MenuItemReview(u3, m1, 5));
        m1.getCustomerRatings().add(new MenuItemReview(u2, m1, 2));
        m1 = menuItemService.save(m1);

        u1.getFavoriteTrucks().add(new DinerTrucks(u3, t2));
        u1 = userService.save(u1);

        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress(),
                        "12345678,12345678");
                fakeUser.getRoles()
                    .add(new UserRoles(fakeUser,
                        r2));
                userService.save(fakeUser);
            }
        }
    }
}