import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

/*
 * FormText
 * JTextFieldの内容に加えて、座標情報を管理
 */

public class FormText extends JTextField{
	private GridBagConstraints gbc;
	
	/*
	 * コンストラクタ
	 * フィールドの長さと座標を指定して初期化する
	 * num => フィールドの長さを指定
	 * pos_x => x座標を指定
	 * pos_y => y座標を指定
	 */
	FormText(int num, int pos_x, int pos_y){
		super(num);
		gbc = ContactForm.GbcSetPosition(pos_x,pos_y,new Insets(0,0,10,30),GridBagConstraints.WEST);		
	}
	
	/*
	 * getGbc
	 * 座標の情報を取得
	 */
	public GridBagConstraints getGbc() {
		return gbc;
	}
}
