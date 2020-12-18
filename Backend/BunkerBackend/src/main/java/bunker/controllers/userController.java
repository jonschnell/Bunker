package bunker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import bunker.models.user;
import bunker.repository.credentialRepository;
import bunker.repository.userRepository;
import bunker.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {


	@Autowired
	userService us;

	//return user given uuid API
	@GetMapping("/user/{id}")
	//user getUser(@PathVariable Integer id) {return db.findOne(id);}
	HashMap getUser(@PathVariable UUID id) {return us.getUserById(id).toHashMap();}

	//get user by name API
	//TODO TESTING ONLY!
	@GetMapping("/user/name/{name}")
	HashMap getUser(@PathVariable String name) {return us.getUserByName(name).toHashMap();}


	//DROP ALL USERS API
	//FOR TESTING ONLY!
	//@RequestMapping("/users")
	//List<HashMap> getAllUsers() { return db.findAll().toHashMap(); }


	//create user API
	@PostMapping("/user")
	user createUser(@RequestBody user u) {
		//u.setId(UUID.randomUUID());
		us.createUser(u);
		return u;
	}

	//login API
//	@PostMapping("/user/login")
	//HashMap<String,String> logIn(@RequestBody user u) {
//	HashMap<String,String> logIn(@RequestBody user u) {
//		HashMap<String, String> returnVal = new HashMap<String, String>();
//		try {
//			user temp = db.findByUser(u.getUser());
//			if (temp.getPassword().equals(u.getPassword())) {
//				returnVal.put("id", temp.getId().toString());
//				returnVal.put("status", "1");
//			} else {
//				returnVal.put("status", "0");
//			}
//		}
//		catch(Exception e){
//			returnVal.put("status", "-1");
//		}
//		return returnVal;
//	}

	//login API
	@PostMapping("/user/login")
	//HashMap<String,String> logIn(@RequestBody user u) {
	HashMap logIn(@RequestBody HashMap u) {
		HashMap<String, String> returnVal = new HashMap<String, String>();
		if (us.userLogin(u.get("userName").toString(), u.get("password").toString()) == true) {
				returnVal.put("status", "1");
				returnVal.put("id", us.getIdByName(u.get("userName").toString()));
			} else {
				returnVal.put("status", "0");
			}

		return returnVal;
	}


	//modify user API
	@PutMapping("/user/{id}")
	HashMap updateUser(@RequestBody user u, @PathVariable UUID id) {
		return us.modifyUser(id, u).toHashMap();
	}

	//delete user API
	@DeleteMapping("/user/{id}")
	HashMap deleteUser(@PathVariable UUID id) {
		HashMap<String, String> returnVal = new HashMap<String, String>();
		boolean valU = us.deleteUserById(id);
		if (valU == true){
			returnVal.put("status", "1");
		}else{
			returnVal.put("status", "0");
		}
		return returnVal;
	}

}
