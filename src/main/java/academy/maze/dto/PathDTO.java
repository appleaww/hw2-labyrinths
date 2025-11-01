package academy.maze.dto;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PathDTO {
    private final List<PointDTO> points;

    public PathDTO() {
        this.points = new ArrayList<>();
    }

    public PathDTO(List<PointDTO> points) {
        this.points = new ArrayList<>(points);
    }

    public void addPoint(PointDTO point) {
        points.add(point);
    }

    public List<PointDTO> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public int length() {
        return points.size();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public PointDTO getStart() {
        return points.isEmpty() ? null : points.get(0);
    }

    public PointDTO getEnd() {
        return points.isEmpty() ? null : points.get(points.size() - 1);
    }

    public static PathDTO fromCollection(Collection<PointDTO> points) {
        return new PathDTO(new ArrayList<>(points));
    }
}
