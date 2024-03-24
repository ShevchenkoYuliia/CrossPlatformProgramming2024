public class Author extends Human {
    public Author(String name, String surname){
        super(name, surname);
    }
    public String getName() {
        return super.getName();
    }
    public void setName(String name) {
        super.setName(name);
    }
    public String getSurname() {
        return super.getSurname();
    }

    public void setSurname(String surname) {
        super.setSurname(surname);
    }
    public String toString(){
        return "name: " + getName() + ",surname: " + getSurname();
    }
}
