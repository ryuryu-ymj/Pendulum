package wireBall;

import java.awt.*;

/**
*ゲームオブジェクト抽象クラス<p>
*プレイヤー、弾、敵などのスーパークラス
*/
public abstract class GameObject
{
	/**
	 * インスタンス有効フラグ（falseならインスタンスは処理されない）
	*/
    public boolean active;

	public double x;
	public double y;
	public double displayX;
	public double displayY;

    abstract void move(double cameraX, double cameraY);

    abstract void draw(Graphics g);
}
