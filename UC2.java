public class UC2 {

    
    static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

 
    static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    
    public static boolean checkFeetEquality() {

        double v1 = 1.0;
        double v2 = 3.0;
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);

        return f1.equals(f2);
    }

   
    public static boolean checkInchesEquality() {

        double v1 = 2.0;
        double v2 = 2.0;
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);

        return i1.equals(i2);
    }

    public static void main(String[] args) {

        System.out.println("Feet Equal: " + checkFeetEquality());
        System.out.println("Inches Equal: " + checkInchesEquality());
    }
}