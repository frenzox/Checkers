package br.pucpr.Pieces;

import java.util.LinkedList;

import br.pucpr.Colors;
import br.pucpr.IPiece;

public class Pawn implements IPiece
{
	private Colors pieceColor;
	private int x0, y0;
	private boolean hitOnce = false;
	private int targetX, targetY;

	@Override
	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		if ( x == x0 || y == y0 )
			return false;
		if ( ( x > 7 ) || ( x < 0 ) )
			return false;

		if ( ( y > 7 ) || ( y < 0 ) )
			return false;

		if ( ( x + y ) % 2 == 0 )
			return false;

		if ( pieceColor == Colors.WHITE )
		{
			if ( x > x0 )
				return false;

			if ( ( Math.abs( x - x0 ) > 1 ) && !( isHit( x, y, table ) ) )
				return false;

		}
		if ( pieceColor == Colors.BLACK )
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

		if ( isHitOnce() )
		{
			if ( ( y < y0 ) && ( x > x0 ) )
			{
				if ( table.get( x + 1 ).get( y - 1 ).getColor() == table
						.get( x0 ).get( y0 ).getColor() )
					return false;

			} else if ( ( y > y0 ) && ( x > x0 ) )
			{
				if ( table.get( x + 1 ).get( y + 1 ).getColor() == table
						.get( x0 ).get( y0 ).getColor() )
					return false;

			}

			if ( ( y < y0 ) && ( x < x0 ) )
			{
				if ( table.get( x - 1 ).get( y - 1 ).getColor() == table
						.get( x0 ).get( y0 ).getColor() )
					return false;
			} else if ( ( y > y0 ) && ( x < x0 ) )
			{
				if ( table.get( x - 1 ).get( y + 1 ).getColor() == table
						.get( x0 ).get( y0 ).getColor() )
					return false;
			}
			if ( x < x0 )
			{
				if ( y > y0 )
				{
					setTargetX( x0 - 1 );
					setTargetY( y0 + 1 );
					return true;
				} else
				{
					setTargetX( x0 - 1 );
					setTargetY( y0 - 1 );
					return true;
				}
			}
			if ( x > x0 )
			{

				if ( y > y0 )
				{
					setTargetX( x0 + 1 );
					setTargetY( y0 + 1 );

					return true;
				} else
				{
					setTargetX( x0 + 1 );
					setTargetY( y0 - 1 );
					return true;
				}
			}
			return true;
		}

		else if ( pieceColor == Colors.WHITE )
		{

			if ( y < y0 )
			{
				if ( table.get( x0 - 1 ).get( y0 - 1 ) == null
						|| table.get( x0 - 1 ).get( y0 - 1 ).getColor() == pieceColor )
					return false;
			} else if ( y > y0 )
			{
				if ( table.get( x0 - 1 ).get( y0 + 1 ) == null
						|| table.get( x0 - 1 ).get( y0 + 1 ).getColor() == pieceColor )
					return false;
			}

			if ( x < x0 )
			{
				if ( y > y0 )
				{
					setTargetX( x0 - 1 );
					setTargetY( y0 + 1 );
					return true;
				} else
				{
					setTargetX( x0 - 1 );
					setTargetY( y0 - 1 );
					return true;
				}
			}

			return true;
		}

		if ( pieceColor == Colors.BLACK )
		{

			if ( y < y0 )
			{
				if ( table.get( x0 + 1 ).get( y0 - 1 ) == null
						|| table.get( x0 + 1 ).get( y0 - 1 ).getColor() == pieceColor )
					return false;
			} else if ( y > y0 )
			{
				if ( table.get( x0 + 1 ).get( y0 + 1 ) == null
						|| table.get( x0 + 1 ).get( y0 + 1 ).getColor() == pieceColor )
					return false;
			}

			if ( x > x0 )
			{

				if ( y > y0 )
				{
					setTargetX( x0 + 1 );
					setTargetY( y0 + 1 );

					return true;
				} else
				{
					setTargetX( x0 + 1 );
					setTargetY( y0 - 1 );
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public void setHitOnce( boolean hitOnce )
	{
		this.hitOnce = hitOnce;
	}

	public int getTargetX()
	{
		return targetX;
	}

	public void setTargetX( int targetX )
	{
		this.targetX = targetX;
	}

	public int getTargetY()
	{
		return targetY;
	}

	public void setTargetY( int targetY )
	{
		this.targetY = targetY;
	}

	@Override
	public Colors getColor()
	{
		return this.pieceColor;
	}

	@Override
	public void setColor( Colors color )
	{
		this.pieceColor = color;

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

	@Override
	public boolean isHitOnce()
	{

		return hitOnce;
	}
}
