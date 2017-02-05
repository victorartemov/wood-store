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
    private String categoryTitle;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
