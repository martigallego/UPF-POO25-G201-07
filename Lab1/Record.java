package Lab1;

public class Record {
    private Vector input;
    private double output;

    //constructor
    public Record(Vector in, double out ){
        this.input = in;
        this.output = out;
    }

    //getters
    public Vector getInput(){
        return input;
    }

    public double getOutput(){
        return output;
    }

    public String toString(){
        return "Input: "+ input.toString()+ " // Output: "+ output;
    }
    
}
