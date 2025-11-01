package academy.maze.dto;

public final class MazeDTO {
    private final int width;
    private final int height;
    private final CellType[][] grid;

    MazeDTO(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new CellType[height][width];
        initializeGrid();
    }

    public static MazeDTO create(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Maze dimensions must be positive");
        }
        return new MazeDTO(width, height);
    }

    private void initializeGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = CellType.WALL;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellType getCell(int x, int y) {
        validateCoordinates(x, y);
        return grid[y][x];
    }

    public CellType getCell(PointDTO point) {
        return getCell(point.x(), point.y());
    }

    public void setCell(int x, int y, CellType type) {
        validateCoordinates(x, y);
        grid[y][x] = type;
    }

    public void setCell(PointDTO point, CellType type) {
        setCell(point.x(), point.y(), type);
    }

    private void validateCoordinates(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isInBounds(PointDTO point) {
        return isInBounds(point.x(), point.y());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(grid[y][x].getSymbol());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
