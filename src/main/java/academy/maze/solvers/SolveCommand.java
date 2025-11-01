package academy.maze.solvers;

import academy.maze.dto.PointDTO;
import academy.maze.util.MazeFileHandler;
import academy.maze.util.MazeRenderer;
import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "solve",
    description = "Solve a maze using specified algorithm"
)
public class SolveCommand implements Callable<Integer> {

    @CommandLine.Option(
        names = {"--algorithm", "-a"},
        description = "Algorithm: astar or dijkstra",
        required = true
    )
    private String algorithm;

    @CommandLine.Option(
        names = {"--file", "-f"},
        description = "Maze file",
        required = true
    )
    private File mazeFile;

    @CommandLine.Option(
        names = {"--start", "-s"},
        description = "Start point in format x,y",
        required = true
    )
    private String startStr;

    @CommandLine.Option(
        names = {"--end", "-e"},
        description = "End point in format x,y",
        required = true
    )
    private String endStr;

    @CommandLine.Option(
        names = {"--output", "-o"},
        description = "Output file for solution"
    )
    private File outputFile;

    @CommandLine.Option(
        names = {"--unicode", "-u"},
        description = "Use Unicode symbols for display",
        defaultValue = "false"
    )
    private boolean useUnicode;

    @Override
    public Integer call() {
        try {
            var maze = MazeFileHandler.loadMaze(mazeFile);
            PointDTO start = PointDTO.fromString(startStr);
            PointDTO end = PointDTO.fromString(endStr);

            Solver solver = createSolver();
            var path = solver.solve(maze, start, end);

            if (outputFile != null) {
                MazeFileHandler.saveSolution(maze, path, start, end, outputFile);
                System.out.println("Solution saved to: " + outputFile.getPath());
            } else {
                MazeRenderer.renderSolution(maze, path, start, end, useUnicode);
            }

            return 0;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
    }

    private Solver createSolver() {
        return switch (algorithm.toLowerCase()) {
            case "astar" -> new AStarSolver();
            case "dijkstra" -> new DijkstraSolver();
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        };
    }
}
