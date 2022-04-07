import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FieldTest {

    @Test
    public void testAddNought() throws Exception {
        Field FieldA = new Field();
        Field FieldB = new Field(3);
        assertThrows(Exception.class, () -> FieldA.addNought(8, 5),
                "Ошибка: В данном поле не существует такого ряда и/или столбца.");
        FieldA.addNought(2,3);
        assertThrows(Exception.class, () -> FieldA.addNought(2, 3),
                "Ошибка: Ячейка уже занята.");
        FieldB.addNought(2, 3);
        assertEquals(FieldA, FieldB);
        FieldA.addNought(1,1);
        assertNotEquals(FieldA, FieldB);
    }

    @Test
    public void testAddCross() throws Exception {
        Field FieldA = new Field();
        assertThrows(Exception.class, () -> FieldA.addCross(8, 5),
                "Ошибка: В данном поле не существует такого ряда и/или столбца.");
        Field FieldB = new Field(3);
        FieldA.addCross(1,2);
        assertThrows(Exception.class, () -> FieldA.addCross(1, 2),
                "Ошибка: Ячейка уже занята.");
        FieldB.addCross(1, 2);
        assertEquals(FieldA, FieldB);
        FieldA.addCross(1,1);
        assertNotEquals(FieldA, FieldB);

    }

    @Test
    public void testClearCell() throws Exception {
        Field FieldA = new Field();
        assertThrows(Exception.class, () -> FieldA.clearCell(8, 5),
                "Ошибка: В данном поле не существует такого ряда и/или столбца.");
        Field FieldB = new Field(3);
        FieldA.addCross(1,2);
        FieldA.addNought(1, 3);
        FieldB.addCross(1, 2);
        FieldB.addNought(1, 3);
        FieldA.clearCell(1,2);
        assertNotEquals(FieldA, FieldB);
        FieldB.clearCell(1,2);
        assertEquals(FieldA, FieldB);
    }
    @Test
    public void testTheLongestNought() throws Exception {
        Field FieldA = new Field(4);
        FieldA.addCross(1,1);
        FieldA.addNought(1, 2);
        FieldA.addNought(1, 3);
        FieldA.addNought(1, 4);
        FieldA.addNought(2, 2);
        assertEquals(3, FieldA.theLongestNought());
    }
    @Test
    public void testTheLongestCross() throws Exception {
        Field FieldA = new Field(4);
        FieldA.addNought(1,1);
        FieldA.addNought(3,2);
        FieldA.addCross(2, 1);
        FieldA.addCross(2, 2);
        FieldA.addCross(1, 3);
        FieldA.addCross(3, 3);
        FieldA.addCross(4, 4);
        assertEquals(3, FieldA.theLongestCross());
    }
}
