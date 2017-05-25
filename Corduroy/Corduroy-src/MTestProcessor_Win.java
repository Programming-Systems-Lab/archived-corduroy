import java.util.*;
import java.io.*;

/**
 * To do list:
 * 1. Allow for keywords like "shuffle", "in", "reverse", "negate"....
 * 2. Let the JML "\result" be equal to the original (unmodified) function call
 *
 * 3. If you process a file, then process it again, the "extends" stuff gets messed up.
 * 4. Allow for the use of double-equals to compare objects (change to .equals)
 * 5. Allow for the use of double-equals to compare floats (change to some sort of tolerance check)
 *
 */


public class MTestProcessor_Win
{
    private PrintWriter out = null, testOut = null;
    private Scanner in = null;

    public static void main(String[] args)
    {
	if (args.length < 1)
	{
	    System.out.println("Please specify a file");
	    System.exit(0);
	}

	MTestProcessor_Win p = new MTestProcessor_Win();
	p.scan(args[0]);
    }

    public void scan(String origFile)
    {
	// keeps track of what level of curly braces we're at, 0 meaning outside the class
	int level = 0;

	// indicates whether we found a rule, and thus we need the corresponding method name
	boolean ruleFound = false;

	// holds all the rules we found
	ArrayList<String> preRules = new ArrayList<String>();
        ArrayList<String> postRules = new ArrayList<String>();

	// whether or not we should be capturing the method name
	boolean captureName = false;

	// whether or not we're supposed to add our test method
	boolean addTestMethod = false;

	// the name of the method we're creating a test for
	String methodName = "";
        
        // flag for recognization of metamorphic "pre-" and "post-" annotations for current method. 0 for none, 1 for post, 2 for post after pre
        int annotationFlag =0;

	try
	{
	    String backup = origFile + ".bak";

	    // make a copy of the original
	    Runtime.getRuntime().exec("cmd /C copy " + origFile + " " + backup);
            

	    System.out.println("scanning the backed-up file");

	    // to read the file
	    in = new Scanner(new File(backup));

	    // each line that we read
	    String line = "";

	    // to keep track of the name of the new class we're creating to hold the tests
	    String testFileName = "MTest.temp";

	    // to rewrite the original file
	    out = new PrintWriter(new File(origFile));

	    // to write the new file with the tests in it
	    testOut = new PrintWriter(new File(testFileName));
	    
	    // the name of the test class
	    String testClassName = "";

	    // the name of the original class
	    String className = "";

	    while (in.hasNext())
	    {
		// read the next line of the file in its entirety
		line = in.nextLine();

		
		if (level == 0 && (line.contains("import") || line.contains("package")))
		{
		    out.println(line);
		    testOut.println(line);
		}

		// see what class this extends, if any
		if (level == 0 && line.contains("class"))
		{
		    //System.out.println("The class is " + line);

		    // this part reads the line containing the class declaration and breaks it apart

		    String permission = line.split("class")[0].trim();
		    //System.out.println("Permission " + permission);

		    String classPart = line.split("class")[1].trim();
		    //System.out.println("ClassPart " + classPart);

		    className = classPart.split(" ")[0].trim();
		    //System.out.println("ClassName " + className);
		    
		    String superClass = null;
		    if (classPart.contains("extends"))
		    {
			superClass = classPart.split("extends")[1].trim().split(" ")[0].trim();
			//System.out.println("SuperClass " + superClass);
		    }

		    String interfaces = null;
		    if (classPart.contains("implements"))
		    {
			interfaces = classPart.split("implements")[1].trim();
			//System.out.println("Interfaces " + interfaces);
		    }


		    // this part puts together the new class declarations 

		    testClassName = "__" + className;
		    //System.out.println("testClassName " + testClassName);

		    String newClassLine = permission + " class " + testClassName;
		    if (superClass != null) newClassLine += " extends " + superClass;
		    if (interfaces != null) newClassLine += " implements " + interfaces;

		    // write the modified class line to the original file
		    System.out.println("newClassLine " + newClassLine);
		    out.println(newClassLine);

		    // now create the class line in the file containing the test
		    String testClassLine = permission + " class " + className + " extends " + testClassName;

		    System.out.println("testClassLine " + testClassLine);
		    testOut.println(testClassLine + " {");
		    
		}
		else
		{
		    // write the line to the file
		    System.out.println(line);
                    if (line.trim().startsWith("@post-meta"))
                    {
                        annotationFlag = 1;
                    }
                    else if (line.trim().startsWith("@pre-meta"))
                    {
                        annotationFlag = 2;
                    }
                    else if (line.trim().endsWith("*/"))
                    {
                        
                    }
                    else if (captureName)
                    {
                        methodName = getMethodName(line);
                        if ( annotationFlag == 1 )
                        {
                            out.println("* @ensures "+className+".testPost"+methodName.substring(0,1).toUpperCase() + methodName.substring(1, methodName.length())+" == true");
                            out.println("*/");
                        }
                        else if (annotationFlag == 2)
                        {
                            out.println("* @ensures "+className+".testPost"+methodName.substring(0,1).toUpperCase() + methodName.substring(1, methodName.length())+" == true");
                            out.println("* @assumes "+className+".testPre"+methodName.substring(0,1).toUpperCase() + methodName.substring(1, methodName.length())+" == true");
                            out.println("*/");
                        }
                        out.println(line);
                    }
                    else
                    {
                        out.println(line);
                    }
		}

		
	     		
		// trim it
		line = line.trim();


		// see what level of curly braces we're at
		if (line.contains("{")) level++;
		else if (line.contains("}")) level--;



		// we only care about such things at the method level
		if (level == 1 || line.startsWith("@"))
		{
                    // please start metamorphic annotation with "@ post-meta" or "@ pre-meta", white space between the keyword and the @ cannot be omitted
		    if (line.startsWith("@pre-meta") )
		    {
			// we found a pre-meta rule!!!
			line = line.split("@pre-meta")[1].trim();
			preRules.add(line);
			//System.out.println("Rule " + line);
			ruleFound = true;
		    }
                    else if (line.startsWith("@post-meta"))
                    {
                        // we found a post-meta rule!!!
			line = line.split("@post-meta")[1].trim();
			postRules.add(line);
			//System.out.println("Rule " + line);
			ruleFound = true;
                    }
		    else if (ruleFound && line.endsWith("*/"))
		    {
			// this means we are at the end of the comment and
			// we need to get ready for the method name
			captureName = true;
			ruleFound = false;
		    }
		    else if (captureName)
		    {
			//System.out.println("The method is " + line);
			methodName = getMethodName(line);
			captureName = false;
			addTestMethod = true;
		    }
		    else if (addTestMethod)
		    {
			addTestMethod = false;
                        if (postRules.isEmpty()==false)
                            createTestMethod(postRules, methodName,true);
                        if (preRules.isEmpty()==false)
                            createTestMethod(preRules, methodName,false);
			preRules.clear();
                        postRules.clear();
		    }			
		}

	    }
	    //System.out.println("Done reading " + file);

	    // add a final curly brace to the test class
	    testOut.println("\n}");

	    // rename the file containing the test
	    Runtime.getRuntime().exec("cmd /C move " + className + ".java " + testClassName + ".java");
            
            // add a final curly brace to the test class
	    System.out.println("swapping the orginal file with the test file");
	    Runtime.getRuntime().exec("cmd /C move MTest.temp " + className + ".java"); 

	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    try { out.flush(); } catch (Exception e) { }
	    try { out.close(); } catch (Exception e) { }
	    try { testOut.flush(); } catch (Exception e) { }
	    try { testOut.close(); } catch (Exception e) { }
	}

	

    }

    private String getMethodName(String line)
    {
	// get rid of any words that could be there
	line = line.replace("public", "");
	line = line.replace("private", "");
	line = line.replace("protected", "");
	line = line.replace("static", "");
	line = line.trim();
	Scanner reader = new Scanner(line);
	String retType = reader.next();
	String methodName = reader.nextLine().trim();
	//System.out.println(methodName);
        return methodName;
    }

    private void createTestMethod(ArrayList<String> rules, String methodName, boolean isPost) throws Exception
    {
        if (isPost)
        {
            String testMethodName = "testPost" + methodName.substring(0,1).toUpperCase() + methodName.substring(1, methodName.length());
            testOut.println("public boolean " + testMethodName + " {");
        }
        else
        {
            String testMethodName = "testPre" + methodName.substring(0,1).toUpperCase() + methodName.substring(1, methodName.length());
            testOut.println("public boolean " + testMethodName + " {");
        }

	

	//for (String rule : rules) System.out.println(rule);


	for (String rule : rules)
	{
            
            // parse the rule to check for any keyword usage
            rule = parseRule(rule, methodName);
            
	    if (rule.contains("if") || rule.contains("}") || rule.contains("{")) 
                testOut.println(rule);
	    else testOut.println("   if (!(" + rule + ")) return false;");
	}

	testOut.println("return true;");
	testOut.println("}");

    }
    
    private static String parseRule(String rule, String methodName)
    {
        // detect keyword "/result" and replace it with the return value of method, prohibited to use on methods with void return
        rule = rule.replace("\\result", funcall(methodName));
        
        System.out.println("replacing "+rule);
        

        // grammar: shuffle(param), param can be any List, array of primitive types of objects
        rule = rule.replace("shuffle", "RuleProcessor.shuffle");
        

        // grammar: reverse(param) where param is any List or array
        rule = rule.replace("reverse", "RuleProcessor.reverse");
        

        // grammar: negate(param) where param is an array of numeric primitive types
        rule = rule.replace("negate", "RuleProcessor.negate");
        

        // grammar: valueIn(param1, param2) where param1 is an Object or value in primitive types, param2 is an array of objects or variables of the same type of param1
        rule = rule.replace("valueIn", "RuleProcessor.valueIn");
        
        
        // grammar: inRange(param1, param2, param3), return true if param2 <= param1 <= param3, 3 parameters must be the same type => either Comparable objects or numeric primitive types
        rule = rule.replace("inRange", "RuleProcessor.inRange");

        
        // grammar: approximatelyEqualTo(value1, value2, offset), return true if the difference between value1 and value2 is less than the offset, values compared musst be numeric
        rule = rule.replace("approximatelyEqualTo", "RuleProcessor.approximatelyEqualTo");
        
        return rule;
    }
    
    private static String funcall(String methodName)
    {

        String name = methodName.split("\\(")[0].trim()+"(";
        
        String arguments = methodName.split("\\(")[1].trim();
        if (arguments.contains(","))
        {
            String[] args = arguments.split(",");
            for (int i=0; i<args.length-1; i++)
            {
                String[] tokens = args[i].trim().split(" ");
                String actualArg = tokens[tokens.length-1];
                name = name + actualArg+",";
            }
            String[] lastTokens = args[args.length-1].trim().split(" ");
            String lastArg = lastTokens[lastTokens.length-1];
            name = name + lastArg;
        }
        else
        {
            String[] tokens = arguments.trim().split(" ");
                String actualArg = tokens[tokens.length-1];
                name = name + actualArg;
        }
        return name;
    }
    
    
    
    
    




}