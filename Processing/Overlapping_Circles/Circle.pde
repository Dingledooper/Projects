  float d;
  float clr;
  float xS;
  float yS;
  
  Circle(float x, float y, float d, float clr, float xS, float yS) {
    this.x = x;
    this.y = y;
    this.d = d;
    this.clr = clr;
    this.xS = xS;
    this.yS = yS;
  }
  
  void grab() {
    fill(clr, 255, 255, 75);
    ellipse(x, y, d, d);
  }
  
  void move() {
    x += xS;
    y += yS;
  }
  
  void bound() {
    float r = d / 2;
    if (x <= -r) {
      x = 1000 + r;
    } else if (x >= 1000 + r) {
      x = -r; 
    } else if (y <= -r) {
      y = 1000 + r;
    } else if (y >= 1000 + r) {
      y = -r;
    }
  }
} 
