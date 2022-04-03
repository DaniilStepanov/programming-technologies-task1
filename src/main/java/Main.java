/**
Вариант 15 -- поле для крестиков-ноликов [Java]
Хранит квадратное поле для игры в крестики-нолики заданного в конструкторе
размера.
Методы: добавление крестика / нолика в заданную клетку, очистка заданной клетки,
поиск самой длинной последовательности крестиков (непрерывной линии по
горизонтали / вертикали / диагонали), то же для ноликов.
*/

public final class Main {
    public static void main(String[] args) {
        Field a = new Field(5);
        a.show();
        a.addNought(3, 3);
        a.show();
        a.clearCell(3, 3);
        a.show();
        //a.addCross(3, 3);
        a.addCross(2, 2);
        a.addCross(1, 1);
        a.addCross(3, 1);
        a.addCross(2, 1);
        a.addCross(5, 5);
        a.addCross(3, 3);
        a.addCross(4, 4);
        a.show();
        a.theLongestCross();
    }
}
