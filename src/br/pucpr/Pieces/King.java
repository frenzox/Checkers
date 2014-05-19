package br.pucpr.Pieces;

import java.util.LinkedList;

import br.pucpr.Player;
import br.pucpr.IPiece;

public class King implements IPiece
{

	private Player pieceplayer;
	private int x0, y0;
	private int targetX = 0, targetY = 0;
	private boolean combo = false;

	@Override
	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		if ( table.get( x ).get( y ) != null )
			return false;

		if ( ( x > 7 ) || ( x < 0 ) )
			return false;

		if ( ( y > 7 ) || ( y < 0 ) )
			return false;

		if ( x > x0 )
		{

			if ( y > y0 )
			{
				for ( int i = x0 + 1, j = y0 + 1; i < x && j < y; i++, j++ )
					if ( table.get( i ).get( j ) != null && !isHit( x, y, table ) )
						return false;
			} else if ( y < y0 )
			{
				for ( int i = x0 + 1, j = y0 - 1; i < x && j > y; i++, j-- )
					if ( table.get( i ).get( j ) != null && !isHit( x, y, table ) )
						return false;
			}
		}

		if ( x < x0 )
		{

			if ( y > y0 )
			{
				for ( int i = x0 - 1, j = y0 + 1; i > x && j < y; i--, j++ )
					if ( table.get( i ).get( j ) != null && !isHit( x, y, table ) )
						return false;
			}

			else if ( y < y0 )
			{
				for ( int i = x0 - 1, j = y0 - 1; i > x && j > y; i--, j-- )
					if ( table.get( i ).get( j ) != null && !isHit( x, y, table ) )
						return false;
			}
		}
		return true;
	}

	@Override
	public boolean isHit( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		int count = 0;
		if ( x > x0 )
		{

			if ( y > y0 )
			{
				for ( int i = x0 + 1, j = y0 + 1; i < x && j < y; i++, j++ )
					if ( table.get( i ).get( j ) != null
							&& ( table.get( i ).get( j ).getPlayer() != pieceplayer ) )
					{
						count++;
						setTargetx( i );
						setTargety( j );
					}
				if ( count != 1 )
					return false;
				return true;
			} else if ( y < y0 )
			{
				for ( int i = x0 + 1, j = y0 - 1; i < x && j > y; i++, j-- )
					if ( table.get( i ).get( j ) != null
							&& ( table.get( i ).get( j ).getPlayer() != pieceplayer ) )
					{

						count++;
						setTargetx( i );
						setTargety( j );
					}
				if ( count != 1 )
					return false;
				return true;
			}
		}

		if ( x < x0 )
		{

			if ( y > y0 )
			{
				for ( int i = x0 - 1, j = y0 + 1; i > x && j < y; i--, j++ )
					if ( table.get( i ).get( j ) != null
							&& ( table.get( i ).get( j ).getPlayer() != pieceplayer ) )
					{

						count++;
						setTargetx( i );
						setTargety( j );
					}
				if ( count != 1 )
					return false;
				return true;
			}

			else if ( y < y0 )
			{
				for ( int i = x0 - 1, j = y0 - 1; i > x && j > y; i--, j-- )
					if ( table.get( i ).get( j ) != null
							&& ( table.get( i ).get( j ).getPlayer() != pieceplayer ) )
					{

						count++;
						setTargetx( i );
						setTargety( j );
					}
				if ( count != 1 )
					return false;
				return true;
			}
		}
		return false;
	}

	@Override
	public Player getPlayer()
	{
		return this.pieceplayer;
	}

	@Override
	public void setPlayer( Player player )
	{
		this.pieceplayer = player;

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
	public boolean isKing()
	{
		return true;
	}

	@Override
	public void setCombo( boolean combo )
	{
		this.combo = combo;

	}

	@Override
	public boolean isCombo()
	{
		return combo;
	}
}
