import java.lang.Math;

class Complex {

  float real;
  float imag;
  
  Complex(float real, float imag) {
    this.real = real;
    this.imag = imag;
  }
  
  float abs() {
    return sqrt(real * real + imag * imag); 
  }
  
  Complex add(Complex addend) {
    return new Complex(real + addend.real, imag + addend.imag);
  }
  
  Complex add(float addend) {
    return new Complex(real + addend, imag); 
  }
  
  float arg() {
    return atan2(imag, real) + PI;
  }
  
  Complex div(Complex divisor) {
    float r = divisor.real;
    float i = divisor.imag;
    
    float r2 = pow(r, 2);
    float i2 = pow(i, 2);
    
    float rr = real * r;
    float ri = real * i;
    float ir = imag * r;
    float ii = imag * i;
    
    return new Complex((rr + ii) / (r2 + i2), (ir - ri) / (r2 + i2));
  }
  
  Complex mult(Complex multiplier) {
    return new Complex(real * multiplier.real - imag * multiplier.imag,
                       real * multiplier.imag + imag * multiplier.real);
  }
  
  Complex sqr() {
    return new Complex(real * real + imag * imag, 2 * real * imag);
  }
  
  Complex sub(Complex subtrahend) {
    return new Complex(real - subtrahend.real, imag - subtrahend.imag);  
  }
  
  Complex sub(float subtrahend) {
    return new Complex(real - subtrahend, imag);  
  } 

}
