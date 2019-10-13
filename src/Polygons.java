import java.util.ArrayList;

public class Polygons {

    static int radius = 300;
    int sides;
    double angle;
    int [] x;
    int [] y;

    static ArrayList<Polygons> poligons = new ArrayList<>();

    public Polygons(){}

    public Polygons(int sides, double angle, int [] x, int [] y) {
        this.sides = sides;
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    public static void init(){
        for (int i = 3; i < 360; i++){
            poligons.add(new Polygons(i, (360f/i), new int[i], new int[i]));
        }
    }

    public void countCoordinates(Polygons p, int rotate){
        double anglecounter = 90 + rotate;
        for (int i = 0; i < p.sides; i++){
            //p.x[0] = 0;
            //p.y[0] = radius;
            p.x[i] = -1 * (int) Math.floor( Math.cos(Math.toRadians(anglecounter))*radius);
            p.y[i] = -1 * (int) Math.floor( Math.sin(Math.toRadians(anglecounter))*radius);
            anglecounter = anglecounter + p.angle;
            //anglecounter = anglecounter + rotate;
        }
    }


}
