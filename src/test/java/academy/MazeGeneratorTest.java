package academy;

import static org.junit.jupiter.api.Assertions.*;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.generators.DFSGenerator;
import academy.maze.generators.PrimGenerator;
import org.junit.jupiter.api.Test;

class MazeGeneratorTest {

    @Test
    void testDFSGenerator1x1() {
        DFSGenerator generator = new DFSGenerator();
        MazeDTO maze = generator.generate(1, 1);

        assertNotNull(maze);
        assertEquals(3, maze.getWidth());
        assertEquals(3, maze.getHeight());
        assertEquals(CellType.PASSAGE, maze.getCell(1, 1));
    }

    @Test
    void testDFSGeneratorSmall() {
        DFSGenerator generator = new DFSGenerator();
        MazeDTO maze = generator.generate(5, 5);

        assertNotNull(maze);
        assertEquals(5, maze.getWidth());
        assertEquals(5, maze.getHeight());

        // Проверяем, что есть хотя бы одна проходимая клетка
        boolean hasPassage = false;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (maze.getCell(x, y) == CellType.PASSAGE) {
                    hasPassage = true;
                    break;
                }
            }
        }
        assertTrue(hasPassage);
    }

    @Test
    void testPrimGenerator1x1() {
        PrimGenerator generator = new PrimGenerator();
        MazeDTO maze = generator.generate(1, 1);

        assertNotNull(maze);
        assertEquals(1, maze.getWidth());
        assertEquals(1, maze.getHeight());
        assertEquals(CellType.PASSAGE, maze.getCell(0, 0));
    }

    @Test
    void testPrimGeneratorSmall() {
        PrimGenerator generator = new PrimGenerator();
        MazeDTO maze = generator.generate(5, 5);

        assertNotNull(maze);
        assertEquals(5, maze.getWidth());
        assertEquals(5, maze.getHeight());

        // Проверяем, что есть хотя бы одна проходимая клетка
        boolean hasPassage = false;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (maze.getCell(x, y) == CellType.PASSAGE) {
                    hasPassage = true;
                    break;
                }
            }
        }
        assertTrue(hasPassage);
    }

    @Test
    void testGeneratorInvalidSize() {
        DFSGenerator dfsGenerator = new DFSGenerator();
        PrimGenerator primGenerator = new PrimGenerator();

        assertThrows(IllegalArgumentException.class, () -> dfsGenerator.generate(0, 5));
        assertThrows(IllegalArgumentException.class, () -> primGenerator.generate(5, 0));
    }
}
