package lab_1;

public class ExprDerivate {
	public static String derivate(Expression expr, String command){
		if (isLegitDerivationCommand(command))
		{
			return expr.derivate(command.charAt(5));
		} else {
			return "This is not a good expression, please retry.";
		}				
	}
	
	private static Boolean isLegitDerivationCommand(String s)
	{
		if (s.length()!=6) return false;
		if (!s.startsWith("!d/d ")) return false;
		if ("abcdefghijklmnopqrstuvwxyz".indexOf(s.charAt(5))==-1) return false;
		return true;
	}	
	
}
