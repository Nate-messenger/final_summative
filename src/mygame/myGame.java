package mygame;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author nate
 */
public class myGame extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 600;
    static final int HEIGHT = 740;

    //Title of the window
    String title = "galactic Dogfight";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    // YOUR GAME VARIABLES WOULD GO HERE
    BufferedImage stars = imageHelper.loadImage("stars.jpg");
    BufferedImage person = imageHelper.loadImage("playerm.png");
    BufferedImage enemyd = imageHelper.loadImage("enemyexpl.jpg");
    BufferedImage lifel = imageHelper.loadImage("player.jpg");
    BufferedImage bossShip = imageHelper.loadImage("bossship.png");
    BufferedImage lifeup = imageHelper.loadImage("life.png");
    BufferedImage starts = imageHelper.loadImage("tittle_screen.png");
    BufferedImage enemy = imageHelper.loadImage("st6.png");
    BufferedImage enemy3m = imageHelper.loadImage("better_Enemy.png");
    BufferedImage lazer = imageHelper.loadImage("1.png");

    int bosstime = 0;
//wall stuff
    int wallp = 0;
    int wally = -500;
    int oy = 0;
    int bossh = 500;

    int o1 = 0;
    int o2 = 100;
    int o3 = 200;
    int o4 = 300;
    int o5 = 400;
    int o6 = 500;

    int wallcounter = 0;

    int playerspeed = 5;

    int rand = speed();

    //timer to spawn the boss
    int bossti = 0;

    //random corinants for enemy spawn
    int x = enemy();
    int x2 = enemy();
    int x3 = enemy();

    int enemy3h = 10;

    //to have the boss spawn offscrean to be put in the game later
    int bossx = 0;
    int bossy = 0;

//    walls
    int y = -50;
    int y2 = -50;
    int y3 = -50;

    //lives and score
    int lives = 4;
    int score = 0;

    int shots = 15;

    int frame = 0;

    int bspeed = 4;

    int lx = 0;
    int ly = 0;

    int lazx = 0;
    int lazy = 0;

    boolean bossshotreset = false;

    boolean wall = true;
    boolean bosss = false;

    //player controls
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean shoot = false;

    boolean rbutton = false;

    //enemy respawning
    boolean destroyed = false;
    boolean destroyed2 = false;
    boolean destroyed3 = false;

    //player death
    boolean death = false;
    boolean life = false;

    //increases the score
    boolean smack = false;

    //boolean to control shooting
    boolean canShoot = true;

    //starting the game
    boolean start = false;

    //gaining life
    boolean lifeGain = false;

    //to initiate win screan 
    boolean win = false;

    //to spawn the boss whenever i want also incrases the score
    boolean bspawn = false;

    boolean powerupl = false;

    Rectangle wall1 = new Rectangle(0, oy, 100, 50);
    Rectangle wall2 = new Rectangle(100, oy, 100, 50);
    Rectangle wall3 = new Rectangle(200, oy, 100, 50);
    Rectangle wall4 = new Rectangle(300, oy, 100, 50);
    Rectangle wall5 = new Rectangle(400, oy, 100, 50);
    Rectangle wall6 = new Rectangle(500, oy, 100, 50);

//players
    Rectangle player1 = new Rectangle(WIDTH / 2, 700, 35, 35);

    Rectangle shot = new Rectangle(player1.x, player1.y, 10, 14);
    Rectangle shot2 = new Rectangle(player1.x + 50, player1.y, 10, 14);

    Rectangle enemy1 = new Rectangle(x, y, 50, 50);
    Rectangle enemy2 = new Rectangle(x2, y2, 50, 50);
    Rectangle enemy3 = new Rectangle(x3, y3, 70, 70);

    Rectangle bosssh = new Rectangle(bossx + 130, bossy + 200, 10, 14);

    Rectangle boss = new Rectangle(150, 80, 300, 200);

    Rectangle heart = new Rectangle(lx, ly, 50, 50);

    Rectangle powerup1 = new Rectangle(lazx, lazy, 50, 50);

    boolean restart = false;

    int powerupLegnth = 0;

    // GAME VARIABLES END HERE   
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public myGame() {
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();

        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // GAME DRAWING GOES HERE

        if (!start) {
            g.drawImage(starts, 0, 0, WIDTH, HEIGHT, this);
        } else {
            //background
            g.drawImage(stars, 0, 0, WIDTH, HEIGHT, this);

            //enemys
            g.drawImage(enemy, enemy1.x, enemy1.y, enemy1.height, enemy1.width, this);
            g.drawImage(enemy, enemy2.x, enemy2.y, enemy2.height, enemy2.width, this);
            g.drawImage(enemy3m, enemy3.x, enemy3.y, enemy3.height, enemy3.width, this);

//random enemy spawn
            if (enemy1.intersects(shot)) {
                g.drawImage(enemyd, enemy1.x, enemy1.y, 100, 100, this);
            }
            enemy();
            if (destroyed) {
                enemy();
                speed();
                enemy1.x = enemy();
                enemy1.y = -100;
                destroyed = false;
                canShoot = false;

            }
            if (enemy3.intersects(shot)) {
                g.drawImage(enemyd, enemy3.x, enemy3.y, 100, 100, this);
            }
            if (destroyed2) {
                enemy();
                speed();
                enemy2.x = enemy();
                enemy2.y = -125;
                destroyed2 = false;
                canShoot = false;

            }
            if (enemy2.intersects(shot)) {
                g.drawImage(enemyd, enemy2.x, enemy2.y, 50, 50, this);
            }
            if (destroyed3) {
                enemy();
                speed();
                enemy3.x = enemy();
                enemy3.y = -125;
                destroyed3 = false;
                canShoot = false;

            }

            //projectile
            g.setColor(Color.CYAN);
            g.drawRect(shot.x, shot.y, shot.width, shot.height);
            g.drawRect(shot2.x, shot2.y, shot2.width, shot2.height);

            //player
            g.drawImage(person, player1.x - 20, player1.y, 100, 100, this);

            //obsticles
            g.setColor(Color.ORANGE);

            g.fillRect(wall1.x, wall1.y, wall1.width, wall1.height);

            g.fillRect(wall2.x, wall2.y, wall2.width, wall2.height);

            g.fillRect(wall3.x, wall3.y, wall3.width, wall3.height);

            g.fillRect(wall4.x, wall4.y, wall4.width, wall4.height);

            g.fillRect(wall5.x, wall5.y, wall5.width, wall5.height);

            g.fillRect(wall6.x, wall6.y, wall6.width, wall6.height);

            //keeping track of score
            if (smack) {
                score += 10;
                smack = false;
            }

            //boss spawing
            if (bosss) {

                g.setColor(Color.white);
                g.drawString("BOSS", 270, 30);
                g.setColor(Color.RED);
                g.fillRect(150, 50, bossh / 2, 25);
                g.setColor(Color.DARK_GRAY);
                g.drawImage(bossShip, boss.x, boss.y, boss.width, boss.height, this);
                g.setColor(Color.ORANGE);
                g.drawRect(bosssh.x, bosssh.y, 10, 14);

            }

            //death
            if (death) {
                g.setColor(Color.white);
                g.drawString("GAME OVER", 280, HEIGHT / 2);
                g.drawString("" + score, 290, HEIGHT / 2 - 50);
                g.drawImage(lifel, player1.x - 20, player1.y, this);
            }

            if (win) {
                score += 1000;
                g.setColor(Color.white);
                g.drawString("YOU WIN", 280, HEIGHT / 2);
                g.drawString("" + score, 290, HEIGHT / 2 - 50);

            }
            //health bar
            g.setColor(Color.white);
            g.drawString("Health", 10, 30);
            g.setColor(Color.RED);
            g.fillRect(55, 20, lives * 50 + 2, 20);

            if (lifeGain) {
                g.drawImage(lifeup, heart.x, heart.y, heart.width, heart.height, this);
            }
            if (score > 2000) {
                g.drawImage(lazer, powerup1.x, powerup1.y, 75, 75, this);
            }

            if (bossh < 1) {
                g.drawImage(enemyd, boss.x, boss.y, boss.width, boss.height, this);

            }
        }

        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here

    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE
            if (restart) {
                bosstime = 0;
//wall stuff
                wallp = 0;
                wally = -500;
                oy = 0;
                bossh = 500;

                o1 = 0;
                o2 = 100;
                o3 = 200;
                o4 = 300;
                o5 = 400;
                o6 = 500;

                wallcounter = 0;

                playerspeed = 5;

                rand = speed();

                bossti = 0;

                x = enemy();
                x2 = enemy();
                x3 = enemy();

                enemy3h = 10;

                bossx = 0;
                bossy = 0;

//    walls
                y = -50;
                y2 = -50;
                y3 = -50;

                lives = 4;
                score = 0;

                shots = 15;

                frame = 0;

                bspeed = 4;

                lx = 0;
                ly = 0;

                lazx = 0;
                lazy = 0;

                wall = false;
                bosss = false;

                up = false;
                down = false;
                left = false;
                right = false;
                shoot = false;

                rbutton = false;

                destroyed = true;
                destroyed2 = true;
                destroyed3 = true;

                death = false;
                life = false;

                smack = false;

                canShoot = true;

                start = false;

                lifeGain = false;

                win = false;

                bspawn = false;

                powerupl = false;

                bossshotreset = false;

            }

            if (rbutton) {
                restart = true;
                done = false;
                win = false;
                death = false;
            } else {
                restart = false;
            }

            if (shoot) {
                start = true;
            }
            if (start) {
                //gradually increses score
                score += 1;
                //powerup spawning
                if (score > 2000 || score > 4000) {
                    powerup1.y += 1;
                    lazy += 1;

                } else {
                    powerup1.y = -100;
                    powerup1.x = WIDTH / 2 + 50;
                }
                if (powerup1.intersects(player1)) {
                    powerupl = true;
                     powerup1.y = -10000;
                }

                if (powerupl) {
                    powerupLegnth += 1;
                    shot.x = player1.x;
                    shot2.x = player1.x + 50;
                    
                    
                    shot.height += 13;
                    shot2.height += 13;
                    if (powerupLegnth > 300) {
                        powerupl = false;
                        powerupLegnth = 0;
                    }
                } else {
                    shot.height = 14;
                    shot2.height = 14;
                }
                colisions();
                wallcolisions();
                //loseing lives
                if (life) {
                    lives -= 1;
                    life = false;
                }
//winning
                if (bossh <= 0) {
                    win = true;
                    done = true;

                }

                //movement
                if (up) {
                    player1.y -= playerspeed;
                }
                if (down) {
                    player1.y += playerspeed;
                }
                if (left) {
                    player1.x -= playerspeed * 2;
                }
                if (right) {
                    player1.x += playerspeed * 2;
                }
//cheat code / debuging
                if (bspawn) {
                    bosstime += 3600;
                    score += 10000;
                }
                //tells when to spawn the boss
                if (bosstime > 3600) {
                    bosss = true;

                }
                //boss spawing
                if (bosss) {

                    bosssh.y += 13;

                    bossti = 0;
                    //boss shooting
                    if (bosssh.y > HEIGHT) {
                        bossshotreset = true;
                    }
                    if (bossshotreset) {
                        bosssh.x = bossbullet(bossx);
                        bosssh.y = bossy + 200;
                        bossshotreset = false;
                    }
                    //boss movemnt from side to side
                    bossti += 1;
                    boss.x -= bspeed;
                    if (boss.x < 40) {
                        bspeed = -bspeed;
                    }
                    if (boss.x + 300 > 570) {
                        bspeed = -bspeed;
                    }

                }
                //shooting macanic

                while (shot.y < 0) {
                    shot.y = player1.y;
                    shot.x = player1.x;
                }
                while (shot2.y < 0) {
                    shot2.y = player1.y;
                    shot2.x = player1.x + 50;

                }
                //controlls shooting
                if (shoot) {
                    shot2.y -= shots;
                    shot.y -= shots;

                    if (canShoot) {

                        shot2.y -= shots;
                        shot.y -= shots;

                    }
                    if (!canShoot) {
                        shot.x = player1.x;
                        shot2.x = player1.x + 50;
                        shot.y = player1.y + 20;
                        shot2.y = player1.y + 20;
                        canShoot = true;
                    }

                    if (shot.intersects(wall1) || shot.intersects(wall2) || shot.intersects(wall3) || shot.intersects(wall4) || shot.intersects(wall5) || shot.intersects(wall6)) {
                        canShoot = false;
                    }
                } else {
                    shot.x = player1.x;
                    shot2.x = player1.x + 50;
                    shot.y = 750;
                    shot2.y = 750;

                }

                //enemy macanics
                enemy1.y += speed();

                enemy2.y += speed();

                enemy3.y += speed();

                //death
                if (lives < 0) {
                    done = true;
                    death = true;
                }
                //moving walls to create openings 
                if (wall && !bosss) {

                    wall1.y += 7;
                    wall2.y += 7;
                    wall3.y += 7;
                    wall4.y += 7;
                    wall5.y += 7;
                    wall6.y += 7;
                    if (wallcounter == 0) {
                        wall4.x = wall5.x;

                    }
                    if (wallcounter == 1) {
                        wall2.x = wall5.x;
                    }
                    if (wallcounter == 2) {
                        wall6.x = wall5.x;
                    }
                    if (wallcounter == 3) {
                        wall1.x = wall5.x;
                    }
                    if (wallcounter == 4) {
                        wall3.x = wall5.x;
                    }
                    if (wallcounter == 5) {
                        wall1.x = wall5.x;
                    }
                    if (wallcounter == 6) {
                        wall6.x = wall5.x;
                    }

                } else {
                    wall1.y = - 200;
                    wall2.y = - 200;
                    wall3.y = - 200;
                    wall4.y = - 200;
                    wall5.y = - 200;
                    wall6.y = - 200;

                    wall1.x = o1;
                    wall2.x = o2;
                    wall3.x = o3;
                    wall4.x = o4;
                    wall5.x = o5;
                    wall6.x = o6;

                    wallcounter += 1;
                    wall = true;
                    if (wallcounter == 6) {
                        wallcounter = 0;
                    }

                }
                if (heart.y > HEIGHT) {
                    heart.y = -4000;
                    ly = -4000;
                }
                if (frame > 0) {
                    lifeGain = true;
                }
                //bringinging the health down from the top of the screen
                if (lifeGain) {
                    heart.x = WIDTH / 2;
                    heart.y += 3;

                }
                //increases the count of stuff i want to use later 
                frame += 1;
                bosstime += 1;
            }

            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {

        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            // if r is pressed 
            if (code == KeyEvent.VK_R) {
                rbutton = true;
            }
            //If pressed right
            if (code == KeyEvent.VK_RIGHT) {
                right = true;
            }
            //if pressed left
            if (code == KeyEvent.VK_LEFT) {
                left = true;
            }
            //if pressed space 
            if (code == KeyEvent.VK_SPACE) {
                shoot = true;

            }
            //if pressed up
            if (code == KeyEvent.VK_UP) {
                up = true;
            }
            // if pressed down
            if (code == KeyEvent.VK_DOWN) {
                down = true;
            }
            //If pressed right
            if (code == KeyEvent.VK_D) {
                right = true;
            }
            //if pressed left
            if (code == KeyEvent.VK_A) {
                left = true;
            }

            //if pressed up
            if (code == KeyEvent.VK_W) {
                up = true;
            }
            // if pressed down
            if (code == KeyEvent.VK_S) {
                down = true;
            }
            // if p is released
            if (code == KeyEvent.VK_P) {
                bspawn = true;
            }
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            //if right is released
            if (code == KeyEvent.VK_R) {
                rbutton = false;
            }
            //if right is released
            if (code == KeyEvent.VK_RIGHT) {
                right = false;
            }
            //if left is released
            if (code == KeyEvent.VK_LEFT) {
                left = false;
            }
            //if space is released
            if (code == KeyEvent.VK_SPACE) {
                shoot = false;
            }
            //if up is released
            if (code == KeyEvent.VK_UP) {
                up = false;
            }
            // if down is released
            if (code == KeyEvent.VK_DOWN) {
                down = false;
            }
            //if right is released
            if (code == KeyEvent.VK_D) {
                right = false;
            }
            //if left is released
            if (code == KeyEvent.VK_A) {
                left = false;
            }

            //if up is released
            if (code == KeyEvent.VK_W) {
                up = false;
            }
            // if down is released
            if (code == KeyEvent.VK_S) {
                down = false;
            }
            // if p is released
            if (code == KeyEvent.VK_P) {
                bspawn = false;
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        myGame game = new myGame();

        // starts the game loop
        game.run();
    }

    /**
     * randomizing enemy spawns
     *
     * @return
     */
    public static int enemy() {
        int p = 0;
        for (int i = 0; i < 10; i++) {

            int randx2 = (int) (Math.random() * (500 - 50 + 1)) + 50;

            p = randx2;
        }
        return p;
    }

    /**
     * speed of the enemy
     *
     * @return
     */
    public static int speed() {

        int randx = (int) (Math.random() * (10 - 8 + 1)) + 8;

        return randx;
    }

    /**
     * *
     * to randomize where the bullet gets dropped from
     *
     * @param bossx
     * @return
     */
    public static int bossbullet(int bossx) {

        int randx = (int) (Math.random() * (600 - bossx + 1)) + bossx;

        return randx;
    }

    public void colisions() {
        //makes sure you do not go off the screen
        if (player1.x > WIDTH - 75) {
            player1.x -= playerspeed * 2;
        }
        if (player1.x < 0 + 25) {
            player1.x += playerspeed * 2;
        }
        if (player1.y > HEIGHT - 70) {
            player1.y -= playerspeed;
        }
        if (player1.y < 0 + 200) {
            player1.y += playerspeed;
        }

        if (player1.intersects(boss)) {
            life = true;
            player1.y += 100;
        }

        //enemy 1
        if (shot.intersects(enemy1) || shot2.intersects(enemy1)) {
            destroyed = true;
            smack = true;
        }
        if (enemy1.intersects(player1)) {
            life = true;
            destroyed = true;
        }

        if (enemy1.y > 740) {
            destroyed = true;
        }

        //enemy 2
        if (shot.intersects(enemy2) || shot2.intersects(enemy2)) {
            destroyed2 = true;
            smack = true;
        }
        if (enemy2.intersects(player1)) {
            life = true;
            destroyed2 = true;
        }
        if (enemy2.y > 740) {
            destroyed2 = true;
        }

        //enemy 3
        if (shot.intersects(enemy3) || shot2.intersects(enemy3)) {
            destroyed3 = true;
            smack = true;
            enemy3h -= 1;
            if (enemy3h < 0) {

                destroyed3 = true;
                enemy3h = 10;
            }
        }
        if (enemy3.intersects(player1)) {
            life = true;
            destroyed3 = true;

        }
        if (enemy3.y > 740) {
            destroyed3 = true;
        }

        if (heart.intersects(player1)) {
            lives += 1;
            heart.y = -4000;
            ly = -4000;

        }
        if (bosss) {

            if (shot.intersects(boss)) {
                bossh -= 6;
                canShoot = false;
            }

        }
    }

    public void wallcolisions() {
        //wall colisions
        if (wall1.intersects(player1)) {
            wall = false;
            life = true;
        }
        if (wall2.intersects(player1)) {
            life = true;
            wall = false;
        }
        if (wall3.intersects(player1)) {
            life = true;
            wall = false;
        }
        if (wall4.intersects(player1)) {
            life = true;
            wall = false;
        }
        if (wall5.intersects(player1)) {
            life = true;
            wall = false;
        }
        if (wall6.intersects(player1)) {
            life = true;
            wall = false;
        }
        if (wall1.y > HEIGHT) {
            wall = false;
        }
        if (bosssh.intersects(player1)) {
            life = true;
            bossshotreset = true;
        }
    }

}
