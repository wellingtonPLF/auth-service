package project.pd.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.pd.microservice.model.User;
import project.pd.microservice.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
   	public List<User> getUsers() {
       	return this.userService.getUsers();
   	}
   
   	@GetMapping("/users/{iduser}")
   	public User getUserById(@PathVariable("iduser") Long iduser) {
       	return this.userService.getUserById(iduser);
   	}
   
   	@PostMapping("/users")
   	public User addUser(@RequestBody User user){
       	return this.userService.insertOrUpdate(user);
   	}
   
   	@PutMapping("/users/{iduser}")
   	public User updateUser(@RequestBody User user){
	   	return this.userService.insertOrUpdate(user);
   	}	

   	@DeleteMapping("/users/{iduser}")
   	public void deleteUser(@PathVariable("iduser") Long iduser) {
	   	this.userService.delete(iduser);
   	}
}