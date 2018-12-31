int rad = 400;
int ctr = 500;
float mult = 2.0;
float deg = PI;
int clr = 0;

void setup() {
  size(1000, 1000);
  background(255);
  
  noFill();
  ellipse(ctr, ctr, rad * 2, rad * 2);
  stroke(255, 0, 0);
  frameRate(20);
}

void draw() {
  background(255);
  colorMode(HSB, 255);
  stroke(clr % 255, 255, 255);
  
  for (int i = 0; i < 360; i++) {
    line((float) (ctr + Math.cos(deg) * rad), (float) (ctr + Math.sin(deg) * rad), 
         (float) (ctr + Math.cos(deg * mult) * rad), (float) (ctr + Math.sin(deg * mult) * rad));
    deg = PI / 180 * i;
  }
  
  mult += 0.1;
  clr++;
}
