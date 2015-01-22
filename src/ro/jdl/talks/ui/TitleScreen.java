package ro.jdl.talks.ui;

import java.awt.GraphicsDevice;
import java.util.Timer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JFrame;


public class TitleScreen extends JFrame {

	/**
	 * Generated version ID
	 */
	private static final long serialVersionUID = -5218742759818222894L;
	private GraphicsDevice _parentDevice;
	private boolean _isVisible = false;
	private Timer _t = null;
	private JFXPanel _fxPanel = new JFXPanel();

	/**
	 * Create the frame.
	 */
	public TitleScreen(GraphicsDevice parentDevice) {
		
		super(parentDevice.getDefaultConfiguration());
		this._parentDevice = parentDevice;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		
		this.setSize(_parentDevice.getDisplayMode().getWidth(), _parentDevice.getDisplayMode().getHeight());	
		_fxPanel.setSize(_parentDevice.getDisplayMode().getWidth(), _parentDevice.getDisplayMode().getHeight());
		
		this.add(_fxPanel);
		
		Platform.runLater(new Runnable() { // this will run initFX as JavaFX-Thread
            @Override
            public void run() {
                initFX(_fxPanel);
            }
        });
	}

	public void toggleScreen() {
		
		_t = new Timer();
		
		if (!_isVisible) {
			this.setOpacity(0.0f);
			this.setVisible(true);
			_t.scheduleAtFixedRate(new FadeTimerTask(this, true), 1, 5);
			
		} else {
			_t.scheduleAtFixedRate(new FadeTimerTask(this, false), 1, 5);
		}
	}
	
	public void setVisibility(boolean v) {
		this._isVisible = v;
	}
	
	public void stopTimer() {
		this._t.cancel();
	}
	
	private static void initFX(JFXPanel fxPanel) {
		Group group = new Group();
		Scene scene = new Scene(group);
	    fxPanel.setScene(scene);

	    WebView webView = new WebView();

	    group.getChildren().add(webView);
	    webView.setMinSize(fxPanel.getWidth(), fxPanel.getHeight());
	    webView.setMaxSize(fxPanel.getWidth(), fxPanel.getHeight());

	    // Obtain the webEngine to navigate
	    WebEngine webEngine = webView.getEngine();
	    webEngine.load("http://localhost:3000");
	}
}
