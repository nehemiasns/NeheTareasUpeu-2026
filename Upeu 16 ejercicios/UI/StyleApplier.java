package UI;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class StyleApplier {

    public static final Color COLOR_NEON_CYAN = new Color(0, 255, 255);
    public static final Color COLOR_DARK_GRAY = new Color(30, 30, 30);
    public static final Color COLOR_BG = new Color(18, 18, 18);

    public static void applyNeonDarkStyle() {
        try {
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
            UIManager.put("TextComponent.arc", 15);
            
            UIManager.put("Panel.background", COLOR_BG);
            UIManager.put("OptionPane.background", COLOR_BG);
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Label.foreground", Color.WHITE);
            
            UIManager.put("Component.focusColor", COLOR_NEON_CYAN);
            UIManager.put("Button.focusedBorderColor", COLOR_NEON_CYAN);
            UIManager.put("Button.default.background", new Color(0, 50, 50));
            UIManager.put("Button.default.hoverBackground", new Color(0, 100, 100));
            UIManager.put("Button.default.focusedBackground", new Color(0, 50, 50));
            UIManager.put("Button.hoverBorderColor", COLOR_NEON_CYAN);
            
            UIManager.put("Table.selectionBackground", new Color(0, 100, 100));
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("Table.focusSelectedCellHighlightBorder", COLOR_NEON_CYAN);
            UIManager.put("Table.focusCellHighlightBorder", COLOR_NEON_CYAN);

            Font baseFont = new Font("Segoe UI", Font.PLAIN, 14);
            UIManager.put("defaultFont", baseFont);

            FlatDarkLaf.setup();
        } catch (Exception ex) {
            System.err.println("Error inicializando FlatLaf");
        }
    }
}
