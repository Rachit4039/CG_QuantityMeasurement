 class Length {
    private double val;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

        public final double conversionFactor;
    
        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    public Length(double val,LengthUnit unit){
        this.val = val;
        this.unit=unit;
    }

    private double convertToBaseUnit(){
        return val*unit.getConversionFactor();
    }

    // public boolean compare(UC3 thatLength){
    //     return 
    // }

    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        Length l = (Length)o;
        return Double.compare(this.convertToBaseUnit(), l.convertToBaseUnit())==0;
    }

    
}


public class UC3{
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        return l1.equals(l2);
    }

    public static void demonstrateFeetEquality(){
        Length f1 = new Length(5, Length.LengthUnit.FEET);
        Length f2 = new Length(3, Length.LengthUnit.FEET);

        System.out.println("Equal or not - " + demonstrateLengthEquality(f1, f2));
    }

    public static void demonstrateInchesEquality(){
        Length i1 = new Length(12, Length.LengthUnit.INCHES);
        Length i2 = new Length(12, Length.LengthUnit.INCHES);

        System.out.println("Equal or not - " + demonstrateLengthEquality(i1, i2));
    }

    public static void demonstrateFeetInchesComparison(){
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        System.out.println("Feet & Inches comparison - " + demonstrateLengthEquality(l1, l2));
    }
}