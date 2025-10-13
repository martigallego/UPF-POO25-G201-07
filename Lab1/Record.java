package Lab1;

public class Record {
    private Vector input;
    private double output;

    //constructor
    public Record(Vector in, double out ){
        in = input;
        out = output;
    }

    //getters
    public Vector getInput(){
        return input;
    }

    public double getOutput(){
        return output;
    }

    public String toString(){
        return "Input: "+input+"// Output: "+output;
    }
    
}
