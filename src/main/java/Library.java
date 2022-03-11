import java.util.ArrayList;
import java.util.Objects;

public class Library {
    private ArrayList<Book> list = new ArrayList<>();
    public Library(Book b1, Book b2, Book b3){
        list.add(b1);
        list.add(b2);
        list.add(b3);

    }
    public Library(Book b1){
        list.add(b1);
    }

    public Library(){
    }

    public void addBook(Book b){
        list.add(b);
    }

    public void removeBook(Book b){
        list.remove(b);
    }



    public Library searchBookbyName(String bookname){
        Library l1 = new Library();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getName().contains(bookname)){
                l1.addBook(list.get(i));
            }
        }
        return l1;
    }

    public Library searchBookbyAutor(String bookautor){
        Library l2 = new Library();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getAutor().contains(bookautor)){
                l2.addBook(list.get(i));
            }
        }
        return l2;
    }

    public Library searchBookbyGenre(String bookgenre){
        Library l3 = new Library();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getGenre().contains(bookgenre)){
                l3.addBook(list.get(i));
            }
        }
        return l3;
    }

    public Library searchBookbyCode(String bookcode){
        Library l4 = new Library();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getCode().contains(bookcode)){
                l4.addBook(list.get(i));
            }
        }
        return l4;
    }

    @Override
    public boolean equals(Object o) { //чтобы сравнивать объекты у которых могут быть поля
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(list, library.list);
    }

    @Override
    public int hashCode() { //даже у одного и того же объекта при каждом запуске программы хэш код будет разный
        return Objects.hash(list);
    }
}