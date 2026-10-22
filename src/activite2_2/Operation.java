package activite2_2;
import java.io.Serializable;
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;
    private double operande1;
    private char operateur;
    private double operande2;
    public Operation(double operande1,char operateur,double operande2) {
        this.operande1 = operande1;
        this.operateur = operateur;
        this.operande2 = operande2;
    }
    public double getOperande1() { return operande1; }
    public char getOperateur()   { return operateur; }
    public double getOperande2() { return operande2; }	
    @Override
    public String toString() {
        return operande1 + " " + operateur + " " + operande2;
    }
}