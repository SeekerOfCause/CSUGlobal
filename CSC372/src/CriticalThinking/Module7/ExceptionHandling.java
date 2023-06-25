package CriticalThinking.Module7;

import java.util.*;

public class ExceptionHandling {
	public static void main(String[] args) {
		try
		{
		// Statements to be monitored for exceptions
			System.out.println("I am here!");
		}
		
		catch(Exception ex)
		{
		//Catching the exceptions here
		}
		finally
		{
		// This block is always executed
		}
	}
}