package dev.fumaz.commons.text.figlet;

public enum PrintDirection {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;

    public static PrintDirection fromHeader(int value) {
        if (value == 1) {
            return RIGHT_TO_LEFT;
        }

        return LEFT_TO_RIGHT;
    }

}
