import org.jetbrains.annotations.NotNull;

public class PointForDesk {
    int x;
    int y;
    String color;
    String figure;

    //colors: white == белый, black == черный, not == пустая клетка

    PointForDesk(int x, int y, String color, String figure) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.figure = figure;
    }

    public void wrongPoint(Chess in) { // никому не нужна

        if (!(x >= 0 && x < in.sizeX) || !(y >= 0 && y < in.sizeY)) {
            throw new IllegalArgumentException("Wrong X||Y format");
        }

        int counter = 0;
        boolean wrong = false;
        if (this.color.equalsIgnoreCase("white")) {
            for (String element : in.figures) {
                if (this.figure.equalsIgnoreCase(element)) {
                    if (!this.figure.equalsIgnoreCase("king")) {
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
        } else if (this.color.equalsIgnoreCase("black")) {
            for (String element : in.figures) {
                if (this.figure.equalsIgnoreCase(element)) {
                    if (!this.figure.equalsIgnoreCase("king")) {
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
