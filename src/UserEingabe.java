import java.util.Scanner;

public class UserEingabe {

    public static void main(String[] args) {

        int ProblemID;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welches Problem möchten Sie lösen?:");
        System.out.println("Problem 1: Straßenbau");
        System.out.println("Problem 2: Wasserversorgung");
        System.out.println("Problem 3: Feuerwerk");
        System.out.println("Problem 4: Hochzeitspaare");
        System.out.println("Problem 5: Einladungen");
        System.out.println("Problem 6: Verteilung auf die Straßen");
        System.out.println("Problem 7: Kompetenz");
        System.out.print("Geben sie die Nummer Ihres gewünschten Problems ein:");

        ProblemID = scanner.nextInt();

        System.out.println("Gewähltes Problem:" + ProblemID);

        switch (ProblemID) {
            case 1:
                System.out.println("Straßenbau");
                break;
            case 2:
                System.out.println("Wasserversorgung");
                break;
            case 3:
                System.out.println("Feuerwerk");
                break;
            case 4:
                System.out.println("Hochzeitspaare");
                break;
            case 5:
                System.out.println("Einladungen");
                break;
            case 6:
                System.out.println("Verteilung auf die Straßen");
                break;
            case 7:
                System.out.println("Kompetenz");
                break;
        }
    }
}
