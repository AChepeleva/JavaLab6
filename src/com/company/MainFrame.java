package com.company;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame implements KeyListener {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private Field field = new Field();

    public MainFrame() {
        super("Программирование и синхронизация потоков");
        this.setSize(700, 500);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 700) / 2, (kit.getScreenSize().height - 500) / 2);
        this.setExtendedState(6);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.addBall();
                if (!MainFrame.this.pauseMenuItem.isEnabled() && !MainFrame.this.resumeMenuItem.isEnabled()) {
                    MainFrame.this.pauseMenuItem.setEnabled(true);
                }

            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.pause();
                MainFrame.this.pauseMenuItem.setEnabled(false);
                MainFrame.this.resumeMenuItem.setEnabled(true);
            }
        };
        this.pauseMenuItem = controlMenu.add(pauseAction);
        this.pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.resume();
                MainFrame.this.pauseMenuItem.setEnabled(true);
                MainFrame.this.resumeMenuItem.setEnabled(false);
            }
        };
        this.resumeMenuItem = controlMenu.add(resumeAction);
        this.resumeMenuItem.setEnabled(false);
        this.getContentPane().add(this.field, "Center");
        this.addKeyListener(this);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        Iterator var2;
        BouncingBall ball;
        if (e.getKeyCode() == 107) {
            for(var2 = this.field.balls.iterator(); var2.hasNext(); ball.speed += 2) {
                ball = (BouncingBall)var2.next();
            }
        }

        if (e.getKeyCode() == 109) {
            for(var2 = this.field.balls.iterator(); var2.hasNext(); ball.speed -= 2) {
                ball = (BouncingBall)var2.next();
            }
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}

