package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wstrug.kataPP.dao.UserDao;
import ru.wstrug.kataPP.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp {


   private final UserDao userDao;

   @Autowired
   UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   public void add(User user) {
      userDao.save(user);
   }

   @Transactional
   public void deleteUserById(int id) { userDao.deleteById(id); }

   @Transactional(readOnly = true)
   public List<User> listUsers() {
      return userDao.findAll();
   }

   @Transactional
   public void updateUserById(User user, int id) {
      user.setId(id);
      userDao.save(user); }

   @Transactional
   public User getUserById(int id) {
      Optional<User> user = userDao.findById(id);
      return user.orElse(null);
   }

}
