package woodstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Виктор on 06.02.2017.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Shipment extends Item {

    public Shipment() {
    }

    private Long workerId;
    private String date;
    private String shipFrom;
    private String shipTo;

    private boolean closed;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWhence() {
        return shipFrom;
    }

    public void setWhence(String whence) {
        this.shipFrom = whence;
    }

    public String getWhere() {
        return shipTo;
    }

    public void setWhere(String where) {
        this.shipTo = where;
    }

    public String getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(String shipFrom) {
        this.shipFrom = shipFrom;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}