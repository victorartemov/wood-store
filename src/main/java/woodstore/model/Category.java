package woodstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
public class Category extends Item{

    public Category() {
    }

    @Column(name = "TITLE")
    private String title;

    public String getCategoryTitle() {
        return title;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.title = categoryTitle;
    }
}

