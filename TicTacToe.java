
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;





    public class TicTacToe implements ActionListener {

        int size = 4;
        int amount = 3;


        JFrame frame = new JFrame();

        JPanel title_panel = new JPanel();

        JPanel button_panel = new JPanel();
        JLabel textfield = new JLabel();
        JButton[] buttons = new JButton[size * size];



        TicTacToe() {

            ImageIcon image = new ImageIcon("background.jpg");


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.getContentPane().setBackground(new Color(71, 60, 59));
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);

            textfield.setBackground(new Color(194, 149, 145));
            textfield.setForeground(new Color(148, 45, 35));
            textfield.setFont(new Font(("Arial"), Font.BOLD, 75));
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


        }


        public void actionPerformed(ActionEvent e) {


            for (int i = 0; i < size * size; i++)
                if (e.getSource() == buttons[i]) {
                    if (buttons[i].getText().equals("")) {

                        buttons[i].setForeground(Color.black);
                        buttons[i].setText("O");
                        check_resizable();
                        if (check_resizable() == -1000) {
                            textfield.setText("O wins!");
                            buttons_off();
                            break;
                        }
                        textfield.setText("X turn");
                        if(check_resizable()==1){
                            buttons_off();
                            textfield.setText("It's a draw!");
                        }
                        BestMove();
                        check_resizable();
                        if (check_resizable() == 1000) {
                            textfield.setText("X wins!");
                            buttons_off();
                            break;
                        }
                        textfield.setText("O turn");

                        if(check_resizable()==1){
                            buttons_off();
                            textfield.setText("It's a draw!");
                        }

                    }
                }

        }

        public void buttons_off(){

            for(int i=0;i<size*size;i++){
                buttons[i].setEnabled(false);
            }

        }


        public int check_resizable() {

            int i = 0;
            int j = 0;
            int b = 0;
            int k = 0;
            int count = 0;

          //  O WYGRYWA W PIONIE
            while (count <= size - 1) {

                j = 0;
                b = 0;
                k = count;

                for (i = 0; i < size; i++) {



                    if (buttons[k].getText().equals("O")) {
                        j++;


                        if (j == amount) {
                            return -1000;
                        }

                    }
                    else j=0;


                    if (buttons[k].getText().equals("X")) {
                        b++;

                        if (b == amount) {

                            return 1000;
                        }
                    }
                    else b=0;


                    k += size;

                }

                count++;

            }



          //  O WYGRYWA W POZIOMIE
            i = 0;
            j = 0;
            k = 0;


            while (k < size * size) {

                j = 0;
                b = 0;
                for (i = 0; i < size; i++) {



                    if (buttons[k].getText().equals("O")) {
                        j++;

                        if (j == amount) {

                            return -1000;
                        }
                    }
                    else j=0;

                    if (buttons[k].getText().equals("X")) {
                        b++;

                        if (b == amount) {


                            return 1000;
                        }
                    }
                    else b=0;

                    k++;

                }


            }


            i = 0;
            j = 0;
            b = 0;
            k = 0;

          //  O WYGRYWA PO PIERWSZEJ PRZEKĄTNEJ

            for (i = 0; i < size; i++) {



                if (buttons[k].getText().equals("O")) {
                    j++;

                    if (j == amount) {

                        return -1000;
                    }

                }
                else j=0;


                if (buttons[k].getText().equals("X")) {
                    b++;

                    if (b == amount) {

                        return 1000;
                    }
                }
                else b=0;

                k += size + 1;
            }


            i = 0;
            j = 0;
            b = 0;
            k = size;

         //   O WYGRYWA PO DRUGIEJ PRZEKĄTNEJ

            for (i = 0; i < size; i++) {




                if (buttons[k - 1].getText().equals("O")) {
                    j++;

                    if (j == amount) {
                        return -1000;
                    }
                }
                else j=0;

                if (buttons[k - 1].getText().equals("X")) {
                    b++;

                    if (b == amount) {
                        return 1000;
                    }
                }
                else b=0;

                k += size - 1;
            }

            i=0;
            int d=0;

            for( i=0; i< size*size;i++)
            {
                if(buttons[i].getText().equals("X") || buttons[i].getText().equals("O"))
                    d++;
            }
            if(d==size*size){
                return 1;
            }


            return 0;

        }


        public void BestMove() {

            int BestScore = Integer.MIN_VALUE;


            int index = 0;
            for (int i = 0; i < size * size; i++) {

                if (buttons[i].getText().equals("")) //sprawdzenie dostepnosci pola
                {
                    buttons[i].setText("X");
                    int moveVal = minimax(0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    buttons[i].setText(""); //undo

                    if (moveVal > BestScore) {
                        BestScore = moveVal;
                        index = i;
                    }
                }

            }
            buttons[index].setText("X");

        }

        public boolean movesleft() {


            for (int i = 0; i < size * size; i++) {
                if (buttons[i].getText().equals("")) return true;
            }

            return false;

        }


        public int minimax(int depth, int alpha, int beta, boolean isMaximizng) {

            int result = check_resizable();

            if(result!=0) {
                if (result == 1000) return 1000 - depth;
                if (result == -1000) return -1000 + depth;
                if (result == 1) return 1;
            }




            if (depth == 5) return 10000;

            if (isMaximizng) {

                int bestScore = Integer.MIN_VALUE; //przypisujemy minimalna wartosc zeby ja pozniej zwiekszyc


                for (int i = 0; i < size * size; i++) {

                    if (buttons[i].getText().equals("")) {

                        buttons[i].setText("X");
                        int score = minimax(depth + 1, alpha, beta, false);
                        buttons[i].setText("");
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, score);
                        if (beta <= alpha) break;

                    }

                }
                return bestScore;
            } else {


                int bestScore = Integer.MAX_VALUE;


                for (int i = 0; i < size * size; i++) {
                    if (buttons[i].getText().equals("")) {

                        buttons[i].setText("O");
                        int score = minimax(depth + 1, alpha, beta, true);
                        buttons[i].setText("");
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, score);
                        if (beta <= alpha) break;

                    }
                }

                return bestScore;

            }

        }

    }





