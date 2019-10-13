import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Control implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (source == Main.angle) {
            Main.rotatespeed = source.getValue();
        } else if (source == Main.sides) {
            Main.setsides = ((JSlider) e.getSource()).getValue()-3;
        }
    }
}
