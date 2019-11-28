package app.getInfo;

import java.util.ArrayList;

public class Data{

    public ArrayList<Integer> datos;
    
    public Data(){
        datos = new ArrayList<Integer>();
    }

    public void add(int index, int dato){
        if(datos.size()==index){
            this.datos.add(index, dato);
        }else{
            this.datos.set(index, dato);
        }
    }

    public int get(int index){
        return this.datos.get(index);
    }

    public int getSize(){
        return this.datos.size();
    }

    public String getString(){
        String temp = "";
        boolean first = false;
        for(int i = 0; i < datos.size();i++){
            if(datos.get(i) != 0 ){
                if(first){
                    temp += "+";
                }else{
                    first = true;
                }
                
                if(i==0){
                    temp += "" + datos.get(i);
                }else{
                    temp += "" + datos.get(i) + "x^" + (i);
                }
            }
        }
        return temp;
    }


    /**
     * @return the datos
     */
    public ArrayList<Integer> getDatos() {
        return datos;
    }


}