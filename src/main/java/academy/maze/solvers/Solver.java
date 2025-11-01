package academy.maze.solvers;

import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;

/** Решатель лабиринта */
public interface Solver {

    /**
     * Решение лабиринта. Если путь не найден, то возвращается путь с длиной 0.
     *
     * @param maze лабиринт.
     * @param start начальная точка.
     * @param end конечная точка.
     * @return путь в лабиринте.
     */
    PathDTO solve(MazeDTO maze, PointDTO start, PointDTO end);
}
