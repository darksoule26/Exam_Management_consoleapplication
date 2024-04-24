import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllResult {

    public void viewAllResults() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root")) {
            String query = "SELECT * FROM result";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No results found for any user.");
            } else {
                System.out.println("Results for all users:");
                System.out.println("Username\tMarks");
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    int marks = resultSet.getInt("marks");
                    System.out.println(username + "\t\t" + marks);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ViewAllResult viewAllResult = new ViewAllResult();
        viewAllResult.viewAllResults();
    }
}
