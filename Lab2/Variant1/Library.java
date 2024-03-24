import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

public class Library implements Serializable{
    private String nameOfLibrary;
    private ArrayList<BookStore> bookStores = new ArrayList<>();
    private ArrayList<BookReader> bookReaders = new ArrayList<>();
    public Library(String nameOfLibrary){
        this.nameOfLibrary = nameOfLibrary;
    }
    public String getNameOfLibrary() {
        return nameOfLibrary;
    }

    public void setNameOfLibrary(String nameOfLibrary) {
        this.nameOfLibrary = nameOfLibrary;
    }
    public List<BookStore> getBookStores() {
        return bookStores;
    }

    public void addBookStore(BookStore bookStore) {
        bookStores.add(bookStore);
    }
    public void setBookStores(ArrayList<BookStore> bookStores) {
        this.bookStores = bookStores;
    }
    public void setBookReaders(ArrayList<BookReader> bookReaders) {
        this.bookReaders = bookReaders;
    }
    public ArrayList<BookReader> getBookReaders() {
        return bookReaders;
    }
    public void addReader(BookReader reader) {
        bookReaders.add(reader);
    }
    public void addStore(BookStore bookStore) {
        bookStores.add(bookStore);
    }
    public String toString() {
        return "\nLIBRARY: " + nameOfLibrary + ",\n BOOKSTORES:" + bookStores + ",\n BOOKREADERS:" + bookReaders;
    }
}