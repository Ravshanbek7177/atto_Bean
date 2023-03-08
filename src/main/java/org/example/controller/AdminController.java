package org.example.controller;


import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Terminal;
import org.example.dto.Transaction;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;
import org.example.service.CardService;
import org.example.service.ProfileService;
import org.example.service.TerminalService;
import org.example.util.ScannerUtil;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminController {

    private CardService cardService;
    private ProfileService profileService ;
    private TerminalService terminalService ;
    private TransactionRepository transactionRepository;
    private CardRepository cardRepository;
    private List<Transaction> transactionList = new LinkedList<>();

    public AdminController() {
    }


    public void start() {
        boolean b = true;
        while (b) {
            menu();
            int operation = ScannerUtil.getAction();
            switch (operation) {
                case 1:
                    addCard();
                    break;
                case 2:
                    cardList();
                    break;
                case 3:
                    updateCard();
                    break;
                case 4:
                    changeCardStatus();
                    break;
                case 5:
                    deleteCard();
                    break;
                case 6:
                    createTerminal();
                    break;
                case 7:
                    terminalList();
                    break;
                case 8:
                    updateTerminal();
                    break;
                case 9:
                    changeTerminalStatus();
                    break;
                case 10:
                    deleteTerminal();
                    break;
                case 11:
                    profileList();
                    break;
                case 12:
                    changeProfileStatus();
                    break;
                case 13:
                    List<Transaction> transactionList1 = transactionList();
                    this.transactionList = transactionList1;
                    break;
                case 14:
                    cardCompany();
                case 15:
                    todayTransactionList();
                case 16:
                    transactionByDay();
                case 17:
                    transactionBetweenDays();
                case 18:
                    totalBalance();
                case 19:
                    transactionByTerminal();
                case 20:
                    transactionByCard();
                    break;
                case 0:
                    b = false;
                    break;
                default:
                    b = false;
                    System.out.println("Wrong operation number");
            }
        }
    }

    public void menu() {
        // (Card)
        System.out.println("1. Create Card");
        System.out.println("2. Card List ");
        System.out.println("3. Update Card ");
        System.out.println("4. Card Change Status");
        System.out.println("5. Delete Card");
        // (Terminal)
        System.out.println("6. Create Terminal");
        System.out.println("7. Terminal List");
        System.out.println("8. Update Termina");
        System.out.println("9. Change Terminal Status");
        System.out.println("10. Delete");
        //  (Profile)
        System.out.println("11. Profile List");
        System.out.println("12. Change Profile Status");
        // (Transaction)
        System.out.println("13. Transaction List");
        System.out.println("14. Company Card Balance");
        //  (Statistic)
        System.out.println("15. Bugungi to'lovlar");
        System.out.println("16. Kunlik to'lovla");
        System.out.println("17. Oraliq to'lovlar");
        System.out.println("18. Umumiy balance");
        System.out.println("19. Transaction by Terminal");
        System.out.println("20. Transaction By Card");
        System.out.println("0. Log out");
    }

    /**
     * Card
     */


    private void addCard() {
        System.out.print("Enter card number: ");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();
        System.out.print("Enter card expired date (yyyy.MM.dd): ");
        String expiredDate = scanner.nextLine();
        cardService.adminCreateCard(cardNumber, expiredDate);
    }

    private void cardList() {
        cardService.cardList();
    }

    private void deleteCard() {
        System.out.print("Enter card number: ");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();

       cardService.adminDeleteCard(cardNumber);
    }

    private void changeCardStatus() {
        System.out.print("Enter card number: ");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();

        cardService.adminChangeStatus(cardNumber);
    }

    private void updateCard() {
        System.out.print("Enter card number: ");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();

        System.out.print("Enter card expired date (yyyy.MM.dd): ");
        String expiredDate = scanner.nextLine();

       cardService.adminUpdateCard(cardNumber, expiredDate);
    }


    /**
     * Terminal
     */

    private void createTerminal() {
        System.out.print("Enter  code: ");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setAddress(address);

        terminalService.addTerminal(terminal);
    }

    private void terminalList() {
        terminalService.terminalList();
    }

    private void updateTerminal() {
        System.out.print("Enter code: ");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setAddress(address);

        terminalService.updateTerminal(terminal);
    }

    private void changeTerminalStatus() {
        System.out.print("Enter code: ");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        terminalService.changeTerminalStatus(code);
    }

    private void deleteTerminal() {
        System.out.print("Enter code: ");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        terminalService.deleteTerminal(code);
    }

    /**
     * Profile
     */

    private void profileList() {
        profileService.profileList();
    }

    private void changeProfileStatus() {
        System.out.print("Enter profile phone: ");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();

        profileService.changeProfileStatus(phone);
    }


    /**
     * Transaction
     *
     * @return
     */

    private List<Transaction> transactionList() {
        List<Transaction> transactionList = transactionRepository.admintransactionList();
        transactionList.forEach(System.out::println);
        return transactionList;
    }

    private void cardCompany() {
        Card card = cardRepository.getCardByNumber("5555");
        System.out.println("Balance -> " + card.getBalance());
    }

    /**
     * Statistic
     */

    private void todayTransactionList() {
        List<Transaction> transactionList = transactionRepository.admintransactionList();
        for (Transaction transaction : transactionList) {
            if (transaction.getCreatedDate().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
                System.out.println(transaction);
            }
        }
    }

    private void transactionByDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date of month: ");
        String date = scanner.nextLine();
        System.out.println("Enter month: ");
        String month = scanner.nextLine();
        System.out.println("Enter year: ");
        String year = scanner.nextLine();
        List<Transaction> transactionList = transactionRepository.admintransactionList();
        for (Transaction transaction : transactionList) {
            if ((String.valueOf(transaction.getCreatedDate().getDayOfMonth()).equals(date)) &&
                    (String.valueOf(transaction.getCreatedDate().getMonthValue()).equals(month)) &&
                    (String.valueOf(transaction.getCreatedDate().getYear()).equals(year))) {
                System.out.println(transaction);
            }
        }


    }

    private void transactionBetweenDays() {

    }

    private void totalBalance() {

    }

    private void transactionByTerminal() {

    }

    private void transactionByCard() {

    }


    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    public TerminalService getTerminalService() {
        return terminalService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public CardService getCardService() {
        return cardService;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardRepository getCardRepository() {
        return cardRepository;
    }
}
