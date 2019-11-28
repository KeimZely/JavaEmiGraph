package app.drawCanvas;

import javax.swing.JPanel;

import app.getInfo.Data;

import java.awt.*;
import java.util.ArrayList;

public class ColorInfo extends JPanel{

    Color color1, color2, color3;

    public ColorInfo(Color color1, Color color2, Color color3){
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int y = 0;
        int text = 25;
        int temp = 40 - text;
        g.setColor(color1);
        g.fillRect(10, y+10, 20, 20);
        y += text;
        g.setColor(Color.BLACK);
        g.drawString("Funcion Actual", 40, y);
        y += temp;
        g.setColor(color2);
        g.fillRect(10, y+10, 20, 20);
        y += text;
        g.setColor(Color.BLACK);
        g.drawString("Derivada", 40, y);
        y += temp;
        g.setColor(color3);
        g.fillRect(10, y+10, 20, 20);
        y += text;
        g.setColor(Color.BLACK);
        g.drawString("Area bajo la curva", 40, y);
    }
}