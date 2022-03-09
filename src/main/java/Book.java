import java.lang.String;
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

}


