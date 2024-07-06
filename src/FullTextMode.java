import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullTextMode extends JFrame implements ActionListener {
    private JTextArea inputTextArea;
    private JTextArea passageTextArea;
    private JLabel accuracyLabel;
    private JLabel speedLabel;

    private String passage = "Type this text as quickly as you can. This is a sample passage for testing your typing speed.";
    private int typedWords;
    private int correctWords;
    private long startTime;

    public FullTextMode() {
        setTitle("Typing Speed Test");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setForeground(new Color(255, 255, 255));

        JPanel panel = new JPanel(new BorderLayout());

        passageTextArea = new JTextArea(passage);
        passageTextArea.setEditable(false);
        passageTextArea.setLineWrap(true);
        passageTextArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(passageTextArea), BorderLayout.CENTER);

        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTypingStats();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTypingStats();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTypingStats();
            }
        });
        panel.add(new JScrollPane(inputTextArea), BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        accuracyLabel = new JLabel("Accuracy: ");
        speedLabel = new JLabel("Speed: ");
        panel.add(accuracyLabel, BorderLayout.WEST);
        panel.add(speedLabel, BorderLayout.EAST);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void updateTypingStats() {
        String inputText = inputTextArea.getText();
        String[] inputWords = inputText.split("\\s+");
        String[] passageWords = passage.split("\\s+");

        typedWords = inputWords.length;
        correctWords = 0;
        for (int i = 0; i < Math.min(inputWords.length, passageWords.length); i++) {
            if (inputWords[i].equals(passageWords[i])) {
                correctWords++;
            }
        }

        double accuracy = (double) correctWords / typedWords * 100;
        accuracyLabel.setText("Accuracy: " + String.format("%.2f%%", accuracy));

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        double typingSpeed = (double) typedWords / (elapsedTime / 1000.0 / 60.0); // Words per minute
        speedLabel.setText("Speed: " + String.format("%.2f WPM", typingSpeed));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            startTime = System.currentTimeMillis();
            inputTextArea.setText("");
            inputTextArea.requestFocusInWindow();
            accuracyLabel.setText("Accuracy: ");
            speedLabel.setText("Speed: ");
        }
        if (e.getActionCommand().equals("Back")) {
            this.dispose();
            new Ui();
        }
    }
}
