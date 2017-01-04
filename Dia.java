import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Dia extends JFrame{
	
	JButton btn,btn2,btn3,btn4,btn5;
	JTextArea txa;

  //-----------------�H�U���غc�l--------------
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
	txa.setText("�п�ܱ��[�K����"+"\n");//�غc��������s

    }
//--------------�H�U�{���i�J�I-------------
	/**public void setT(){
		txa.append("�}�l�[�K...");
	}*/
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Dia app=new Dia();
	}
//--------------------�H�U�}��---------------------------

static class open implements ActionListener{
	static String fPath;
	public void actionPerformed(ActionEvent ae){ 
		JFileChooser fileChooser = new JFileChooser(); //�ŧifilechooser 
		int returnValue = fileChooser.showOpenDialog(null); //�s�Xfilechooser 
			if (returnValue == JFileChooser.APPROVE_OPTION){ 
			File selectedFile = fileChooser.getSelectedFile(); //������File 
			fPath = selectedFile.getAbsolutePath();
			System.out.println(fPath); //�L�X�ɦW 

			} 

	} 
	public static String getPath(){
		return fPath;
	}//�}���ɮ�
	
}
//--------------�H�U���}----------------------------------------
class ext implements ActionListener{
	public void actionPerformed(ActionEvent ae){ 
        System.exit(0);
        
	} 
	
}

//----------------�H�U���o�K�X-----------------------------------
static class pass implements ActionListener{
	JInternalFrame Dia;
	static String pass;
	public void actionPerformed(ActionEvent ae){ 
	    pass=JOptionPane.showInputDialog("�п�J�K�X:");  
	    
	} 
	public static String getPass(){
		return pass;
	}

}
 //----------------�H�U�[�ѱK--------------------------
static class lock implements ActionListener{
	
	static int kar[];
	static int nct[];
	public void actionPerformed(ActionEvent ae){ 		
        String fPath =open.getPath();
        String key = pass.getPass();
        kar = new int[key.length()];
        for ( int i = 0; i < key.length(); i++ ){ //PWto��ư}�C
        char c = key.charAt( i ); 
        kar[i] = (int) c; 
        }

        try {
			FileReader fr= new FileReader(fPath);
			BufferedReader br=new BufferedReader(fr);
			String line;
			String newContent="";

			while((line = br.readLine()) != null){  //�v��Ū�J�ɮפ��e

			  newContent = newContent+line+"\n";  //�Q��String��replace() Method�N����Ÿ�"\n"�m�����Ŧr��
		      
			}

			FileWriter fw = new FileWriter(fPath);  //new �@�ӭ쥻�ɮת�FileWriter����
			//fw.write(newContent);  //�N�m���L���ɮפ��e�g�^
			//��������
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