public class NBody {
    public static double readRadius(String fname) {
        In in = new In(fname);
        int pNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fname) {
        In in = new In(fname);
        int pNum = in.readInt();
        double radius = in.readDouble();
        Planet[] pArray = new Planet[pNum];
        for(int i = 0; i < pNum; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String n = in.readString();
            pArray[i] = new Planet(xp, yp, xv, yv, m, n);
        }
        return pArray;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] ps = readPlanets(filename);

        double r = readRadius(filename);



        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-r, r);
        String imageToDraw = "images/starfield.jpg";
        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();
        for(Planet p:ps) {
            p.draw();
        }

        StdAudio.play("audio/2001.mid");



        double t = 0;
        while(t != T) {
            double[] xForces = new double[ps.length];
            double[] yForces = new double[ps.length];
            for(int i = 0; i < ps.length; i++) {
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }

            for(int i = 0; i < ps.length; i++) {
                ps[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-r, r);
            String bg = "images/starfield.jpg";
            StdDraw.picture(0, 0, bg);
            StdDraw.show();

            for(Planet p:ps) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t = t + dt;
        }

        StdAudio.close();
        System.out.println(ps.length);
        System.out.println(r);
        for(Planet p:ps) {
            System.out.println(p.xxPos+" "+p.yyPos+" "+p.xxVel+" "+p.yyVel+" "+p.mass+" "+p.imgFileName);
        }
    }
}
