package be.niels.jpaskeleton.shared;

import java.io.Serializable;
import java.util.Objects;

public abstract class UniqueId implements Serializable {

    private final Long id;

    public UniqueId(Long id) {
        this.id = id;
    }

    public Long value() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqueId uniqueId1 = (UniqueId) o;
        return Objects.equals(id, uniqueId1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UniqueId{" + id + "}";
    }
}
