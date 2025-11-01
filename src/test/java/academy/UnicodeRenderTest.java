package academy;

import static org.junit.jupiter.api.Assertions.*;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;
import academy.maze.util.MazeRenderer;
import academy.maze.util.MazeSymbols;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class UnicodeRenderTest {

    @Test
    void testAsciiSymbols() {
        MazeSymbols ascii = MazeSymbols.ascii();
        assertEquals('#', ascii.getWall());
        assertEquals(' ', ascii.getPassage());
        assertEquals('.', ascii.getPath());
        assertEquals('O', ascii.getStart());
        assertEquals('X', ascii.getEnd());
    }

    @Test
    void testUnicodeSymbols() {
        MazeSymbols unicode = MazeSymbols.unicode();
        assertEquals('█', unicode.getWall());
        assertEquals(' ', unicode.getPassage());
        assertEquals('·', unicode.getPath());
        assertEquals('S', unicode.getStart());
        assertEquals('E', unicode.getEnd());
    }

    @Test
    void testRenderWithUnicode() {
        MazeDTO maze = MazeDTO.create(3, 3);
        maze.setCell(1, 1, CellType.PASSAGE);

        assertDoesNotThrow(() -> MazeRenderer.renderMaze(maze, true));
        assertDoesNotThrow(() -> MazeRenderer.renderMaze(maze, false));
    }

    @Test
    void testSolutionRenderWithUnicode() {
        MazeDTO maze = MazeDTO.create(3, 3);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                maze.setCell(x, y, CellType.PASSAGE);
            }
        }

        PathDTO path = new PathDTO(Arrays.asList(new PointDTO(0, 0), new PointDTO(1, 1), new PointDTO(2, 2)));

        PointDTO start = new PointDTO(0, 0);
        PointDTO end = new PointDTO(2, 2);

        assertDoesNotThrow(() -> MazeRenderer.renderSolution(maze, path, start, end, true));
        assertDoesNotThrow(() -> MazeRenderer.renderSolution(maze, path, start, end, false));
    }
}
