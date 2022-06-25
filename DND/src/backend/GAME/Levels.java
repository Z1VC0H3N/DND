package backend.GAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Levels{

    public File[] getLevels() throws IOException {
        File input = new File("C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir");
        File[] st = input.listFiles();
        for (int i = 0; i < st.length; i++) {
            if(st[i].isFile()){//other condition like name ends in html
                String content = "";
                BufferedReader in = new BufferedReader(new FileReader(st[i]));
                String str;
                while ((str = in.readLine()) != null) {
                    content +=str;
                    System.out.println(str);
                }
            }
        }
        return st;

    }
}
