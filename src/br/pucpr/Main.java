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
import java.awt.Image;
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
	private static LinkedList<LinkedList<IPiece>> table = new LinkedList<LinkedList<IPiece>>();

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

		for ( int i = 0; i < 8; i++ )
		{
			table.add( new LinkedList<IPiece>() );

			for ( int j = 0; j < 8; j++ )
			{
				table.get( i ).add( null );
			}
		}

		for ( int i = 0; i < 8; i++ )
			for ( int j = 0; j < 8; j++ )
			{
				if ( ( i + j ) % 2 != 0 )
				{

					if ( i <= 2 )
					{
						table.get( i ).set( j, new Pawn() );
						table.get( i ).get( j ).setX0( i );
						table.get( i ).get( j ).setY0( j );
						table.get( i ).get( j ).setColor( Colors.BLACK );

					}
					if ( i >= 5 )
					{

						table.get( i ).set( j, new Pawn() );
						table.get( i ).get( j ).setX0( i );
						table.get( i ).get( j ).setY0( j );
						table.get( i ).get( j ).setColor( Colors.BLACK );

					}

				}

				ImageIcon whitePiece = new ImageIcon(
						"../Checkers/src/br/pucpr/img/white.png" );
				ImageIcon blackPiece = new ImageIcon(
						"/Checkers/src/br/pucpr/img/black.png" );

				buttons = new JButtonCheckers[8][8];

				final JFrame frame = new JFrame( "Damas" );
				frame.setSize( 800, 800 );
				frame.setLocationRelativeTo( null );
				frame.setLayout( null );

				JPanel contentPane = new JPanel();
				contentPane.setLayout( new GridLayout( 8, 8 ) );

				contentPane.setBounds( 0, 0, 600, 600 );
				frame.add( contentPane );

				for ( i = 0; i < 8; i++ )
					for ( j = 0; j < 8; j++ )
					{
						JButtonCheckers btn = new JButtonCheckers( i, j );
						buttons[i][j] = btn;

						if ( table.get( i ).get( j ) != null
								&& table.get( i ).get( j ).getColor() == Colors.BLACK )
							buttons[i][j].setIcon( blackPiece );

						if ( table.get( i ).get( j ) != null
								&& table.get( i ).get( j ).getColor() == Colors.WHITE )
							buttons[i][j].setIcon( whitePiece );

						if ( ( i + j ) % 2 != 0 )
							buttons[i][j].setBackground( Color.black );

						else
							buttons[i][j].setBackground( Color.white );
						buttons[i][j].setSize( 10, 10 );

						contentPane.add( btn );

						frame.setVisible( true );

					}
			}

	}

	public int movimenta( int xOr, int yOr, int xDest, int yDest )
			throws MovErr
	{

		// verifica se existe uma Peca na posicao de origem
		if ( table.get( xOr ).get( yOr ) == null )
			throw new MovErr( "Erro, posicao (x,y) sem peca: " + xOr + ","
					+ yOr );
		/*
		 * verifica se o movimento e valido
		 */
		if ( !table.get( xOr ).get( yOr ).isValid( xDest, yDest, table ) )
			throw new MovErr( "Erro, posicao (x,y) invalida: " + xDest + ","
					+ yDest );
		// verifica se a posicao de destino ja esta ocupada
		if ( table.get( xDest ).get( yDest ) == null )
		{
			// testa possibilidade de ataque
			if ( !( table.get( xOr ).get( yOr ).isHit( xDest, yDest, table ) ) )
				throw new MovErr( "Erro, posicao (x,y) invalidaaa: " + xDest
						+ "," + yDest );

			IPiece tmp = table.get( xOr ).get( yOr );
			table.get( xOr ).set( yOr, null );
			table.get( xDest ).set( yOr, tmp );
			tmp.setX0( xDest );
			tmp.setY0( yDest );
			return 0;

		} else
		{

			/*
			 * caso a posicao de destino nao esteja ocupada,atualiza a
			 * posi�‹o
			 */

			IPiece tmp = table.get( xOr ).get( yOr );
			table.get( xOr ).set( yOr, null );
			table.get( xDest ).set( yOr, tmp );
			tmp.setX0( xDest );
			tmp.setY0( yDest );
			return 0;

		}

	}

}
