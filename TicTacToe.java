
// Tic Tac Toe Game Implemented using Java Swing GUI frame
// BY Sirak Berhane
// 09/22/2019

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame
{
  // create board
    static JLabel whoLabel = new JLabel();
    static JLabel turnLabel = new JLabel();
    static JLabel grid0Label = new JLabel();
    static JLabel grid1Label = new JLabel();
    static JLabel grid2Label = new JLabel();
    static JLabel grid3Label = new JLabel();
    static JLabel grid4Label = new JLabel();
    static JLabel grid5Label = new JLabel();
    static JLabel grid6Label = new JLabel();
    static JLabel grid7Label = new JLabel();
    static JLabel grid8Label = new JLabel();
    static JLabel[] choiceLabel = new JLabel[9];
    static JLabel h1Label = new JLabel();
    static JLabel h2Label = new JLabel();
    static JLabel v1Label = new JLabel();
    static JLabel v2Label = new JLabel();
    static ImageIcon X = new ImageIcon("x.png");
    static ImageIcon O = new ImageIcon("O.png");
    static JButton resetButton = new JButton();

    static boolean XTurn; // if true, it's X's turn
    static boolean canClick; // if true, can click grid
    static int numberClicks; //
    static int[] marker = new int[9]; // Marker Represents Location

    public static void main(String[] args)
    {
        // create frame
        new TicTacToe().setVisible(true);
        // initialize game
        resetButton.doClick();
    }

    public TicTacToe()
    {
        // frame constructor
        setTitle("Tic-Tac-Toe Game");
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                exitForm();
            }
        });
        getContentPane().setLayout(new GridBagLayout());

        // position controls
        GridBagConstraints gridConstraints;

        gridConstraints = new GridBagConstraints();
        whoLabel.setPreferredSize(new Dimension(100, 100));
        whoLabel.setIcon(X);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(whoLabel, gridConstraints);

        gridConstraints = new GridBagConstraints();
        turnLabel.setText("Turn");
        turnLabel.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 24));
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        getContentPane().add(turnLabel, gridConstraints);

        choiceLabel[0] = grid0Label;
        choiceLabel[1] = grid1Label;
        choiceLabel[2] = grid2Label;
        choiceLabel[3] = grid3Label;
        choiceLabel[4] = grid4Label;
        choiceLabel[5] = grid5Label;
        choiceLabel[6] = grid6Label;
        choiceLabel[7] = grid7Label;
        choiceLabel[8] = grid8Label;
        int grid = 0;
        for (int j = 2; j < 7; j += 2)
        {
            for (int i = 0; i < 5; i += 2)
            {
                gridConstraints = new GridBagConstraints();
                choiceLabel[grid].setPreferredSize(new Dimension(100, 100));
                gridConstraints.gridx = i;
                gridConstraints.gridy = j;
                gridConstraints.insets = new Insets(10, 10, 10, 10);
                getContentPane().add(choiceLabel[grid], gridConstraints);
                choiceLabel[grid].addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        labelMouseClicked(e);
                    }
                });
                grid++;
            }
        }

        h1Label.setPreferredSize(new Dimension(360, 10));
        h1Label.setOpaque(true);
        h1Label.setBackground(Color.BLACK);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.gridwidth = 5;
        getContentPane().add(h1Label, gridConstraints);
        h2Label.setPreferredSize(new Dimension(360, 10));
        h2Label.setOpaque(true);
        h2Label.setBackground(Color.BLACK);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        gridConstraints.gridwidth = 5;
        getContentPane().add(h2Label, gridConstraints);
        v1Label.setPreferredSize(new Dimension(10, 360));
        v1Label.setOpaque(true);
        v1Label.setBackground(Color.BLACK);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.gridheight = 5;
        getContentPane().add(v1Label, gridConstraints);
        v2Label.setPreferredSize(new Dimension(10, 360));
        v2Label.setOpaque(true);
        v2Label.setBackground(Color.BLACK);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 2;
        gridConstraints.gridheight = 5;
        getContentPane().add(v2Label, gridConstraints);

        resetButton.setText("Reset Game");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 7;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(resetButton, gridConstraints);
        resetButton.addActionListener(e -> resetButtonActionPerformed());



        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
    }

    private void labelMouseClicked(MouseEvent e)
    {
        // Decide which picture was clicked (1-9)
        int index;
        boolean win = false;
        Component clickedComponent = e.getComponent();
        for (index = 0; index < 9; index++)
        {
            if (clickedComponent == choiceLabel[index])
            {
                break;
            }
        }
        // If can't click or marker already there return
        if (!canClick || marker[index] != 0)
        {
            return;
        }
        // Increment clicks and  then mark
        numberClicks++;
        if (XTurn)
        {
            marker[index] = 1;
            choiceLabel[index].setIcon(X);
        }
        else
        {
            marker[index] = 2;
            choiceLabel[index].setIcon(O);
        }
        // Check for win
        // Top horizontal win
        if (marker[0] == marker[index] && marker[1] == marker[index] && marker[2] == marker[index])
        {

            win = true;
        }
        // Middle horizontal win
        else if (marker[3] == marker[index] && marker[4] == marker[index] && marker[5] == marker[index])
        {

            win = true;
        }
        // Bottom horizontal win
        else if (marker[6] == marker[index] && marker[7] == marker[index] && marker[8] == marker[index])
        {

            win = true;
        }
        // Left vertical win
        else if (marker[0] == marker[index] && marker[3] == marker[index] && marker[6] == marker[index])
        {

            win = true;
        }
        // Middle vertical win
        else if (marker[1] == marker[index] && marker[4] == marker[index] && marker[7] == marker[index])
        {

            win = true;
        }
        // Right vertical win
        else if (marker[2] == marker[index] && marker[5] == marker[index] && marker[8] == marker[index])
        {

            win = true;
        }
        // Top, left down diagonal win
        else if (marker[0] == marker[index] && marker[4] == marker[index] && marker[8] == marker[index])
        {

            win = true;
        }
        // Bottom, left up diagonal win
        else if (marker[6] == marker[index] && marker[4] == marker[index] && marker[2] == marker[index])
        {

            win = true;
        }
        if (win)
        {

            turnLabel.setText("Win!!");
            canClick = false;
        }
        else
        {
            // If there are nine clicks , its a tie
            if (numberClicks < 9)
            {
                XTurn = !XTurn;
                if (XTurn)
                {
                    whoLabel.setIcon(X);
                }
                else
                {
                    whoLabel.setIcon(O);
                }
            }
            else
            {

                whoLabel.setIcon(null);
                turnLabel.setText("A Tie ...");
                canClick = false;
            }
        }
    }

    private void resetButtonActionPerformed()
    {
        // clear board (loop)
        for (int i = 0; i < 9; i++)
        {
            choiceLabel[i].setIcon(null);
            marker[i] = 0;
        }

        whoLabel.setIcon(X);
        turnLabel.setText("Turn");
        XTurn = true;
        canClick = true;
        numberClicks = 0;

    }

    private void exitForm()
    {
        System.exit(0);
    }
}
