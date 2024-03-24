import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Serializable{
    private String title;
    private ArrayList<Author> authors = new ArrayList<Author>();
    private int year;
    private int number;
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
}