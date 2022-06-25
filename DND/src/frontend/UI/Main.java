package frontend.UI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Please run the code as follows:");
            System.out.println("java -jar hw3.jar <Path to directory of files>");
        }
        else {
            RunGame runGame = new RunGame();
            try {
                runGame.startGame(args);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}