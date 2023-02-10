package timnekk.models;

public record Range(int start, int end) {
    public boolean contains(int value) {
        return value >= start && value <= end;
    }
}
