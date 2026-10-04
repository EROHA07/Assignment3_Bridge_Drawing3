public class Main {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {

        runT1();
        runT2();
        runT3();
        runT4();
        runT5();
        runT6();
        runT7();

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static void check(String id, boolean condition, String details) {
        total++;

        if (condition) {
            passed++;
            System.out.println(id + " PASS | " + details);
        } else {
            System.out.println(id + " FAIL | " + details);
        }
    }

    private static void runT1() {
        Circle circle = new Circle(1, 2, new VectorRenderer());
        String actual = circle.execute();

        check("T1",
                actual.equals("VECTOR circle radius=2"),
                "Circle + VectorRenderer | result=" + actual);
    }

    private static void runT2() {
        Circle circle = new Circle(1, 2, new RasterRenderer());
        String actual = circle.execute();

        check("T2",
                actual.equals("RASTER circle radius=2"),
                "Circle + RasterRenderer | result=" + actual);
    }

    private static void runT3() {
        Square square = new Square(2, 3, new VectorRenderer());
        String actual = square.execute();

        check("T3",
                actual.equals("VECTOR square side=3"),
                "Square + VectorRenderer | result=" + actual);
    }

    private static void runT4() {
        Square square = new Square(2, 3, new RasterRenderer());
        String actual = square.execute();

        check("T4",
                actual.equals("RASTER square side=3"),
                "Square + RasterRenderer | result=" + actual);
    }

    private static void runT5() {
        Circle circle = new Circle(10, 2, new VectorRenderer());

        Shape originalReference = circle;

        String before = circle.execute();
        int idBefore = circle.getId();

        circle.setImplementation(new RasterRenderer());

        Shape afterReference = circle;

        String after = circle.execute();
        int idAfter = circle.getId();

        boolean sameObject = originalReference == afterReference;
        boolean dataUnchanged = idBefore == idAfter;

        boolean correct = sameObject
                && dataUnchanged
                && before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2");

        check("T5",
                correct,
                "sameObject=" + sameObject
                        + " | dataUnchanged=" + dataUnchanged
                        + " | before=" + before
                        + " | after=" + after);
    }

    private static void runT6() {
        Circle circle = new Circle(3, 2, new AsciiRenderer());
        String actual = circle.execute();

        check("T6",
                actual.equals("ASCII circle radius=2"),
                "Circle + AsciiRenderer | result=" + actual);
    }

    private static void runT7() {
        Square square = new Square(4, 3, new AsciiRenderer());
        String actual = square.execute();

        check("T7",
                actual.equals("ASCII square side=3"),
                "Square + AsciiRenderer | result=" + actual);
    }
}//TIP To <b>Run</b> code, press <shortcut actionId="Run