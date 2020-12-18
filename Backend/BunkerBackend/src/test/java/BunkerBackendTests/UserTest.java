package BunkerBackendTests;

//project imports
import bunker.controllers.userController;
import bunker.models.user;
import bunker.repository.userRepository;
import bunker.service.userService;

// import junit/spring tests
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    private userService us;

    @Mock
    private userRepository ur;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() throws Exception {


        //decalre a user
        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("password");
        u.setAddress("123 test address");

        //declare login hashmap
        HashMap<String, String> uMap = new HashMap<String, String>();
        uMap.put("userName", "jon");
        uMap.put("password", "password");

        //declare a sucessful login hashmap
        HashMap<String, String> sucess = new HashMap<String, String>();
        sucess.put("status", "1");
        sucess.put("id", "74a5ce17-df9a-4502-98b6-84fb9d369859");

        //mock
        when(ur.findByUser("jon")).thenReturn(u);

        //sucessful login
        assertTrue(us.userLogin("jon", "password"));

        //failed login
        assertFalse(us.userLogin("jon", "badpassword"));

    }

    @Test
    public void testGetIdByUname() throws Exception {

        //decalre a user
        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("password");
        u.setAddress("123 test address");

        //for easy comparison
        String id = "74a5ce17-df9a-4502-98b6-84fb9d369859";

        //mock
        when(ur.findByUser("jon")).thenReturn(u);

        //test getting id as a string from a username
        assertEquals(us.getIdByName("jon"), id);

        //test a bad username
        assertEquals(us.getIdByName("badUname"), null);
    }

    @Test
    public void testGetUserByName() throws Exception {

        //decalre a user
        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("password");
        u.setAddress("123 test address");

        //mock
        when(ur.findByUser("jon")).thenReturn(u);

        assertEquals(us.getUserByName("jon"), u);

    }

    @Test
    public void testModifyUser() throws Exception {
        //decalre a user
        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("password");
        u.setAddress("123 test address");

        user newU = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("newpassword");
        u.setAddress("123 test address");

        UUID id = UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859");

        //mock
        when(ur.findById(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"))).thenReturn(u);
        when(ur.save(newU)).thenReturn(newU);

        //test the user is saved
        assertEquals(us.getUserById(id), u);

        //modify user
        us.modifyUser(id, newU);

        //test the the user has changed
        assertNotEquals(us.getUserById(id), newU);

    }

}
