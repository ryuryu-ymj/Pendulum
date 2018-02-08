package wireBall;

public class Camera
{
	/**
	 * 画面の中心座標
	 */
	double x, y;
	boolean active;

	Camera()
	{
		x = MyPanel.WIDTH / 2;
		y = MyPanel.HEIGHT / 2;
		active = false;
	}

	void move(double playerX, double playerY)
	{
		if (active)
		{
			x += (playerX - x) / 20;
			y += (playerY - 100 - y) / 20;
		}
	}
}
