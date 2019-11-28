package app;

import javax.swing.SwingUtilities;

import app.drawCanvas.DrawGraph;
import app.getInfo.*;

public class App {
    public static void main(String[] args) throws Exception {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    DrawGraph graph = new DrawGraph();
                }
            });

    }
}