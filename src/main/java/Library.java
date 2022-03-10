import java.util.ArrayList;
import java.util.Objects;

public class Library {
    private final ArrayList<Book> list = new ArrayList<>();
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

    public Library searchBook(String... arr){
        Library mas = new Library();
        ArrayList<String> listArgs = new ArrayList<>();
        String str="";
        int count=0;
        for(String a : arr){
            listArgs.add(a);
        }
        for(int i=0;i<list.size();i++){
            count=0;
            str=list.get(i).getName()+" "+list.get(i).getAutor()+" "+list.get(i).getCode()+" "+list.get(i).getGenre();
            for(int j=0;j<listArgs.size();j++){
                if(str.contains(listArgs.get(j))){
                    count+=1;
                }
            }
            if(listArgs.size()==count){
                mas.addBook(list.get(i));
            }
        }
        return mas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(list, library.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}