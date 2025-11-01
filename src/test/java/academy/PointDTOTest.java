package academy;

import academy.maze.dto.PointDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointDTOTest {

    @Test
    void testPointCreation() {
        PointDTO point = new PointDTO(3, 5);
        assertEquals(3, point.x());
        assertEquals(5, point.y());
    }

    @Test
    void testPointEquality() {
        PointDTO p1 = new PointDTO(2, 3);
        PointDTO p2 = new PointDTO(2, 3);
        PointDTO p3 = new PointDTO(3, 2);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }

    @Test
    void testPointFromString() {
        PointDTO point = PointDTO.fromString("5,7");
        assertEquals(5, point.x());
        assertEquals(7, point.y());
    }

    @Test
    void testPointFromStringInvalid() {
        assertThrows(IllegalArgumentException.class, () -> PointDTO.fromString("5"));
        assertThrows(IllegalArgumentException.class, () -> PointDTO.fromString("5,7,9"));
        assertThrows(IllegalArgumentException.class, () -> PointDTO.fromString("a,b"));
    }

    @Test
    void testPointToString() {
        PointDTO point = new PointDTO(4, 6);
        assertEquals("4,6", point.toString());
    }
}
