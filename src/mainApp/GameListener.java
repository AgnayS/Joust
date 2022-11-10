package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * GameListener continuously updates the Game component
 * @author jonescm
 *
 */
public class GameListener implements ActionListener {

	private GameComponent gameComponent;
	
	public GameListener(GameComponent gameComponent) {
		this.gameComponent = gameComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.gameComponent.updateGame();
		this.gameComponent.drawScreen();;
	}

}

