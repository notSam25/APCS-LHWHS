package RecursionCalc;

/**
 * (14 / 2 * (3 / 3 + 2))
 * 14 / 2 * (3 / 3 + 2)
 * 3 / 3 + 2
 */
public class Calculator {
    public static void main(String[] args) {
        // calc("(14 / 2 * (3 / 3 + 2))");
        String equation = "53+12*22+18";
        int i = equation.indexOf("*");

        int iPreviousTerm = getPreviousTerm(i - 1, equation.substring(0, i));
        int iNextTerm = getNextTerm(i + 1, equation.substring(i + 1));
        System.out.println("Previous Term: " + equation.substring(iPreviousTerm, i));
        System.out.println("Next Term: " + equation.substring(i + 1, iNextTerm + 1));
    }

    /**
     * Searches for the previous term and return's the index.
     * 
     * @param start the index to start the search
     * @param eq    the string equation to parse
     * @return the start index of the previous term.
     */
    public static int getPreviousTerm(int start, String eq) {
        System.out.printf("getPreviousTerm(%s)\n", eq);
        for (int i = start; i != 0; i--) {
            char cc = eq.charAt(i);
            if (!Character.isDigit(cc)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Searches for the next term and return's the index.
     * 
     * @param start the index to start the search
     * @param eq    the string equation to parse
     * @return the end index of the next term.
     */
    public static int getNextTerm(int start, String eq) {
        System.out.printf("getNextTerm(%s)\n", eq);
        for (int i = 0; i < eq.length(); i++) {
            char cc = eq.charAt(i);
            if (!Character.isDigit(cc)) {
                return start + i - 1;
            }
        }
        return -1;
    }

    public static int calc(String equation) {
        int pIndex = equation.indexOf("(");
        int total = 0;
        if (pIndex > -1) { // we have a parenthesis to solve for
            total += calc(equation.substring(pIndex + 1, equation.length() - 1));
        } else { // solve the input equation
            System.out.println(equation);
            int iPreviousTerm = -1, iNextTerm = -1;
            for (int i = 0; i < equation.length(); i++) {
                char cc = equation.charAt(i);

                if (Character.isDigit(cc))
                    continue;

                // get our term indexes
                iPreviousTerm = getPreviousTerm(i - 1, equation.substring(0, i));
                iNextTerm = getNextTerm(i + 1, equation.substring(i + 1));
                
                // get the numbers from the indexes
                int previousTerm = Integer.parseInt(equation.substring(iPreviousTerm, i));
                int nextTerm = Integer.parseInt(equation.substring(i + 1, iNextTerm + 1));
                
                // perform EMDAS
                if (cc == '^') {
                    
                } else if (cc == '*') {

                } else if (cc == '/') {

                } else if (cc == '+') {

                } else if (cc == '-') {

                }
            }
        }
        return total;
    }
};
