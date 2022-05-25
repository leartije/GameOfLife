package life;

import java.util.Random;

public class Universe {

    private final static String ALIVE = "O";
    private final static String DEAD = " ";

    private final int size;
    private int numOfGenerations;
    private final String[][] currentGeneration;
    private final String[][] nextGeneration;
    private final Random random;

    public Universe(int size, int numOfGenerations) {
        this.size = size;
        this.numOfGenerations = numOfGenerations;
        this.currentGeneration = new String[size][size];
        this.nextGeneration = new String[size][size];
        this.random = new Random();
        initialState();
    }

    public void initialState() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentGeneration[i][j] = random.nextBoolean() ? ALIVE : DEAD;
            }
        }
    }

    public void print() {
        for (String[] strings : currentGeneration) {
            for (int j = 0; j < size; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }

    public void generations() {
        int gen = 1;
        int alive;

        if (numOfGenerations == 0) {
            print();
            return;
        }

        while (numOfGenerations > 0) {
            alive = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int aliveN = aliveNeighbors(i, j);
                    //if it is alive and has 2 or 3 alive neighbors it stays alive
                    if (isAlive(i, j) && (aliveN == 2 || aliveN == 3)) {
                        nextGeneration[i][j] = ALIVE;
                        alive++;
                        continue;
                    }
                    //if it is dead but has 3 alive neighbors then it should be revived
                    if (aliveN == 3) {
                        nextGeneration[i][j] = ALIVE;
                        alive++;
                        continue;
                    }
                    //otherwise it is dead
                    nextGeneration[i][j] = DEAD;

                }
            }

            numOfGenerations--;
            updateCurrentGeneration();
            displayStatistic(gen, alive);
            print();
            gen++;


        }

    }

    public void updateCurrentGeneration() {
        for (int i = 0; i < size; i++) {
            System.arraycopy(nextGeneration[i], 0, currentGeneration[i], 0, size);

        }
    }

    public void displayStatistic(int gen, int alive) {
        System.out.println("Generation #" + gen);
        System.out.println("Alive: " + alive);
    }

    private boolean isAlive(int i, int j) {
        return currentGeneration[i][j].equals(ALIVE);
    }

    public int aliveNeighbors(int i, int j) {

        int size = currentGeneration.length - 1;
        int aliveNeighbors = 0;

        int x;
        int y;

        //nw
        x = i - 1 < 0 ? size : i - 1;
        y = j - 1 < 0 ? size : j - 1;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //n
        y = j;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //ne
        y = j + 1 > size ? 0 : j + 1;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //w
        x = i;
        y = j - 1 < 0 ? size : j - 1;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //e
        y = j + 1 > size ? 0 : j + 1;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //sw
        x = i + 1 > size ? 0 : i + 1;
        y = j - 1 < 0 ? size : j - 1;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //s
        y = j;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        //se
        y = j + 1 > size ? 0 : j + 1;
        aliveNeighbors += isAlive(x, y) ? 1 : 0;

        return aliveNeighbors;
    }

}


