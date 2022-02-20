import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class ChessTest {

    @Test
    public void TestChess() {
        Chess forErrors = new Chess();

        Chess e1 = new Chess();

        Chess.addFigure(new Chess.point(1, 4, "White", "pawn"), e1);
        assertEquals(false, Chess.equals(e1, forErrors));


        Chess.deleteFigure(1, 4, e1);
        assertEquals(true, Chess.equals(e1, forErrors));


        Chess.addFigure(new Chess.point(1, 4, "White", "pawn"), forErrors);
        Chess.addFigure(new Chess.point(1, 3, "White", "pawn"), e1);
        Chess.changePlaceOfFigure(1, 3, 1, 4, e1);
        Chess.viewDesk(forErrors.desk);
        Chess.viewDesk(e1.desk);
        assertEquals(true, Chess.equals(e1, forErrors));

    }


}
