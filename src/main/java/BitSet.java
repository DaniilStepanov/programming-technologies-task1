
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;

public class BitSet<T> {

    public int size;
    private ArrayList<T> arr = new ArrayList<T>();

    public BitSet(int size) {
        this.size = size;
    }

    public void addElement(T el) {
        if (!arr.contains(el) && arr.size() < size) {
            arr.add(el);
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void addElement(T[] el) {
        for (int i = 0; i < el.length; i++) {
            if (!arr.contains(el[i]) && arr.size() < size) {
                arr.add(el[i]);
            }else{
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void delete(T el) {
        arr.remove(el);
    }

    public void delete(T[] el) {
        for (int i = 0; i < el.length; i++) {
            arr.remove(el[i]);
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i) + " ";
        }
        return str.trim();
    }

    public T get(int index) {
        return arr.get(index);
    }

    public boolean contain(T el) {
        return arr.contains(el);
    }

    public BitSet<T> intersection(BitSet<T> other) {
        BitSet<T> intersections = new BitSet<T>(this.arr.size() + other.arr.size());

        for (int i = 0; i < size; i++) {
            if (other.arr.contains(this.get(i))) {
                intersections.arr.add(this.get(i));
            }
        }
        return intersections;
    }

    public BitSet<T> association(BitSet<T> other) {
        BitSet<T> associations = new BitSet<T>(this.arr.size() + other.arr.size());
        associations.arr = this.arr;
        for (int i = 0; i < other.arr.size(); i++) {
            associations.addElement(other.get(i));
        }
        return associations;
    }

    public BitSet<T> addition(BitSet<T> other) {
        BitSet<T> additions = new BitSet<T>(this.arr.size() + other.arr.size());
        for (int i = 0; i < size; i++) {
            if (!other.arr.contains(this.get(i))) {
                additions.arr.add(this.get(i));
            }
        }
        return additions;
    }


}
