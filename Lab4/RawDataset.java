package Lab4;

public class RawDataset extends Dataset { // classe que exten la classe abstracta Dataset

    public RawDataset(int d) { // constructor
        super(d);
    }

    
    public Vector meanInput(){ // mitjana de les entrades
        Vector sum = new Vector(getDim(), 0.0);
        for (Record r : getData()) { // recorre tots els registres i suma les entrades
            sum = sum.add(r.getInput());
        }
        return sum.divideScalar(getData().size()); 
    }

    public Vector stdInput() { // desviació estàndard de les entrades
        Vector mean = meanInput(); 
        Vector sumSq = new Vector(getDim(), 0.0); 

        for (Record r : getData()) { // recorre tots els registres i calcula la suma dels quadrats de les desviacions
            Vector v = r.getInput().subtract(mean);
            Vector vSquared = v.multiplyElement(v);
            sumSq = sumSq.add(vSquared);
        }

        sumSq = sumSq.divideScalar(getData().size());
        return sumSq.sqrt();  
    }

    public double meanOutput() { // mitjana de les sortides
        double sum = 0;
        for (Record r : getData()) { // recorre tots els registres i suma les sortides
            sum += r.getOutput();
        }
        return sum / getData().size();
    }

    public double stdOutput() { // desviació estàndard de les sortides
        double mean = meanOutput();
        double sum = 0;
        for (Record r : getData()) { // recorre tots els registres i calcula la suma dels quadrats de les desviacions
            double diff = r.getOutput() - mean;
            sum += diff * diff;
        }
        return Math.sqrt(sum / getData().size());
    }

    // crea un nou StandarizedDataset amb els registres estandarditzats
    public StandarizedDataset standardize() {
        Vector mi = meanInput();
        Vector si = stdInput();
        double mo = meanOutput();
        double so = stdOutput(); 
        
        StandarizedDataset sds = new StandarizedDataset(getDim(), mi, si, mo, so); // nou dataset estandarditzat
        
        // afegir tots els records transformats al nou dataset
        for (Record r : getData()) {
            Record transformed = sds.transform(r);
            sds.addRecord(transformed.getInput(), transformed.getOutput());
        }
        
        return sds;
    }

    // implementació dels mètodes abstractes
    
    // per a RawDataset, transform NO modifica el record
    @Override
    public Record transform(Record r) {
        return r;  // retorna el mateix record sense transformar
    }

    // per a RawDataset, output NO modifica el valor
    @Override
    public double output(double transformedOutput) {
        return transformedOutput;  // retorna el mateix valor
    }
}