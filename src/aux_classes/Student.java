package aux_classes;

import java.util.ArrayList;

public class Student {

    private String ime;
    private String prezime;
    private String fakultet;
    private SubjectManager subjectManager;

    public Student(String ime, String prezime, String fakultet){
        this.ime = ime;
        this.prezime = prezime;
        this.fakultet = fakultet;
        this.subjectManager = new SubjectManager();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getFakultet() {
        return fakultet;
    }

    public SubjectManager getSubjectManager() {
        return subjectManager;
    }


    @Override
    public String toString() {
        return "Student{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", fakultet='" + fakultet + '\'' +
                '}';
    }


}
