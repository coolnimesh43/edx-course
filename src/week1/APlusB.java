package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class APlusB {

    public static void writeToFile(File file, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
            writer.write(content);
        } catch (IOException e) {
            throw e;
        }
    }

    public static String readLineFromFile(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        return line;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter writer = null;) {
            InputStream inputStream = new FileInputStream(new File("aplusb.in"));
            String[] numbers = APlusB.readLineFromFile(inputStream).split(" ");
            Integer sum = Integer.valueOf(numbers[0]) + Integer.valueOf(numbers[1]);
            File outFile = new File("aplusb.out");
            APlusB.writeToFile(outFile, sum.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
