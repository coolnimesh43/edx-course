package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Do you know that the way you sit during a programming competition may have an impact on your results? In particular, location of chairs
 * related to tables and the computer can strongly inﬂuence the relationships in your team. </br>
 * </br>
 * The famous team of three, called Dream Team, is going to participate in a competition called NIRC. According to the regulations of this
 * competition, every team is given a single computer, which is located on a triangular table, and three chairs. </br>
 * </br>
 * Dream Team thinks that the most convenient location of participants is the one where each of the three participants sits at his/her own
 * side of the triangular table, and, what’s important, exactly at the middle of this side. Of course, chairs should be put the same
 * way. </br>
 * </br>
 * It is important that, during the competition, the participants sit not very far one from another. The Dream Team’s captain thinks that a
 * proper estimation of this factor is an average distance between all the pairs of these participants. </br>
 * </br>
 * In the case of the NIRC competition, one have to compute an average distance between the middle points of the sides of a triangular
 * table. Write a program which computes exactly this. Note that the distance is Euclidean – that is, the distance between (x1,y1) and
 * (x2,y2) is sqrt((x1 − x2)2 + (y1 − y2)2). </br>
 * </br>
 * Input The input ﬁle contains three positive integer numbers not exceeding 100 – the lengths of sides of the table. It is guaranteed that
 * such a table will have a non-zero area. </br>
 * </br>
 * Output Output the average distance between the middle points of the sides of the table, which was described in the input. Any answer,
 * which diﬀers from the correct one by not more than 10−6, will be accepted. </br>
 * </br>
 * Examples: chairs.in 3 4 5 </br>
 * chairs.out 2.00000000
 * 
 * @author nimesh
 */
public class Chair {

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
            InputStream inputStream = new FileInputStream(new File("chairs.in"));
            String[] numbers = Chair.readLineFromFile(inputStream).split(" ");
            /**
             * Mid point theorm since A',B',C' will bisect each side AB,BC,AC so if we connect the three point A',B',C' they will make a
             * triangle that eachside in it is half the main side, and compute the average of |A'B'|, |A'C'| and |B'C'|.
             */
            double sum = ((Double.valueOf(numbers[0]) / 2) + (Double.valueOf(numbers[1]) / 2) + (Double.valueOf(numbers[2]) / 2)) / 3;
            File outFile = new File("chairs.out");
            System.out.println("The avg distance between the sides is " + sum);
            Chair.writeToFile(outFile, String.valueOf(sum));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
