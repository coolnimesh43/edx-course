package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class APlusBSquare {

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
            InputStream inputStream = new FileInputStream(new File("aplusbb.in"));
            String[] numbers = APlusBSquare.readLineFromFile(inputStream).split(" ");
            // algorithm http://math.stackexchange.com/questions/922470/an-algorithm-to-obtain-square-of-any-number
            Long toBeSquared = Long.valueOf(numbers[1]);
            Long A = toBeSquared % 10;
            Long B = toBeSquared / 10;
            Long aSqared = A * A;
            Long unitPlace = aSqared % 10;
            Long carry = aSqared / 10;
            Long tempSum = ((B * toBeSquared) + (A * B) + carry);
            Long sum = (Long.valueOf((tempSum.toString() + unitPlace.toString())) + Long.valueOf(numbers[0]));
            File outFile = new File("aplusbb.out");
            APlusBSquare.writeToFile(outFile, sum.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
