package Tests;

import Task.Complex;
import org.junit.Test;

import Task.*;

public class Tests_FFT {

    public Complex[] multiplyArrays(Complex[] arr1, Complex[] arr2) {
        int n = arr1.length;

        FFT.fft_recursion(arr1, false);
        FFT.fft_recursion(arr2, false);

        for (int i = 0; i < n; i++) {
            arr1[i] = arr1[i].multiply(arr2[i]);
        }

        FFT.fft_recursion(arr1, true);
        return arr1;
    }

    public void printArray(Complex[] arr) {
        for (Complex c : arr) {
            System.out.print(c + "   ");
        }
        System.out.println();
    }

    public int checkPower2(int n) {
        if (n == 1) {
            return 2;
        } else {
            int res = 1;
            while (res < n) {
                res <<= 1;
            }
            return res;
        }
    }

    @Test
    public void testPolynomBit() {
        int[] polynom = {1, 2, 3, 4};

        Complex[] compl_coeff = new Complex[polynom.length];
        for (int i = 0; i < polynom.length; i++) {
            compl_coeff[i] = new Complex(polynom[i], 0);
        }

        //FFT
        FFT.fft_bit(compl_coeff, false);
        printArray(compl_coeff);

        //Reverse FFT
        FFT.fft_bit(compl_coeff, true);
        printArray(compl_coeff);
    }

    @Test
    public void testPolynomRecursion() {
        int[] polynom = {1, 2, 3, 4};

        Complex[] compl_coeff = new Complex[polynom.length];
        for (int i = 0; i < polynom.length; i++) {
            compl_coeff[i] = new Complex(polynom[i], 0);
        }

        FFT.fft_recursion(compl_coeff, false);
        printArray(compl_coeff);
        FFT.fft_recursion(compl_coeff, true);
        printArray(compl_coeff);

    }

    @Test
    public void testMultiplyPolynomsSameLength() {
        int[] polynom1 = {-2, 5, 6, -1};
        int[] polynom2 = {1, 2, 3, 4};

        //init
        Complex[] compl_coeff1 = new Complex[2*polynom1.length];
        Complex[] compl_coeff2 = new Complex[2*polynom2.length];
        for (int i = 0; i < compl_coeff1.length; i++) {
            compl_coeff1[i] = new Complex(0, 0);
            compl_coeff2[i] = new Complex(0, 0);
        }
        for (int i = 0; i < polynom1.length; i++) {
            compl_coeff1[i + polynom1.length] = new Complex(polynom1[i], 0);
            compl_coeff2[i + polynom2.length] = new Complex(polynom2[i], 0);
        }

        printArray(multiplyArrays(compl_coeff1, compl_coeff2));
    }

    @Test
    public void testMultiplyPolynomsDifferentLength() {
        int[] polynom1 = {-2, 6, -1, 13};
        int[] polynom2 = {1, 2, 3, 4, 5, 6, 7};

        int max_len = Math.max(polynom1.length, polynom2.length);
        max_len = checkPower2(max_len);

        Complex[] compl_coeff1 = new Complex[2*max_len];
        Complex[] compl_coeff2 = new Complex[2*max_len];
        for (int i = 0; i < compl_coeff1.length; i++) {
            compl_coeff1[i] = new Complex(0, 0);
            compl_coeff2[i] = new Complex(0, 0);
        }

        for (int i = 0; i < polynom1.length ; i++) {
            compl_coeff1[i + (2*max_len - polynom1.length)] = new Complex(polynom1[i], 0);
        }
        for (int i = 0; i < polynom2.length ; i++) {
            compl_coeff2[i + (2*max_len - polynom2.length)] = new Complex(polynom2[i], 0);
        }

        printArray(multiplyArrays(compl_coeff1, compl_coeff2));
    }

}

