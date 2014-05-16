package br.pucpr.Pieces;

import java.util.LinkedList;

import br.pucpr.Player;
import br.pucpr.IPiece;

public class Pawn implements IPiece
{
	private Player player;
	private int x0, y0;
	private int targetX = 0, targetY = 0;

	@Override
	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		if ( x == x0 || y == y0 )
			return false;

		if ( table.get( x ).get( y ) != null )
			return false;

		if ( ( x > 7 ) || ( x < 0 ) )
			return false;

		if ( ( y > 7 ) || ( y < 0 ) )
			return false;

		if ( ( x + y ) % 2 == 0 )
			return false;

		if ( player == Player.WHITE )
		{
			if ( x > x0 )
				return false;

			if ( ( Math.abs( x - x0 ) > 1 ) && !( isHit( x, y, table ) ) )
				return false;

		}
		if ( player == Player.BLACK )
		{

			if ( x < x0 )
				return false;

			if ( ( Math.abs( x - x0 ) > 1 ) && !( isHit( x, y, table ) ) )
				return false;

		}

		return true;
	}

	@Override
	public boolean isHit( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		if ( table.get( x ).get( y ) != null )
			return false;

		if ( Math.abs( x - x0 ) != 2 )
			return false;

		if ( player == Player.WHITE )
		{

			if ( y < y0 )
			{
				if ( table.get( x0 - 1 ).get( y0 - 1 ) == null
						|| table.get( x0 - 1 ).get( y0 - 1 ).getPlayer() == player )
					return false;
				setTargetx( x0 - 1 );
				setTargety( y0 - 1 );
			} else if ( y > y0 )
			{
				if ( table.get( x0 - 1 ).get( y0 + 1 ) == null
						|| table.get( x0 - 1 ).get( y0 + 1 ).getPlayer() == player )
					return false;
				setTargetx( x0 - 1 );
				setTargety( y0 + 1 );
			}

			return true;
		} else
		{

			if ( y < y0 )
			{
				if ( table.get( x0 + 1 ).get( y0 - 1 ) == null
						|| table.get( x0 + 1 ).get( y0 - 1 ).getPlayer() == player )
					return false;
				setTargetx( x0 + 1 );
				setTargety( y0 - 1 );
			} else if ( y > y0 )
			{
				if ( table.get( x0 + 1 ).get( y0 + 1 ) == null
						|| table.get( x0 + 1 ).get( y0 + 1 ).getPlayer() == player )
					return false;
				setTargetx( x0 + 1 );
				setTargety( y0 + 1 );
			}

		}
		return true;
	}

	public boolean isKing()
	{

		if ( player == Player.WHITE )
		{

			if ( x0 == 0 )
				return true;
		}
		if ( player == Player.BLACK )
		{
			if ( x0 == 7 )
				return true;
		}
		return false;
	}

	public int getTargetx()
	{
		return targetX;
	}

	public void setTargetx( int targetX )
	{
		this.targetX = targetX;
	}

	public int getTargety()
	{
		return targetY;
	}

	public void setTargety( int targetY )
	{
		this.targetY = targetY;
	}

	@Override
	public Player getPlayer()
	{
		return this.player;
	}

	@Override
	public void setPlayer( Player player )
	{
		this.player = player;

	}

	@Override
	public int getX0()
	{
		return this.x0;
	}

	@Override
	public void setX0( int x0 )
	{
		this.x0 = x0;

	}

	@Override
	public int getY0()
	{
		return this.y0;
	}

	@Override
	public void setY0( int y0 )
	{
		this.y0 = y0;

	}

}
