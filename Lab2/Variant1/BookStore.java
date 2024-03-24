import java.io.Serializable;
import java.util.*;

public class BookStore implements Serializable{
    private String nameOfStore;
    private ArrayList<Book> books = new ArrayList<Book>();
    public BookStore (String nameOfStore){
        this.nameOfStore = nameOfStore;
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public String getNameOfStore(){
        return nameOfStore;
    }
    public void setNameOfStore(String nameOfStore){
        this.nameOfStore = nameOfStore;
    }
    public ArrayList<Book> getBooks(){
        return books;
    }
    public String toString(){
        return "\n  Name of the store: " + nameOfStore + "\n   Book:" + books;
    }
}