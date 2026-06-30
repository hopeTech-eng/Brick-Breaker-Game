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
public class Ball {
    int x, y, diameter;
    int xVelocity, yVelocity;
    Rectangle rect;
    
    public Ball(int x, int y, int diameter, int xVelocity, int yVelocity) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        rect = new Rectangle(x, y, diameter, diameter);
    }
    
    public void move() {
        x += xVelocity;
        y += yVelocity;
        rect.setLocation(x, y);
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, diameter, diameter);
    }
}
