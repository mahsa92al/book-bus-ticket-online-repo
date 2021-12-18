package model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Admin extends Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Role role;
}
