public class PostfixEvaluator {

    // Evaluates a postfix expression given as a space-separated string,
    // e.g. "5 3 + 2 *"
    public static double evaluate(String expression) {
        LinkedStack stack = new LinkedStack();
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (isNumber(token)) {
                double value = Double.parseDouble(token);
                stack.push(value);
                System.out.println("Pushed number: " + value);
                stack.displayStack();

            } else if (isOperator(token)) {
                // Order matters for - and /: first pop is the RIGHT operand
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = applyOperator(operand1, operand2, token);

                stack.push(result);
                System.out.println("Applied operator '" + token + "' on "
                        + operand1 + " and " + operand2 + " -> " + result);
                stack.displayStack();

            } else {
                throw new RuntimeException("Invalid token in expression: " + token);
            }
        }

        double finalResult = stack.pop();

        if (!stack.isEmpty()) {
            throw new RuntimeException("Malformed expression: too many operands left on stack");
        }

        return finalResult;
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-")
                || token.equals("*") || token.equals("/");
    }

    private static double applyOperator(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new RuntimeException("Division by zero in postfix expression");
                }
                return operand1 / operand2;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }

    // Standalone test for Task A3
    public static void main(String[] args) {
        String expression = "5 3 + 2 *";
        System.out.println("Evaluating: " + expression);
        double result = evaluate(expression);
        System.out.println("Final Result: " + result);
    }
}