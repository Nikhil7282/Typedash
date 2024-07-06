
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

public class Ui extends JFrame implements ActionListener {
    private JCheckBox mode1;
    private JCheckBox mode2;
    private JButton startButton;
    private ButtonGroup group;

    public Ui() {
        this.setTitle("Typedash");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Welcome To TypeDash");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Mv Boli", Font.PLAIN, 20));

        JLabel secondLabel = new JLabel("Select a Mode");
        secondLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        secondLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        secondLabel.setForeground(new Color(255, 255, 255));

        mode1 = new JCheckBox("Mode 1");
        mode1.setFocusable(false);
        mode2 = new JCheckBox("Mode 2");
        mode1.setFocusable(false);

        group = new ButtonGroup();
        group.add(mode1);
        group.add(mode2);

        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        Insets padding = new Insets(5, 10, 5, 10);
        mode1.setMargin(padding);
        mode2.setMargin(padding);

        this.add(Box.createVerticalGlue());
        this.add(label);
        add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(secondLabel);
        this.add(mode1);
        add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(mode2);
        add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(startButton);
        this.add(Box.createVerticalGlue());

        ClassLoader classLoader = this.getClass().getClassLoader();
        URL imageURL = classLoader.getResource("./assets/Typedash.jpg");

        if (imageURL != null) {
            ImageIcon imgIcon = new ImageIcon(imageURL);
            this.setIconImage(imgIcon.getImage());
        } else {
            System.err.println("Image not found!");
        }

        this.getContentPane().setBackground(new Color(0x123456));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (mode1.isSelected()) {
                System.out.println("Mode 1");
                this.dispose();
                new FullTextMode();
            } else if (mode2.isSelected()) {
                System.out.println("Mode 2");
                this.dispose();
                new TypeInMinute();
                // new TypingSpeedTest("Mode 2");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a mode!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
