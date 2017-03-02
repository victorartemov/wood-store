package woodstore.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Viktor_Artemov on 3/2/2017.
 */
@Entity
@Table(name = "roles")
public class Role extends Item {

    public Role() {
    }

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Profile> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Profile> getUsers() {
        return users;
    }

    public void setUsers(Set<Profile> users) {
        this.users = users;
    }
}
