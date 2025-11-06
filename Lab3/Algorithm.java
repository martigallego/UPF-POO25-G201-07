package Lab3;

public class Algorithm {

    private double learningRate;        //taxa d'aprenentatge (α) --> controla la mida del pas del gradient descent
    private double stoppingCriterion;   //criteri de parada: quan el gradient és prou petit, l'entrenament s'atura
    private int maxIterations = 10000; //nombre màxim d'iteracions per evitar bucles infinits (opcional)

    //constructor --> inicialitza la taxa d'aprenentatge i el criteri de parada
    public Algorithm(double lr, double sc){
        this.learningRate = lr;         // Assigna el valor de lr (learning rate)
        this.stoppingCriterion = sc;    // Assigna el valor de sc (stopping criterion)
    }

    // metode: gradient --> calcula el gradient del model donat el dataset actual
    //gradient --> indica en quina direccio s'han d'ajustar els pesos per minimitzar l'error
    public Vector gradient(Dataset ds, Model m) {
        //crea un vector (g) de la mateixa dimensio que els parametres del model (theta) i l'inicialitza amb zeros
        Vector g = new Vector(m.getParams().getDim(), 0);

        //recorrem tots els registres (Record) del dataset
        for (Record r : ds.getData()) {
            //vector d'entrada(x) i li afegim el bias (augment)
            Vector x = r.getInput().augment();
            //valor real de sortida (y)
            double y = r.getOutput();

            //calcula la diferència entre la predicció i el valor real: diff = y_pred - y_real
            double diff = m.predict(x) - y;

            //actualitzar el gradient sumant x * diff
            //acumula l'error multiplicat pel vector d'entrada
            g = g.add(x.multiplyScalar(diff));
        }

        //dividir entre el nombre total de mostres per obtenir la mitjana del gradient
        g = g.divideScalar(ds.getData().size());

        //mutliplicar  x2 (prové de la derivada del MSE)
        g = g.multiplyScalar(2.0);

        //retorna el vector gradient final
        return g;
    }

    //metode: solve --> entrenar el model usant descens de gradient fins que el gradient sigui prou petit
    public Model solve(Dataset ds) {
        int inputDim = ds.getDim();         //dimensió d'entrada (nombre de features d'entrada)
        Model m = new Model(inputDim +1);  //nou model amb inputDim 
        Vector g = gradient(ds, m);     //calcula el primer gradient del model inicial (tots els pesos = 0)
        
        //bucle descens per gradient
        int iterations = 0;
        while (g.norm() > stoppingCriterion && iterations < maxIterations) {
            // θ = θ - α * g
            m.update(g, learningRate);

            //calcular el gradient amb els nous paràmetres
            g = gradient(ds, m);
            iterations++;
        }

        //quan el gradient és petit o s'ha arribat al màxim d'iteracions --> retorna el model entrenat
        
        return m;
    }

}
