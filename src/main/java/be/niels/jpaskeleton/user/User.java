package be.niels.jpaskeleton.user;

import be.niels.jpaskeleton.shared.Identifiable;
import be.niels.jpaskeleton.shared.UniqueId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User implements Identifiable<User.UserId> {

    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    private User() {}

    public User(String username) {
        this.username = username;
    }

    @Override
    public UserId getId() {
        return new UserId(id);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ": " + username;
    }

    public final static class UserId extends UniqueId {
        public UserId(Long id) {
            super(id);
        }
    }

}
