package project.computershop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private static Regex regex;
    private final Pattern namePattern;
    private final Pattern nicPattern;
    private final Pattern telephoneNumberPattern;
    private final Pattern emailPattern;
    private final Pattern pricePattern;
    private Regex() {
        namePattern = Pattern.compile("^[a-zA-Z.+=@\\-_\\s]{3,50}$");
        nicPattern = Pattern.compile("^[A-Z]{1,2}[0-9]{6}$");
        telephoneNumberPattern = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
        emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");;
        pricePattern = Pattern.compile("^(\\d+)||((\\d+\\.)(\\d){2})$");
    }
    public static Regex getInstance(){
        return regex==null?(regex=new Regex()):regex;
    }

    public boolean isValid(RegexType regexType,String text){
        Matcher matcher;
        switch (regexType){
            case NAME:
                matcher = namePattern.matcher(text);
                return matcher.matches();
            case NIC:
                matcher = nicPattern.matcher(text);
                return matcher.matches();
            case TELEPHONE_NUMBER:
                matcher = telephoneNumberPattern.matcher(text);
                return matcher.matches();
            case EMAIL:
                matcher = emailPattern.matcher(text);
                return matcher.matches();
            case PRICE:
                matcher = pricePattern.matcher(text);
                return matcher.matches();
            default:
                return false;
        }
    }
}

