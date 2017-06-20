package woodstore.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.Data;

/**
 * Created by Виктор on 18.01.2017.
 */

@Entity
@Data
public class Profile extends Item {

    public Profile() {
    }

    private String name;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDITIONAL_PHONE_NUMBER")
    private String additionalPhoneNumber;

    private String address;
    private String login;
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable()
    private Set<Role> roles;
}
