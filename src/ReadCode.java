import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class ReadCode {
    public ArrayList<Term> binarySearcher(String wordStart, ArrayList<Term> listOfWords, int length) {
        String currentWord;
        do {

        } while(currentWord)
    }

    public static void main(String[] args) {
        try {
            LeftistHeap<Term> leftHeap = new LeftistHeap<>();
            ArrayList<Term> wordArray = new ArrayList<>();

            String filename = "SortedWords.txt";
            Scanner reader = new Scanner(new File(filename));

            int length = reader.nextInt();

            while ((reader.hasNext())) {
                String word = reader.next();
                long freq = reader.nextInt();
                wordArray.add(new Term(word, freq));
            }

            System.out.print("Enter string: ");
            Scan


            System.out.println("\n\n\n " + leftHeap.deleteMax());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}