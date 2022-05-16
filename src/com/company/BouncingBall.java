package com.company;

import java.awt.Color;
import java.awt.Graphics2D;

public class BouncingBall implements Runnable {
    private static final int MAX_RADIUS = 40;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_SPEED = 15;
    private Field field;
    private int radius;
    private Color color;
    private double x;
    private double y;
    protected int speed;
    private double speedX;
    private double speedY;

    public BouncingBall(Field field) {
        this.field = field;
        this.radius = (new Double(Math.random() * 37.0D)).intValue() + 3;
        this.speed = (new Double((double)Math.round((float)(75 / this.radius)))).intValue();
        if (this.speed > 15) {
            this.speed = 15;
        }

        double angle = Math.random() * 2.0D * 3.141592653589793D;
        this.speedX = 3.0D * Math.cos(angle);
        this.speedY = 3.0D * Math.sin(angle);
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        this.x = Math.random() * (field.getSize().getWidth() - (double)(2 * this.radius)) + (double)this.radius;
        this.y = Math.random() * (field.getSize().getHeight() - (double)(2 * this.radius)) + (double)this.radius;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    public void run() {
        try {
            while(true) {
                this.field.canMove(this);
                if (this.x + this.speedX <= (double)this.radius) {
                    this.speedX = -this.speedX;
                    this.x = (double)this.radius;
                } else if (this.x + this.speedX >= (double)(this.field.getWidth() - this.radius)) {
                    this.speedX = -this.speedX;
                    this.x = (double)(new Double((double)(this.field.getWidth() - this.radius))).intValue();
                } else if (this.y + this.speedY <= (double)this.radius) {
                    this.speedY = -this.speedY;
                    this.y = (double)this.radius;
                } else if (this.y + this.speedY >= (double)(this.field.getHeight() - this.radius)) {
                    this.speedY = -this.speedY;
                    this.y = (double)(new Double((double)(this.field.getHeight() - this.radius))).intValue();
                } else {
                    this.x += this.speedX;
                    this.y += this.speedY;
                }

                Thread.sleep((long)(16 - this.speed));
            }
        } catch (InterruptedException var2) {
        }
    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(this.color);
        canvas.setPaint(this.color);
        java.awt.geom.Ellipse2D.Double ball = new java.awt.geom.Ellipse2D.Double(this.x - (double)this.radius, this.y - (double)this.radius, (double)(2 * this.radius), (double)(2 * this.radius));
        canvas.draw(ball);
        canvas.fill(ball);
    }
}
