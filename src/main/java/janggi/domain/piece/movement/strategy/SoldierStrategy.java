package janggi.domain.piece.movement.strategy;

import janggi.domain.board.Position;
import janggi.domain.piece.Camp;
import janggi.domain.piece.movement.strategy.pathgenerator.PathGenerator;
import janggi.domain.piece.movement.strategy.pathgenerator.SingleStepPathGenerator;
import java.util.List;

public class SoldierStrategy implements MoveStrategy {

    private final Camp camp;
    private final PathGenerator pathGenerator;

    public SoldierStrategy(Camp camp) {
        this.camp = camp;
        pathGenerator = new SingleStepPathGenerator();
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        DirectionInformation directionInformation = new DirectionInformation(source, destination);
        camp.validateForwardDirection(directionInformation.calculateRowDirection());

        return pathGenerator.generatePath(source, destination);
    }
}
