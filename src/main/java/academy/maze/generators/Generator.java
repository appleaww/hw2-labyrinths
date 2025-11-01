package academy.maze.generators;

import academy.maze.dto.MazeDTO;

/** Генератор лабиринта */
public interface Generator {

    /**
     * Генерирует лабиринт.
     *
     * @param width ширина лабиринта.
     * @param height высота лабиринта.
     * @return лабиринт
     * @throws IllegalArgumentException если невозможно сгенерировать лабиринт.
     */
    MazeDTO generate(int width, int height);
}
