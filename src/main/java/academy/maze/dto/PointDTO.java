package academy.maze.dto;

public record PointDTO(int x, int y) {

    @Override
    public String toString() {
        return x + "," + y;
    }

    public static PointDTO fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid point format: " + str + ", expected format: x,y");
        }
        try {
            return new PointDTO(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid point format: " + str + ", expected format: x,y");
        }
    }
}
