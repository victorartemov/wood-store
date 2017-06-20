package woodstore.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
@Data
public class Workday extends Item {

    public Workday() {
    }

    private String date;
    private Long workerId;

    @Column(nullable = true)
    private boolean open;

    @OneToMany(fetch = FetchType.EAGER)
    private List<SoldProduct> products = new ArrayList<>();

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
