package academy.maze.util;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MazeFileHandler {

    private static final MazeSymbols FILE_SYMBOLS = MazeSymbols.ascii();

    public static MazeDTO loadMaze(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getPath());
        }

        var lines = Files.readAllLines(file.toPath());
        if (lines.isEmpty()) {
            throw new IOException("File is empty");
        }

        int height = lines.size();
        int width = lines.get(0).length();

        MazeDTO maze = new MazeDTO(width, height);

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            if (line.length() != width) {
                throw new IOException("Inconsistent maze width at line " + (y + 1));
            }

            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                maze.setCell(x, y, CellType.fromChar(c));
            }
        }

        return maze;
    }
    private static char convertCellTypeToChar(CellType cellType) {
        switch (cellType) {
            case WALL: return FILE_SYMBOLS.getWall();
            case PASSAGE: return FILE_SYMBOLS.getPassage();
            case START: return FILE_SYMBOLS.getStart();
            case END: return FILE_SYMBOLS.getEnd();
            case PATH: return FILE_SYMBOLS.getPath();
            default: return '?';
        }
    }


    public static void saveMaze(MazeDTO maze, File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.print(maze.toString());
        }
    }

    public static void saveSolution(MazeDTO originalMaze, PathDTO path, PointDTO start, PointDTO end, File file) throws IOException {
        MazeDTO solutionMaze = createSolutionMaze(originalMaze, path, start, end);
        saveMaze(solutionMaze, file);
    }

    private static MazeDTO createSolutionMaze(MazeDTO original, PathDTO path, PointDTO start, PointDTO end) {
        MazeDTO solution = new MazeDTO(original.getWidth(), original.getHeight());

        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                solution.setCell(x, y, original.getCell(x, y));
            }
        }

        for (PointDTO point : path.getPoints()) {
            if (!point.equals(start) && !point.equals(end)) {
                solution.setCell(point, CellType.PATH);
            }
        }

        solution.setCell(start, CellType.START);
        solution.setCell(end, CellType.END);

        return solution;
    }
}
