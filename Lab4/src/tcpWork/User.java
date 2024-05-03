package tcpWork;
import java.awt.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private static final DateFormat dateFormatter =
            new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat dateParser = dateFormatter;
    private String name, surName, patronymic, gender;
    private Date birthday;
    public User(){
        name = "Ann";
        surName = "Smith";
        gender = "female";
        try {
            birthday = dateParser.parse("14.10.2000");
        } catch (ParseException e) {
            System.out.println("Error: " + e);
        }
    }
    public User(String name, String surName, String patronymic, String gender, String birthday) {
        this.name = name;
        this.surName = surName;
        this.patronymic = patronymic;
        this.gender = gender;
        try {
            this.birthday = dateParser.parse(birthday);
        } catch (ParseException ex) {
            System.out.println("Error: " + ex);
        }
    }
    public String getName(){
        return name;
    }
    public String getSurName(){
        return surName;
    }
    public String getPatronymic(){
        return patronymic;
    }
    public String getGender() {
        return gender;
    }
    public Date getBirthday(){
        return birthday;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        return name + " " + surName + " " + patronymic + " " + gender +
                " " + dateFormatter.format(birthday);
    }

}
