package tr.com.obssintern.book_portal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "fav_books")
    private List<Book> favBooks;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "read_list")
    private List<Book> readList;

}
