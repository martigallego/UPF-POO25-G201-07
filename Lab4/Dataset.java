package Lab4;

import java.util.ArrayList;
import java.util.List;

public abstract class Dataset {
    private int dim;
    private List<Record> data;

    public Dataset(int d){
        this.dim = d;
        this.data = new ArrayList<>();
    }

    public int getDim(){
        return dim;
    }

    public List<Record> getData(){
        return data;
    }

    public void addRecord(Vector in, double out) {
        Record r = new Record(in, out);
        data.add(r);
    }

    // Mètodes abstractes: totes les subclasses els han d'implementar
    public abstract Record transform(Record r);
    public abstract double output(double transformedOutput);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dataset (dim=").append(dim).append(", n=").append(data.size()).append(")\n");
        for (Record r : data) sb.append(r.toString()).append("\n");
        return sb.toString();
    }
}