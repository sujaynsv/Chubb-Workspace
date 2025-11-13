public class Calculator {
    private int result;

    public Calculator(int startValue) {
        result = startValue;
    }

    public void add(int value) {
        result =+ value; // supposed to add but does something else!
    }

    public void subtract(int value) {
//        result - value; // invalid statement
    }

    public int getResult() {
        return result;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator(10);
        calc.add(5);
        calc.subtract(3);
//        System.out.println("Result is: " + calc.result());
    }
}
