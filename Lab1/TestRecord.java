package Lab1;

public class TestRecord {
    public static void main(String[] args){
        //prova VECTOR
        double[] valors1 = {1.0 , 3.0 ,6.0};
        Vector v1 = new Vector(valors1);





        //prova RECORD
        Record r1 = new Record(v1, 10);
        System.out.println("(RECORD) "+ r1); 
        System.out.println("X RECORD: "+r1.getInput());
        System.out.println("Y RECORD: "+r1.getOutput());
        
    }
    
}
