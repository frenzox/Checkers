package br.pucpr;

import javax.swing.JButton;

public class JButtonCheckers extends JButton
{
	int row, col;

	public JButtonCheckers( int row, int col )
	{
		this.row = row;
		this.col = col;
	}
}
