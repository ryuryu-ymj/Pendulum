package wireBall;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	/** キーの状態を保持しておくためのフラグ */
	boolean keySpace;

	/**
	 * コンストラクタ
	 */
	KeyInput()
	{
		keySpace = false;
	}

	/**
	 * キーがタイプされたときに呼び出される.
	 */
	public void keyTyped(KeyEvent e)
	{
		
	}

	/**
	 * キーが押されたときに呼び出される.
	 */
	public void keyPressed(KeyEvent e)
	{
		// 押されたキーで分岐する.
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				keySpace = true;
				break;
		}
	}

	/**
	 * キーが離されたときに呼び出される.
	 */
	public void keyReleased(KeyEvent e)
	{
		// 離されたキーで分岐する.
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				keySpace = false;
				break;
		}
	}
}
