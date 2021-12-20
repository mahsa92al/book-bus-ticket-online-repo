package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@MappedSuperclass
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
}
