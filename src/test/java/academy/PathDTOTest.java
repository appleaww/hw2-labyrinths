package academy;

import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PathDTOTest {

    @Test
    void testEmptyPath() {
        PathDTO path = new PathDTO();
        assertTrue(path.isEmpty());
        assertEquals(0, path.length());
        assertNull(path.getStart());
        assertNull(path.getEnd());
    }

    @Test
    void testPathWithPoints() {
        List<PointDTO> points = Arrays.asList(
            new PointDTO(0, 0),
            new PointDTO(1, 1),
            new PointDTO(2, 2)
        );

        PathDTO path = new PathDTO(points);
        assertEquals(3, path.length());
        assertEquals(new PointDTO(0, 0), path.getStart());
        assertEquals(new PointDTO(2, 2), path.getEnd());
        assertFalse(path.isEmpty());
    }

    @Test
    void testAddPoints() {
        PathDTO path = new PathDTO();
        path.addPoint(new PointDTO(1, 1));
        path.addPoint(new PointDTO(2, 2));

        assertEquals(2, path.length());
        assertEquals(new PointDTO(1, 1), path.getStart());
        assertEquals(new PointDTO(2, 2), path.getEnd());
    }

    @Test
    void testPathImmutability() {
        List<PointDTO> original = new ArrayList<>();
        original.add(new PointDTO(0, 0));

        PathDTO path = new PathDTO(original);

        original.add(new PointDTO(1, 1));

        assertEquals(1, path.length());
        assertEquals(new PointDTO(0, 0), path.getStart());
    }

    @Test
    void testPathFromUnmodifiableList() {
        List<PointDTO> points = Arrays.asList(
            new PointDTO(0, 0),
            new PointDTO(1, 1)
        );
        List<PointDTO> unmodifiable = List.of(points.toArray(new PointDTO[0]));
        PathDTO path = new PathDTO(unmodifiable);
        assertEquals(2, path.length());
    }
}
