/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xId, int xWeight, int xPrice) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Phone phone = new Phone(xId, xWeight, xPrice);
        Node newNode = new Node(phone);
        Node temp = head;
        while (temp != null) {
            if (temp.info.id.equals(xId)) {
                return;
            }
            temp = temp.next;
        }
        if (xWeight <= 0 || xPrice <= 0) {
            return;
        }
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (null != current.next) {
            current = current.next;
        }
        current.next = newNode;
        //---------------------------------------------------------
    }

    //==================================================================
    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Node v = new Node(new Phone("New", 8, 9));
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        insertPosition(3, v.info);
        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    public void insertPosition(int position, Phone value) {
        //1 --> 4 --> 5
        //1 --> 6 --> 4 --> 5
        Node node = new Node(value);
        if (position == 1) {
            node.next = head;
            head = node;
        } else {
            Node previous = head;
            int count = 1; //position - 1
            while (count < position - 1) {
                previous = previous.next;
                count++;
            }
            Node current = previous.next;
            previous.next = node;
            node.next = current;

        }

    }
//==================================================================

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Node temp = head;
        int count = 1;
        while (temp != null) {
            if (temp.info == getMaxPhone()) {
                break;
            }
            count++;
            temp = temp.next;
        }
        deletePosition(count);
        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    public Phone getMaxPhone() {
        if (isEmpty()) {
            return null;
        }
        Phone max = head.info;
        Node p = head;
        while (p != null) {
            if (p.info.weight > max.weight) {
                max = p.info;
            }
            p = p.next;
        }
        return max;
    }

    public void deletePosition(int position) {
        //position is valid and starting from 1
        // 3 --> 4 --> 7 --> 8 --> 9 --> null
        if (position == 1) {
            head = head.next;
        } else {
            Node previous = head;
            int count = 1;
            while (count < position - 1) {
                previous = previous.next;
                count++;
            }
            Node current = previous.next;
            previous.next = current.next;
        }
    }
//==================================================================

    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        int k = this.countNode();
        f.writeBytes(k + "");
        // hint: you should write a new function named countSomeThing() for this question,
        // then assign int k = this.countSomeThing()
        // finally, you just call f.writeBytes(k + "") to complete this question.
        //---------------------------------------------------------
        f.close();
    }

    public int countNode() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            if (temp.info.price > 5) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }
//==================================================================

    void f5() throws Exception {
        clear();
        loadData(17);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        deleteFirst();
        sort();
        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
    public Node deleteFirst() {
        if (head == null) {
            return null;
        }
        Node temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }

    public void sort() {
        Node pi, pj;
        Phone x;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pj.info.weight > pi.info.weight) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }
}
