package Task;

/**
 * Simple class with the implementation of basic functions for working with complex numbers
 */
public class Complex {
    private final double Re;
    private final double Im;

    /**
     * Constructor for creating new Complex number
     * @param Re double part of new complex number
     * @param Im imaginary part of new complex number
     */
    public Complex(double Re, double Im) {
        this.Re = Re;
        this.Im = Im;
    }

    /**
     * [Complex1] + [Complex2]
     * @param c2 added complex number
     * @return sum of two complex numbers
     */
    public Complex plus(Complex c2) {
        Complex c1 = this;
        double Re = c1.Re + c2.Re;
        double Im = c1.Im + c2.Im;

        return new Complex(Re, Im);
    }

    /**
     * [Complex1] - [Complex2]
     * @param c2 subtrahend complex number
     * @return difference of two complex numbers
     */
    public Complex minus(Complex c2) {
        Complex c1 = this;
        double Re = c1.Re - c2.Re;
        double Im = c1.Im - c2.Im;

        return new Complex(Re, Im);
    }

    /**
     * [Complex1] * [Complex2]
     * @param с2 second factor complex number
     * @return multiplication of two complex numbers
     */
    public Complex multiply(Complex с2) {
        Complex с1 = this;
        double Re = с1.Re * с2.Re - с1.Im * с2.Im;
        double Im = с1.Re * с2.Im + с1.Im * с2.Re;
        return new Complex(Re, Im);
    }

    /**
     * dividing auxiliary function of two complex numbers
     * @return a new comlex number which value is inverse current
     */
    public Complex reverse() {
        double sq_coeff = Re*Re + Im*Im;
        return new Complex(Re / sq_coeff, (-Im) / sq_coeff);
    }

    /**
     * [Complex1] / [Complex2]
     * @param c2 divider complex number
     * @return quotient of two complex numbers
     */
    public Complex divides(Complex c2) {
        Complex c1 = this;
        return c1.multiply(c2.reverse());
    }

    /**
     * Override method to print complex number
     * @return
     */
    @Override
    public String toString() {
        if (Im == 0)  return Re + "i";
        if (Re == 0)  return Im + "";
        if (Im < 0) return Re + " - " + (-Im) + "i";

        return Re + " + " + Im + "i";
    }


}