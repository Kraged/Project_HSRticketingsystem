import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import javax.swing.*;


@SuppressWarnings("serial")
public class HSR_main extends JFrame implements ActionListener, MouseListener {
	
		static HSR_main mp = new HSR_main();
		static Container cr = mp.getContentPane();	//主頁
		static Container bk = mp.getContentPane();	//訂票
		static Container ad = mp.getContentPane();	//管理
		static Container pr = mp.getContentPane();	//票價查詢
		static Container rev = mp.getContentPane();	//查詢訂單
		static JPanel mpe = new JPanel();			
		static JLabel title = new JLabel();
		
		static JLabel lone = new JLabel();
		static JLabel lonp = new JLabel();
		static TextField nef = new TextField();
		static JPasswordField pwf = new JPasswordField();
		static JButton login = new JButton("登入");
		static JButton booking = new JButton("線上訂票");
		static JButton checkm = new JButton("票價查詢");
		static JButton timete = new JButton("班次表");
		
		static String[] stops = new String[]{"南港","台北","板橋","桃園","新竹","苗栗","台中","彰化","雲林","嘉義","台南","左營","高雄"};
		static String[] carlt = new String[]{"08:00","10:00","12:00","14:00","16:00","18:00","20:00","22:00"};
		static String[] nu1 = new String[]{"一般","特別"};
		static String[] nu2 = new String[]{"1","2","3"};
		static JComboBox<String> start = new JComboBox<String>(stops);	//起點
		static JComboBox<String> destin = new JComboBox<String>(stops);	//終點
		static JComboBox<String> nuer = new JComboBox<String>(nu1);		//票數
		static JComboBox<String> nute = new JComboBox<String>(nu2);		//時間
		static JComboBox<String> telist = new JComboBox<String>(carlt);
		static JLabel nuber = new JLabel("票數: ");
		static JLabel dateo = new JLabel("日期: ");
		static JLabel datet = new JLabel("時間: ");
		static JButton buy = new JButton("訂票付款");
		static JButton reset = new JButton("重設選擇");
		static JButton back = new JButton("返回上頁");

		static JComboBox<String> inlist = new JComboBox<String>();	//班次查詢
		static JComboBox<String> onlist = new JComboBox<String>();	//終點查詢
		static JButton check1 = new JButton("查詢");
		static JButton check2 = new JButton("查詢");
		static JPanel train = new JPanel();							//內部_班次
		static JPanel time = new JPanel();							//內部_終點
		static JButton logout = new JButton("登出");
		
		static JLabel ber = new JLabel("訂單編號: ");
		static JLabel date = new JLabel("出發日期: ");
		static JLabel goz = new JLabel("出發時間: ");
		static JButton ed = new JButton("修改訂單");
		static JButton pt = new JButton("列印訂單");
		
		static Font cm = new Font("Courier", Font.CENTER_BASELINE, 20);
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		mp.setResizable(false);
		mainp();
//		mp.addWindowListener(new WindowAdapter(){
//			public void windowClosing(WindowEvent e){System.exit(0);}
//		});
		
	}
	
	static public void mainp(){
		
		//登入
		mp.setSize(750, 800);
		cr.setBackground(Color.lightGray);
		cr.setVisible(true);
		ad.setEnabled(false);
		bk.setEnabled(false);
		pr.setEnabled(false);
		rev.setEnabled(false);
		mp.setLayout(null);
		mp.setVisible(true);
		mp.setTitle("歡迎來到High Speed Railway訂票系統！");
		mp.add(title);
		title.setText("HSR 訂票系統");
		title.setFont(new Font("Courier", Font.BOLD, 30));
		title.setVisible(true);
		title.setBounds(300, 50, 500, 80);
		lone.setText("帳號/訂票號碼");
		lone.setFont(cm);
		lonp.setText("密碼/電話");
		lonp.setFont(cm);
		nef.setFont(cm);
		pwf.setFont(cm);
		lone.setBounds(100,250,150,30);
		lonp.setBounds(100,290,150,30);
		nef.setBounds(250, 255, 150, 30);
		pwf.setBounds(250, 295, 150, 30);
		login.setBounds(420, 260, 100, 60);
		booking.setBounds(100, 400, 160, 70);
		checkm.setBounds(100, 500, 160, 70);
		timete.setBounds(100, 600, 160, 70);
				
		mp.add(lone);
		mp.add(lonp);
		mp.add(pwf);
		mp.add(nef);
		mp.add(login);
		mp.add(booking);
		mp.add(checkm);
		mp.add(timete);
		
		login.addActionListener(mp);
		booking.addActionListener(mp);
		checkm.addActionListener(mp);
		timete.addActionListener(mp);		
	}
	
	
	static public void bk_tk(){
		bk.setEnabled(true);
		bk.setBackground(Color.yellow);
		bk.setVisible(true);
		title.setText("請選擇：");		
		lone.setText("請選擇起點: ");
		lonp.setText("請選擇終點: ");
		start.setBounds(250, 255, 100, 30);
		start.setFont(cm);
		destin.setBounds(250, 295, 100, 30);
		destin.setFont(cm);
		start.setMaximumRowCount(4);
		destin.setMaximumRowCount(4);
		nuber.setBounds(100, 330, 100, 30);
		nuber.setFont(cm);
		nuer.setBounds(250, 330, 100, 30);
		nuer.setFont(cm);
		nute.setBounds(360, 330, 50, 30);
		nute.setFont(cm);
		dateo.setBounds(100, 370, 100, 30);
		datet.setBounds(360, 370, 100, 30);
		dateo.setFont(cm);
		datet.setFont(cm);
		telist.setBounds(420, 370, 100, 30);
		telist.setFont(cm);
		buy.setBounds(140, 500, 130, 60);
		reset.setBounds(280, 500, 130, 60);
		back.setBounds(420, 500, 130, 60);
		
		bk.add(buy);
		bk.add(reset);
		bk.add(back);
		bk.add(telist);
		bk.add(dateo);
		bk.add(datet);
		bk.add(nute);
		bk.add(nuer);
		bk.add(nuber);
		bk.add(title);
		bk.add(lone);
		bk.add(lonp);
		bk.add(start);
		bk.add(destin);
		
		reset.addActionListener(mp);
		back.addActionListener(mp);

	}
	
	static public void admi(){
		ad.setEnabled(true);
		ad.setBackground(Color.MAGENTA);
		ad.setVisible(true);
		title.setText("內部系統處理");
		title.setBounds(100, 20, 500, 80);
		lone.setText("請選擇班次: ");
		lonp.setText("請選擇終點: ");
		lone.setBounds(100,100,150,30);
		lonp.setBounds(100,140,150,30);
		inlist.setBounds(250, 100, 150, 30);
		onlist.setBounds(250, 140, 150, 30);
		check1.setBounds(410, 100, 100, 30);
		check2.setBounds(410, 140, 100, 30);
		logout.setBounds(600, 40, 80, 50);
		
		ad.add(logout);
		ad.add(check1);
		ad.add(check2);
		ad.add(onlist);
		ad.add(inlist);
		ad.add(title);
		ad.add(lone);
		ad.add(lonp);
		ad.add(train);
		ad.add(time);
		
		train.setBounds(100, 300, 500, 400);
		time.setBounds(100, 300, 500, 400);
		train.setVisible(false);
		time.setVisible(false);
		check1.addActionListener(mp);
		check2.addActionListener(mp);
		logout.addActionListener(mp);
		
	}
	
	static public void revisit(){
		rev.setEnabled(true);
		rev.setBackground(Color.green);
		rev.setVisible(true);
		title.setText("查詢訂單");
		title.setBounds(100, 20, 500, 80);
		lone.setText("您的班次: ");
		lonp.setText("您的終點: ");
		lone.setBounds(100,100,150,30);
		lonp.setBounds(100,140,150,30);
		ber.setBounds(100, 180, 150, 30);
		ber.setFont(cm);
		date.setBounds(100, 220, 150, 30);
		date.setFont(cm);
		goz.setBounds(100, 260, 150, 30);
		goz.setFont(cm);
		inlist.setBounds(250, 100, 150, 30);
		onlist.setBounds(250, 140, 150, 30);
		check1.setBounds(410, 100, 100, 30);
		check2.setBounds(410, 140, 100, 30);
		logout.setBounds(600, 40, 80, 50);
		
		rev.add(date);
		rev.add(goz);
		rev.add(lone);
		rev.add(lonp);
		rev.add(ber);
		rev.add(title);
		rev.add(ed);
		rev.add(pt);
		rev.add(logout);
		
		ed.setBounds(140, 350, 130, 60);
		pt.setBounds(280, 350, 130, 60);
		logout.setBounds(420, 350, 130, 60);
		logout.addActionListener(mp);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == booking)
		{
			cr.removeAll();
			cr.setEnabled(false);
			bk_tk();
		}
		if (e.getSource() == login)
		{
			cr.removeAll();
			cr.setEnabled(false);
//			boolean psg = Pattern.matches("^[0-9]*$", nef.getText());
			if (nef.getText().contains("0"))
			{
				revisit();
			}
			else
			{
				admi();
			}
		}
		if (e.getSource() == reset)
		{
			
			start.setSelectedIndex(0);
			destin.setSelectedIndex(0);
			nuer.setSelectedIndex(0);
			nute.setSelectedIndex(0);
			telist.setSelectedIndex(0);
		}
		if ((e.getSource() == back) || (e.getSource() == logout))
		{
			bk.removeAll();
			bk.setEnabled(false);
			rev.removeAll();
			rev.setEnabled(false);
			mainp();
		}
		if (e.getSource() == check1)
		{
			train.setVisible(true);
			time.setEnabled(false);

		}
		if (e.getSource() == check2)
		{
			time.setVisible(true);
			train.setEnabled(false);
		}
	}

}
