package academy.maze.generators;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import java.util.*;

public class PrimGenerator implements Generator {

    private static class Wall {
        final int x, y;
        final int fromX, fromY;

        Wall(int x, int y, int fromX, int fromY) {
            this.x = x;
            this.y = y;
            this.fromX = fromX;
            this.fromY = fromY;
        }
    }

    @Override
    public MazeDTO generate(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Maze dimensions must be at least 1x1");
        }

        MazeDTO maze = new MazeDTO(width, height);

        if (width == 1 && height == 1) {
            maze.setCell(0, 0, CellType.PASSAGE);
            return maze;
        }

        if (width < 3 || height < 3) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    maze.setCell(x, y, CellType.PASSAGE);
                }
            }
            return maze;
        }

        Random random = new Random();
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze.setCell(x, y, CellType.WALL);
            }
        }

        int startX = 1;
        int startY = 1;
        maze.setCell(startX, startY, CellType.PASSAGE);

        addWalls(maze, startX, startY, walls);

        while (!walls.isEmpty()) {
            Wall wall = walls.remove(random.nextInt(walls.size()));
            int oppositeX = wall.x + (wall.x - wall.fromX);
            int oppositeY = wall.y + (wall.y - wall.fromY);

            if (maze.isInBounds(oppositeX, oppositeY) && maze.getCell(oppositeX, oppositeY) == CellType.WALL) {

                maze.setCell(wall.x, wall.y, CellType.PASSAGE);
                maze.setCell(oppositeX, oppositeY, CellType.PASSAGE);

                addWalls(maze, oppositeX, oppositeY, walls);
            }
        }

        return maze;
    }

    private void addWalls(MazeDTO maze, int x, int y, List<Wall> walls) {
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (maze.isInBounds(nx, ny) && maze.getCell(nx, ny) == CellType.WALL) {
                walls.add(new Wall(nx, ny, x, y));
            }
        }
    }
}
