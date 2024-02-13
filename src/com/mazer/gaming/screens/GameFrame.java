package com.mazer.gaming.screens;
import javax.swing.JFrame;
import com.mazer.gaming.utils.GameConstants;
public class GameFrame  extends JFrame implements GameConstants{

/**
	 * @param
	 */
	private static final long serialVersionUID = 1L;
GameFrame()
	{
		setTitle(Title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GWIDTH,GHEIGHT);
		setLocationRelativeTo(null);
		Board board = new Board();
		add(board);
		setVisible(true);
		setResizable(false);
        board.requestFocusInWindow(); // Ensure the board has focus

	}
public static void main(String[] args) {
		
@SuppressWarnings("unused")
GameFrame frame = new GameFrame();
//SwingUtilities.invokeLater(() -> new GameFrame());
	}

}