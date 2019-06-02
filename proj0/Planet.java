public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet p) {
		return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
	}

	public double calcForceExertedBy(Planet p) {
		double G = 6.67e-11;
		return G * mass * p.mass / ((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
	}

	public double calcForceExertedByX(Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0;
		for (int i = 0; i < planets.length; i++) {
			if (this.calcDistance(planets[i]) != 0) {
				netForceX += this.calcForceExertedByX(planets[i]);
			}
			
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0;
		for (int i = 0; i < planets.length; i++) {
			if (this.calcDistance(planets[i]) != 0) {
				netForceY += this.calcForceExertedByY(planets[i]);
			}
		}
		return netForceY;
	}

	public void update(double dt, double xForce, double yForce) {
		xxVel += dt * (xForce) / mass;
		yyVel += dt * (yForce) / mass;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}
	public void draw() {
		String fn = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, fn);
	}


}