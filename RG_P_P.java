 /**
 * 2017학년도 1학기
 * 객체지향프로그래밍(001)
 * 윤용익 교수님
 * 
 * TERM project 8
 * package rhythmGame
 *
 * @author SOOHEE
 * github.com/33H002
 * 
 * Main.java
 */
package rhythmGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import main_package.*;

public class RG_P_P extends JPanel implements Variable {
	RG_MainPanel mp;
	
	public RG_P_P() {
		super();
		setBackground(Color.WHITE);
		
		mp = new RG_MainPanel();
		mp.setPreferredSize(new Dimension(GAME_PANEL_WIDTH-10, GAME_PANEL_HEIGHT-10));
		add(mp);
		
		/**
		 * Key listener
		 */
		mp.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				for (int j = 0; j < Variable.keyNoteCode.length; j++) {
					if(e.getKeyCode() == Variable.keyNoteCode[j]){	
						mp.keyRelease(j);
						return;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_F2){
					mp.startGame();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				for (int j = 0; j < Variable.keyNoteCode.length; j++) {
					if(e.getKeyCode() == Variable.keyNoteCode[j]){	
						mp.keyPress(j);
						return;
					}
				}	
			}});
		mp.setFocusable(true);
	}
	/* Keyboard Sound
	public void playSound(String file_url) {
		 try{       	
		  InputStream in = MainFrame.class.getResourceAsStream(file_url);
		  AudioStream as = new AudioStream(in);
		    AudioData ad = as.getData();
		  AudioDataStream ads = new AudioDataStream(ad);
		  AudioPlayer.player.start(as);
		 	} catch(Exception e){ 
			  e.printStackTrace();
			  System.out.println("sound exception");	
		 	}
		}*/
}