package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {


    private final RoleDao roleDao;

    @Autowired
    RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public void add(Role role) {
        roleDao.save(role);
    }

    @Transactional
    public void deleteRoleById(int id) { roleDao.deleteById(id); }

    @Transactional(readOnly = true)
    public List<Role> listRoles() {
        return roleDao.findAll();
    }

    @Transactional
    public void updateRoleById(Role role, Integer id) {
        role.setId(id);
        roleDao.save(role); }

    @Transactional
    public Role getRoleById(int id) {
        Optional<Role> user = roleDao.findById(id);
        return user.orElse(null);
    }

    //@Override
    public Role findByRole(String role) {
        return roleDao.findByRole(role);
    }

}