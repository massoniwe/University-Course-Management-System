import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/university_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    //private int id;
    private String name;
    private int age;
    private int course;


        public DBConnection(String name, int age, int course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }


    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void save() throws SQLException {
        String sql = "INSERT INTO students(name, age, course) VALUES (?, ?, ?)";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, course);
            ps.executeUpdate();
        }
    }

    public static ArrayList<DBConnection> getAll() throws SQLException {
        ArrayList<DBConnection> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection c = connect();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new DBConnection(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getInt("course")
                ));
            }
        }
        return list;
    }

    public static void updateCourse(String name, int newCourse) throws SQLException {
        String sql = "UPDATE students SET course=? WHERE name=?";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, newCourse);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    public static void delete(String name) throws SQLException {
        String sql = "DELETE FROM students WHERE name=?";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return name + ", age " + age + ", course " + course;
    }
}



