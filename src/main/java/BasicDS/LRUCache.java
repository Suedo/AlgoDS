package BasicDS;

import java.util.HashMap;
import java.util.Optional;

public class LRUCache {
    
    private int capacity;
    private int pageCount;
    private Page head, tail;
    // assuming no repeating elem scenario, else value will be Page[], ie an array
    // but if something is repeating, by Cache logic, it should not be put in the cache itself
    private HashMap<Integer, Page> elemPageMap = new HashMap();
    
    class Page {
        Integer elem;
        Page previous;
        Page next;
        
        public Page(Integer elem) {
            this.elem = elem;
            this.previous = null;
            this.next = null;
        }
        
        @Override
        public String toString() {
            return String.format("{%2d, [%2d, %2d]}", elem, this.previous.elem, this.next.elem);
        }
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.pageCount = 0;
        this.head = new Page(-1);
        this.tail = new Page(-2);
        this.head.next = this.tail;
        this.tail.previous = this.head;
    }
    
    // add to tail
    public Page put(int x, Integer elem) {
        checkSize();
        Page newpage = new Page(elem);
        Page oldLast = tail.previous;
        
        oldLast.next = newpage;
        newpage.previous = oldLast;
        
        newpage.next = tail;
        tail.previous = newpage;
        
        elemPageMap.put(elem, newpage);
        pageCount++;
        //        System.out.println("Adding: " + newpage);
        return newpage;
    }
    
    private void checkSize() {
        if (pageCount < capacity) return;
        popHead();
    }
    
    private void popHead() {
        Page currentOldest = head.next;
        Page nextOldest = currentOldest.next;
        
        head.next = nextOldest;
        nextOldest.previous = this.head;
        
        currentOldest.previous = null;
        currentOldest.next = null;
        
        elemPageMap.remove(currentOldest.elem);
        pageCount--;
    }
    
    public Integer get(Integer elem) {
        Page p = elemPageMap.get(elem);
        if (p == null) return -1;
        else {
            moveToTail(p);
            return p.elem;
        }
    }
    
    public void moveToTail(Page p1) {
        Page prev = p1.previous;
        Page next = p1.next;
        
        next.previous = prev;
        prev.next = next;
        
        Page tailprev = tail.previous;
        
        p1.previous = tailprev;
        p1.next = tail;
        tailprev.next = p1;
        tail.previous = p1;
    }
    
    public void print() {
        Page p = this.head;
        for (int i = 0; i < pageCount; i++) {
            System.out.print(p.next.elem + " ");
            p = p.next;
        }
        System.out.println("");
    }
    
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
        cache.print();
    }
}
