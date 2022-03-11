import java.lang.String;
import java.util.Objects;
public class Book {
    private String name;
    private String autor;
    private String genre;
    private String code;

    public Book(String name,String autor,String genre,String code) {
        this.name = name;
        this.autor = autor;
        this.genre = genre;
        this.code = code;
    }
    public Book(){

    }
    public String toString(){ //перегруженный метод toString, ляя вывода полей объекта
        StringBuilder str = new StringBuilder();
        str.append(name + " ").append(autor + " ").append(autor + " ").append(code);
        return str.toString();
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getAutor(){
        return autor;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getGenre(){
        return genre;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(autor, book.autor) &&
                Objects.equals(genre, book.genre) && Objects.equals(code, book.code);

    }
    @Override
    public int hashCode() {
        return Objects.hash(name, autor, genre, code);
    }

}


