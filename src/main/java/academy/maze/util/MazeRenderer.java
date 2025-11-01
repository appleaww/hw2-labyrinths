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
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                CellType cellType = maze.getCell(x, y);
                sb.append(convertCellTypeToSymbol(cellType, symbols));
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    public static void renderSolution(
            MazeDTO originalMaze, PathDTO path, PointDTO start, PointDTO end, boolean useUnicode) {
        renderSolution(originalMaze, path, start, end, useUnicode ? MazeSymbols.unicode() : MazeSymbols.ascii());
    }

    public static void renderSolution(
            MazeDTO originalMaze, PathDTO path, PointDTO start, PointDTO end, MazeSymbols symbols) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < originalMaze.getHeight(); y++) {
            for (int x = 0; x < originalMaze.getWidth(); x++) {
                PointDTO current = new PointDTO(x, y);
                CellType cellType = originalMaze.getCell(x, y);

                if (current.equals(start)) {
                    sb.append(symbols.getStart());
                } else if (current.equals(end)) {
                    sb.append(symbols.getEnd());
                } else if (path.getPoints().contains(current)) {
                    sb.append(symbols.getPath());
                } else {
                    sb.append(convertCellTypeToSymbol(cellType, symbols));
                }
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());

        if (path.isEmpty()) {
            System.out.println("No path found!");
        } else {
            System.out.println("Path length: " + path.length());
        }
    }

    private static char convertCellTypeToSymbol(CellType cellType, MazeSymbols symbols) {
        switch (cellType) {
            case WALL:
                return symbols.getWall();
            case PASSAGE:
                return symbols.getPassage();
            case START:
                return symbols.getStart();
            case END:
                return symbols.getEnd();
            case PATH:
                return symbols.getPath();
            default:
                return '?';
        }
    }
}
