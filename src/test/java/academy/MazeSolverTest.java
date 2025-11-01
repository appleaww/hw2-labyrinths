package academy;

import static org.junit.jupiter.api.Assertions.*;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;
import academy.maze.solvers.AStarSolver;
import academy.maze.solvers.DijkstraSolver;
import org.junit.jupiter.api.Test;

class MazeSolverTest {

    private MazeDTO createSimpleMaze() {
        MazeDTO maze = MazeDTO.create(5, 5);
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                maze.setCell(x, y, CellType.PASSAGE);
            }
        }

        return maze;
    }

    private MazeDTO createMazeWithWalls() {
        MazeDTO maze = MazeDTO.create(5, 5);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                maze.setCell(x, y, CellType.PASSAGE);
            }
        }
        maze.setCell(2, 1, CellType.WALL);
        maze.setCell(2, 2, CellType.WALL);
        maze.setCell(2, 3, CellType.WALL);

        return maze;
    }

    @Test
    void testAStarSimpleMaze() {
        AStarSolver solver = new AStarSolver();
        MazeDTO maze = createSimpleMaze();
        PointDTO start = new PointDTO(0, 0);
        PointDTO end = new PointDTO(4, 4);

        PathDTO path = solver.solve(maze, start, end);

        assertFalse(path.isEmpty());
        assertEquals(start, path.getStart());
        assertEquals(end, path.getEnd());
        assertTrue(path.length() > 0);
    }

    @Test
    void testDijkstraSimpleMaze() {
        DijkstraSolver solver = new DijkstraSolver();
        MazeDTO maze = createSimpleMaze();
        PointDTO start = new PointDTO(0, 0);
        PointDTO end = new PointDTO(4, 4);

        PathDTO path = solver.solve(maze, start, end);

        assertFalse(path.isEmpty());
        assertEquals(start, path.getStart());
        assertEquals(end, path.getEnd());
        assertTrue(path.length() > 0);
    }

    @Test
    void testAStarMazeWithWalls() {
        AStarSolver solver = new AStarSolver();
        MazeDTO maze = createMazeWithWalls();
        PointDTO start = new PointDTO(0, 0);
        PointDTO end = new PointDTO(4, 4);

        PathDTO path = solver.solve(maze, start, end);

        assertFalse(path.isEmpty());
        assertTrue(path.length() > 5);
    }

    @Test
    void testDijkstraMazeWithWalls() {
        DijkstraSolver solver = new DijkstraSolver();
        MazeDTO maze = createMazeWithWalls();
        PointDTO start = new PointDTO(0, 0);
        PointDTO end = new PointDTO(4, 4);

        PathDTO path = solver.solve(maze, start, end);

        assertFalse(path.isEmpty());
        assertTrue(path.length() > 5);
    }

    @Test
    void testNoPath() {
        MazeDTO maze = MazeDTO.create(3, 3);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                maze.setCell(x, y, CellType.WALL);
            }
        }

        AStarSolver astar = new AStarSolver();
        DijkstraSolver dijkstra = new DijkstraSolver();

        PointDTO start = new PointDTO(0, 0);
        PointDTO end = new PointDTO(2, 2);

        PathDTO pathAStar = astar.solve(maze, start, end);
        PathDTO pathDijkstra = dijkstra.solve(maze, start, end);

        assertTrue(pathAStar.isEmpty());
        assertTrue(pathDijkstra.isEmpty());
    }

    @Test
    void testSameStartAndEnd() {
        MazeDTO maze = createSimpleMaze();
        AStarSolver solver = new AStarSolver();
        PointDTO point = new PointDTO(2, 2);

        PathDTO path = solver.solve(maze, point, point);

        assertFalse(path.isEmpty());
        assertEquals(1, path.length());
        assertEquals(point, path.getStart());
        assertEquals(point, path.getEnd());
    }

    @Test
    void testOutOfBoundsPoints() {
        MazeDTO maze = createSimpleMaze();
        AStarSolver solver = new AStarSolver();
        PointDTO valid = new PointDTO(0, 0);
        PointDTO invalid = new PointDTO(10, 10);

        PathDTO path = solver.solve(maze, valid, invalid);
        assertTrue(path.isEmpty());

        path = solver.solve(maze, invalid, valid);
        assertTrue(path.isEmpty());
    }

    @Test
    void testWallStartOrEnd() {
        MazeDTO maze = createMazeWithWalls();
        AStarSolver solver = new AStarSolver();
        PointDTO wallPoint = new PointDTO(2, 2); // Это стена
        PointDTO passagePoint = new PointDTO(0, 0);

        PathDTO path = solver.solve(maze, wallPoint, passagePoint);
        assertTrue(path.isEmpty());

        path = solver.solve(maze, passagePoint, wallPoint);
        assertTrue(path.isEmpty());
    }
}
