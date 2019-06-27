package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.DiagonalMove;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.HorizontalMove;
import chess.domain.chessmove.VerticalMove;

import java.util.HashMap;
import java.util.List;

public class Queen extends ChessPiece {
    private static final int SCORE = 9;

    public Queen(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put(Direction.HORIZONTAL, HorizontalMove.getInstance());
        movingMap.put(Direction.VERTICAL, VerticalMove.getInstance());
        movingMap.put(Direction.DIAGONAL, DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        Direction moveName = Direction.DIAGONAL;

        if (source.isInLine(target) == Position.VERTICAL_LINE) {
            moveName = Direction.VERTICAL;
        }
        if (source.isInLine(target) == Position.HORIZONTAL_LINE) {
            moveName = Direction.HORIZONTAL;
        }

        return movingMap.get(moveName).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
