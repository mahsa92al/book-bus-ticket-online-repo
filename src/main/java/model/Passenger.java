package model;

import lombok.Data;
import model.enumeration.Gender;

import javax.persistence.*;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private String phoneNumber;
    private long nationalNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    @OneToOne
    private Ticket ticket;
}
