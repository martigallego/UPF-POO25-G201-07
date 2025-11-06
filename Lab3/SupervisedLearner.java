package Lab3;

public class SupervisedLearner {
    private Algorithm algorithm;   //algoritme d'aprenentatge (descens de gradient)
    private Dataset dataset;       //dades d'entrenament (inputs + sortides reals)
    private Model model;           // Model entrenat --> (pesos) després d'entrenar

    //constructor
    public SupervisedLearner(Algorithm a, Dataset d){
        this.algorithm = a;
        this.dataset = d;
        this.model = null; //cap model entrenat
    }


    //metode principal per entrenar el model
    public void solve() {
        this.model = algorithm.solve(dataset); //passem un dataset --> retorna model entrenat
    }

    //metode predicció sobre un nou vector d'entrada
    public double predict(Vector v){
        Vector x = v.augment();     //augmentem el vector per afegir el bias (1 al principi)
        return model.predict(x);    //retorna la prediccio del model ja entrenat
    } 

    public String toString() {
        StringBuilder sb = new StringBuilder();

        //info SupervisedLearner
        sb.append("SupervisedLearner:\n");

        //nom de la clase del algoritme utilitzat
        sb.append("  Algorithm: ").append(algorithm.getClass().getSimpleName()).append("\n");

        //mostrar info del dataset: dimensió dels vectors i nombre de mostres
        sb.append("  Dataset: dim=").append(dataset.getDim())
          .append(", n=").append(dataset.getData().size()).append("\n");

        //si el model ja ha estat entrenat --> mostrem els seus paràmetres
        if (model != null) {
            sb.append("  Model: ").append(model.toString());
        } 
        //si el model no esta entrenat --> indicar
        else {
            sb.append("  Model: [no entrenat]");
        }

        //retornat text complet
        return sb.toString();
    }
}
