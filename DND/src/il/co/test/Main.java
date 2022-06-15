package il.co.test;

import EnemyTypes.*;
import GAME.Board;
import Rogues.*;
import TILE.*;
import UI.RunGame;
import UNIT.*;
import UTILITY.*;
import Hunters.*;
import Mages.*;
import Warriors.*;

import javax.swing.plaf.synth.SynthRootPaneUI;
import java.io.File;
import java.io.IOException;
import java.util.*;

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