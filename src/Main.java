import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String result = calc(input);
    }

    public static String calc(String input) {
        char action;
        String[] parts;
        if(input.contains(" + ")) {
            parts = input.split(" \\+ ");
            action = '+';
        }
        else if(input.contains(" - ")) {
            parts = input.split(" - ");
            action = '-';
        }
        else if(input.contains(" * ")) {
            parts = input.split(" \\* ");
            action = '*';
        }
        else if(input.contains(" / ")) {
            parts = input.split(" / ");
            action = '/';
        }
        else throw new IllegalArgumentException("Неверный оператор!");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Неверное количество переменных!");
        }


        String operand1 = parts[0];
        String operand2 = parts[1];

        if (action == '*' || action == '/') {
            if (operand2.contains("\"")) throw new IllegalArgumentException("Число должно быть без кавычек");
        }
        if (!operand1.contains("\"")) throw new IllegalArgumentException("Неверный формат выражения");

        operand1 = operand1.replace("\"", "");
        operand2 = operand2.replace("\"", "");

        if (operand1.length() > 10 || operand2.length() > 10) {
            throw new IllegalArgumentException("Одна из переменных больше 10 символов!");
        }

        String result = null;
        int num;


        switch (action) {
            case '+':
                printQuotes(operand1 + operand2);
                break;
            case '-':
                int index = operand1.indexOf(operand2);
                if(index == -1) {
                    printQuotes(operand1);
                }else{
                    result = operand1.substring(0,index);
                    result+=operand1.substring(index+operand2.length());
                    printQuotes(result);
                }
                break;
            case '*':
                num = 0;
                try {
                        num = Integer.parseInt(operand2);
                    } catch (NumberFormatException e) {
                        System.out.println("Вторым оператором должно быть число");
                    }
                if (num < 1 || num > 10 || num < 1 || num > 10) {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
                }
                result = operand1;
                for (int i = 1; i < num; i++) {
                    result += operand1;
                }
                printQuotes(result);
                break;
            case '/':
                num = 0;
                try {
                    num = Integer.parseInt(operand2);
                } catch (NumberFormatException e) {
                    System.out.println("Вторым оператором должно быть число");
                }
                if (num < 1 || num > 10 || num < 1 || num > 10) {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
                }
                int newLen = operand1.length()/num;
                result = operand1.substring(0,newLen);
                printQuotes(result);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: ");
        }
        return result;
    }

    private static void printQuotes(String text) {
        if (text.length() > 40) {
            System.out.print("\""+text.substring(0,40)+"...\"");
            return;
        }
        System.out.print("\""+text+"\"");
    }

}