import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.WatchKey;
import java.util.ArrayList;

public class Animation implements ActionListener { //adapted from demo code
    private BufferedImage[] frames;
    private BufferedImage[] atkframes;
    private Timer timer;
    private int currentFrame;
    private boolean atk = false;

    public Animation(BufferedImage[] frames, BufferedImage[] atkF, int delay) {
        this.frames = frames;
        atkframes = atkF;
        currentFrame = 0;
        timer = new Timer(delay, this);
        timer.start();
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public  BufferedImage[] getFrames() {
        return frames;
    }

    public BufferedImage getActiveFrame() {
        if (atk){
            if (currentFrame == atkframes.length - 1){
                atk = false;
                currentFrame = 0;
                return atkframes[atkframes.length-1];
            }
            return atkframes[currentFrame];
        }
        return frames[currentFrame];
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            //This advances the animation to the next frame
            //It also uses modulus to reset the frame to the beginning after the last frame
            //In other words, this allows our animation to loop
                currentFrame = (currentFrame + 1) % frames.length;


        }
    }

    public void attack(){
        atk = true;
        currentFrame = 0;
    }
}
