package app.getInfo;

import app.getInfo.Data;

public class MathInfo{

    public MathInfo(){

    }

    public Data derivar(Data data){
        Data temp = new Data();
        for(int i = 1; i < data.getSize();i++){
            temp.add(i-1, data.get(i)*i);
        }
        return temp;
    }

    public double areaBajoCurva(Data data, int a, int b){
        double tempA = 0.0, tempB = 0.0;
        for(int i = 0; i < data.getSize();i++){
            tempA += (double)((double)data.get(i)  / (double)(i+1)) * (Math.pow(a, (double)(i+1))) ;
            tempB += (double)((double)data.get(i)  / (double)(i+1)) * (Math.pow(b, (double)(i+1))) ;
        }
        return tempB - tempA;
    }
}