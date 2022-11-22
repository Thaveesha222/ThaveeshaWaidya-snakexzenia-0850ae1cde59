import java.util.Random;

public class Maze extends CommonFunctions {

    private int length = 0;
    private int width = 0;
    private Random rand = null;
    private boolean generate_food_status = true;
    private int food_x = 0;
    private int food_y = 0;
    private static Snake snake;

    public Maze(int length, int width, Snake snake) {
        this.length = length;
        this.width = width;
        Maze.snake = snake;
        rand = new Random();
    }

    protected void drawMaze() throws GameOverException {
        int snake_x = snake.getCurrent_head_x();
        int snake_y = snake.getCurrent_head_y();
        String snake_direction = snake.getCurrent_direction();
        int length = snake.getLength();
        int snake_length = snake.getLength();
        if (generate_food_status == true) {
            food_x = rand.nextInt(this.length);
            food_y = rand.nextInt(width);
            generate_food_status = false;
        }
        if (snake_direction == Snake.FORWARD) {
            print("|");
            drawHorizontalLine();
            print_new_line("|");
            print("|");
            for (int y = 0; y < this.width; y++) {
                for (int x = 0; x < this.length; x++) {
                    if (y == snake_y && x == snake_x) {
                        for (int i = 0; i < length; i++) {
                            print("-");
                        }
                        print("o");
                        x = x + snake_length + 1;
                    }
                    if (checkIfPostionHasFood(food_x, food_y, y, x)) {
                        spawnFood();
                    } else {
                        print(" ");
                    }
                }
                print_new_line("|");
                print("|");
            }
            for (int x = 0; x < this.length; x++) {
                if (checkIfPostionHasFood(food_x, food_y, this.width, x)) {
                    spawnFood();
                } else {
                    print(" ");
                }
            }
            print_new_line("|");
            print("|");
            drawHorizontalLine();
            print("|");

        } else if (snake_direction == Snake.BACKWARD) {
            print("|");
            drawHorizontalLine();
            print_new_line("|");
            print("|");
            for (int y = 0; y < this.width; y++) {
                for (int x = 0; x < this.length; x++) {
                    if (y == snake_y && x == snake_x) {
                        print("o");
                        for (int i = 0; i < length; i++) {
                            print("-");
                        }
                        x = x + snake_length + 1;
                    }
                    if (checkIfPostionHasFood(food_x, food_y, y, x)) {
                        spawnFood();
                    } else {
                        print(" ");
                    }
                }
                print_new_line("|");
                print("|");
            }
            for (int x = 0; x < this.length; x++) {
                if (checkIfPostionHasFood(food_x, food_y, this.width, x)) {
                    spawnFood();
                } else {
                    print(" ");
                }
            }
            print_new_line("|");
            print("|");
            drawHorizontalLine();
            print("|");

        } else if (snake_direction == Snake.UPWARD) {
            boolean head_printed_status = false;
            print("|");
            drawHorizontalLine();
            print_new_line("|");
            print("|");
            for (int y = 0; y < this.width; y++) {
                for (int x = 0; x < this.length; x++) {
                    if (head_printed_status == true && y < (snake_y + length) && x == snake_x) {
                        print("|");
                        x = x + 1;
                        if (y == snake_y + length) {
                            head_printed_status = false;
                        }
                    } else if (y == snake_y && x == snake_x) {
                        print("o");
                        x = x + 1;
                        head_printed_status = true;
                    }
                    if (checkIfPostionHasFood(food_x, food_y, y, x)) {
                        spawnFood();
                    } else {
                        print(" ");
                    }
                }
                print_new_line("|");
                print("|");
            }
            for (int x = 0; x < this.length; x++) {
                print(" ");
            }
            print_new_line("|");
            print("|");
            drawHorizontalLine();
            print("|");
        } else if (snake_direction == Snake.DOWNWARD) {
            print("|");
            drawHorizontalLine();
            print_new_line("|");
            print("|");
            int count_of_tail_segments_printed = 0;
            for (int y = 0; y < this.width; y++) {
                for (int x = 0; x < this.length; x++) {
                    if (y == snake_y && x == snake_x) {
                        print("o");
                        x = x + 1;
                    }
                    if (y > (snake_y - length) && x == snake_x) {
                        if (count_of_tail_segments_printed < (length - 1)) {
                            print("|");
                            count_of_tail_segments_printed++;
                        } else {
                            if (checkIfPostionHasFood(food_x, food_y, y, x)) {
                                spawnFood();
                            } else {
                                print(" ");
                            }
                        }
                        x = x + 1;
                    }
                    if (checkIfPostionHasFood(food_x, food_y, y, x)) {
                        spawnFood();
                    } else {
                        print(" ");
                    }
                }
                print_new_line("|");
                print("|");
            }
            for (int x = 0; x < this.length; x++) {
                print(" ");
            }
            print_new_line("|");
            print("|");
            drawHorizontalLine();
            print("|");
        }
        checkIfSnakeHasEatenFood(food_x, food_y, snake_y, snake_x);
        checkIfGameOver(snake_x, snake_y);
    }

    private void spawnFood() {
        print("*");
    }

    private boolean checkIfSnakeHasEatenFood(int food_x, int food_y, int snake_y, int snake_x) {
        if (food_x == snake_x) {
            if (food_y == snake_y) {
                snake.incrementScore();
                snake.incrementLength();
                generate_food_status = true;
                return true;
            }
        }
        generate_food_status = false;
        return false;
    }

    private boolean checkIfPostionHasFood(int food_x, int food_y, int y, int x) {
        if (food_x == x) {
            if (food_y == y) {
                return true;
            }
        }
        return false;
    }

    private void drawHorizontalLine() {
        for (int x = 0; x < this.length; x++) {
            print("-");
        }
    }

    private void checkIfGameOver(int snake_x, int snake_y) throws GameOverException {
        if (snake_x >= length - 4 || snake_y >= width || snake_x < 0 || snake_y < 0) {
            throw new GameOverException("Game over");
        }
    }
}
