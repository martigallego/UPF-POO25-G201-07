package Lab4;
public class GradientDescent extends Algorithm {
    private double stoppingCriterion;

    //constructor
    public GradientDescent(double lr, double sc) {
        super(lr); // Inicialitza learningRate
        this.stoppingCriterion = sc;
    }

    /**
     * Calcula el gradient complet del dataset. 
     * És la mitjana de tots els gradients individuals.
     * @param ds El conjunt de dades.
     * @param m El model actual (amb els paràmetres Theta).
     * @return El vector gradient mitjà.
     */
    public Vector fullGradient(Dataset ds, Model m) {
        // Inicialitza el vector gradient (g) amb zeros i la dimensió del model
        Vector g = new Vector(m.getParams().getDim(), 0);

        // Suma els gradients de cada mostra del dataset (la suma de l'error)
        for (Record r : ds.getData()) {
            // Utilitza el mètode gradient(Record, Model) definit a la classe Algorithm
            g = g.add(gradient(r, m));
        }

        // Divideix entre el nombre total de mostres (m) per obtenir la mitjana
        g = g.divideScalar(ds.getData().size());
        
        // Multiplica per 2.0 (factor de la derivada del MSE: E(θ) = 1/2m * Σ(h(x) - y)^2)
        g = g.multiplyScalar(2.0); 

        return g;
    }

    /**
     * Entrena el model usant Descens de Gradient clàssic (Full Batch).
     * @param ds El conjunt de dades a entrenar.
     * @return El model (Model) entrenat.
     */
    @Override
    public Model solve(Dataset ds) {
        int inputDim = ds.getDim();
        // Inicialitza el model (mida de les features + 1 pel bias)
        Model m = new Model(inputDim + 1);
        
        Vector g = fullGradient(ds, m); // Calcula el primer gradient
        int iterations = 0;
        
        // Bucle mentre la norma del gradient sigui superior al criteri de parada 
        // I no s'hagi excedit el màxim d'iteracions (definit a Algorithm)
        while (g.norm() > stoppingCriterion && iterations < maxIterations) {
            // Actualització dels paràmetres: θ = θ - α * g
            m.update(g, learningRate);

            // Recalcula el gradient amb els nous paràmetres
            g = fullGradient(ds, m);
            iterations++;
        }
        
        System.out.println("GD Finalitzat després de " + iterations + " iteracions. Norma final del gradient: " + g.norm());
        return m;
    }
}