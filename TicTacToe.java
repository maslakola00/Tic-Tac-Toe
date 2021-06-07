
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TicTacToe implements ActionListener {


            int size; //rozmiar tablicy
            int amount; //ilosc znakow wyrgywajacych w rzedzie



        JFrame frame = new JFrame();

        JPanel title_panel = new JPanel(); //tworzenie panelu tekstowego

        JPanel button_panel = new JPanel();
        JLabel textfield = new JLabel();
        JButton[] buttons;//tworzenie tablicy guzikow


        TicTacToe(int size1 , int amount1) {

            size = size1;
            amount=amount1;

            buttons = new JButton[size * size];
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamykanie programu przy uzyciu X
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
                        if (check_resizable() == 1) {
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

                        if (check_resizable() == 1) {
                            buttons_off();
                            textfield.setText("It's a draw!");
                        }
                    }
                }
        }


        //funkcja wylaczajaca guziki
        public void buttons_off() {

            for (int i = 0; i < size * size; i++) {
                buttons[i].setEnabled(false); //wylaczenie wszystkich guzikow po skonczonej grze
            }

        }


    //funkcja sprawdzajaca wygrana/remis
        public int check_resizable() {

            int j;
            int b;
            int k;
            int count = 0;

            //Wygrana w pionie
            while (count <= size - 1) {
                j = 0;
                b = 0;
                k = count;

                for (int i = 0; i < size; i++) {
                    if (buttons[k].getText().equals("O")) {
                        j++;
                        if (j == amount) return -1000;
                    }
                    else j = 0;

                    if (buttons[k].getText().equals("X")) {
                        b++;
                        if (b == amount) return 1000;
                    }
                    else b = 0;

                    k += size;
                }
                count++;
            }


            //Wygrana w poziomie
            k = 0;

            while (k < size * size) {

                j = 0;
                b = 0;
                for (int i = 0; i < size; i++) {
                    if (buttons[k].getText().equals("O")) {
                        j++;

                        if (j == amount) return -1000;
                    }
                    else j = 0;

                    if (buttons[k].getText().equals("X")) {
                        b++;
                        if (b == amount) return 1000;
                    }
                    else b = 0;
                    k++;
                }
            }


            j = 0;
            b = 0;
            k = 0;

            //Wygrana na przekatnej diagonalnej

            for (int i = 0; i < size; i++) {


                if (buttons[k].getText().equals("O")) {
                    j++;
                    if (j == amount) return -1000;
                }
                else j = 0;

                if (buttons[k].getText().equals("X")) {
                    b++;
                    if (b == amount) return 1000;
                }
                else b = 0;

                k += size + 1;
            }


            j = 0;
            b = 0;
            k = size;

            //Wygrana na drugiej przekatnej

            for (int i = 0; i < size; i++) {
                if (buttons[k - 1].getText().equals("O")) {
                    j++;

                    if (j == amount) return -1000;
                }
                else j = 0;

                if (buttons[k - 1].getText().equals("X")) {
                    b++;
                    if (b == amount) return 1000;
                }
                else b = 0;

                k += size - 1;
            }


            //Remis

            int d = 0;

            for (int i = 0; i < size * size; i++) {
                if (buttons[i].getText().equals("X") || buttons[i].getText().equals("O"))
                    d++;
            }
            if (d == size * size) {
                return 1;
            }

            //Gra toczy sie dalej
            return 0;
        }



        //funkcja sprawdzajaca ile danych znakow znajduje sie w pionie/poziomie/na przekatnych
        public int Calculate_current_state(String sign) {
            int count2 = 0;
            int result = 0;
            int count = 0;
            int k;


            //Ilosc znakow w pionie
            while (count <= size - 1) {

                k = count;
                count2 = 0;

                for (int i = 0; i < size; i++) {

                    if (buttons[k].getText().equals(sign)) count2++;
                    k += size;
                }
                count++;

                if (count2 != 0) result += count * 10;
            }

            k = 0;

            //Ilosc znakow w poziomie
            while (k < size * size) {

                count2 = 0;
                for (int i = 0; i < size; i++) {
                    if (buttons[k].getText().equals(sign)) count2++;

                    k++;
                }
                if (count2 != 0) result += count * 10;
            }


            //Ilosc znakow na przekatnej diagonalnej
            k = 0;
            for (int i = 0; i < size; i++) {

                if (buttons[k].getText().equals(sign)) count2++;

                k += size + 1;
            }
            if (count2 != 0) result += count * 10;


            k = size;

            //Ilosc znakow na drugiej przekatnej
            for (int i = 0; i < size; i++) {
                if (buttons[k - 1].getText().equals(sign)) count2++;
                k += size - 1;
            }

            if (count2 != 0) result += count * 10;


            return result; //zwrocenie ilosci znakow
        }



        //funkcja znajdujaca najlepszy mozliwy ruch
        public void BestMove() {

            int BestScore = Integer.MIN_VALUE; //ustawienie BestScore na -inf


            int index = 0;
            for (int i = 0; i < size * size; i++) {

                if (buttons[i].getText().equals("")) //sprawdzenie dostepnosci pola
                {
                    buttons[i].setText("X"); //wpisanie X w pierwsze wolne miejsce
                    int moveVal = minimax(0, Integer.MIN_VALUE, Integer.MAX_VALUE, false); //wywolanie minimax
                    buttons[i].setText(""); //usuniecie wczesniej wstawionego X

                    if (moveVal > BestScore) {
                        BestScore = moveVal;
                        index = i;
                    }
                }

            }
            buttons[index].setText("X"); //postawienie X na najlepszej mozliwej pozycji

        }



        //algorytm minimax
        public int minimax(int depth, int alpha, int beta, boolean isMaximizng) {

            int result = check_resizable(); //sprawdzenie czy ktos wygral

            if (result != 0) {
                if (result == 1000) return 1000 - depth; //X wygrywa
                if (result == -1000) return -1000 + depth; //O wygrywa
                if (result == 1) return 1; //remis
            }


            if (depth == 5) {
                if (isMaximizng) return Calculate_current_state("O"); //zwrocenie stanu gry dla O gdy gra trwa
                else return (-1) * Calculate_current_state("X");//zwrocenie stanu gry dla X gdy gra trwa
            }

            int bestScore;//zmienna przechowujaca najlepszy wynik

            //gra gracz maksymalizujacy - komputer
            if (isMaximizng) {
                bestScore = Integer.MIN_VALUE; //ustawienie zmiennej na -inf

                for (int i = 0; i < size * size; i++) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("X");
                        int score = minimax(depth + 1, alpha, beta, false); //rekurencyjne wywolanie minimax
                        buttons[i].setText("");
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, score);//szukanie maksymalnej wartosci
                        if (beta <= alpha) break;
                    }
                }
            }
            //gra gracz minimalizujacy
            else {
                bestScore = Integer.MAX_VALUE;//ustawienie zmiennej na +inf

                for (int i = 0; i < size * size; i++) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("O");
                        int score = minimax(depth + 1, alpha, beta, true); //rekurencyjne wywolanie minimax
                        buttons[i].setText("");
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, score); //szukanie minimalnej wartosci
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        }
    }






