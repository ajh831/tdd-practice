package com.example;

import com.example.exception.InvalidOperatorException;
import com.example.sample.CalculationRequest;
import com.example.sample.CalculationRequestReader;
import com.example.sample.Calculator;

import java.util.Scanner;

public class SampleApplication {
    public static void main(String[] args) {
        CalculationRequest calculationRequest = new CalculationRequestReader().read();
        long answer = new Calculator().calculate(
                calculationRequest.getNum1(),
                calculationRequest.getOperator(),
                calculationRequest.getNum2()
        );
        System.out.println(answer);
    }
}