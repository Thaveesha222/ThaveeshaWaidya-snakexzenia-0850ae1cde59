import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends CommonFunctions {

    public static String current_direction = Snake.FORWARD;

    public static void main(String[] args) throws IOException, InterruptedException {
        clearScreen();
        printHomeScreen();
        Thread.sleep(2000);
        clearScreen();
        Player current_player = Player.selectPlayerProfile();
        Snake snake = new Snake(0, 0, 2);
        Maze maze = new Maze(100, 10, snake);
        snake.setCurrent_direction(current_direction);

        JFrame frame = new JFrame("Key Listner");
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A) {
                    current_direction = Snake.BACKWARD;
                } else if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D) {
                    current_direction = Snake.FORWARD;
                } else if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W) {
                    current_direction = Snake.UPWARD;
                } else if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S) {
                    current_direction = Snake.DOWNWARD;
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }
        };
        frame.addKeyListener(listener);
        frame.setSize(200, 200);
        frame.setVisible(true);
        try {
            while (true) {
                if (current_direction == Snake.FORWARD) {
                    snake.moveForward();
                } else if (current_direction == Snake.BACKWARD) {
                    snake.moveBackward();
                } else if (current_direction == Snake.UPWARD) {
                    snake.moveUp();
                } else if (current_direction == Snake.DOWNWARD) {
                    snake.moveDown();
                }
                maze.drawMaze();
                Thread.sleep(150);
                clearScreen();
            }
        } catch (GameOverException e) {
            clearScreen();
            printGameOver();
            print_new_line("");
            print_new_line("");
            print_new_line("Your Score is :" + snake.getScore());
        } finally {
            Writer output;
            output = new BufferedWriter(new FileWriter("player_profile_" + current_player.getId() + ".txt", true));
            output.append(String.valueOf(snake.getScore()+"\n"));
            output.close();
            Thread.sleep(2000);
            clearScreen();
            Player.displayLeaderboard();
            System.exit(0);
        }
    }

    public static void printHomeScreen() {
        print_new_line("┌───────────┐  ┌──       ┌─┐                       ┌─┐            ┌────────────┐");
        print_new_line("├─┼─────────┘  │xx       │ │         xx            │ │            ├─┼──────────┘");
        print_new_line("│ │            │ xx      │ │        xxxx           │ │    xxx     │ │");
        print_new_line("│ │            │ │xxx    │ │      xxx  xxx         │ │  xxx       ├─┼─────┐");
        print_new_line("├─┼────────┐   │ │  xx   │ │     xxx    xxx        │ xxx          ├─┼─────┘");
        print_new_line("└────────┼─┤   │ │   xx  │ │    xxx      xxx       │ x            │ │");
        print_new_line("         │ │   | |     xx│ │   xxxxxxxxxxxxxx      │ xx           │ │");
        print_new_line("         │ │   | |      x│ │   xx         xxx      │ │ xx         │ │");
        print_new_line("┌────────┤ │   │ │       │x│  xxx          xxx     │ │  xxx       ├─┼──────────┐");
        print_new_line("└────────┴─┘   └─┘       └─┘  xx            xxx    └─┘    xxx     └────────────┘");
    }

    public static void printGameOver(){
        print_new_line(" xxxxx     x     xx   xx  xxxxx        xxxx  x    x  xxxxxx  xxxx");
        print_new_line("xx        xxx    xxxxxxx  x           x   xx xx  xx  xx      x  xx");
        print_new_line("x   xx   xx xx   x  xx x  xxx         x    x  x xx   xxxx    xxxx");
        print_new_line("xx  xx  xxxxxxx  x     x  x           xx  xx  xxx    xx      xxxx");
        print_new_line(" xxxxx xx     xx x     x  xxxxx        xxxx    x     xxxxxx  x  xx");
    }
}
