package com.chainsys.carsale.logger;

public class Logger {
	public static Logger getInstance()
	{
		Logger log=new Logger();
		return log;
	}
	public void getInput(Object message)
	{
		System.out.println(message);
	}
	
	public void error(Object message)
	{
		System.err.println(message);
	}
	public void error(Exception e)
	{
		System.err.println(e.getMessage());
	
	}
	public void info(Object message)

	{
		System.out.println(message);
	}
	

}
