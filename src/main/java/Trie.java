import java.util.*;


public class Trie {
    TrieNode root;

    Trie () {
        root = new TrieNode(' ');
    }

    public boolean ifExist(String word) {
        TrieNode curr = root;
        for (char c:word.toCharArray()) {
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
        var chars = word.toCharArray();
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
    }

    public void remove(String word) {
        if (!ifExist(word) || word.length() == 0) return;
        TrieNode curr = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            TrieNode child = curr.getChild(c);
            if (child.count == 1) {
                curr.children.remove(child);
                return;
            } else {
                child.count--;
                curr = child;
            }
        }
        curr.isLastSymbol = false;
    }

    public ArrayList<String> prefixSearch(String prefix) {
        TrieNode curr = root;
        for(char c:prefix.toCharArray()) {
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


    public static void main(String[] args) {
        Trie t = new Trie();
        t.add("qer");
        t.add("qre");
        t.add("qaw");
        t.add("qawr");
        t.printIfExist("qaw");
        t.remove("qaw");
        t.printIfExist("qaw");
        System.out.println(t.prefixSearch("q"));
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