/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author HOPEWELL
 */
public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 700;
    static final int DELAY = 8;
    
    static final int ROWS = 8;
    static final int COLS = 7;
    static final int BRICK_WIDTH = 90;
    static final int BRICK_HEIGHT = 15;
    static final int BRICK_GAP = 5;
    static final int BRICK_TOP_OFFSET = 60;
    
    Timer timer;
    Paddle paddle;
    Ball ball;
    Brick[] bricks;
    
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean gameOver = false;
    boolean gameWon = false;
    boolean started = false;
    int score = 0;
    int lives = 3;
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyReleased(e);
            }
        });
        
        initGame();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
            
    private void initGame() {
        paddle = new Paddle(SCREEN_WIDTH / 2 - 50, SCREEN_HEIGHT - 50, 100, 15, 6, SCREEN_WIDTH);
        ball = new Ball(SCREEN_WIDTH / 2 - 10, SCREEN_HEIGHT - 80, 20, 3, -4);
        createBricks();
        score = 0;
        lives = 3;
        gameOver = false;
        gameWon = false;
        started = false;
    }
    
    private void createBricks(){
        bricks = new Brick[ROWS * COLS];
        Color[] rowColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
        int totalBrickWidth = COLS * (BRICK_WIDTH + BRICK_GAP) - BRICK_GAP;
        int startX = (SCREEN_WIDTH - totalBrickWidth) / 2;
        
        int index = 0;
        for (int row = 0; row < ROWS; row++ ) {
            for (int col = 0; col < COLS; col++) {
                int x = startX + col * (BRICK_WIDTH + BRICK_GAP);
                int y = BRICK_TOP_OFFSET + row * (BRICK_HEIGHT + BRICK_GAP);
                bricks[index] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, rowColors[row % rowColors.length]);
                index ++;
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g) {
        paddle.draw(g);
        ball.draw(g);
        for (Brick b : bricks) {
            b.draw(g);
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Score: " + score, 10, 25);
        g.drawString("Lives: " + lives, SCREEN_WIDTH - 90, 25);
        
        if (gameOver) {
            drawCenteredMessage(g, "GAME OVER - Press R to restart");
        } else if (gameWon) {
            drawCenteredMessage(g, "YOU WIN! - Press R to restart");
        }
    }
    private void drawCenteredMessage(Graphics g, String message) {
        g.setFont(new Font("Arial", Font.BOLD, 22));
        int msgWidth = g.getFontMetrics(). stringWidth(message);
        g.setColor(Color.WHITE);
        g.drawString(message, (SCREEN_WIDTH - msgWidth) / 2, SCREEN_HEIGHT / 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (started && !gameOver && !gameWon) {
            move();
            checkCollisions();
        }
        repaint();
    }
    
    private void move() {
        if (leftPressed) {
            paddle.move(-paddle.speed);
        }
        if (rightPressed) {
            paddle.move(paddle.speed);
        }
        ball.move();
    }
    
    private void checkCollisions() {
        //WALLS
        if (ball.x <= 0 || ball.x + ball.diameter >= SCREEN_WIDTH) {
            ball.xVelocity *= -1;
        }
        if (ball.y <= 0) {
            ball.yVelocity *= -1;
        }
        
        if (ball.rect.intersects(paddle.rect) && ball.yVelocity > 0) {
            ball.yVelocity *= -1;
            int paddleCenter = paddle.x + paddle.width / 2;
            int ballCenter = ball.x + ball.diameter / 2;
            int diff = ballCenter - paddleCenter;
            ball.xVelocity = diff / 10;
            if (ball.xVelocity == 0) {
                ball.xVelocity = 1;
            }
        }
        
        boolean allBroken = true;
        for (Brick b : bricks) {
            if (b.visible) {
                allBroken = false;
                if (ball.rect.intersects(b.rect)) {
                    b.visible = false;
                    ball.yVelocity *= -1;
                    score += 10;
                    break;
                }
            }
        }
        
        if (allBroken) {
            gameWon = true;
        }
        
        if (ball.y > SCREEN_HEIGHT) {
            lives--;
            if (lives <= 0) {
                gameOver = true;
            } else {
                resetBall();
            }
        }
    }
    
    private void resetBall(){
        ball.x = SCREEN_WIDTH / 2 - 10;
        ball.y = SCREEN_HEIGHT - 80;
        ball.xVelocity = 3;
        ball.yVelocity = -4;
        ball.rect.setLocation(ball.x, ball.y);
        paddle.x = SCREEN_WIDTH / 2 - 50;
        paddle.rect.setLocation(paddle.x, paddle.y);
    }
    
    private void handleKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_R) {
            initGame();
        }
        if (key == KeyEvent.VK_SPACE && !started) {
            started = true;
        }
    }
    
    private void handleKeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
