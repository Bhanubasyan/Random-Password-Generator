import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame {
    private JTextField lengthField;
    private JCheckBox includeNumbers;
    private JCheckBox includeLowercase;
    private JCheckBox includeUppercase;
    private JCheckBox includeSpecialChars;
    private JButton generateButton;

    public PasswordGenerator() {
        // Set up the frame
        setTitle("Random Password Generator");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        lengthField = new JTextField(10);
        includeNumbers = new JCheckBox("Include Numbers");
        includeLowercase = new JCheckBox("Include Lowercase Letters");
        includeUppercase = new JCheckBox("Include Uppercase Letters");
        includeSpecialChars = new JCheckBox("Include Special Characters");
        generateButton = new JButton("Generate Password");

        // Set up the layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Password Length:"));
        panel.add(lengthField);
        panel.add(includeNumbers);
        panel.add(includeLowercase);
        panel.add(includeUppercase);
        panel.add(includeSpecialChars);
        panel.add(generateButton);
        
        add(panel);

        // Add action listener to the button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        try {
            int length = Integer.parseInt(lengthField.getText());
            if (length <= 0) {
                JOptionPane.showMessageDialog(this, "Length must be a positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder characterSet = new StringBuilder();
            if (includeNumbers.isSelected()) {
                characterSet.append("0123456789");
            }
            if (includeLowercase.isSelected()) {
                characterSet.append("abcdefghijklmnopqrstuvwxyz");
            }
            if (includeUppercase.isSelected()) {
                characterSet.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            }
            if (includeSpecialChars.isSelected()) {
                characterSet.append("!@#$%^&*()-_=+[]{}|;:,.<>?");
            }

            if (characterSet.length() == 0) {
                JOptionPane.showMessageDialog(this, "Please select at least one character set.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String password = generateRandomPassword(characterSet.toString(), length);
            displayPassword(password);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for length.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateRandomPassword(String characterSet, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }
        return password.toString();
    }

    private void displayPassword(String password) {
        JOptionPane.showMessageDialog(this, "Generated Password: " + password, "Password", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            PasswordGenerator generator = new PasswordGenerator();
            generator.setVisible(true);
        });
    }
}
