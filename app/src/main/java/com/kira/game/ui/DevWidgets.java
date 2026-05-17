package com.kira.game.ui;

import javax.swing.*;
import java.awt.*;

import com.kira.game.ui.DebugUIBridge;

public class DevWidgets {
	
	
	private JFrame frame;
	private DebugUIBridge pBridge;
	
	public DevWidgets(DebugUIBridge pBridge) {
		
		this.pBridge =  pBridge;
		
		frame = new JFrame("dev gui");
		frame.setSize(300 , 300);
		frame.setLayout(new BoxLayout(frame.getContentPane() , BoxLayout.Y_AXIS));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		functions();
	}
	
	public void init() {	
	}
	int xPos = 0 , yPos = 0;
	private void functions() {
		
		
		
		
		JButton exit = new JButton("exit");
		exit.addActionListener( e -> {
			System.out.println("exiting...");
			System.exit(0);
		});
		
		
		JSlider xS = new JSlider(0 , 500 , 0);
		xS.addChangeListener( e -> {
			xPos = xS.getValue();
		});
		xS.setPaintTicks(true);
		xS.setMajorTickSpacing(50);
		xS.setPaintLabels(true);
		
		JSlider yS = new JSlider(0 , 500 , 0);
		yS.addChangeListener( e -> {
			yPos = xS.getValue();
		});
		yS.setPaintTicks(true);
		yS.setMajorTickSpacing(50);
		yS.setPaintLabels(true);
		
		JButton spawn = new JButton("spawn tonk");
		spawn.addActionListener( e -> {
			pBridge.spawn(1 , (float)xPos , (float)yPos);
		});
		
		frame.add(exit);
		frame.add(spawn);
		frame.add(Box.createVerticalStrut(10));
		frame.add(new JLabel("POSITION-X"));
		frame.add(xS);
		frame.add(new JLabel("POSITION-Y"));
		frame.add(yS);
	}
	
	public void render() {
		frame.setVisible(true);
	}
}