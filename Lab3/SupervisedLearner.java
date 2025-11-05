package Lab3;

public class SupervisedLearner {
    private Algorithm algorithm;   // Algorisme de descens de gradient
    private Dataset dataset;       // Dades d'entrenament
    private Model model;           // Model entrenat (paràmetres resultants)

    //Constructor

    public SupervisedLearner(Algorithm a, Dataset d){
        this.algorithm = a;
        this.dataset = d;
        this.model = null;
    }

    //Metodes

    public void solve() {
        // Usa algorithm() per entrenar el model amb el dataset donats
        this.model = algorithm.solve(dataset);
    }


    //Fa una prediccio per a un nou vector 
    public double predict(Vector v){
        Vector x = v.augment();
        return model.predict(x);
    } 

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SupervisedLearner:\n");
        sb.append("  Algorithm: ").append(algorithm.getClass().getSimpleName()).append("\n");
        sb.append("  Dataset: dim=").append(dataset.getDim())
          .append(", n=").append(dataset.getData().size()).append("\n");
        if (model != null) {
            sb.append("  Model: ").append(model.toString());
        } else {
            sb.append("  Model: [no entrenat]");
        }
        return sb.toString();
    }
}
