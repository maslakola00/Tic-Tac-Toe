import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;




public class TicTacToe implements ActionListener {

    int size = 3;

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[size * size];
    boolean player1_turn = false;


    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(71, 60, 59));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(194, 149, 145));
        textfield.setForeground(new Color(148, 45, 35));
        textfield.setFont(new Font(("Italic"), Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(size, size));
        button_panel.setBackground(new Color(194, 149, 105));

        for (int i = 0; i < size * size; i++) {

            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Italic", Font.BOLD, 120));
            buttons[i].setBackground(new Color(255, 225, 222));
            buttons[i].setBorder(BorderFactory.createEtchedBorder());
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        FirstTurn();


    }


    /*public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < size * size; i++) {

            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.black);
                        BestMove();
                        player1_turn = false;
                        textfield.setText("O turn");
                        check_resizable();

                    }


                }
                else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.black);
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check_resizable();

                    }

                }
            }
        }
    }*/

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < size * size; i++) {


            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText() == "") {
                    buttons[i].setForeground(Color.black);
                    buttons[i].setText("O");
                    check_resizable();
                    textfield.setText("X turn");
                    BestMove();
                    textfield.setText("O turn");
                    check_resizable();

                }
            }
        }

    }

    public void FirstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(player1_turn=true) {

            textfield.setText("X turn");

        }
        else textfield.setText("O turn");
    }


    public int check_resizable() {

        int i = 0;
        int j = 0;
        int b = 0;
        int k = 0;
        int count = 0;

        /*O WYGRYWA W PIONIE*/
        while (k < size * size) {

            j = 0;
            b = 0;
            k = count;

            for (i = 0; i < size; i++) {

                if (buttons[k].getText() == "O") j++;
                if (j == size) {
                    textfield.setText("O wins!");
                    return -1;
                }
                if (buttons[k].getText() == "X") b++;
                if (b == size) {
                    textfield.setText("X wins!");
                    return 1;
                }

                k += size;

            }
            count++;

        }



        /*O WYGRYWA W POZIOMIE*/
        i = 0;
        j = 0;
        k = 0;
        count = 0;

        while (k < size * size) {

            j = 0;
            b = 0;
            for (i = 0; i < size; i++) {

                if (buttons[k].getText() == "O") j++;
                if (j == size) {
                    textfield.setText("O wins!");
                    return -1;
                }
                if (buttons[k].getText() == "X") b++;
                if (b == size) {
                    textfield.setText("X wins!");
                    return 1;
                }
                k++;

            }


        }


        i = 0;
        j = 0;
        b = 0;
        k = 0;

        /*O WYGRYWA PO PIERWSZEJ PRZEKĄTNEJ*/

        for (i = 0; i < size; i++) {

            if (buttons[k].getText() == "O") j++;
            if (j == size) {
                textfield.setText("O wins!");
                return -1;
            }


            if (buttons[k].getText() == "X") b++;
            if (b == size) {
                textfield.setText("X wins!");
                return 1;
            }

            k += size + 1;
        }


        i = 0;
        j = 0;
        b = 0;
        k = size;

        /*O WYGRYWA PO DRUGIEJ PRZEKĄTNEJ*/

        for (i = 0; i < size; i++) {

            if (buttons[k].getText() == "O") j++;
            if (j == size) {
                textfield.setText("O wins!");
                return -1;
            }
            if (buttons[k].getText() == "X") b++;
            if (b == size) {
                textfield.setText("X wins!");
                return 1;
            }
            k += size - 1;
        }


        return 0;

    }



    public void BestMove() {

        int BestScore = Integer.MIN_VALUE;

        for (int i = 0; i < size * size; i++) {

            if (buttons[i].getText() == "") //sprawdzenie dostepnosci pola
            {
                buttons[i].setText("X");
                int score = minimax(0, true);
                buttons[i].setText(""); //undo

                if (score > BestScore) {
                    BestScore = score;
                    buttons[i].setText("X");
                }
            }
        }

        //player1_turn = false;
    }



    public int minimax(int depth, boolean isMaximizng) {

        int result = check_resizable();

        if (result != 0){
            if (result==1) return 1;
            if (result==-1) return -1;
        }


        if(isMaximizng){

            int bestScore = Integer.MIN_VALUE; //przypisujemy minimalna wartosc zeby ja pozniej zwiekszyc


            for(int i=0;i<size*size;i++){

                if (buttons[i].getText() == ""){
                    buttons[i].setText("X");
                    int score = minimax(depth+1,false);
                    buttons[i].setText("");
                    bestScore = Math.max(score , bestScore);

                }

            }
            return bestScore;
        }



        else{


        int bestScore = Integer.MAX_VALUE;


            for(int i=0;i<size*size;i++){
                if (buttons[i].getText() == ""){
                    buttons[i].setText("O");
                    int score = minimax(depth+1,true);
                    buttons[i].setText("");
                    bestScore = Math.min(score , bestScore);

                }
            }

            return bestScore;

        }

    }











}

