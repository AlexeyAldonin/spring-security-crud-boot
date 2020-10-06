package app.service;

import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleBootService implements EntityService<Role> {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleBootService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Set<Role> getAll() {
        //List<Role> all = roleRepository.findAll();
        return new HashSet<>(roleRepository.findAll());
    }
}
