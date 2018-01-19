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
		  	 XY value[]; 			// x, y 좌표 값 쌍 
		  Robot robot = new Robot();
	PointerInfo pi;					// 마우스 포인터 위치
	
		  Color color; 
		  Color cc = new Color(0x7b68ee);

		    int  flag = NOT_READY;  // 할당 완료 
		    int sFlag = STOP;		// 작동 중  
		    //int gFlag = STOP;
		    int eFlag = NO_ERROR;	// 에러 검사 
	
	public Mac() throws AWTException{
				
		mf = new MacFrame();
		value = new XY[5];
		
		/**
		 * ready button
		 * 
		 * 입력받은 값들을 할당
		 */
		mf.body[BTN].readyBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Object o = e.getSource();
            	if(o==mf.body[BTN].readyBtn && flag==NOT_READY) {
            		try {
            			// 좌표 값 할당
            			for(int num=0; num<4; num++) {
            				value[num]=new XY(Integer.parseInt(mf.body[num].x_tf.getText()), Integer.parseInt(mf.body[num].y_tf.getText()));
            			}
            			// 색상 값  할당  
            			value[COLOR]=new XY(mf.body[COLOR].x_tf.getText());      		 
            			} catch(NumberFormatException nx) {
    					JOptionPane.showMessageDialog(mf, "숫자로 입력하세요");
    					eFlag=ERROR;
    					return;
            			} catch(NullPointerException ne) {
            			JOptionPane.showMessageDialog(mf, "값을 입력하세요");
            			eFlag=ERROR;
    					return;
            			}
            		
            		// 할당 완료
            		if(eFlag==NO_ERROR) {
            			flag = READY; 
            			
            			// 좌표 값 수정 불가
	            		for(int num=0; num<5; num++) {
	            			mf.body[num].x_tf.setEditable(false);
	            			if(num!=4) mf.body[num].y_tf.setEditable(false); 
	            		}
	                	 mf.body[BTN].readyBtn.setBackground(Color.LIGHT_GRAY);
            		}
            		eFlag=NO_ERROR;
            	}	            			     	
            	
            	// 좌표 값 수정
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
		 * 할당 값 토대로 search
		 * search 완료 시 해당 좌표로 포인터 이동 후 클릭
		 */
		 mf.body[BTN].startBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Object o = e.getSource();
            	if(o==mf.body[BTN].startBtn && flag==READY) {
            		// 작동
            		if(sFlag== STOP) {
            			sFlag=START;
            			mf.body[BTN].startBtn.setEnabled(false);
            			mf.body[BTN].startBtn.setBackground(Color.LIGHT_GRAY);
            			mf.body[BTN].startBtn.setText("STOP");
	            		
            			/**
            			 * start 시작 좌표
            			 *   end 끝 좌표
            			 * space 간격
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
            			// 포인터 이동 후 클릭
	            		robot.mouseMove((int)pi.getLocation().getX(), (int)pi.getLocation().getY());
	                	leftClick();
            		}
            		// 작동 중지
            		else {
            			sFlag=STOP;
            			mf.body[BTN].readyBtn.setEnabled(true);
            			mf.body[BTN].startBtn.setBackground(Color.WHITE);
            			mf.body[BTN].startBtn.setText("START");
            		}
            	}
            	// 할당 미완
            	else if(o==mf.body[BTN].startBtn && flag==NOT_READY) {
            		JOptionPane.showMessageDialog(mf, "Ready 버튼을 누르세요");
            	}
            }});
		 
		/* 마우스 포인터 실시간 좌표 출력 ************************************************* */
		while(flag==NOT_READY) { 
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			mf.mousePos.setText("X: "+ (int)pointerInfo.getLocation().getX() + " Y: " + (int)pointerInfo.getLocation().getY());
		}		
	}
		
	/* 왼쪽 클릭 ************************************* */
	private void leftClick()	{
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
