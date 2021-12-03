package lesson7.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseAuthService implements AuthService {
    private List<Entry> entries;

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("login1", "pass1", "nick1"));
        entries.add(new Entry("login2", "pass2", "nick2"));
        entries.add(new Entry("login3", "pass3", "nick3"));
    }

    @Override
    public void start() {
        System.out.println("Base authentication service started");
    }

    @Override
    public void stop() {
        System.out.println("Base authentication service stopped");
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String password) {
        return entries.stream()
                .filter(entry -> entry.login.equals(login) && entry.password.equals(password))
                .map(entry -> entry.nick)
                .findFirst();
    }

    private class Entry {
        private String login;
        private String password;
        private String nick;


        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }
}
