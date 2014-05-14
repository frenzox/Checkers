/* Checkers with Alfa-Beta pruning
 * 
 * Developed by:
 * Guilherme Silva (frenzox@msn.com)
 * AndrŽ Luppi (andrell_alemao@hotmail.com)
 * 
 * Pontifical Catholic University of Paran‡ - Curitiba/Paran‡/Brazil
 * 
 * Project repository: https://github.com/frenzox/Checkers/
 */
package br.pucpr;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.pucpr.Pieces.Pawn;

public class Main
{
	private boolean flag = false;
	static JButtonCheckers[][] buttons;

	public static void main( String[] args )
	{
		try
		{
			UIManager.setLookAndFeel( UIManager
					.getCrossPlatformLookAndFeelClassName() );
		} catch ( UnsupportedLookAndFeelException e )
		{

		} catch ( ClassNotFoundException e )
		{

		} catch ( InstantiationException e )
		{

		} catch ( IllegalAccessException e )
		{

		}

		buttons = new JButtonCheckers[8][8];

		final JFrame frame = new JFrame( "Damas" );
		frame.setSize( 800, 800 );
		frame.setLocationRelativeTo( null );
		frame.setLayout( null );

		JPanel contentPane = new JPanel();
		contentPane.setLayout( new GridLayout( 8, 8 ) );

		contentPane.setBounds( 0, 0, 600, 600 );

		for ( int i = 0; i < 8; i++ )
		{
			for ( int j = 0; j < 8; j++ )
			{
				JButtonCheckers btn = new JButtonCheckers( i, j );

				buttons[i][j] = btn;

				if ( ( i + j ) % 2 != 0 )
					buttons[i][j].setBackground( Color.black );

				else
					buttons[i][j].setBackground( Color.white );
				buttons[i][j].setSize( 10, 10 );

				contentPane.add( btn );

			}
		}

		Checkers table = new Checkers();
		printTable( table );
		frame.add( contentPane );
		frame.setVisible( true );
	}

	public static void printTable( Checkers t )
	{
		LinkedList<LinkedList<IPiece>> table = t.getTable();

		ImageIcon whitePiece = new ImageIcon(
				"../Checkers/src/br/pucpr/img/white.png" );
		ImageIcon blackPiece = new ImageIcon(
				"../Checkers/src/br/pucpr/img/black.png" );

		for ( int i = 0; i < 8; i++ )
		{
			for ( int j = 0; j < 8; j++ )
			{

				IPiece piece = table.get( i ).get( j );

				if ( piece != null )
				{
					if ( piece.getColor() == Colors.BLACK )
						buttons[i][j].setIcon( blackPiece );
					else
						buttons[i][j].setIcon( whitePiece );
				}

			}
		}
	}

}
