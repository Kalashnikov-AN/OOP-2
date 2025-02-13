package ZabSu;

import java.sql.SQLOutput;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        telephone_sub example = new telephone_sub("+7(924)500-00-00", "000000", "Medium", "Ivan", 528);
        System.out.println(example.getPhone_number());
        System.out.println(example.getAccount_number());
        System.out.println(example.getTariff());
        System.out.println(example.getName());
    }
}