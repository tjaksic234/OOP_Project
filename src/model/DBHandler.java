package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DBHandler {
    private static final String FILE_PATH = "data.bin";

    public void saveDataToFile(HashMap<Student, Double> data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(data);
            System.out.println("Successfully saved data to file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Student, Double> readDataFromFile() {
        HashMap<Student, Double> data = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            data = (HashMap<Student, Double>) objectInputStream.readObject();
            System.out.println("Successfully read data from file");

            // Print data to console for debugging
            for (Map.Entry<Student, Double> entry : data.entrySet()) {
                Student student = entry.getKey();
                Double averageGrade = entry.getValue();
                System.out.println("Name: " + student.getName());
                System.out.println("Surname: " + student.getSurname());
                System.out.println("College: " + student.getCollege());
                System.out.println("Average Grade: " + averageGrade);
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }


}
