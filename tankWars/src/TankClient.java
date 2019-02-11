import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {

    // 让坦克动起来,把fillOval里面x，y值放置成变量的
    int x = 50, y = 50;

    // 这个方法默认会被调用
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,30,30);
        g.setColor(c);
         y += 5;
    }

    public void launchFrame(){
        this.setLocation(400,300);
        this.setSize(800,600);
        this.setTitle("TankWar");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.green);
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args){
        TankClient tc = new TankClient();
        tc.launchFrame();
    }

    private class PaintThread implements Runnable{

        @Override
        public void run() {
            while (true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
