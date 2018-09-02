package com.company;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        System.out.println("CALCULATOR\n");
        Calculator c = new CalculatorImpl();
        System.out.println(c.Evaluate("(1+38.358647)*4-5")); // Result: 151
        System.out.println(c.Evaluate("7*6/2+8")); // Result: 29
        System.out.println(c.Evaluate("-12)1//(")); // Result: null

        System.out.println("PYRAMID BUILDER\n");
        PyramidBuilder p = new PyramidBuilder();
        try {
            int a[] = {3,5,1,9,7};
            p.getPyramids(a);
        } catch (CannotBuildPyramidException e) {
            System.out.println(e);
        }


        System.out.println("Subsequence\n");
        Subsequence s = new Subsequence();
        boolean b = s.find(Arrays.asList("A", "B", "C", "D"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));
        System.out.println(b); // Result: true



    }
}
