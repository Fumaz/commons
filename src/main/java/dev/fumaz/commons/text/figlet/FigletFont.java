package dev.fumaz.commons.text.figlet;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FigletFont {

    private final char hardBlankChar;
    private final int height;
    private final int baseline;
    private final int maxLength;
    private final int oldLayout;
    private final int commentLines;
    private final PrintDirection printDirection;
    private final int fullLayout;
    private final int codetagCount;
    private final Map<Character, FigletCharacter> characters;

    public FigletFont(char hardBlankChar, int height, int baseline, int maxLength, int oldLayout, int commentLines, PrintDirection printDirection, int fullLayout, int codetagCount, Map<Character, String> characters) {
        Map<Character, FigletCharacter> fontCharacters = new HashMap<>();
        characters.forEach((character, data) -> fontCharacters.put(character, new FigletCharacter(this, data)));

        this.hardBlankChar = hardBlankChar;
        this.height = height;
        this.baseline = baseline;
        this.maxLength = maxLength;
        this.oldLayout = oldLayout;
        this.commentLines = commentLines;
        this.printDirection = printDirection;
        this.fullLayout = fullLayout;
        this.codetagCount = codetagCount;
        this.characters = fontCharacters;
    }

    /**
     * Loads a FigFont from an {@link InputStream}.
     *
     * @param inputStream The input stream containing the FIGfont data to load.
     * @return The loaded FigFont instance.
     * @throws IOException if there is a problem loading the stream data.
     */
    @NotNull
    public static FigletFont load(final InputStream inputStream) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            FigletFontReader fontReader = new FigletFontReader(inputStreamReader);
            return fontReader.readFont();
        }
    }

    public char getHardBlankChar() {
        return hardBlankChar;
    }

    public int getHeight() {
        return height;
    }

    public int getBaseline() {
        return baseline;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getOldLayout() {
        return oldLayout;
    }

    public int getCommentLines() {
        return commentLines;
    }

    public PrintDirection getPrintDirection() {
        return printDirection;
    }

    public int getFullLayout() {
        return fullLayout;
    }

    public int getCodetagCount() {
        return codetagCount;
    }

    public FigletCharacter getCharacter(char character) {
        return characters.get(character);
    }

    /**
     * Calculates the amount that two FigCharacters will overlap based on a smushing
     * mode and print direction.
     *
     * @param first          the first character
     * @param second         the second character
     * @param smushMode      the smush mode that determines the nature of the overlap
     * @param printDirection the print direction
     * @return The amount of overlap measured in characters.
     * @see LayoutOptions
     */
    public int calculateOverlapAmount(char first, char second, int smushMode, PrintDirection printDirection) {
        if (!LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_SMUSHING_BY_DEFAULT | LayoutOptions.HORIZONTAL_FITTING_BY_DEFAULT, smushMode)) {
            return 0;
        }

        if (first == '\0' || second == '\0') {
            return 0;
        }

        FigletCharacter leftCharacter;
        FigletCharacter rightCharacter;

        if (printDirection == PrintDirection.LEFT_TO_RIGHT) {
            leftCharacter = getCharacter(first);
            rightCharacter = getCharacter(second);
        } else {
            leftCharacter = getCharacter(second);
            rightCharacter = getCharacter(first);
        }

        if (rightCharacter.getWidth() < 2 || leftCharacter.getWidth() < 2) {
            return 0;
        }

        int smushAmount = rightCharacter.getWidth();

        for (int row = 0; row < getHeight(); ++row) {
            int rowSmushAmount;

            int leftBoundary = leftCharacter.getWidth() - 1;
            while (leftCharacter.getCharacterAt(leftBoundary, row) == ' ' && leftBoundary > 0) {
                leftBoundary--;
            }

            int rightBoundary = 0;
            while ((rightCharacter.getCharacterAt(rightBoundary, row) == ' ') && (rightBoundary < (rightCharacter.getWidth() - 1))) {
                rightBoundary++;
            }

            rowSmushAmount = Math.min(rightCharacter.getWidth(), (leftCharacter.getWidth() - (leftBoundary + 1)) + rightBoundary);

            if (leftCharacter.getCharacterAt(leftBoundary, row) == ' ') {
                rowSmushAmount++;
            } else if (smushCharacters(leftCharacter.getCharacterAt(leftBoundary, row), rightCharacter.getCharacterAt(rightBoundary, row), smushMode, printDirection) != '\0') {
                rowSmushAmount++;
            }

            smushAmount = Math.min(smushAmount, rowSmushAmount);
        }

        return smushAmount;
    }

    /**
     * Calculates the character that is the result of merging two characters.
     * Possible outcomes are a single character to use as the replacement for the
     * input characters when smushing FIGcharacters, or the <code>null</code>
     * character '/0' which represents unsmushable input characters.
     *
     * @param first          The first character to smush.
     * @param second         The second character to smush.
     * @param mode           The smushmode that determines how smushing occurs. This value may
     *                       be generated by combining values from {@link LayoutOptions}.
     * @param printDirection The print direction.
     * @return The character representing the result of smushing the input
     * characters, or the <code>null</code> character '/0' if the input
     * characters cannot be smushed.
     * @see LayoutOptions
     */
    public char smushCharacters(char first, char second, int mode, PrintDirection printDirection) {
        if (first == ' ') {
            return second;
        }

        if (second == ' ') {
            return first;
        }

        if (!LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_SMUSHING_BY_DEFAULT, mode)) {
            return '\0';
        }

        if ((mode & 63) == 0) {
            if (first == hardBlankChar) {
                return second;
            }

            if (second == hardBlankChar) {
                return first;
            }

            if (printDirection == PrintDirection.LEFT_TO_RIGHT) {
                return second;
            }

            return first;
        }

        if (LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_HARDBLANK_SMUSHING, mode)) {
            if (first == hardBlankChar && second == hardBlankChar) {
                return first;
            }
        }

        if (first == hardBlankChar || second == hardBlankChar) {
            return '\0';
        }

        if (LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_EQUAL_CHARACTER_SMUSHING, mode)) {
            if (first == second) {
                return first;
            }
        }

        if (LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_UNDERSCORE_SMUSHING, mode)) {
            if (first == '_' && "|/\\[]{}()<>".indexOf(second) != -1) {
                return second;
            }

            if (second == '_' && "|/\\[]{}()<>".indexOf(first) != -1) {
                return first;
            }
        }

        if (LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_HIERARCHY_SMUSHING, mode)) {
            if (first == '|' && "/\\[]{}()<>".indexOf(second) != -1) {
                return second;
            }

            if (second == '|' && "/\\[]{}()<>".indexOf(first) != -1) {
                return first;
            }

            if ("/\\".indexOf(first) != -1 && "[]{}()<>".indexOf(second) != -1) {
                return second;
            }

            if ("/\\".indexOf(second) != -1 && "[]{}()<>".indexOf(first) != -1) {
                return first;
            }

            if ("[]".indexOf(first) != -1 && "{}()<>".indexOf(second) != -1) {
                return second;
            }

            if ("[]".indexOf(second) != -1 && "{}()<>".indexOf(first) != -1) {
                return first;
            }

            if ("{}".indexOf(first) != -1 && "()<>".indexOf(second) != -1) {
                return second;
            }

            if ("{}".indexOf(second) != -1 && "()<>".indexOf(first) != -1) {
                return first;
            }

            if ("()".indexOf(first) != -1 && "<>".indexOf(second) != -1) {
                return second;
            }

            if ("()".indexOf(second) != -1 && "<>".indexOf(first) != -1) {
                return first;
            }
        }

        if (LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_OPPOSITE_PAIR_SMUSHING, mode)) {
            if (first == '[' && second == ']') {
                return '|';
            }

            if (second == '[' && first == ']') {
                return '|';
            }

            if (first == '{' && second == '}') {
                return '|';
            }

            if (second == '{' && first == '}') {
                return '|';
            }

            if (first == '(' && second == ')') {
                return '|';
            }

            if (second == '(' && first == ')') {
                return '|';
            }
        }

        if (LayoutOptions.isSelected(LayoutOptions.HORIZONTAL_BIG_X_SMUSHING, mode)) {
            if (first == '/' && second == '\\') {
                return '|';
            }

            if (second == '/' && first == '\\') {
                return 'Y';
            }

            if (first == '>' && second == '<') {
                return 'X';
            }
        }

        return '\0';
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        characters.forEach((character, figletCharacter) -> {
            builder.append(character)
                    .append(":\n")
                    .append(figletCharacter)
                    .append("\n");
        });

        return builder.toString();
    }

}
