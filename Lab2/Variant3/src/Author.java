import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
public class Author extends Human implements Externalizable {
    public Author(){
    }
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
        return "name: " + getName() + ", surname: " + getSurname();
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getSurname());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName((String) in.readObject());
        setSurname((String) in.readObject());
    }
}
