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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.pucpr.Pieces.Pawn;

public class Main
{
	private boolean flag = false;
	private static IPiece table[][];
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

		ImageIcon imgTst = new ImageIcon();
		

		buttons = new JButtonCheckers[8][8];

		final JFrame frame = new JFrame( "Damas" );
		frame.setSize( 800, 800 );
		frame.setLocationRelativeTo( null );
		frame.setLayout( null );

		JPanel contentPane = new JPanel();
		contentPane.setLayout( new GridLayout( 8, 8 ) );
		// contentPane.setLocation( 0, 0 );
		contentPane.setBounds( 0, 0, 600, 600 );
		frame.add( contentPane );

		for ( int i = 0; i < 8; i++ )
			for ( int j = 0; j < 8; j++ )
			{
				JButtonCheckers btn = new JButtonCheckers( i, j );
				btn.setIcon( imgTst );
				buttons[i][j] = btn;
				if ( ( i + j ) % 2 != 0 )
					buttons[i][j].setBackground( Color.black);

				else
					buttons[i][j].setBackground( Color.white );
				buttons[i][j].setSize( 10, 10 );

				contentPane.add( btn );

				frame.setVisible( true );

				/*
				for ( i = 0; i < 8; i++ )
					for ( j = 0; j < 8; j++ )
					{

						if ( i + j % 2 != 0 )
						{

							if ( j <= 2 )
							{

								table[i][j] = new Pawn();
								table[i][j].setX0( i );
								table[i][j].setY0( j );
								table[i][j].setColor( Colors.BLACK );

							}
							if ( j >= 5 )
							{

								table[i][j] = new Pawn();
								table[i][j].setX0( i );
								table[i][j].setY0( j );
								table[i][j].setColor( Colors.WHITE );

							}

						}
					}*/
			}

	}

	public int movimenta( int xOr, int yOr, int xDest, int yDest )
			throws MovErr
	{

		// verifica se existe uma Peca na posicao de origem
		if ( table[xOr][yOr] == null )
			throw new MovErr( "Erro, posicao (x,y) sem peca: " + xOr + ","
					+ yOr );
		/*
		 * verifica se o movimento e valido
		 */
		if ( !table[xOr][yOr].isValid( xDest, yDest, table ) )
			throw new MovErr( "Erro, posicao (x,y) invalida: " + xDest + ","
					+ yDest );
		// verifica se a posicao de destino ja esta ocupada
		if ( table[xDest][yDest] == null )
		{
			// testa possibilidade de ataque
			if ( !( table[xOr][yOr].isHit( xDest, yDest, table ) ) )
				throw new MovErr( "Erro, posicao (x,y) invalidaaa: " + xDest
						+ "," + yDest );

			IPiece tmp = table[xOr][yOr];
			table[xOr][yOr] = null;
			table[xDest][yDest] = tmp;
			tmp.setX0( xDest );
			tmp.setY0( yDest );
			return 0;

		} else
		{

			/*
			 * caso a posicao de destino nao esteja ocupada,atualiza a posi�‹o
			 */

			IPiece tmp = table[xOr][yOr];
			table[xOr][yOr] = null;
			table[xDest][yDest] = tmp;
			tmp.setX0( xDest );
			tmp.setY0( yDest );
			return 0;

		}

	}

}
