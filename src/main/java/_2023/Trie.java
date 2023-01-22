package _2023;

public class Trie {
    public static char a = 'a';
    public char value;
    public boolean isWord;
    public Trie[] children = new Trie[26]; // since all lowercase, else use Map<>

    public Trie(char value) {
        this.value = value;
    }

    public void addWord(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c - a] == null) curr.children[c - a] = new Trie(c);
            curr = curr.children[c - a];
        }
        curr.isWord = true;
    }
}
