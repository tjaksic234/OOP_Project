package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DBHandler {
    private static final String FILE_PATH = "data.txt";

    public void saveDataToFile(HashMap<Student, Double> data) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            for (Map.Entry<Student, Double> entry : data.entrySet()) {
                Student student = entry.getKey();
                Double averageGrade = entry.getValue();
                String line = student.getName() + "," + student.getSurname() + "," +
                        student.getCollege() + "," + averageGrade;
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Student, Double> readDataFromFile() {
        HashMap<Student, Double> data = new HashMap<>();

        try (FileReader fileReader = new FileReader(FILE_PATH, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String surname = parts[1];
                    String college = parts[2];
                    Double averageGrade = Double.parseDouble(parts[3]);

                    Student student = new Student(name, surname, college);
                    data.put(student, averageGrade);
                }
            }

            System.out.println("Data loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
