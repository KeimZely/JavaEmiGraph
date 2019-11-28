package app.drawCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import app.drawCanvas.Canvas;
import app.getInfo.Data;
import app.getInfo.MathInfo;

public class DrawGraph extends JFrame implements ActionListener{

    private final String grafica = "Aceptar", derAc = "Derivar actual", intAc = "Integrar actual";
    private final Color 
    color1 = new Color(46,17,20,255), //COLOR DE LA FUNCION ACT
    color2 = new Color(80,27,29,255), //COLOR DE LA DERIVADA 100,72,92
    color3 = new Color(173,173,173,255), //AREA BAJO CURVA
    colorBG = new Color(130,220,187,255), //COLOR DE FONDO
    colorGRID = new Color(226,125,96,255), //COLOR DE LAS LINEAS
    colorC = new Color(47,47,162,255), //COLOR DE LOS EJES
    colorBGPalette = Color.PINK; //COLOR DONDE SE VEN LOS COLORES
    private boolean usingMoreDer;

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        boolean actualizarAreaBC = false;
        
        if(command == derAc){
            index = 0;
            System.out.println(indexOfDerivate);
            dataDer.add(indexOfDerivate+1, math.derivar(dataDer.get(indexOfDerivate)));
            dataDer.set(indexOfDerivate+1, math.derivar(dataDer.get(indexOfDerivate)));
            graph.setDatos(dataDer.get(indexOfDerivate), dataDer.get(indexOfDerivate+1));
            nxtInfo2.setText("f(x): " + dataDer.get(indexOfDerivate).getString());
            nxtInfo4.setText("f'(x): " + dataDer.get(indexOfDerivate+1).getString());
            if(dataDer.get(indexOfDerivate+1).getString() == ""){
                actualDer.setEnabled(false);
            }
            atras.setEnabled(false);
            adelante.setEnabled(false);
            usingMoreDer = true;
            indexOfDerivate++; 
            graph.repaint();
            actualizarAreaBC = true;
        }else if(command == intAc){
            index = 0;
            System.out.println(indexOfDerivate);
            indexOfDerivate--;
            if(indexOfDerivate == 0){
                graph.setDatos(data, dataDer.get(0));
                nxtInfo2.setText("f(x): " + data.getString());
                nxtInfo4.setText("f'(x): " + dataDer.get(0).getString());
            }else if(indexOfDerivate > 0){
                graph.setDatos(dataDer.get(indexOfDerivate-1), dataDer.get(indexOfDerivate));
                nxtInfo2.setText("f(x): " + dataDer.get(indexOfDerivate-1).getString());
                nxtInfo4.setText("f'(x): " + dataDer.get(indexOfDerivate).getString());
            }
            actualDer.setEnabled(true);
            actualizarAreaBC = true;
            graph.repaint();
        }else if(command == "Area bajo la curva(ON):"){
            cualAreaBc.setVisible(false);
            int a = Integer.parseInt(getALabel.getText());
            int b = Integer.parseInt(getBLabel.getText());
            graph.setAreaBajoCurva(a, b);
            verAreaBC.setText("Area bajo la curva(OFF):");
            if(indexOfDerivate == 0){
                cualAreaBc.setText("ABC: "+math.areaBajoCurva(data, a, b));
            }else{
                cualAreaBc.setText("ABC: "+math.areaBajoCurva(dataDer.get(indexOfDerivate-1), a, b));
            }
            repaint();
            
        }else if(command == "Area bajo la curva(OFF):"){
            cualAreaBc.setVisible(true);
            int a = Integer.parseInt(getALabel.getText());
            int b = Integer.parseInt(getBLabel.getText());
            graph.setAreaBajoCurva(a, b);
            verAreaBC.setText("Area bajo la curva(ON):");
            if(indexOfDerivate == 0){
                cualAreaBc.setText("ABC: "+math.areaBajoCurva(data, a, b));
            }else{
                cualAreaBc.setText("ABC: "+math.areaBajoCurva(dataDer.get(indexOfDerivate-1), a, b));
            }
            repaint();
        }else if(command == "<" || command == ">" || command == grafica){
            if(command == "<"){
                index--;
            }
            else if(command == ">"){
                index++;  
            } 
            else{
                if(usingMoreDer){
                    data = new Data();
                    dataDer = new ArrayList<Data>();
                    usingMoreDer = false;
                    indexOfDerivate = 0;
                }
                int temp;
                if(getA.getText().charAt(0) == '-'){
                    getA.setText(getA.getText().substring(1));
                    temp = Integer.parseInt(getA.getText());
                    temp = -temp;
                }else{
                    temp = Integer.parseInt(getA.getText());
                }
                data.add(index, temp);
                index++;
                nxtInfo2.setText("f(x): " + data.getString());
                actualizarAreaBC = true;
            }
            if(index>0){
                atras.setEnabled(true);
            }else{
                atras.setEnabled(false);
            }
            if(data.getSize() > index){
                adelante.setEnabled(true);
                getA.setText(""+data.get(index));
            }else{
                adelante.setEnabled(false);
                getA.setText("0");
            }
        }else if(command == "Zoom In (+)" || command == "Zoom Out (-)"){
            int temp = graph.getZoom();
            if(temp >= 10 && temp <= 100){
                if(command == "Zoom In (+)" && temp != 100)graph.setZoom(temp + 5);
                else if(command == "Zoom Out (-)" && temp != 10) graph.setZoom(temp - 5);
            }
            graph.repaint();
        }
        
        if(command == grafica){
            
            if(data.getSize() > 1){
                dataDer.add(0,math.derivar(data));
                dataDer.set(0,math.derivar(data));
                graph.setDatos(data, dataDer.get(0));
                nxtInfo4.setText("f'(x): " + dataDer.get(0).getString());
            }else{
                dataDer.add(math.derivar(data));
                graph.setDatos(data);
                nxtInfo4.setText("f'(x): 0");
            }
            if(dataDer.size() !=0){
                if(dataDer.get(indexOfDerivate).getString() == ""){
                    actualDer.setEnabled(false);
                }else{
                    actualDer.setEnabled(true);
                }
            }
            graph.repaint();
        }

        textA.setText("Ingrese A (n = " + (index+1) +"):");
        if(indexOfDerivate>0){
            intAct.setEnabled(true);
        }else{
            intAct.setEnabled(false);
        }

        if(actualizarAreaBC){
            int a = Integer.parseInt(getALabel.getText());
            int b = Integer.parseInt(getBLabel.getText());
            graph.setAreaBajoCurva(a, b);
            graph.setAreaBajoCurva(a, b);
            actualizarAreaBC = false;
            if(indexOfDerivate == 0){
                cualAreaBc.setText("ABC: "+math.areaBajoCurva(data, a, b));
            }else{
                cualAreaBc.setText("ABC: "+math.areaBajoCurva(dataDer.get(indexOfDerivate-1), a, b));
            }
            repaint();
        }
        
    }

    private int index, indexOfDerivate;
    private final int WIDTH = 1064;
    private final int HEIGHT = 720;
    JLabel nxtInfo,nxtInfo2,nxtInfo3,nxtInfo4,textA,areaBCLabel, cualAreaBc;
    boolean negativoBool;
    private Data data;
    private ArrayList<Data> dataDer;
    JButton aceptar, atras, adelante, verAreaBC, actualDer, intAct;
    JTextField getA, getALabel, getBLabel;
    Canvas graph;
    boolean canDer;
    private MathInfo math;

    public DrawGraph(){
        super("Grafica");
        this.setSize(WIDTH, HEIGHT);
        data = new Data();
        dataDer = new ArrayList<Data>();
        index = 0;
        indexOfDerivate = 0;
        negativoBool = true;
        math = new MathInfo();
        usingMoreDer = false;

        int usedWidth = HEIGHT;
        int leftWidth = WIDTH - HEIGHT;
        int actualY = 0;
        int itemsHeight = 24;

        graph = new Canvas();
        graph.setSize(usedWidth,HEIGHT);
        graph.setBackground(colorBG);
        graph.setColors(color1, color2, color3, colorGRID, colorC);

        JLabel textInfo = new JLabel("Ingrese los valores que ", SwingConstants.CENTER);
        textInfo.setSize(leftWidth, itemsHeight);
        textInfo.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;
        JLabel textInfo1 = new JLabel("desee(A) para la formula", SwingConstants.CENTER);
        textInfo1.setSize(leftWidth, itemsHeight);
        textInfo1.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;
        JLabel textInfo2 = new JLabel("desde n=1 hasta n=\u221E", SwingConstants.CENTER);
        textInfo2.setSize(leftWidth, itemsHeight);
        textInfo2.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;
        JLabel textInfo3 = new JLabel("f(x) = \u03A3 Ax^n ", SwingConstants.CENTER);
        textInfo3.setSize(leftWidth, itemsHeight);
        textInfo3.setLocation(usedWidth, actualY);
        actualY+=itemsHeight * 2;

        textA = new JLabel("Ingrese A (n = " + (index+1) +"):");
        textA.setSize(leftWidth, itemsHeight);
        textA.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        getA = new JTextField("0");
        getA.addKeyListener(new KeyAdapter()
        {
        public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') ||
                    (caracter > '9')) &&
                    (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter != '-'))
                {
                    e.consume();
                }
                if(caracter == '-'){
                    for(int i = 1; i < getA.getText().length() + 1;i++){
                        if(getA.getText().charAt(i) == '-')e.consume();
                    }
                }
            }
        });
        getA.setSize((leftWidth), itemsHeight);
        getA.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        int btnWidth = 50;

        aceptar = new JButton(grafica);
        aceptar.setSize((leftWidth-btnWidth*2), itemsHeight*2);
        aceptar.setLocation(usedWidth+btnWidth, actualY);

        atras = new JButton("<");
        atras.setEnabled(false);
        atras.setSize(btnWidth, itemsHeight*2);
        atras.setLocation(usedWidth, actualY);

        adelante = new JButton(">");
        adelante.setEnabled(false);
        adelante.setSize(btnWidth, itemsHeight*2);
        adelante.setLocation(usedWidth+aceptar.getWidth()+btnWidth, actualY);
        actualY+=itemsHeight * 3;

        nxtInfo = new JLabel("Función actual: ");
        nxtInfo.setSize(leftWidth, itemsHeight);
        nxtInfo.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        nxtInfo2 = new JLabel("f(x): 0");
        nxtInfo2.setSize(leftWidth, itemsHeight);
        nxtInfo2.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        nxtInfo3 = new JLabel("Derivada actual: ");
        nxtInfo3.setSize(leftWidth, itemsHeight);
        nxtInfo3.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        nxtInfo4 = new JLabel("f'(x): 0");
        nxtInfo4.setSize(leftWidth, itemsHeight);
        nxtInfo4.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        actualDer = new JButton(derAc);
        actualDer.setEnabled(false);
        actualDer.setSize(leftWidth/2, itemsHeight);
        actualDer.setLocation(usedWidth, actualY);
        intAct = new JButton(intAc);
        intAct.setEnabled(false);
        intAct.setSize(leftWidth/2, itemsHeight);
        intAct.setLocation(usedWidth+intAct.getWidth(), actualY);
        actualY+=itemsHeight * 2;

        areaBCLabel = new JLabel("AREA BAJO LA CURVA:", SwingConstants.CENTER);
        areaBCLabel.setSize(leftWidth, itemsHeight);
        areaBCLabel.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        JLabel areaALabel = new JLabel(" Valor a hasta valor b", SwingConstants.CENTER);
        areaALabel.setSize(leftWidth, itemsHeight);
        areaALabel.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        JLabel areaBLabel = new JLabel(" A ", SwingConstants.CENTER);
        areaBLabel.setSize(leftWidth, itemsHeight);
        areaBLabel.setLocation(usedWidth, actualY);

        getALabel = new JTextField("0");
        getALabel.addKeyListener(new KeyAdapter()
        {
        public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') ||
                    (caracter > '9')) &&
                    (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter != '-'))
                {
                    e.consume();
                }
                if(caracter == '-'){
                    for(int i = 1; i < getALabel.getText().length() + 1;i++){
                        if(getALabel.getText().charAt(i) == '-')e.consume();
                    }
                }
            }
        });
        getALabel.setSize((leftWidth/2 - 8), itemsHeight);
        getALabel.setLocation(usedWidth, actualY);

        getBLabel = new JTextField("0");
        getBLabel.addKeyListener(new KeyAdapter()
        {
        public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') ||
                    (caracter > '9')) &&
                    (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter != '-'))
                {
                    e.consume();
                }
                if(caracter == '-'){
                    for(int i = 1; i < getBLabel.getText().length() + 1;i++){
                        if(getBLabel.getText().charAt(i) == '-')e.consume();
                    }
                }
            }
        });
        getBLabel.setSize((leftWidth/2 -8), itemsHeight);
        getBLabel.setLocation(usedWidth+(leftWidth/2)+8, actualY);
        actualY+=itemsHeight;

        verAreaBC = new JButton("Area bajo la curva(OFF):");
        verAreaBC.setSize(leftWidth, itemsHeight);
        verAreaBC.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        cualAreaBc = new JLabel("");
        cualAreaBc.setVisible(false);
        cualAreaBc.setSize(leftWidth, itemsHeight);
        cualAreaBc.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        JButton zoomIn = new JButton("Zoom In (+)");
        zoomIn.setEnabled(true);
        zoomIn.setSize(leftWidth, itemsHeight);
        zoomIn.setLocation(usedWidth, actualY);
        actualY+=itemsHeight;

        JButton zoomOut = new JButton("Zoom Out (-)");
        zoomOut.setEnabled(true);
        zoomOut.setSize(leftWidth, itemsHeight);
        zoomOut.setLocation(usedWidth, actualY);

        actualY+=itemsHeight*2;

        ColorInfo colorInfo = new ColorInfo(color1, color2, color3);
        colorInfo.setLocation(usedWidth, actualY);
        colorInfo.setSize(leftWidth, HEIGHT - actualY);
        colorInfo.setBackground(colorBGPalette);

        aceptar.addActionListener(this);
        atras.addActionListener(this);
        adelante.addActionListener(this);
        actualDer.addActionListener(this);
        intAct.addActionListener(this);
        verAreaBC.addActionListener(this);
        zoomIn.addActionListener(this);
        zoomOut.addActionListener(this);

        this.setLayout(null);
        this.add(graph);
        this.add(textInfo);
        this.add(textInfo1);
        this.add(textInfo2);
        this.add(textInfo3);
        this.add(textA);
        this.add(getA);
        this.add(aceptar);
        this.add(atras);
        this.add(adelante);
        this.add(nxtInfo);
        this.add(nxtInfo2);
        this.add(nxtInfo3);
        this.add(nxtInfo4);
        this.add(actualDer);
        this.add(intAct);
        this.add(areaBCLabel);
        this.add(areaALabel);
        this.add(areaBLabel);
        this.add(getALabel);
        this.add(getBLabel);
        this.add(verAreaBC);
        this.add(cualAreaBc);
        this.add(zoomIn);
        this.add(zoomOut);
        this.add(colorInfo);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}