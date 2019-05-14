package Task;

import Task.Complex;

/**
 * Class with two implementations of FFT
 */
public class FFT {

    public static void fft_bit(Complex[] data, boolean invert) {
        int n = data.length;

        // doing bit-reversal permutation and if (i < j) -> swap element_i and element_j
        for (int i = 1, j = 0; i < n; i++) {
            //find pair (i, j) to reverse
            int bit = n >> 1;
            for(; j >= bit; bit >>= 1) {
                j -= bit;
            }
            j += bit;

            // swap
            if (i < j) {
                Complex tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
            }
        }

        // counting DFT for blocks
        for (int k = 2; k <= n; k <<= 1) {
            //root arg
            double ARG = 2*Math.PI/k * (invert ? -1 : 1);
            Complex root = new Complex(Math.cos(ARG), Math.sin(ARG));
            for (int i = 0; i < n; i += k) {
                Complex current_root = new Complex(1, 0);
                // butterfly method (a + b; a - b) for count values
                for (int j = 0; j < k / 2; ++j) {
                    Complex c1 = data[i + j];
                    Complex c2 = data[i + j + k / 2].multiply(current_root);

                    //new values
                    data[i + j] = c1.plus(c2);
                    data[i + j + k / 2] = c1.minus(c2);
                    current_root = current_root.multiply(root);
                }
            }
        }

        //Check if (invert)
        // invert == true? -> count reverse matrix
        if (invert) {
            Complex compl_n = new Complex(n, 0);
            for (int i = 0; i < n; i++) {
                data[i] = data[i].divides(compl_n);
            }
        }

    }

    public static void fft_recursion(Complex[] data, boolean invert) {
        int n = data.length;
        if (n == 1) {
            return;
        }

        //Create two arrays for splitting
        Complex[] data1 = new Complex[n / 2];
        Complex[] data2 = new Complex[n / 2];
        for (int i = 0, j = 0; i < n; i += 2, ++j) {
            data1[j] = data[i];
            data2[j] = data[i + 1];
        }

        fft_recursion(data1, invert);
        fft_recursion(data2, invert);

        //root arg
        double ARG = 2*Math.PI/n * (invert ? -1 : 1);
        Complex root = new Complex(Math.cos(ARG), Math.sin(ARG)); //wn = root

        Complex current_root = new Complex(1, 0);
        for (int i = 0; i < n / 2; i++) {
            data[i] = data1[i].plus(current_root.multiply(data2[i]));
            data[i + n / 2] = data1[i].minus(current_root.multiply(data2[i]));

            //Check for invert
            if (invert) {
                data[i] = data[i].divides(new Complex(2, 0));
                data[i + n / 2] = data[i + n / 2].divides(new Complex(2, 0));
            }

            current_root = current_root.multiply(root);
        }

    }
}