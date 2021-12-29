package com.foodtrucktracker.foundation.services;

import com.foodtrucktracker.foundation.FoundationApplicationTesting;
import com.foodtrucktracker.foundation.exceptions.ResourceNotFoundException;
import com.foodtrucktracker.foundation.models.Role;
import com.foodtrucktracker.foundation.models.User;
import com.foodtrucktracker.foundation.models.UserRoles;
import com.foodtrucktracker.foundation.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoundationApplicationTesting.class,
    properties = {
        "command.line.runner.enabled=false"})
public class UserServiceImplNoDBTest
{
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userrepos;

    @MockBean
    private RoleService roleService;

    @MockBean
    private HelperFunctions helperFunctions;

    private List<User> userList;

    @Before
    public void setUp() throws Exception
    {
        userList = new ArrayList<>();

        Role r1 = new Role("admin");
        r1.setRoleid(1);
        Role r2 = new Role("user");
        r2.setRoleid(2);
        Role r3 = new Role("data");
        r3.setRoleid(3);

        // admin, data, user
        User u1 = new User("admin",
            "ILuvM4th!",
            "admin@lambdaschool.test",
                "12345678,12345678");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));

        u1.setUserid(101);
        userList.add(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.test",
                "12345678,12345678");
        u1.getRoles()
            .add(new UserRoles(u2,
                r2));
        u1.getRoles()
            .add(new UserRoles(u2,
                r3));

        u2.setUserid(102);
        userList.add(u2);

        // user
        User u3 = new User("testingbarn",
            "ILuvM4th!",
            "testingbarn@school.lambda",
                "12345678,12345678");
        u3.getRoles()
            .add(new UserRoles(u3,
                r1));

        u3.setUserid(103);
        userList.add(u3);

        User u4 = new User("testingcat",
            "password",
            "testingcat@school.lambda",
                "12345678,12345678");
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));

        u4.setUserid(104);
        userList.add(u4);

        User u5 = new User("testingdog",
            "password",
            "testingdog@school.lambda",
                "12345678,12345678");
        u4.getRoles()
            .add(new UserRoles(u5,
                r2));

        u5.setUserid(105);
        userList.add(u5);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserById()
    {
        Mockito.when(userrepos.findById(101L))
            .thenReturn(Optional.of(userList.get(0)));

        assertEquals("admin",
            userService.findUserById(101L)
                .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findUserByIdNotFound()
    {
        Mockito.when(userrepos.findById(10L))
            .thenReturn(Optional.empty());

        assertEquals("admin",
            userService.findUserById(10L)
                .getUsername());
    }

    @Test
    public void findAll()
    {
        Mockito.when(userrepos.findAll())
            .thenReturn(userList);

        assertEquals(5,
            userService.findAll()
                .size());
    }

    @Test
    public void delete()
    {
        Mockito.when(userrepos.findById(103L))
            .thenReturn(Optional.of(userList.get(0)));

        Mockito.doNothing()
            .when(userrepos)
            .deleteById(103L);

        userService.delete(103L);
        assertEquals(5,
            userList.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFoundDelete()
    {
        Mockito.when(userrepos.findById(10L))
            .thenReturn(Optional.empty());

        Mockito.doNothing()
            .when(userrepos)
            .deleteById(10L);

        userService.delete(10L);
        assertEquals(5,
            userList.size());
    }

    @Test
    public void findByUsername()
    {
        Mockito.when(userrepos.findByUsername("admin"))
            .thenReturn(userList.get(0));

        assertEquals("admin",
            userService.findByName("admin")
                .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByUsernameNotfound()
    {
        Mockito.when(userrepos.findByUsername("nonsense"))
            .thenReturn(null);

        assertEquals("nonsense",
            userService.findByName("nonsense")
                .getUsername());
    }

    @Test
    public void findByNameContaining()
    {
        Mockito.when(userrepos.findByUsernameContainingIgnoreCase("a"))
            .thenReturn(userList);

        assertEquals(5,
            userService.findByNameContaining("a")
                .size());
    }

    @Test
    public void save()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("tiger",
            "ILuvMath!",
            "tiger@school.lambda",
                "12345678,12345678");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));

        Mockito.when(userrepos.save(any(User.class)))
            .thenReturn(u2);

        Mockito.when(roleService.findRoleById(2))
            .thenReturn(r2);

        assertEquals("tiger",
            userService.save(u2)
                .getUsername());
    }

    @Test
    public void savePut()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("tiger",
            "ILuvMath!",
            "tiger@school.lambda",
                "12345678,12345678");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.setUserid(103L);

        Mockito.when(roleService.findRoleById(2))
            .thenReturn(r2);

        Mockito.when(userrepos.findById(103L))
            .thenReturn(Optional.of(u2));

        Mockito.when(userrepos.save(any(User.class)))
            .thenReturn(u2);

        assertEquals(103L,
            userService.save(u2)
                .getUserid());
    }

    @Test
    public void update()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("cinnamon",
            "password",
            "cinnamon@school.lambda",
                "12345678,12345678");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));

        Mockito.when(roleService.findRoleById(2))
            .thenReturn(r2);

        Mockito.when(userrepos.findById(103L))
            .thenReturn(Optional.of(userList.get(2)));

        Mockito.when(userrepos.save(any(User.class)))
            .thenReturn(u2);

        Mockito.when(helperFunctions.isAuthorizedToMakeChange(anyString()))
            .thenReturn(true);

        assertEquals("cinnamon@school.lambda",
            userService.update(u2,
                103L)
                .getEmail());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateNotFound()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("cinnamon",
            "password",
            "cinnamon@school.lambda",
                "12345678,12345678");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));

        Mockito.when(roleService.findRoleById(2))
            .thenReturn(r2);

        Mockito.when(userrepos.findById(103L))
            .thenReturn(Optional.empty());

        Mockito.when(userrepos.save(any(User.class)))
            .thenReturn(u2);

        Mockito.when(helperFunctions.isAuthorizedToMakeChange(anyString()))
            .thenReturn(false);

        assertEquals("cinnamon@school.lambda",
                userService.update(u2,
                                103L)
                        .getEmail());
    }

    @Test
    public void deleteAll()
    {
        Mockito.doNothing()
            .when(userrepos)
            .deleteAll();

        userService.deleteAll();
        assertEquals(5,
            userList.size());

    }
}