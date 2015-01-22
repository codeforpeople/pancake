package ro.jdl.talks.ui;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ControlPanel {

	private JFrame frame;
	private JComboBox<String> displayListBox;
	private GraphicsEnvironment g;
	private GraphicsDevice[] devices;
	private TitleScreen _titleScreen = null;
	private boolean _visible = false;

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public ControlPanel() throws InterruptedException {
		initialise();
	}
	
	public void showWindow() {
		frame.setVisible(true);
	}

	/**
	 * Initialise the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialise() throws InterruptedException {
		frame = new JFrame();
		frame.setTitle("Pancake Presentation Tool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(500, 300);
		frame.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 250, dim.height / 2 - 150);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblSelectDisplay = new JLabel("Select display:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectDisplay, 24, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSelectDisplay, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblSelectDisplay);
		
		displayListBox = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.NORTH, displayListBox, 24, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, displayListBox, 6, SpringLayout.EAST, lblSelectDisplay);
		springLayout.putConstraint(SpringLayout.EAST, displayListBox, 391, SpringLayout.EAST, lblSelectDisplay);
		frame.getContentPane().add(displayListBox);
		
		JButton btnNewButton = new JButton("Toggle title screen [OFF]");
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, displayListBox);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_titleScreen == null) {
					_titleScreen = new TitleScreen(devices[displayListBox.getSelectedIndex()]);
				}
				
				_titleScreen.toggleScreen();
				_visible = !_visible;
				((JButton) e.getSource()).setText("Toggle Title screen " + ((_visible) ? "[ON]" : "[OFF]"));
			}
		});
		
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, displayListBox);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblSelectDisplay);
		frame.getContentPane().add(btnNewButton);
		
		detectDisplays();
		runServer();
	}

	private void runServer() throws InterruptedException {
		
//		try {
//			Process p = Runtime.getRuntime().exec("node C:\\Users\\Alexandru\\Documents\\GitHub\\test\\server\\bin\\www");
//			p.waitFor();
//			System.out.println("Gata pula!");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void detectDisplays() {
		g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		devices = g.getScreenDevices();
		
		for (int i = 0; i < devices.length; i++) {
			displayListBox.addItem(devices[i].getIDstring());
		}
	}
}
