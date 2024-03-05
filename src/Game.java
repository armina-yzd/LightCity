import java.awt.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) {
        try {
             Menu.ShowMenu();
        } catch (HeadlessException | IOException e) {
             e.printStackTrace();
        }

    }
}