package woodstore.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import lombok.Data;

/**
 * Created by Виктор on 18.01.2017.
 */
@MappedSuperclass
@Data
public abstract class Item implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
}
