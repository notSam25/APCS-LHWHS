package RecursionCalc;

public class Calculator {
    public static void main(String[] args) {
        String equation = "(3*(2+2)+8)";
        System.out.printf("calculateRecusive(%s) -> %d\n", equation, calculateRecursive(equation));
    }

    /**
     * Searches for the previous term and return's the index.
     * 
     * @param start the index to start the search
     * @param eq    the string equation to parse
     * @return the start index of the previous term, or if there was no index found
     *         return's -1.
     */
    private static int getPreviousTerm(int start, String eq) {
        //System.out.printf("getPreviousTerm(%d, %s)\n", start, eq);
        for (int i = start; i >= 0; i--) {
            char cc = eq.charAt(i);
            // System.out.printf("i(%d) cc(%s)\n", i, cc);

            if (!Character.isDigit(cc)) {
                return i + 1;
            } else if (i == 0) {
                return 0;
            }
        }
        return -1;
    }

    /**
     * Searches for the next term and return's the index.
     * 
     * @param start the index to start the search
     * @param eq    the string equation to parse
     * @return the end index of the next term, or if there was no index found
     *         return's -1.
     */
    private static int getNextTerm(int start, String eq) {
        // System.out.printf("getNextTerm(%d, %s)\n", start, eq);
        for (int i = 0; i < eq.length(); i++) {
            char cc = eq.charAt(i);

            if (!Character.isDigit(cc)) {
                return start + i - 1;
            } else if (i == eq.length() - 1) {
                return start + i;
            }
        }
        return -1;
    }

    /**
     * Solves an 2 variable equation.
     * 
     * @param equation  the equation to solve
     * @param operation the operation to perform
     * @return the operation of the equations terms
     */
    private static String solveByOperation(String equation, char operation) {
        int solution = 0;
        int previousTerm = Integer.parseInt(equation.substring(0, equation.indexOf(operation)));
        int nextTerm = Integer.parseInt(equation.substring(equation.indexOf(operation) + 1, equation.length()));

        if (operation == '^') {
            solution += (int) Math.pow(previousTerm, nextTerm);
        } else if (operation == '*') {
            solution += previousTerm * nextTerm;
        } else if (operation == '/') {
            solution += previousTerm / nextTerm;
        } else if (operation == '+') {
            solution += previousTerm + nextTerm;
        } else if (operation == '-') {
            solution += previousTerm - nextTerm;
        }
        return "" + solution;
    }

    /**
     * Handles the given operation on a equation
     * 
     * @param equation  the equation
     * @param operation the operation to use
     * @return the result of the operation of the terms of the equation
     */
    private static String handleOperation(String equation, char operation) {
        String result = equation;

        while (result.indexOf(operation) > -1) {
            // get the index for each var
            int iOperator = result.indexOf(operation);
            int iPreviousTerm = getPreviousTerm(iOperator - 1, result.substring(0, iOperator));
            int iNextTerm = getNextTerm(iOperator + 1, result.substring(iOperator + 1, result.length()));

            if (iOperator == -1 || iPreviousTerm == -1 || iNextTerm == -1) {
                break;
                //System.out.printf("RUNTIME ERROR: equation(%s), iOperator(%d), iPreviousTerm(%d), iNextTerm(%d)\n", result, iOperator,
                //        iPreviousTerm, iNextTerm);
            }

            // the equation to solve
            String eq = result.substring(iPreviousTerm, iNextTerm + 1);

            // append the result
            result = result.replace(eq, solveByOperation(eq, operation));
        }
        return result;
    }

    /**
     * Calculates an equation recusivly.
     * 
     * @param equation the equation to solve
     * @return the solution
     */
    public static int calculateRecursive(String equation) {
        // start by creating our modifiable equation
        String equationToSolve = equation;

        // if a parenthesis is found, the programm will solve that first
        if (equation.indexOf("(") > -1) {
            // get the index's of the open and close of the parenthesis
            int iOpen = equation.indexOf("(");
            int iClose = -1;

            for (int i = equation.length() - 1; i >= 0; i--) {
                char cc = equation.charAt(i);
                if (cc == ')') {
                    iClose = i;
                    break;
                }
            }

            // the solution for the parenthesis
            int solution = calculateRecursive(equationToSolve.substring(iOpen + 1, iClose));

            // replace the equation we solved with the solution
            equationToSolve = equationToSolve.replace(equationToSolve.substring(iOpen, iClose + 1), "" + solution);
        }

        // Perform EMDAS on the equation
        equationToSolve = handleOperation(equationToSolve, '^');
        System.out.println("performOperation(^) -> " + equationToSolve);

        equationToSolve = handleOperation(equationToSolve, '*');
        System.out.println("performOperation(*) -> " + equationToSolve);

        equationToSolve = handleOperation(equationToSolve, '/');
        System.out.println("performOperation(/) -> " + equationToSolve);

        equationToSolve = handleOperation(equationToSolve, '+');
        System.out.println("performOperation(+) -> " + equationToSolve);

        equationToSolve = handleOperation(equationToSolve, '-');
        System.out.println("performOperation(-) -> " + equationToSolve);

        // return the solved equation
        return Integer.parseInt(equationToSolve);
    }
};
