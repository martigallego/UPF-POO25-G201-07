package Lab4;

public class TestLearner {
    public static void main(String[] args) {
        //TEST 1: Crear RawDataset i verificar estadístiques
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("TEST 1: RawDataset - Estadístiques");
        System.out.println("═══════════════════════════════════════════════════════");
        
        //inicialitzar raw dataset amb 2 dimensions
        RawDataset raw = new RawDataset(2);
        //afegir records al dataset
        raw.addRecord(new Vector(new double[]{1.0, 2.0}), 5.0);
        raw.addRecord(new Vector(new double[]{2.0, 3.0}), 7.0);
        raw.addRecord(new Vector(new double[]{3.0, 4.0}), 9.0);
        raw.addRecord(new Vector(new double[]{4.0, 5.0}), 11.0);
        raw.addRecord(new Vector(new double[]{5.0, 6.0}), 13.0);

        //mostrar informació del dataset
        System.out.println("Dataset creat amb " + raw.getData().size() + " records");
        System.out.println("Mitjana input: " + raw.meanInput());
        System.out.println("Std input: " + raw.stdInput());
        System.out.println("Mitjana output: " + raw.meanOutput());
        System.out.println("Std output: " + raw.stdOutput());
        
        //verificar que transform i output no modifiquen res
        Record testRecord = new Record(new Vector(new double[]{2.5, 3.5}), 8.0);
        Record transformed = raw.transform(testRecord);
        System.out.println("\nTest transform (ha de ser igual):");
        System.out.println("  Original: " + testRecord);
        System.out.println("  Transformat: " + transformed);
        System.out.println("  Output 10.0 → " + raw.output(10.0) + " (ha de ser 10.0)");
        
        //TEST 2: RawDataset + GradientDescent --> entrenament amb gradient descent en dades raw
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 2: RawDataset + GradientDescent");
        System.out.println("═══════════════════════════════════════════════════════");
        
        // inicialitzar gradient descent amb learning rate i criteri d'aturada
        GradientDescent gd = new GradientDescent(0.01, 0.001);
        SupervisedLearner learner1 = new SupervisedLearner(gd, raw);
        
        System.out.println("Entrenant model amb GradientDescent...");
        System.out.println("  Learning rate: 0.01");
        System.out.println("  Stopping criterion: 0.001");

        //entrenar model
        learner1.solve();
        System.out.println("\n" + learner1);
        
        //provar prediccions
        System.out.println("\nPrediccions:");
        Vector test1 = new Vector(new double[]{2.5, 3.5});
        Vector test2 = new Vector(new double[]{1.0, 2.0});
        Vector test3 = new Vector(new double[]{6.0, 7.0});
        
        System.out.println("  Input " + test1 + " → " + learner1.predict(test1));
        System.out.println("  Input " + test2 + " → " + learner1.predict(test2));
        System.out.println("  Input " + test3 + " → " + learner1.predict(test3));

        //TEST 3: StandardizedDataset --> crear dataset estandaritzat a partir del raw
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 3: StandardizedDataset - Transformacions");
        System.out.println("═══════════════════════════════════════════════════════");
        
        StandarizedDataset std = raw.standardize();
        System.out.println("StandarizedDataset creat amb " + std.getData().size() + " records");
        
        //verificar que els records estan standarditzats
        System.out.println("\nPrimers 3 records standarditzats:");
        for (int i = 0; i < Math.min(3, std.getData().size()); i++) {
            System.out.println("  " + std.getData().get(i));
        }
        
        //provar transformació i destransformació
        Record originalRecord = new Record(new Vector(new double[]{3.0, 4.0}), 9.0);
        Record stdRecord = std.transform(originalRecord);
        double destransformed = std.output(stdRecord.getOutput());
        
        System.out.println("\nTest transform + output:");
        System.out.println("  Record original: " + originalRecord);
        System.out.println("  Record standarditzat: " + stdRecord);
        System.out.println("  Output destransformat: " + destransformed + " (ha de ser ≈9.0)");

        //TEST 4: StandardizedDataset + GradientDescent --> entrenament amb gradient descent en dades standarditzades
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 4: StandarizedDataset + GradientDescent");
        System.out.println("═══════════════════════════════════════════════════════");
        
        GradientDescent gd2 = new GradientDescent(0.1, 0.001);
        SupervisedLearner learner2 = new SupervisedLearner(gd2, std);
        
        System.out.println("Entrenant model amb dades standarditzades...");
        System.out.println("  Learning rate: 0.1 (més gran perquè les dades estan normalitzades)");
        System.out.println("  Stopping criterion: 0.001");
        
        //entrenar model
        learner2.solve();
        System.out.println("\n" + learner2);

        // provar prediccions amb destransformació automàtica
        System.out.println("\nPrediccions (amb destransformació automàtica):");
        System.out.println("  Input " + test1 + " → " + learner2.predict(test1));
        System.out.println("  Input " + test2 + " → " + learner2.predict(test2));
        System.out.println("  Input " + test3 + " → " + learner2.predict(test3));

        //TEST 5: RawDataset + StochasticGradientDescent --> entrenament amb stochastic gradient descent en dades raw
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 5: RawDataset + StochasticGradientDescent");
        System.out.println("═══════════════════════════════════════════════════════");
        
        StochasticGradientDescent sgd = new StochasticGradientDescent(0.005, 3, 2000);
        SupervisedLearner learner3 = new SupervisedLearner(sgd, raw);
        
        System.out.println("Entrenant model amb SGD...");
        System.out.println("  Learning rate: 0.005");
        System.out.println("  Batch size: 3");
        System.out.println("  Iterations: 2000");
        
        //entrenar model
        learner3.solve();
        System.out.println("\n" + learner3);
        
        System.out.println("\nPrediccions:");
        System.out.println("  Input " + test1 + " → " + learner3.predict(test1));
        System.out.println("  Input " + test2 + " → " + learner3.predict(test2));
        System.out.println("  Input " + test3 + " → " + learner3.predict(test3));

        //TEST 6: StandardizedDataset + StochasticGradientDescent --> entrenament amb stochastic gradient descent en dades standarditzades
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 6: StandardizedDataset + StochasticGradientDescent");
        System.out.println("═══════════════════════════════════════════════════════");
        
        StochasticGradientDescent sgd2 = new StochasticGradientDescent(0.05, 3, 2000);
        SupervisedLearner learner4 = new SupervisedLearner(sgd2, std);
        
        System.out.println("Entrenant model amb SGD i dades standarditzades...");
        System.out.println("  Learning rate: 0.05");
        System.out.println("  Batch size: 3");
        System.out.println("  Iterations: 2000");
        
        //entrenar model
        learner4.solve();
        System.out.println("\n" + learner4);
        
        System.out.println("\nPrediccions:");
        System.out.println("  Input " + test1 + " → " + learner4.predict(test1));
        System.out.println("  Input " + test2 + " → " + learner4.predict(test2));
        System.out.println("  Input " + test3 + " → " + learner4.predict(test3));

        //TEST 7: Comparació de resultats --> entre GD i SGD
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 7: Comparació de resultats (input [2.5, 3.5])");
        System.out.println("═══════════════════════════════════════════════════════");
        
        Vector testInput = new Vector(new double[]{2.5, 3.5});
        
        System.out.println("GD (raw):              " + learner1.predict(testInput));
        System.out.println("GD (standardized):     " + learner2.predict(testInput));
        System.out.println("SGD (raw):             " + learner3.predict(testInput));
        System.out.println("SGD (standardized):    " + learner4.predict(testInput));
        System.out.println("\nTots els resultats haurien de ser similars (~8.0)");

        //TEST 8: Dataset més gran per SGD
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 8: Dataset més gran (millor per SGD)");
        System.out.println("═══════════════════════════════════════════════════════");
        
        RawDataset bigRaw = new RawDataset(1);
        for (int i = 0; i < 100; i++) {
            double x = i / 10.0;
            double y = 2 * x + 3 + (Math.random() - 0.5) * 0.5; // y = 2x + 3 amb soroll
            bigRaw.addRecord(new Vector(new double[]{x}), y);
        }
        
        System.out.println("Dataset creat amb " + bigRaw.getData().size() + " records");
        System.out.println("Relació: y ≈ 2x + 3");
        
        //entrenar amb GD
        GradientDescent gdBig = new GradientDescent(0.01, 0.001);
        SupervisedLearner learnerGD = new SupervisedLearner(gdBig, bigRaw);
        learnerGD.solve();
        
        //entrenar amb SGD
        StochasticGradientDescent sgdBig = new StochasticGradientDescent(0.005, 10, 1000);
        SupervisedLearner learnerSGD = new SupervisedLearner(sgdBig, bigRaw);
        learnerSGD.solve();
        
        System.out.println("\nModel GD:  " + learnerGD.toString().split("Model: ")[1]);
        System.out.println("Model SGD: " + learnerSGD.toString().split("Model: ")[1]);
        
        Vector testX = new Vector(new double[]{5.0});
        System.out.println("\nPredicció per x=5.0 (esperat ≈13.0):");
        System.out.println("  GD:  " + learnerGD.predict(testX));
        System.out.println("  SGD: " + learnerSGD.predict(testX));

        //TEST 9: Verificar herència i polimorfisme
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("TEST 9: Verificació de polimorfisme");
        System.out.println("═══════════════════════════════════════════════════════");
        
        Dataset[] datasets = {raw, std};
        Algorithm[] algorithms = {
            new GradientDescent(0.01, 0.001),
            new StochasticGradientDescent(0.005, 2, 1000)
        };
        
        System.out.println("Provant totes les combinacions Dataset x Algorithm:");
        for (int i = 0; i < datasets.length; i++) { //bucle sobre tots els datasets
            for (int j = 0; j < algorithms.length; j++) { //bucle sobre tots els algoritmes
                SupervisedLearner sl = new SupervisedLearner(algorithms[j], datasets[i]); 
                sl.solve(); //entrenar model amb el dataset (i),i el algoritme (j)
                double pred = sl.predict(new Vector(new double[]{2.5, 3.5})); //fer prediccio amb input fix 
                //mostrar tipus dataset, algoritme i prediccio
                System.out.println("  " + datasets[i].getClass().getSimpleName() + 
                                 " + " + algorithms[j].getClass().getSimpleName() + 
                                 " → predicció: " + pred);
            }
        }

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              TOTS ELS TESTS COMPLETATS! ✓              ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}