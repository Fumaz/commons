package dev.fumaz.commons.text.figlet;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Java implementation of FIGlet, edited for my personal use.
 *
 * @author Fumaz, dtmo
 * @see <a href="https://github.com/dtmo/jfiglet">https://github.com/dtmo/jfiglet</a>
 */
public enum FigletFonts {
    BANNER("banner.flf"),
    BIG("big.flf"),
    BLOCK("block.flf"),
    BUBBLE("bubble.flf"),
    DIGITAL("digital.flf"),
    IVRIT("ivrit.flf"),
    LEAN("lean.flf"),
    MINI("mini.flf"),
    MNEMONIC("mnemonic.flf"),
    SCRIPT("script.flf"),
    SHADOW("shadow.flf"),
    SLANT("slant.flf"),
    SMALL("small.flf"),
    SMSHADOW("smshadow.flf"),
    SMSLANT("smslant.flf"),
    STANDARD("standard.flf"),
    TERM("term.flf");

    private final static Map<FigletFonts, FigletFont> CACHE = new HashMap<>();
    private final String resourceName;

    FigletFonts(String resourceName) {
        this.resourceName = resourceName;
    }

    @NotNull
    public FigletFont load() {
        FigletFont cached = CACHE.get(this);

        if (cached != null) {
            return cached;
        }

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("figlet/" + resourceName)) {
            cached = FigletFont.load(stream);
            CACHE.put(this, cached);

            return cached;
        } catch (IOException e) {
            throw new RuntimeException("Could not find resource: " + resourceName);
        }
    }

    @NotNull
    public FigletRenderer renderer() {
        FigletFont font = load();

        return new FigletRenderer(font);
    }

}
