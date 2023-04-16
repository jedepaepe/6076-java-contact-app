package eu.epfc.j6077.contactapp;

import java.util.Scanner;

public class Bootstrapper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Choisissez dans les options suivantes");
            System.out.println("(1) lister les contacts");
            System.out.println("(2) ajouter un contact");
            System.out.println("(3) modifier un contact");
            System.out.println("(4) supprimer un contact");
            System.out.println("(Q) quitter l'application");
            choice = input.nextLine();
        } while (! choice.equals("Q"));
    }
}
