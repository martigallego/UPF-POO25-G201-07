package Lab1;

public class TestRecord {
    public static void main(String[] args){
        //prova VECTOR
        double[] valors1 = {1.0 , 3.0 ,6.0};
        double[] valors2 = {3.0, 4.1, 2.22};
        Vector v1 = new Vector(valors1);
        Vector v2 = new Vector(valors2);





        //prova RECORDS
        Record r1 = new Record(v1, 10);
        System.out.println("(RECORD 1) "+ r1); 
        System.out.println("X RECORD: "+r1.getInput());
        System.out.println("Y RECORD: "+r1.getOutput());

        Record r2 = new Record(v2, 10);
        System.out.println("(RECORD 2) "+ r2); 
        System.out.println("X RECORD: "+r2.getInput());
        System.out.println("Y RECORD: "+r2.getOutput());

        //prova METODES
        Vector sum = v1.add(v2);
        System.out.println("v1 + v2 = " + sum);

        // Subtract
        Vector diff = v1.subtract(v2);
        System.out.println("v1 - v2 = " + diff);

        // Element-wise multiply
        Vector elemMul = v1.multiplyElement(v2);
        System.out.println("v1 * v2 (element-wise) = " + elemMul);

        // Element-wise divide
        Vector elemDiv = v2.divideElement(v1);
        System.out.println("v2 / v1 (element-wise) = " + elemDiv);

        // Multiply by scalar
        Vector mulScalar = v1.multiplyScalar(2);
        System.out.println("v1 * 2 = " + mulScalar);

        // Divide by scalar
        Vector divScalar = v2.divideScalar(2);
        System.out.println("v2 / 2 = " + divScalar);

        // Square root of elements
        Vector sqrtV1 = v1.sqrt(v1); // you can just pass v1; other parameter is ignored
        System.out.println("sqrt(v1) = " + sqrtV1);

        // Dot product
        double dotProd = v1.dot(v2);
        System.out.println("v1 · v2 = " + dotProd);

        // Norm
        double normV1 = v1.norm();
        System.out.println("||v1|| = " + normV1);
        
    }
    
}
