package academy.maze.util;

public class MazeSymbols {
    private final char wall;
    private final char passage;
    private final char path;
    private final char start;
    private final char end;

    private MazeSymbols(char wall, char passage, char path, char start, char end) {
        this.wall = wall;
        this.passage = passage;
        this.path = path;
        this.start = start;
        this.end = end;
    }

    public static MazeSymbols ascii() {
        return new MazeSymbols('#', ' ', '.', 'O', 'X');
    }

    public static MazeSymbols unicode() {
        return new MazeSymbols('█', ' ', '·', 'S', 'E');
    }

    public char getWall() {
        return wall;
    }

    public char getPassage() {
        return passage;
    }

    public char getPath() {
        return path;
    }

    public char getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }
}
