import java.io.IOException;

public class CommonFunctions {


    protected static void print(String x)
    {
        System.out.print(x);
    }

    protected static void print_new_line(String x)
    {
        System.out.println(x);
    }

    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
