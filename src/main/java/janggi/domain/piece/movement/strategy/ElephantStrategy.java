package janggi.domain.piece.movement.strategy;

import janggi.domain.board.Position;
import janggi.domain.piece.movement.strategy.pathgenerator.PathGenerator;
import janggi.domain.piece.movement.strategy.pathgenerator.StraightThenDiagonalPathGenerator;
import java.util.List;

public class ElephantStrategy implements MoveStrategy {

    private static final int DIAGONAL_COUNT = 2;

    private static final int MIN_ABS_DELTA = 2;
    private static final int MAX_ABS_DELTA = 3;

    private static final int ELEPHANT_STRAIGHT_MOVE_DISTANCE = 1;
    private static final int ELEPHANT_DIAGONAL_MOVE_DISTANCE = 2;

    private static final String INVALID_ELEPHANT_MOVE = String.format(
            "[ERROR] 해당 기물은 직선 %d칸 이동 후 대각선 %d칸 이동만 가능합니다.",
            ELEPHANT_STRAIGHT_MOVE_DISTANCE,
            ELEPHANT_DIAGONAL_MOVE_DISTANCE
    );

    private final PathGenerator pathGenerator;

    public ElephantStrategy() {
        this.pathGenerator = new StraightThenDiagonalPathGenerator(DIAGONAL_COUNT);
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        validateMovement(new DirectionInformation(source, destination));
        return pathGenerator.generatePath(source, destination);
    }

    protected void validateMovement(DirectionInformation directionInfo) {
        if ((directionInfo.calculateAbsRowDifference() != MIN_ABS_DELTA
                || directionInfo.calculateAbsColumnDifference() != MAX_ABS_DELTA)
                && (directionInfo.calculateAbsRowDifference() != MAX_ABS_DELTA
                || directionInfo.calculateAbsColumnDifference() != MIN_ABS_DELTA)) {
            throw new IllegalArgumentException(INVALID_ELEPHANT_MOVE);
        }
    }
}
