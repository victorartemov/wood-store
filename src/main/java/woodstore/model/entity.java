package woodstore.model;

import java.io.Serializable;

/**
 * Created by Виктор on 18.01.2017.
 */
public abstract class Entity implements Serializable{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
