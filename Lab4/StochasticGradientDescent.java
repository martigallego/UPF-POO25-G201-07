package Lab4; 

import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;       //gestionar llistes de Record --> minibatch
import java.util.Random;       //numeros aleatoris i barrejar les dades

public class StochasticGradientDescent extends Algorithm {
    private int batchSize; //mida minibatch (k)
    private int iterations; //num iteracions (epoques) a realitzar
    private Random random; //generador numeros aleatoris --> selecció del batch

    //constructor
    public StochasticGradientDescent(double lr, int bs, int it) {
        super(lr); 
        this.batchSize = bs; 
        this.iterations = it; 
        this.random = new Random(); 
    }
    
    //metode: calcular el gradient basat en un minibatch (SGD)
    public Vector stochasticGradient(Dataset ds, Model m) {
    //fem una còpia de la llista per no modificar l'original del dataset
        List<Record> fullCopy = new ArrayList<>(ds.getData());
        Collections.shuffle(fullCopy, random);

        int actualBatchSize = Math.min(batchSize, fullCopy.size());
        List<Record> miniBatch = fullCopy.subList(0, actualBatchSize);

        Vector g = new Vector(m.getParams().getDim(), 0);

        //calcula el gradient sobre les mostres TRANSFORMADES
        for (Record r : miniBatch) {
        Record tr = ds.transform(r);    // <-- IMPORTANT: transformar la mostra abans del gradient
        g = g.add(gradient(tr, m));
        }

        if (actualBatchSize > 0) {
            g = g.divideScalar(actualBatchSize);
        }
        g = g.multiplyScalar(2.0);

        return g;
    }


    @Override
    public Model solve(Dataset ds) {
        int inputDim = ds.getDim(); //obté dimensió d'entrada
        Model m = new Model(inputDim + 1);  

        // Bucle per al nombre d'iteracions predefinit (criteri de parada de SGD)
        for (int i = 0; i < iterations; i++) {
            Vector g = stochasticGradient(ds, m); //calcula SGD
            m.update(g, learningRate);
        }
        
        System.out.println("SGD Finalitzat després de " + iterations + " iteracions.");
        return m; 
    }
}