import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;

public class BookReader extends Human implements Externalizable {
    transient private int readerNumber;
    transient private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
    public BookReader(){

    }
    public BookReader(String name, String surname, int readerNumber){
        super(name, surname);
        this.readerNumber = readerNumber;
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

    public int getReaderNumber() {
        return readerNumber;
    }

    public void setReaderNumber(int readerNumber) {
        this.readerNumber = readerNumber;
    }
    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }
    public void addBorrowedBooks(Book borrowedBook) {
        borrowedBooks.add(borrowedBook);
    }

    public String toString() {
        return "\n  Reader number "+ readerNumber + ", name: " + getName() +
                ", surname: " + getSurname() + ", books: " + borrowedBooks ;
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getSurname());
        out.writeInt(borrowedBooks.size());
        for (Externalizable books :
                borrowedBooks) {
            books.writeExternal(out);
        }
        out.writeInt(readerNumber);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName((String) in.readObject());
        setSurname((String) in.readObject());
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Book book = new Book();
            book.readExternal(in);
            borrowedBooks.add(book);
        }
        readerNumber = in.readInt();
    }
}
