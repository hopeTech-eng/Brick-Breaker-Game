/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import javax.swing.JFrame;

/**
 *
 * @author HOPEWELL
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Brick Breaker");
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        panel.requestFocusInWindow();
    }
}
