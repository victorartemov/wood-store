package woodstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
@Data
public class Category extends Item{

    public Category() {
    }

    @Column(name = "TITLE")
    private String title;

    public String getCategoryTitle() {
        return title;
    }

    @Column(name = "KIND")
    private boolean simple;

}

