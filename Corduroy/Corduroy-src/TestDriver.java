public class TestDriver
{
    public static void main(String[] args)
    {
	Test t = new Test();
	double angle = Math.random() * Math.PI * 2;
	System.out.println("angle: " + angle);
	System.out.println("cosine: " + t.cosine(angle));

	int num1 = (int)(Math.random() * 100);
	int num2 = (int)(Math.random() * 100);
	System.out.println("add: " + t.add(num1, num2));

    }

}