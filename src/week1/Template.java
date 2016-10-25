//package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Template {

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
        List<String> numbers = Template.readAllLines(new File("template.in"));
        String[] layout = numbers.get(0).split(" ");
        int width = Integer.valueOf(layout[0]);
        int height = Integer.valueOf(layout[1]);
        String[] sequence = new String[width];
        Map<String, Coordinate> charMapping = new HashMap<>();
        for (int i = 1; i <= height; i++) {
            sequence = numbers.get(i).split("");
            for (int j = 0; j < sequence.length; j++) {
                charMapping.put(sequence[j], new Coordinate(i, j + 1));
            }
        }
        List<Integer> templateEndIndex = new ArrayList<>();
        for (int i = height + 2; i < numbers.size(); i++) {
            String msg = numbers.get(i);
            System.out.println("message is: " + msg + " index: " + i);
            if (msg.length() == 1 && msg.equals(" ")) {
                templateEndIndex.add(i - 1);
            }
        }
        System.out.println("\n\nTemplate end index is: " + templateEndIndex);
        Map<String, String> langInputMapping = new HashMap<>();
        langInputMapping.put(numbers.get(height + 2), readInputChars(numbers, height + 3, templateEndIndex.get(0)));
        langInputMapping.put(numbers.get(templateEndIndex.get(0) + 2),
            readInputChars(numbers, templateEndIndex.get(0) + 3, templateEndIndex.get(1)));
        langInputMapping.put(numbers.get(templateEndIndex.get(1) + 2), readInputChars(numbers, templateEndIndex.get(1) + 3,
            templateEndIndex.size() > 2 ? templateEndIndex.get(2) : numbers.size() - 1));

        int score = 0;
        int smallestSum = 0;
        List<String> lang = new ArrayList<String>();
        for (Entry<String, String> entry : langInputMapping.entrySet()) {
            String key = entry.getKey();
            String input = entry.getValue();
            score = calculateTotalSum(input, charMapping);
            // System.out.println(key + " " + score);
            if (smallestSum == 0) {
                smallestSum = score;
            }
            if (score < smallestSum) {
                smallestSum = score;
                lang.add(key);
            }
        }
        Template.writeToFile(new File("template.out"), lang.get(lang.size() - 1) + "\r\n" + score + "\r\n");

    }

    public static String readInputChars(List<String> source, int fromIndex, int toIndex) {
        System.out.println("Reading  from index: " + fromIndex + " toIndex: " + toIndex);
        StringBuilder input = new StringBuilder(source.get(fromIndex));
        for (int i = fromIndex + 1; i <= toIndex; i++) {
            input.append(source.get(i));
        }
        System.out.println("Returned string is: " + input);
        return input.toString();
    }

    public static int calculateTotalSum(String input, Map<String, Coordinate> mapping) {
        int sum = 0;
        String[] chars = input.split("");

        for (int i = 0; i < chars.length - 1; i++) {
            Coordinate c1 = mapping.get(chars[i]);
            Coordinate c2 = mapping.get(chars[i + 1]);
            sum += calculateDistance(c1, c2);
        }

        return sum;
    }

    public static int calculateDistance(Coordinate c1, Coordinate c2) {
        return Math.max(Math.abs(c1.getX() - c2.getX()), Math.abs(c1.getY() - c2.getY()));
    }
    public static class Coordinate {

        private int x;
        private int y;

        public Coordinate(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

    }

}
