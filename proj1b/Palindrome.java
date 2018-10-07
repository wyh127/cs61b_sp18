public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /** implementing is Palindrome function and its helper function */
    public boolean isPalindrome(String word) {
        return helper(wordToDeque(word));
    }

    public boolean helper(Deque<Character> deq) {
        if(deq.size() == 0 || deq.size() == 1) return true;
        char first = deq.removeFirst();
        char last = deq.removeLast();

        if(first == last) {
            return helper(deq);
        }
        return false;
    }

    /** */

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return helper1(wordToDeque(word), cc);
    }

    public boolean helper1(Deque<Character> deq, CharacterComparator cc) {
        if(deq.size() == 0 || deq.size() == 1) return true;
        char first = deq.removeFirst();
        char last = deq.removeLast();

        if(cc.equalChars(first, last)) {
            return helper1(deq, cc);
        }
        return false;
    }






}
