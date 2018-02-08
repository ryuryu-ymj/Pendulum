package wireBall;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * マウスの動作を受け取るためのクラス.
 * MouseListener, MouseMotionListener を実装している.
 */
public class MouseInput implements MouseListener, MouseMotionListener
{
	/** マウスの座標 */
	int mouseX, mouseY;
	boolean isMousePushed;

	/**
	 * コンストラクタ
	 */
	MouseInput()
	{
		mouseX = 0;
		mouseY = 0;
		isMousePushed = false;
	}

	/**
	 * マウスがクリックされたとき呼び出される.
	 * (MouseListener)
	 */
	public void mouseClicked(MouseEvent e)
	{
	}

	/**
	 * マウスボタンが押されたとき呼び出される.
	 * (MouseListener)
	 */
	public void mousePressed(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
		isMousePushed = true;
	}

	/**
	 * マウスボタンが離されたとき呼び出される.
	 * (MouseListener)
	 */
	public void mouseReleased(MouseEvent e)
	{
		mouseX = 0;
		mouseY = 0;
		//isMousePushed = false;
	}

	/**
	 * マウスカーソルがウィンドウに入ったとき呼び出される.
	 * (MouseListener)
	 */
	public void mouseEntered(MouseEvent e)
	{
	}

	/**
	 * マウスカーソルがウィンドウから出たとき呼び出される.
	 * (MouseListener)
	 */
	public void mouseExited(MouseEvent e)
	{
	}

	/**
	 * マウスがドラッグされたとき呼び出される.
	 * (MouseMotionListener)
	 */
	public void mouseDragged(MouseEvent e)
	{
	}

	/**
	 * マウスカーソルが動いたとき呼び出される.
	 * (MouseMotionListener)
	 */
	public void mouseMoved(MouseEvent e)
	{
	}
}