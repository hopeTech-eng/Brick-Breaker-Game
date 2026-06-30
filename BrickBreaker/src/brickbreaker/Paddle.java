/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author HOPEWELL
 */
public class Paddle {
    int x, y, width, height, speed, screenWidth;
    Rectangle rect;
    
    public Paddle(int x, int y, int width, int height, int speed, int screenWidth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height =  height;
        this.speed = speed;
        this.screenWidth = screenWidth;
        rect = new Rectangle(x, y, width, height);
    }
    
    public void move(int dx) {
        x += dx;
        if (x < 0) {
            x = 0;
        }
        if (x + width > screenWidth) {
            x = screenWidth - width;
        }
        rect.setLocation(x, y);
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, width, height, 10, 10);
    }
}
