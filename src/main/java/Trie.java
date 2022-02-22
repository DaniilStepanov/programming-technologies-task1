import java.util.*;

import static java.util.Objects.hash;


public class Trie {
    TrieNode root;
    ArrayList<String> words;

    Trie () {
        root = new TrieNode(' ');
        words = new ArrayList<>();
    }

    public boolean ifExist(String word) {
        TrieNode curr = root;
        for (char c:word.trim().toLowerCase().toCharArray()) {
            if (curr.getChild(c) == null) return false;
            else curr = curr.getChild(c);
        }
        return curr.isLastSymbol;
    }

    public void printIfExist(String word) {
        if (ifExist(word)) System.out.printf("Слово \"%s\" найдено\n", word);
        else System.out.printf("Слово \"%s\" не найдено\n", word);
    }


    public void add(String word) {
        if (ifExist(word) || word.length() == 0) return;
        TrieNode curr = root;
        var chars = word.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            TrieNode child = curr.getChild(chars[i]);
            if (child != null) curr = child;
            else {
                curr.children.add(new TrieNode(chars[i]));
                curr = curr.getChild(chars[i]);
            }
            curr.count++;
            curr.text = word.substring(0, i + 1);
        }
        curr.isLastSymbol = true;
        words.add(word.trim().toLowerCase());
    }

    public void remove(String word) {
        if (!ifExist(word) || word.trim().length() == 0) return;
        TrieNode curr = root;
        char[] chars = word.trim().toLowerCase().toCharArray();
        for (char c : chars) {
            TrieNode child = curr.getChild(c);
            if (child.count == 1) {
                curr.children.remove(child);
                words.remove(word);
                return;
            } else {
                child.count--;
                curr = child;
            }
        }
        curr.isLastSymbol = false;
        words.remove(word.trim().toLowerCase());
    }

    public ArrayList<String> prefixSearch(String prefix) {
        TrieNode curr = root;
        for(char c:prefix.toLowerCase().toCharArray()) {
            curr = curr.getChild(c);
            if(curr == null) return null;
        }
        return allWords(curr);
    }

    private ArrayList<String> allWords(TrieNode node) {
        if (node == null) return null;
        ArrayList<String> res = new ArrayList<>();
        if (node.isLastSymbol) res.add(node.text);
        for (TrieNode child:node.children) {
            res.addAll(allWords(node.getChild(child.value)));
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trie t = (Trie) obj;
        return this.words.equals(t.words);
    }

    @Override
    public String toString() {
        return this.words.toString();
    }

    @Override
    public int hashCode() {
        return hash(this.words);
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        Trie t1 = new Trie();
        t.add("qer");
        t1.add("qer");
        t1.add("qaw");
        System.out.println(t1.words);
        t1.remove("qaw");
        System.out.println(t1.words);
    }
}

class TrieNode {
    char value;
    ArrayList<TrieNode> children;
    boolean isLastSymbol;
    int count;
    String text;

    TrieNode(char value) {
        this.value = value;
        isLastSymbol = false;
        children = new ArrayList<>();
        count = 0;
    }

    public TrieNode getChild(char input) {
        if (children != null) {
            for (TrieNode child : children) {
                if (child.value == input) return child;
            }
        }
        return null;
    }
}