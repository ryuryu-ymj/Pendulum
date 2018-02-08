package wireBall;

import java.awt.Color;
import java.awt.Graphics;

public class Heart extends GameObject
{
	/**
	 * 半径
	 */
	static int radius = 12;
	/**
	 * ハートがステージ上のどのハートを演じているのか（heartXの番号）
	 */
	int num;

	void move(double cameraX, double cameraY)
	{
		if (active)
		{
			displayX = x - cameraX + MyPanel.WIDTH / 2;
			displayY = y - cameraY + MyPanel.HEIGHT / 2;
		}

		if (displayX < 0 && MyPanel.WIDTH < displayX)
		{
			if (displayY < 0 && MyPanel.HEIGHT < displayY)
			{
				active = false;
				ObjectPool.heartActive[num] = false;
				ObjectPool.heartDisplay[num] = false;
			}
		}
	}

	void draw(Graphics g)
	{
		drawHeart(g, displayX, displayY);
	}

	static void drawHeart(Graphics g, double displayX, double displayY)
	{
		g.setColor(Color.PINK);
		g.drawArc((int)displayX - radius, (int)displayY - radius, radius * 2, radius * 2, 0, -180);
		g.drawArc((int)displayX - radius, (int)displayY - radius * 3 / 4, radius, radius * 3 / 2, 0, 180);
		g.drawArc((int)displayX, (int)displayY - radius * 3 / 4, radius, radius * 3 / 2, 0, 180);
	}

	public void activate(int x, int y)
	{
		this.x = x;
		this.y = y;
		active = true;
	}
}
