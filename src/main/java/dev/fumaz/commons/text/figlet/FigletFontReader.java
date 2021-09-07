package dev.fumaz.commons.text.figlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FigletFontReader {

    public static final String FONT_MAGIC_NUMBER = "flf2";
    private static final Pattern CODE_TAG_PATTERN = Pattern.compile("([^\\s]+)\\s*.*");
    private static final int[] deutschCodePoints = new int[]{196, 214, 220, 228, 246, 252, 223};

    private final Reader reader;

    public FigletFontReader(Reader reader) {
        this.reader = reader;
    }

    public FigletFont readFont() throws IOException {
        FigletFontBuilder builder = new FigletFontBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String header = bufferedReader.readLine();

            try {
                parseHeader(header, builder);
            } catch (IllegalArgumentException e) {
                throw new IOException("Could not read font header", e);
            }

            for (int comments = 0; comments < builder.getCommentLines(); ++comments) {
                bufferedReader.readLine();
            }

            for (int codePoint = 32; codePoint < 127; ++codePoint) {
                String data = readCharacterData(builder.getHeight(), bufferedReader);
                builder.setCharacter((char) codePoint, data);
            }

            for (int codePoint : deutschCodePoints) {
                String data = readCharacterData(builder.getHeight(), bufferedReader);
                builder.setCharacter((char) codePoint, data);
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String data = readCharacterData(builder.getHeight(), bufferedReader);
                builder.setCharacter(parseCodeTag(line), data);
            }
        }

        return builder.build();
    }

    private char parseCodeTag(String text) {
        Matcher codeTagMatcher = CODE_TAG_PATTERN.matcher(text);

        if (!codeTagMatcher.matches()) {
            throw new IllegalArgumentException("Could not parse text as a code tag: " + text);
        }

        String codePointText = codeTagMatcher.group(1);
        int codePoint = Integer.decode(codePointText);

        return (char) codePoint;
    }

    private String readCharacterData(int height, BufferedReader bufferedReader) throws IOException {
        StringBuilder builder = new StringBuilder();

        for (int charLine = 0; charLine < height; charLine++) {
            final String line = bufferedReader.readLine();

            int charIndex = line.length() - 1;
            while (charIndex >= 0 && Character.isWhitespace(line.charAt(charIndex))) {
                charIndex--;
            }

            char endChar = line.charAt(charIndex);
            while (charIndex >= 0 && line.charAt(charIndex) == endChar) {
                charIndex--;
            }

            builder.append(line, 0, charIndex + 1);
        }

        return builder.toString();
    }

    private void parseHeader(String header, FigletFontBuilder builder) {
        String[] arguments = header.split("\\s+");

        if (!arguments[0].startsWith(FONT_MAGIC_NUMBER)) {
            throw new IllegalArgumentException("Header does not start with FIGFont magic number " + FONT_MAGIC_NUMBER + ": " + header);
        }

        builder.setHardBlankChar(arguments[0].charAt(arguments[0].length() - 1));

        if (arguments.length > 1) {
            builder.setHeight(Integer.decode(arguments[1]));
        }

        if (arguments.length > 2) {
            builder.setBaseline(Integer.decode(arguments[2]));
        }

        if (arguments.length > 3) {
            builder.setMaxLength(Integer.decode(arguments[3]));
        }

        if (arguments.length > 4) {
            final int oldLayout = Integer.decode(arguments[4]);

            builder.setOldLayout(oldLayout);
            builder.setFullLayout(LayoutOptions.fullLayoutFromOldLayout(oldLayout));
        }

        if (arguments.length > 5) {
            builder.setCommentLines(Integer.decode(arguments[5]));
        }

        if (arguments.length > 6) {
            builder.setPrintDirection(PrintDirection.fromHeader(Integer.decode(arguments[6])));
        }

        if (arguments.length > 7) {
            builder.setFullLayout(Integer.decode(arguments[7]));
        }

        if (arguments.length > 8) {
            builder.setCodetagCount(Integer.decode(arguments[8]));
        }
    }

}
