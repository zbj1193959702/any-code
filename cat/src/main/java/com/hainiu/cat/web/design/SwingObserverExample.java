package com.hainiu.cat.web.design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * create by biji.zhao on 2020/12/10
 */
public class SwingObserverExample {

    private JFrame jFrame;

    public static void main(String[] args) {
        SwingObserverExample observerExample = new SwingObserverExample();
        observerExample.go();
    }

    private void go() {
        jFrame = new JFrame();

        JButton jButton = new JButton("should i do it ?");
        jButton.addActionListener(new AngelListener());
        jButton.addActionListener(new DevilListener());

        jFrame.getContentPane().add(BorderLayout.CENTER, jButton);
    }

    static class AngelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("do not it ,you might regret it!");
        }
    }

    static class DevilListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("come on ,do it");
        }
    }
}
