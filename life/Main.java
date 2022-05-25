package life;

import java.util.Scanner;

public class Main {
    public static Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        int size = SCANNER.nextInt();

        new Universe(size, 100).generations();

    }
}
