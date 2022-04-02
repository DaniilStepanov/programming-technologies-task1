public final class Field {
    public final int size; // длина стороны поля
    private final char[][] view;

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

    public void addNought(int row, int column) {
        view[row - 1][column - 1] = 'O';
    }

    public void addCross(int row, int column) {
        view[row - 1][column - 1] = 'X';
    }

    public void clearCell(int row, int column) {
        view[row - 1][column - 1] = ' ';
    }

    public void theLongestCross() {
        int[] count = {0, 0, 0, 0};
        int max = -1;
        // проверка рядов и строк
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (view[row][column] == 'X') count[0]++;
                else {
                    if (count[0] > max) max = count[0];
                    count[0] = 0;
                }
                if (view[column][row] == 'X') count[1]++;
                else {
                    if (count[1] > max) max = count[1];
                    count[1] = 0;
                }
            }
            for (int k = 0; k <= 1; k++) {
                if (count[k] > max) max = count[k];
                count[k] = 0;
            }
        }
        // проверка диагоналей
        for (int i = 0; i < size; i++){
            for (int j = i; j >= 0 ; j--) {
                for (int l = 0; l <= i; l++) {
                    if (view[l][j] == 'X') count[0]++;
                    else {
                        if (count[0] > max) max = count[0];
                        count[0] = 0;
                    }
                    if (view[j][l] == 'X') count[1]++;
                    else {
                        if (count[1] > max) max = count[1];
                        count[1] = 0;
                    }
                    if (view[l][size - j - 1] == 'X') count[2]++;
                    else {
                        if (count[2] > max) max = count[2];
                        count[2] = 0;
                    }
                    if (view[j][size - l - 1] == 'X') count[3]++;
                    else {
                        if (count[3] > max) max = count[3];
                        count[3] = 0;
                }

                }
            }
            for (int k = 0; k <= 3; k++) {
                if (count[k] > max) max = count[k];
            }
        }
        System.out.println("Самая длинная последовательность крестиков: " + max);
    }
}
