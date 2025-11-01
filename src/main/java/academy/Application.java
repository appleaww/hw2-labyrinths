package academy;

import academy.maze.generators.GenerateCommand;
import academy.maze.solvers.SolveCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "maze",
        version = "1.0",
        description = "Maze generator and solver",
        mixinStandardHelpOptions = true,
        subcommands = {GenerateCommand.class, SolveCommand.class})
public class Application implements Runnable {

    @Override
    public void run() {
        System.out.println("Use 'generate' or 'solve' subcommands. Use --help for more information.");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }
}
