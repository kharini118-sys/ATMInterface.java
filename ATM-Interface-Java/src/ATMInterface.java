
import javax.swing.*;
import java.awt.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        if (amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }
}

public class ATMInterface extends JFrame {

    private BankAccount account;
    private JLabel balanceLabel, messageLabel;
    private JTextField amountField;
    private JButton depositButton, withdrawButton, checkBalanceButton, clearButton;

    public ATMInterface() {

        account = new BankAccount(5000.00);

        setTitle("ATM Interface");
        setSize(520, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("ATM INTERFACE", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        balanceLabel = new JLabel("Available Balance: ₹5000.00", JLabel.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        messageLabel = new JLabel("Enter amount and choose an option", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));
        amountField.setHorizontalAlignment(JTextField.CENTER);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");
        clearButton = new JButton("Clear");

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));

        centerPanel.add(balanceLabel);
        centerPanel.add(new JLabel("Enter Amount:", JLabel.CENTER));
        centerPanel.add(amountField);
        centerPanel.add(messageLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(clearButton);

        centerPanel.add(buttonPanel);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        depositButton.addActionListener(e -> depositAmount());
        withdrawButton.addActionListener(e -> withdrawAmount());
        checkBalanceButton.addActionListener(e -> showBalance());
        clearButton.addActionListener(e -> {
            amountField.setText("");
            messageLabel.setText("Enter amount and choose an option");
        });

        setVisible(true);
    }

    private void depositAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (account.deposit(amount)) {
                messageLabel.setText("Deposit successful: ₹" + amount);
                updateBalance();
            } else {
                messageLabel.setText("Invalid deposit amount.");
            }

            amountField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    private void withdrawAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (account.withdraw(amount)) {
                messageLabel.setText("Withdrawal successful: ₹" + amount);
                updateBalance();
            } else {
                messageLabel.setText("Insufficient balance or invalid amount.");
            }

            amountField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(this,
                "Your current balance is ₹" + String.format("%.2f", account.checkBalance()));
    }

    private void updateBalance() {
        balanceLabel.setText("Available Balance: ₹" + String.format("%.2f", account.checkBalance()));
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}
