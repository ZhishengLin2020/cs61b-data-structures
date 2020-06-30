public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	String imgFileName;

	static final double g = 6.67E-11;

	public Body(double xP, double yP, double xV,
	            double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Body b) {
		double r = calcDistance(b);
		return g * this.mass * b.mass / (r * r);
	}

	public double calcForceExertedByX(Body b) {
		double f = calcForceExertedBy(b);
		double dx = b.xxPos - this.xxPos;
		double r = calcDistance(b);
		return f * dx / r;
	}

	public double calcForceExertedByY(Body b) {
		double f = calcForceExertedBy(b);
		double dy = b.yyPos - this.yyPos;
		double r = calcDistance(b);
		return f * dy / r;
	}

	public double calcNetForceExertedByX(Body[] bodies) {
		double fX = 0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				fX += calcForceExertedByX(b);
			}
		}
		return fX;
	}

	public double calcNetForceExertedByY(Body[] bodies) {
		double fY = 0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				fY += calcForceExertedByY(b);
			}
		}
		return fY;
	}

	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}