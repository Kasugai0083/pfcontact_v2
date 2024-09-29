import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * FormTable
 * テーブルの描画とデータを管理するクラス
 */

public class FormTable extends JTable{
	private GridBagConstraints gbc;
	private FormData csv_data = new FormData();
	private DefaultTableModel table_model = new DefaultTableModel(csv_data.getClientData(), csv_data.getClientTags());
	private JScrollPane scrollPane;
	
	/*
	 * コンストラクタ
	 * テーブルの初期化と座標指定
	 * pos_x => x座標を指定
	 * pos_y => y座標を指定
	 */
	FormTable(int pos_x, int pos_y){
		scrollPane = new JScrollPane(this);
		super.setModel(table_model);
		
		// テーブルの直接編集を無効化
		super.setDefaultEditor(Object.class, null);
		
		// テーブルの複数選択を不可に変更
		super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		gbc = ContactForm.GbcSetPosition(pos_x,pos_y,new Insets(10,30,10,10),10,1);		
	}
	
	/*
	 * saveToCSV
	 * テーブルの値をCSVに入力
	 * table => 値を参照するテーブルを指定
	 */
	public void saveToCSV(FormTable table) {
		csv_data.saveToCSV(table);
	}
	
	/*
	 * tableAddRow
	 * テーブルの末尾に行を追加
	 * args => mainのargsを指定
	 */
	public void tableAddRow(String[] args) {
		table_model.addRow(args);
	}
	
	/*
	 * getCurrentRow
	 * 現在のテーブル行数を取得
	 */
	public int getCurrentRow() {
		return table_model.getRowCount();
	}
	
	/*
	 * getScrollPane
	 * テーブルのオブジェクト情報を取得
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	/*
	 * getGbc
	 * 座標データを取得
	 */
	public GridBagConstraints getGbc() {
		return gbc;
	}
	
	/*
	 * selectedDeleteRow
	 * 選択されている行を削除
	 */
	public void selectedDeleteRow() {
		table_model.removeRow(this.getSelectedRow());
	}
	
}
