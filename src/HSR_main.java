import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class HSR_main extends JFrame implements ActionListener, MouseListener {
	
		static HSR_main mp = new HSR_main();
		static Container cr = mp.getContentPane();	//主頁
		static Container bk = mp.getContentPane();	//訂票
		static Container ad = mp.getContentPane();	//管理
		static Container pr = mp.getContentPane();	//票價查詢
		static Container rev = mp.getContentPane();	//查詢訂單
		static Container cte = mp.getContentPane();	//班次表
		static JPanel mpe = new JPanel();			
		static JLabel title = new JLabel();
		static JLabel about = new JLabel("關於");
		static JLabel aboutc = new JLabel();
		
		//主頁
		static JLabel lone = new JLabel();
		static JLabel lonp = new JLabel();
		static JTextField nef = new JTextField();
		static JPasswordField pwf = new JPasswordField();
		static JButton login = new JButton("登入");
		static JButton booking = new JButton("線上訂票");
		static JButton checkm = new JButton("票價查詢");
		static JButton timete = new JButton("班次表");
		static JLabel news = new JLabel("最新消息");
		static JLabel nnews = new JLabel("<html>2017/01/05<br>"
				+ "東海首創「吻恆超鐵訂票系統」正式開放！<br><br>"
				+ "2017/01/04<br>"
				+ "首創全車自由座列車，不怕沒有位子，只怕你不敢買票！<br><br>"
				+ "2017/01/04<br>"
				+ "目前只有線上訂票可享有特別票優惠喔！！親臨現場饒恕只有原價，不便之處，敬請原諒。<br><br>"
				+ "2017/01/03<br>"
				+ "全台首設訂票以簡潔風格列印訂單，為您節省油墨錢！</html>", SwingConstants.RIGHT);
		static Border bdd = BorderFactory.createLineBorder(Color.black, 1);
		static JLabel la = new JLabel(new ImageIcon("pic/hsr_cover.jpg"));

//        static ImageIcon background = new ImageIcon("pic/hsr_cover.PNG");
		
		//訂票頁
		static String[] stops = new String[]{"南港","台北","板橋","桃園","新竹","苗栗","台中","彰化","雲林","嘉義","台南","左營"};
		static String[] carlt = new String[]{"08:00","10:00","12:00","14:00","16:00","18:00","20:00","22:00"};
		static String[] nu1 = new String[]{"一般","特別"};
		static String[] nu2 = new String[]{"1","2","3"};
//		,"3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"
//		,"29日","30日","31日"
		static String[] month_d = new String[]{"1月","2月"};
		static String[] day_d = new String[]{"01日","02日","03日","04日","05日","06日","07日","08日","09日"
		        ,"10日","11日","12日","13日","14日","15日","16日","17日","18日","19日"
		        ,"20日","21日","22日","23日","24日","25日","26日","27日","28日"};
		static JComboBox<String> start = new JComboBox<String>(stops);	//起點
		static JComboBox<String> destin = new JComboBox<String>(stops);	//終點
		static JComboBox<String> nuer = new JComboBox<String>(nu1);		//票數
		static JComboBox<String> nute = new JComboBox<String>(nu2);		//時間
		static JComboBox<String> month = new JComboBox<String>(month_d);
		static JComboBox<String> day = new JComboBox<String>(day_d);
		static JComboBox<String> telist = new JComboBox<String>(carlt);
		static JLabel nuber = new JLabel("票數: ");
		static JLabel dateo = new JLabel("日期: ");
		static JLabel datet = new JLabel("時間: ");
		static JLabel putph = new JLabel("電話號碼: ");
		static JTextField phone = new JTextField();
		static JButton buy = new JButton("搜尋班次");
		static JButton buy1 = new JButton("確認購票");
		static JButton reset = new JButton("重設選擇");
		static JButton back = new JButton("返回上頁");
		static JLabel lc = new JLabel(new ImageIcon("pic/hsr_booking.jpg"));
		
		//管理/後台頁
		static String[] number = new String[] {"班次","101","103","105","107","112","114","116","118","201","203","205","207","212","214","216","218"};
		static JComboBox<String> inlist = new JComboBox<String>(number);	//班次查詢
//		static JComboBox<String> onlist = new JComboBox<String>();	//終點查詢
		static JButton check1 = new JButton("查詢");
//		static JButton check2 = new JButton("查詢");
		static JPanel train = new JPanel();							//內部_班次
//		static JPanel time = new JPanel();							//內部_終點
		static JButton logout = new JButton("登出");
		static JLabel le = new JLabel(new ImageIcon("pic/hsr_admin.jpg"));
		//後台訂票頁
			//Label train
		static JLabel train_no = new JLabel("班次號碼: ");
		static JLabel train_da = new JLabel("班次日期: ");
		static JLabel train_ta = new JLabel("班次時間: ");
		static JLabel train_seatleft = new JLabel("應繳金額: ");
		static JLabel phone_no = new JLabel("手提電話: ");		
		static JTextField train_no_dis = new JTextField();
		static JTextField train_da_dis = new JTextField();
		static JTextField train_ta_dis = new JTextField();
		static JTextField train_pr_dis = new JTextField();
		static JTextField phone_dis = new JTextField();		
		static JButton confirm_train = new JButton("確認");
		static JButton reset_train = new JButton("重設");
			//Label time
		static JLabel time_no = new JLabel("班次號碼: ");		
		static JTextField time_no_dis = new JTextField();
		static JTextField time_da_dis = new JTextField();
		static JTextField time_ta_dis = new JTextField();
		static JTextField time_pr_dis = new JTextField();
		static JComboBox<String> time_sele_no = new JComboBox<String>();		
		static JButton confirm_time = new JButton("確認");
		
		//查看訂單頁
		static JLabel ber = new JLabel("訂單編號: ");
		static JLabel date = new JLabel("出發日期: ");
		static JLabel goz = new JLabel("出發時間: ");
		static JLabel cancel = new JLabel("已取消！");
		static JButton ed = new JButton("取消訂單");
		static JButton pt = new JButton("列印訂單");
		static JLabel bank = new JLabel();//班次號碼
		static JLabel monk = new JLabel();//要付多少
		static JLabel berk = new JLabel();//訂單編號
		static JLabel datk = new JLabel();//日期
		static JLabel gozk = new JLabel();//時間
		static JLabel lf = new JLabel(new ImageIcon("pic/hsr_pricebg.jpg"));
		
		//查票價頁
		static JLabel lb = new JLabel(new ImageIcon("pic/pricetable.PNG"));
		static JLabel lg = new JLabel(new ImageIcon("pic/hsr_pricebg.jpg"));
		//班次頁
		static JLabel ld = new JLabel(new ImageIcon("pic/classtable.png"));
		static JLabel lh = new JLabel(new ImageIcon("pic/hsr_classbg.jpg"));

		//列印頁
		static JFrame mp1 = new JFrame();
		static JFrame mp2 = new JFrame();
		static JFrame mp3 = new JFrame();
		static JLabel mystart = new JLabel();
		static JLabel myend = new JLabel();
		static JLabel mytrno = new JLabel();
		static JLabel mytyno = new JLabel();
		static JLabel myttno = new JLabel();
		static JLabel mystday = new JLabel();
		static JLabel mysttime = new JLabel();
		static JLabel myedtime = new JLabel();
		static JLabel myno = new JLabel();
		static JLabel myph = new JLabel();
				
		static Font cm = new Font("Courier", Font.CENTER_BASELINE, 20);
		static Font cn = new Font("Microsoft JhengHei", Font.BOLD, 25);
		
		static String url = "jdbc:mysql://localhost:3306/";
	    static String user = "root";
	    static String password = "";
		
		static String a,k,m,n;
		static String relst;
		
		static String[] station = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l"};
		static String train_dino;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		search();
		try{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection con = DriverManager.getConnection(url, user, password); 
			String sql ="INSERT INTO acc (usename, password)" + "VALUES (?,?)"; 
			PreparedStatement stt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
			stt.execute("CREATE DATABASE IF NOT EXISTS hsr CHARACTER SET utf8 COLLATE utf8_unicode_ci"); 
			stt.execute("USE hsr"); 
			stt.execute("CREATE TABLE IF NOT EXISTS acc (" + 
			"uid BIGINT NOT NULL AUTO_INCREMENT," 
			+ "usename CHAR(30),"	//1
			+ "password LONG," 		//2
			+ "PRIMARY KEY(uid)" 	//3
			+ ")"); 
			// stt.setInt(1, null); 
			stt.setString(1, "admin"); 
			stt.setString(2, "123"); 
			stt.execute(); 
			stt.close(); 
			} 
			catch (Exception e) 
			{ 
			e.printStackTrace(); 
			}
		
		mp.pack();
		mp.setResizable(false);
		mp1.setSize(450, 900);
		mp1.setResizable(false);
		mp1.setLocationRelativeTo(mp);
		mp2.setSize(450, 900);
		mp2.setResizable(false);
		mp2.setLocationRelativeTo(mp);
		mp3.setSize(250, 250);
//		mp3.setResizable(false);
		mp3.setLocationRelativeTo(mp);
		
		int cust = (int)(Math.random()*(999-100+1))+100;
		String cuss = "0" + Integer.toString(cust);
//		System.out.println(cuss);
		
		mainp();
		mp.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		
	}
	
	static public void aboutus(){
        about.addMouseListener(mp);
        about.setBounds(700, 5, 30, 30);
        aboutc.setBounds(560, 20, 300, 180);
        aboutc.setVisible(false);
        aboutc.setText("<html><div style=\"text-align: right;\">本程式僅供測試，<br>"
        		+ "請勿隨意散播、發售或更改；<br>"
        		+ "如有問題請以電郵或其他方式<br>"
        		+ "聯絡本作品之作者們：<br>"
        		+ "s03352032、s03353047<br>"
        		+ "s03352006、s03352030<br>"
        		+ "s03352011<br>"
        		+ "感謝合作。<br>"
        		+ "\u00a9THUCS:103<br>"
        		+ "</html>");		
	}
	
	static public void mainp(){
		
		//登入	
		mp.setSize(750, 800);
//		cr.setBackground(Color.lightGray);
		cr.setVisible(true);
		ad.setEnabled(false);
		bk.setEnabled(false);
		pr.setEnabled(false);
		rev.setEnabled(false);
		cte.setEnabled(false);
		mp.setLayout(null);
		mp.setVisible(true);
		mp.setTitle("歡迎來到Hickey Super-Railway訂票系統！");
		mp.add(title);
		title.setText("訂票系統");
		title.setFont(new Font("Courier", Font.ITALIC + Font.BOLD, 30));
		title.setVisible(true);
		title.setBounds(270, 60, 500, 80);
		lone.setText("帳號/訂單號碼");
		lone.setFont(cm);
		lonp.setText("密碼/電話");
		lonp.setFont(cm);
		nef.setFont(cm);
		pwf.setFont(cm);
		lone.setBounds(100,250,150,30);
		lonp.setBounds(100,290,150,30);
		nef.setBounds(250, 255, 150, 30);
		pwf.setBounds(250, 295, 150, 30);
		login.setBounds(410, 260, 100, 60);
		booking.setBounds(100, 400, 160, 70);
		checkm.setBounds(100, 500, 160, 70);
		timete.setBounds(100, 600, 160, 70);
		news.setBounds(300, 390, 100, 50);
		news.setFont(new Font("Microsoft JhengHei",Font.BOLD, 15));
		news.setForeground(Color.MAGENTA);
		nnews.setBounds(300, 400, 330, 270);
		nnews.setBorder(bdd);

		mp.add(nnews);
		mp.add(news);
		mp.add(lone);
		mp.add(lonp);
		mp.add(pwf);
		mp.add(nef);
		mp.add(login);
		mp.add(booking);
		mp.add(checkm);
		mp.add(timete);
        mp.add(about);
        mp.add(aboutc);
        aboutus();

        la.setBounds(0, 0, 750, 800);
		la.setVisible(true);	
		la.setLayout(null);
		la.setOpaque(false);
		mp.getLayeredPane().setLayout(null);
		mp.add(la, new Integer(Integer.MIN_VALUE));
		login.addActionListener(mp);
		booking.addActionListener(mp);
		checkm.addActionListener(mp);
		timete.addActionListener(mp);
		mp.repaint();
	}	
	
	static public void bk_tk(){
		bk.setEnabled(true);
//		bk.setBackground(Color.yellow);
		bk.setVisible(true);
		title.setText("請選擇：");		
		lone.setText("請選擇起點: ");
		lonp.setText("請選擇終點: ");
		month.setBounds(200, 370, 70, 30);
		month.setFont(cm);
		day.setBounds(280, 370, 70, 30);
		day.setFont(cm);
		start.setBounds(250, 255, 100, 30);
		start.setFont(cm);
		destin.setBounds(250, 295, 100, 30);
		destin.setFont(cm);
		start.setMaximumRowCount(4);
		destin.setMaximumRowCount(4);
		month.setMaximumRowCount(4);
		day.setMaximumRowCount(4);
		nuber.setBounds(100, 330, 100, 30);
		nuber.setFont(cm);
		nuer.setBounds(250, 330, 100, 30);
		nuer.setFont(cm);
		nute.setBounds(360, 330, 50, 30);
		nute.setFont(cm);
		dateo.setBounds(100, 370, 100, 30);
//		datet.setBounds(360, 370, 100, 30);
		dateo.setFont(cm);
//		datet.setFont(cm);
//		telist.setBounds(420, 370, 100, 30);
//		telist.setFont(cm);
		putph.setBounds(100, 410, 100, 30);
		putph.setFont(cm);
		phone.setBounds(250, 410, 160, 30);
		phone.setFont(cm);
		buy.setBounds(140, 500, 130, 60);
		reset.setBounds(280, 500, 130, 60);
		back.setBounds(420, 500, 130, 60);
		
		bk.add(putph);
		bk.add(phone);
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
		bk.add(month);
		bk.add(day);
//		bk.add(lc);

        lc.setBounds(0, 0, 750, 800);
		lc.setVisible(true);	
		lc.setLayout(null);
		lc.setOpaque(false);
		mp.getLayeredPane().setLayout(null);
		mp.add(lc, new Integer(Integer.MIN_VALUE));
		reset.addActionListener(mp);
		back.addActionListener(mp);
		buy.addActionListener(mp);
		reset.setEnabled(true);
		mp.repaint();

	}
	
	static public void admi(){
		ad.setEnabled(true);
		ad.setBackground(Color.MAGENTA);
		ad.setVisible(true);
		title.setText("內部系統處理");
		title.setBounds(100, 20, 500, 80);
		lone.setText("請選擇班次: ");
//		lonp.setText("請選擇終點: ");
		lone.setBounds(100,100,150,30);
//		lonp.setBounds(100,140,150,30);
		inlist.setBounds(250, 100, 150, 30);
//		onlist.setBounds(250, 140, 150, 30);
		check1.setBounds(410, 100, 100, 30);
//		check2.setBounds(410, 140, 100, 30);
		logout.setBounds(600, 40, 80, 50);
		
		ad.add(logout);
		ad.add(check1);
//		ad.add(check2);
//		ad.add(onlist);
		ad.add(inlist);
		ad.add(title);
		ad.add(lone);
//		ad.add(lonp);
		ad.add(train);
//		ad.add(time);

        le.setBounds(0, 0, 750, 800);
		le.setVisible(true);	
		le.setLayout(null);
		le.setOpaque(false);
		mp.getLayeredPane().setLayout(null);
		mp.add(le, new Integer(Integer.MIN_VALUE));
		train.setBounds(100, 300, 500, 400);
//		time.setBounds(100, 300, 500, 400);
		train.setVisible(false);
//		time.setVisible(false);
				
		check1.addActionListener(mp);
//		check2.addActionListener(mp);
		logout.addActionListener(mp);	
		mp.repaint();	
	}
	
	static public void adcheck1(){

		//train_no
		train_no.setFont(cm);
		train_no.setBounds(0, 10, 100, 30);
		train_no.setVisible(true);
		train.add(train_no);
		
		train_no_dis.setBounds(100, 10, 150, 30);
		train_no_dis.setEditable(false);
		train_no_dis.setVisible(true);
		train.add(train_no_dis);
		
		//train_da
		train_da.setFont(cm);
		train_da.setBounds(0, 50, 100, 30);
		train_da.setVisible(true);
		train.add(train_da);
		
		train_da_dis.setBounds(100, 50, 150, 30);
		train_da_dis.setEditable(false);
		train_da_dis.setVisible(true);
		train.add(train_da_dis);
		
		//train_ta
		train_ta.setFont(cm);
		train_ta.setBounds(0, 90, 100, 30);
		train_ta.setVisible(true);
		train.add(train_ta);
		
		train_ta_dis.setBounds(100, 90, 150, 30);
		train_ta_dis.setEditable(false);
		train_ta_dis.setVisible(true);
		train.add(train_ta_dis);
		
		//train_seatleft
		train_seatleft.setFont(cm);
		train_seatleft.setBounds(0, 130, 100, 30);
		train_seatleft.setVisible(true);
		train.add(train_seatleft);
		
		train_pr_dis.setBounds(100, 130, 150, 30);
		train_pr_dis.setEditable(false);
		train_pr_dis.setVisible(true);
		train.add(train_pr_dis);
		
		//phone_no
		phone_no.setFont(cm);
		phone_no.setBounds(0, 170, 100, 30);
		phone_no.setVisible(true);
		train.add(phone_no);
		
		phone_dis.setBounds(100, 170, 150, 30);
		phone_dis.setEditable(true);
		phone_dis.setVisible(true);
		train.add(phone_dis);
		
		confirm_train.setBounds(300, 300, 150, 30);
		train.add(confirm_train);
		reset_train.setBounds(100, 300, 150, 30);
		train.add(reset_train);
		
		confirm_train.addActionListener(mp);
		reset_train.addActionListener(mp);
		train.setOpaque(false);
	}
//	
	static public void revisit(){
		rev.setEnabled(true);
		rev.setBackground(Color.green);
		rev.setVisible(true);
		title.setText("查詢訂單");
		title.setBounds(100, 20, 500, 80);
		lone.setText("您的班次: ");
		lonp.setText("應繳金額: ");
		lone.setBounds(100,100,150,30);
		lonp.setBounds(100,140,150,30);
		ber.setBounds(100, 180, 150, 30);
		ber.setFont(cm);
		date.setBounds(100, 220, 150, 30);
		date.setFont(cm);
		goz.setBounds(100, 260, 150, 30);
		goz.setFont(cm);
		logout.setBounds(600, 40, 80, 50);
		bank.setBounds(200, 100, 150, 30);
		monk.setBounds(200, 140, 150, 30);
		berk.setBounds(200, 180, 150, 30);
		datk.setBounds(200, 220, 150, 30);
		gozk.setBounds(200, 260, 150, 30);
		bank.setFont(cm);
		monk.setFont(cm);
		berk.setFont(cm);
		datk.setFont(cm);
		gozk.setFont(cm);
		
		rev.add(bank);
		rev.add(monk);
		rev.add(berk);
		rev.add(datk);
		rev.add(gozk);
		rev.add(cancel);
		rev.add(date);
		rev.add(goz);
		rev.add(lone);
		rev.add(lonp);
		rev.add(ber);
		rev.add(title);
		rev.add(ed);
		rev.add(pt);
		rev.add(logout);

        lf.setBounds(0, 0, 750, 800);
		lf.setVisible(true);	
		lf.setLayout(null);
		lf.setOpaque(false);
		mp.getLayeredPane().setLayout(null);
		mp.add(lf, new Integer(Integer.MIN_VALUE));
		cancel.setVisible(false);
		cancel.setFont(new Font("Microsoft JhengHei", Font.BOLD + Font.ITALIC, 35));
		cancel.setForeground(Color.RED);
		cancel.setBounds(280, 380, 150, 150);
		ed.setBounds(140, 350, 130, 60);
		pt.setBounds(280, 350, 130, 60);
		logout.setBounds(420, 350, 130, 60);
		logout.addActionListener(mp);
		ed.addActionListener(mp);
		mp.repaint();
	}
	
	static public void prtable(){
		pr.setEnabled(true);
//		pr.setBackground(Color.orange);
		pr.setVisible(true);
		logout.setBounds(35, 40, 80, 50);
		logout.setText("返回");

		mp.setSize(800, 600);
		lb.setBounds(35, 120, 722, 399);
		lb.setVisible(true);
		
		pr.add(lb);	
		pr.add(logout);

        lg.setBounds(0, 0, 750, 800);
		lg.setVisible(true);	
		lg.setLayout(null);
		lg.setOpaque(false);
		mp.getLayeredPane().setLayout(null);
		mp.add(lg, new Integer(Integer.MIN_VALUE));
		logout.addActionListener(mp);
		mp.repaint();
	}
	
	static public void trtable(){
		cte.setEnabled(true);
//		cte.setBackground(Color.pink);
		cte.setVisible(true);
		logout.setBounds(35, 40, 80, 50);
		logout.setText("返回");

		mp.setSize(1300, 750);
		ld.setBounds(35, 120, 1223, 564);
		ld.setVisible(true);

		cte.add(ld);	
		cte.add(logout);

        lh.setBounds(0, 0, 750, 800);
		lh.setVisible(true);	
		lh.setLayout(null);
		lh.setOpaque(false);
		mp.getLayeredPane().setLayout(null);
		mp.add(lh, new Integer(Integer.MIN_VALUE));
		logout.addActionListener(mp);	
		mp.repaint();	
	}
	
	static public void printtime(){
		mp3.setVisible(true);

		datet.setBounds(50, 50, 100, 30);
		datet.setFont(cm);
		telist.setBounds(105, 50, 100, 30);
		telist.setFont(cm);
		buy1.setBounds(55, 90, 130, 60);
		buy1.addActionListener(mp);
		
		mp3.add(buy1);
		mp3.add(datet);
		mp3.add(telist);
		mp3.add(mystart);
		mp3.add(myend);
		mp3.add(mytyno);
		mp.repaint();
	}
	
	static public void printbuy(){
		int cust = (int)(Math.random()*(999-100+1))+100;
		String cuss = "0" + Integer.toString(cust);
//		System.out.println(cuss);
		mp1.setVisible(true);
		mystart.setText("起點: "+start.getSelectedItem().toString());
		//System.out.print(start.getSelectedIndex());
		myend.setText("終點: "+destin.getSelectedItem().toString());
		mytyno.setText("票種: "+nuer.getSelectedItem().toString());
		myttno.setText("/ "+nute.getSelectedItem().toString());
//		mytrno.setText(arg0);
		mystday.setText("日期: "+month.getSelectedItem().toString()+day.getSelectedItem().toString());
		mysttime.setText("時間: "+telist.getSelectedItem().toString());
		myno.setText("訂票號碼: "+cuss);
		myph.setText("電話號碼: "+phone.getText());

		mystart.setFont(cn);
		myend.setFont(cn);
		mytyno.setFont(cn);
		myttno.setFont(cn);
		mystday.setFont(cn);
		mysttime.setFont(cn);
		myno.setFont(cn);
		myph.setFont(cn);
		
		myno.setBounds(70, 50, 200, 100);
		myph.setBounds(70, 100, 500, 100);
		mystart.setBounds(120, 150, 200, 100);
		myend.setBounds(120, 200, 200, 100);
		mytyno.setBounds(120, 250, 200, 100);
		myttno.setBounds(240, 250, 200, 100);
		mystday.setBounds(120, 300, 200, 100);
		mysttime.setBounds(120, 350, 200, 100);
		
		mp1.add(myph);
		mp1.add(myno);
		mp1.add(mystart);
		mp1.add(myend);
		mp1.add(mytyno);
		mp1.add(myttno);
		mp1.add(mystday);
		mp1.add(mysttime);
		mp1.add(myedtime);
		try{ 
			//Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection con = DriverManager.getConnection(url, user, password); 
			String sql ="INSERT INTO booking_data (book_no, train_no, phone)" + "VALUES (?,?,?)"; 
			PreparedStatement stt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
			//stt.execute("CREATE DATABASE IF NOT EXISTS hsr CHARACTER SET utf8 COLLATE utf8_unicode_ci"); 
			stt.execute("USE hsr"); 
			//stt.execute("CREATE TABLE IF NOT EXISTS acc (" + 
			//"uid BIGINT NOT NULL AUTO_INCREMENT," 
			//+ "usename CHAR(30),"	//1
			//+ "password LONG," 		//2
			//+ "PRIMARY KEY(uid)" 	//3
			//+ ")"); 
			// stt.setInt(1, null); 
			stt.setString(1, cuss); 
			stt.setString(2, train_dino); 
			stt.setString(3, phone.getText()); 
			stt.execute(); 
			stt.close(); 
			} 
			catch (Exception e) 
			{ 
			e.printStackTrace(); 
			}
	}
	
	static public void printbuyad(){
		int cust = (int)(Math.random()*(999-100+1))+100;
		String cuss = "0" + Integer.toString(cust);
//		System.out.println(cuss);
		mp2.setVisible(true);
//		mystart.setText("起點: "+train_no_dis.getSelectedItem().toString());
//		myend.setText("終點: "+train_da_dis.getSelectedItem().toString());
		mytrno.setText("班次: "+train_no_dis.getText());
		mystday.setText("日期: "+train_da_dis.getText());
		mysttime.setText("時間: "+train_ta_dis.getText());
		myno.setText("訂票號碼: "+cust);
		myph.setText("電話號碼: "+phone_dis.getText());

		mytrno.setFont(cn);
		mystday.setFont(cn);
		mysttime.setFont(cn);
		myno.setFont(cn);
		myph.setFont(cn);
		
		myno.setBounds(70, 50, 200, 100);
		myph.setBounds(70, 100, 500, 100);
		mytrno.setBounds(120, 200, 200, 100);
		mystday.setBounds(120, 250, 200, 100);
		mysttime.setBounds(120, 300, 200, 100);
		
		mp2.add(myph);
		mp2.add(myno);
		mp2.add(mytrno);
		mp2.add(mystday);
		mp2.add(mysttime);
		mp2.add(myedtime);

try{ 
			//Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection con = DriverManager.getConnection(url, user, password); 
			String sql ="INSERT INTO booking_data (book_no, train_no, phone)" + "VALUES (?,?,?)"; 
			PreparedStatement stt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
			//stt.execute("CREATE DATABASE IF NOT EXISTS hsr CHARACTER SET utf8 COLLATE utf8_unicode_ci"); 
			stt.execute("USE hsr"); 
			//stt.execute("CREATE TABLE IF NOT EXISTS acc (" + 
			//"uid BIGINT NOT NULL AUTO_INCREMENT," 
			//+ "usename CHAR(30),"	//1
			//+ "password LONG," 		//2
			//+ "PRIMARY KEY(uid)" 	//3
			//+ ")"); 
			// stt.setInt(1, null); 
			stt.setString(1, cuss); 
			stt.setString(2, train_no_dis.getText()); 
			stt.setString(3, phone_dis.getText()); 
			stt.execute(); 
			stt.close(); 
			} 
			catch (Exception e) 
			{ 
			e.printStackTrace(); 
			}
		
		
	}
	
	static public void login_check(){
	try{
//        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(url, user, password);
        String qCk = "SELECT password FROM acc WHERE usename ='" + nef.getText() + "'";
//        System.out.println(qCk);
        PreparedStatement ps = con.prepareStatement(qCk);       
        ps.execute("USE hsr");     
        ps.executeQuery(qCk);       
        ResultSet Rest = ps.executeQuery(qCk);       
        ps.getResultSet();
        a = pwf.getText();
//		System.out.println(a);
		long b = Long.parseLong(a);
//		System.out.println(b);
        if(Rest.next()){					//核對資料
        	Long relst = Rest.getLong("password");
//        	System.out.println(Rest);
        	if(b == relst)
        	{
        	//userid.add(relst);
//        	System.out.println("a");
        	admi();
        	}
        	else if(b != relst)
        	{
        		JOptionPane.showMessageDialog(mp, "錯密碼/帳戶!");
        		mainp();
        	}
           // ps.close();
        }
        	ps.close();
    }
    catch (Exception e)
    {         
			e.printStackTrace(); 
    }

	}

	static public void train_check(){
	 

	if(start.getSelectedIndex()<destin.getSelectedIndex()) 
	{
	try{
//      Class.forName("com.mysql.jdbc.Driver").newInstance();
	  
      Connection con = DriverManager.getConnection(url, user, password);
      String qCk = "SELECT train_no FROM train_stop WHERE " + station[start.getSelectedIndex()] + " = '1' and " + station[destin.getSelectedIndex()] + " = '1'";
      PreparedStatement ps = con.prepareStatement(qCk);       
      ps.execute("USE hsr");     
      ps.executeQuery(qCk);       
      ResultSet Rest_1 = ps.executeQuery(qCk);       
      ps.getResultSet();
//      System.out.println(start.getSelectedIndex());
//      System.out.println(destin.getSelectedIndex());
//      System.out.println(qCk);
      
      if(Rest_1.next()){					//核對資料
    	  
      	String relst = Rest_1.getString("train_no");
      	
//      	System.out.println(relst);
      	train_dino = relst;
      	
      		if (JOptionPane.showConfirmDialog(mp, "確定以上資訊嗎？\n確定將無法更改喔！", "注意！",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
			{
				start.setEnabled(false);
				destin.setEnabled(false);
				nuer.setEnabled(false);
				nute.setEnabled(false);
				month.setEnabled(false);
				day.setEnabled(false);
//				telist.setEnabled(false);
				phone.setEnabled(false);
				buy.setEnabled(false);
				time_no.setEnabled(false);
				printtime();
//				printbuy();
			}
      	}
      else
      {
        		JOptionPane.showMessageDialog(mp, "沒有班次!");
        	}
     
         // ps.close();
      
      	ps.close();
  }

  catch (Exception e)
  {         
			e.printStackTrace(); 
  }
}
	else
	{
		try{
	      Connection con = DriverManager.getConnection(url, user, password);
	      String qCk = "SELECT train_no FROM train_stop WHERE " + station[start.getSelectedIndex()] + " = '2' and " + station[destin.getSelectedIndex()] + " = '2'";
	      PreparedStatement ps = con.prepareStatement(qCk);       
	      ps.execute("USE hsr");     
	      ps.executeQuery(qCk);       
	      ResultSet Rest_1 = ps.executeQuery(qCk);       
	      ps.getResultSet();
//	      System.out.println(start.getSelectedIndex());
//	      System.out.println(destin.getSelectedIndex());
//	      System.out.println(qCk);
	      if(Rest_1.next()){					//核對資料
	    	  
	     String relst = Rest_1.getString("train_no");
//	     System.out.println(relst);
	      	
	      		if (JOptionPane.showConfirmDialog(mp, "確定以上資訊嗎？\n確定將無法更改喔！", "注意！",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{
					start.setEnabled(false);
					destin.setEnabled(false);
					nuer.setEnabled(false);
					nute.setEnabled(false);
					month.setEnabled(false);
					day.setEnabled(false);
//					telist.setEnabled(false);
					phone.setEnabled(false);
					buy.setEnabled(false);
					time_no.setEnabled(false);
					printtime();
//					printbuy();
				}
	      	}
	      else
	      {
	        		JOptionPane.showMessageDialog(mp, "沒有班次!");
	        	}
	     
	         // ps.close();
	      
	      	ps.close();
	  }

	  catch (Exception e)
	  {         
				e.printStackTrace(); 
	  }
	}
	
	}
	
	static public void price(){ //訂單票價查詢
		
//		System.out.println("a");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			String sql = "SELECT fare FROM train_far WHERE start = 'a' and destin = 'l'";
//			System.out.println(sql);
//			System.out.println(station[start.getSelectedIndex()]);
//			System.out.println(station[destin.getSelectedIndex()]);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute("USE hsr");     
		    ps.executeQuery(sql);       
		    ResultSet Rest_1 = ps.executeQuery(sql);       
		    ps.getResultSet();
//		    System.out.println(start.getSelectedIndex());
//		    System.out.println(destin.getSelectedIndex());
		   
		    if (Rest_1.next()) {
		    	String relst = Rest_1.getString("fare");
//		    	System.out.println(relst);
		    }
		   
		    ps.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		train_pr_dis.setText(relst);
//		System.out.println(relst);
	}
	
	static public void back() {  //後臺
//		if(start.getSelectedIndex()<destin.getSelectedIndex()) 
		price();
		try { 
				Class.forName("com.mysql.jdbc.Driver").newInstance(); 
				Connection con = DriverManager.getConnection(url, user, password); 
				String sql ="SELECT time_start FROM t WHERE train_no =" + inlist.getSelectedItem() + " and train_start = 'a'";  //南港發車班次
				PreparedStatement ps = con.prepareStatement(sql);       
			      ps.execute("USE hsr");     
			      ps.executeQuery(sql);       
			      ResultSet Rest_1 = ps.executeQuery(sql);       
			      ps.getResultSet();
//			      System.out.println(inlist.getSelectedItem());
//			      System.out.println(sql);
			      if(Rest_1.next()){					//核對資料
			      	relst = Rest_1.getString("time_start");
//			      	System.out.println(relst);
			      	}
			      	 ps.close();
			  }
			  catch (Exception e) {         
						e.printStackTrace(); 
			  }
			String a =String.valueOf(inlist.getSelectedItem());
//			System.out.println(a);
			train_no_dis.setText(a);
			train_ta_dis.setText(relst);
				  }
	
	static public void search(){
		try{
	      Connection con = DriverManager.getConnection(url, user, password);
	      String qCk = "SELECT train_no FROM booking_data WHERE '" + nef.getText() + "'";
	      PreparedStatement ps = con.prepareStatement(qCk);       
	      ps.execute("USE hsr");     
	      ps.executeQuery(qCk);       
	      ResultSet Rest_1 = ps.executeQuery(qCk);       
	      ps.getResultSet();
	      if(Rest_1.next()){
	    	  k = Rest_1.getString("train_no");
		      String qk = "SELECT time_start FROM t WHERE '" + k + "'";
		      PreparedStatement pps = con.prepareStatement(qk);       
		      pps.execute("USE hsr");     
		      pps.executeQuery(qk);       
		      ResultSet Restp = pps.executeQuery(qCk);       
		      pps.getResultSet();
		      if (Restp.next()){
		    	  m = Restp.getString("time_start");
		    	  gozk.setText(m);
		      }
		      pps.close();
		      }
		  }
		  catch (Exception e)
		  {         
					e.printStackTrace(); 
		  }
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		aboutc.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		aboutc.setVisible(false);
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
		if (e.getSource() == login) // Login admin/check booking
		{
			cr.removeAll();
			cr.setEnabled(false);
			if (nef.getText().contains("0"))
			{
				search();
				revisit();
			}
			else if (((nef.getText().equals("")) || (pwf.getText().equals(""))) )
			{
				JOptionPane.showMessageDialog(mp, "請輸入帳號/訂單 或 密碼/電話！");
				mainp();
				
			}
			else 
				{
				login_check();
				}
			
		}
		if (e.getSource() == reset)
		{			
			start.setSelectedIndex(0);
			destin.setSelectedIndex(0);
			nuer.setSelectedIndex(0);
			nute.setSelectedIndex(0);
			telist.setSelectedIndex(0);
			month.setSelectedIndex(0);
			day.setSelectedIndex(0);
			phone.setText("");
		}
		
		if ((e.getSource() == back) || (e.getSource() == logout))
		{
			bk.removeAll();
			bk.setEnabled(false);
			rev.removeAll();
			rev.setEnabled(false);
			nef.setText("");
			pwf.setText("");
			phone.setText("");
			phone_dis.setText("");
			start.setEnabled(true);
			destin.setEnabled(true);
			nuer.setEnabled(true);
			nute.setEnabled(true);
			month.setEnabled(true);
			day.setEnabled(true);
			telist.setEnabled(true);
			phone.setEnabled(true);
			buy.setEnabled(true);
			time_no.setEnabled(true);
			phone_dis.setEnabled(true);
			reset.setEnabled(true);
			ed.setEnabled(true);
			mainp();
		}
		if (e.getSource() == check1)
		{
			train.setVisible(true);
			train.setLayout(null);
//			time.setEnabled(false);
//			time.setVisible(false);
			adcheck1();
			back();
		}
		if (e.getSource() == reset_train)
		{
			train_no_dis.setText("");
			train_da_dis.setText("");
			train_ta_dis.setText("");
			train_pr_dis.setText("");
			phone_dis.setText("");
			time_sele_no.setSelectedItem(0);
			time_da_dis.setText("");
			time_ta_dis.setText("");
			time_pr_dis.setText("");
		}
		if (e.getSource() == checkm)
		{
			cr.removeAll();
			cr.setEnabled(false);
			prtable();
		}
		if (e.getSource() == timete)
		{
			cr.removeAll();
			cr.setEnabled(false);
			trtable();
		}
		if (e.getSource() == buy) // Print out booking
		{
//			System.out.println("a");
			if ( phone.getText().equals("") )
			{
				JOptionPane.showMessageDialog(mp, "請輸入電話號碼！");
			}
			else
			{
				train_check();
			}
		}
		if (e.getSource() == buy1) // Print out booking
		{
			buy1.setEnabled(false);
			printbuy();
		}
		if ((e.getSource() == confirm_train) || (e.getSource() == confirm_time))
		{
			if (phone_dis.getText().equals(""))
			{
				JOptionPane.showMessageDialog(mp, "請輸入電話號碼！");
			}
			else
			{
				if (JOptionPane.showConfirmDialog(mp, "確定以上資訊嗎？\n確定將無法更改喔！", "注意！",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{
					phone_dis.setEnabled(false);
					reset.setEnabled(false);
					printbuyad();
				}			
		}
		if (e.getSource() == ed)
		{
			if (JOptionPane.showConfirmDialog(mp, "確定要取消嗎？\n確定將無法更改喔！", "注意！",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
			{
				cancel.setVisible(true);
				ed.setEnabled(false);
			}
		}
	}
	}

}
