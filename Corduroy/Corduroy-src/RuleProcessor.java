import java.util.*;
import java.io.*;

/*
 * RuleProcessor.java
 *
 * Created on September 17, 2008, 2:26 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Kuang Shen
 */
public class RuleProcessor {
    
    /** Creates a new instance of RuleProcessor */
    public RuleProcessor() {
    }
    
    public static Object[] reverse(Object[] a) {
        Object[] array = a.clone();
        Object[] newArray = new Object[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static int[] reverse(int[] a) {
        int[] array = a.clone();
        int[] newArray = new int[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static double[] reverse(double[] a) {
        double[] array = a.clone();
        double[] newArray = new double[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static char[] reverse(char[] a) {
        char[] array = a.clone();
       char[] newArray = new char[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static boolean[] reverse(boolean[] a) {
        boolean[] array = a.clone();
        boolean[] newArray = new boolean[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static long[] reverse(long[] a) {
        long[] array = a.clone();
        long[] newArray = new long[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static short[] reverse(short[] a) {
        short[] array = a.clone();
        short[] newArray = new short[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static float[] reverse(float[] a) {
        float[] array = a.clone();
        float[] newArray = new float[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    public static byte[] reverse(byte[] a) {
        byte[] array = a.clone();
        byte[] newArray = new byte[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = array[array.length-1-i];
        }
        array = newArray;
        return array;
    }
    
    
    public static int[] negate(int[] a) {
        int[] array = a.clone();
        int[] newArray = new int[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = -array[i];
        }
        array = newArray;
        return array;
    }
    
    public static double[] negate(double[] a) {
        double[] array = a.clone();
        double[] newArray = new double[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = -array[i];
        }
        array = newArray;
        return array;
    }
    
    public static float[] negate(float[] a) {
        float[] array = a.clone();
        float[] newArray = new float[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = -array[i];
        }
        array = newArray;
        return array;
    }
    
    public static long[] negate(long[] a) {
        long[] array = a.clone();
        long[] newArray = new long[array.length];
        for (int i=0; i<array.length; i++)
        {
            newArray[i] = -array[i];
        }
        array = newArray;
        return array;
    }
    
    public static Object[] shuffle(Object[] array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static Object[] shuffle(Object[] a, java.util.Random random) {
                Object[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        Object v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
   
    public static int[] shuffle(int[] array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static int[] shuffle(int[] a, java.util.Random random) {
                int[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        int v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static double[] shuffle(double[] array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static double[] shuffle(double[] a, java.util.Random random) {
                double[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        double v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static float[] shuffle(float[] array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static float[] shuffle(float[] a, java.util.Random random) {
                float[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        float v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static char[] shuffle(char[] array) {
               return shuffle(array,new java.util.Random());
    }
    
    public static char[] shuffle(char[] a, java.util.Random random) {
                char[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        char v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static long[] shuffle(long[] array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static long[] shuffle(long[] a, java.util.Random random) {
                long[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        long v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static short[] shuffle(short[] array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static short[] shuffle(short[] a, java.util.Random random) {
                short[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        short v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static byte[] shuffle(byte[] array) {
               return shuffle(array,new java.util.Random());
    }
    
    public static byte[] shuffle(byte[] a, java.util.Random random) {
                byte[] array = a.clone();
                for(int i=0;i<array.length;i++) {
                        int j=random.nextInt(array.length);
                        byte v=array[i];
                        array[i]=array[j];
                        array[j]=v;
                }
                return array;
    }
    
    public static ArrayList shuffle(ArrayList array) {
                return shuffle(array,new java.util.Random());
    }
    
    public static ArrayList shuffle(ArrayList a, java.util.Random random) {
                ArrayList array = new ArrayList(Arrays.asList(a.toArray()));
                for(int i=0;i<array.size();i++) {
                        int j=random.nextInt(array.size());
                        Object v=array.get(i);
                        array.set(i,array.get(j));
                        array.set(j, v);
                }
                return array;
    }
    
    
    public static boolean valueIn(int v, int[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean valueIn(double v, double[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean valueIn(char v, char[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean valueIn(short v, short[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean valueIn(long v, long[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean valueIn(byte v, byte[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean valueIn(float v, float[] vArray) {
        boolean found = false;
        int i = 0;
        while (!found && i<vArray.length)
        {
            if (v == vArray[i])
                found = true;
            i++;
        }
        return found;
    }
    
    public static boolean approximatelyEqualTo(double v1, double v2, double difference)
    {
        if ((v1-v2)<difference || (v2-v1)<difference) 
            return true;
        else return false;
    }
    
    public static void parseKeywordIn(String rule)
    {
        String prefix = rule.split(" in ")[0];
            StringTokenizer st = new StringTokenizer(prefix);
            String lastToken = "";
            while (st.hasMoreTokens()) {
                String nextToken = st.nextToken();
                
                lastToken = nextToken;
            } 
    }
    
    public static boolean inRange(Comparable o, Comparable obj1, Comparable obj2)
    {
        return (o.compareTo(obj1)>=0) && (o.compareTo(obj2)<=0);
    }
    
    public static boolean inRange(double v, double v1, double v2)
    {
        return (v>=v1) && (v<=v2);
    }
    


    
}
