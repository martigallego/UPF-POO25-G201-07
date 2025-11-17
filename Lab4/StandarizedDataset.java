package Lab3;

public class StandarizedDataset extends Dataset{
    private Vector mi; //mu in
    private Vector si; //sigma in
    private double mo; //mu out
    private double so; //sigma out

    //constructor
    public StandarizedDataset(int d,Vector mi, Vector si, double mo, double so){
        super(d);
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
    }


    public Record transform(Record r){
        //standaritzar vector entrada
        Vector standardizedInput = r.getInput().subtract(mi).divideElement(si);

        //normalitzar sortida
        double standardizedOutput = (r.getOutput() - mo) / so;

        //retorna new recordamb valors standaritzats
        return new Record(standardizedInput, standardizedOutput);
    }
 
    
}
