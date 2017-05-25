public class Test
{
    public double last;

    /*@
      @assignable last;
 @ensures testCosine(x, \result) == true;
*/
    public double cosine(double x)
    {
	System.out.println("COSINE");
	return last = Math.cos(x);
    }
 public synchronized boolean testCosine(double x, double result) {
    double __last = last;
    try {
        if (!(result == cosine(x + 2 * Math.PI))) return false;
        if (!(result == cosine(-x))) return false;
        return true;
    }
    finally {
        last = __last;
    }
 }



    public static void main(String[] args)
    {
	Test t = new Test();
	t.cosine(Math.PI);
	System.out.println(t.last);
    }


}
