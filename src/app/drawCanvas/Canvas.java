package app.drawCanvas;

import javax.swing.JPanel;

import app.getInfo.Data;

import java.awt.*;
import java.util.ArrayList;


public class Canvas extends JPanel{

    private Data[] datos;
    private boolean canGraph;
    private int centerX, centerY, howManyGraphs;
    private Color color1, color2, color3, colorGRID, colorC;
    private boolean areaBajoCurva;
    private int abcA, abcB;

    private int zoom;

    public Canvas(){
        datos = new Data[3];
        canGraph = false;
        howManyGraphs = 0;
        zoom = 20;
        areaBajoCurva = false;
        abcA = 0;
        abcB = 0;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintGrid(g);
        paintGraph(g);
    }

    private void paintGraph(Graphics g){
        if(canGraph){
            // ArrayList<Integer> data2 = datos[0].getDatos() */;
            for(int l = 0; l < howManyGraphs; l++){
                ArrayList<Integer> data = datos[l].getDatos();
                int dot = 0;
                int dotTemp = 0;
                
                if(l==1 && data.size() == 0){

                }else{
                    for(int i = 0; i < this.getWidth() - centerX;i++){/* DERECHA */
                        // OBTENIENDO Y, F(x) = 2x^2 + 4x + 5
                        
                        for(int j = 0; j <data.size();j++){
                            if(i == 0){
                                dotTemp = data.get(0) * zoom;
                            }
                            if(j==0){
                                dot = (data.get(j)) * zoom;
                            }else if(j==1){
                                dot += (((data.get(j)) * (Math.pow(i, j))));
                            }else{
                                dot += ((data.get(j)) * (Math.pow(((double)i/(double)zoom), j)) * zoom);
                            }       
                        }
                        if(l == 0 && areaBajoCurva && i>(abcA * zoom) && i<=(abcB * zoom)){
                            g.setColor(color3);
                            g.drawLine(centerX + i, centerY-1, centerX + i, centerY-dot);
                        }
                        if(l==0){
                            g.setColor(color1);
                        }else{
                            g.setColor(color2);
                        }
                        g.drawLine(centerX + i, (centerY - dotTemp), centerX + i, (centerY - dot));
                        dotTemp = dot;
                        dot = 0;
                    }
                    for(int i = centerX; i >= 0; i--){/* IZQUIERDA */
                        for(int j = 0; j <data.size();j++){
                            if(i==0){
                                dotTemp = data.get(0) * zoom;
                            }
                            if(j==0){
                                // if(data.size() == 1 || (data.size()>3 && (data.size() % 4 == 0 || data.size() % 4 == 1))){
                                dot = (data.get(j)) * zoom;       
                            }else if(j==1){
                                dot -= ((data.get(j)) * (Math.pow(i, j)));
                            }else{
                                if(j % 2 == 0){
                                    dot += (data.get(j)) * (Math.pow(((double)i/(double)zoom), j)) * zoom;
                                }else{
                                    dot -= (data.get(j)) * (Math.pow(((double)i/(double)zoom), j)) * zoom;
                                }
                            }
                            
                        }
                        if(l==0 && areaBajoCurva && (i>=(centerX + (abcA * zoom))) && i<(centerX + (abcB * zoom))){
                            g.setColor(color3);
                            g.drawLine(centerX + i, centerY-1, centerX + i, centerY-dot);
                        }
                        if(l==0){
                            g.setColor(color1);
                        }else{
                            g.setColor(color2);
                        }
                        g.drawLine(centerX - i, (centerY - dotTemp), centerX - i, (centerY - dot));
                        dotTemp = dot;
                        dot = 0;
                    }
                }
            }
        }
    }

    public void paintGrid(Graphics g){
        g.setColor(colorGRID);
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        for(int i = centerX; i < this.getWidth();i+=zoom){
            g.drawLine(i, 0, i, this.getHeight());
        }
        for(int i = centerX; i >= 0; i-=zoom){
            g.drawLine(i, 0, i, this.getHeight());
        }
        for(int i = centerY; i < this.getHeight();i+=zoom){
            g.drawLine(0, i, this.getHeight(), i);
        }
        for(int i = centerY; i >= 0;i-=zoom){
            g.drawLine(0, i, this.getHeight(), i);
        }
        g.setColor(colorC);

        g.drawLine(centerX, 0, centerX, this.getHeight());
        g.drawLine(0, centerY, this.getWidth(), centerY);
    }

    public void setDatos(Data datosFuncion, Data datosDerivada) {
        this.datos[0] = datosFuncion;
        this.datos[1] = datosDerivada;
        // this.datos[2] = datosIntegral; */
        canGraph = true;
        howManyGraphs = 2;
    }

    public void setDatos(Data datosFuncion) {
        this.datos[0] = datosFuncion;
        canGraph = true;
        howManyGraphs = 1;
    }

    /**
     * @param zoom the zoom to set
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * @return the zoom
     */
    public int getZoom() {
        return zoom;
    }

    public void setColors(Color color1, Color color2, Color color3, Color colorGRID, Color colorC){
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.colorGRID = colorGRID;
        this.colorC = colorC;
    }

    /**
     * @param areaBajoCurva the areaBajoCurva to set
     */
    public void setAreaBajoCurva(int abcA, int abcB) {
        this.areaBajoCurva = !this.areaBajoCurva;
        this.abcA = abcA;
        this.abcB = abcB;
    }
    
}