/* Checkers with Alfa-Beta pruning
 * 
 * Developed by:
 * Guilherme Silva (frenzox@msn.com)
 * Andr≈Ω Luppi (andrell_alemao@hotmail.com)
 * 
 * Pontifical Catholic University of Paran‚Ä° - Curitiba/Paran‚Ä°/Brazil
 * 
 * Project repository: https://github.com/frenzox/Checkers/
 */
package br.pucpr;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.LinkedList;

public class Main
{

	static JButtonCheckers[][] buttons;
	static JFrame frame;
	static Checkers game;
	static int x = 0;
	static int y = 0;
	static LinkedList<Action> actions;
	static boolean combo = false;

	public static void main( String[] args )
	{
		try
		{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch ( UnsupportedLookAndFeelException e )
		{
			e.printStackTrace();
		} catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		} catch ( InstantiationException e )
		{
			e.printStackTrace();
		} catch ( IllegalAccessException e )
		{
			e.printStackTrace();
		}

		game = new Checkers();
		game.setTurn( Player.WHITE );
		buttons = new JButtonCheckers[8][8];

		frame = new JFrame( "Damas" );
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
					/**
					 * MAGIA NEGRA
					 */
					public void actionPerformed( ActionEvent e )
					{
						JButtonCheckers btn = ( JButtonCheckers ) e.getSource();
						if ( game.getTurn() == game.getTurn() )
						{
							if ( game.isSelected() )
							{
								Action act = new Action( x, y, btn.row, btn.col );
								boolean found = false;

								if ( actions != null )
								{
									for ( Action a : actions )
									{
										if ( a.compare( act ) )
											found = true;

									}
									if ( !found )
									{
										JOptionPane.showMessageDialog( frame,
												"Você tem uma jogada obrigatória a realizar!" );
										game.setSelected( false );

										x = 0;
										y = 0;
										return;

									}
								}

								try
								{
									actions = game.act( act );

								} catch ( MovErr e1 )
								{
									JOptionPane.showMessageDialog( frame, e1 );
									game.setSelected( false );

									x = 0;
									y = 0;
									return;

								}

								game.setSelected( false );

								x = 0;
								y = 0;

								if ( actions == null )
								{
									game.getStatus().get( btn.row ).get( btn.col ).setCombo( false );
									game.changeTurn();
								}

							} else
							{
								if ( game.getStatus().get( btn.row ).get( btn.col ) != null
										&& game.getStatus().get( btn.row ).get( btn.col )
												.getPlayer() != game.getTurn() )
								{
									JOptionPane.showMessageDialog( frame, "Está não é sua peça!" );
									return;
								}
								x = btn.row;
								y = btn.col;
								game.setSelected( true );
							}
						}
						printTable( game );
					}
				} );
			}
		}

		printTable( game );
		frame.add( contentPane );
		frame.setVisible( true );
	}

	public static void printTable( Checkers game )
	{
		LinkedList<LinkedList<IPiece>> table = game.getStatus();

		ImageIcon whitePiece = new ImageIcon( "../Checkers/src/br/pucpr/img/white.png" );
		ImageIcon blackPiece = new ImageIcon( "../Checkers/src/br/pucpr/img/black.png" );
		ImageIcon blackKPiece = new ImageIcon( "../Checkers/src/br/pucpr/img/blackK.png" );
		ImageIcon whiteKPiece = new ImageIcon( "../Checkers/src/br/pucpr/img/whiteK.png" );

		for ( int i = 0; i < 8; i++ )
		{
			for ( int j = 0; j < 8; j++ )
			{

				IPiece piece = table.get( i ).get( j );

				if ( piece != null )
				{
					if ( piece.getPlayer() == Player.BLACK )
					{
						buttons[i][j].setIcon( blackPiece );
						if ( piece.isKing() )
						{
							buttons[i][j].setIcon( blackKPiece );
						}

					} else
					{
						buttons[i][j].setIcon( whitePiece );
						if ( piece.isKing() )
						{
							buttons[i][j].setIcon( whiteKPiece );
						}
					}
				} else
					buttons[i][j].setIcon( null );

			}
		}
	}

}
