package dev.fumaz.commons.text.figlet;

public class FigletCharacter {

    private final FigletFont font;
    private final String data;

    public FigletCharacter(FigletFont font, String data) {
        this.font = font;
        this.data = data;
    }

    public char getCharacterAt(int column, int row) {
        if (column < 0 || column >= getWidth() || row < 0 || row >= getHeight()) {
            throw new IndexOutOfBoundsException("Character index out of bounds: " + column + ", " + row);
        }

        return data.charAt((row * getWidth()) + column);
    }

    public String getRow(int row) {
        if (row < 0 || row >= getHeight()) {
            throw new IndexOutOfBoundsException("Row must be between 0 and " + (getHeight() - 1));
        }

        int width = getWidth();
        int start = row * width;

        return data.substring(start, start + width);
    }

    public int getWidth() {
        return data.length() / getHeight();
    }

    public int getHeight() {
        return font.getHeight();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int width = getWidth();
        for (int y = 0; y < getHeight(); ++y) {
            builder.append(getRow(y));
            builder.append("\n");
        }

        return builder.toString();
    }

}
