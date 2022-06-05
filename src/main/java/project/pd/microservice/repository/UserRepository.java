package project.pd.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.pd.microservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByEmailAndName(String email, String name);
}