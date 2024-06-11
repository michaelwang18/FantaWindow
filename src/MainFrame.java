import javax.swing.*;
import java.awt.*;

public class MainFrame implements Runnable{

    private Thread thread;

    private Game panel;

    public MainFrame(String name){
        JFrame frame = new JFrame("Untitled FG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        System.out.println("set size");
        frame.setLocationRelativeTo(null);
        System.out.println("HUH");

        panel = new Game();
        frame.add(panel);

    //    frame.add(panel);
        frame.setVisible(true);

        thread = new Thread(this);
        thread.start();

       // panel.start();


    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
      while (true){
          panel.repaint(); // finish later
      }
    }

    public void pause(int miliseconds) throws InterruptedException {
        wait(miliseconds);
    }
}
