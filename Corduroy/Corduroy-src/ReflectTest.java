import java.lang.reflect.*;
import java.lang.annotation.*;

public class ReflectTest
{
    public static void main(String[] args)
    {
	try
	{
	    // get the Class
	    Class c = Class.forName("Test");
	    
	    // inspect the methods
	    System.out.println("METHODS");
	    Method[] methods = c.getDeclaredMethods();
	    for (Method method : methods)
	    {
		//System.out.println(method.getName());
		System.out.println(method.toGenericString());
		// parameters for the method
		Class[] types = method.getParameterTypes();
		for (Class type : types)
		    System.out.println("   " + type);
		if (method.getModifiers() == Method.PUBLIC) System.out.println("PUBLIC");
	    }

	    System.out.println("\nFIELDS");
	    // now the fields
	    Field[] fields = c.getDeclaredFields();
	    for (Field field : fields)
	    {
		System.out.println(field.getName());
	    }

	    System.out.println("\nCREATE DEFAULT");
	    // now create an instance with the default constructor
	    Object o = c.newInstance();

	    System.out.println("\nINVOKE WITH ARGS");
	    // now try to invoke a method
	    Method m = c.getMethod("testCosine", double.class);
	    System.out.println(m.invoke(o, new Double(10)));
	    

	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
}
