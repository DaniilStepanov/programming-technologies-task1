import org.jetbrains.annotations.NotNull;

public class Chess {

    private static final int sizeX = 8;
    private static final int sizeY = 8;
    public static final String[] figures = {"king", "queen", "castle", "bishop", "knight", "pawn"};

    // king == король
    // queen == королева
    // castle == ладья
    // bishop == слон
    // knight == конь
    // pawn == пешка

    static class point {
        int x;
        int y;
        final String color;
        final String figure;

        //colors: white == белый, black == черный, not == пустая клетка

        point(int x, int y, String color, String figure) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.figure = figure;
        }

        private void wrongPoint(@NotNull point newPoint, Chess in) {

            if (!(this.x >= 0 && this.x < sizeX) || !(this.y >= 0 && this.y < sizeY)) {
                throw new IllegalArgumentException("Wrong X||Y format");
            }

            int counter = 0;
            boolean wrong = false;
            if (newPoint.color.equalsIgnoreCase("white")) {
                for (String element : figures) {
                    if (newPoint.figure.equalsIgnoreCase(element)) {
                        if (!newPoint.figure.equalsIgnoreCase("king")) {
                            if (in.typeOfWhiteFigures[5] < 8) {
                                in.typeOfWhiteFigures[counter]++;
                                wrong = true;
                            }
                            break;
                        }
                    }
                    counter++;
                }
                if (!wrong) throw new IllegalArgumentException("Wrong figure type");
            } else if (newPoint.color.equalsIgnoreCase("black")) {
                for (String element : figures) {
                    if (newPoint.figure.equalsIgnoreCase(element)) {
                        if (!newPoint.figure.equalsIgnoreCase("king")) {
                            if (in.typeOfBlackFigures[5] < 8) {
                                in.typeOfBlackFigures[counter]++;
                                wrong = true;
                            }
                            break;
                        }
                    }
                    counter++;
                }
                if (!wrong) throw new IllegalArgumentException("Wrong figure type");
            } else {
                throw new IllegalArgumentException("Wrong color type");
            }
        }


    }


    @NotNull
    public static point[][] createDesk() {
        point[][] desk = new point[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int k = 0; k < sizeY; k++) {
                desk[i][k] = new point(i, k, "not", "0");
            }
        }
        desk[0][4] = new point(0, 4, "black", "king");
        desk[7][3] = new point(7, 3, "white", "king");
        return desk;
    }

    //static point[][] desk = new point[sizeX][sizeY];

    public static void viewDesk(@NotNull point[][] in) {
        String column1Format = "%-4.4s";
        String formatInfo = column1Format + "\t|\t";
        System.out.println("|-------------------------------------------------------------------------------------------|");
        for (point[] element1 : in) {
            System.out.print("|");
            for (point element2 : element1) {
                //System.out.print(element2);
                System.out.format(formatInfo, element2.figure);
            }
            System.out.println();
            System.out.println("|-------------------------------------------------------------------------------------------|");
        }
    }

    point[][] desk = createDesk();
    int[] typeOfWhiteFigures = {1, 0, 0, 0, 0, 0};
    int[] typeOfBlackFigures = {1, 0, 0, 0, 0, 0};

    public Chess() {
        this.desk = desk;
        this.typeOfWhiteFigures = typeOfWhiteFigures;
        this.typeOfBlackFigures = typeOfBlackFigures;
    }


    public static void addFigure(@NotNull point newPoint, Chess in) {
        newPoint.wrongPoint(newPoint, in);
        in.desk[newPoint.x][newPoint.y] = newPoint;
    }

    public static void deleteFigure(int deleteX, int deleteY, Chess desk) {
        if ((deleteX < 0 || deleteX > 7) || ((deleteY < 0 || deleteY > 7)))
            throw new IllegalArgumentException("Wrong X||Y format");

        for (int i = 0; i < 6; i++) {
            if (figures[i].equalsIgnoreCase(desk.desk[deleteX][deleteY].figure)) {
                if (desk.desk[deleteX][deleteY].color.equalsIgnoreCase("white") && desk.typeOfWhiteFigures[i] != 0) {
                    desk.typeOfWhiteFigures[i]--;
                } else if (desk.desk[deleteX][deleteY].color.equalsIgnoreCase("black") && desk.typeOfBlackFigures[i] != 0) {
                    desk.typeOfBlackFigures[i]--;
                } else {
                    throw new IllegalArgumentException("Wrong deleted figure format");
                }
            }
        }
        if (desk.desk[deleteX][deleteY].figure.equalsIgnoreCase("king"))
            throw new IllegalArgumentException("Wrong deleted figure format");

        desk.desk[deleteX][deleteY] = new point(deleteX, deleteY, "not", "0");
    }

    public static int w2() {
        System.out.println("____________");
        return 1;
    }


}
