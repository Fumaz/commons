package dev.fumaz.commons.text.figlet;

import dev.fumaz.commons.text.Strings;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FigletRenderer {

    private final FigletFont font;
    private int smushMode;
    private PrintDirection printDirection;

    public FigletRenderer(FigletFont font) {
        this.font = font;
        this.smushMode = font.getFullLayout();
        this.printDirection = font.getPrintDirection();
    }

    public FigletFont getFont() {
        return font;
    }

    public int getSmushMode() {
        return smushMode;
    }

    public void setSmushMode(int smushMode) {
        this.smushMode = smushMode;
    }

    public PrintDirection getPrintDirection() {
        return printDirection;
    }

    public void setPrintDirection(PrintDirection printDirection) {
        this.printDirection = printDirection;
    }

    public String render(@NotNull String text) {
        StringBuilder result = new StringBuilder();

        List<StringBuilder> rows = IntStream.range(0, font.getHeight())
                .mapToObj(row -> new StringBuilder())
                .collect(Collectors.toList());

        char previousCharacter = '\0';
        for (char character : text.toCharArray()) {
            if (Character.isWhitespace(character)) {
                character = (character == '\t' || character == ' ') ? ' ' : '\n';
            }

            if ((character > '\0' && character < ' ' && character != '\n') || character == 127) {
                continue;
            }

            if (character == '\n') {
                // Render the buffer
                result.append(rows
                        .stream()
                        .map(StringBuilder::toString)
                        .map(row -> row.replace(font.getHardBlankChar(), ' '))
                        .collect(Collectors.joining("\n", "", "\n")));

                // Clear buffer
                rows.forEach(row -> row.setLength(0));
                previousCharacter = '\0';

                continue;
            }

            int smushAmount = font.calculateOverlapAmount(previousCharacter, character, smushMode, printDirection);
            FigletCharacter figletCharacter = font.getCharacter(character);

            for (int row = 0; row < font.getHeight(); ++row) {
                StringBuilder rowBuilder = rows.get(row);

                if (rowBuilder.length() <= 0) {
                    rowBuilder.append(figletCharacter.getRow(row));
                    continue;
                }

                if (printDirection == PrintDirection.LEFT_TO_RIGHT) {
                    for (int smushColumn = 0; smushColumn < smushAmount; ++smushColumn) {
                        int smushIndex = rowBuilder.length() - (smushColumn + 1);

                        rowBuilder.setCharAt(smushIndex, font.smushCharacters(
                                rowBuilder.charAt(smushIndex),
                                figletCharacter.getCharacterAt(smushAmount - (smushColumn + 1), row),
                                smushMode,
                                printDirection
                        ));
                    }

                    rowBuilder.append(figletCharacter.getRow(row).substring(smushAmount));
                } else {
                    for (int smushColumn = 0; smushColumn < smushAmount; smushColumn++) {
                        rowBuilder.setCharAt(smushColumn, font.smushCharacters(
                                rowBuilder.charAt(smushColumn),
                                figletCharacter.getCharacterAt((figletCharacter.getWidth() - smushAmount) + smushColumn, row),
                                smushMode,
                                printDirection
                        ));
                    }

                    rowBuilder.insert(0, figletCharacter.getRow(row).substring(0, figletCharacter.getWidth() - smushAmount));
                }
            }

            previousCharacter = character;
        }

        result.append(rows
                .stream()
                .map(StringBuilder::toString)
                .map(row -> row.replace(font.getHardBlankChar(), ' '))
                .collect(Collectors.joining("\n"))
        );

        return result.toString();
    }

    public List<String> renderLines(@NotNull String text) {
        return Arrays.stream(render(text).split("\n"))
                .filter(line -> !Strings.isBlank(line))
                .collect(Collectors.toList());
    }

    public void log(@NotNull Logger logger, @NotNull String text) {
        renderLines(text).forEach(logger::info);
    }

}
