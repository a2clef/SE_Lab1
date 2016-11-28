package lab_1;

public class ExprSimplify {
	public static String simplify(Expression expr, String command){
		if (isLegitSimplifyCommand(command))
		{
			return expr.simplify(command);
		} else {
			return "This is not a good expression, please retry.";
		}		
	}
	
	private static Boolean isLegitSimplifyCommand(String s)
	{
		for (int i=0;i<s.length();i++)		//if contains illegal character
			if ("!0123456789-=. abcdefghijklmnopqrstuvwxyz".indexOf(s.charAt(i)) == -1) 
				return false;
		if (!s.startsWith("!simplify")) return false;
		return true;
	}
}
