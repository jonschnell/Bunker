package bunker.controllers;

import bunker.repository.credentialRepository;
import bunker.models.credential;
import bunker.service.credentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class credentialController {

    @Autowired
    credentialRepository db;

    @Autowired
    credentialService cs;

    //add credential API
    @PostMapping("/credential/{id}")
    HashMap createCredentialByUser(@RequestBody credential c, @PathVariable UUID id) {
        cs.createCredentialByUser(c, id);
        return c.toHashMap();
    }

    //get credential API
    @GetMapping("/credential/getAll/{id}")
    HashMap<String, List<HashMap<String,String>>> findByUser_Id(@PathVariable UUID id){
        return cs.getAllCredential(id);
    }

    @GetMapping("/credential/getAllId/{id}")
    HashMap<String, List> UUID_findByUser_Id(@PathVariable UUID id){
        return cs.getAllCredentialId(id);
    }

}
