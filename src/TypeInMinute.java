import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TypeInMinute extends JFrame implements ActionListener {
    private JTextArea inputTextArea;
    private JTextArea passageTextArea;
    private JLabel accuracyLabel;
    private JLabel speedLabel;

    private String passage = "Type this text as quickly as you can. This is a sample passage for testing your typing speed.";

    public TypeInMinute() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            inputTextArea.setText("");
            inputTextArea.requestFocusInWindow();
            TimerThread t = new TimerThread(() -> {
                SwingUtilities.invokeLater(() -> {
                    inputTextArea.setFocusable(false);
                    findResult(inputTextArea.getText());
                    System.out.println(speedLabel.getText() + " " + accuracyLabel.getText());
                });
            });
            t.start();
        }
        if (e.getActionCommand().equals("Back")) {
            this.dispose();
            new Ui();
        }
    }

    private void findResult(String userInput) {
        int correct = 0;
        for (int i = 0; i < userInput.length(); i++) {
            if (i < userInput.length() && userInput.charAt(i) == passage.charAt(i)) {
                correct++;
            }
        }
        if (userInput.length() > 0) {
            accuracyLabel.setText("Accuracy: " + (int) ((double) correct / userInput.length() * 100));
        } else {
            accuracyLabel.setText("Accuracy: " + 100 + "%");
        }
        speedLabel.setText("Speed: " + (double) correct / 60);
    }
}
