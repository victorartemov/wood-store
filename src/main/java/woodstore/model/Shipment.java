package woodstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.Data;

/**
 * Created by Виктор on 06.02.2017.
 */
@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Shipment extends Item {

    public Shipment() {
    }

    private Long workerId;
    private String date;
    private String shipFrom;
    private String shipTo;

    private boolean closed;
}