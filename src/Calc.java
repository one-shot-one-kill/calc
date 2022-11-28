import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws CalcException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws CalcException {
        String[] resArr = input.split(" ");
        if(resArr.length > 3){
            throw new CalcException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if(resArr.length == 1){
            throw new CalcException("Строка не является математической операцией");
        }
        int num1 = 0, num2 = 0;
        if(isInt(resArr[0]) && isInt(resArr[2])){
            num1 = Integer.parseInt(resArr[0]);
            num2 = Integer.parseInt(resArr[2]);

            return retRes(num1, resArr[1], num2);
        }
        else if(isRim(resArr[0]) && isRim(resArr[2])){
            num1 = Number.valueOf(resArr[0]).getTrans();
            num2 = Number.valueOf(resArr[2]).getTrans();

            int res1, res2;
            String res = "";

            int sum = Integer.parseInt(retRes(num1, resArr[1], num2));
            if(sum < 0){
                throw new CalcException("В римской системе нет отрицательных чисел");
            }
            else if(sum <= 10){
                for(Number number: Number.values()){
                    if(number.getTrans() == sum) {
                        return String.valueOf(number);
                    }
                }
            }
            else if(sum > 10 && sum <= 100) {
                res1 = sum - (sum % 10);
                res2 = sum % 10;
                for (Number number : Number.values()) {
                    if (number.getTrans() == res1) {
                        res += String.valueOf(number);
                    }
                }
                for (Number number : Number.values()) {
                    if (number.getTrans() == res2) {
                        res += String.valueOf(number);
                    }
                }
                return res;
            }
        }
        else{
            throw new CalcException("Используются одновременно разные системы счисления");
        }
        return "";
    }

    static boolean isInt(String a){
        try {
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    static boolean isRim(String a){
        try{
            Number.valueOf(a).getTrans();
            return true;
        }catch (IllegalArgumentException x){
            return false;
        }
    }
    static String retRes(int a, String d, int b) throws CalcException {
        if(d.equals("+")){
            return String.valueOf(a + b);
        }
        else if(d.equals("-")){
            return String.valueOf(a - b);
        }
        else if(d.equals("/")){
            return String.valueOf(a / b);
        }
        else if(d.equals("*")){
            return String.valueOf(a * b);
        }
        else{
            throw new CalcException("Выберете доступное действие: * / - +");
        }
    }
}
