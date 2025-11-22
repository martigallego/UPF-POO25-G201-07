package Lab4;

public class StandarizedDataset extends Dataset {
    private Vector mi; // mu_in: mitjana input original
    private Vector si; // sigma_in: std input original
    private double mo; // mu_out: mitjana output original
    private double so; // sigma_out: std output original

    // constructor
    public StandarizedDataset(int d, Vector mi, Vector si, double mo, double so){ 
        super(d);
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
    }

    // implementació dels mètodes abstractes

    // transforma un record de l'espai original a l'espai standarditzat
    // x̂ = (x - mu_in) / sigma_in
    // ŷ = (y - mu_out) / sigma_out
    @Override
    public Record transform(Record r){
        Vector standarizedInput = r.getInput().subtract(mi).divideElement(si);
        double standarizedOutput = (r.getOutput() - mo) / so;
        return new Record(standarizedInput, standarizedOutput);
    }

    // transformació inversa per l'output: de l'espai standarditzat a l'original
    // y = v * sigma_out + mu_out
    @Override
    public double output(double transformedOutput) {
        return transformedOutput * so + mo;
    }
}