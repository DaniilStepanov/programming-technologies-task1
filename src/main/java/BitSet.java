
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import java.util.Objects;

public class BitSet<T> {

    private int size;
    private ArrayList<T> arr = new ArrayList<T>();

    public BitSet(int size) {
        setSize(size);
    }

    public BitSet(int size, T a, T b, T c) {
        setSize(size);
        arr.add(a);
        arr.add(b);
        arr.add(c);
    }

    public BitSet(int size, T a) {
        setSize(size);
        arr.add(a);
    }

    public BitSet(int size, T a, T b, T c, T d, T e) {
        setSize(size);
        arr.add(a);
        arr.add(b);
        arr.add(c);
        arr.add(d);
        arr.add(e);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void addElement(T el) {
        if (!arr.contains(el) && arr.size() < size) {
            arr.add(el);
        } else if (arr.size() >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addElement(T[] el) {
        for (int i = 0; i < el.length; i++) {
            if (!arr.contains(el[i]) && arr.size() < size) {
                arr.add(el[i]);
            } else if (arr.size() >= size) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void delete(T el) {
        arr.remove(el);
        setSize(this.size - 1);
    }

    public void delete(T[] el) {
        for (int i = 0; i < el.length; i++) {
            arr.remove(el[i]);
        }
        setSize(this.size - el.length);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            str.append(arr.get(i) + " ");
        }
        return str.toString().trim();
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
        intersections.setSize(intersections.arr.size());
        return intersections;
    }

    public BitSet<T> association(BitSet<T> other) {
        BitSet<T> associations = new BitSet<T>(this.arr.size() + other.arr.size());
        associations.arr = this.arr;
        for (int i = 0; i < other.arr.size(); i++) {
            associations.addElement(other.get(i));
        }
        associations.setSize(associations.arr.size());
        return associations;
    }

    public BitSet<T> addition(BitSet<T> other) {
        BitSet<T> additions = new BitSet<T>(this.arr.size() + other.arr.size());
        for (int i = 0; i < size; i++) {
            if (!other.arr.contains(this.get(i))) {
                additions.arr.add(this.get(i));
            }
        }
        additions.setSize(additions.arr.size());
        return additions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet<?> bitSet = (BitSet<?>) o;
        return size == bitSet.size && Objects.equals(arr, bitSet.arr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, arr);
    }
}
