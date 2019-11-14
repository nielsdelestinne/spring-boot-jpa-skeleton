package be.niels.jpaskeleton.user.dtos;

import be.niels.jpaskeleton.user.User;

public class GetAllUserResponse {

    public Long id;
    public String username;

    private GetAllUserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static GetAllUserResponse of(User user) {
        return new GetAllUserResponse(
                user.getId().value(),
                user.getUsername());
    }

}
