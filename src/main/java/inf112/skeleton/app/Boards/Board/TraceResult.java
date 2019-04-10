package inf112.skeleton.app.Boards.Board;

import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class TraceResult
{
    private Vector<Vector2> positions;
    private boolean hitWall = true;
    private boolean hitPlayer = true;

    TraceResult(Vector<Vector2> positions)
    {
        this.positions = positions;
    }
    TraceResult(Vector<Vector2> positions, boolean hitWall){ this.positions = positions; this.hitWall = hitWall;}

    public Vector<Vector2> getPositions(){ return positions; }
    public boolean hitWall() { return hitWall; }
}
