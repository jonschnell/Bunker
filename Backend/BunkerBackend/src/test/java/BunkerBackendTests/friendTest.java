package BunkerBackendTests;

//project imports
import bunker.controllers.userController;
import bunker.models.friend;
import bunker.models.user;
import bunker.repository.friendRepository;
import bunker.repository.userRepository;
import bunker.service.friendService;
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
public class friendTest {

    @InjectMocks
    private friendService fs;

    @Mock
    private friendRepository fr;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFriend() throws Exception {

        UUID ownerId = UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859");

        UUID friendId = UUID.fromString("97f2979a-31fd-4026-b275-01f250df3de5");
        UUID friendId1 = UUID.fromString("a9a756e3-0127-4a96-95f1-9ec22d062207");

        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("imaymir");
        u.setFirstName("isabel");
        u.setLastName("maymir");
        u.setEmail("example@example.com");
        u.setPhone("4652183");
        u.setPassword("password");
        u.setAddress("123 address");

        friend f = new friend();
        f.setOwner(u);
        f.setId(friendId);
        f.setFriendName("friend1");

        friend f1 = new friend();
        f.setOwner(u);
        f.setId(friendId1);
        f.setFriendName("friend2");


        List<friend> friendList = new ArrayList<friend>();
        friendList.add(f);
        //friendList.add(c1);

        List<HashMap<String,String>> returnList;
        returnList = new ArrayList<>();
        returnList.add(f.toHashMap());
        //returnList.add(f1.toHashMap());

        HashMap<String, List<HashMap<String,String>>> returnHash = new HashMap<String, List<HashMap<String,String>>>();
        returnHash.put("friends", returnList);

        when(fr.findByUser_Id(ownerId)).thenReturn(friendList);

        assertEquals(fs.getAllFriend(ownerId), returnHash);

    }
    @Test
    public void testGetAllFriendId() throws Exception {

        UUID ownerId = UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859");

        UUID friendId = UUID.fromString("97f2979a-31fd-4026-b275-01f250df3de5");
        UUID friendId1 = UUID.fromString("a9a756e3-0127-4a96-95f1-9ec22d062207");

        user u = new user();
        u.setId(UUID.fromString("74a5ce17-df9a-4502-98b6-84fb9d369859"));
        u.setUser("imaymir");
        u.setFirstName("isabel");
        u.setLastName("maymir");
        u.setEmail("example@example.com");
        u.setPhone("4652183");
        u.setPassword("password");
        u.setAddress("123 address");

        friend f = new friend();
        f.setOwner(u);
        f.setId(friendId);
        f.setFriendName("friend");

        friend f1 = new friend();
        f.setOwner(u);
        f.setId(friendId1);
        f.setFriendName("alsofriend");

        List<friend> friendList = new ArrayList<friend>();
        friendList.add(f);
        friendList.add(f1);

        List<UUID> friendIdList = new ArrayList<UUID>();
        friendIdList.add(f.getId());
        friendIdList.add(f1.getId());

        HashMap<String, List> returnHash;
        returnHash = new HashMap<>();
        returnHash.put("friend", friendIdList);

        when(fr.findByUser_Id(ownerId)).thenReturn(friendList);

        assertEquals(fs.getAllFriendId(ownerId), returnHash);
    }
}