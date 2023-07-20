public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null || word.isEmpty()) {
            return null;
        }

        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); ++i) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (!cc.equalChars(word.charAt(left), word.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }

        return true;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> str = wordToDeque(word);

        return isPalindromeHelper(str);
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque == null) {
            return false;
        }

        if (deque.size() == 1) {
            return true;
        }

        char left = deque.removeFirst();
        char right = deque.removeLast();
        if (left != right) {
            return false;
        } else if (deque.size() == 0) {
            return true;
        } else  {
            return isPalindromeHelper(deque);
        }
    }
}
