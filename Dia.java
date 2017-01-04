import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Dia extends JFrame{
	
	JButton btn,btn2,btn3,btn4,btn5;
	JTextArea txa;

  //-----------------以下為建構子--------------
    public Dia(){
    super("demo");
    setBounds(600, 400, 600, 500);
    btn = new JButton("Lock");
    btn2 = new JButton("UnLock");
    btn3 = new JButton("Open");
    btn4 = new JButton("Exit");
    btn5 = new JButton("SetPass");
    
    txa = new JTextArea();
	btn.setBounds(0, 400, 150, 30);
	btn2.setBounds(150, 400, 150, 30);
	btn5.setBounds(300, 400, 300, 30);
	btn3.setBounds(0, 430, 300, 30);
	btn4.setBounds(300, 430, 300, 30);
	txa.setEditable(false); 
	txa.setBounds(0, 0, 600, 400);
    setLayout(null);
	add(btn);
	add(btn2);
	add(btn3);
	add(btn4);
	add(btn5);
	add(txa);
	txa.setBounds(0, 0, 600, 400);
	setBounds(600, 400, 600, 500);
	btn.addActionListener(new lock());
	btn2.addActionListener(new lock());
	btn3.addActionListener(new open());
	btn4.addActionListener(new ext());
	btn5.addActionListener(new pass());
	setVisible(true);
	txa.setText("請選擇欲加密文檔"+"\n");//建構視窗跟按鈕

    }
//--------------以下程式進入點-------------
	/**public void setT(){
		txa.append("開始加密...");
	}*/
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Dia app=new Dia();
	}
//--------------------以下開檔---------------------------

static class open implements ActionListener{
	static String fPath;
	public void actionPerformed(ActionEvent ae){ 
		JFileChooser fileChooser = new JFileChooser(); //宣告filechooser 
		int returnValue = fileChooser.showOpenDialog(null); //叫出filechooser 
			if (returnValue == JFileChooser.APPROVE_OPTION){ 
			File selectedFile = fileChooser.getSelectedFile(); //指派給File 
			fPath = selectedFile.getAbsolutePath();
			System.out.println(fPath); //印出檔名 

			} 

	} 
	public static String getPath(){
		return fPath;
	}//開啟檔案
	
}
//--------------以下離開----------------------------------------
class ext implements ActionListener{
	public void actionPerformed(ActionEvent ae){ 
        System.exit(0);
        
	} 
	
}

//----------------以下取得密碼-----------------------------------
static class pass implements ActionListener{
	JInternalFrame Dia;
	static String pass;
	public void actionPerformed(ActionEvent ae){ 
	    pass=JOptionPane.showInputDialog("請輸入密碼:");  
	    
	} 
	public static String getPass(){
		return pass;
	}

}
 //----------------以下加解密--------------------------
static class lock implements ActionListener{
	
	static int kar[];
	static int nct[];
	public void actionPerformed(ActionEvent ae){ 		
        String fPath =open.getPath();
        String key = pass.getPass();
        kar = new int[key.length()];
        for ( int i = 0; i < key.length(); i++ ){ //PWto整數陣列
        char c = key.charAt( i ); 
        kar[i] = (int) c; 
        }

        try {
			FileReader fr= new FileReader(fPath);
			BufferedReader br=new BufferedReader(fr);
			String line;
			String newContent="";

			while((line = br.readLine()) != null){  //逐行讀入檔案內容

			  newContent = newContent+line+"\n";  //利用String的replace() Method將分行符號"\n"置換成空字串
		      
			}

			FileWriter fw = new FileWriter(fPath);  //new 一個原本檔案的FileWriter物件
			//fw.write(newContent);  //將置換過的檔案內容寫回
			//關閉物件
	        nct = new int[newContent.length()];	 
	        int cnt = 0;
	        
	        for ( int i = 0; i < newContent.length(); i++ ){
	            char c = newContent.charAt( i ); 	           
	            nct[i] = kar[cnt]^(int) c;
	            cnt++;
	            if(cnt==key.length())
	            	cnt = 0;
	            }
	        
	        String afterTran = "";
	        
	        for ( int i = 0; i < newContent.length(); i++ ){
	        	afterTran = afterTran+new Character((char)nct[i]).toString();
	        }
	        System.out.println(afterTran);
	        fw.write(afterTran);
			br.close(); 
			fr.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}            
	} 
	
	 public static int[] getParr(){     	

     	return kar;
     }	 
	 
	 public static int[] getCarr(){     	
	    return nct;
	 }
}
}