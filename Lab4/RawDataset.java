package Lab4;

public class RawDataset extends Dataset {

    public RawDataset(int d) {
        super(d);
    }

    // ============================================
    // Mètodes per calcular estadístiques
    // ============================================
    
    public Vector meanInput(){
        Vector sum = new Vector(getDim(), 0.0);
        for (Record r : getData()) {
            sum = sum.add(r.getInput());
        }
        return sum.divideScalar(getData().size());
    }

    public Vector stdInput() {
        Vector mean = meanInput();
        Vector sumSq = new Vector(getDim(), 0.0);

        for (Record r : getData()) {
            Vector v = r.getInput().subtract(mean);
            Vector vSquared = v.multiplyElement(v);
            sumSq = sumSq.add(vSquared);
        }

        sumSq = sumSq.divideScalar(getData().size());
        return sumSq.sqrt(sumSq);  
    }

    public double meanOutput() {
        double sum = 0;
        for (Record r : getData()) {
            sum += r.getOutput();
        }
        return sum / getData().size();
    }

    public double stdOutput() {
        double mean = meanOutput();
        double sum = 0;
        for (Record r : getData()) {
            double diff = r.getOutput() - mean;
            sum += diff * diff;
        }
        return Math.sqrt(sum / getData().size());
    }

    // ============================================
    // Mètode per crear StandardizedDataset
    // ============================================
    
    public StandarizedDataset standardize() {
        Vector mi = meanInput();
        Vector si = stdInput();
        double mo = meanOutput();
        double so = stdOutput();
        
        StandarizedDataset sds = new StandarizedDataset(getDim(), mi, si, mo, so);
        
        // Afegir tots els records transformats al nou dataset
        for (Record r : getData()) {
            Record transformed = sds.transform(r);
            sds.addRecord(transformed.getInput(), transformed.getOutput());
        }
        
        return sds;
    }

    // Implementació dels mètodes abstractes
    
    // Per a RawDataset, transform NO modifica el record
    @Override
    public Record transform(Record r) {
        return r;  // Retorna el mateix record sense transformar
    }

    // Per a RawDataset, output NO modifica el valor
    @Override
    public double output(double transformedOutput) {
        return transformedOutput;  // Retorna el mateix valor
    }
}