package com.dgvm.ws.utils;

/**
 * Created with IntelliJ IDEA.
 * User: diana
 * Date: 22/12/13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class LengthComparator  implements java.util.Comparator<String> {

        private int referenceLength;

        public LengthComparator() {
            //super();
            //this.referenceLength = reference.length();
        }



    public int compare(String s1, String s2) {
            int dist1 = Math.abs(s1.length() - referenceLength);
            int dist2 = Math.abs(s2.length() - referenceLength);

            return dist1 - dist2;
        }
    }

