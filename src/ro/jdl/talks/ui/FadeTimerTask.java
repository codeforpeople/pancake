package ro.jdl.talks.ui;

import java.util.TimerTask;

public class FadeTimerTask extends TimerTask{

	private TitleScreen _frame;
	private boolean _fadeIn;
	
	public FadeTimerTask(TitleScreen frame, boolean fadeDirection) {
		super();
		
		this._frame = frame;
		this._fadeIn = fadeDirection;
	}
	
	@Override
	public void run() {
		
		float opacity = _frame.getOpacity();
		
		if (_fadeIn) {
			opacity += 0.01f;
			if (opacity > 1) opacity = 1.0f;
		} else {
			opacity -= 0.01f;
			if (opacity < 0) opacity = 0.0f;
		}
		
		_frame.setOpacity(opacity);
		if (opacity == 0.0f) {
			_frame.setVisible(false);
			_frame.setVisibility(false);
			_frame.stopTimer();
		}
		
		if (opacity == 1.0f) {
			_frame.setVisibility(true);
			_frame.stopTimer();
		}
	}

}
