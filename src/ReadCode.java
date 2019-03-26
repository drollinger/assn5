import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class ReadCode {
    public static LeftistHeap<Term> binarySearcher(String wordStart, ArrayList<Term> listOfWords, int listSize) {
        LeftistHeap<Term> priorityWords = new LeftistHeap<>();
        String currentWord;
        int length = listSize;
        int position = length/2;
        boolean found = false;
        boolean inList = true;
        while(!found && inList) {

            currentWord = listOfWords.get(position).word;

            if(currentWord.startsWith(wordStart)) {
                Term temp;
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

            Scanner in = new Scanner(System.in);
            System.out.print("Enter string: ");
            String input = in.nextLine();



            leftHeap = binarySearcher(input, wordArray, length);

            for(int i = 0; i < 10; i++) {
                System.out.println(leftHeap.deleteMax());
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}