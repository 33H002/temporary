/**
 * Mac.java
 * 
 * @author SOOHEE
 * github.com/33H002
 * 
 */
package mac_test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Mac implements Variables {

	   MacFrame mf;
		  	 XY value[]; 			// x, y ��ǥ �� �� 
		  Robot robot = new Robot();
	PointerInfo pi;					// ���콺 ������ ��ġ
	
		  Color color; 
		  Color cc = new Color(0x7b68ee);

		    int  flag = NOT_READY;  // �Ҵ� �Ϸ� 
		    int sFlag = STOP;		// �۵� ��  
		    //int gFlag = STOP;
		    int eFlag = NO_ERROR;	// ���� �˻� 
	
	public Mac() throws AWTException{
				
		mf = new MacFrame();
		value = new XY[5];
		
		/**
		 * ready button
		 * 
		 * �Է¹��� ������ �Ҵ�
		 */
		mf.body[BTN].readyBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Object o = e.getSource();
            	if(o==mf.body[BTN].readyBtn && flag==NOT_READY) {
            		try {
            			// ��ǥ �� �Ҵ�
            			for(int num=0; num<4; num++) {
            				value[num]=new XY(Integer.parseInt(mf.body[num].x_tf.getText()), Integer.parseInt(mf.body[num].y_tf.getText()));
            			}
            			// ���� ��  �Ҵ�  
            			value[COLOR]=new XY(mf.body[COLOR].x_tf.getText());      		 
            			} catch(NumberFormatException nx) {
    					JOptionPane.showMessageDialog(mf, "���ڷ� �Է��ϼ���");
    					eFlag=ERROR;
    					return;
            			} catch(NullPointerException ne) {
            			JOptionPane.showMessageDialog(mf, "���� �Է��ϼ���");
            			eFlag=ERROR;
    					return;
            			}
            		
            		// �Ҵ� �Ϸ�
            		if(eFlag==NO_ERROR) {
            			flag = READY; 
            			
            			// ��ǥ �� ���� �Ұ�
	            		for(int num=0; num<5; num++) {
	            			mf.body[num].x_tf.setEditable(false);
	            			if(num!=4) mf.body[num].y_tf.setEditable(false); 
	            		}
	                	 mf.body[BTN].readyBtn.setBackground(Color.LIGHT_GRAY);
            		}
            		eFlag=NO_ERROR;
            	}	            			     	
            	
            	// ��ǥ �� ����
            	else if(o==mf.body[BTN].readyBtn && flag==READY) {
            		flag = NOT_READY;
            		
            		for(int num=0; num<5; num++) {
            			mf.body[num].x_tf.setEditable(true);
            			if(num!=4) mf.body[num].y_tf.setEditable(true); 
            		}	     				
	            	
	            	mf.body[BTN].readyBtn.setBackground(Color.WHITE);
            }}});
			
		/**
		 * start button
		 * 
		 * �Ҵ� �� ���� search
		 * search �Ϸ� �� �ش� ��ǥ�� ������ �̵� �� Ŭ��
		 */
		 mf.body[BTN].startBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Object o = e.getSource();
            	if(o==mf.body[BTN].startBtn && flag==READY) {
            		// �۵�
            		if(sFlag== STOP) {
            			sFlag=START;
            			mf.body[BTN].startBtn.setEnabled(false);
            			mf.body[BTN].startBtn.setBackground(Color.LIGHT_GRAY);
            			mf.body[BTN].startBtn.setText("STOP");
	            		
            			/**
            			 * start ���� ��ǥ
            			 *   end �� ��ǥ
            			 * space ����
            			 */
            			/* original code ****************************************************************
            			search: for(int y=value[START]._yV; y<value[END]._yV+value[SPACE]._yV ; y+=value[SPACE]._yV) {
        					//if(y>value[END]._yV) y=value[BEGIN].yV;
        					for(int x=value[START]._xV; x<=value[END]._xV; x+=value[SPACE]._xV) {
        						robot.mouseMove(x, y);
        						if(robot.getPixelColor(x, y).equals(cc)) {
        							pi = MouseInfo.getPointerInfo();
        							break search;
        						}
        					}
            			}
            			********************************************************************************* */
            			
            			/* init for test *************************************************************** */
	            		search: for(int y=284; y<=456 ; y+=13) {
	            					//if(y>=450) y=284;
	            					for(int x=340; x<=456; x+=13) {
	            						robot.mouseMove(x, y);
	            						if(robot.getPixelColor(x, y).equals(cc)) {
	            							pi = MouseInfo.getPointerInfo();
	            							break search;
	            						}
	            					}
	            			}
            			// ������ �̵� �� Ŭ��
	            		robot.mouseMove((int)pi.getLocation().getX(), (int)pi.getLocation().getY());
	                	leftClick();
            		}
            		// �۵� ����
            		else {
            			sFlag=STOP;
            			mf.body[BTN].readyBtn.setEnabled(true);
            			mf.body[BTN].startBtn.setBackground(Color.WHITE);
            			mf.body[BTN].startBtn.setText("START");
            		}
            	}
            	// �Ҵ� �̿�
            	else if(o==mf.body[BTN].startBtn && flag==NOT_READY) {
            		JOptionPane.showMessageDialog(mf, "Ready ��ư�� ��������");
            	}
            }});
		 
		/* ���콺 ������ �ǽð� ��ǥ ��� ************************************************* */
		while(flag==NOT_READY) { 
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			mf.mousePos.setText("X: "+ (int)pointerInfo.getLocation().getX() + " Y: " + (int)pointerInfo.getLocation().getY());
		}		
	}
		
	/* ���� Ŭ�� ************************************* */
	private void leftClick()	{
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
