package life;

import java.util.Scanner;

public class Main {
    public static Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        int n = SCANNER.nextInt();

        new Universe(n, 100).generations();

    }
}
