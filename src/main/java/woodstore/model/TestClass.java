package woodstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Виктор on 22.01.2017.
 */
@Entity
public class TestClass {
    @Id
    private Long id;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
