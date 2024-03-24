import java.io.*;
public class LibraryDriver{
    public static void main(String[] args) {
        Author author1 = new Author("Ray", "Bradbury");
        Author author2 = new Author("Stephen", "King");
        Author author3 = new Author("Agatha", "Cristie");
        Author author4 = new Author("John", "Tolkien");
        Book book1 = new Book("Fahrenheit 451", 1953, 1, author1);
        Book book2 = new Book("It", 1986, 2, author2);
        Book book3 = new Book("The Mysterious Affair at Styles", 1920, 3, author3);
        Book book4 = new Book("The A.B.C. Murders", 1936, 4, author3);
        Book book5 = new Book("The Fellowship of the Ring", 1954, 5, author4);
        Book book6 = new Book("The Shining", 1977, 6, author2);
        BookStore bookStore1 = new BookStore("Fantasy");
        BookStore bookStore2 = new BookStore("Horror");
        BookStore bookStore3 = new BookStore("Detective");
        Library library = new Library("Main Library");
        bookStore1.addBook(book1);
        bookStore1.addBook(book5);
        bookStore2.addBook(book2);
        bookStore2.addBook(book6);
        bookStore3.addBook(book3);
        bookStore3.addBook(book4);
        library.addStore(bookStore1);
        library.addStore(bookStore2);
        library.addStore(bookStore3);
        BookReader bookReader1 = new BookReader("Andrii", "Boyko", 1);
        BookReader bookReader2 = new BookReader("Mariia", "Shvets", 2);
        bookReader1.addBorrowedBooks(book1);
        bookReader1.addBorrowedBooks(book2);
        bookReader2.addBorrowedBooks(book5);
        library.addReader(bookReader1);
        library.addReader(bookReader2);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("library.dat"));
            out.writeObject(library);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("library.dat"));
            Library library2 = (Library) in.readObject();
            System.out.println(library2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
