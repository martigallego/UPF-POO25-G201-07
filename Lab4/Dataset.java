package Lab4;

import java.util.ArrayList;
import java.util.List;

public abstract class Dataset { // classe abstracta
    private int dim;
    private List<Record> data;

    // constructor
    public Dataset(int d){
        this.dim = d;
        this.data = new ArrayList<>();
    }

    // getters
    public int getDim(){ //obté dimensió d'entrada
        return dim;
    }

    public List<Record> getData(){ //obté llista de registres
        return data;
    }

    public void addRecord(Vector in, double out) { //afegeix un registre a la llista de dades
        Record r = new Record(in, out);
        data.add(r);
    }

    // metodes abstractes
    public abstract Record transform(Record r);
    public abstract double output(double transformedOutput);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dataset (dim=").append(dim).append(", n=").append(data.size()).append(")\n");
        for (Record r : data) sb.append(r.toString()).append("\n");
        return sb.toString();
    }
}