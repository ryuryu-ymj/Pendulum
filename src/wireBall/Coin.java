package wireBall;

import java.awt.Color;
import java.awt.Graphics;

public class Coin extends GameObject
{
	/**
	 * 半径
	 */
	static int radius = 12;
	/**
	 * コインがステージ上のどのコインを演じているのか（coinXの番号）
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
				ObjectPool.coinActive[num] = false;
				ObjectPool.heartDisplay[num] = false;
			}
		}
	}

	void draw(Graphics g)
	{
		drawCoin(g, displayX, displayY, radius);
	}

	static void drawCoin(Graphics g, double displayX, double displayY, int radius)
	{
		g.setColor(Color.getHSBColor(54, 100, 94));
		g.drawOval((int)displayX - radius, (int)displayY - radius, radius * 2, radius * 2);
		g.drawRect((int)displayX - radius / 5, (int)displayY - radius * 4 / 5, radius * 2 / 5, radius * 8 / 5);
	}

	public void activate(int x, int y)
	{
		this.x = x;
		this.y = y;
		active = true;
	}
}
