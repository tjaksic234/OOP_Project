package model;

public class Teacher {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String subject;

    public Teacher(String name, String surname, String password, String subject){
        this.name = name;
        this.surname = surname;
        this.username = name + surname + "@teacher.com";
        this.password = password;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

}