import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

public class UC14 {

    interface IMeasurable {

        double toBase(double value);
        double fromBase(double baseValue);

        @FunctionalInterface
        interface SupportsArithmetic {
            boolean isSupported();
        }

        SupportsArithmetic supportsArithmetic = () -> true;

        default boolean supportsArithmetic() {
            return supportsArithmetic.isSupported();
        }

        default void validateOperationSupport(String operation) {
        }
    }

    enum ArithmeticOperation {

        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double apply(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    enum LengthUnit implements IMeasurable {

        METER(1.0),
        FEET(0.3048);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double toBase(double value) {
            return value * factor;
        }

        public double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }

    enum TemperatureUnit implements IMeasurable {

        CELSIUS(c -> c, c -> c),
        FAHRENHEIT(f -> (f - 32) * 5 / 9, c -> (c * 9 / 5) + 32),
        KELVIN(k -> k - 273.15, c -> c + 273.15);

        private final Function<Double, Double> toBaseFunc;
        private final Function<Double, Double> fromBaseFunc;

        TemperatureUnit(Function<Double, Double> toBaseFunc,
                        Function<Double, Double> fromBaseFunc) {
            this.toBaseFunc = toBaseFunc;
            this.fromBaseFunc = fromBaseFunc;
        }

        public double toBase(double value) {
            return toBaseFunc.apply(value);
        }

        public double fromBase(double baseValue) {
            return fromBaseFunc.apply(baseValue);
        }

        public boolean supportsArithmetic() {
            return false;
        }

        public void validateOperationSupport(String operation) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation + " operation"
            );
        }
    }

    static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;
        private static final double EPSILON = 0.0001;

        public Quantity(double value, U unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        public Quantity<U> convertTo(U targetUnit) {
            double base = unit.toBase(value);
            double converted = targetUnit.fromBase(base);
            return new Quantity<>(converted, targetUnit);
        }

        public Quantity<U> add(Quantity<U> other) {
            return performOperation(other, ArithmeticOperation.ADD);
        }

        public Quantity<U> subtract(Quantity<U> other) {
            return performOperation(other, ArithmeticOperation.SUBTRACT);
        }

        public Quantity<U> divide(Quantity<U> other) {
            return performOperation(other, ArithmeticOperation.DIVIDE);
        }

        private Quantity<U> performOperation(Quantity<U> other,
                                             ArithmeticOperation op) {

            if (!unit.getClass().equals(other.unit.getClass())) {
                throw new IllegalArgumentException("Different unit categories");
            }

            unit.validateOperationSupport(op.name());

            double base1 = unit.toBase(this.value);
            double base2 = other.unit.toBase(other.value);

            double resultBase = op.apply(base1, base2);
            double result = unit.fromBase(resultBase);

            return new Quantity<>(result, unit);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Quantity<?> other)) return false;

            if (!unit.getClass().equals(other.unit.getClass())) return false;

            double base1 = unit.toBase(this.value);
            double base2 = other.unit.toBase(other.value);

            return Math.abs(base1 - base2) < EPSILON;
        }

        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))
        );

        System.out.println(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT)
        );

        try {
            System.out.println(
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                            .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS))
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.METER);
        Quantity<LengthUnit> l2 = new Quantity<>(1, LengthUnit.FEET);

        System.out.println(l1.add(l2));
    }
}