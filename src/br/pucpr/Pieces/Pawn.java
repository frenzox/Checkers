package br.pucpr.Pieces;

import br.pucpr.Colors;
import br.pucpr.IPiece;

public class Pawn implements IPiece
{
	private Colors pieceColor;
	private int x0, y0;

	@Override
	public boolean isValid( int x, int y, IPiece[][] table )
	{
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

			if ( ( Math.abs( x - x0 ) > 1 )
					&& !( table[x][y].isHit( x, y, table ) ) )
				return false;

		}
		if ( pieceColor == Colors.BLACK )
		{

			if ( x < x0 )
				return false;

			if ( ( Math.abs( x - x0 ) > 1 )
					&& !( table[x][y].isHit( x, y, table ) ) )
				return false;

		}

		return true;
	}

	@Override
	public boolean isHit( int x, int y, IPiece[][] table )
	{
		if ( table[x][y] != null )
			return false;

		if ( Math.abs( x - x0 ) != 2 )
			return false;

		if ( pieceColor == Colors.WHITE )
		{

			if ( table[x + 1][y - 1].getColor() == table[x0][y0].getColor() )
				return false;
			else
			{
				table[x + 1][y - 1].setColor( Colors.YELLOW );
				table[x][y] = table[x0][y0];

			}

			return true;
		}

		if ( pieceColor == Colors.BLACK )
		{

			if ( table[x - 1][y + 1].getColor() == table[x0][y0].getColor() )
				return false;
			else
			{
				table[x - 1][y + 1].setColor( Colors.YELLOW );
				table[x][y] = table[x0][y0];
			}

			return true;
		}
		return false;
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
}
