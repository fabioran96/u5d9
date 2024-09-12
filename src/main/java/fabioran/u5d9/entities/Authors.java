package fabioran.u5d9.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
    private String avatar;
}

/*
{
    "id": 1,
    "name": "mario",
    "surname": "rossi",
    "email": "prova@prova.com",
    "dateOfBirth": "Prova",
    "avatar": "https://ui-avatars.com/api/?name=m+r"
}
 */
