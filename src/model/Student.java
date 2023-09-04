package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Student class represents a student with their name, surname, and college.
 */
public class Student implements Serializable {

    private String name;
    private String surname;

    /**
     * Constructs a Student object with the specified name, surname, and college.
     *
     * @param name    The student's name.
     * @param surname The student's surname.
     */
    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the student.
     *
     * @return The student's surname.
     */
    public String getSurname() {
        return surname;
    }


    @Override
    /**
     * Returns a string representation of the Student object.
     *
     * @return A string representation of the student.
     */
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    /**
     * Sets the name of the student.
     *
     * @param name The student's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the surname of the student.
     *
     * @param surname The student's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }



    @Override
    /**
     * Checks if the Student object is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname);
    }

    @Override
    /**
     * Returns the hash code value for the Student object.
     *
     * @return The hash code value.
     */
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
