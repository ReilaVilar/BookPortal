package tr.com.obssintern.book_portal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tr.com.obssintern.book_portal.enumeration.RoleType;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType name;
}
