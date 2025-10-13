package Lab1;
public class Vector {
    private double elems[];


    //Constructors

    public Vector(double[] elems) {
        this.elems = elems.clone(); 
    }

    public Vector(int dim, double val){
        this.elems = new double[dim];
        for (int i = 0; i < dim; i++){
            this.elems[i] = val;
        }
    }

    public int getDim(){
        return elems.length;
    }

    //Methods

    public Vector add(Vector other){
        double[] result = new double[this.getDim()];
        for (int i = 0; i < this.getDim(); i++) {
            result[i] = this.elems[i] + other.elems[i];
        }
        return new Vector(result);
    }

    public Vector subtract(Vector other){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] - other.elems[i];
        }
        return new Vector(result);
    }

    public Vector multiply(Vector other){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] * other.elems[i];
        }
        return new Vector(result);
    }

    public Vector divide(Vector other){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] / other.elems[i];
        }
        return new Vector(result);
    }

    public Vector multiply(double scalar){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] * scalar;
        }
        return new Vector(result);
    }
    
}
