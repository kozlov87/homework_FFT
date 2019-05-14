package Task;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void printArray(Complex[] arr) {
        for (Complex c : arr) {
            System.out.print(c + "   ");
        }
        System.out.println();
    }

    public static int checkPower2(int n) {
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

    public static void main(String[] args) throws IOException {
        int num = 0;
        System.out.println("Enter positive numbers of coefficients of your polynom: ");
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();

        int nearestPower2 = checkPower2(num);
        int[] coeffecients = new int[nearestPower2];

        System.out.println("Enter coefficients of your polynom: ");
        for (int i = (nearestPower2 - num); i < nearestPower2; i++) {
            coeffecients[i] = sc.nextInt();
        }

        sc.close();

        //Init complex array
        Complex[] compl_coeff = new Complex[nearestPower2];
        for (int i = 0; i < nearestPower2; i++) {
            compl_coeff[i] = new Complex(0,0);
        }

        for (int i = 0; i < nearestPower2; i++) {
            compl_coeff[i] = new Complex(coeffecients[i], 0);
        }

        //count FFT
        FFT.fft_bit(compl_coeff, false);
        printArray(compl_coeff);

        //count reverse FFT
        FFT.fft_bit(compl_coeff, true);
        printArray(compl_coeff);



    }

}
