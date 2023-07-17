public class TestPlanet {

    public static void main(String[] args) { checkPlanet(); }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    public static void checkPlanet() {
        Planet p = new Planet(0, 0, 0, 0, 1, null);
        Planet p1 = new Planet(3, 4, 0, 0, 1, null);

        checkEquals(Planet.G / 25, p.calcForceExertedBy(p1), "pairwise force", 0.01);
    }
}
