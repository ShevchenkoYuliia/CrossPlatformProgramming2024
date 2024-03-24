import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Externalizable{
    transient private String title;
    transient private ArrayList<Author> authors = new ArrayList<Author>();
    transient private int year;
    transient private int number;
    public Book(){

    }
    public Book(String title, int year, int number, Author ... authors){
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
        this.year = year;
        this.number = number;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public List<Author> getAuthors(){
        return authors;
    }
    public void addAuthors(Author author) {
        authors.add(author);
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public String toString(){
        return "\n    "+ number  + ". title : " + title + ", authors: " + authors + ", year: " + year ;
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(authors.size());
        for (Externalizable author :
                authors) {
            author.writeExternal(out);
        }
        out.writeInt(year);
        out.writeInt(number);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Author author = new Author();
            author.readExternal(in);
            authors.add(author);
        }
        year = in.readInt();
        number = in.readInt();
    }
}
