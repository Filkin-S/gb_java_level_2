package lesson7.server;

import java.sql.*;
import java.util.Optional;


public class JdbcAuthService implements AuthService {
    private static Connection connection;
    private static PreparedStatement authStatement;


    @Override
    public void start() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:auth_db.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            authStatement = connection.prepareStatement("SELECT nick FROM users WHERE login = ? AND password = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (authStatement != null) {
            try {
                authStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String password) {
        try {
            authStatement.setString(1, login);
            authStatement.setString(2, password);
            ResultSet resultSet = authStatement.executeQuery();

            if(resultSet.next()) {
                String nick = resultSet.getString("nick");
                return Optional.of(nick);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }
}
