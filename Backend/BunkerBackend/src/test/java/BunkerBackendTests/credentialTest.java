package BunkerBackendTests;

//project imports
import bunker.controllers.userController;
import bunker.models.credential;
import bunker.models.user;
import bunker.repository.credentialRepository;
import bunker.repository.userRepository;
import bunker.service.credentialService;
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
public class credentialTest {

    @InjectMocks
    private credentialService cs;

    @Mock
    private credentialRepository cr;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCredential() throws Exception {

        UUID ownerId = UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859");

        UUID credentialId = UUID.fromString("97f2979a-31fd-4026-b275-01f250df3de5");
        UUID credentialId1 = UUID.fromString("a9a756e3-0127-4a96-95f1-9ec22d062207");

        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("password");
        u.setAddress("123 test address");

        credential c = new credential();
        c.setOwner(u);
        c.setId(credentialId);
        c.setCredentialName("netflix");
        c.setPassword("password");
        c.setUserName("uname");

        credential c1 = new credential();
        c.setOwner(u);
        c.setId(credentialId1);
        c.setCredentialName("hulu");
        c.setPassword("hulupassword");
        c.setUserName("huluuname");

        List<credential> credList = new ArrayList<credential>();
        credList.add(c);
        //credList.add(c1);

        List<HashMap<String,String>> returnList;
        returnList = new ArrayList<>();
        returnList.add(c.toHashMap());
        //returnList.add(c1.toHashMap());

        HashMap<String, List<HashMap<String,String>>> returnHash = new HashMap<String, List<HashMap<String,String>>>();
        returnHash.put("credentials", returnList);

        //mock
        when(cr.findByUser_Id(ownerId)).thenReturn(credList);

        //testing the construction of a credential list to be returned
        assertEquals(cs.getAllCredential(ownerId), returnHash);

    }
    @Test
    public void testGetAllCredentialId() throws Exception {

        UUID ownerId = UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859");

        UUID credentialId = UUID.fromString("97f2979a-31fd-4026-b275-01f250df3de5");
        UUID credentialId1 = UUID.fromString("a9a756e3-0127-4a96-95f1-9ec22d062207");

        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("jon");
        u.setFirstName("jonathon");
        u.setLastName("schnell");
        u.setEmail("example@gmail.com");
        u.setPhone("123");
        u.setPassword("password");
        u.setAddress("123 test address");

        credential c = new credential();
        c.setOwner(u);
        c.setId(credentialId);
        c.setCredentialName("netflix");
        c.setPassword("password");
        c.setUserName("uname");

        credential c1 = new credential();
        c.setOwner(u);
        c.setId(credentialId1);
        c.setCredentialName("hulu");
        c.setPassword("hulupassword");
        c.setUserName("huluuname");

        List<credential> credList = new ArrayList<credential>();
        credList.add(c);
        credList.add(c1);

        //construct expected list
        List<UUID> credIdList = new ArrayList<UUID>();
        credIdList.add(c.getId());
        credIdList.add(c1.getId());


        HashMap<String, List> returnHash;
        returnHash = new HashMap<>();
        returnHash.put("credentials", credIdList);

        //mock
        when(cr.findByUser_Id(ownerId)).thenReturn(credList);

        //test the construction of and array of credential ids
        assertEquals(cs.getAllCredentialId(ownerId), returnHash);
    }
}