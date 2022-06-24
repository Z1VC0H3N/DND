package Interactions;


import EnemyTypes.Monster;
import EnemyTypes.Trap;
import TILE.*;
import UNIT.*;

import java.util.LinkedList;

public interface Visitor {
    String visit(Player player, LinkedList<Tile> board);
    String visit(Monster monster, LinkedList<Tile> board);
    String visit(Trap t, LinkedList<Tile> board);
    String visit(Wall wall,LinkedList<Tile> board);
    String visit(LinkedList<Enemy> enemies,LinkedList<Tile> board);
    String visit(EmptyTile t,LinkedList<Tile> board);
    }

