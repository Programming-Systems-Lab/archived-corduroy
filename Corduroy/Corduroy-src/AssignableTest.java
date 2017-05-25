import java.lang.reflect.*;
import java.util.ArrayList;

public class AssignableTest
{
    private ArrayList<String> types = new ArrayList<String>();

    private int name;
    private double foo;
    private CloneableTest test;
    private String testString;

    public static void main(String[] args)
    {
	AssignableTest t = new AssignableTest();
	t.createRule("AssignableTest", "name");
	t.createRule("AssignableTest", "foo");
	t.createRule("AssignableTest", "test");
	t.createRule("AssignableTest", "testString");
    }

    public AssignableTest()
    {
	types.add("byte");
	types.add("short");
	types.add("int");
	types.add("long");
	types.add("double");
	types.add("float");
	types.add("boolean");
	types.add("char");
	types.add("java.lang.String");
    }


    public void createRule(String className, String fieldName)
    {
	try
	{
	    // get the Class
	    Class c = Class.forName(className);
	    
	    // now the fields - IS THERE A WAY TO JUST GET THE ONE WE WANT???
	    Field[] fields = c.getDeclaredFields();
	    for (Field field : fields)
	    {
		//System.out.println(field.getName());
		if (field.getName().equals(fieldName))
		{
		    // get the class/type of this variable
		    Class type = field.getType();

		    // if it's a primitive, just copy it
		    if (types.contains(type.getName()))
			System.out.println(type.getName() + " __" + fieldName + " = " + fieldName + ";");
		    // otherwise, see if it implements cloneable
		    else
		    {
			Class[] interfaces = type.getInterfaces();
			boolean implementsCloneable = false;
			for (Class i : interfaces)
			    if (i.getName().equals("java.lang.Cloneable")) implementsCloneable = true;
			if (implementsCloneable)
			    System.out.println(type.getName() + " __" + fieldName + " = " + fieldName + ".clone();");
				
		    }
		}
	    }	    
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}

    }

}

class CloneableTest implements Cloneable
{
    protected Object clone()
    {
	return new CloneableTest();
    }
}