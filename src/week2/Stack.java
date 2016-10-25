//package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Stack {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (InputStream inputStream = new FileInputStream(new File("stack.in"));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        OutputStream outputStream = new FileOutputStream(new File("stack.out"));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));) {
            Integer noOfCommands = Integer.valueOf(reader.readLine());
            int currentLine = 1;
            String line;
            String[] commands = new String[noOfCommands];
            int commandsLength = 0;
            while ((line = reader.readLine()) != null && currentLine <= noOfCommands) {
                if (line.contains("+")) {
                    commands[commandsLength] = line.split(" ")[1];
                    commandsLength++;
                    currentLine++;
                }
                else if (line.contains("-")) {
                    writer.write(commands[commandsLength - 1] + "\r\n");
                    commands[commandsLength - 1] = null;
                    commandsLength--;
                    currentLine++;
                }
            }

        }
    }
}
