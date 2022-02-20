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
        String color;
        String figure;

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
                            if (in.typeOfWhiteFigures[5] < 8) { // считаем белые пешки
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
                            if (in.typeOfBlackFigures[5] < 8) {  // считаем черные пешки
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


    public static void viewDesk(@NotNull point[][] in) { // :)
        final String ANSI_RESET = "\u001B[0m"; // белые будут белыми
        final String ANSI_RED = "\u001B[31m";  // а черные будут красными
        String column1Format = "%-4.4s";
        String formatInfo = column1Format;
        System.out.println("|-------------------------------------------------------------------------------------------|");
        for (point[] element1 : in) {
            System.out.print("|");
            for (point element2 : element1) {
                String str = String.format(formatInfo, element2.figure);
                if (element2.color.equalsIgnoreCase("black")) {
                    System.out.print(ANSI_RED + str + ANSI_RESET + "\t|\t");
                } else System.out.print(str + "\t|\t");

            }
            System.out.println();
            System.out.println("|-------------------------------------------------------------------------------------------|");
        }
    }


    point[][] desk = createDesk();
    int[] typeOfWhiteFigures = {1, 0, 0, 0, 0, 0}; // считаем все виды фигур, хотя можно было бы проверять только пешки
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
                    desk.typeOfWhiteFigures[i]--; // удаляем из счетчика
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


    private static void wrongCoordinate(int x, int y) {
        if (x < 0 || x > sizeX) throw new IllegalArgumentException("Wrong X coordinate");
        if (y < 0 || y > sizeX) throw new IllegalArgumentException("Wrong Y coordinate");
    }

    public static void changePlaceOfFigure(int oldX, int oldY, int newX, int newY, Chess desk) {
        wrongCoordinate(oldX, oldY);
        wrongCoordinate(newX, newY);
        boolean wrong = false;
        /*
        if (!desk.desk[oldX][oldY].figure.equalsIgnoreCase("0")) { // старая фигура не теряется!
            for (int i = 0; i < 6; i++) {
                if (desk.desk[oldX][oldY].figure.equalsIgnoreCase(figures[i])) {
                    if (desk.desk[oldX][oldY].color.equalsIgnoreCase( "white")) {
                        desk.typeOfWhiteFigures[i]--;
                    } else desk.typeOfBlackFigures[i]--;
                    wrong = true;
                    break;
                }
            }
            if (!wrong) throw new IllegalArgumentException("Try in another coordinate"); // вряд-ли дойдет до такого
        } else throw new IllegalArgumentException("Try in another coordinate");
*/


        wrong = false;
        if (!desk.desk[newX][newY].figure.equalsIgnoreCase("0")) { // если новая клетка занята, новую фигуру выкидываем
            for (int i = 0; i < 6; i++) {
                if (desk.desk[newX][newY].figure.equalsIgnoreCase(figures[i])) { // а есть ли такая фигура... считаем ее, чтобы удалить из счетчика по фигурам
                    if (!desk.desk[newX][newY].color.equalsIgnoreCase(desk.desk[oldX][oldY].color)) { // своих бить не будем
                        if (desk.desk[newX][newY].color.equalsIgnoreCase("white")) {
                            desk.typeOfWhiteFigures[i]--;
                        } else desk.typeOfBlackFigures[i]--;
                        wrong = true;
                        break;
                    }
                    throw new IllegalArgumentException("You cant hit your figure");
                }
            }
            if (!wrong) throw new IllegalArgumentException("Try something another"); // очень вряд-ли сюда попадет
        }


        desk.desk[newX][newY].color = desk.desk[oldX][oldY].color;
        desk.desk[newX][newY].figure = desk.desk[oldX][oldY].figure;
        desk.desk[oldX][oldY].color = "not";
        desk.desk[oldX][oldY].figure = "0";
        // desk.desk[oldX][oldY] = new point(oldX, oldY, "not", "0");
    }


    public static boolean equals(Chess a, Chess b) {  // сравнение
        for (int i = 0; i < sizeX; i++) {
            for (int l = 0; l < sizeY; l++) {
                if (!a.desk[i][l].figure.equals(b.desk[i][l].figure)) return false;
                if (!a.desk[i][l].color.equals(b.desk[i][l].color)) return false;
                if (a.desk[i][l].x != b.desk[i][l].x) return false;
                if (a.desk[i][l].y != b.desk[i][l].y) return false;

            }
        }

        return true;
    }


    public static void addAllColorPawns(String color, Chess desk) {
        if (color.equalsIgnoreCase("white")) {
            for (int l = 0; l < sizeY; l++) {
                desk.desk[l][6].color = "white";
                desk.desk[l][6].figure = "pawn";
            }
        } else if (color.equalsIgnoreCase("black")) {
            for (int i = 0; i < sizeY; i++) {
                desk.desk[i][1].color = "black";
                desk.desk[i][1].figure = "pawn";
            }
        } else throw new IllegalArgumentException("Wrong information about color");


    }

}
