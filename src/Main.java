import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends JFrame implements Runnable {

    JFrame frame;
    private static Canvas canvas;
    private static JPanel panel;
    public static JLabel anglelabel = new JLabel("Set the rotatespeed here: ", SwingConstants.CENTER);
    public static JSlider angle;
    public static JLabel sideslabel = new JLabel("Set the number of sides here: ", SwingConstants.CENTER );
    public static JSlider sides;
    private static Control control;

    private int s = 800; // screen size
    public int offSet = 300;
    private int roatate = 0;
    public static int rotatespeed = 0;
    public static int setsides = 0;

    public Main() {
        frame = new JFrame("POLYGONS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        frame.add(canvas = new Canvas());
        frame.add(panel = new JPanel());

        /*
        angle = new JSlider(-15, 15, 0);
        angle.addChangeListener(control = new Control());
        panel.add(anglelabel);
        panel.add(angle);

         */


        sides = new JSlider(3, 20, 3);
        sides.addChangeListener(control = new Control());
        panel.add(sideslabel);
        panel.add(sides);



        frame.setSize(1240, 640);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();

    }


    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.white);
        g.drawOval(0, 0, 600, 600);

        g.setColor(Color.white);
        for (int i = 0; i < setsides ; i++) {
        Polygons p = Polygons.poligons.get(i);
        for (int j = 0; j < p.sides - 1; j++) {
            g.drawLine(p.x[p.sides - 1] + offSet, p.y[p.sides - 1] + offSet, p.x[0] + offSet, p.y[0] + offSet);
            g.drawLine(p.x[j] + offSet, p.y[j] + offSet, p.x[j + 1] + offSet, p.y[j + 1] + offSet);
            }
        }
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        BasicTimer basicTimer = new BasicTimer(60);
        while (true) {
            basicTimer.sync();
            render();
            for (int j = 0; j < Polygons.poligons.size(); j++) {
                Polygons.poligons.get(j).countCoordinates(Polygons.poligons.get(j), roatate);
            }
            roatate = roatate + rotatespeed;
        }
    }


    public static void main(String[] args) {
        Polygons.init();

        /*
        for (int j = 0; j < Polygons.poligons.size(); j++) {
            Polygons.poligons.get(j).countCoordinates(Polygons.poligons.get(j), 30);
        }

         */
        for (int j = 0; j < Polygons.poligons.size(); j++) {
            for (int i = 0; i < Polygons.poligons.get(j).sides; i++) {
                System.out.println(Polygons.poligons.get(j).x[i] + " " + Polygons.poligons.get(j).y[i]);
            }
            System.out.println(Polygons.poligons.get(j).angle);
        }


        new Main();
        System.out.println(Math.sin(Math.toRadians(90)));
    }

}
