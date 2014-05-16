package br.pucpr;

import java.util.LinkedList;

import br.pucpr.Pieces.King;
import br.pucpr.Pieces.Pawn;

public class Checkers
{
	private LinkedList<LinkedList<IPiece>> table = new LinkedList<LinkedList<IPiece>>();
	private boolean selected = false;

	public Checkers()
	{
		for ( int i = 0; i < 8; i++ )
		{
			table.add( new LinkedList<IPiece>() );

			for ( int j = 0; j < 8; j++ )
			{
				table.get( i ).add( null );
			}
		}

		for ( int i = 0; i < 8; i++ )
		{
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
						table.get( i ).get( j ).setColor( Colors.WHITE );

					}

				}
			}
		}
	}

	public LinkedList<LinkedList<IPiece>> getTable()
	{
		return table;
	}

	public void setTable( LinkedList<LinkedList<IPiece>> table )
	{
		this.table = table;
	}

	public void move( int xOr, int yOr, int xDest, int yDest ) throws MovErr
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
			if ( table.get( xOr ).get( yOr ).isHit( xDest, yDest, table ) )
			{
				IPiece tmp = table.get( xOr ).get( yOr );
				table.get( xOr ).set( yOr, null );
				table.get( xDest ).set( yDest, tmp );
				tmp.setX0( xDest );
				tmp.setY0( yDest );

				table.get( tmp.getTargetx() ).set( tmp.getTargety(), null );

				if ( tmp.isKing() )
				{
					table.get( xDest ).set( yDest, new King() );
					table.get( xDest ).get( yDest ).setX0( xDest );
					table.get( xDest ).get( yDest ).setY0( yDest );
					table.get( xDest ).get( yDest ).setColor( tmp.getColor() );

				}

				return;

			}

			IPiece tmp = table.get( xOr ).get( yOr );
			table.get( xOr ).set( yOr, null );
			table.get( xDest ).set( yDest, tmp );
			tmp.setX0( xDest );
			tmp.setY0( yDest );

			if ( tmp.isKing() )
			{
				table.get( xDest ).set( yDest, new King() );
				table.get( xDest ).get( yDest ).setX0( xDest );
				table.get( xDest ).get( yDest ).setY0( yDest );
				table.get( xDest ).get( yDest ).setColor( tmp.getColor() );
			}

		}

	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected( boolean selected )
	{
		this.selected = selected;
	}

}
