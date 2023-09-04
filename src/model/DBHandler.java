package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The DBHandler class is responsible for saving and reading data to/from a file.
 */
public class DBHandler {

    private static final String FILE_PATH = "Projekt/src/Storage/data.bin";

    /**
     * Saves the provided data to a file.
     *
     * @param data The data to be saved.
     */
    public void saveDataToFile(HashMap<Student, HashMap<String, Object>> data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(data);
            System.out.println("Successfully saved data to file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Student, HashMap<String, Object>> readDataFromFile() {
        HashMap<Student, HashMap<String, Object>> data = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            data = (HashMap<Student, HashMap<String, Object>>) objectInputStream.readObject();
            for (Map.Entry<Student, HashMap<String, Object>> entry : data.entrySet()) {
                Student student = entry.getKey();
                HashMap<String, Object> studentData = entry.getValue();

                System.out.println("Student: " + student.getName() + " " + student.getSurname());
                System.out.println("Data: " + studentData);
            }
            System.out.println("Successfully read data from file");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

}
