package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import woodstore.model.Profile;
import woodstore.model.Role;
import woodstore.repository.ProfileRepository;
import woodstore.repository.RoleRepository;
import woodstore.service.ItemService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Виктор on 08.02.2017.
 */
@Service
public class ProfileService implements ItemService<Profile> {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public void add(Profile item) {
        item.setPassword(bCryptPasswordEncoder.encode(item.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        item.setRoles(roles);

        profileRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        profileRepository.delete(id);
    }

    @Override
    public void edit(Profile item) {
        profileRepository.saveAndFlush(item);
    }

    public Profile findByName(String name){
        return profileRepository.findByName(name);
    }
}
