import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {

    // 定义成常量，便于程序的扩展
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    // 让坦克动起来,把fillOval里面x，y值放置成变量的
    int x = 50, y = 50;

    Image offScreenImage = null;

    // repaint方法先调用update方法，再调用paint方法
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        // 图片的画笔，先画图片再把图片一次性画到屏幕上面
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    // 这个方法默认会被调用
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,30,30);
        g.setColor(c);
    }

    public void launchFrame(){
        this.setLocation(400,300);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setTitle("TankWar");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.green);

        this.addKeyListener(new KeyMonitor());

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

    private class KeyMonitor extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT: x -= 5; break;
                case KeyEvent.VK_RIGHT: x += 5; break;
                case KeyEvent.VK_UP: y -= 5; break;
                case KeyEvent.VK_DOWN: y += 5; break;
            }
//            if(key == KeyEvent.VK_RIGHT){
//                y += 5;
//            }else if(key == KeyEvent.VK_LEFT){
//                y -= 5;
//            }
        }

    }

}
