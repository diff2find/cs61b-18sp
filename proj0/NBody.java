public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);	

		StdDraw.enableDoubleBuffering();
		/** Sets up the universe so it goes from 
		  * -100, -100 up to 100, 100 */
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/* Clears the drawing window. */
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}
		StdDraw.show();
		StdDraw.clear();
		for (double t = 0; t < T; t = t + dt) {
			double[] xForce = new double[planets.length];
			double[] yForce = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForce[i] = planets[i].calcNetForceExertedByX(planets);
				yForce[i] = planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForce[i], yForce[i]);
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			StdDraw.clear();
		}
		StdDraw.pause(2000);

	}

	public static double readRadius(String filePath) {
		In in = new In(filePath);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filePath) {
		In in = new In(filePath);
		
		Planet[] planets = new Planet[in.readInt()];
		double radius = in.readDouble();
		for(int i = 0; i < planets.length; i++) {
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return planets;
	}

	
}