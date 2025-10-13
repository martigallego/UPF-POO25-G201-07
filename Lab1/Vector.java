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

    public Vector multiplyElement(Vector other){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] * other.elems[i];
        }
        return new Vector(result);
    }

    public Vector divideElement(Vector other){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] / other.elems[i];
        }
        return new Vector(result);
    }

    public Vector multiplyScalar(double scalar){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] * scalar;
        }
        return new Vector(result);
    }
    
    public Vector divideScalar(double scalar){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = this.elems[i] / scalar;
        }
        return new Vector(result);
    }

    public Vector sqrt(Vector other){
        double[] result = new double[this.getDim()];
        for(int i = 0; i < this.getDim(); i++){
            result[i] = Math.sqrt(this.elems[i]);
        }
        return new Vector(result);
    }

    public double dot(Vector other){
        double result = 0.00;
        for(int i = 0; i < this.getDim(); i++){
            result += this.elems[i] * other.elems[i];
        }
        return result;
    }

    //ToString

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < getDim(); i++) {
            sb.append(elems[i]);
            if (i < getDim() - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    public double norm(){
        return Math.sqrt(this.dot(this));
    }
}
