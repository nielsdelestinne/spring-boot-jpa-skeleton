package be.niels.jpaskeleton.secret;

import be.niels.jpaskeleton.shared.Identifiable;
import be.niels.jpaskeleton.shared.UniqueId;
import be.niels.jpaskeleton.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SECRET")
public class Secret implements Identifiable<Secret.SecretId> {

    @Id
    @SequenceGenerator(name = "secret_seq_gen", sequenceName = "secret_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secret_seq_gen")
    @Column(name = "ID")
    private Long id;

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

    @Override
    public SecretId getId() {
        return new SecretId(id);
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
        return Objects.equals(id, secret.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ": " + content + ". Owned by: " + owner;
    }

    public final static class SecretId extends UniqueId {
        public SecretId(Long id) {
            super(id);
        }
    }
}
