import java.util.ArrayList;

/**
 * Created by kevinandresortizmerchan on 1/27/17.
 */
public class Hill_climbing_simple {
    private  float  partialValueMemory;
    private  float  sizeCache;
    private  float bd;
    private  float cd;
    private ArrayList<Float> listHit = new ArrayList<Float>();
    private ArrayList<Float> listMiss = new ArrayList<Float>();
    private int frequency;

    public Hill_climbing_simple(float bd, float cd, float sizeCache, ArrayList<Float> listHit, ArrayList<Float> listMiss,float m,int frequency){
        this.partialValueMemory = m;
        this.sizeCache = sizeCache;
        this.bd = bd;
        this.cd = cd;
        this.listHit = listHit;
        this.listMiss = listMiss;
        this.sizeCache = sizeCache;
        this.frequency = frequency;
    }


    public float algorithm(){
        float valueEAD ;
        float temporalValue = 0;
        float valueMemory = 0;
        float valuepartialValue;
        valuepartialValue = this.partialValueMemory;
        for (int i = 0; i < listHit.size(); i++) {
            valueEAD = EAD(this.listHit.get(i),this.listMiss.get(i));
            valueMemory = this.frequency * valueEAD;
            valuepartialValue = valuepartialValue + valueMemory;
            /*
            * Condicion para obtener el tamano optimo de la memoria de un workload se basa en la siguiente condicion
            * Suma de los valores parciales de memoria sea menos o igual al tamano de la cache en total, y que el valor
            * actual sea mayor o igual al valor pasado de la memoria obtenida
            * */
            if (valueMemory < temporalValue && valuepartialValue <= this.sizeCache &&  valueMemory >= this.partialValueMemory ){
                return temporalValue;

            }
            temporalValue = valueMemory;
            valuepartialValue = this.partialValueMemory;
        }
        return temporalValue;

    }

    public float EAD(float hit, float miss){
        return (hit*this.cd + miss*this.bd);
    }
}
