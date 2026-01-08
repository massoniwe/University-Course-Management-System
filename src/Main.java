import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
//main

public class Main {
    public static void main(String[] args) throws Exception {
        DBConnection s = new DBConnection("Azamat", 18, 1);
        s.save();

        DBConnection.updateCourse("Azamat", 2);

        for (DBConnection st : DBConnection.getAll()) {
            System.out.println(st);
        }

        DBConnection.delete("Azamat");
    }
}


