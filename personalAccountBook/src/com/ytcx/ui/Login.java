package com.ytcx.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame {
    //����һ�����б���ͼƬ�����
	private String userName=null;
	private String fileNames=null;
    private MyPanel panel;
    private JLabel lab_zhanghao = new JLabel("�˺�:");
    private JTextField jta_text = new JTextField();
    Font lab = new Font("����",1,30);
    private JLabel lat_password = new JLabel("����:");
    Font lat  = new Font("����",1,30);
    private JPasswordField jtb_text = new JPasswordField();
    private JButton btn_register = new JButton("ע��");
    private JButton btn_land = new JButton("��½");
    Font btn = new Font("����",2,30);
    public static  int pd = 0;
    public static String ak1, ak2;
    private Container contentPane = this.getContentPane();
    public String getUserName(){
    	return userName;
    }
    public Login () {
        this.setSize(400,267);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("note.png").getImage());
        this.setTitle("��½");
        this.setResizable(false);
        btn_register.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
                pd = 1;
                ak1 = jta_text .getText();
                ak2 = jtb_text .getText();
                String fileName = "users.dat";
                try {
                    FileWriter writer = new FileWriter(fileName,true);
                    BufferedWriter outWriter=new BufferedWriter(writer);
                    outWriter.write("ID:"+ak1+"&&"+"Password:"+ak2);
                    outWriter.newLine();
                    outWriter.close();
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                jta_text.setText("");
                jtb_text.setText("");
                JOptionPane.showMessageDialog(null,"ע��ɹ��� �����û����ǣ�"+ak1+"�����ǣ�"+ ak2);
            }
        });
        btn_land.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
            	
                if(pd==1||pd==0) {
                	File fileName=new File("users.dat");
                	try{
                	FileReader inOne=new FileReader(fileName);
                	BufferedReader inTwo=new BufferedReader(inOne);
                	String s=null;
                	int judge=1;
                	while((s=inTwo.readLine())!=null)
                	{
                	  StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append("ID:");
						stringBuilder.append(jta_text.getText());
						stringBuilder.append("&&");
						stringBuilder.append("Password:");
						stringBuilder.append(jtb_text.getText());
					String ak=stringBuilder.toString();
                	  
                	  
                      if(s.equals(ak)) {
                        JOptionPane.showMessageDialog(null, "��¼�ɹ���");
                        fileNames =jta_text.getText()+jtb_text.getText()+".txt";
                        userName=jta_text.getText();
                        jta_text .setText("");
                        jtb_text .setText("");
                        judge=0;
                        
                        break;
                      }
                	}  
                	inTwo.close();
                    inOne.close();
                	if(judge==1) {
                         JOptionPane.showMessageDialog(null,"��½ʧ�ܣ�����˺������Ƿ���ȷ����ע����¼",null,JOptionPane.ERROR_MESSAGE, null);
                         jta_text .setText("");
                         jtb_text .setText("");
                      }
                	 if(judge==0){
                		 new UserWindow(userName,fileNames);
                		 dispose(); 
                		 return; 
                	 }
                	}catch(IOException event){
                		JOptionPane.showMessageDialog(null,"ϵͳ����"+event,null,JOptionPane.ERROR_MESSAGE, null);
                	}
                }
            }
 
        });
         
        init();
         
        panel = new MyPanel();
        //�����е������ӵ�panel�����
        panel.add(lab_zhanghao);
        panel.add(lat_password);
        panel.add(jta_text);
        panel.add(jtb_text);
        panel.add(btn_register);
        panel.add(btn_land);
        panel.setLayout(null);
         
        getContentPane().add(panel);
    }
    private void init() {
        lab_zhanghao.setSize(200,100);
        lab_zhanghao. setLocation(50,20);
        lab_zhanghao.setFont(lab);
        lat_password.setSize(200,100);
        lat_password.setLocation(50,80);
        lat_password.setFont(lat);
        jta_text.setSize(190,30);
        jta_text.setLocation(160,55);
        jtb_text.setSize(190,30);
        jtb_text.setLocation(160,115);
        btn_register.setSize(120, 40);
        btn_register.setLocation(60, 180);
        btn_land.setSize(120, 40);
        btn_land.setLocation(230, 180);
        btn_register.setFont(btn);
        btn_land.setFont(btn);
    }
     
    public static void main(String[] args)
    throws ClassNotFoundException,
        InstantiationException, IllegalAccessException,
        UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        new Login().setVisible(true);
    }
}