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

public class Prepare {

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
            InputStream inputStream = new FileInputStream(new File("prepare.in"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> data = bufferedReader.lines();
            List<String> dataAsString = data.collect(Collectors.toList());
            int noOfDays = Integer.valueOf(dataAsString.get(0));
            String[] practical = dataAsString.get(1).split(" ");
            String[] theory = dataAsString.get(2).split(" ");
            Integer sum = 0;
            Boolean practicalIncluded = Boolean.FALSE;
            Boolean theoryIncluded = Boolean.FALSE;
            for (int i = 0; i < noOfDays; i++) {
                System.out.println("nth day" + i);
                int p = Integer.valueOf(practical[i]);
                int t = Integer.valueOf(theory[i]);
                if (p >= t) {
                    System.out.println("its practical" + p);
                    sum += p;
                    practicalIncluded = Boolean.TRUE;
                }
                else {
                    System.out.println("its theory" + t);
                    sum += t;
                    theoryIncluded = Boolean.TRUE;
                }
            }
            if (!theoryIncluded || !practicalIncluded) {
                System.out.println("theory or practical not included.");
                int smallestDiff = Math.abs(Integer.valueOf(practical[0]) - Integer.valueOf(theory[0]));
                for (int i = 1; i < noOfDays; i++) {
                    int currentDiff = Math.abs(Integer.valueOf(practical[i]) - Integer.valueOf(theory[i]));
                    if (currentDiff < smallestDiff) {
                        smallestDiff = currentDiff;
                    }
                }
                sum -= smallestDiff;
            }
            File outFile = new File("prepare.out");
            Prepare.writeToFile(outFile, sum.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
