package academy.maze.util;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;

public class MazeRenderer {

    public static void renderMaze(MazeDTO maze, boolean useUnicode) {
        renderMaze(maze, useUnicode ? MazeSymbols.unicode() : MazeSymbols.ascii());
    }

    public static void renderMaze(MazeDTO maze, MazeSymbols symbols) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                CellType cellType = maze.getCell(x, y);
                System.out.print(convertCellTypeToSymbol(cellType, symbols));
            }
            System.out.println();
        }
    }

    public static void renderSolution(
            MazeDTO originalMaze, PathDTO path, PointDTO start, PointDTO end, boolean useUnicode) {
        renderSolution(originalMaze, path, start, end, useUnicode ? MazeSymbols.unicode() : MazeSymbols.ascii());
    }

    public static void renderSolution(
            MazeDTO originalMaze, PathDTO path, PointDTO start, PointDTO end, MazeSymbols symbols) {
        for (int y = 0; y < originalMaze.getHeight(); y++) {
            for (int x = 0; x < originalMaze.getWidth(); x++) {
                PointDTO current = new PointDTO(x, y);
                CellType cellType = originalMaze.getCell(x, y);

                if (current.equals(start)) {
                    System.out.print(symbols.getStart());
                } else if (current.equals(end)) {
                    System.out.print(symbols.getEnd());
                } else if (path.getPoints().contains(current)) {
                    System.out.print(symbols.getPath());
                } else {
                    System.out.print(convertCellTypeToSymbol(cellType, symbols));
                }
            }
            System.out.println();
        }

        if (path.isEmpty()) {
            System.out.println("No path found!");
        } else {
            System.out.println("Path length: " + path.length());
        }
    }

    private static char convertCellTypeToSymbol(CellType cellType, MazeSymbols symbols) {
        return switch (cellType) {
            case WALL -> symbols.getWall();
            case PASSAGE -> symbols.getPassage();
            case START -> symbols.getStart();
            case END -> symbols.getEnd();
            case PATH -> symbols.getPath();
        };
    }
}
