package RecursionCalc;

public class CalculatorV2 {
    public static void main(String[] args) {

        String equations[] = { "2^3", "7^2", "2^1" };
        int solutions[] = { 8, 49, 2 };

        for (int i = 0; i < equations.length; i++) {
            int solution = calculate(equations[i]);
            System.out.printf("calculate(%s) -> {%d, %B}\n",
                    equations[i], solution, solution == solutions[i]);
        }
    }

    private static int getPreviousTermIndex(String eq) {
        for (int i = eq.length() - 1; i >= 0; i--) {
            if (!Character.isDigit(eq.charAt(i))) {
                return i + 1;
            } else if (i == 0) {
                return 0;
            }
        }
        return -1;
    }

    private static int getNextTermIndex(String eq) {
        for (int i = 0; i < eq.length(); i++) {
            if (!Character.isDigit(eq.charAt(i))) {
                return i - 1;
            } else if (i == eq.length() - 1) {
                return i + 1;
            }
        }
        return -1;
    }

    public static int calculate(String equation) {

        // Print the equation to solve
        //System.out.printf("calculate(%s)\n", equation);

        // There are parentheses present in the equation
        if (equation.indexOf("(") > -1) {

            // Aquire the index of parenthese
            int iPOpen = equation.indexOf("("),
                    iPClose = -1;

            // Aquire closing parentheses
            for (int i = equation.length() - 1; i >= 0; i--) {
                if (equation.charAt(i) == ')') {
                    iPClose = i;
                    break;
                }
            }

            // Replace the solved equation with the solution
            equation = equation.replace(equation.substring(iPOpen, iPClose + 1),
                    "" + calculate(equation.substring(iPOpen + 1, iPClose)));
        }

        // Perform EMDAS

        // While there is exponents to solve
        while (equation.indexOf("^") > -1) {
            // Define index variables and get numbers from index's
            int iOperator = equation.indexOf("^"),
                    iPrevTerm = getPreviousTermIndex(equation.substring(0, iOperator)),
                    iNextTerm = iOperator + getNextTermIndex(equation.substring(iOperator + 1, equation.length())),
                    prevTerm = Integer.parseInt(equation.substring(iPrevTerm, iOperator)),
                    nextTerm = Integer.parseInt(equation.substring(iOperator + 1, iNextTerm + 1)),
                    solution = (int) Math.pow(prevTerm, nextTerm);

            String eq = equation.substring(iPrevTerm, iNextTerm + 1);

            System.out.printf("calculate(pow, %s) -> %d\n", eq, solution);

            // replace equation we solved with the solution
            equation = equation.replace(eq, "" + solution);
        }
        return Integer.parseInt(equation);
    }
}
