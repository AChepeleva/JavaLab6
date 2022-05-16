package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel {
    private boolean paused;
    protected ArrayList<BouncingBall> balls = new ArrayList(10);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            Field.this.repaint();
        }
    });

    public Field() {
        this.setBackground(Color.WHITE);
        this.repaintTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D)g;
        Iterator var3 = this.balls.iterator();

        while(var3.hasNext()) {
            BouncingBall ball = (BouncingBall)var3.next();
            ball.paint(canvas);
        }

    }

    public void addBall() {
        this.balls.add(new BouncingBall(this));
    }

    public synchronized void pause() {
        this.paused = true;
    }

    public synchronized void resume() {
        this.paused = false;
        this.notifyAll();
    }

    public synchronized void canMove(BouncingBall ball) throws InterruptedException {
        if (this.paused) {
            this.wait();
        }

    }
}