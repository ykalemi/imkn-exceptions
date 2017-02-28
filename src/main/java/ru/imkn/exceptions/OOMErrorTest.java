package ru.imkn.exceptions;

import java.util.LinkedList;
import java.util.List;

/**
 * run with VM options: -Xmx1m -XX:+HeapDumpOnOutOfMemoryError
 */
public class OOMErrorTest {

    public static void main(String[] args) {
        List<Long> ll = new LinkedList<Long>();

        long l = 0;
        try {
            while (true) {
                ll.add(new Long(l++));
            }
        } catch (Throwable t) {
            System.out.println("Error!!");
            System.out.println("size:" + ll.size());
        }
        System.out.println("Test finished");
    }
}