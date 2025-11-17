package Lab4;

public class StandarizedDataset extends Dataset {
    private Vector mi; // μ_in: mitjana input original
    private Vector si; // σ_in: std input original
    private double mo; // μ_out: mitjana output original
    private double so; // σ_out: std output original

    public StandarizedDataset(int d, Vector mi, Vector si, double mo, double so){
        super(d);
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
    }

    // ============================================
    // Implementació dels mètodes abstractes
    // ============================================
    
    // Transforma un record de l'espai original a l'espai standarditzat
    // x̂ = (x - μ_in) / σ_in
    // ŷ = (y - μ_out) / σ_out
    @Override
    public Record transform(Record r){
        Vector standarizedInput = r.getInput().subtract(mi).divideElement(si);
        double standarizedOutput = (r.getOutput() - mo) / so;
        return new Record(standarizedInput, standarizedOutput);
    }

    // Transformació inversa per l'output: de l'espai standarditzat a l'original
    // y = v * σ_out + μ_out
    @Override
    public double output(double transformedOutput) {
        return transformedOutput * so + mo;
    }
}