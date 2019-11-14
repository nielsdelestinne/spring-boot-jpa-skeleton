package be.niels.jpaskeleton.secret;

import be.niels.jpaskeleton.secret.Secret.SecretId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretRepository extends JpaRepository<Secret, SecretId> {
}
