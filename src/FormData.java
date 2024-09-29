import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * FormData
 * CSVのデータを入出力するクラス
 */

public class FormData {
	
	private Vector<Vector<String>> main_data;
	private Vector<Vector<String>> client_data;
	private Vector<String> client_tags;
	
	/*
	 * コンストラクタ
	 * CSVのファイル読み込み
	 * ヘッダー => client_tags に代入
	 * 本文 => client_data に代入
	 */
	FormData(){
		main_data = readCsv(new File("csv/data.csv"));
		client_data = new Vector<>(main_data.subList(1, main_data.size()));
		client_tags = main_data.get(0);
	}
	
	
	/*
	 * getClientData
	 * CSV本文を取得するゲッター
	 */
	public Vector<Vector<String>> getClientData() {
		return client_data;
	}
	
	/*
	 * getClientTags
	 * CSVヘッダーを取得するゲッター
	 */
	public Vector<String> getClientTags(){
		return client_tags;
	}
	
	
	/*
	 * readCsv
	 * CSVを読み込む関数
	 * f => 読み込みファイル(csv)を指定
	 */
	public Vector<Vector<String>> readCsv(File f){
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		try {
			
			FileInputStream s = new FileInputStream(f);
			InputStreamReader r = new InputStreamReader(s, "UTF-8");
			BufferedReader br = new BufferedReader(r);
			
			String line;
			
			while((line = br.readLine()) != null) {
				line = line.substring(0, line.length());
				String[] ary = line.split(",");
				Vector<String> v = new Vector<String>();
				for(String cell : ary) {
					v.add(cell);
				}
				data.add(v);
			}
			br.close();
			r.close();
			s.close();
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		return data;
	}
	
	/*
	 * saveToCSV
	 * テーブルの値をCSVに保存するクラス
	 * table => アプリケーションに描画されているtableを指定
	 */
	public void saveToCSV(JTable table) {
		try {
			
			// ファイルを指定
			FileWriter csv = new FileWriter("csv/data.csv");
			
			// テーブルモデルを取得
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
            // ヘッダーを書き込み
            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + (i < model.getColumnCount() - 1 ? "," : ""));
            }
            csv.write("\n");

            // 行データを書き込み
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString() + (j < model.getColumnCount() - 1 ? "," : ""));
                }
                csv.write("\n");
            }
			
			
			csv.close();
			System.out.println("CSVファイルに保存されました！");
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("ファイルの書き込みに失敗しました。");
		}
	}
}
