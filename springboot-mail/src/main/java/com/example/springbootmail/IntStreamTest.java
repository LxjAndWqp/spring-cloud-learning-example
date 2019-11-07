package com.example.springbootmail;

import java.util.stream.IntStream;

public class IntStreamTest {

    public static void main(String[] args) {
        IntStream.range(1,100).filter(i -> i % 2 == 0).forEach(System.out::print);
    }
}
