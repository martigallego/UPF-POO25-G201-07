package Lab1;
public class Vector {
    private double elems[];


    //Constructors

    public Vector(double[] elements) {
        this.elements = elements.clone(); 
    }

    public Vector(int dim, double val){
        this.elems = new double[dim];
        for (int i = 0; i < dim; i++){
            this.elems[i] = val;
        }
    }

    public getDim(){
        return elems.length;
    }

    public Vector add(Vector other){
        double[] result = new double[this.elems.length];
        for (int i = 0; i < this.elems.length; i++) {
            result[i] = this.elems[i] + other.elems[i];
        }
        return new Vector(result);
    }

    public Vector substract(Vector other){
        
    }
    
}
