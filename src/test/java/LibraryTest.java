import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    @Test
    public void add() {
        Book newbook  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Library expected = new Library(newbook);
        Library fact = new Library();
        fact.addBook(newbook);
        assertEquals(expected, fact);

    }

    @Test
    public void remove() {
        Book newbook  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Library expected = new Library();
        Library fact = new Library(newbook);
        fact.removeBook(newbook);
        assertEquals(expected, fact);

    }
    @Test
    public void newName() {
        Book newbook  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Book expected = new Book("Повести Белкина", "Пушкин","Повесть","А14");
        newbook.setName("Повести Белкина");
        assertEquals(expected, newbook);

    }
    @Test
    public void newAutor() {
        Book newbook  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Book expected = new Book("Капитанская дочка", "Толстой","Повесть","А14");
        newbook.setAutor("Толстой");
        assertEquals(expected, newbook);

    }
    @Test
    public void newGenre() {
        Book newbook  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Book expected = new Book("Капитанская дочка", "Пушкин","Роман","А14");
        newbook.setGenre("Роман");
        assertEquals(expected, newbook);

    }
    @Test
    public void newCode() {
        Book newbook  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Book expected = new Book("Капитанская дочка", "Пушкин","Повесть","А15");
        newbook.setCode("А15");
        assertEquals(expected, newbook);
    }

    @Test
    public void search() {
        Library l1 = new Library();
        Library l2 = new Library();
        Book newbook1  = new Book("Капитанская дочка", "Пушкин","Повесть","А14");
        Book newbook2  = new Book("Капитанская дочка", "Онегин","Повесть","А14");
        Book newbook3  = new Book("Капитанская дочка", "Толстой","Роман","А14");
        Library expected = new Library(newbook3);
        l1.addBook(newbook1);
        l1.addBook(newbook2);
        l1.addBook(newbook3);
        l2 = l1.searchBook("Толстой","Роман");
        assertEquals(expected, l2);


    }


}
