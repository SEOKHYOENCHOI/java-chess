package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.HorizontalMove;
import chess.domain.chessmove.VerticalMove;

import java.util.EnumMap;
import java.util.List;

public class Rook extends ChessPiece {
    public Rook(Team team) {
        super(team);
        chessScore = ChessScore.ROOK;
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new EnumMap<>(Direction.class);
        movingMap.put(Direction.HORIZONTAL, HorizontalMove.getInstance());
        movingMap.put(Direction.VERTICAL, VerticalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        Direction moveName = Direction.HORIZONTAL;

        if (source.isInLine(target) == Position.VERTICAL_LINE) {
            moveName = Direction.VERTICAL;
        }

        return movingMap.get(moveName).move(source, target);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
