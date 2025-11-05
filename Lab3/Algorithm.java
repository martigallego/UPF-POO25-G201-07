package Lab3;

public class Algorithm {

    private double learningRate;
    private double stoppingCriterion;

    //Constructor
    public Algorithm(double lr, double sc){
        this.learningRate = lr;
        this.stoppingCriterion = sc;  
    }

    //Metodes
    public Vector gradient(Dataset ds, Model m) {
        Vector g = new Vector(m.getParams().getDim(), 0); //Crea un nou vector amb la maeixa dim que els params del Model i l'omple de 0

        for (Record r : ds.getData()) {
            Vector x = r.getInput().augment();
            double y = r.getOutput();
            double diff = m.predict(x) - y;
            g = g.add(x.multiplyScalar(diff));
        }

        g = g.divideScalar(ds.getData().size());
        g = g.multiplyScalar(2.0);
        return g;
    }

    public Model solve(Dataset ds) {
        int inputDim = ds.getDim();           // Nombre de variables d'entrada
        Model m = new Model(inputDim + 1);    // +1 per al terme de bias
        Vector g = gradient(ds, m);           // Calcula el primer gradient

        // Itera fins que el gradient sigui prou petit 
        while (g.norm() > stoppingCriterion) {
            m.update(g, learningRate);
            g = gradient(ds, m);
        }

        return m;
    }

    

}
