package dev.fumaz.commons.text.figlet;

import java.util.HashMap;
import java.util.Map;

public class FigletFontBuilder {
    private char hardBlankChar;
    private int height;
    private int baseline;
    private int maxLength;
    private int oldLayout;
    private int commentLines;
    private PrintDirection printDirection;
    private int fullLayout;
    private int codetagCount;
    private Map<Character, String> characters = new HashMap<>();

    public char getHardBlankChar() {
        return hardBlankChar;
    }

    public FigletFontBuilder setHardBlankChar(char hardBlankChar) {
        this.hardBlankChar = hardBlankChar;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public FigletFontBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getBaseline() {
        return baseline;
    }

    public FigletFontBuilder setBaseline(int baseline) {
        this.baseline = baseline;
        return this;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public FigletFontBuilder setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public int getOldLayout() {
        return oldLayout;
    }

    public FigletFontBuilder setOldLayout(int oldLayout) {
        this.oldLayout = oldLayout;
        return this;
    }

    public int getCommentLines() {
        return commentLines;
    }

    public FigletFontBuilder setCommentLines(int commentLines) {
        this.commentLines = commentLines;
        return this;
    }

    public PrintDirection getPrintDirection() {
        return printDirection;
    }

    public FigletFontBuilder setPrintDirection(PrintDirection printDirection) {
        this.printDirection = printDirection;
        return this;
    }

    public int getFullLayout() {
        return fullLayout;
    }

    public FigletFontBuilder setFullLayout(int fullLayout) {
        this.fullLayout = fullLayout;
        return this;
    }

    public int getCodetagCount() {
        return codetagCount;
    }

    public FigletFontBuilder setCodetagCount(int codetagCount) {
        this.codetagCount = codetagCount;
        return this;
    }

    public Map<Character, String> getCharacters() {
        return characters;
    }

    public FigletFontBuilder setCharacters(Map<Character, String> characters) {
        this.characters = characters;
        return this;
    }

    public FigletFontBuilder setCharacter(char character, String data) {
        characters.put(character, data);
        return this;
    }

    public FigletFont build() {
        return new FigletFont(hardBlankChar, height, baseline, maxLength, oldLayout, commentLines, printDirection, fullLayout, codetagCount, characters);
    }
}