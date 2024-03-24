import java.io.Serializable;
import java.util.*;
import java.io.*;

public class Library implements Serializable{
    private String nameOfLibrary;
    private  ArrayList<BookStore> bookStores = new ArrayList<>();
    private transient ArrayList<BookReader> bookReaders = new ArrayList<>();
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
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(bookReaders.size());
        for (BookReader bookReader :
                bookReaders) {
            out.writeObject(bookReader.getName());
            out.writeObject(bookReader.getSurname());
            out.writeInt(bookReader.getBorrowedBooks().size());
            for (Book book :
                    bookReader.getBorrowedBooks()) {
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
            out.writeInt(bookReader.getReaderNumber());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int countReaders = in.readInt();
        ArrayList<BookReader> readers = new ArrayList<>(countReaders);
        for (int i = 0; i < countReaders; i++) {
            String name = (String) in.readObject();
            String surname = (String) in.readObject();
            int countBooks = in.readInt();
            ArrayList<Book> ownedBooks = new ArrayList<Book>();
            for (int j = 0; j < countBooks; j++) {
                String title = (String) in.readObject();
                int year = in.readInt();
                int count = in.readInt();
                ArrayList<Author> authors = new ArrayList<Author>(count);
                for (int k = 0; k < count; k++) {
                    Author author = new Author((String) in.readObject(), (String) in.readObject());
                    authors.add(k, author);
                }
                int number = in.readInt();
                Book book = new Book(title, year, number, authors.toArray(new Author[0]));
                ownedBooks.add(book);
            }
            BookReader bookReader = new BookReader(name, surname, in.readInt());
            for (Book book :
                    ownedBooks) {
                bookReader.addBorrowedBooks(book);
            }
            readers.add(bookReader);
        }
        setBookReaders(readers);
    }
}
