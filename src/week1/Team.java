package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Team {

    public static void writeToFile(File file, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
            writer.write(content);
        } catch (IOException e) {
            throw e;
        }
    }

    public static List<String> readAllLines(File file) {
        try (InputStream inputStream = new FileInputStream(file);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
            Stream<String> data = bufferedReader.lines();
            return data.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        List<String> numbers = Team.readAllLines(new File("team.in"));
        String[] andrew = numbers.get(0).split(" ");
        String[] peter = numbers.get(1).split(" ");
        String[] paul = numbers.get(2).split(" ");
        double sum = 0;
        int a;
        int b;
        int c;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    for (int k = 0; k < 3; k++) {
                        if (i != k && j != k) {
                            a = Integer.parseInt(andrew[i]);
                            b = Integer.parseInt(peter[j]);
                            c = Integer.parseInt(paul[k]);
                            double tempSum = Math.sqrt(a * a + b * b + c * c);
                            sum = tempSum > sum ? tempSum : sum;
                        }
                    }
                }
            }
        }
        File outFile = new File("team.out");
        Team.writeToFile(outFile, String.valueOf(sum));
    }
}
