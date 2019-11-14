package be.niels.jpaskeleton.shared;

public interface Identifiable<ID extends UniqueId> {

    ID getId();

}
