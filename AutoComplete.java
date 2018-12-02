import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

class AutoComplete {
  private static List<String> input;
  private static List<Integer> occurence;
  private static TrieNode root;

  public static void main(String[] args) {
    initialise();
    handleInput();
    handleStream("i love ");
    // handleStream("i ");
  }

  private static void handleStream(String s) {
    TrieNode traverse = root;
    for (int i =0; i < s.length(); i++) {
      Character c = s.charAt(i);
      traverse = traverse.child.get(c);
    }
    if (traverse == null) {
      System.out.println("Nothing exist");
    } else {
      findTopK(traverse);
    }
  }

  private static void findTopK(TrieNode node) {
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.count == b.count ? a.s.compareTo(b.s) : b.count - a.count);
    // node.freq.forEach((k, v) -> (pq.offer(new Pair(k, v))));
    for (Map.Entry<String, Integer> entry : node.freq.entrySet()) {
      pq.offer(new Pair(entry.getKey(), entry.getValue()));
    }
    int k = 0;
    List<String> res = new ArrayList<String>();
    while (k < 3 && !pq.isEmpty()) {
      res.add(pq.poll().s);
      k++;
    }
    for (int i =0; i <res.size(); i++) {
      System.out.println(res.get(i));
    }
  }

  private static void handleInput() {
    for (int i =0; i <input.size(); i++) {
      insertIntoTrie(input.get(i), occurence.get(i));
    }
  }

  private static void insertIntoTrie(String input, int count) {
    TrieNode traverse = root;
    for (int i = 0; i <input.length(); i++) {
      Character c = input.charAt(i);

      // if (!traverse.child.containsKey(c))
      //   traverse.child.put(c, new TrieNode());
      traverse.child.putIfAbsent(c, new TrieNode());
      traverse = traverse.child.get(c);
      
      // if (!traverse.freq.containsKey(input))
      //   traverse.freq.put(input, count);
      // else
      //   traverse.freq.put(input, traverse.freq.get(input) + count);
        traverse.freq.put(input, traverse.freq.getOrDefault(input, 0) + count);
    }
  }

  private static void initialise() {
    input = new ArrayList<String>();  
    occurence = new ArrayList<Integer>(Arrays.asList(5, 3, 2, 1));
    input.add("i love you"); // 5
    input.add("island"); // 3
    input.add("ironman"); // 2
    input.add("i love leetcode"); // 1
    root = new TrieNode(); 
  }

  static class TrieNode {
    HashMap<String, Integer> freq;
    HashMap<Character, TrieNode> child;
    TrieNode() {
      freq = new HashMap<String, Integer>();
      child = new HashMap<Character, TrieNode>();
    }
  }

  static class Pair {
    String s;
    int count;
    Pair(String temp, int c) {
      s = temp;
      count = c;
    }
  }
}
