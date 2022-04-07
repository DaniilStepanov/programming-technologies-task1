import java.util.Arrays;
import java.util.Objects;

public final class Field {
    public final int size; // длина стороны поля
    private final char[][] view;
    private int theLongestCross;
    private int theLongestNought;

    public Field(int size) {   // конструктор
        this.size = size;
        view = new char[size][size];
        for (char[] row : view) {
            java.util.Arrays.fill(row, ' ');
        }
    }

    public Field() {   // конструктор
        this.size = 3;
        view = new char[size][size];
        for (char[] row : view) java.util.Arrays.fill(row, ' ');
    }

    public void show() {
        System.out.println("Состояние поля:");
        for (char[] i : view) {
            for (char j : i) System.out.print(j + " ");
            System.out.println();
        }

    }

    public void addNought(int row, int column) throws Exception {
        if (row < 1 || row > size || column < 1 || column > size) {
            throw new Exception("Ошибка: В данном поле не существует такого ряда и/или столбца.");
        } else if (view[row - 1][column - 1] != ' ') {
            throw new Exception("Ошибка: Ячейка уже занята.");
        }
        view[row - 1][column - 1] = 'O';
    }

    public void addCross(int row, int column) throws Exception {
        if (row < 1 || row > size || column < 1 || column > size) {
            throw new Exception("Ошибка: В данном поле не существует такого ряда и/или столбца.");
        } else if (view[row - 1][column - 1] != ' ') {
            throw new Exception("Ошибка: Ячейка уже занята.");
        }
        view[row - 1][column - 1] = 'X';
    }

    public void clearCell(int row, int column) throws Exception {
        if (row < 1 || row > size || column < 1 || column > size) {
            throw new Exception("Ошибка: В данном поле не существует такого ряда и/или столбца.");
        }
        view[row - 1][column - 1] = ' ';
    }

    public int theLongestCross() {
        int[] count = {0, 0, 0, 0};
        // проверка рядов и строк
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (view[i][j] == 'X') count[0]++;
                else {
                    if (count[0] > theLongestCross) theLongestCross = count[0];
                    count[0] = 0;
                }
                if (view[j][i] == 'X') count[1]++;
                else {
                    if (count[1] > theLongestCross) theLongestCross = count[1];
                    count[1] = 0;
                }
            }
            for (int k = 0; k <= 1; k++) {
                if (count[k] > theLongestCross) theLongestCross = count[k];
                count[k] = 0;
            }
        }
        // проверка диагоналей
        for (int i = 0; i < size; i++){
            for (int j = 0; j <= i; j++) {
                if (view[j][i - j] == 'X') count[0]++;
                else {
                    if (count[0] > theLongestCross) theLongestCross = count[0];
                    count[0] = 0;
                }
                if (view[size - 1 - j][size - 1 - i + j] == 'X') count[1]++;
                else {
                    if (count[1] > theLongestCross) theLongestCross = count[1];
                    count[1] = 0;
                }
                if (view[j][size - 1 - i + j] == 'X') count[2]++;
                else {
                    if (count[2] > theLongestCross) theLongestCross = count[2];
                    count[2] = 0;
                }
                if (view[size - 1 - j][i - j] == 'X') count[3]++;
                else {
                    if (count[3] > theLongestCross) theLongestCross = count[3];
                    count[3] = 0;
                }
            }
            for (int k = 0; k <= 3; k++) {
                if (count[k] > theLongestCross) theLongestCross = count[k];
                count[k] = 0;
            }
        }
        return theLongestCross;
    }
    public int theLongestNought() {
        int[] count = {0, 0, 0, 0};
        // проверка рядов и строк
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (view[row][column] == 'O') count[0]++;
                else {
                    if (count[0] > theLongestNought) theLongestNought = count[0];
                    count[0] = 0;
                }
                if (view[column][row] == 'O') count[1]++;
                else {
                    if (count[1] > theLongestNought) theLongestNought = count[1];
                    count[1] = 0;
                }
            }
            for (int k = 0; k <= 1; k++) {
                if (count[k] > theLongestNought) theLongestNought = count[k];
                count[k] = 0;
            }
        }
        // проверка диагоналей
        for (int i = 0; i < size; i++){
            for (int j = 0; j <= i; j++) {
                if (view[j][i - j] == 'O') count[0]++;
                else {
                    if (count[0] > theLongestNought) theLongestNought = count[0];
                    count[0] = 0;
                }
                if (view[size - 1 - j][size - 1 - i + j] == 'O') count[1]++;
                else {
                    if (count[1] > theLongestNought) theLongestNought = count[1];
                    count[1] = 0;
                }
                if (view[j][size - 1 - i + j] == 'O') count[2]++;
                else {
                    if (count[2] > theLongestNought) theLongestNought = count[2];
                    count[2] = 0;
                }
                if (view[size - 1 - j][i - j] == 'O') count[3]++;
                else {
                    if (count[3] > theLongestNought) theLongestNought = count[3];
                    count[3] = 0;
                }
            }
            for (int k = 0; k <= 3; k++) {
                if (count[k] > theLongestNought) theLongestNought = count[k];
                count[k] = 0;
            }
        }
        return theLongestNought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return size == field.size
                && Arrays.deepEquals(view, field.view);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, theLongestCross, theLongestNought);
        result = 31 * result + Arrays.deepHashCode(view);
        return result;
    }
}
