package lesson7.server;

import java.util.Optional;

public interface AuthService {

    void start();

    void stop();

    Optional<String> getNickByLoginAndPass(String login, String password);

}
