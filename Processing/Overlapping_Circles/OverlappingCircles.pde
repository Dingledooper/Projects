ArrayList<Circle> circles;

void setup() {
  size(1000, 1000);
  noStroke();
  
  circles = new ArrayList<Circle>();
  for (int i = 0; i < 50; i++) {
    float x = random(1000);
    float y = random(1000);
    float d = random(10, 200);
    float clr = random(0, 255);
    float xS = random(-5, 5);
    float yS = random(-5, 5);
    
    fill(0, 0, 255);
    colorMode(HSB, 255); 

    circles.add(new Circle(x, y, d, clr, xS, yS));
  }
}

void draw() {
  background(0);
  for (Circle c : circles) {
      c.grab();
      c.move();
      c.bound();
  }
}
