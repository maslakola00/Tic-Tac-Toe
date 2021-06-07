import java.awt.Color;
import java.awt.Font;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {


        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Podaj rozmiar tablicy: ");
                int size1 = scanner.nextInt();
                System.out.println("Podaj ilosc znakow: ");
                int amount1 = scanner.nextInt();

                TicTacToe game = new TicTacToe(size1,amount1);


        }


}
