import java.util.ArrayList;
public class Library {
    private ArrayList<Book> list = new ArrayList<>();

    public void addBook(Book b){
        list.add(b);


    }
    public void removeBook(Book b){

        list.remove(b);
    }
    public ArrayList<Book> searchBook(String a, String b, String c, String d ) {
        ArrayList<Book> search = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getName().equals(a) || a.equals("")) && (list.get(i).getAutor() == b || b.equals(""))
                    && (list.get(i).getGenre() == c || c.equals("")) && (list.get(i).getCode() == d || d.equals(""))) {
                search.add(list.get(i));

            }
        }
        return search;
    }
}