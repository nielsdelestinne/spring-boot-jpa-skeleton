package be.niels.jpaskeleton.secret;

import be.niels.jpaskeleton.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SECRET")
public class Secret {

    @Id
    @SequenceGenerator(name = "secret_seq_gen", sequenceName = "secret_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secret_seq_gen")
    @Column(name = "ID")
    private long id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private Secret() {}

    public Secret(String content, User owner) {
        this.content = content;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secret secret = (Secret) o;
        return id == secret.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ": " + content + ". Owned by: " + owner;
    }
}
