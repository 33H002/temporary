/**
 * Main.java
 * 
 * @author SOOHEE
 * github.com/33H002
 * 
 */
import java.awt.*;

public class Main {
		
		 Robot 	robot = new Robot();
		
	public Main() throws AWTException {
		go();
	}
	
	 private void leftClick()	{
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.delay(200);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    robot.delay(200);
	 }
	  
	 /* 숫자 입력 ************************************* */
	 private void type(int i)	{
		robot.keyPress(i);
		robot.keyRelease(i);
	}	  
		/* 문자 입력 ************************************* */
	 private void type(String s)	{
		byte[] bytes = s.getBytes();
		for (byte b : bytes)	{
			 int code = b;
			 if (code > 96 && code < 123) code = code - 32;
			    robot.keyPress(code);
			    robot.keyRelease(code);
		}
	 }
	  
	 public static void main(String[] args) throws AWTException {
		new Main();
	}
		
}
