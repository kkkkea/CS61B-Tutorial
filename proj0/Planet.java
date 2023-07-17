public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xp, double yp, double xv, double yv, double m, String img) {
        this.xxPos = xp;
        this.yyPos = yp;
        this.xxVel = xv;
        this.yyVel = yv;
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

    public double calcDistance(Planet p) {
        double xD = this.xxPos - p.xxPos;
        double yD = this.yyPos - p.yyPos;

        return Math.sqrt(Math.pow(xD, 2) + Math.pow(yD, 2));
    }

    public double calcForceExertedBy(Planet p) {
        return Planet.G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double R = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return F * dx / R;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double R = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return F * dy / R;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double ans = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                ans += this.calcForceExertedByX(p);
            }
        }
        return ans;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double ans = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                ans += this.calcForceExertedByY(p);
            }
        }
        return ans;
    }

    private boolean equals(Planet p) {
        return this == p;
    }

    public void update(double times, double xF, double yF) {
        double xa = xF / this.mass;
        double ya = yF / this.mass;
        this.xxVel += xa * times;
        this.yyVel += ya * times;
        this.xxPos += this.xxVel * times;
        this.yyPos += this.yyVel * times;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
