package lab_1;

import java.util.Scanner;

public class Calculator {
	public static void main(String args[])
	{
		Expression expr = new Expression();
		String	inputString;
		
		System.out.println("Expression Calculator.");
		System.out.println("Please input an legitimate expression first.");
		
		
		Scanner inputSource	=	new Scanner(System.in);
		
		while(true)
		{
			System.out.print(">>");
			inputString = inputSource.nextLine();
			
			if (inputString.equals("exit"))				//exit the program
				break;
			
			if (inputString.length()==0) continue;	//continue the loop if the input is empty
			
			if (!expr.haveExpression())					//first run, without an expression
			{
				System.out.println(ExprInitialization.init(expr,inputString));
			} else
			{
				if (inputString.contains("!"))	//possible command
				{
					if (inputString.startsWith("!simplify"))
						System.out.println(ExprSimplify.simplify(expr, inputString));
					else if (inputString.startsWith("!d"))
						System.out.println(ExprDerivate.derivate(expr, inputString));
					else System.out.println("This command doesn't exist, please check.");
				} else
				{
					System.out.println(ExprInitialization.init(expr,inputString));
				}
					
			};
		}
		//post run process
		inputSource.close();
	}
}
