package janggi.domain.piece.movement.strategy.pathgenerator;

import janggi.domain.board.Position;
import janggi.domain.piece.movement.strategy.DirectionInformation;
import java.util.ArrayList;
import java.util.List;

public class StraightThenDiagonalPathGenerator implements PathGenerator {

    private final int diagonalCount;

    public StraightThenDiagonalPathGenerator(int diagonalCount) {
        this.diagonalCount = diagonalCount;
    }

    @Override
    public List<Position> generatePath(Position source, Position destination) {
        DirectionInformation directionInformation = new DirectionInformation(source, destination);

        if (directionInformation.isRowBiggerThanColumn()) {
            return createRowFirstPath(source, directionInformation);
        }
        return createColumnFirstPath(source, directionInformation);
    }

    private List<Position> createRowFirstPath(Position source, DirectionInformation directionInformation) {
        List<Position> path = new ArrayList<>();

        Position current = source.moveRow(directionInformation.calculateRowDirection());
        path.add(current);

        path.addAll(moveDiagonal(current, directionInformation));
        return path;
    }

    private List<Position> createColumnFirstPath(Position source, DirectionInformation directionInformation) {
        List<Position> path = new ArrayList<>();

        Position current = source.moveColumn(directionInformation.calculateColumnDirection());
        path.add(current);

        path.addAll(moveDiagonal(current, directionInformation));
        return path;
    }

    private List<Position> moveDiagonal(Position source, DirectionInformation directionInfo) {
        List<Position> path = new ArrayList<>();

        Position current = source;
        for (int i = 0; i < diagonalCount; i++) {
            current = current.moveDiagonal(directionInfo.calculateRowDirection(),
                    directionInfo.calculateColumnDirection());
            path.add(current);
        }
        return path;
    }
}
