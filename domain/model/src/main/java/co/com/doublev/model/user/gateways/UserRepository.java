package co.com.doublev.model.user.gateways;

import co.com.doublev.model.user.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    List<User> getAllUser();
}
