package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.KingMove;

import java.util.HashMap;
import java.util.List;

public class King extends ChessPiece {
    private static final int SCORE = 0;

    public King(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put("fourDirections", KingMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get("fourDirections").move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
