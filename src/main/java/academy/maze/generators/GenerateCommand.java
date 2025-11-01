package academy.maze.generators;

import academy.maze.util.MazeFileHandler;
import academy.maze.util.MazeRenderer;
import java.io.File;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "generate", description = "Generate a maze with specified algorithm and dimensions")
public class GenerateCommand implements Callable<Integer> {

    @CommandLine.Option(
            names = {"--algorithm", "-a"},
            description = "Algorithm: dfs or prim",
            required = true)
    private String algorithm;

    @CommandLine.Option(
            names = {"--width", "-w"},
            description = "Width of the maze",
            required = true)
    private int width;

    @CommandLine.Option(
            names = {"--height", "-h"},
            description = "Height of the maze",
            required = true)
    private int height;

    @CommandLine.Option(
            names = {"--unicode", "-u"},
            description = "Use Unicode symbols for display",
            defaultValue = "false")
    private boolean useUnicode;

    @CommandLine.Option(
            names = {"--output", "-o"},
            description = "Output file")
    private File outputFile;

    @Override
    public Integer call() {
        try {
            Generator generator = createGenerator();
            var maze = generator.generate(width, height);

            if (outputFile != null) {
                MazeFileHandler.saveMaze(maze, outputFile);
            } else {
                MazeRenderer.renderMaze(maze, useUnicode);
            }

            return 0;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
    }

    private Generator createGenerator() {
        return switch (algorithm.toLowerCase()) {
            case "dfs" -> new DFSGenerator();
            case "prim" -> new PrimGenerator();
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        };
    }
}
