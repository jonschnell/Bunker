package bunker.controllers;

import bunker.repository.friendRepository;
import bunker.models.friend;
import bunker.service.friendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class friendController {

    @Autowired
    friendRepository db;

    @Autowired
    friendService fs;

    //add friend API
    @PostMapping("/friend/{id}")
    HashMap createFriendByUser(@RequestBody friend f, @PathVariable UUID id) {
        fs.createFriendByUser(f, id);
        return f.toHashMap();
    }

    //get friend API
    @GetMapping("/friend/getAll/{id}")
    HashMap<String, List<HashMap<String,String>>> findByUser_Id(@PathVariable UUID id){
        return fs.getAllFriend(id);
    }

    @GetMapping("/friend/getAllId/{id}")
    HashMap<String, List> UUID_findByUser_Id(@PathVariable UUID id){
        return fs.getAllFriendId(id);
    }

}
