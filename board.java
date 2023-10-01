import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class board extends JPanel implements ActionListener {

    private Image appale;
    private Image dot;
    private Image head;

    private final int ALL_DOTS = 900;
    private final int DOTS_SIZE = 10;
    private final int RANDOM_POSITION = 29;

    private int appale_x;
    private int appale_y;



    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean ingame = true;
    private int dots;

    private Timer timer;
    board(){
        addKeyListener(new vasu());

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);

        loadimagee();
        initGame();
    }

    public void loadimagee(){
        ImageIcon i1 = new  ImageIcon(ClassLoader.getSystemResource("icons/apple.png"));
        appale =i1.getImage();

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/dot.png"));
        dot =i2.getImage();

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/head.png"));
        head =i3.getImage();

    }
    public void initGame(){
        dots = 3;

        for(int i=0; i< dots; i++ ){
            y[i] = 50;
            x[i] = 50- i * DOTS_SIZE;
        }

        locateApple();

        Timer timer = new Timer(140,this);
        timer.start();
    }
    public void locateApple(){
        int r = (int)(Math.random() * RANDOM_POSITION);
        appale_x = r * DOTS_SIZE;

        r = (int)(Math.random() * RANDOM_POSITION);
        appale_y = r * DOTS_SIZE;

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        if (ingame) {
            g.drawImage(appale, appale_x, appale_y, this);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);

                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameover(g);
        }
    }


   public void gameover(Graphics g){
        String msg ="gaBHB meover";
        //Font font = new Font("SAN SERIF",Font.BOLD,14);
       Font font = new Font("SAN_SERIF",Font.BOLD,20);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg,(300 - metrics.stringWidth(msg))/2,300/2);
    }

    public void move(){
        for(int i = dots; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
           // repaint();
        }

        if(leftDirection){
            x[0] = x[0] - DOTS_SIZE;
        }
        if(rightDirection){
            x[0] = x[0] + DOTS_SIZE;
        }
        if(upDirection){
            y[0] = y[0] - DOTS_SIZE;
        }
        if(downDirection){
            y[0] = y[0] + DOTS_SIZE;
        }


        //x[0] += DOTS_SIZE;
        // y[0] += DOTS_SIZE;
    }
    public void checkapple(){
        if(x[0] == appale_x && y[0] == appale_y){
            dots ++;
            locateApple();
        }
    }
    public void checkcollision(){
        for(int i = dots;i >0; i--){
            if((i > 4) && (x[0] == x[i])&&(y[0] == y[i])){
                ingame = false;
            }
        }
        if(y[0] >= 300){
            ingame = false;
        }
        if(x[0] >= 300){
            ingame = false;
        }
        if(y[0] < 0){
            ingame = false;
        }
        if(x[0] < 0){
            ingame = false;
        }
        if(!ingame){
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ingame) {
            checkapple();
            checkcollision();
            move();
        }

        repaint();
    }
    public class vasu extends KeyAdapter{
        public void keyPressed( KeyEvent event){
            int Key = event.getKeyCode();

            if(Key == KeyEvent.VK_LEFT && (!rightDirection)){
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if(Key == KeyEvent.VK_RIGHT && (!leftDirection)){
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if(Key == KeyEvent.VK_UP && (!downDirection)){
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
            if(Key == KeyEvent.VK_DOWN && (!upDirection)){
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}
