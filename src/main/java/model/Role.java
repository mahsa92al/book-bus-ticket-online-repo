package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> users;
    @OneToOne
    private Admin admin;
}
