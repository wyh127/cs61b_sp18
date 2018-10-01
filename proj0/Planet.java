public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet q) {
        double dSquare = Math.pow(this.xxPos - q.xxPos, 2) + Math.pow(this.yyPos - q.yyPos, 2);
        return Math.sqrt(dSquare);
    }

    public double calcForceExertedBy(Planet q) {
        double dis = this.calcDistance(q);
        return G * this.mass * q.mass / (dis * dis);
    }

    public double calcForceExertedByX(Planet q) {
        return this.calcForceExertedBy(q)* (q.xxPos-this.xxPos) / this.calcDistance(q);
    }

    public double calcForceExertedByY(Planet q) {
        return this.calcForceExertedBy(q)* (q.yyPos-this.yyPos) / this.calcDistance(q);
    }

    public double calcNetForceExertedByX(Planet[] qs) {
        double netX = 0;
        for(Planet q:qs) {
            if(!this.equals(q)) {
                netX = netX + this.calcForceExertedByX(q);
            }
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] qs) {
        double netY = 0;
        for(Planet q:qs) {
            if(!this.equals(q)) {
                netY = netY + this.calcForceExertedByY(q);
            }
        }
        return netY;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;

        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
        StdDraw.show();
    }


















}
