package academy;

import static org.junit.jupiter.api.Assertions.*;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PointDTO;
import org.junit.jupiter.api.Test;

class MazeDTOTest {

    @Test
    void testMazeCreation() {
        MazeDTO maze = MazeDTO.create(5, 7);
        assertEquals(5, maze.getWidth());
        assertEquals(7, maze.getHeight());
    }

    @Test
    void testMazeInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> MazeDTO.create(0, 5));
        assertThrows(IllegalArgumentException.class, () -> MazeDTO.create(5, 0));
        assertThrows(IllegalArgumentException.class, () -> MazeDTO.create(-1, 5));
    }

    @Test
    void testSetAndGetCell() {
        MazeDTO maze = MazeDTO.create(5, 5);
        PointDTO point = new PointDTO(1, 1);

        maze.setCell(1, 1, CellType.PASSAGE);
        assertEquals(CellType.PASSAGE, maze.getCell(1, 1));
        assertEquals(CellType.PASSAGE, maze.getCell(point));
    }

    @Test
    void testSetCellOutOfBounds() {
        MazeDTO maze = MazeDTO.create(5, 5);
        assertThrows(IllegalArgumentException.class, () -> maze.setCell(5, 5, CellType.PASSAGE));
        assertThrows(IllegalArgumentException.class, () -> maze.getCell(5, 5));
    }

    @Test
    void testIsInBounds() {
        MazeDTO maze = MazeDTO.create(5, 3);
        assertTrue(maze.isInBounds(0, 0));
        assertTrue(maze.isInBounds(2, 2));
        assertFalse(maze.isInBounds(3, 3));
        assertFalse(maze.isInBounds(-1, 0));
    }

    @Test
    void testMaze1x1() {
        MazeDTO maze = MazeDTO.create(1, 1);
        assertEquals(1, maze.getWidth());
        assertEquals(1, maze.getHeight());
        assertEquals(CellType.WALL, maze.getCell(0, 0));
    }
}
