package Lab4;

public abstract class Algorithm {

    protected double learningRate;        //taxa d'aprenentatge (α) --> controla la mida del pas del gradient descent
    protected int maxIterations = 10000; //nombre màxim d'iteracions per evitar bucles infinits (opcional)

    //constructor --> inicialitza la taxa d'aprenentatge i el criteri de parada
    public Algorithm(double lr){
        this.learningRate = lr;         //assigna el valor de lr (learning rate)
    }

    //metode abs que implementaran les subclasses(GD/SGD)
    public abstract Model solve(Dataset ds);

    //gradient per a una única mostra(GD i SGD)
    //fórmula: x * (h_theta(x) - y)
    public Vector gradient(Record r, Model m) {
        Vector x = r.getInput().augment();
        double y = r.getOutput();

        //calcul error: (h_theta(x) - y)
        double diff = m.predict(x) - y;

        return x.multiplyScalar(diff);
    }
}

