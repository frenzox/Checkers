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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

		final Checkers table = new Checkers();
		buttons = new JButtonCheckers[8][8];
		final int org[] = { 0, 0 };

		final JFrame frame = new JFrame( "Damas" );
		frame.setSize( 600, 625 );
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

				btn.addActionListener( new ActionListener()
				{
					@Override
					// quando houver evento
					public void actionPerformed( ActionEvent e )
					{
						JButtonCheckers btn = ( JButtonCheckers ) e.getSource();

						if ( table.isSelected() )
						{
							try
							{
								table.move( org[0], org[1], btn.row, btn.col );

							} catch ( MovErr e1 )
							{
								JOptionPane.showMessageDialog( frame, e1 );

							}
							table.setSelected( false );
							org[0] = 0;
							org[1] = 0;

						} else
						{
							org[0] = btn.row;
							org[1] = btn.col;
							table.setSelected( true );
						}

						printTable( table );
					}
				} );
			}
		}

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
				} else
					buttons[i][j].setIcon( null );

			}
		}
	}

}
