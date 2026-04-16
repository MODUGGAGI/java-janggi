package janggi.domain.piece.movement.strategy.pathgenerator;

import janggi.domain.board.Position;
import java.util.List;

public interface PathGenerator {

    List<Position> generatePath(Position source, Position destination);
}
