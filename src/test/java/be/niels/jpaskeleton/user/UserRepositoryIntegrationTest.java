package be.niels.jpaskeleton.user;

import be.niels.jpaskeleton.user.User.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(savedUser.getId()).isEqualTo(new UserId(1L));
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
