package organization;

/*
*Created by: Chris Dovolis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

public class N {
    public N() {}
    public N(Object o, N link) {
        data = o;
        next = link; }
    public Object getData() {
        return data; }
    public void setData(Object o) {
        data = o; }
    public N getNext() {
        return next; }
    public void setNext(N link) {
        next = link; }
    private Object data;
    private N next;


    public static N reverseList(N list) {
        N current = list;
        int tracker = 0;
        while (current.getNext() != null) {
            current = current.getNext();
            tracker++;
        }

        N reversed = new N(current.getData(),null);
        N cRev = reversed;
        System.out.println("Tracker: " + tracker);
        for (int i = tracker; i > 0; i--) {
            N c = list;
            int inner = 1;
            while (inner < i) {
                c = c.getNext();
                inner++;
            }
            cRev.setNext(new N(c.getData(),null));
            cRev = cRev.getNext();
        }
        return reversed;
    }

    public static void main(String[] args) {
        N list = new N(1,null);
        list.setNext(new N(2,null));
        list.getNext().setNext(new N(3,null));
        list.getNext().getNext().setNext(new N(4,null));
        N current = reverseList(list);
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
} // N class
