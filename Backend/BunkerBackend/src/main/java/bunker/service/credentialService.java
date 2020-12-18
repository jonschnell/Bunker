package bunker.service;

import bunker.models.credential;
import bunker.models.user;
import bunker.repository.credentialRepository;
import bunker.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@Transactional
public class credentialService {

    @Autowired
    userRepository ur;

    @Autowired
    credentialRepository cr;

    public List<credential> getAllCredentialsForUser(UUID id){
        return cr.findByUser_Id(id);
    }

    public void createCredentialByUser(credential c, UUID u_id){
        c.setId(UUID.randomUUID());
        user tmp = ur.findById(u_id);
        c.setOwner(tmp);
        cr.save(c);
    }

    //TODO remove or use
    public Set getCredentialByUser(@PathVariable UUID id){
        user tmp = ur.findById(id);
        Set<credential> tempCreds = tmp.getCredentials();
        return tempCreds;
    }

    //TODO remove or use
    void removeByUser_id(UUID id){
        cr.removeByUser_id(id);
    }

    public HashMap<String, List<HashMap<String, String>>> getAllCredential(UUID id){
        List<HashMap<String,String>> returnList;
        returnList = new ArrayList<HashMap<String, String>>();
        List<credential> credList;
        credList = cr.findByUser_Id(id);
        for(credential c : credList) {
            returnList.add(c.toHashMap());
        }
        HashMap<String, List<HashMap<String,String>>> returnHash = new HashMap<String, List<HashMap<String,String>>>();
        returnHash.put("credentials", returnList);
        return returnHash;
    }


    public HashMap<String, List>  getAllCredentialId(UUID id){
        HashMap<String, List> returnHash;
        returnHash = new HashMap<>();

        List<credential> credList;
        credList = cr.findByUser_Id(id);

        List<UUID> credIdList;
        credIdList = new LinkedList<>();

        for(credential c : credList) {
            credIdList.add(c.getId());
        }
        returnHash.put("credentials", credIdList);
        return returnHash;
    }

}