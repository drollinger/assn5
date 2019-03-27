import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class ReadCode {
    static private int badInputCheckerForInt() {
        Scanner in = new Scanner(System.in);
        do {
            try {
                return in.nextInt();
            }
            catch (java.util.InputMismatchException ex) {
                System.out.print("Please use a number this time: ");
                in.nextLine();
            }
        } while (true);
    }


    private static LeftistHeap<Term> createSpecifiedHeap(String input, ArrayList<Term> listOfWords, int listSize) {
        LeftistHeap<Term> combinedHeap = new LeftistHeap<>();
        String[] listOfOrs = input.toLowerCase().replaceAll("\\s", "").split("\\|");
        for(String prefix : listOfOrs) {
            combinedHeap.merge(binarySearcher(prefix, listOfWords, listSize));
        }
        return combinedHeap;
    }

    private static LeftistHeap<Term> binarySearcher(String wordStart, ArrayList<Term> listOfWords, int listSize) {
        LeftistHeap<Term> priorityWords = new LeftistHeap<>();
        String currentWord;
        int length = listSize/2;
        int position = length;
        boolean found = false;
        boolean inList = true;
        while(!found && inList) {

            currentWord = listOfWords.get(position).word;

            if(currentWord.startsWith(wordStart)) {
                int preindecies = position;

                do {
                    priorityWords.insert(listOfWords.get(preindecies--));
                } while(preindecies >= 0 && listOfWords.get(preindecies).word.startsWith(wordStart));

                position++;
                do {
                    priorityWords.insert(listOfWords.get(position++));
                } while(position < listSize && listOfWords.get(position).word.startsWith(wordStart));

                found = true;
            }
            else if(length == 0) {
                inList = false;
            }
            else {
                length /= 2;
                position += currentWord.compareTo(wordStart) < 0 ? length : -length;
            }
        }

        return priorityWords;
    }

    public static void main(String[] args) {
        try {
            LeftistHeap<Term> leftHeap;
            ArrayList<Term> wordArray = new ArrayList<>();

            String filename = "SortedWords.txt";
            Scanner reader = new Scanner(new File(filename));

            int length = reader.nextInt();
            boolean userWantsToPlay = true;

            while ((reader.hasNext())) {
                String word = reader.next();
                long freq = reader.nextInt();
                wordArray.add(new Term(word, freq));
            }

            System.out.println("Welcome to Autocomplete!!!");

            Scanner in = new Scanner(System.in);
            while(userWantsToPlay) {
                System.out.print("\nEnter the prefix of the word (use | to \"or\" two words): ");

                String input = in.nextLine();
                while (!input.matches("[a-zA-Z |]+")) {
                    System.out.print("Please only use characters and \"|\". Try again: ");
                    input = in.nextLine();
                }

                leftHeap = createSpecifiedHeap(input, wordArray, length);

                System.out.print("Enter the number of outputs: ");
                int count = badInputCheckerForInt();

                System.out.println("\nSubstring: " + input + "\nCount: " + count);

                Term maxValue;
                for (int i = 0; i < count; i++) {
                    maxValue = leftHeap.deleteMax();
                    if(maxValue == null) i = count;
                    else System.out.println("   " + maxValue.word);
                }

                do {
                    System.out.print("\nAgain?(y or n): ");
                    input = in.nextLine();
                    if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
                        userWantsToPlay = false;
                    }
                } while(!(input.toLowerCase().equals("n") || input.toLowerCase().equals("no") || input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")));
            }
            reader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}