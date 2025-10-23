package Lab2;

public class TestDataset {
    public static void main(String args[]){
        //vectors de ENTRADA
        Vector v1 = new Vector(new double[] {1.0, 2.0, 3.0});
        Vector v2 = new Vector(new double[] {4.0, 5.0, 6.0});

        //crear RECORDS
        Record r1 = new Record(v1, 10.0);
        Record r2 = new Record(v2, 20.0);

        //crear DATASET i afegir RECORDS
        Dataset d  = new Dataset(3); //dataset mida 3

        d.addRecord(r1); //afegir el record 1
        d.addRecord(r2); //afegir el record 2

        //mostrar per terminal
        //dataset sense estandaritzar
        System.out.println("DATASET ORIGINAL");
        System.out.println(d.toString());
    
        //dataset ESTANDARITZAT
        StandarizedDataset ds = d.standardize();
        System.out.println("DATASET ESTANDARITZAT");
        System.out.println(ds.toString());

        //transformar record
        Record transformed = ds.transform(r2);
        System.out.println("RECORD TRANSFORMAT");
        System.out.println("Sense transformar:  " + r2);
        System.out.println("Transformat:  " + transformed);
    }

    
}
