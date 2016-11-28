package lab_1;

public class ExprInitialization {
	public static String init(Expression expr, String command){
		if (isLegitExpression(command))
		{
			return expr.initialize(command);
		} else {
			return "This is not a good expression, please retry.";
		}
	}
	
	private static Boolean isLegitExpression(String s)
	{
		Boolean illegalExpression=false;
		//add rules for illegal expression
		for (int i=0;i<s.length();i++)		//if contains illegal character
			if ("0123456789-+*^. abcdefghijklmnopqrstuvwxyz".indexOf(s.charAt(i)) == -1) 
			{
				illegalExpression=true;
				break;
			};
		
		
		
		if (illegalExpression) return false;
		return true;
	}
}
