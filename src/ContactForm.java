
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

public class ContactForm{
	// gbcの初期化
	public static GridBagConstraints GbcSetPosition(int pos_x, int pos_y, Insets margin, int layout) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = pos_x;
		gbc.gridy = pos_y;
		gbc.gridwidth = 3;
		gbc.insets = margin;
		gbc.anchor = layout;
		
		return gbc;
	}
	
	public static GridBagConstraints GbcSetPosition(int pos_x, int pos_y, Insets margin, int layout, double weight) {
		GridBagConstraints gbc = GbcSetPosition(pos_x,pos_y,margin,layout);
		gbc.gridwidth = 1;
		gbc.weightx = weight;
		return gbc;
	}
	public static GridBagConstraints GbcSetPosition(int pos_x, int pos_y, Insets margin,int height, int width) {
		GridBagConstraints gbc = GbcSetPosition(pos_x,pos_y,margin,GridBagConstraints.CENTER);
		gbc.gridheight = height;
		gbc.gridwidth = width;
		gbc.weightx = width;
		return gbc;
	}
	
	public static void main(String[] args) {
		
		/*
		 * テーブルの作成
		 */
		FormTable client_table = new FormTable(0,0);

		/*
		 * 入力エリアを作成
		 */
		Map<String, FormText> area_map = new HashMap<>() {{
			put("name_area",new FormText(20,1,3));
			put("phone_area",new FormText(20,1,5));
			put("mail_area",new FormText(20,1,7));
		}};

		/*
		 * ラベルを作成
		 */
		Map<String, FormLabel> label_map = new HashMap<>() {{
			put("id_label",new FormLabel("ID", 1,0));
			put("id_area",new FormLabel("xxxx", 1,1,10));
			put("name_label",new FormLabel("名前", 1,2));
			put("phone_label",new FormLabel("電話番号", 1,4));
			put("mail_label",new FormLabel("メールアドレス", 1,6));
		}};
		
		/*
		 * ボタンの作成		
		 */
		Map<String, FormButton> btn_map = new HashMap<>() {{
			put("new_button",new FormButton("新規", 1,8));
			put("edit_button",new FormButton("編集", 2,8));
			put("delete_button",new FormButton("削除", 3,8,30));
		}};

		// 新規をクリックした時の処理
		btn_map.get("new_button").setNewButton(client_table, args, label_map,area_map);

		// 削除をクリックした時の処理
		btn_map.get("delete_button").setDeleteBtn(client_table);
		
		//編集をクリックした時の処理	
		btn_map.get("edit_button").setEditBtn(client_table, label_map, area_map, btn_map);
		
		/*
		 *	ウィンドウの作成
		 */				
		FormWindow frame = new FormWindow("顧客管理ポートフォリオ",1280,720);
		
		//テーブルを配置		
		frame.add(client_table.getScrollPane(), client_table.getGbc());
		
		//　ラベルの配置
		for(Map.Entry<String, FormLabel> e : label_map.entrySet()) {
			frame.add(e.getValue(),e.getValue().getGbc());
		}
		
		// テキストフィールドの配置
		for(Map.Entry<String, FormText> e : area_map.entrySet()) {
			frame.add(e.getValue(),e.getValue().getGbc());
		}
		
		//　ボタンの配置
		for(Map.Entry<String, FormButton> e : btn_map.entrySet()) {
			frame.add(e.getValue(),e.getValue().getGbc());
		}
		
		//ウィンドウを表示
		frame.setVisible(true);

		
	}

	
}
