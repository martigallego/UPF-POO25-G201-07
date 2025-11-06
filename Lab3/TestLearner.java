package Lab3;

public class TestLearner {
    public static void main(String[] args){
        //crear dataset
        Dataset ds = new Dataset(1);  // 1 feature
        ds.addRecord(new Vector(new double[]{0.0}), 2.0);
        ds.addRecord(new Vector(new double[]{1.0}), 4.0);
        ds.addRecord(new Vector(new double[]{2.0}), 6.0);
        ds.addRecord(new Vector(new double[]{3.0}), 8.0);

        //crear algoritme taxa d'aprenentatge i criteri de parada
        Algorithm alg = new Algorithm(0.1, 1e-6);

        //crear SupervisedLearner amb l’algorisme + dataset
        SupervisedLearner learner = new SupervisedLearner(alg, ds);

        //entrenar model
        learner.solve();

        //mostrar resulttats
        System.out.println(learner);

        //fer prediccio amb un nou vector
        Vector x = new Vector(new double[]{4.0});
        double ypred = learner.predict(x);
        System.out.println("Predicció per x=4.0 → " + ypred);
    }
}
   
