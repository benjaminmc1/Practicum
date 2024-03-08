import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class PersonGenerator {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList <String>people = new ArrayList<>();

        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;
        boolean done = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Enter your title");
            YOB = SafeInput.getRangedInt(in, "Enter your year of birth" , 1000, 9999);

            done = SafeInput.getYNConfirm(in, "Are you finished entering records?");

            people.add(ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB);
        }while(!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String p : people) {
                writer.write(p, 0, p.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
