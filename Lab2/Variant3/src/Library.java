import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.*;

public class Library implements Externalizable{
    transient private String nameOfLibrary;
    transient private ArrayList<BookStore> bookStores = new ArrayList<>();
    transient private ArrayList<BookReader> bookReaders = new ArrayList<>();
    public Library(){

    }
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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(nameOfLibrary);
        out.writeInt(bookStores.size());
        for (Externalizable bookStore :
                bookStores) {
            bookStore.writeExternal(out);
        }
        out.writeInt(bookReaders.size());
        for (Externalizable bookReader :
                bookReaders) {
            bookReader.writeExternal(out);
        }

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nameOfLibrary = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            BookStore bookStore = new BookStore();
            bookStore.readExternal(in);
            bookStores.add(bookStore);
        }
        count = in.readInt();
        for (int i = 0; i < count; i++) {
            BookReader bookReader = new BookReader();
            bookReader.readExternal(in);
            bookReaders.add(bookReader);
        }
    }
}