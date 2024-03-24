import java.util.*;

public class BookReader extends Human {
    private int readerNumber;
    private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
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
}
