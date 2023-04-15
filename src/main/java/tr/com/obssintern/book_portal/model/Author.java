package tr.com.obssintern.book_portal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Author extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
}
