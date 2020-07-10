package flashcards;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

class Card {
    String term;
    String definition;
    long errorCount;
    Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.errorCount = 0;
    }
    Card(String term, String definition, String ec) {
        this(term, definition);
        this.errorCount = Long.parseLong(ec);
    }
}
public class Main {
    public static HashMap<String, String> termToDefinition = new HashMap<>();
    public static HashMap<String, String> definitionToTerm = new HashMap<>();
    public static TreeSet<String> hardest = new TreeSet<>();
    public static Long highError = 0L;
    public static ArrayList<Card> deck = new ArrayList<>();
    public static ArrayList<String> log = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    public static void addCard() {
        System.out.println("The card:");
        log.add("The card:");
        String term = scanner.nextLine();
        log.add(term);
        if (termToDefinition.containsKey(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            log.add("The card \"" + term + "\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        log.add("The definition of the card:");
        String definition = scanner.nextLine();
        log.add(definition);
        if (definitionToTerm.containsKey(definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            log.add("The definition \"" + definition + "\" already exists.");
            return;
        }
        deck.add(new Card(term, definition));
        termToDefinition.put(term, definition);
        definitionToTerm.put(definition, term);
        System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
        log.add("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }


    public static void removeCard() {
        System.out.println("The card:");
        log.add("The card:");
        String term = scanner.nextLine();
        log.add(term);
        //System.out.println("You entered " + term);
        if (termToDefinition.containsKey(term)) {
            String definition = termToDefinition.get(term);
            //System.out.println("Definition is" + definition);

            //System.out.println(new Card(term, definition) + " " +(deck.get(0)));
            //System.out.println(deck.remove(new String[]{term, definition}));
            for (Card i : deck) {
                if (i.term.equals(term)) {
                    deck.remove(i);
                    break;
                }
            }
            termToDefinition.remove(term);
            //System.out.println(termToDefinition);
            definitionToTerm.remove(definition);
            //System.out.println(definitionToTerm);
            System.out.println("The card has been removed.");
            log.add("The card has been removed.");
            //printAll();
        } else {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
            log.add("Can't remove \"" + term + "\": there is no such card.");
        }
    }
    public static void importCards() {
        System.out.println("File name:");
        log.add("File name:");
        String inputFile = scanner.nextLine();
        log.add(inputFile);
        File file;
        try {
            file = new File(inputFile);
        } catch (Exception e) {
            System.out.println("File not found.");
            log.add("File not found.");
            return;
        }
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("File not found.");
            log.add("File not found");
            return;
        }
        int importCount = 0;
        while (fileScanner.hasNextLine()) {
            String term = fileScanner.nextLine();
            String definition;
            String ec;
            try {
                definition = fileScanner.nextLine();
            } catch (Exception e) {
                return;
            }
            try {
                ec = fileScanner.nextLine();
            } catch (Exception e) {
                ec = "0";
            }
            if (termToDefinition.containsKey(term)) {
                String oldDefinition = termToDefinition.get(term);
                //deck.remove(new String[]{term, oldDefinition});
                for (Card i : deck) {
                    if (i.term.equals(term)) {
                        deck.remove(i);
                        break;
                    }
                }
                termToDefinition.remove(term);
                definitionToTerm.remove(oldDefinition);
            }
            if (definitionToTerm.containsKey(definition)) {
                String oldTerm = definitionToTerm.get(definition);
                //deck.remove(new String[]{oldTerm, definition});
                for (Card i : deck) {
                    if (i.term.equals(oldTerm)) {
                        deck.remove(i);
                        break;
                    }
                }
                termToDefinition.remove(oldTerm);
                definitionToTerm.remove(definition);
            }
            Card temp = new Card(term, definition, ec);
            deck.add(temp);
            termToDefinition.put(term, definition);
            definitionToTerm.put(definition, term);
            if (temp.errorCount == highError) {
                hardest.add(temp.term);
            } else if (temp.errorCount > highError) {
                highError = temp.errorCount;
                hardest.clear();
                hardest.add(temp.term);
            }
            importCount++;
        }
        System.out.println(importCount + " cards have been loaded.");
        log.add(importCount + " cards have been loaded.");
        fileScanner.close();
    }

    public static void exportCards() {
        System.out.println("File name:");
        log.add("File name:");
        String outputFile = scanner.nextLine();
        log.add(outputFile);
        int exportCount = 0;
        try(PrintWriter writer = new PrintWriter(outputFile)) {
            for (Card i : deck) {
                writer.println(i.term);
                writer.println(i.definition);
                writer.println(i.errorCount);
                exportCount++;
            }
        } catch (Exception e) {
            System.out.println("File not Found or Can't be opened");
            log.add("File not Found or Can't be opened");
        }
        System.out.println(exportCount + " cards have been saved.");
        log.add(exportCount + " cards have been saved.");
    }
    public static void askCard() {
        //System.out.println(deck.size());
        System.out.println("How many times to ask?");
        log.add("How many times to ask?");
        int numberOfCards = scanner.nextInt();
        scanner.nextLine();
        log.add(Integer.toString(numberOfCards));
        ArrayList<Card> cards = new ArrayList<>();
        Random random = new Random();
        while (cards.size() < numberOfCards) {
            int i = random.nextInt(deck.size());
            cards.add(deck.get(i));
        }
        for (Card i : cards) {
            System.out.println("Print the definition of \"" + i.term + "\":");
            log.add("Print the definition of \"" + i.term + "\":");
            String ans = scanner.nextLine();
            log.add(ans);
            if (i.definition.equals(ans)) {
                System.out.println("Correct answer");
                log.add("Correct answer");
            } else if (definitionToTerm.containsKey(ans)) {
                i.errorCount += 1;
                System.out.println("Wrong answer, The correct one is \"" + i.definition + "\", you've just written the definition of \"" + definitionToTerm.get(ans) + "\".");
                log.add("Wrong answer, The correct one is \"" + i.definition + "\", you've just written the definition of \"" + definitionToTerm.get(ans) + "\".");
            } else {
                i.errorCount += 1;
                System.out.println("Wrong answer, The correct one is \"" + i.definition + "\".");
                log.add("Wrong answer, The correct one is \"" + i.definition + "\".");
            }
        }
    }

    public static void makeLog() {
        System.out.println("File name:");
        log.add("File name:");
        String logFile = scanner.nextLine();
        log.add(logFile);
        try(PrintWriter writer = new PrintWriter(logFile)) {

            log.add("The log has been saved.");
            for (String i : log) {
                writer.println(i);
            }
            System.out.println("The log has been saved.");
        } catch (Exception e) {
            System.out.println("File can't be opened or not found");
            log.add("File can't be opened or not found");
        }
    }

    public  static void resetStats() {
        for (Card i : deck) {
            i.errorCount = 0;
        }
        highError = 0L ;
        hardest.clear();
        System.out.println("Card statistics has been reset.");
        log.add("Card statistics has been reset.");
    }

    public static void hardestCard() {
        //System.out.println(errorsToTerm + "Done");
        highError = 0L;
        hardest.clear();
        for (Card i : deck) {
            if (i.errorCount > highError) {
                hardest.clear();
                hardest.add(i.term);
                highError = i.errorCount;
            } else if (i.errorCount == highError) {
                hardest.add(i.term);
            }
        }
        if (highError == 0) {
            System.out.println("There are no cards with errors.");
            log.add("There are no cards with errors.");
        } else {
            if (hardest.size() == 1) {
                System.out.println("The hardest card is \"" + hardest.first() + "\". You have " + highError + " errors answering it.");
                log.add("The hardest card is \"" + hardest.first() + "\". You have " + highError + " errors answering it.");
            } else {
                System.out.print("The hardest cards are ");
                log.add("The hardest cards are ");
                for (String i : hardest) {
                    if (hardest.first().equals(i)) {
                        System.out.print("\"" + i + "\"");
                        log.add("\"" + i + "\"");
                    } else {
                        System.out.print(", \"" + i + "\"");
                        log.add(", \"" + i + "\"");
                    }
                }
                System.out.println(". You have " + highError + " errors answering them.");
                log.add(". You have " + highError + " errors answering them.");
            }
        }
    }

    public static void printAll() {
        System.out.println("all deck of size" + deck.size());
        for (Card i : deck) {
            System.out.println(i.term + " " + i.definition + " " + i.errorCount);
        }
    }
    public static void main(String[] args) {
        String inputFile = null;
        String exportFile = null;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-import")) {
                inputFile = args[i + 1];
            } else if (args[i].equals("-export")) {
                exportFile = args[i + 1];
            }
        }
        if (inputFile != null) {
            File file;
            try {
                file = new File(inputFile);
            } catch (Exception e) {
                System.out.println("File not found.");
                log.add("File not found.");
                return;
            }
            Scanner fileScanner;
            try {
                fileScanner = new Scanner(file);
            } catch (Exception e) {
                System.out.println("File not found.");
                log.add("File not found");
                return;
            }
            int importCount = 0;
            while (fileScanner.hasNextLine()) {
                String term = fileScanner.nextLine();
                String definition;
                String ec;
                try {
                    definition = fileScanner.nextLine();
                } catch (Exception e) {
                    return;
                }
                try {
                    ec = fileScanner.nextLine();
                } catch (Exception e) {
                    ec = "0";
                }
                if (termToDefinition.containsKey(term)) {
                    String oldDefinition = termToDefinition.get(term);
                    //deck.remove(new String[]{term, oldDefinition});
                    for (Card i : deck) {
                        if (i.term.equals(term)) {
                            deck.remove(i);
                            break;
                        }
                    }
                    termToDefinition.remove(term);
                    definitionToTerm.remove(oldDefinition);
                }
                if (definitionToTerm.containsKey(definition)) {
                    String oldTerm = definitionToTerm.get(definition);
                    //deck.remove(new String[]{oldTerm, definition});
                    for (Card i : deck) {
                        if (i.term.equals(oldTerm)) {
                            deck.remove(i);
                            break;
                        }
                    }
                    termToDefinition.remove(oldTerm);
                    definitionToTerm.remove(definition);
                }
                Card temp = new Card(term, definition, ec);
                deck.add(temp);
                termToDefinition.put(term, definition);
                definitionToTerm.put(definition, term);
                if (temp.errorCount == highError) {
                    hardest.add(temp.term);
                } else if (temp.errorCount > highError) {
                    highError = temp.errorCount;
                    hardest.clear();
                    hardest.add(temp.term);
                }
                importCount++;
            }
            System.out.println(importCount + " cards have been loaded.");
            log.add(importCount + " cards have been loaded.");
            fileScanner.close();
        }
        boolean looping = true;
        while (looping) {
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String action = scanner.nextLine();
            log.add(action);
            switch (action) {
                case "add" :
                    addCard();
                    break;
                case "remove" :
                    removeCard();
                    break;
                case "import" :
                    importCards();
                    break;
                case "export" :
                    exportCards();
                    break;
                case "ask" :
                    askCard();
                    break;
                case "exit" :
                    System.out.println("Bye bye!");
                    looping = false;
                    int exportCount = 0;
                    if (exportFile != null) {

                        try (PrintWriter writer = new PrintWriter(exportFile)) {
                            for (Card i : deck) {
                                writer.println(i.term);
                                writer.println(i.definition);
                                writer.println(i.errorCount);
                                exportCount++;
                            }
                        } catch (Exception e) {
                            System.out.println("File not Found or Can't be opened");
                            log.add("File not Found or Can't be opened");
                        }
                        System.out.println(exportCount + " cards have been saved.");
                        log.add(exportCount + " cards have been saved.");
                    }
                    break;
                case "hardest card" :
                    //System.out.println("hc");
                    hardestCard();
                    break;
                case "log" :
                    makeLog();
                    break;
                case "reset stats" :
                    resetStats();
                    break;
                default:
                    //System.out.println("def");
                    printAll();
                    break;
            }
        }
    }
}
