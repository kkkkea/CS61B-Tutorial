public class NBody {
    public static double readRadius(String path) {
        In reader = new In(path);
        reader.readInt();
        return reader.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In reader = new In(path);
        int n = reader.readInt();
        reader.readDouble();
        Planet[] res = new Planet[n];

        double xp;
        double yp;
        double xv;
        double yv;
        double m;
        String im;

        for (int i = 0; i < res.length; i++) {
            xp = reader.readDouble();
            yp = reader.readDouble();
            xv = reader.readDouble();
            yv = reader.readDouble();
            m = reader.readDouble();
            im = reader.readString();
            res[i] = new Planet(xp, yp, xv, yv, m, im);
        }
        return res;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        double time = 0;
        int pausetime = 10;
        double[] xForce = new double[planets.length];
        double[] yForce = new double[planets.length];
        while (time < T) {
            StdDraw.clear();
            for (int i = 0; i < planets.length; ++i) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; ++i) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(pausetime);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
