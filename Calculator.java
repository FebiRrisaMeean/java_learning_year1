import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.valueOf;
public class Calculator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = s.nextLine(); //записываю строку из консоли в переменную input
        String[] expression = input.split(" "); //разделяю строку (разделитель - пробел) и создаю массив из строк
        String a = expression[0]; //выбираю первый символ выражения
        String e = expression[1]; //выбираю математический знак
        String b = expression[2]; //выбираю второй символ выражения
        if (expression.length > 3){
            System.out.println("Вы ввели слишком много символов");
            return;
        }
        try{
            int an1 = valueOf(a); //если не вылезает ошибка, то число арабское и мы идем дальше
            try{
                int bn1 = valueOf(b); //если тут вылезет, то мы отловим
//                System.out.println("Оба числа - арабские");
                int c = an1 + bn1;
                MyEquation equation1 = new MyEquation();
                equation1.operator = e;
                equation1.aaa = an1;
                equation1.bbb = bn1;
                if ((1 <= an1 && an1 <= 10) && (1 <= bn1 && bn1 <= 10)) {
                    System.out.println(equation1.counter());
                }
                else{
                    System.out.println("№1: одно или оба числа не принадлежат интервалу [1,10]");
                }
            }
            catch (NumberFormatException ex3){
                System.out.println("№2: только первое число - арабское");
            }
        }
        catch (NumberFormatException ex1){
            try{
                int bn1 = valueOf(b); //если и тут вылезет, то оба римские
                System.out.println("№3: только второе число - арабское");
            }
            catch (NumberFormatException ex2){
                try {
//                    System.out.println("оба римские блять");
                    MyEquation equation2 = new MyEquation();
                    equation2.operator = e; //оператор выражения
                    MyInput number1 = new MyInput(); //работает, если оба римские
                    number1.aa = a; //первое римское число
                    int an = number1.convertor(); //первое римское, переведенное в арабское
                    equation2.aaa = an; //запись арабского числа в выражение
                    MyInput number2 = new MyInput();
                    number2.aa = b; //второе римское число
                    int bn = number2.convertor(); //перевод в арабское
                    equation2.bbb = bn; //добавление второго числа в выражение
                    int result = equation2.counter();
                    if ((1 <= an && an <= 10) && (1 <= bn && bn <= 10) && an > bn) { //если числа подходящие, то выведется результат в римских числах
                        IntegerToRoman.intToRoman(result);
                    } else if (result < 0) { //если число отрицательное, выйдет ошибка
                        System.out.println("№4: итог выражения - отрицательное число");
                    } else if ((1 >= an || an >= 10) || (1 >= bn || bn >= 10)) { //если одно из них больше 10 или меньше 1, то выйдет следующая ошибка:
                        System.out.println("№5: одно или оба числа не принадлежат интервалу [1,10]");
                    } else {
                        System.out.println("№6: калькулятор пока не умеет выполнять операции со случайными символами");
                    }
                }
                catch (IllegalStateException ex4){
                    System.out.println("№7: как ты увидел эту ошибку вообще?");
                }
            }
        }
    }
    static class MyInput {

        String aa; //перевод строки в число

        int convertor() {
            int x = 0;
            switch (aa) {
                case "I" -> {
                    x = 1;
                    break;
                }
                case "II" -> {
                    x = 2;
                    break;
                }
                case "III" -> {
                    x = 3;
                    break;
                }
                case "IV" -> {
                    x = 4;
                    break;
                }
                case "V" -> {
                    x = 5;
                    break;
                }
                case "VI" -> {
                    x = 6;
                    break;
                }
                case "VII" -> {
                    x = 7;
                    break;
                }
                case "VIII" -> {
                    x = 8;
                    break;
                }
                case "IX" -> {
                    x = 9;
                    break;
                }
                case "X" -> {
                    x = 10;
                    break;
                }
                default -> throw new IllegalStateException("Unexpected value: " + aa);
            }
            return x;

        } //конвертирует полученные строки в числа
    } //сделал сам, работает
    static class MyEquation {
        String operator;
        int aaa;
        int bbb;
        int counter() {
            int r = 0;
            switch (operator){
                case "+" -> {
                    r = aaa + bbb;
                    break;
                }
                case "-" -> {
                    r = aaa - bbb;
                    break;
                }
                case "*" -> {
                    r = aaa * bbb;
                    break;
                }
                case "/" -> {
                    r = aaa / bbb;
                    break;
                }
                default ->
                System.out.println("Введён некорректный оператор");
            }
            return r;
        }

    } //сделал сам, работает
    static class IntegerToRoman {
        static void intToRoman(int num) {
            int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] romanLetters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder roman = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                while (num >= values[i]) {
                    num = num - values[i];
                    roman.append(romanLetters[i]);
                }
            }
            System.out.println(roman.toString());
        }
    } //позаимствовал и адаптировал
}




