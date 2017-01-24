package woodstore.model;

/**
 * Created by Виктор on 18.01.2017.
 */
public class Category extends Entity {

    public Category() {}

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
