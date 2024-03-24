import java.util.*;
import java.util.Scanner;
public class Main {
    public static void displayMenu() {
        System.out.println("1. Serialize library");
        System.out.println("2. Deserialize library");
        System.out.println("3. Exit");
    }
    public static void main(String[] args) {
        Author author1 = new Author("Ray", "Bradbury");
        Author author2 = new Author("Stephen", "King");
        Author author3 = new Author("Agatha", "Cristie");
        Author author4 = new Author("John", "Tolkien");
        Book book1 = new Book("Fahrenheit 451", 1953, 1, author1);
        Book book2 = new Book("It", 1986, 2, author2);
        Book book3 = new Book("The Mysterious Affair at Styles",  1920, 3, author3);
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
        bookReader1.addBorrowedBooks(book5);
        bookReader2.addBorrowedBooks(book2);
        library.addReader(bookReader1);
        library.addReader(bookReader2);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    LibraryDriver.serializeObject("library.dat", library);
                    break;
                case 2:
                    Library deserializedLibrary = (Library) LibraryDriver.deSerializeObject("library.dat");
                    if (deserializedLibrary != null) {
                        System.out.println("Deserialized Library:");
                        System.out.println(deserializedLibrary);
                    } else {
                        System.out.println("Failed to deserialize library.");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}
