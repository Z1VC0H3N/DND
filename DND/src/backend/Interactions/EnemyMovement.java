package backend.Interactions;

import backend.EnemyTypes.Monster;
import backend.EnemyTypes.Trap;
import backend.TILE.EmptyTile;
import backend.UNIT.Tile;
import backend.TILE.Wall;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import backend.UTILITY.Position;

import java.util.LinkedList;

public class EnemyMovement extends Movement {
    private Enemy e;
    public EnemyMovement(Enemy enemy){
        this.e=enemy;
    }
    public String visit(Player player, LinkedList<Tile> board) {
        return e.attack(player);
    }

    @Override
    public String visit(Monster monster, LinkedList<Tile> board) {
        return "";
    }

    @Override
    public String visit(Trap t, LinkedList<Tile> board) {
        return "";
    }

    @Override
    public String visit(Wall wall, LinkedList<Tile> board) {
        return "";
    }

    @Override
    public String visit(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return "";
    }

    @Override
    public String visit(EmptyTile t, LinkedList<Tile> board) {
        if(e.range(t)<=1) {
            int idxe=0;
            int idxT =0;
            Position idxE=new Position(0, 0);
            Position idxTile=new Position(0, 0);
            for(Tile tile :board){
                if(tile.equals(t)){
                    idxTile=tile.getPosition();
                    idxT= board.indexOf(tile);
                }
                if(tile.equals(e)){
                    idxE= tile.getPosition();
                    idxe = board.indexOf(tile);
                }
            }
            e.setPosition(idxTile);
            t.setPosition(idxE);
            board.remove(idxT);
            board.add(idxT,e);
            board.remove(idxe);
            board.add(idxe,t);
        }
        return "";
    }
}
