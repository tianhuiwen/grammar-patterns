package com.thw.top.grammar.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 使用lambda表达式和函数式接口Predicate
 *
 * @author tianhuiwen
 * @date 2018/12/27 22:15
 */
public class PredicateDemo {

    public static void main(String[] args) {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.toString().length() > 4);
    }

    public static void filter(List names, Predicate condition) {
        for (Object name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
