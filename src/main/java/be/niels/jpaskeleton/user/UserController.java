package be.niels.jpaskeleton.user;

import be.niels.jpaskeleton.user.dtos.GetAllUserResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAllUserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(GetAllUserResponse::of)
                .collect(Collectors.toList());
    }

}
