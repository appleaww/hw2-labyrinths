package academy.maze.dto;

public enum CellType {
    WALL('#'),
    PASSAGE(' '),
    START('O'),
    END('X'),
    PATH('.');

    private final char symbol;

    CellType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static CellType fromChar(char c) {
        for (CellType type : values()) {
            if (type.symbol == c) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown cell type: " + c);
    }
}
