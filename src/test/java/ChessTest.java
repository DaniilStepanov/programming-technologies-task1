import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class ChessTest {

    @Test
    public void TestChess() {
        Chess forErrors = new Chess();

        Chess e1 = new Chess();

        Chess.addFigure(new Chess.point(1, 4, "White", "pawn"), e1);
        assertFalse(Chess.equals(e1, forErrors));


        Chess.deleteFigure(1, 4, e1);
        assertTrue(Chess.equals(e1, forErrors));


        Chess.addFigure(new Chess.point(1, 4, "White", "pawn"), forErrors);
        Chess.addFigure(new Chess.point(1, 3, "White", "pawn"), e1);
        Chess.changePlaceOfFigure(1, 3, 1, 4, e1);
        assertTrue(Chess.equals(e1, forErrors));


        Chess.addAllColorPawns("white", e1);
        Chess.addAllColorPawns("black", e1);
        //Chess.viewDesk(e1.desk);

        Chess.addAllFigures(e1);
        Chess.addAllFigures(forErrors);
        Chess.changePlaceOfFigure(1, 3, 4, 5, e1);
        Chess.viewDesk(e1.desk);
        Chess.changePlaceOfFigure(4, 5, 1, 3, e1);
        Chess.viewDesk(forErrors.desk); // 7   3
        assertTrue(Chess.equals(e1, forErrors));


    }


}
