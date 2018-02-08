package wireBall;

import java.awt.*;

/**
* 敵弾クラス<p>
* 移動処理、描画処理など
*/
public class Bullet extends GameObject
{
	static int radius = 4;
	double direction;
	double speed;
	double speedX;
	double speedY;

	Bullet()
	{
		active = false;
	}

	public void move(double cameraX, double cameraY)
	{
		x += speedX;
		y += speedY;

		displayX = x - cameraX + MyPanel.WIDTH / 2;
		displayY = y - cameraY + MyPanel.HEIGHT / 2;

		if ( (displayX < 0)||(MyPanel.WIDTH < displayX)||(displayY < 0)||(MyPanel.HEIGHT < displayY) )
		{
			active = false;
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.blue);
		g.drawOval((int)(displayX - radius), (int)(displayY - radius), (int)radius * 2, (int)radius * 2);
	}

	/**
	 * インスタンスを有効にする。インスタンスの使い回しをしているので、
	 * 初期化処理もここで行う。
	 * @param ix 生成する位置(X座標)
	 * @param iy 生成する位置(Y座標)
	 * @param idirection 向き(単位は度　0-360)
	 * @param ispeed 速度(単位はピクセル)
	 */
	public void activate(double ix, double iy, double idirection, double ispeed)
	{
		x = ix;
		y = iy;
		direction = idirection;
		speed = ispeed;
		active = true;


		double radian;
		radian = Math.toRadians(direction);
		speedX = speed * Math.cos(radian);
		speedY = speed * Math.sin(radian);
	}

	/**
	 * 指定された方向に弾を撃つ
	 * @param angle 動かす方向の角度（３時の方向から反時計回り）
	 */
	public static void FireRound(double x, double y, int angle)
	{
		ObjectPool.newBullet(x, y, angle, 3);
	}

	/**
	 * プレイヤーの位置に向けて弾を撃つ
	 */
	public static void FireAim(double x, double y, Player player)
	{
		double degree = Math.toDegrees(Math.atan2(player.y - y, player.x - x));
		ObjectPool.newBullet(x, y, degree, 4);
		ObjectPool.newBullet(x, y, degree+20, 4);
		ObjectPool.newBullet(x, y, degree-20, 4);
	}
}

