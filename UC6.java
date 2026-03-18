

public class UC6 {
    public static void main(String[] args) {
        System.out.println(domonstrateLengthConversion(4.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS));
        Length l1 = new Length(4.0, Length.LengthUnit.FEET);
Length l2 = new Length(48.0, Length.LengthUnit.INCHES);

System.out.println(demonstrateLengthEquality(l1, l2));
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        return l1.equals(l2);
    }
    
    public static double domonstrateLengthConversion(double v1,Length.LengthUnit unit1,Length.LengthUnit unit2){
        double t = Length.convert(v1,unit1,unit2);
        t = Double.parseDouble(String.format("%.2f", t));
        return t;
    }
    
}



 class Length {
    private double val;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);


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

    static double convert(double val,LengthUnit source,LengthUnit target){
        if(source==null || target==null){
            throw new IllegalArgumentException("Target or source is null");
        }
         if (!Double.isFinite(val))
            throw new IllegalArgumentException("Value must be finite");
        double base = val * source.getConversionFactor(); 
    double result = base / target.getConversionFactor(); 

    return result;
    }
    public String toString()
    {
        return val + " " + unit;
    }

    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        Length l = (Length)o;
        return Double.compare(this.convertToBaseUnit(), l.convertToBaseUnit())==0;
    }
    
    public Length add(Length other) {
    if (other == null) {
        throw new IllegalArgumentException("Other length is null");
    }
    if (!Double.isFinite(this.val) || !Double.isFinite(other.val)) {
        throw new IllegalArgumentException("Invalid value");
    }

   
    double base1 = this.convertToBaseUnit();
    double base2 = other.convertToBaseUnit();

    double sumBase = base1 + base2;


    double resultValue = sumBase / this.unit.getConversionFactor();

    
    return new Length(resultValue, this.unit);
}
}
