int range = 3;
float incr = 0.005;

void setup() {
  size(900, 900);
  colorMode(HSB, 2 * PI, 1, 1);
  strokeWeight(1.5);

  for (float r = -range; r <= range; r += incr) {
    for (float i = -range; i <= range; i += incr) {
      Complex c = new Complex(r, i);
      Complex func = c.sqr().sub(1).mult(c.sub(2).sub(new Complex(0, 1))).div(c.sqr().add(2).add(new Complex(0, 2)));
      
      float H = func.arg();
      float S = 1;
      float B = 1 - pow(0.1, func.abs());

      stroke(H, S, B);
      point((r + range) * (450 / range), (i + range) * (450 / range));
    }
  }
}
