package bunker.service;

import bunker.models.user;
import bunker.repository.credentialRepository;
import bunker.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.UUID;

@Service
@Configurable
public class userService {

    @Autowired
    public userRepository ur;

    @Autowired
    credentialRepository cr;


    public boolean userLogin(String uName, String password){
        user u = ur.findByUser(uName);
        if (u.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean returnsTrue(String uName, String password){
        return true;
    }

    public String getIdByName(String uName){
        HashMap<String, String> uMap = new HashMap<String, String>();
        try {
            uMap = ur.findByUser(uName).toHashMap();
        }
        catch (NullPointerException e){
            return null;
        }
        return uMap.get("id");
    }


    //getters
    public user getUserByName(String uName){
        return ur.findByUser(uName);
    }
    public user getUserById(UUID id){
        return ur.findById(id);
    }

    //setters
    public user createUser(user u){
        return ur.save(u);
    }

    public user modifyUser(UUID id, user u){
        user old_u = ur.findById(id);
        old_u.setUser(u.getUser());
        old_u.setAddress(u.getAddress());
        old_u.setFirstName(u.getFirstName());
        old_u.setLastName(u.getLastName());
        old_u.setEmail(u.getEmail());
        old_u.setPhone(u.getPhone());
        old_u.setPassword(u.getPassword());
        ur.save(old_u);
        return old_u;
    }

    public boolean deleteUserById(UUID id){
        int valDB = cr.removeByUser_id(id);
        //remove user form db
        int valU = ur.removeById(id);
        if (valU == 1){
            return true;
        }else{
            return false;
        }
    }


//    @Modifying
//    @Query(value="DELETE FROM user WHERE user_id = ?1", nativeQuery=true)
//    public void removeAUser(UUID id){
//        //ur.removeById(id);
//    }
}
