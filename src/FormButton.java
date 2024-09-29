import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * FormButton
 * ボタンの実行時処理や座標を管理
 */

public class FormButton extends JButton{
	private GridBagConstraints gbc;
	
	/*
	 * コンストラクタ
	 * ボタン名と座標を指定
	 * name => ボタンの名前を指定
	 * pos_x => x座標を指定
	 * pos_y => y座標を指定
	 * mg_r => オブジェクト右側の余白を指定
	 */
	FormButton(String name,int pos_x, int pos_y){
		super(name);
		gbc = ContactForm.GbcSetPosition(pos_x,pos_y,new Insets(0,0,0,0),GridBagConstraints.WEST,0.3);		
	}
	FormButton(String name,int pos_x, int pos_y, int mg_r){
		super(name);
		gbc = ContactForm.GbcSetPosition(pos_x,pos_y,new Insets(0,0,0,mg_r),GridBagConstraints.WEST,0.3);		
	}
	
	/*
	 * getGbc
	 * 座標を取得
	 */
	public GridBagConstraints getGbc() {
		return gbc;
	}
	
	/*
	 * setNewButton
	 * 新規ボタンの処理を追加
	 * テーブルに新しい行を追加して、テキストフィールドの値を代入する
	 * 最後に変更をCSVに保存する
	 * 
	 * table => 行を追加するテーブルを指定
	 * args => mainのargsを指定
	 * labels => ラベルのオブジェクトを指定
	 * areas => テキストフィールドのオブジェクトを指定
	 */
	public void setNewButton(FormTable table, String[] args, Map<String, FormLabel> labels, Map<String, FormText> areas) {
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// テキストフィールドが空の時
				if(areas.get("name_area").getText().isEmpty() || areas.get("phone_area").getText().isEmpty() || areas.get("mail_area").getText().isEmpty()) {
					// ポップアップを表示
					JOptionPane.showMessageDialog(null, "項目をすべて入力してください。");
				}else {
					// 行の追加
					table.tableAddRow(args);

					// 現在の行数を取得
					int current_row = table.getCurrentRow() - 1; 
					
					// テキストフィールドの値を追加した行に代入
					table.setValueAt(current_row, current_row, 0); // id
					table.setValueAt(areas.get("name_area").getText(), current_row, 1); // 名前
					table.setValueAt(areas.get("phone_area").getText(), current_row, 2); // 電話
					table.setValueAt(areas.get("mail_area").getText(), current_row, 3); // メール
					
					// テキストフィールドを初期値に戻す
					labels.get("id_area").setText("xxxx");
					areas.get("name_area").setText(null);
					areas.get("phone_area").setText(null);
					areas.get("mail_area").setText(null);
					
					// csvを保存
					table.saveToCSV(table);
				}
				
			}
		});	
	}
	
	/*
	 * setDeleteBtn
	 * ボタンに削除処理を追加
	 * 選択された行を削除 削除後IDを降りなおす
	 * 最後に内容をCSVに保存する
	 * 
	 * table => 行削除を行うテーブルを指定
	 */
	public void setDeleteBtn(FormTable table) {
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// アクティブな行がない場合、処理しない
				if(table.getSelectedRow() != -1) {
					// 選択された行の削除
					table.selectedDeleteRow();
					
					// 現在の行数を取得
					int current_row = table.getCurrentRow(); 
					
					// idの振り直し
					for(int i = 0; i < current_row; i++) {
						table.setValueAt(i, i, 0);
					}
					
					// csvを保存
					table.saveToCSV(table);
				}else {
					// ポップアップを表示
					JOptionPane.showMessageDialog(null, "削除する行を選択してください。");
				}
			}
		});
	}
	
	/*
	 * setEditBtn
	 * ボタンに編集処理を追加
	 * ターブルの選択された行から値を取得し、テキストフィールドに出力
	 * 2回目のボタン操作で、テキストフィールドの値をテーブルに入力する
	 * 最後にCSVにテーブルの値を保存する
	 * 
	 * table => 編集したいテーブルを指定
	 * labels => ラベルのオブジェクトを指定
	 * areas => テキストフィールドのオブジェクトを指定
	 * btns => ボタンのオブジェクトを指定
	 */
	public void setEditBtn(FormTable table,Map<String, FormLabel> labels,Map<String, FormText> areas, Map<String, FormButton> btns) {
		this.addActionListener(new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 編集ボタン1回目の処理
				if(count == 0) {
					// アクティブな行がない場合、処理しない
					if(table.getSelectedRow() != -1) {
						// アクティブな行の値を取得
						Object active_id = table.getValueAt(table.getSelectedRow(), 0);
						Object active_name = table.getValueAt(table.getSelectedRow(), 1);
						Object active_phone = table.getValueAt(table.getSelectedRow(), 2);
						Object active_mail = table.getValueAt(table.getSelectedRow(), 3);
						
						// 入力欄に内容を反映
						labels.get("id_area").setText(active_id.toString());
						areas.get("name_area").setText(active_name.toString());
						areas.get("phone_area").setText(active_phone.toString());
						areas.get("mail_area").setText(active_mail.toString());
						
						// 編集以外のボタンを無効化する
						btns.get("new_button").setEnabled(false);
						btns.get("delete_button").setEnabled(false);
						count++;
					}else{
						// ポップアップを表示
						JOptionPane.showMessageDialog(null, "編集する行を選択してください。");
					};	

				// 編集ボタン再実行時
				}else {
					
					// テキストフィールドの値をアクティブなセルに代入
					table.setValueAt(labels.get("id_area").getText(), table.getSelectedRow(), 0); // id
					table.setValueAt(areas.get("name_area").getText(), table.getSelectedRow(), 1); // 名前
					table.setValueAt(areas.get("phone_area").getText(), table.getSelectedRow(), 2); // 電話番号
					table.setValueAt(areas.get("mail_area").getText(), table.getSelectedRow(), 3); // メール

					// テキストフィールドを初期値に戻す
					labels.get("id_area").setText("xxxx");
					areas.get("name_area").setText(null);
					areas.get("phone_area").setText(null);
					areas.get("mail_area").setText(null);
					
					// 編集以外のボタンを有効化する
					btns.get("new_button").setEnabled(true);
					btns.get("delete_button").setEnabled(true);
					count = 0;
					
					// csvを保存
					table.saveToCSV(table);
				}
			}
		});
	}
	
}
