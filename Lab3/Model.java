package Lab3;

public class Model {
    private Vector params;

    // Constructor: rep la dimensió de l'input sense bias (afegirem +1 per al bias)
    public Model(int dimension){
        //es crea un vector de mida dim ple de zeros
        this.params = new Vector(dimension, 0);
    }
    //getter per obtenir els parametres actuals del model
    public Vector getParams(){
        return params;
    }

    //metode per fer una prediccio a partir d'un vector d'entrada v
    public double predict(Vector v){
    // v ja ve augmentat des d'Algorithm
    return params.dot(v);
}

    //metode per actualitzar els parametres del model segons un vector de gradient i una taxa d'aprenentatge
    public void update(Vector v, double rate){
        //multipliquem el vector 'v' pel factor d'aprenentatge (rate)
        Vector step = v.multiplyScalar(rate);
        //restem aquest pas dels paràmetres actuals: params = params - rate * v
        this.params = this.params.subtract(step);
    }
    
}
