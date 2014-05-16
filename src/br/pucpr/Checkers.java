package br.pucpr;

import java.util.LinkedList;

import br.pucpr.Pieces.King;
import br.pucpr.Pieces.Pawn;

public class Checkers
{

	private LinkedList<LinkedList<IPiece>> table = new LinkedList<LinkedList<IPiece>>();
	private boolean selected = false;

	public Checkers( LinkedList<LinkedList<IPiece>> table )
	{
		this.table = table;
	}

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

	public void act( Action act ) throws MovErr
	{

		// verifica se existe uma Peca na posicao de origem
		if ( table.get( act.getxOrigin() ).get( act.getyOrigin() ) == null )
			throw new MovErr( "Erro, posicao (x,y) sem peca: "
					+ act.getxOrigin() + "," + act.getyOrigin() );
		/*
		 * verifica se o movimento e valido
		 */
		if ( !table.get( act.getxOrigin() ).get( act.getyOrigin() )
				.isValid( act.getxDest(), act.getyDest(), table ) )
			throw new MovErr( "Erro, posicao (x,y) invalida: " + act.getxDest()
					+ "," + act.getyDest() );
		// verifica se a posicao de destino ja esta ocupada
		if ( table.get( act.getxDest() ).get( act.getyDest() ) == null )
		{
			// testa possibilidade de ataque
			if ( table.get( act.getxOrigin() ).get( act.getyOrigin() )
					.isHit( act.getxDest(), act.getyDest(), table ) )
			{
				IPiece tmp = table.get( act.getxOrigin() ).get(
						act.getyOrigin() );
				table.get( act.getxOrigin() ).set( act.getyOrigin(), null );
				table.get( act.getxDest() ).set( act.getyDest(), tmp );
				tmp.setX0( act.getxDest() );
				tmp.setY0( act.getyDest() );

				table.get( tmp.getTargetx() ).set( tmp.getTargety(), null );

				if ( tmp.isKing() )
				{
					table.get( act.getxDest() )
							.set( act.getyDest(), new King() );
					table.get( act.getxDest() ).get( act.getyDest() )
							.setX0( act.getxDest() );
					table.get( act.getxDest() ).get( act.getyDest() )
							.setY0( act.getyDest() );
					table.get( act.getxDest() ).get( act.getyDest() )
							.setColor( tmp.getColor() );

				}

				return;

			}

			IPiece tmp = table.get( act.getxOrigin() ).get( act.getyOrigin() );
			table.get( act.getxOrigin() ).set( act.getyOrigin(), null );
			table.get( act.getxDest() ).set( act.getyDest(), tmp );
			tmp.setX0( act.getxDest() );
			tmp.setY0( act.getyDest() );

			if ( tmp.isKing() )
			{
				table.get( act.getxDest() ).set( act.getyDest(), new King() );
				table.get( act.getxDest() ).get( act.getyDest() )
						.setX0( act.getxDest() );
				table.get( act.getxDest() ).get( act.getyDest() )
						.setY0( act.getyDest() );
				table.get( act.getxDest() ).get( act.getyDest() )
						.setColor( tmp.getColor() );
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

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected( boolean selected )
	{
		this.selected = selected;
	}

	public LinkedList<Action> getActions()
	{
		return null;
	}

}
