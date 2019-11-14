package be.niels.jpaskeleton.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test class uses the @DataJpaTest annotation:
 * Closely inspect the full output when running this test. You will notice the following:
 * + Spring will find the Spring Data Repositories
 *      "Finished Spring Data repository scanning in 92ms. Found 2 repository interfaces."
 * + Spring will replace the DataSource with an embedded (H2) variant, which it will also start (it's added as a dependency)
 * + Hibernate, activated by Spring, will drop existing tables / sequences and create new tables and sequences based
 *      on the JPA annotations (e.g. For User and Secret). Thus, JPA is used to generate the Database Schema.
 * + For each test method, a (test) transaction will be created, after each test method the transaction will end with a rollback
 * + At the end, the embedded H2 database is shutdown.
 */
@DataJpaTest
class UserRepositoryIntegrationTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryIntegrationTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void save_givenANewUser_thenUserIsCorrectlySavedAndReceivedAnId() {
        User newUser = new User("jimmy.jamma");

        User savedUser = userRepository.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("jimmy.jamma");
    }

    @Test
    void findAll_givenUsersInTheDatabase_thenAllUsersAreReturned() {
        User dirk = userRepository.save(new User("Dirk"));
        User stefania = userRepository.save(new User("Stefania"));

        List<User> allUsers = userRepository.findAll();

        assertThat(allUsers).containsExactlyInAnyOrder(dirk, stefania);
    }

    @Test
    void findById_givenAnExistingUserForId_thenReturnThatUser() {
        User dirk = userRepository.save(new User("Dirk"));
        userRepository.save(new User("Stefania"));

        Optional<User> actualDirk = userRepository.findById(dirk.getId().value());

        assertThat(actualDirk)
                .isPresent()
                .get().isEqualTo(dirk);
    }


}
