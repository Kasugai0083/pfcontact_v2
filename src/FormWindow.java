import java.awt.GridBagLayout;

import javax.swing.JFrame;

/*
 * FormWindow
 * アプリケーションのウィンドウを管理するクラス
 */

public class FormWindow extends JFrame{
	
	/*
	 * コンストラクタ
	 * 
	 *  title => アプリのタイトルを指定
	 *  w_width => ウィンドウの幅を指定
	 *  w_height => ウィンドウの高さを指定
	 */
	FormWindow(String title,int w_width, int w_height){
		// タイトルを指定		
		super(title);
		
		// ウィンドウサイズを指定		
		this.setSize(w_width, w_height);
		
		// ウィンドウのリサイズを無効化
		this.setResizable(false);
		
		//ウィンドウをモニターの中央に配置
		this.setLocationRelativeTo(null);
		
		//ウィンドウを閉じたらプログラムを終了
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// レイアウトをGridBagLayoutで初期化
		this.setLayout(new GridBagLayout());
	}
	
	
	
}
