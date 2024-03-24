import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class BookStore implements Serializable{
    private String nameOfStore;
    private transient ArrayList<Book> books = new ArrayList<Book>();
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
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book book : books) {
            out.writeObject(book.getTitle());
            out.writeInt(book.getYear());
            out.writeInt(book.getAuthors().size());
            for (Author author : book.getAuthors()) {
                out.writeObject(author.getName());
                out.writeObject(author.getSurname());
            }
            out.writeInt(book.getNumber());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        books = new ArrayList<Book>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String title = (String) in.readObject();
            int year = in.readInt();
            int count = in.readInt();
            ArrayList<Author> authors = new ArrayList<Author>(count);
            for (int j = 0; j < count; j++) {
                Author author = new Author((String) in.readObject(), (String) in.readObject());
                authors.add(author);
            }
            int number = in.readInt();
            Book book = new Book(title, year, number, authors.toArray(new Author[0]));
            books.add(book);
        }
    }/*private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book book :
                books) {
            out.writeObject(book.getTitle());
            out.writeInt(book.getYear());
            out.writeInt(book.getAuthors().size());
            for (Author author :
                    book.getAuthors()) {
                out.writeObject(author.getName());
                out.writeObject(author.getSurname());
            }
            out.writeInt(book.getNumber());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        books = new ArrayList<Book>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String title = (String) in.readObject();
            int year = in.readInt();
            int count = in.readInt();
            ArrayList<Author> authors = new ArrayList<Author>(count);
            for (int j = 0; j < count; j++) {
                Author author = new Author((String) in.readObject(), (String) in.readObject());
                authors.add(j, author);
            }
            int number = in.readInt();
            Book book = new Book(title, year, number, new Author[0]);
            books.add(book);
        }
    }*/
}