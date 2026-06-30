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
public class Brick {
    
    int x, y, width, height;
    boolean visible;
    Color color;
    Rectangle rect;
    
    public Brick(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width =  width;
        this.height = height;
        this.color = color;
        this.visible = true;
        rect = new Rectangle(x, y, width, height);
    }
    
    public void draw(Graphics g) {
        if(visible) {
            g.setColor(color) ;
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
}
