import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;

/*
 * FormLabel
 * ラベルの情報を管理するクラス
 */

public class FormLabel extends Label{
	private GridBagConstraints gbc;
	
	/*
	 * コンストラクタ
	 * ラベルの名前と座標を指定して初期化する
	 * name => ラベルの名前を指定
	 * pos_x => x座標を指定
	 * pos_y => y座標を指定
	 * mgb => オブジェクト下部の空白を指定
	 */
	FormLabel(String name,int pos_x,int pos_y){
		super(name);
		gbc = ContactForm.GbcSetPosition(pos_x,pos_y,new Insets(0,0,0,30),GridBagConstraints.WEST);		
	}
	FormLabel(String name,int pos_x,int pos_y, int mgb){
		super(name);
		gbc = ContactForm.GbcSetPosition(pos_x,pos_y,new Insets(0,0,mgb,30),GridBagConstraints.WEST);		
	}
	
	
	/*
	 * getGbc
	 * 座標を取得
	 */
	GridBagConstraints getGbc(){
		return gbc;
	}
	
}
