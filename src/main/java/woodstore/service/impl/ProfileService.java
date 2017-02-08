package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Profile;
import woodstore.repository.ProfileRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Виктор on 08.02.2017.
 */
@Service
public class ProfileService implements ItemService<Profile> {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public void add(Profile item) {
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

    public Profile getByName(String name){
        return profileRepository.findByName(name);
    }
}
