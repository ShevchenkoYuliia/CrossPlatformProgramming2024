
public class Human{
    transient private String name;
    transient private String surname;
    public Human(){

    }
    public Human(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String toString(){
        return "Author name and surname - " + name + " " + surname;
    }

}