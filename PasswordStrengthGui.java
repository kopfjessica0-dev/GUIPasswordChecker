import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthGui {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new PasswordStrengthGui().createAndShow());
    }

    private void createAndShow() {
        JFrame frame = new JFrame("Password Strength Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));

        JLabel titleLabel = new JLabel("Enter a password to check its strength", SwingConstants.CENTER);
        JPasswordField passwordField = new JPasswordField();
        JButton checkButton = new JButton ("Check Strength");
        JTextArea infoLabel = new JTextArea(3, 30);
        infoLabel.setEditable(false);
        infoLabel.setFocusable(false);
        infoLabel.setLineWrap(true);
        infoLabel.setWrapStyleWord(true);
        infoLabel.setOpaque(false);
        infoLabel.setFont(new JLabel().getFont());
        JLabel resultLabel = new JLabel ("Strength: ", SwingConstants.CENTER);

        
        
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                boolean hasLower = false;
                boolean hasUpper = false;
                boolean hasDigit = false;
                boolean hasSymbol = false;
                for (int i = 0; i < password.length(); i++) {
                    char c = password.charAt(i);
                    if (Character.isLowerCase(c)) hasLower = true;
                    else if (Character.isUpperCase(c)) hasUpper = true;
                    else if (Character.isDigit(c)) hasDigit = true;
                    else hasSymbol = true;
                }
                int score = 0;
                if (password.length() >= 8) score++;
                if (hasLower) score++;
                if (hasUpper) score++;
                if (hasDigit) score++;
                if (hasSymbol) score++;
                String strength = getStrengthScore(score);
                String infoText =
                    "Password Info:\n" +
                    "Length: " + password.length() + "\n" +
                    "Contains Uppercase: " + hasUpper + "\n" +
                    "Contains Lowercase: " + hasLower + "\n" +
                    "Contains Digit: " + hasDigit + "\n" +
                    "Contains Symbol: " + hasSymbol + "\n";
                infoLabel.setText(infoText);
                resultLabel.setText("Strength: " + strength);

                }
            });

            JPanel centerPanel = new JPanel(new GridLayout(3, 1, 5,5));
            centerPanel.add(titleLabel);
            centerPanel.add(passwordField);
            centerPanel.add(checkButton);
            panel.add(centerPanel, BorderLayout.CENTER);
            panel.add(resultLabel, BorderLayout.SOUTH);
            panel.add(infoLabel, BorderLayout.NORTH);

            frame.setContentPane(panel);
            frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

            private String getStrengthScore(int score) {
        if (score <= 1) return "Very Weak";
        else if (score == 2) return "Weak";
        else if (score == 3) return "Moderate";
        else if (score == 4) return "Strong";
        else return "Very Strong";
    }
}
