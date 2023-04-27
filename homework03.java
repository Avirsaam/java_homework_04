import java.nio.channels.AcceptPendingException;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/*
 * Напишите постфиксный калькулятор. 
 * Пользователь вводит данные и операции в обратной польской записи. 
 * Калькулятор вычисляет результат и проверяет, что в стэке получилось единственное число.
 */

public class homework03 {
    static String ACCEPTABLE_OPEERATIONS = "+-*/^";
    static String INPUT_FILTER = "^(([-]?([1-9]\\d*(\\.|\\,)\\d*[ ]+|0?(\\.|\\,)\\d*[1-9]\\d*[ ]+|[1-9]\\d*[ ]+))*([\\+\\-\\*\\/]{1}[ ]+)*)*([\\+\\-\\*\\/]{1}[ ]*)$";

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Ввведите данные и операции в обратной польской записи:");
        System.out.println("1 2 3 * +");
        System.out.println("1 2 + 3 *");
        System.out.println("для выхода нажмите Ctrl+C или введите пустую строку");

        Scanner myScanner = new Scanner(System.in);
        String inputString = " ";

        Stack<Double> calculationStack = new Stack<>();

        while (!inputString.equals("")) {

            inputString = myScanner.nextLine();

            if (!inputString.matches(INPUT_FILTER)) {
                System.out.println("Неправильный формат ввода выражения или неверный оператор, повторите ввод");
            } else {

                while (inputString.contains("  ")) { // чистим строку от двойных пробелов перед делением на части
                    inputString.replace("  ", " ");
                }

                for (String inputStringElement : inputString.split(" ")) {

                    if (inputStringElement.length() == 1
                            && ACCEPTABLE_OPEERATIONS.contains(inputStringElement)) {
                        
                        try{
                            Double lastNumber = calculationStack.pop();
                            Double secondLastNumber = calculationStack.pop();
                            char operand = inputStringElement.toCharArray()[0];
                            calculationStack.push(calculate(secondLastNumber, lastNumber, operand));
                            System.out.printf("%.2f %c %.2f = %.2f\n", secondLastNumber, operand, lastNumber, calculationStack.peek());
                        }
                        catch (Exception EmptyStackException){
                            System.out.println("Ошибка: опустошение стека, проверьте исходное выражение");
                            break;
                        }                        

                    } else {
                        try {
                            calculationStack.push(Double.parseDouble(inputStringElement));
                        } catch (Exception ex) {
                            System.out.println("Ошибка ввода числа, повторите ввод");
                            break;
                        }
                    }
                }
            }

            if (!calculationStack.isEmpty()) {
                System.out.println("Конечный результат вычисления выражения равен " + calculationStack.peek());
            }
        }

        myScanner.close();
    }

    private static Double calculate(Double firstNumber, Double secondNumber, char operation) {
        Double result = 0.0;
        switch (operation) {
            case '+':
                result = firstNumber + secondNumber;
                break;

            case '-':
                result = firstNumber - secondNumber;
                break;

            case '*':
                result = firstNumber * secondNumber;
                break;

            case '/':
                try {
                    result = firstNumber / secondNumber;
                } catch (Exception NullPointerException) {
                    System.out.println("Ошибка! Деление на ноль");
                }
                break;
        }       

        return result;
    }
}
