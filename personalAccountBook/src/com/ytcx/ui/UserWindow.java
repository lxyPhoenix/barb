package com.ytcx.ui;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFrame.*;
import javax.swing.table.DefaultTableModel;

import com.ytcx.server.Operation;
import com.ytcx.util.Tool;
public class UserWindow extends JFrame{
	private final String str=null;
	private  String fileStr=null;
	Operation oper=null;
	

	private JTable table=new JTable();
	private DefaultTableModel dtm=new DefaultTableModel();
	Vector colName=new Vector();
	private JComboBox typeCom=new JComboBox();
	private JTextField moneyTxt=new JTextField();
	private JTextField remarkTxt=new JTextField();
	private String id;
	private String time;
	public UserWindow(String str,String fileStr){
		
		this.fileStr=fileStr;
		oper=new Operation(fileStr);
		setTitle("欢迎用户"+str+"使用记账本");
		this.setIconImage(new ImageIcon("note.png").getImage());
		setBounds(50,50,900,600);
		init();
		setVisible(true);
	}
	void init(){
		JScrollPane sp=new JScrollPane(setTable());
		final JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,sp,setInfo());
		jsp.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				jsp.setDividerLocation(0.5);
			}
		});
		this.add(jsp);
		//showUserImage=new ImageIcon(this.getClass().getResource("notes.png"));
		
		
	}

	public JTable setTable(){
		
		colName.add("流水号");
		colName.add("类型");
		colName.add("金额");
		colName.add("日期");
		colName.add("备注");
		Vector data=oper.select();
		dtm.setDataVector(data, colName);
		table.setModel(dtm);
		
		table.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				id=dtm.getValueAt(row, 0).toString();
				//测试语句System.out.println(id);
				String type=dtm.getValueAt(row, 1).toString();
				String money=dtm.getValueAt(row, 2).toString();
				String remark=dtm.getValueAt(row, 4).toString();
				typeCom.setSelectedItem(type);
				moneyTxt.setText(money);
				remarkTxt.setText(remark);
				time=dtm.getValueAt(row, 3).toString();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		return table;
	}
	/*public static void main(String[] args) {
		new UserWindow();
	}*/
	public JPanel setInfo(){
		JPanel jp=new JPanel();
		jp.setLayout(null);
		
		JLabel label1=new JLabel("类型");
		label1.setBounds(20, 50, 50, 30);
		jp.add(label1);
		typeCom.addItem("收入");
		typeCom.addItem("支出");
		typeCom.setBounds(80,50,60,30);
		
		JLabel label2=new JLabel("金额");
		label2.setBounds(200,50,50,30);
		jp.add(label2);
		moneyTxt.setBounds(250,50,100,30);
		
		JLabel label3=new JLabel("备注");
		label3.setBounds(380,50,50,30);
		jp.add(label3);
		remarkTxt.setBounds(420,50,100,30);
		
		JButton button1=new JButton("修改");
		button1.setBounds(50, 100, 100, 50);
		button1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String type=typeCom.getSelectedItem().toString();
				String money=moneyTxt.getText();
				String remark=remarkTxt.getText();
				Tool tool=new Tool();
				String time=tool.getTime();
				oper.update(id, type, money, time, remark);
				/*
				 * 表格刷新
				 */
				Vector dataSel=oper.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		JButton button2=new JButton("增加");
		button2.setBounds(150, 100, 100, 50);
		button2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String type=typeCom.getSelectedItem().toString();
				String money=moneyTxt.getText();
				String remark=remarkTxt.getText();
				Tool tool=new Tool();
				int id=tool.getNewId();
				String time=tool.getTime();
				String data=id+" "+type+" "+money+" "+time+" "+remark+"\n";
				oper.add(data);
				/*
				 * 表格刷新
				 */
				Vector dataSel=oper.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JButton button3=new JButton("删除");
		button3.setBounds(250, 100, 100, 50);
		button3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				oper.delete(id);
				/*
				 * 表格刷新
				 */
				
				Vector dataSel=oper.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		jp.add(remarkTxt);
		jp.add(moneyTxt);
		jp.add(typeCom);
		jp.add(button1);
		jp.add(button2);
		jp.add(button3);
		return jp;
	}
}













