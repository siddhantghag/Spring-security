package com.springboot.Main.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Main.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	public User findByUsername(String username);
}
