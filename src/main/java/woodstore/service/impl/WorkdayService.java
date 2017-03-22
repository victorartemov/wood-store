package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Workday;
import woodstore.repository.WorkdayRepository;
import woodstore.service.ItemService;

import java.util.Date;
import java.util.List;

/**
 * Created by Viktor_Artemov on 3/22/2017.
 */
@Service
public class WorkdayService implements ItemService<Workday> {

    @Autowired
    private WorkdayRepository workdayRepository;

    @Override
    public void add(Workday item) {
        workdayRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        workdayRepository.delete(id);
    }

    @Override
    public void edit(Workday item) {
        workdayRepository.saveAndFlush(item);
    }

    @Override
    public List<Workday> findAll() {
        return workdayRepository.findAll();
    }

    public Workday findByDate(String date){
        return workdayRepository.findByDate(date);
    }
}
