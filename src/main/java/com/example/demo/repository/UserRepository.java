package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import com.example.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Nullable
	public User findByUserName(String userName);
	@Nullable
	public Iterable<User> findByCityAndZipOrderByUserNameAsc(String city, int zip);
	public void removeByUserName(String userName);
	
}
