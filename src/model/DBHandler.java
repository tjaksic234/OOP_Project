package model;

import controller.Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DBHandler {

    private static final String FILE_PATH = "Projekt/src/Storage/data.bin";
    private Controller controller;

    public void saveData() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(controller);

            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while saving data.");
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
