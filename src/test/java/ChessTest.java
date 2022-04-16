import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessTest {

    @Test
    public void TestChess() {
        Chess forErrors = new Chess(7, 3, 0, 4);
        Chess e1 = new Chess(7, 3, 0, 4);

        e1.addFigure(1, 4, "White", "pawn");
        assertFalse(e1.equals(forErrors));

        e1.deleteFigure(1, 4);
        assertTrue(e1.equals(forErrors));

        forErrors.addFigure(1, 4, "White", "pawn");
        e1.addFigure(1, 3, "White", "pawn");
        e1.changePlaceOfFigure(1, 3, 1, 4);
        assertTrue(e1.equals(forErrors));

        e1.addAllColorPawns("white");
        e1.addAllColorPawns("black");

        e1.addAllFigures();
        forErrors.addAllFigures();

        e1.changePlaceOfFigure(1, 3, 4, 5);
        e1.changePlaceOfFigure(4, 5, 1, 3);
        e1.viewDesk();
        forErrors.viewDesk(); // 7   3
        assertTrue(e1.equals(forErrors));
    }


}
