package br.pucpr;

import java.util.LinkedList;

import br.pucpr.Pieces.King;
import br.pucpr.Pieces.Pawn;

public class Checkers
{

	private LinkedList<LinkedList<IPiece>> status = new LinkedList<LinkedList<IPiece>>();
	private boolean selected = false;
	private Player turn;

	public Checkers()
	{
		for ( int i = 0; i < 8; i++ )
		{
			status.add( new LinkedList<IPiece>() );

			for ( int j = 0; j < 8; j++ )
			{
				status.get( i ).add( null );
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
						status.get( i ).set( j, new Pawn() );
						status.get( i ).get( j ).setX0( i );
						status.get( i ).get( j ).setY0( j );
						status.get( i ).get( j ).setPlayer( Player.BLACK );

					}
					if ( i >= 5 )
					{

						status.get( i ).set( j, new Pawn() );
						status.get( i ).get( j ).setX0( i );
						status.get( i ).get( j ).setY0( j );
						status.get( i ).get( j ).setPlayer( Player.WHITE );

					}

				}
			}
		}
	}

	public Checkers( LinkedList<LinkedList<IPiece>> status )
	{
		this.status = status;
	}

	public void act( Action act ) throws MovErr
	{

		// verifica se existe uma Peca na posicao de origem
		if ( status.get( act.getxOrigin() ).get( act.getyOrigin() ) == null )
			throw new MovErr( "Erro, posicao (x,y) sem peca: "
					+ act.getxOrigin() + "," + act.getyOrigin() );
		/*
		 * verifica se o movimento e valido
		 */
		if ( !status.get( act.getxOrigin() ).get( act.getyOrigin() )
				.isValid( act.getxDest(), act.getyDest(), status ) )
			throw new MovErr( "Erro, posicao (x,y) invalida: " + act.getxDest()
					+ "," + act.getyDest() );
		// verifica se a posicao de destino ja esta ocupada
		if ( status.get( act.getxDest() ).get( act.getyDest() ) == null )
		{
			// testa possibilidade de ataque
			if ( status.get( act.getxOrigin() ).get( act.getyOrigin() )
					.isHit( act.getxDest(), act.getyDest(), status ) )
			{
				IPiece tmp = status.get( act.getxOrigin() ).get(
						act.getyOrigin() );
				status.get( act.getxOrigin() ).set( act.getyOrigin(), null );
				status.get( act.getxDest() ).set( act.getyDest(), tmp );
				tmp.setX0( act.getxDest() );
				tmp.setY0( act.getyDest() );

				status.get( tmp.getTargetx() ).set( tmp.getTargety(), null );

				if ( tmp.isKing() )
				{
					status.get( act.getxDest() ).set( act.getyDest(),
							new King() );

					tmp = status.get( act.getxDest() ).get( act.getyDest() );
					tmp.setX0( act.getxDest() );
					tmp.setY0( act.getyDest() );
					tmp.setPlayer( tmp.getPlayer() );

				}

				return;

			}

			IPiece tmp = status.get( act.getxOrigin() ).get( act.getyOrigin() );
			status.get( act.getxOrigin() ).set( act.getyOrigin(), null );
			status.get( act.getxDest() ).set( act.getyDest(), tmp );
			tmp.setX0( act.getxDest() );
			tmp.setY0( act.getyDest() );

			if ( tmp.isKing() )
			{
				status.get( act.getxDest() ).set( act.getyDest(), new King() );
				status.get( act.getxDest() ).get( act.getyDest() )
						.setX0( act.getxDest() );
				status.get( act.getxDest() ).get( act.getyDest() )
						.setY0( act.getyDest() );
				status.get( act.getxDest() ).get( act.getyDest() )
						.setPlayer( tmp.getPlayer() );
			}

		}

	}

	/**
	 * @return List of possible actions for a player
	 */
	public LinkedList<Action> getActions( Player player )
	{
		LinkedList<Action> actions = new LinkedList<Action>();

		for ( LinkedList<IPiece> l : this.getStatus() )
		{
			for ( IPiece p : l )
			{
				if ( p.getPlayer() == player )
				{
					if ( p.getPlayer() == Player.WHITE )
					{
						if ( this.status.get( p.getX0() - 1 ).get(
								p.getY0() + 1 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() - 1, p.getY0() + 1 ) );
						} else if ( this.status.get( p.getX0() - 1 )
								.get( p.getY0() + 1 ).getPlayer() == Player.BLACK
								&& this.status.get( p.getX0() - 2 ).get(
										p.getY0() + 2 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() - 2, p.getY0() + 2 ) );
						}
						if ( this.status.get( p.getX0() - 1 ).get(
								p.getY0() - 1 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() - 1, p.getY0() - 1 ) );
						} else if ( this.status.get( p.getX0() - 1 )
								.get( p.getY0() - 1 ).getPlayer() == Player.BLACK
								&& this.status.get( p.getX0() - 2 ).get(
										p.getY0() - 2 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() - 2, p.getY0() - 2 ) );
						}
					} else
					{
						if ( this.status.get( p.getX0() + 1 ).get(
								p.getY0() + 1 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() + 1, p.getY0() + 1 ) );
						} else if ( this.status.get( p.getX0() + 1 )
								.get( p.getY0() + 1 ).getPlayer() == Player.WHITE
								&& this.status.get( p.getX0() + 2 ).get(
										p.getY0() + 2 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() + 2, p.getY0() + 2 ) );
						}
						if ( this.status.get( p.getX0() + 1 ).get(
								p.getY0() - 1 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() + 1, p.getY0() - 1 ) );
						} else if ( this.status.get( p.getX0() + 1 )
								.get( p.getY0() - 1 ).getPlayer() == Player.WHITE
								&& this.status.get( p.getX0() + 2 ).get(
										p.getY0() - 2 ) == null )
						{
							actions.add( new Action( p.getX0(), p.getY0(), p
									.getX0() + 2, p.getY0() - 2 ) );
						}
					}
				}

			}
		}

		return actions;
	}

	/**
	 * 
	 * @return game status
	 */
	public LinkedList<LinkedList<IPiece>> getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus( LinkedList<LinkedList<IPiece>> status )
	{
		this.status = status;
	}

	/**
	 * @return true or false, depends if any piece has been selected
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * @param selected
	 */
	public void setSelected( boolean selected )
	{
		this.selected = selected;
	}

}
