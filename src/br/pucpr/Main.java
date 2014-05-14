/* Checkers with Alfa-Beta pruning
 * 
 * Developed by:
 * Guilherme Silva (frenzox@msn.com)
 * André Luppi (andrell_alemao@hotmail.com)
 * 
 * Pontifical Catholic University of Paraná - Curitiba/Paraná/Brazil
 * 
 * Project repository: https://github.com/frenzox/Checkers/
 */
package br.pucpr;

import br.pucpr.Pieces.Pawn;

public class Main
{
	private boolean flag = false;
	private static IPiece table[][];

	public static void main()
	{
		for ( int i = 0; i < 8; i++ )
			for ( int j = 0; j < 8; j++ )
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
			 * caso a posicao de destino nao esteja ocupada,atualiza a posição
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
