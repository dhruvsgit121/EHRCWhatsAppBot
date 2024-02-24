package com.EHRC.EHRC.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public boolean isValid(String s) {

        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.

        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(s);

        // Returning boolean value
        return (m.matches());
    }



}
