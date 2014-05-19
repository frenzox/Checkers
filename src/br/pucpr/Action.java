package br.pucpr;

public class Action
{
	private int xOrigin;
	private int yOrigin;
	private int xDest;
	private int yDest;

	/**
	 * @param xOrigin
	 * @param yOrigin
	 * @param xDest
	 * @param yDest
	 */
	public Action( int xOrigin, int yOrigin, int xDest, int yDest )
	{
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.xDest = xDest;
		this.yDest = yDest;
	}

	/**
	 * @return the xOrigin
	 */
	public int getxOrigin()
	{
		return xOrigin;
	}

	/**
	 * @param xOrigin
	 *            the xOrigin to set
	 */
	public void setxOrigin( int xOrigin )
	{
		this.xOrigin = xOrigin;
	}

	/**
	 * @return the yOrigin
	 */
	public int getyOrigin()
	{
		return yOrigin;
	}

	/**
	 * @param yOrigin
	 *            the yOrigin to set
	 */
	public void setyOrigin( int yOrigin )
	{
		this.yOrigin = yOrigin;
	}

	/**
	 * @return the xDest
	 */
	public int getxDest()
	{
		return xDest;
	}

	/**
	 * @param xDest
	 *            the xDest to set
	 */
	public void setxDest( int xDest )
	{
		this.xDest = xDest;
	}

	/**
	 * @return the yDest
	 */
	public int getyDest()
	{
		return yDest;
	}

	/**
	 * @param yDest
	 *            the yDest to set
	 */
	public void setyDest( int yDest )
	{
		this.yDest = yDest;
	}

	public boolean compare( Action act )
	{
		if ( act.getxOrigin() == this.getxOrigin()
				&& act.getyOrigin() == this.getyOrigin()
				&& act.getxDest() == this.getxDest()
				&& act.getyDest() == this.getyDest() )
			return true;

		return false;

	}

}
