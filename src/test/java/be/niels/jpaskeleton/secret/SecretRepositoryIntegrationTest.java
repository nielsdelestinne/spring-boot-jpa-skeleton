package be.niels.jpaskeleton.secret;

import be.niels.jpaskeleton.user.User;
import be.niels.jpaskeleton.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class SecretRepositoryIntegrationTest {


    private final SecretRepository secretRepository;
    private final UserRepository userRepository;

    @Autowired
    SecretRepositoryIntegrationTest(SecretRepository secretRepository, be.niels.jpaskeleton.user.UserRepository userRepository) {
        this.secretRepository = secretRepository;
        this.userRepository = userRepository;
    }

    @Test
    void save_givenANewSecretForAnExistingUser_thenSecretIsCorrectlySavedAndReceivedAnId() {
        User secretOwner = userRepository.save(new User("Brutus"));
        Secret secret = new Secret("I sometimes find pleasure in writing bugs", secretOwner);

        Secret savedSecret = secretRepository.save(secret);

        assertThat(savedSecret).isNotNull();
        assertThat(savedSecret.getId()).isNotNull();
        assertThat(savedSecret.getContent()).isEqualTo("I sometimes find pleasure in writing bugs");
        assertThat(savedSecret.getOwner()).isEqualTo(secretOwner);
    }

    @Test
    void findById_givenAnExistingSecretForId_thenReturnThatSecret() {
        Secret savedSecret = secretRepository
                .save(new Secret("I sometimes find pleasure in writing bugs",
                        userRepository.save(new User("Brutus"))));

        Optional<Secret> actualSecret = secretRepository.findById(savedSecret.getId().value());

        assertThat(actualSecret).isPresent();
        assertThat(actualSecret.get()).isEqualTo(savedSecret);
    }

}
