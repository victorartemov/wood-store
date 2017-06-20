package woodstore.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by Viktor_Artemov on 3/2/2017.
 */
@Entity
@Data
@Table(name = "roles")
public class Role extends Item {

    public Role() {
    }

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Profile> users;
}
