import java.awt.Color;

import javax.swing.JLabel;

public class ScoreLabel extends JLabel{
	public ScoreLabel() {
		super("000000");
		this.setBackground(Color.white);
	}
	
	private Integer score = 0;
	public void addToScore() {
		score += 100;
		StringBuilder finalScore = new StringBuilder(score);
		for(int i = finalScore.length();i<7;i++) {
			finalScore.append("0");
		}
		this.setText(finalScore.toString());
	}
}
