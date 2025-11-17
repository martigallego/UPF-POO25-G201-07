package Lab4;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
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

    public Vector meanInput(){
        Vector sum = new Vector(new double[dim]);
        for (Record r : data) {
            sum = sum.add(r.getInput());
        }
        return sum.divideScalar(data.size());
    }

    //public Vector stdInput() {
    //    Vector mean = meanInput();
    //    double[] sumSq = new double[dim];

    //    for( Record r : data){
    //        Vector v = r.getInput().subtract(mean);
    //        for (int i = 0; i < dim; i++){
    //            sumSq[i] += Math.pow(v.toString().charAt(i), 2);
    //        }
    //    }

    //    double[] std = new double[dim];

   //     for (int i = 0; i < dim; i++){
    //        std[i] = Math.sqrt(sumSq[i] / data.size());
   //     }

   //     return new Vector(std);
    //}

    public Vector stdInput() {
        Vector mean = meanInput();
        Vector sumSq = new Vector(dim, 0.0);

        for (Record r : data) {
            Vector v = r.getInput().subtract(mean);
            Vector vSquared = v.multiplyElement(v);
            sumSq = sumSq.add(vSquared);
        }

        sumSq = sumSq.divideScalar(data.size());
        return sumSq.sqrt(sumSq);  
    }

    public double meanOutput() {
        double sum = 0;
        for (Record r : data) sum += r.getOutput();
        return sum / data.size();
    }

    public double stdOutput() {
        double mean = meanOutput();
        double sum = 0;
        for (Record r : data) {
            double diff = r.getOutput() - mean;
            sum += diff * diff;
        }
        return Math.sqrt(sum / data.size());
    }
    
    public StandarizedDataset standardize() {
            Vector mi = meanInput();
            Vector si = stdInput();
            double mo = meanOutput();
            double so = stdOutput();
            return new StandarizedDataset(dim, mi, si, mo, so);
        }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dataset (dim=").append(dim).append(", n=").append(data.size()).append(")\n");
        for (Record r : data) sb.append(r.toString()).append("\n");
        return sb.toString();
    }

}
