import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // URL de la base de datos H2 en memoria
        String jdbcURL = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";

        // SQL para crear la tabla
        String createTableSQL = "CREATE TABLE users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL, " +
                "email VARCHAR(50) NOT NULL)";

        // SQL para insertar datos
        String insertUserSQL = "INSERT INTO users (username, email) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            // Crear la tabla
            connection.createStatement().execute(createTableSQL);

            // Insertar datos
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
                preparedStatement.setString(1, "john_doe");
                preparedStatement.setString(2, "john.doe@example.com");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "jane_doe");
                preparedStatement.setString(2, "jane.doe@example.com");
                preparedStatement.executeUpdate();

                System.out.println("Datos insertados correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
