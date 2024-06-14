package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wstrug.kataPP.model.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
//   void add(User user);
//   void deleteUserById(int id);
//   void updateUserById(User user, int id);
//   List<User> listUsers();
//   User getUserById(int id);
//   int clean();
}
