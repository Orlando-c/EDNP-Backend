import java.sql.*;

public class UserLogin {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public UserLogin() throws SQLException {
        // Connect to the database
        String url = "jdbc:mysql://localhost/mydatabase";
        String username = "root";
        String password = "password";
        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
    }

    public void createUser(String username, String password) throws SQLException {
        // Create a new user in the database
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.executeUpdate();
    }

    public boolean checkUserExists(String username) throws SQLException {
        // Check if a user exists in the database
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        return rs.next();
    }

    public boolean checkPasswordMatches(String username, String password) throws SQLException {
        // Check if the provided password matches the one stored in the database
        String sql = "SELECT password FROM users WHERE username = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        if (!rs.next()) {
            return false;
        }
        String dbPassword = rs.getString("password");
        return password.equals(dbPassword);
    }

    public void updateUser(String username, String password) throws SQLException {
        // Update the password for a user in the database
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        pstmt.executeUpdate();
    }

    public void deleteUser(String username) throws SQLException {
        // Delete a user from the database
        String sql = "DELETE FROM users WHERE username = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
    }
}