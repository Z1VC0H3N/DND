package backend.Interactions;

import backend.EnemyTypes.Monster;
import backend.EnemyTypes.Trap;
import backend.TILE.EmptyTile;
import backend.TILE.Tile;
import backend.TILE.Wall;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import backend.UTILITY.Position;

import java.util.LinkedList;

public class PlayerMovement extends Movement{
    protected Player player;
    public PlayerMovement(Player p){
        this.player=p;
    }
    @Override
    public String visit(Player player, LinkedList<Tile> board) {
        return "";
    }

    @Override
    public String visit(Monster monster, LinkedList<Tile> board) {
        return player.attack(monster,board);
    }

    @Override
    public String visit(Trap t, LinkedList<Tile> board) {
        return player.attack(t,board);
    }

    @Override
    public String visit(Wall wall, LinkedList<Tile> board) {
         return "";
    }

    @Override
    public String visit(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        String s=player.cast(enemies, board);
        return s;
    }

    @Override
    public String visit(EmptyTile t, LinkedList<Tile> board) {
        if(player.range(t)<=1) {
            int idxP=0;
            int idxT =0;
            Position idxPlayer=new Position(0, 0);
            Position idxTile=new Position(0, 0);
            for(Tile tile :board){
                if(tile.equals(t)){
                    idxTile=tile.getPosition();
                    idxT= board.indexOf(tile);
                }
                if(tile.equals(player)){
                    idxPlayer= tile.getPosition();
                    idxP = board.indexOf(tile);
                }
            }
            player.setPosition(idxTile);
            t.setPosition(idxPlayer);
            board.remove(idxT);
            board.add(idxT,player);
            board.remove(idxP);
            board.add(idxP,t);
        }
         return "";
    }
}
