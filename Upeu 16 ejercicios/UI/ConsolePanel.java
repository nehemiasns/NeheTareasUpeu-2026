package UI;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePanel extends JPanel {
    private JTextArea textArea;

    public ConsolePanel() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setBackground(StyleApplier.COLOR_BG);
        textArea.setForeground(StyleApplier.COLOR_NEON_CYAN);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(StyleApplier.COLOR_DARK_GRAY), "Salida de Consola"));
        add(scrollPane, BorderLayout.CENTER);

        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
        System.setOut(printStream);
    }
    
    public void clear() {
        textArea.setText("");
    }
}
