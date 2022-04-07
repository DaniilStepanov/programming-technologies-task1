import org.jetbrains.annotations.NotNull;

import java.sql.Array;

public class Chess {
    final int sizeX = 8;
    final int sizeY = 8;
    String[] figures = {"king", "queen", "castle", "bishop", "knight", "pawn"};

    // king == король
    // queen == королева
    // castle == ладья
    // bishop == слон
    // knight == конь
    // pawn == пешка

    int[] typeOfWhiteFigures = {1, 0, 0, 0, 0, 0}; // считаем все виды фигур, хотя можно было бы проверять только пешки
    int[] typeOfBlackFigures = {1, 0, 0, 0, 0, 0};
    int[] blackKing = new int[2]; // храним положение королей, чтобы их было проще огородить дург от друга
    int[] whiteKing = new int[2];
    PointForDesk[][] desk = new PointForDesk[sizeX][sizeY];

    Chess(int wX, int wY, int bX, int bY) {
        for (int i = 0; i < sizeX; i++) {
            for (int k = 0; k < sizeY; k++) {
                desk[i][k] = new PointForDesk(i, k, "not", " ");
            }
        }
        this.whiteKing = new int[]{wX, wY};
        this.blackKing = new int[]{bX, bY};
    }

    public void viewDesk() { // :)
        final String ANSI_RESET = "\u001B[0m"; // белые будут белыми
        final String ANSI_RED = "\u001B[31m";  // а черные будут красными
        String formatInfo = "%-4.4s";
        System.out.println("|-------------------------------------------------------------------------------------------|");
        for (PointForDesk[] element1 : desk) {
            System.out.print("|");
            for (PointForDesk element2 : element1) {
                String str = String.format(formatInfo, element2.figure);
                if (element2.color.equalsIgnoreCase("black")) {
                    System.out.print(ANSI_RED + str + ANSI_RESET + "\t|\t");
                } else System.out.print(str + "\t|\t");

            }
            System.out.println();
            System.out.println("|-------------------------------------------------------------------------------------------|");
        }
    }



    public void addFigure(int x, int y, String color, String figure) {
        desk[x][y] = new PointForDesk(x,y,color, figure);
        for (int i = 0; i<figures.length;i++){
            if (figures[i].equals(figure)){
                if (color.equalsIgnoreCase("white")) typeOfWhiteFigures[i]++;
                else  if (color.equalsIgnoreCase("black")) typeOfBlackFigures[i]++;
                else throw new IllegalArgumentException("Wrong color");
            }
        }
    }

    public void deleteFigure(int deleteX, int deleteY) {
        wrongCoordinate(deleteX, deleteY);
        //if ((deleteX < 0 || deleteX > 7) || ((deleteY < 0 || deleteY > 7)))
         //   throw new IllegalArgumentException("Wrong X||Y format");

        for (int i = 0; i < 6; i++) {
            if (figures[i].equalsIgnoreCase(desk[deleteX][deleteY].figure)) {
                if (desk[deleteX][deleteY].color.equalsIgnoreCase("white") && typeOfWhiteFigures[i] != 0) {
                    typeOfWhiteFigures[i]--; // удаляем из счетчика
                } else if (desk[deleteX][deleteY].color.equalsIgnoreCase("black") && typeOfBlackFigures[i] != 0) {
                    typeOfBlackFigures[i]--;
                } else {
                    throw new IllegalArgumentException("Wrong deleted figure format");
                }
            }
        }
        if (desk[deleteX][deleteY].figure.equalsIgnoreCase("king"))
            throw new IllegalArgumentException("Wrong deleted figure format");

        desk[deleteX][deleteY] = new PointForDesk(deleteX, deleteY, "not", " ");
    }


    private void wrongCoordinate(int x, int y) {
        if (x < 0 || x > sizeX) throw new IllegalArgumentException("Wrong X coordinate");
        if (y < 0 || y > sizeY) throw new IllegalArgumentException("Wrong Y coordinate");
    }

    public void changePlaceOfFigure(int oldX, int oldY, int newX, int newY) {
        wrongCoordinate(oldX, oldY);
        wrongCoordinate(newX, newY);

        boolean wrong = false;
        if (!desk[newX][newY].figure.equalsIgnoreCase(" ")) { // если новая клетка занята, новую фигуру выкидываем
            for (int i = 0; i < 6; i++) {
                if (desk[newX][newY].figure.equalsIgnoreCase(figures[i])) { // а есть ли такая фигура... считаем ее, чтобы удалить из счетчика по фигурам

                    // проверка на близость королей при возможном ходе одного из них
                    if (desk[oldX][oldY].figure.equalsIgnoreCase("king")) {
                        if (desk[oldX][oldY].figure.equalsIgnoreCase("white")) {
                            if (Math.sqrt(Math.pow(newX - blackKing[0], 2) + Math.pow(newY - blackKing[1], 2)) < 2) {
                                throw new IllegalArgumentException("You cant stay with another king");
                            }
                        } else {
                            if (Math.sqrt(Math.pow(newX - whiteKing[0], 2) + Math.pow(newY - whiteKing[1], 2)) < 2) {
                                throw new IllegalArgumentException("You cant stay with another king");
                            }
                        }
                    }

                    if (!desk[newX][newY].color.equalsIgnoreCase(desk[oldX][oldY].color)) { // своих бить не будем
                        if (desk[newX][newY].color.equalsIgnoreCase("white")) {
                            typeOfWhiteFigures[i]--;
                        } else typeOfBlackFigures[i]--;
                        wrong = true;
                        break;
                    }
                    throw new IllegalArgumentException("You cant hit your figure");
                }
            }
            if (!wrong) throw new IllegalArgumentException("Try something another"); // очень вряд-ли сюда попадет
        }
        //boolean proverka = desk.desk[--newX][newY].figure.equalsIgnoreCase("king") || desk.desk[newX][--newY].figure.equalsIgnoreCase("king") || desk.desk[--newX][--newY].figure.equalsIgnoreCase("king") || desk.desk[++newX][newY].figure.equalsIgnoreCase("king") || desk.desk[newX][++newY].figure.equalsIgnoreCase("king") || desk.desk[++newX][++newY].figure.equalsIgnoreCase("king") || desk.desk[--newX][newY].figure.equalsIgnoreCase("king") || desk.desk[--newX][newY].figure.equalsIgnoreCase("king") ||

        if (desk[oldX][oldY].figure.equalsIgnoreCase("king")) {
            if (desk[oldX][oldY].figure.equalsIgnoreCase("white")) {
                whiteKing[0] = newX;
                whiteKing[1] = newY;
            } else {
                blackKing[0] = newX;
                blackKing[1] = newY;
            }
        } // desk.desk[oldX][oldY] = new PointForDesk(oldX, oldY, "not", " ");

        desk[newX][newY].color = desk[oldX][oldY].color;
        desk[newX][newY].figure = desk[oldX][oldY].figure;
        desk[oldX][oldY].color = "not";
        desk[oldX][oldY].figure = " ";

    }


    public boolean equals(Chess b) {  // сравнение
        for (int i = 0; i < sizeX; i++) {
            for (int l = 0; l < sizeY; l++) {
                if (!desk[i][l].figure.equals(b.desk[i][l].figure)) return false;
                if (!desk[i][l].color.equals(b.desk[i][l].color)) return false;
                if (desk[i][l].x != b.desk[i][l].x) return false;
                if (desk[i][l].y != b.desk[i][l].y) return false;

            }
        }

        return true;
    }


    public void addAllColorPawns(@NotNull String color) {
        if (color.equalsIgnoreCase("white")) {
            for (int l = 0; l < sizeY; l++) {
                desk[6][l].color = "white";
                desk[6][l].figure = "pawn";
            }
        } else if (color.equalsIgnoreCase("black")) {
            for (int i = 0; i < sizeY; i++) {
                desk[1][i].color = "black";
                desk[1][i].figure = "pawn";
            }
        } else throw new IllegalArgumentException("Wrong information about color");
    }


    public void addAllFigures() {
        deleteAllFigures();
        for (int l = 0; l < sizeY; l++) {
            desk[6][l].color = "white";
            desk[6][l].figure = "pawn";
        }
        for (int i = 0; i < sizeY; i++) {
            desk[1][i].color = "black";
            desk[1][i].figure = "pawn";
        }
        addFigure(0, 0, "black", "castle"); // ладьи
        addFigure(0, sizeY - 1, "black", "castle");
        addFigure(sizeX - 1, 0, "white", "castle");
        addFigure(sizeX - 1, sizeY - 1, "white", "castle");

        addFigure(0, 1, "black", "knight"); // кони
        addFigure(0, sizeY - 2, "black", "knight");
        addFigure(sizeX - 1, 1, "white", "knight");
        addFigure(sizeX - 1, sizeY - 2, "white", "knight");

        addFigure(0, 2, "black", "bishop"); // слоны
        addFigure(0, sizeY - 3, "black", "bishop");
        addFigure(sizeX - 1, 2, "white", "bishop");
        addFigure(sizeX - 1, sizeY - 3, "white", "bishop");

        addFigure(0, 3, "black", "queen"); //королевы
        addFigure(sizeX - 1, sizeY - 4, "white", "queen");

        desk[0][4] = new PointForDesk(0, 4, "black", "king"); // благодаря полному удалению фигур можем ставить королей
        desk[7][3] = new PointForDesk(7, 3, "white", "king");

    }

    public void deleteAllFigures() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                desk[i][j] = new PointForDesk(i, j, "not", " ");
            }
        }
    }


}
