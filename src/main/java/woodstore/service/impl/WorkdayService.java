package woodstore.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodstore.model.Workday;
import woodstore.repository.WorkdayRepository;
import woodstore.service.ItemService;
import woodstore.utils.CurrentDateUtil;

/**
 * Created by Viktor_Artemov on 3/22/2017.
 */
@Service
public class WorkdayService implements ItemService<Workday> {

    @Autowired
    private WorkdayRepository workdayRepository;

    @Override
    public void add(Workday item) {
        Workday existingWorkday = workdayRepository.findByDate(CurrentDateUtil.getCurrentDate("dd.MM.yyyy"));
        if (existingWorkday == null) {
            workdayRepository.saveAndFlush(item);
        }
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

    public Workday findByDate(String date) {
        return workdayRepository.findByDate(date);
    }

    public Workday today() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        return workdayRepository.findByDate(dateFormatter.format(currentDate));
    }
}
