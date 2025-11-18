package Lab4; 

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
        List<Record> fullData = ds.getData();    //llista de dades completa del Dataset
        Collections.shuffle(fullData, random);   //barrejar dades per assegurar aleatorietat en el batch

        int actualBatchSize = Math.min(batchSize, fullData.size());   //mida real del batch, agafant el mínim entre batchSize i la mida total del dataset

        //minibatch --> subllista des de inici (0) fins a la mida del batch
        List<Record> miniBatch = fullData.subList(0, actualBatchSize);
        Vector g = new Vector(m.getParams().getDim(), 0);  //inicialitza el vector gradient (g) amb zeros i la dimensió del model


        //suma els gradients per a cada mostra del minibatch 
        for (Record r : miniBatch) {
            //gradient d'una sola mostra
            g = g.add(gradient(r, m));
        }
        //divideix entre la mida del batch 
        g = g.divideScalar(actualBatchSize);
        // x 2(factor que prové de la derivada de l'Error Quadràtic Mitjà
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