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
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList <String>record = new ArrayList<>();

        String ID = "";
        String name = "";
        String description = "";
        double cost = 0;

        boolean done = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter an ID");
            name = SafeInput.getNonZeroLenString(in, "Enter your name");
            description = SafeInput.getNonZeroLenString(in, "Enter your description");
            cost = SafeInput.getRangedDouble(in, "Enter the cost", 0, 9999);

            done = SafeInput.getYNConfirm(in, "Are you finished entering records?");

            record.add(ID + ", " + name + ", " + description + ", " + cost);
        }while(!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String r : record) {
                writer.write(r, 0, r.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
