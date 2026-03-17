
import java.util.*;
public class UC1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            UC1.Feet f1 = new UC1.Feet(a); 
            UC1.Feet f2 = new UC1.Feet(b); 
            System.out.println(f1.equals(f2));

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }
      
    }

    static class Feet{
        private final double val;
        public Feet(double val){
            this.val = val;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == this){
                return true; 
            }
            if(obj==null || getClass() != obj.getClass()){
                return false;
            }
            Feet other = (Feet) obj;
            return Double.compare(this.val, other.val) == 0;
        }
    }

}