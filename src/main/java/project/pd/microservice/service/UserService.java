package project.pd.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.pd.microservice.amqp.UserProducer;
import project.pd.microservice.model.User;
import project.pd.microservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProducer userProducer;
	
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}
   
	public User getUserById(Long idUser) {
		return this.userRepository.findById(idUser).orElse(null);
	}
   
	@Transactional
	public User insertOrUpdate(User user) {
		return this.userRepository.save(user);
	} 
	
	public void delete(Long idUser) {
		User usuario = this.userRepository.findById(idUser).orElse(null);
		this.userProducer.sendToDeleteUser("bk_1", usuario.getDecisions());
		this.userRepository.deleteById(idUser);
	}
}
