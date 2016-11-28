package lab_1;

public class Expression {
	
	final static int	maxItemCount	=	1000;
	final static int	maxVarCount		=	30;
	final static double	minDouble		=	0.0000001;
	final static Boolean isDebugging	=	false;
	
	private Boolean haveExpression	=	false;
	private int itemCount = 0;
	private int expressionArray[][];
	private double coefficientArray[]; 
	
	private static int charIndex(char c)
	{
		return (int)(c - 'a');
	}
	
	private static char indexChar(int i)
	{
		return (char)('a'+i);
	}
	
	private static Boolean isZero(double d)
	{
		if (Math.abs(d)<minDouble)
			return true;
		else
			return false;
	}
	
	public String parseResult(int[][] exp, double[] co)
	{
		//combine same items, if possible
		String result = "";
			for (int i=0;i<itemCount-1;i++)
				{
					if (isZero(co[i]))			//skip the empty term
						continue;
					for  (int j=i+1;j<itemCount;j++)
					{
						Boolean sameFlag=true;
						for (int k=0;k<maxVarCount;k++)			//check if they are similar items
							if (exp[i][k]!=exp[j][k])
							{
								sameFlag=false;
								break;
							};
						if (sameFlag)							//combine the items and clean the latter one
						{
							co[i]+=co[j];
							co[j]=0;
							for (int k=0;k<maxVarCount;k++)
								exp[j][k]=0;
						}
					}
				};
				
				
				//do something, output the expression to see the result
				
				Boolean isFirstFlag=true;
				for (int i=0;i<itemCount;i++)
					if (!isZero(co[i]))
					{
						if(!isFirstFlag)
						{
							if (co[i]>0){
								result+="+";
							}
						} else isFirstFlag=false;
						//if (coefficientArray[i]<0)
						//	System.out.print("-");
						result+=co[i];
						
						
						for (int j=0;j<maxVarCount;j++)
							if (exp[i][j]!=0)
							{
								if (exp[i][j] == 1)	
								{
									result+=indexChar(j);
								} else 
								{
									result+=indexChar(j);
									result+="^";
									result+=exp[i][j];
								}
							}
					}
				return result;
	}	
	
	private String parseResult()
	{
		//combine same items, if possible
		String result = "";
			for (int i=0;i<itemCount-1;i++)
				{
					if (isZero(coefficientArray[i]))			//skip the empty term
						continue;
					for  (int j=i+1;j<itemCount;j++)
					{
						Boolean sameFlag=true;
						for (int k=0;k<maxVarCount;k++)			//check if they are similar items
							if (expressionArray[i][k]!=expressionArray[j][k])
							{
								sameFlag=false;
								break;
							};
						if (sameFlag)							//combine the items and clean the latter one
						{
							coefficientArray[i]+=coefficientArray[j];
							coefficientArray[j]=0;
							for (int k=0;k<maxVarCount;k++)
								expressionArray[j][k]=0;
						}
					}
				};
				
				
				//do something, output the expression to see the result
				
				Boolean isFirstFlag=true;
				for (int i=0;i<itemCount;i++)
					if (!isZero(coefficientArray[i]))
					{
						if(!isFirstFlag)
						{
							if (coefficientArray[i]>0){
								result+="+";
							}
						} else isFirstFlag=false;
						//if (coefficientArray[i]<0)
						//	System.out.print("-");
						result+=coefficientArray[i];
						
						
						for (int j=0;j<maxVarCount;j++)
							if (expressionArray[i][j]!=0)
							{
								if (expressionArray[i][j] == 1)	
								{
									result+=indexChar(j);
								} else 
								{
									result+=indexChar(j);
									result+="^";
									result+=expressionArray[i][j];
								}
							}
					}
				return result;
	}		

	public String initialize(String s)
	{
		s = s.replace(" ", "");			//delete all spaces
		s = s.replace("-","+@");		//use @ to represent minus
		s = s.replace("*","");			//remove all *
		if (s.charAt(0)=='+')
			s=s.substring(1);
		
		if (isDebugging) System.out.println("DBG:replaced useless chars:"+s);
		
		String items[] = s.split("\\+");
		itemCount = items.length;
		
		if (isDebugging)
		{
			System.out.println("DBG:splitted tokens:");
			for (int i=0;i<itemCount;i++)
				System.out.println(items[i]+"#");
		}
		
		//data structure initializing
		expressionArray 	= new int[itemCount][maxVarCount];
		coefficientArray	= new double[itemCount];
		
		
		
		//parse each token into the array
		for (int i=0;i<itemCount;i++)
		{
			if (isDebugging) System.out.println("DBG:Processing token "+i);
			
			coefficientArray[i]=1;
			StringBuffer token = new StringBuffer(items[i]);
			StringBuffer cache = new StringBuffer();
			while (token.length() != 0)
			{
				//if (isDebugging) System.out.println(" DBG:another sub-token:"+token);
				cache.setLength(0);
				if (".0123456789@".indexOf(token.charAt(0)) != -1) //parse into number
				{
					while (".0123456789@".indexOf(token.charAt(0)) != -1)
					{
						cache.append(token.charAt(0));
						token.deleteCharAt(0);
						if (token.length()==0) break;
					};
					if (cache.charAt(0) == '@')
						cache.setCharAt(0, '-');
					if (isDebugging) System.out.println(" DBG:sub-token:"+cache );
					coefficientArray[i] *= Double.parseDouble(cache.toString());
						
					
				} else	if("abcdefghijklmnopqrstuvwxyz".indexOf(token.charAt(0)) != -1)//parse into variable
				{
					if (token.length()>1) //possible power
					{
						if (token.charAt(1)=='^')
						{
							int varTemp = charIndex(token.charAt(0));
							if (isDebugging) System.out.print(" DBG:sub-token:"+token.substring(0, 2) );
							token.deleteCharAt(0);
							token.deleteCharAt(0);
							
							if (token.length()==0)
							{
								haveExpression=false;
								return "Empty after ^symbol, please check";
							}
							
							if ("0123456789".indexOf(token.charAt(0)) == -1) //deal with the case ^ followed by not a number
							{
								haveExpression=false;
								return "Illegal Character after ^symbol, please check";
							};
							
							while ("0123456789".indexOf(token.charAt(0)) != -1)
							{
								cache.append(token.charAt(0));
								token.deleteCharAt(0);
								if (token.length()==0) break;
							};
							if (isDebugging) System.out.println(cache );
							expressionArray[i][varTemp]+= Integer.parseInt(cache.toString());
						} else
						{
							expressionArray[i][charIndex(token.charAt(0))]++;
							if (isDebugging) System.out.println(" DBG:sub-token:"+ token.charAt(0) );
							token.deleteCharAt(0);
						}
						
					} else		//there's only one variable in the expression
					{
						expressionArray[i][charIndex(token.charAt(0))]++;
						if (isDebugging) System.out.println(" DBG:sub-token:"+ token.charAt(0) );
						token.deleteCharAt(0);
					}
				} else
				{
					//Illegal character detected, parse failed
					haveExpression=false;
					return "Illegal Character Detected, please retry";
				};
				
			}
		}
		//combine same items, if possible
		haveExpression = true;
		return parseResult();
	}

	public String simplify(String s)
	{
		//copy the expression for simplification
		int[][]		tempExArray = new int[expressionArray.length][];
		double[]	tempCoArray = coefficientArray.clone();
		for (int i=0;i<expressionArray.length;i++)
			tempExArray[i]=expressionArray[i].clone();
		//parse the command and do the simplification
		s=s.substring(9);
		while (s.startsWith(" ")) s=s.substring(1);
		while (s.endsWith(" ")) s=s.substring(0, s.length()-1);
		while (s.contains("  ")) s.replace("  ", " ");
		
		//deals with empty commands here:
		if (s.length()==0)
		{
			return parseResult();
		}
		
		String[]	detVariables	= s.split(" "); 
		String[][]	assignments		= new String[detVariables.length][];
		
		Boolean parseError = false;
		for (int i=0;i<detVariables.length;i++)
		{
			double value;
		
			assignments[i]=detVariables[i].split("=");
			if (assignments[i].length!=2) parseError=true;
			if ((assignments[i][0].length()!=1) ||( !"abcdefghijklmnopqrstuvwxyz".contains(assignments[i][0])))
			{
				parseError=true;
				System.out.println("Illegal Variable for assignment");
				return "illegal variable";
			};
			
			try
			{
				value=Double.parseDouble(assignments[i][1]);
			} catch (NumberFormatException e)
			{
				System.out.println("Illegal assignment: Not a number.");
				parseError=true;
				return "illegal assignment";
			};
			
			if (parseError) break;
				
		}
		if(parseError)
		{
			System.out.println("Illegal Simplification Command.Please check.");
			return "illegal simplification string";
		}
		
		for (int i=0;i<detVariables.length-1;i++)
			for (int j=i+1;j<detVariables.length;j++)
				if (assignments[i][0].equals(assignments[j][0]))
				{
					System.out.println("Duplicated Assignment, please check.");
					return "duplicated assignment";
				};
		
		for (int i=0;i<detVariables.length;i++)
		{
			Boolean existVariable = false;
			for (int j=0;j<itemCount;j++)
				if (!isZero(tempCoArray[j]))
					if (tempExArray[j][charIndex(assignments[i][0].charAt(0))]!=0)
					{
						existVariable = true;
						tempCoArray[j]*=Math.pow(Double.parseDouble(assignments[i][1]), tempExArray[j][charIndex(assignments[i][0].charAt(0))]);
						tempExArray[j][charIndex(assignments[i][0].charAt(0))]=0;
					}
			if (!existVariable)
			{
				System.out.println("Assignment to non exist variable, please check.");
				return "variable does not exist";
			}
		};
				
			
		return parseResult(tempExArray,tempCoArray);
	}

	public String derivate(char var)
	{
		//copy the expression for simplification
		int[][]		tempExArray = new int[expressionArray.length][];
		double[]	tempCoArray = coefficientArray.clone();
		for (int i=0;i<expressionArray.length;i++)
			tempExArray[i]=expressionArray[i].clone();		
		//do the derivation
		Boolean varExist=false;
		for (int i=0;i<itemCount;i++)
			if (!isZero(tempCoArray[i]))
				if (tempExArray[i][charIndex(var)]==0) //this var does not exist in this item, delete it
				{
					tempCoArray[i]=0;
					//TODO: may be clean the table here or not
				} else //this var exist in this item
				{
					tempCoArray[i]*=tempExArray[i][charIndex(var)];
					tempExArray[i][charIndex(var)]--;
					varExist=true;
				};
		if(!varExist)
		{
			System.out.println("Variable does not exist, please check.");
			return "variable does not exist";
		}
		//output the expression processed
		//outputExpression(tempExArray,tempCoArray);
		return parseResult(tempExArray,tempCoArray);
	}
	
	public boolean haveExpression(){
		return haveExpression;
	}
	
	
}
