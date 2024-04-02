import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getText().equals("")) {
            if (xTurn) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.BLUE); // X color
            } else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.RED); // O color
            }
            xTurn = !xTurn;
            if (checkForWinner()) {
                JOptionPane.showMessageDialog(this, "Game Over! " + (xTurn ? "O" : "X") + " wins!");
                if (!playAgain()) {
                    JOptionPane.showMessageDialog(this, "Thanks for playing!");
                    System.exit(0);
                } else {
                    resetGame();
                }
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                if (!playAgain()) {
                    JOptionPane.showMessageDialog(this, "Thanks for playing!");
                    System.exit(0);
                } else {
                    resetGame();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Move!");
        }
    }

    private boolean checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText()) &&
                    !buttons[0][j].getText().equals("")) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(Color.BLACK); // Reset text color
            }
        }
        xTurn = true;
    }

    private boolean playAgain() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}


