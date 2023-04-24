package eu.epfc.j6077.contactapp;

public class ContactApplication {

    public static void main(String[] args) {
        ContactDao.initialize();
        String choice;
        do {
            System.out.println("\nChoisissez dans les options suivantes");
            System.out.println("(1) lister les contacts");
            System.out.println("(2) ajouter un contact");
            System.out.println("(3) modifier un contact");
            System.out.println("(4) supprimer un contact");
            System.out.println("(Q) quitter l'application");
            choice = ContactUI.input.nextLine();
            switch (choice) {
                case "1" -> ContactUC.consultContacts();
                case "2" -> ContactUC.addContact();
                case "3" -> ContactUC.updateContact();
                case "4" -> ContactUC.deleteContact();
            }
        } while (! choice.equals("Q"));
    }

}
