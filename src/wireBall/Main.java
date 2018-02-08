package wireBall;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main extends JFrame
{
	/**
	 * メインクラス
	 */
	public static void main(String args[])
	{
		//フレームの作成
		new Main();
	}

	/**
	 * 引数なしのコンストラクタ
	 */
	Main()
	{
		//*** ウィンドウの初期化
		//タイトル
		super("PENDULUM");

		//クローズボタンによる終了処理の実装
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

		// ウィンドウサイズの変更を不可にする.
		setResizable(false);

		setSize(MyPanel.WIDTH, MyPanel.HEIGHT); //ウィンドウのサイズ

		//*** キャンバスの初期化
		MyPanel mc = new MyPanel();
		add(mc);				//フレームにキャンバスを追加
		setVisible(true);		//ウィンドウの表示
		//ゲームデータの初期化
		mc.init();
		//スレッドを作成
		mc.initThread();
	}
}
