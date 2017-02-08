package woodstore.service;

import woodstore.model.Item;

import java.util.List;

/**
 * Created by Виктор on 07.02.2017.
 */
public interface ItemService<T extends Item> {
    void add(T item);
    void delete(Long id);
    void edit(T item);
    List<T> getAll();
}
