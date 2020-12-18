package bunker.service;

import bunker.models.friend;
import bunker.models.user;
import bunker.repository.friendRepository;
import bunker.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@Transactional
public class friendService {

    @Autowired
    userRepository ur;

    @Autowired
    friendRepository fr;

    public List<friend> getAllFriendsForUser(UUID id){
        return fr.findByUser_Id(id);
    }

    public void createFriendByUser(friend f, UUID u_id){
        f.setId(UUID.randomUUID());
        user tmp = ur.findById(u_id);
        f.setOwner(tmp);
        fr.save(f);
    }

    //TODO remove or use
    public Set getFriendByUser(@PathVariable UUID id){
        user tmp = ur.findById(id);
        Set<friend> tempFriends = tmp.getFriends();
        return tempFriends;
    }

    //TODO remove or use
    void removeByUser_id(UUID id){
        fr.removeByUser_id(id);
    }

    public HashMap<String, List<HashMap<String, String>>> getAllFriend(UUID id){
        List<HashMap<String,String>> returnList;
        returnList = new ArrayList<HashMap<String, String>>();
        List<friend> friendList;
        friendList = fr.findByUser_Id(id);
        for(friend f : friendList) {
            returnList.add(f.toHashMap());
        }
        HashMap<String, List<HashMap<String,String>>> returnHash = new HashMap<String, List<HashMap<String,String>>>();
        returnHash.put("friends", returnList);
        return returnHash;
    }


    public HashMap<String, List>  getAllFriendId(UUID id){
        HashMap<String, List> returnHash;
        returnHash = new HashMap<>();

        List<friend> friendList;
        friendList = fr.findByUser_Id(id);

        List<UUID> friendIdList;
        friendIdList = new LinkedList<>();

        for(friend f : friendList) {
            friendIdList.add(f.getId());
        }
        returnHash.put("friend", friendIdList);
        return returnHash;
    }

}