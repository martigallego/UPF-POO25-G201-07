package Lab4;
public class GradientDescent extends Algorithm {
    private double stoppingCriterion;

    //constructor
    public GradientDescent(double lr, double sc) {
        super(lr); // Inicialitza learningRate
        this.stoppingCriterion = sc;
    }

    public Vector fullGradient(Dataset ds, Model m) {
        //inicialitza el vector gradient (g) amb zeros i la dimensió del model
        Vector g = new Vector(m.getParams().getDim(), 0);

        //suma gradients de cada mostra del dataset (la suma de el error)
        for (Record r : ds.getData()) {
            g = g.add(gradient(r, m));
        }
        //obtenir la mitjana
        g = g.divideScalar(ds.getData().size());
        
        //x 2(factor de la derivada del MSE)
        g = g.multiplyScalar(2.0); 

        return g;
    }
    @Override
    public Model solve(Dataset ds) {
        int inputDim = ds.getDim();
        Model m = new Model(inputDim + 1);
        Vector g = fullGradient(ds, m); //calcula el primer gradient
        int iterations = 0;
        
        //bucle: mentre la norma del gradient sigui superior al criteri de parada 
        while (g.norm() > stoppingCriterion && iterations < maxIterations) {
            m.update(g, learningRate);   //actualitzacio parametres: θ = θ - α * g
            g = fullGradient(ds, m);  //recalcula el gradient amb els nous param

            iterations++;
        }
        
        System.out.println("GD Finalitzat després de " + iterations + " iteracions. Norma final del gradient: " + g.norm());
        return m;
    }
}