import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;

public class BookStore implements Externalizable{
    transient private String nameOfStore;
    transient private ArrayList<Book> books = new ArrayList<Book>();
    public BookStore(){

    }
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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(nameOfStore);
        out.writeInt(books.size());
        for (Externalizable book:
                books) {
            book.writeExternal(out);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nameOfStore = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
    }
}