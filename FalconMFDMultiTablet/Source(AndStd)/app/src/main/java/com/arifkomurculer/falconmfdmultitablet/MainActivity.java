package com.arifkomurculer.falconmfdmultitablet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;
import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import com.arifkomurculer.falconmfdmultitablet.haritalar.Item;

import android.R.layout;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.PowerManager.WakeLock;
import android.renderscript.Element;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {
	
static int vrResimIndex;
	
	public static Bitmap bmpMisSch1;
	public static Bitmap bmpMisSch2;
	public static Bitmap bmpMisSch3;
	public static Bitmap bmpMisSch4;
	public static Bitmap bmpMisSch5;
	public static Bitmap bmpMisSch;
	
	//private String vrBtn ;	
	private String vrHangiTus;
	
	//integer Variable lar		
		int vrECM;
		int vrROLL;
		int vrPITCH;
		int vrPRGM;
		int vrMODE;
		int vrHMCS;
		int vrICPSAGJS;		
		int vrBntMainSwitch;
		float vrPnlYardimciTop;
		//int vrAP;
		
	//integer Variable lar Sonu
		
	//String Variable lar
		String vrRWRTaklit;
		String	vrSpeedBreak;
		String	vrGearPos;
		String	vrFlareCount;
		String	vrFlareLow;
		String	vrChaffCount;
		String	vrChaffLow;
		String	vrYCoordinate;
		String	vrXCoordinate;
		static String vrCurrentHeading;
		String	vrRPM;
		String	vrTotalFuel;
		String	vrFuelFlow;
		String	vrkias ; 
		String	vraauz;
		String  vrMenu;
		//int vrAP;
		
	//integer Variable lar Sonu
		
	
	//String Variable lar Sonu
	
	//ViewFlipper ler
		private static final int SWIPE_MIN_DISTANCE = 20;
		private static final int SWIPE_THRESHOLD_VELOCITY = 5;
		
		private ViewFlipper mview_flipperIcpSolUst;	
		private ViewFlipper mview_flipperIcpSagUst;
		private ViewFlipper mview_flipperIcpSolAlt;
		private ViewFlipper mview_flipperIcpSagAlt;
		
		private ViewFlipper mview_flipperPnlYardimci;
		
		private ViewFlipper mview_flipperECM;
		private ViewFlipper mview_flipperROLL;
		private ViewFlipper mview_flipperPITCH;
		
		private ViewFlipper mview_flipperPRGM;
		private ViewFlipper mview_flipperMODE;
		private ViewFlipper mview_flipperHMCS;
		
		private ViewFlipper mview_flipperICPSAGJS;
		
		
	//ViewFlipper ler Sonu
		
		
		//PnlUst  
			ImageButton imgBntMainSwitch;
			TextView txtDataFlrCnt;
			TextView txtDataChftCnt;
			TextView txtDataTotalFuel;
			TextView txtDataFuelFlow;
			TextView txtAirSpead;
			TextView txtAltitute;
			TextView txtCurrentHeading;
			TextView textView1;
			TextView txtPnlTardimciTop;
			
		//PnlUst Sonu
		
		//PnlYedek
			ImageButton imgBtnYardimciKapat;
		//PnlYedek Sonu	
			
			
			
		//Menu
			ImageButton imgBtnMenuSecLMFD;
			ImageButton imgBtnMenuSecRMFD;
			ImageButton imgBtnMenuSecICP;
			ImageButton imgBtnHome;
			ImageButton imgBtnMenuSecRWR;
			ImageButton btnMenuMisSch;
		//Menu Sonu
			
			
		//Pnl A/B
			ImageButton imgBtnAB;
			ImageButton imgBtnGear;
		//Pnl A/B Sonu	
		
		//ICP Buttonlarý
	        ImageButton btna_a ;
	        ImageButton btna_g ;
	        ImageButton btnCom1 ;
	        ImageButton btnCom2 ;
	        ImageButton btnIIF ;
	        ImageButton btnList ;
	       
	        ImageButton btnIcp0 ;
	        ImageButton btnIcp1 ;
	        ImageButton btnIcp2 ;
	        ImageButton btnIcp3;
	        ImageButton btnIcp4 ;
	        ImageButton btnIcp5 ;
	        ImageButton btnIcp6 ;
	        ImageButton btnIcp7 ;
	        ImageButton btnIcp8 ;
	        ImageButton btnIcp9 ;
	       
	        ImageButton btnIcpRtrn ;
	        ImageButton btnIcpSeq ;
	        ImageButton btnIcpUp;
	        ImageButton btnIcpDown ;
	       
	        ImageButton btnIcpNext ;
	        ImageButton btnIcpPrev;
	       
	        ImageButton btnIcpRcl ;
	        ImageButton btnIcpEnter ;
	       
	       
	        //RWR için
	        ImageButton imgIcpMenuSwitch;
	        
	 //ICP Buttonlarý Sonu
	        
	        
	        
	        
	        
		//Sol MFD
		
				 ImageButton btnSol1;
		         ImageButton btnSol2; 
		         ImageButton btnSol3; 
		         ImageButton btnSol4; 
		         ImageButton btnSol5;
		         ImageButton btnSol6;
		         ImageButton btnSol7; 
		         ImageButton btnSol8; 
		         ImageButton btnSol9; 
		         ImageButton btnSol10; 
		        
		         ImageButton btnSol11; 
		         ImageButton btnSol12; 
		         ImageButton btnSol13; 
		         ImageButton btnSol14; 
		         ImageButton btnSol15; 
		         ImageButton btnSol16; 
		         ImageButton btnSol17; 
		         ImageButton btnSol18;
		         ImageButton btnSol19; 
		         ImageButton btnSol20;
		         
		//Sol Mfd Sonu
		         
		//Sag MFD
					
				 ImageButton btnSag1;
		         ImageButton btnSag2; 
		         ImageButton btnSag3; 
		         ImageButton btnSag4; 
		         ImageButton btnSag5;
		         ImageButton btnSag6;
		         ImageButton btnSag7; 
		         ImageButton btnSag8; 
		         ImageButton btnSag9; 
		         ImageButton btnSag10; 
		        
		         ImageButton btnSag11; 
		         ImageButton btnSag12; 
		         ImageButton btnSag13; 
		         ImageButton btnSag14; 
		         ImageButton btnSag15; 
		         ImageButton btnSag16; 
		         ImageButton btnSag17; 
		         ImageButton btnSag18;
		         ImageButton btnSag19; 
		         ImageButton btnSag20;
		         
		//Sag Mfd Sonu	         
		         
		         
	
	//ImageViewler
		ImageView imgMFDSagIc;
		ImageView imgMFDSolIc;
		ImageButton btnAmblem;
    	ImageButton imgBtnModesMenu;
    	ImageButton imgBtnExit ;
    	ImageButton ImgBtnSettings ;
    	ImageView imgRWRIc;
    	ImageView imgRWR;
    	ImageView imgDEDIc;
	//ImageViewler Sonu
    	
   //RelativeLayout lar	
    	RelativeLayout pnlICP_DED;
    	RelativeLayout pnlUst;
    	RelativeLayout pnlMFD;
    	RelativeLayout pnlYardimci;
    	RelativeLayout pnlYardimciSag;
    	RelativeLayout pnlSolMFD;
    	RelativeLayout pnlSagMFD;
    	RelativeLayout pnlICP_DEDDis;
    	RelativeLayout pnlMenu;
    	RelativeLayout pnlRWR;
    	
   //RelativeLayout lar Sonu
		
	//TextViewlwe	
		TextView txtWidth ;
		TextView txtHeight;
	//TextViewlwe Sonu
		
		
	//Threadler
		Thread thfnkListenMsgPort;
		Thread thfnkListenLMFDPort;
		Thread thfnkListenRMFDPort;
		Thread thfnkListenRWRPort;
		Thread thfnkListenDEDPort;
		Thread thfnkListenMisSchPort;
		
		Thread ThSendKey;
	//Threadler Sonu	
	
	
//Port Tanýmlamalarý
		static String prServerIP; //		 prServerIP = "192.168.0.31";
		String prServerPort ;//		 prServerPort = "21111";  
		static String prMsgPort ;//		 prMsgPort = "21111"; 
		String prLMFDPort ;//		 prLMFDPort = "21112";  
		String prRMFDPort ;//		 prRMFDPort = "21113";
		String prDEDPort;//		     prDEDPort = "21114";	
		static String prMisSchPort ;//		 prMisSchPort = "21115";
		String prRWRPort;//		     prRWRPort = "21116";
		String prRefreshRate;//		 prRefreshRate = "100";
	//Port Tanýmlamalarý Sor	
	
	
	//Popup Forumlar 	
		PopupWindow popup ;
		Point p;
		final Context context = this;
	//Popup Forumlar Sonu
		
	
		
		//Cancel Back Key
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event)
	    {
	        if ((keyCode == KeyEvent.KEYCODE_BACK))
	        {
	        	fnkPopUp("Falcon MFD" , "Have a Falcon Day"  ,"Msg" ,"Info", R.layout.frmpopup);
	        	
	        	//finish();
	        }
	        //return super.onKeyDown(keyCode, event);
	        return false;
	    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        fnkPopUp("Falcon MFD" , "IP of Your Tablet : " + getIPAddress(true) 
//        		,"Msg" ,"Info", R.layout.frmpopup);
        
        
       PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
        
vrResimIndex = 1;
        
       
        
        
  //Yardýmcý Panellerin Deðiþkenleri
        vrECM 	= 0; //Kapalý
		vrROLL 	= 0; //Kapalý
		vrPITCH	= 0; //Kapalý
		vrPRGM	= 0; //Kapalý
		vrMODE	= 0; //Kapalý
		vrHMCS	= 0; //Kapalý
		vrICPSAGJS = 0; //Kapalý
		
		vrBntMainSwitch = 0; //MFD
		
		//vrAP = 0; //AP Off
		
 //Yardýmcý Panellerin Deðiþkenleri Sonu
		
		vrRWRTaklit = "RWR";
        
        
//ViewFlipper ler Tanýmlama        
        mview_flipperIcpSolUst = (ViewFlipper) this.findViewById(R.id.view_flipperIcpSolUst);
        mview_flipperIcpSagUst = (ViewFlipper) this.findViewById(R.id.view_flipperIcpSagUst);
        mview_flipperIcpSolAlt = (ViewFlipper) this.findViewById(R.id.view_flipperIcpSolAlt);
        mview_flipperIcpSagAlt = (ViewFlipper) this.findViewById(R.id.view_flipperIcpSagAlt);
        mview_flipperPnlYardimci = (ViewFlipper) this.findViewById(R.id.view_flipperPnlYardimci);
        
        mview_flipperECM = (ViewFlipper) this.findViewById(R.id.flipperECM);
        mview_flipperROLL = (ViewFlipper) this.findViewById(R.id.flipperROLL);
        mview_flipperPITCH = (ViewFlipper) this.findViewById(R.id.flipperPITCH);
        
        mview_flipperPRGM = (ViewFlipper) this.findViewById(R.id.flipperPRGM);
        mview_flipperMODE = (ViewFlipper) this.findViewById(R.id.flipperMODE);
        mview_flipperHMCS = (ViewFlipper) this.findViewById(R.id.flipperHMCS);
        
        mview_flipperICPSAGJS = (ViewFlipper) this.findViewById(R.id.flipperICPjsSag);
        
        
        
//ViewFlipper ler Tanýmlama Sonu    
        
//ViewFlipper ler Detektör Tanýmlama        
        final GestureDetector detectorview_flipperIcpSolUst = new GestureDetector(new Swipeview_flipperIcpSolUst());
        final GestureDetector detectorview_flipperIcpSagUst = new GestureDetector(new Swipeview_flipperIcpSagUst());
        final GestureDetector detectorview_flipperIcpSolAlt = new GestureDetector(new Swipeview_flipperIcpSolAlt());
        final GestureDetector detectorview_flipperIcpSagAlt = new GestureDetector(new Swipeview_flipperIcpSagAlt());
        final GestureDetector detectorview_flipperPnlYardimci = new GestureDetector(new Swipeview_flipperPnlYardimci());
        
        final GestureDetector detectorview_flipperECM = new GestureDetector(new Swipeview_flipperECM());
        final GestureDetector detectorview_flipperROLL = new GestureDetector(new Swipeview_flipperROLL());
        final GestureDetector detectorview_flipperPITCH = new GestureDetector(new Swipeview_flipperPITCH());
        final GestureDetector detectorview_flipperPRGM = new GestureDetector(new Swipeview_flipperPRGM());
        final GestureDetector detectorview_flipperMODE = new GestureDetector(new Swipeview_flipperMODE());
        final GestureDetector detectorview_flipperHMCS = new GestureDetector(new Swipeview_flipperHMCS());
        final GestureDetector detectorview_flipperICPSAGJS = new GestureDetector(new Swipeview_flipperICPSAGJS());
        
//ViewFlipper ler Detektör Tanýmlama Sonu           
        
        
//ImageButton Larýn Tanýmlama
        	imgMFDSagIc =  (ImageView) findViewById(R.id.imgMFDSagIc);
        	imgMFDSolIc =  (ImageView) findViewById(R.id.imgMFDSolIc);
        	imgBtnExit = (ImageButton) findViewById(R.id.imgBtnExit);
        	ImgBtnSettings = (ImageButton) findViewById(R.id.ImgBtnSettings);
        	imgBtnModesMenu = (ImageButton) findViewById(R.id.imgBtnScreens);        	
        	imgRWRIc =  (ImageView) findViewById(R.id.imgRWRIc);
        	imgRWR =  (ImageView) findViewById(R.id.imgRWR);
        	imgDEDIc =  (ImageView) findViewById(R.id.icp_dedic);
        	
        	ImageButton btnMenuICP =(ImageButton) findViewById(R.id.btnMenuMaps);
        	
		       btnMenuICP.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						fnkGetLocation(R.id.ImgBtnSettings);
			            	  fnkPopUp("Falcon MFD" , ""  ,"maps" ,"Info", R.layout.select_map);
					}
				});
		       
		       
		       
		       
//ImageButton Larýn Tanýmlama Sonu

//RelativeLayout Larýn Tanýmlama        	
        pnlMFD =(RelativeLayout)findViewById(R.id.pnlMFD);
        pnlICP_DED =(RelativeLayout)findViewById(R.id.pnlICP_DED);
        pnlYardimci =(RelativeLayout)findViewById(R.id.pnlYardimci);
        pnlYardimciSag  =(RelativeLayout)findViewById(R.id.pnlYardimciSag);
        pnlSolMFD =(RelativeLayout)findViewById(R.id.pnlSolMFD);
        pnlRWR =(RelativeLayout)findViewById(R.id.pnlRWR);
        pnlSagMFD =(RelativeLayout)findViewById(R.id.pnlSagMFD);
        pnlICP_DEDDis =(RelativeLayout)findViewById(R.id.pnlICP_DEDDis);
        pnlMenu =(RelativeLayout)findViewById(R.id.pnlMenu);
        
        
        pnlICP_DEDDis.setRotation(90);
        //RelativeLayout.LayoutParams vrLp = new RelativeLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
//        RelativeLayout.LayoutParams vrLp = new RelativeLayout.LayoutParams( 500,500);
//        pnlICP_DEDDis.setLayoutParams(vrLp);
       
        
        pnlMenu.setVisibility(View.VISIBLE);
        
//RelativeLayout Larýn Tanýmlama Sonu        
        
        fnkReadXML();
        
        fnkGetScreen();
        
         
//ViewFlipper ler Kodlarýnýn Baþlangýcý         
        mview_flipperIcpSolUst.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperIcpSolUst.onTouchEvent(event);
				return true;
			}
		});	
        
        mview_flipperIcpSagUst.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperIcpSagUst.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperIcpSolAlt.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperIcpSolAlt.onTouchEvent(event);
				return true;
			}
		});	
        
        mview_flipperIcpSagAlt.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperIcpSagAlt.onTouchEvent(event);
				return true;
			}
		});
        
        
        mview_flipperPnlYardimci.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperPnlYardimci.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperECM.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperECM.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperROLL.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperROLL.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperPITCH.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperPITCH.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperICPSAGJS.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperICPSAGJS.onTouchEvent(event);
				return true;
			}
		});
        
        
        mview_flipperPRGM.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperPRGM.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperMODE.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperMODE.onTouchEvent(event);
				return true;
			}
		});
        
        mview_flipperHMCS.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(final View view, final MotionEvent event) {
				detectorview_flipperHMCS.onTouchEvent(event);
				return true;
			}
		});
//ViewFlipper ler Kodlarýnýn Sonu         
          
// ImageButton larýn Kodlarýnýn Baþlangýcý 
        imgBtnExit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View arg0) {
        	  
        	  fnkGetLocation(R.id.imgBtnExit);
        	  
            //Open popup window
            if (p != null)
            	fnkPopUp("Falcon MFD" , "Have a Falcon Day"  ,"Msg" ,"Info", R.layout.frmpopup);
          }
        });
        
        
        
        ImgBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
          	  
          	  fnkGetLocation(R.id.ImgBtnSettings);
          	  
              //Open popup window
              if (p != null)
            	  fnkPopUp("Falcon MFD" , ""  ,"settings" ,"Info", R.layout.settings);
            }
          });
        
        imgBtnModesMenu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View arg0) {
        	  
        	  fnkGetLocation(R.id.imgBtnScreens);
      
            //Open popup window
            if (p != null)
            	MenushowPopup(MainActivity.this, p , R.layout.modes_menu , "modes_menu");
          }
        });

// ImageButton larýn Kodlarýnýn Baþlangýcý Sonu
        

///////////// thread lerin baþlama yeri
        

thfnkListenMisSchPort = new Thread(new Runnable()
        {
                // Setup the run() method that is called when the background thread
                // is started.
                public void run()
                {
                	ServerSocket ss = null;
         	    	 Boolean end = false;
         	    	
         	    	 
         	    	try {
         	    				ss = new ServerSocket(Integer.parseInt(prMisSchPort));
         	    		
         	    				while(!end){	
         						
         	    					Socket s = ss.accept();
    	       						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	    						
    	       						String incomingMsg = in.readLine() ;//+ System.getProperty("line.separator");
    	       						s.close();
          							       						
    	       						Message msg = threadHandlerthfnkListenMisSchPort.obtainMessage();
    	      						msg.obj = incomingMsg;
    	      						threadHandlerthfnkListenMisSchPort.sendMessage(msg);
         	    				}     
         					        ss.close();
         				
         	    	} catch (Exception e) {
//         	    		Message msgx = threadHandlerthfnkListenErrorMessage.obtainMessage();
//       					msgx.obj = e.getMessage();
//       					threadHandlerthfnkListenErrorMessage.sendMessage(msgx);
         			}

                }
        }); //thfnkListenMisSchPort Sonu
        
        thfnkListenMisSchPort.start();  
        
 
        thfnkListenDEDPort = new Thread(new Runnable()
        {
                // Setup the run() method that is called when the background thread
                // is started.
                public void run()
                {
                	ServerSocket ss = null;
         	    	 Boolean end = false;
         	    	
         	    	 
         	    	try {
         	    				ss = new ServerSocket(Integer.parseInt(prDEDPort));
         	    		
         	    				while(!end){	
         						
         	    					Socket s = ss.accept();
    	       						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	    						
    	       						String incomingMsg = in.readLine() ;//+ System.getProperty("line.separator");
    	       						s.close();
          							       						
    	       						Message msg = threadHandlerthfnkListenDEDPort.obtainMessage();
    	      						msg.obj = incomingMsg;
    	      						threadHandlerthfnkListenDEDPort.sendMessage(msg);
         	    				}     
         					        ss.close();
         				
         	    	} catch (Exception e) {
//         	    		Message msgx = threadHandlerthfnkListenErrorMessage.obtainMessage();
//       					msgx.obj = e.getMessage();
//       					threadHandlerthfnkListenErrorMessage.sendMessage(msgx);
         			}

                }
        }); //thfnkListenDEDPort Sonu
        
        thfnkListenDEDPort.start();  
        

        thfnkListenRWRPort = new Thread(new Runnable()
        {
                // Setup the run() method that is called when the background thread
                // is started.
                public void run()
                {
                	ServerSocket ss = null;
         	    	 Boolean end = false;
         	    	
        	    	 
         	    	try {
         	    				ss = new ServerSocket(Integer.parseInt(prRWRPort));
         	    		
         	    				while(!end){	
         						
         	    					Socket s = ss.accept();
    	       						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	    						
    	       						String incomingMsg = in.readLine() ;//+ System.getProperty("line.separator");
    	       						s.close();
          							       						
    	       						Message msg = threadHandlerthfnkListenRWRPort.obtainMessage();
    	      						msg.obj = incomingMsg;
    	      						threadHandlerthfnkListenRWRPort.sendMessage(msg);
         	    				}     
         					        ss.close();
         				
         	    	} catch (Exception e) {
//         	    		Message msgx = threadHandlerthfnkListenErrorMessage.obtainMessage();
//       					msgx.obj = e.getMessage();
//       					threadHandlerthfnkListenErrorMessage.sendMessage(msgx);
         			}

                }
        }); //thfnkListenRWRPort Sonu
        
        thfnkListenRWRPort.start();  
        
    	thfnkListenMsgPort = new Thread(new Runnable()
        {
                // Setup the run() method that is called when the background thread
                // is started.
                public void run()
                {
                	ServerSocket ss = null;
         	    	 Boolean end = false;
         	    	
         	    	 
         	    	try {
         	    				ss = new ServerSocket(Integer.parseInt(prMsgPort));
         	    		
         	    				while(!end){	
         						
   	       						Socket s = ss.accept();
   	       						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
   	    						
   	       						String incomingMsg = in.readLine() ;//+ System.getProperty("line.separator");
   	       						s.close();
         							       						
	   	       					Message msg = threadHandlerthfnkListenMsgPort.obtainMessage();
	   	    					msg.obj = incomingMsg;
	   	    					threadHandlerthfnkListenMsgPort.sendMessage(msg);
	   	    					
   	      						
         	    				}     
         					        ss.close();
         				
         	    	} catch (Exception e) {
//         	    		Message msgx = threadHandlerthfnkListenErrorMessage.obtainMessage();
//       					msgx.obj = e.getMessage();
//       					threadHandlerthfnkListenErrorMessage.sendMessage(msgx);
         			}

                }
        }); //thfnkListenMsgPort Sonu
        
        thfnkListenMsgPort.start();  

        
        thfnkListenRMFDPort = new Thread(new Runnable()
        {
                // Setup the run() method that is called when the background thread
                // is started.
                public void run()
                {
                	ServerSocket ss = null;
         	    	 Boolean end = false;
         	    	
        	    	try {
         	    				ss = new ServerSocket(Integer.parseInt(prRMFDPort));
         	    		
         	    				while(!end){	
         						
         	    					Socket s = ss.accept();
    	       						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	    						
    	       						String incomingMsg = in.readLine() ;//+ System.getProperty("line.separator");
    	       						s.close();
          							       						
    	       						Message msg = threadHandlerthfnkListenRMFDPort.obtainMessage();
    	      						msg.obj = incomingMsg;
    	      						threadHandlerthfnkListenRMFDPort.sendMessage(msg);
         	    				}     
         					        ss.close();
         				
         	    	} catch (Exception e) {
//         	    		Message msgx = threadHandlerthfnkListenErrorMessage.obtainMessage();
//       					msgx.obj = e.getMessage();
//       					threadHandlerthfnkListenErrorMessage.sendMessage(msgx);
         			}

                }
        }); //thfnkListenRMFDPort Sonu
        
        thfnkListenRMFDPort.start();  

        
        thfnkListenLMFDPort = new Thread(new Runnable()
        {
                // Setup the run() method that is called when the background thread
                // is started.
                public void run()
                {
                	ServerSocket ss = null;
         	    	 Boolean end = false;
         	    	
         	    	try {
         	    				ss = new ServerSocket(Integer.parseInt(prLMFDPort));
         	    		
         	    				while(!end){	
         						
         	    					Socket s = ss.accept();
    	       						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	    						
    	       						String incomingMsg = in.readLine() ;//+ System.getProperty("line.separator");
    	       						s.close();
          							       						
    	       						Message msg = threadHandlerthfnkListenLMFDPort.obtainMessage();
    	      						msg.obj = incomingMsg;
    	      						threadHandlerthfnkListenLMFDPort.sendMessage(msg);
	   	    					
   	      						
         	    				}     
         					        ss.close();
         				
         	    	} catch (Exception e) {
//         	    		Message msgx = threadHandlerthfnkListenErrorMessage.obtainMessage();
//       					msgx.obj = e.getMessage();
//       					threadHandlerthfnkListenErrorMessage.sendMessage(msgx);
         			}

                }
        }); //thfnkListenLMFDPort Sonu
        
        thfnkListenLMFDPort.start();  

        
        
/////////// thread lerin sonu        
      
        //Tuþlarýn Default Deðerleri Ayarlanacak
			mview_flipperROLL.showNext();
			vrROLL = 0;
			
			mview_flipperPITCH.showNext();
			vrPITCH = 0;
			
			mview_flipperICPSAGJS.showNext();
		//Tuþlarýn Default Deðerleri Ayarlanacak	
			
		//PnlYedek
			imgBtnYardimciKapat =(ImageButton)findViewById(R.id.imgBtnYardimciKapat);
		//PnlYedek Sonu		
			
			
		//Menu
			imgBtnMenuSecLMFD =(ImageButton)findViewById(R.id.imgBtnMenuSecLMFD);
			imgBtnMenuSecRMFD =(ImageButton)findViewById(R.id.imgBtnMenuSecRMFD);
			imgBtnMenuSecICP =(ImageButton)findViewById(R.id.imgBtnMenuSecICP);
			imgBtnHome =(ImageButton)findViewById(R.id.imgBtnHome);
			imgBtnMenuSecRWR =(ImageButton)findViewById(R.id.imgBtnMenuSecRWR);
			btnMenuMisSch =(ImageButton)findViewById(R.id.btnMenuMisSch);
			
		//Menu sonu	
			
		//Pnl A/B
			imgBtnAB =(ImageButton)findViewById(R.id.imgBtnAB);
			imgBtnGear  =(ImageButton)findViewById(R.id.imgBtnGear);
		//Pnl A/B Sonu	
			
		//ICP
	        
	        btna_a =(ImageButton)findViewById(R.id.icp_aa);
	        btna_g =(ImageButton)findViewById(R.id.icp_ag);
	        btnCom1 =(ImageButton)findViewById(R.id.icp_com1);
	        btnCom2 =(ImageButton)findViewById(R.id.icp_com2);
	        btnIIF =(ImageButton)findViewById(R.id.icp_iff);
	        btnList =(ImageButton)findViewById(R.id.icp_list);
	        
	        btnIcp0 =(ImageButton)findViewById(R.id.icp_0);
	        btnIcp1 =(ImageButton)findViewById(R.id.icp_1);
	        btnIcp2 =(ImageButton)findViewById(R.id.icp_2);
	        btnIcp3 =(ImageButton)findViewById(R.id.icp_3);
	        btnIcp4 =(ImageButton)findViewById(R.id.icp_4);
	        btnIcp5 =(ImageButton)findViewById(R.id.icp_5);
	        btnIcp6 =(ImageButton)findViewById(R.id.icp_6);
	        btnIcp7 =(ImageButton)findViewById(R.id.icp_7);
	        btnIcp8 =(ImageButton)findViewById(R.id.icp_8);
	        btnIcp9 =(ImageButton)findViewById(R.id.icp_9);
	        
	        btnIcpRtrn =(ImageButton)findViewById(R.id.icp_rtn);
	        btnIcpSeq =(ImageButton)findViewById(R.id.icp_seq);
	        btnIcpUp =(ImageButton)findViewById(R.id.icp_js_up);
	        btnIcpDown =(ImageButton)findViewById(R.id.icp_js_down);
	        
	        btnIcpNext =(ImageButton)findViewById(R.id.icp_next );
	        btnIcpPrev =(ImageButton)findViewById(R.id.icp_prev);
	        
	        
	        
	         btnIcpRcl =(ImageButton)findViewById(R.id.icp_rcl);
	         btnIcpEnter =(ImageButton)findViewById(R.id.icp_entr);
	         
	       //RWR için
		        imgIcpMenuSwitch =(ImageButton)findViewById(R.id.imgIcpMenuSwitch);
	        
	    // ICP Sonu 
			
		//Üst Panel
		        imgBntMainSwitch =(ImageButton)findViewById(R.id.imgBntMainSwitch);
		        
		        
		        txtDataFlrCnt =(TextView)findViewById(R.id.txtDataFlrCnt);
		        txtDataChftCnt =(TextView)findViewById(R.id.txtDataChftCnt);
		        txtDataTotalFuel =(TextView)findViewById(R.id.txtDataTotalFuel);
				txtDataFuelFlow =(TextView)findViewById(R.id.txtDataFuelFlow);
				txtAirSpead = (TextView)findViewById(R.id.txtAirSpead);
				txtAltitute = (TextView)findViewById(R.id.txtAltitute);
				txtCurrentHeading = (TextView)findViewById(R.id.txtCurrentHeading);
				
				textView1 = (TextView)findViewById(R.id.textView1);
				txtPnlTardimciTop = (TextView)findViewById(R.id.txtPnlTardimciTop);
		        
		//Üst Panel sonu        
		        
		//Sol MFD
	          btnSol1 =(ImageButton)findViewById(R.id.mfdSol1);
	          btnSol2 =(ImageButton)findViewById(R.id.mfdSol2);
	          btnSol3 =(ImageButton)findViewById(R.id.mfdSol3);
	          btnSol4 =(ImageButton)findViewById(R.id.mfdSol4);
	          btnSol5 =(ImageButton)findViewById(R.id.mfdSol5);
	          btnSol6 =(ImageButton)findViewById(R.id.mfdSol6);
	          btnSol7 =(ImageButton)findViewById(R.id.mfdSol7);
	          btnSol8 =(ImageButton)findViewById(R.id.mfdSol8);
	          btnSol9 =(ImageButton)findViewById(R.id.mfdSol9);
	          btnSol10 =(ImageButton)findViewById(R.id.mfdSol10);
	        
	          btnSol11 =(ImageButton)findViewById(R.id.mfdSol11);
	          btnSol12 =(ImageButton)findViewById(R.id.mfdSol12);
	          btnSol13 =(ImageButton)findViewById(R.id.mfdSol13);
	          btnSol14 =(ImageButton)findViewById(R.id.mfdSol14);
	          btnSol15 =(ImageButton)findViewById(R.id.mfdSol15);
	          btnSol16 =(ImageButton)findViewById(R.id.mfdSol16);
	          btnSol17 =(ImageButton)findViewById(R.id.mfdSol17);
	          btnSol18 =(ImageButton)findViewById(R.id.mfdSol18);
	          btnSol19 =(ImageButton)findViewById(R.id.mfdSol19);
	          btnSol20 =(ImageButton)findViewById(R.id.mfdSol20);
	  //Sol MFD Sonu	
	          
	          //Sag MFD
	          btnSag1 =(ImageButton)findViewById(R.id.mfdSag1);
	          btnSag2 =(ImageButton)findViewById(R.id.mfdSag2);
	          btnSag3 =(ImageButton)findViewById(R.id.mfdSag3);
	          btnSag4 =(ImageButton)findViewById(R.id.mfdSag4);
	          btnSag5 =(ImageButton)findViewById(R.id.mfdSag5);
	          btnSag6 =(ImageButton)findViewById(R.id.mfdSag6);
	          btnSag7 =(ImageButton)findViewById(R.id.mfdSag7);
	          btnSag8 =(ImageButton)findViewById(R.id.mfdSag8);
	          btnSag9 =(ImageButton)findViewById(R.id.mfdSag9);
	          btnSag10 =(ImageButton)findViewById(R.id.mfdSag10);
	        
	          btnSag11 =(ImageButton)findViewById(R.id.mfdSag11);
	          btnSag12 =(ImageButton)findViewById(R.id.mfdSag12);
	          btnSag13 =(ImageButton)findViewById(R.id.mfdSag13);
	          btnSag14 =(ImageButton)findViewById(R.id.mfdSag14);
	          btnSag15 =(ImageButton)findViewById(R.id.mfdSag15);
	          btnSag16 =(ImageButton)findViewById(R.id.mfdSag16);
	          btnSag17 =(ImageButton)findViewById(R.id.mfdSag17);
	          btnSag18 =(ImageButton)findViewById(R.id.mfdSag18);
	          btnSag19 =(ImageButton)findViewById(R.id.mfdSag19);
	          btnSag20 =(ImageButton)findViewById(R.id.mfdSag20);
//Sag MFD Sonu			          
        
	          
	          
	        //PnlYedek
				imgBtnYardimciKapat.setOnClickListener(new View.OnClickListener() {					
					public void onClick(View v) {
						pnlYardimci.setVisibility(View.INVISIBLE);						
					}
				});
			//PnlYedek Sonu	
				
				
				
			//Menu
				
				btnMenuMisSch.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						//Mis Sch Aç
						startActivity(new Intent("com.arifkomurculer.falconmfdmultitablet.mis_sch"));					
					}
				});
				
				imgBtnMenuSecLMFD.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						pnlSolMFD.setVisibility(View.VISIBLE);
						pnlYardimci.setVisibility(View.VISIBLE);
						vrMenu = "pnlSolMFD";
						pnlMenu.setVisibility(View.INVISIBLE);
						vrHangiTus = "CmdStartSolMFD" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
						fnkTusGonder(vrHangiTus);						
					}
				});
				
				imgBtnMenuSecRMFD.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						pnlSagMFD.setVisibility(View.VISIBLE);
						pnlYardimciSag.setVisibility(View.VISIBLE);
						vrMenu = "pnlSagMFD";
						pnlMenu.setVisibility(View.INVISIBLE);
						vrHangiTus = "CmdStartSagMFD" + "@" + prMsgPort + "@" + prRMFDPort + "@" + prRefreshRate;
						fnkTusGonder(vrHangiTus);						
					}
				});
				
				
				imgBtnMenuSecICP.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						pnlICP_DEDDis.setVisibility(View.VISIBLE);
						vrMenu = "pnlICP_DEDDis";
						pnlMenu.setVisibility(View.INVISIBLE);
						vrHangiTus = "CmdStartDED" + "@" + prMsgPort + "@" + prDEDPort + "@" + prRefreshRate;
						fnkTusGonder(vrHangiTus);						
					}
				});
				
				imgBtnMenuSecRWR.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						pnlRWR.setVisibility(View.VISIBLE);
						vrMenu = "pnlRWR";
						pnlMenu.setVisibility(View.INVISIBLE);
						vrHangiTus = "CmdStartRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
						fnkTusGonder(vrHangiTus);						
					}
				});
				
				
				
				imgBtnHome.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						pnlICP_DEDDis.setVisibility(View.INVISIBLE);
						pnlSolMFD.setVisibility(View.INVISIBLE);
						pnlYardimci.setVisibility(View.INVISIBLE);
						pnlYardimciSag.setVisibility(View.INVISIBLE);
						pnlSagMFD.setVisibility(View.INVISIBLE);
						pnlRWR.setVisibility(View.INVISIBLE);
						
						pnlMenu.setVisibility(View.VISIBLE);
						
						if (vrMenu == "pnlICP_DEDDis")
						{
							vrHangiTus = "CmdStopDED" + "@" + prMsgPort + "@" + prDEDPort + "@" + prRefreshRate;
							fnkTusGonder(vrHangiTus);
						}
						
						if (vrMenu == "pnlSolMFD")
						{
							vrHangiTus = "CmdStopSolMFD" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
							fnkTusGonder(vrHangiTus);
						}
						
						if (vrMenu == "pnlSagMFD")
						{
							vrHangiTus = "CmdStopSagMFD" + "@" + prMsgPort + "@" + prRMFDPort + "@" + prRefreshRate;
							fnkTusGonder(vrHangiTus);
						}
						if (vrMenu == "pnlRWR")
						{
							vrHangiTus = "CmdStopRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
							fnkTusGonder(vrHangiTus);
						}
												
					}
				});
				
			//Menu Sonu	
	          
	        //Pnl A/B
				imgBtnAB.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "AFBrakesToggle";
						fnkTusGonder(vrHangiTus);						
					}
				});
			//Pnl A/B Sonu	
				
		        
				//Pnl A/B
					imgBtnGear.setOnClickListener(new View.OnClickListener() {
						
						public void onClick(View v) {
							vrHangiTus = "AFGearToggle";
							fnkTusGonder(vrHangiTus);								
						}
					});
				//Pnl A/B Sonu		
				
				
	        //ICP Tuþlarý
		        

		        
		        
		        
		        btnIcpPrev.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPPrevious";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcpNext.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPNext";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btna_a.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPAA";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btna_g.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPAG";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnCom1.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPCom1";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnCom2.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPCom2";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIIF.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPIFF";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnList.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPLIST";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        
		        
		        btnIcp0.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPZERO";
						fnkTusGonder(vrHangiTus);
						
					}
				});


		        btnIcp1.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPTILS";
						fnkTusGonder(vrHangiTus);
						
					}
				});


		        btnIcp2.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPALOW";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcp3.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPTHREE";
						fnkTusGonder(vrHangiTus);
						
					}
				});


		        btnIcp4.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPStpt";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcp5.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPCrus";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcp6.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPSIX";
						fnkTusGonder(vrHangiTus);
						
					}
				});


		        btnIcp7.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPMark";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcp8.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPEIGHT";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcp9.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPNINE";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcpEnter.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPEnter";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnIcpRcl.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPCLEAR";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        
		        btnIcpRtrn.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPResetDED";
						fnkTusGonder(vrHangiTus);
						
					}
				});

		        
		        btnIcpUp.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPDEDUP";
						fnkTusGonder(vrHangiTus);
						
					}
				});

		        
		        btnIcpDown.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPDEDDOWN";
						fnkTusGonder(vrHangiTus);
						
					}
				});


		        btnIcpSeq.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimICPDEDSEQ";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        //RWR için
		        
		        imgIcpMenuSwitch.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) 
						{
							
						
							if (vrRWRTaklit=="RWR")
								{
									vrRWRTaklit="LMFD";
									
									String uri = "@drawable/menu_switch_lmfd";
									int imageResource = getResources().getIdentifier(uri, null, getPackageName());
									Drawable res = getResources().getDrawable(imageResource);
									imgIcpMenuSwitch.setImageDrawable(res);
								}
							else if (vrRWRTaklit=="LMFD")
								{
									vrRWRTaklit="RMFD";
									String uri = "@drawable/menu_switch_rmfd";
									int imageResource = getResources().getIdentifier(uri, null, getPackageName());
									Drawable res = getResources().getDrawable(imageResource);
									imgIcpMenuSwitch.setImageDrawable(res);
								}
							else if (vrRWRTaklit=="RMFD")
								{
									vrRWRTaklit="RWR";
									String uri = "@drawable/menu_switch_rwr";
									int imageResource = getResources().getIdentifier(uri, null, getPackageName());
									Drawable res = getResources().getDrawable(imageResource);
									imgIcpMenuSwitch.setImageDrawable(res);
								}
							
							fnkTaklitDegistir(vrRWRTaklit);
						}
				});
		        
	//ICP Tuþlarý			 
	          
	       //Sol MFD Baþý
	          btnSol1.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						//fnkSolTarafiKucult();
						
						vrHangiTus = "SimCBEOSB_1L";
						fnkTusGonder(vrHangiTus);						
					}
				});
	          
	          btnSol2.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimCBEOSB_2L";
						 fnkTusGonder(vrHangiTus);
						
						
						
					}
				});
		        
		        
		        btnSol3.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_3L";
						 fnkTusGonder(vrHangiTus);
						
						
						
					}
				});
		        
		        btnSol4.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_4L";
						 fnkTusGonder(vrHangiTus);
										
						
					}
				});
		        
		        
		        btnSol5.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_5L";
						 fnkTusGonder(vrHangiTus);
						
						
						
					}
				});
		        
		        
		        btnSol6.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_6L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});

		        
		        btnSol7.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_7L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol8.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_8L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnSol9.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_9L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol10.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_10L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol11.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_11L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol12.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_12L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol13.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_13L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol14.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_14L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol15.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_15L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol16.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_16L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnSol17.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_17L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol18.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_18L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        btnSol19.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_19L";
						 fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnSol20.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimCBEOSB_20L";
						 fnkTusGonder(vrHangiTus);
						
															
						}
				});
		        
	          
	          
	          
	        //Sol MFD Sonu  
	          
	     //Sað MFD Baþý
	          btnSag1.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						//fnkSagTarafiKucult();
						
						vrHangiTus = "SimCBEOSB_1R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        
		        btnSag2.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimCBEOSB_2R";
						fnkTusGonder(vrHangiTus);
						
					}
				});
		        
		        
		        btnSag3.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_3R";
						fnkTusGonder(vrHangiTus);

						
						
					}
				});
		        
		        btnSag4.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_4R";
						fnkTusGonder(vrHangiTus);
					
						
					}
				});
		        
		        
		        btnSag5.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_5R";
						fnkTusGonder(vrHangiTus);

						
						
					}
				});
		        
		        
		        btnSag6.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_6R";
						fnkTusGonder(vrHangiTus);

					}
				});

		        
		        btnSag7.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_7R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag8.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_8R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        
		        btnSag9.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_9R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag10.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_10R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag11.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_11R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag12.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_12R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag13.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_13R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag14.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_14R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag15.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_15R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag16.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_16R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        
		        btnSag17.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_17R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag18.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_18R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        btnSag19.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						vrHangiTus = "SimCBEOSB_19R";
						fnkTusGonder(vrHangiTus);

					}
				});
		        
		        
		        btnSag20.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						
						vrHangiTus = "SimCBEOSB_20R";
						fnkTusGonder(vrHangiTus);

															
						}
				});
	          
	     //Sað MFD Sonu
		        
		        
		        imgBntMainSwitch.setOnClickListener(new View.OnClickListener() {
						
						public void onClick(View v) {
							if (vrBntMainSwitch == 1 ) //ICP ise MFDler Açýlacak
								{
									imgMFDSagIc =  (ImageView) findViewById(R.id.imgMFDSagIc );
									imgMFDSolIc =  (ImageView) findViewById(R.id.imgMFDSolIc );
									pnlICP_DEDDis.setVisibility(View.INVISIBLE);
									pnlMFD.setVisibility(View.VISIBLE);
									
									vrBntMainSwitch = 0; //	MFD
									
									String uri = "@drawable/main_menu_switch_icp";
									int imageResource = getResources().getIdentifier(uri, null, getPackageName());
									Drawable res = getResources().getDrawable(imageResource);
									imgBntMainSwitch.setImageDrawable(res);
									
									//RWR DED otomatik Baðlantýyý Kapatacak
								        vrHangiTus = "CmdStopDED" + "@" + prMsgPort + "@" + prDEDPort + "@" + prRefreshRate;
										fnkTusGonder(vrHangiTus);
										
										//try{ Thread.sleep(500); }catch(InterruptedException e){ }
										
										vrHangiTus = "CmdStopRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
										fnkTusGonder(vrHangiTus);
							       //RWR DED otomatik Baðlantýyý Kapatacak Sonu
								}
							else //MFD is ICP ve RWR Açýlacak
								{
									
								vrHangiTus = "CmdStartDED" + "@" + prMsgPort + "@" + prDEDPort + "@" + prRefreshRate;
								fnkTusGonder(vrHangiTus);
								
								//try{ Thread.sleep(500); }catch(InterruptedException e){ }
								
									fnkTaklitDegistir(vrRWRTaklit);
									
									pnlICP_DEDDis.setVisibility(View.VISIBLE);
									pnlMFD.setVisibility(View.INVISIBLE);
									
									
									vrBntMainSwitch = 1; //	ICP
									
									String uri = "@drawable/main_menu_switch_mfd";
									int imageResource = getResources().getIdentifier(uri, null, getPackageName());
									Drawable res = getResources().getDrawable(imageResource);
									imgBntMainSwitch.setImageDrawable(res);
								}
							
							//fnkPopUp("Falcon MFD" , "Have a Falcon Day"  ,"Msg" ,"Info", R.layout.frmpopup);
						}
					});
			      
		        
		   //Servere otomatik Baðlantý Yapacak
//		        vrHangiTus = "CmdStartSagMFD" + "@" + prMsgPort + "@" + prRMFDPort + "@" + prRefreshRate;
//				fnkTusGonder(vrHangiTus);
				
				//try{ Thread.sleep(500); }catch(InterruptedException e){ }
				
//				vrHangiTus = "CmdStartSolMFD" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
//				fnkTusGonder(vrHangiTus);
				
//				For Single Tablet
//				vrHangiTus = "CmdStartFlightData" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
//				fnkTusGonder(vrHangiTus);
				
				
//				For Multi Tablet
//				vrHangiTus = "CmdStartFlightDataLoad" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
//				fnkTusGonder(vrHangiTus);		
//				
//				vrHangiTus = "CmdStartFlightDataNav" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
//				fnkTusGonder(vrHangiTus);
//				
//				vrHangiTus = "CmdStartFlightDataNavMap" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
//				fnkTusGonder(vrHangiTus);
				
		  //Servere otomatik Baðlantý Yapacak Sonu      
		        
		        
		        pnlICP_DEDDis.setVisibility(View.INVISIBLE);
				pnlMFD.setVisibility(View.VISIBLE);
				
				textView1.setVisibility(View.INVISIBLE);

				//txtDataTotalFuel.setText(Float.toString(pnlYardimci.getY()));
				
				//vrPnlYardimciTop = pnlYardimci.getTop();
				vrPnlYardimciTop = Float.valueOf(txtPnlTardimciTop.getText().toString());
				
				
				
				 
				
				
				//pnlYardimci.setY(vrPnlYardimciTop);
//				fnkGlobalData(); // Global Data Bilgilerini Okuyacak
//				
//				fnkGlobalDataWrite();
    } //onCreate Sonu
    
    
//    @Override
//    protected void onPause() {
//        super.onPause();
//        wl.release();
//    }//End of onPause
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        wl.acquire();
//    }//End of onResume


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus ) {
     
//       int[] location = new int[2];
//       ImageButton imgBtnOption = (ImageButton) findViewById(R.id.imgBtnModesMenu);
//     
//       // Get the x, y location and store it in the location[] array
//       // location[0] = x, location[1] = y.
//       imgBtnOption.getLocationOnScreen(location);
//     
//       //Initialize the Point with x, and y positions
//       p = new Point();
//       p.x = location[0];
//       p.y = location[1];
       
       
//    }
    
    
    
    // Fonksiyonlar 
    
    public void fnkTaklitDegistir(String prRWRTaklit)
    {
    	//fnkPopUp("Falcon MFD" ,prRWRTaklit  ,"Msg" ,"Info", R.layout.frmpopup);
    	
        
	 
    	if (prRWRTaklit=="RWR")
		{
			String uri = "@drawable/rwr";
			
			imgMFDSagIc =  (ImageView) findViewById(R.id.imgMFDSagIc );
			imgMFDSolIc =  (ImageView) findViewById(R.id.imgMFDSolIc );

			int imageResource = getResources().getIdentifier(uri, null, getPackageName());
			Drawable res = getResources().getDrawable(imageResource);
			imgRWR.setImageDrawable(res);
			
			vrHangiTus = "CmdStartRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
			fnkTusGonder(vrHangiTus);
			
		}
		else if (prRWRTaklit=="LMFD")
			{
				//
				imgMFDSolIc =  (ImageView) findViewById(R.id.imgRWR );
				imgMFDSagIc =  (ImageView) findViewById(R.id.imgMFDSagIc );
				
				vrHangiTus = "CmdStopRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
//				String uri = "@drawable/icp_com2";
//
//				int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//				Drawable res = getResources().getDrawable(imageResource);
//				imgMFDSolIc.setImageDrawable(res);
			}
		else if (prRWRTaklit=="RMFD")
			{
				//
				imgMFDSagIc =  (ImageView) findViewById(R.id.imgRWR );
				imgMFDSolIc =  (ImageView) findViewById(R.id.imgMFDSolIc );
				
				vrHangiTus = "CmdStopRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
//				String uri = "@drawable/icp_com1";
//
//				int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//				Drawable res = getResources().getDrawable(imageResource);
//				imgMFDSolIc.setImageDrawable(res);
			}
    	
    }
    
    
    
    public void fnkMODE(int prMode)
	{		
		
    	//fnkPopUp("Falcon MFD" ,String.valueOf(prMode)  ,"Msg" ,"Info", R.layout.frmpopup);
    	
    	switch (prMode) {        
		case 0:
        	vrHangiTus = "SimEWSModeOff";
            break;            
		case 1:
        	vrHangiTus = "SimEWSModeStby";            
        	break;
		case 2:
        	vrHangiTus = "SimEWSModeMan";            
        	break;
		case 3:
        	vrHangiTus = "SimEWSModeSemi";            
        	break;
		case 4:
        	vrHangiTus = "SimEWSModeAuto";            
        	break;
		case 5:
        	vrHangiTus = "SimEWSModeByp";            
        	break;	
       
        			}	
		fnkTusGonder(vrHangiTus);
		
	}
    
    public void fnkPRGM(int prPrgm)
	{		
    	//fnkPopUp("Falcon MFD" , prPrgm + ""  ,"Msg" ,"Info", R.layout.frmpopup);
		switch (prPrgm) {        
						case 0:
				        	vrHangiTus = "SimEWSProgDec";
				            break;            
						case 1:
				        	vrHangiTus = "SimEWSProgOne";            
				        	break;
						case 2:
				        	vrHangiTus = "SimEWSProgTwo";            
				        	break;
						case 3:
				        	vrHangiTus = "SimEWSProgThree";            
				        	break;
						case 4:
				        	vrHangiTus = "SimEWSProgFour";            
				        	break;
        			}	
		fnkTusGonder(vrHangiTus);
		
	}
    
    public void fnkTusGonder(String btn )
    {
    	//fnkPopUp("Falcon MFD" , btn + " Pressed"  ,"Msg" ,"Info", R.layout.frmpopup);
    	//runTcpClient(btn , IP );
    	//Çalýþan Hali
    	//vrBtn = btn;
    	
    	//ThSendKey.start();
    	new ThSendKey().execute(btn);    	
    }
    
    public void fnkGetLocation(int btn)
    {
    	
    	int[] location = new int[2];
        ImageButton imgBtnOption = (ImageButton) findViewById(btn);
      
        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        imgBtnOption.getLocationOnScreen(location);
      
        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
        
//        fnkPopUp("Falcon MFD" , p.x + " " + p.y 	            			
//    			,"Msg" ,"Info", R.layout.frmpopup);
        
    } // fnkGetLocation Sonu
  
    
    
 // The method that displays the popup.
    private void MenushowPopup(final Activity context, Point p , int prMenu , String prMenuAdi) 
    {
       
    	
       int popupWidth = 200;
       int popupHeight = 300;
     
       // Inflate the popup_layout.xml
       LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
       LayoutInflater layoutInflater = (LayoutInflater) context
         .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View layout = layoutInflater.inflate( prMenu, viewGroup);
     
       // Creating the PopupWindow
       popup = new PopupWindow(context);
       popup.setContentView(layout);
       popup.setWidth(popupWidth);
       popup.setHeight(popupHeight);
       popup.setFocusable(true);
     
       // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
       int OFFSET_X = 10;
       int OFFSET_Y = 40;
     
       // Clear the default translucent background
       popup.setBackgroundDrawable(new BitmapDrawable());
     
       // Displaying the popup at the specified location, + offsets.
       popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
       
       if (prMenuAdi == "modes_menu")
       {
    	   
    	   	   ImageButton btnMenuAP =(ImageButton)  layout.findViewById(R.id.btnMenuAP);
    	   	   btnMenuAP.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {									
						popup.dismiss();
						pnlYardimci.setVisibility(View.VISIBLE);
						//fnkPopUp("Falcon MFD" , "Have a Falcon Day"  ,"Msg" ,"Info", R.layout.frmpopup);
					}
				});
		       
		       ImageButton btnMenuICP =(ImageButton)  layout.findViewById(R.id.btnMenuMaps);
		       btnMenuICP.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						popup.dismiss();
						
						//Haritalarý Aç
						
//						haritalar.vrX = 0;
//						haritalar.vrY = 0;
//						
//						haritalar.vrHangiHarita = "Balkan";
//						
//						startActivity(new Intent("com.arifkomurculer.falconmfd.haritalar"));
						
						fnkGetLocation(R.id.ImgBtnSettings);
			          	  
			              //Open popup window
			              
			            	  fnkPopUp("Falcon MFD" , ""  ,"maps" ,"Info", R.layout.select_map);
			              
					}
				});
		       
		       
		       
		      
       } 
       
       
       
    } // MenushowPopup Sonu
    
    public void fnkPopUp(String prTitle, final String prMsg, final String prType , final String prTypeInfo , int LayOut  )
    {
    	// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView( LayOut);
		
		
		if (prType == "amblem") 
		{
			
			ImageView imgType = (ImageView) dialog.findViewById(R.id.imgType);
			ImageView imgBaslik = (ImageView) dialog.findViewById(R.id.imgBaslik);
			TextView txtMsg = (TextView) dialog.findViewById(R.id.txtMsg);
			TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitle);
			final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			
			imgType.setImageResource( R.drawable.amblem);
				
			dialogButton.setVisibility(View.INVISIBLE);
			txtMsg.setVisibility(View.INVISIBLE);
			txtTitle.setVisibility(View.INVISIBLE);
			imgBaslik.setVisibility(View.INVISIBLE);
			
			imgType.setOnClickListener(new View.OnClickListener() {			
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		} // amblem Sonu
		
		else if (prType == "Msg")
		{
			ImageView imgType = (ImageView) dialog.findViewById(R.id.imgType);
			ImageView imgBaslik = (ImageView) dialog.findViewById(R.id.imgBaslik);
			TextView txtMsg = (TextView) dialog.findViewById(R.id.txtMsg);
			TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitle);
			final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			
			txtTitle.setText(prTitle);
			txtMsg.setText(prMsg);
			
			if (prTypeInfo=="Info") 
			{
				imgType.setImageResource( R.drawable.type_info);
			}
			else if (prTypeInfo=="question") 
			{
				imgType.setImageResource( R.drawable.type_question);
			}
			else if (prTypeInfo=="Error") 
			{
				imgType.setImageResource( R.drawable.type_error);
			}
			else if (prTypeInfo=="attention") 
			{
				imgType.setImageResource( R.drawable.type_attention);
			}
			
			
			
			if (prMsg == "Have a Falcon Day") 
			{
				
				
				vrHangiTus = "CmdStopSagMFD" + "@" + prMsgPort + "@" + prRMFDPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
				//try{ Thread.sleep(500); }catch(InterruptedException e){ }
				
				vrHangiTus = "CmdStopSolMFD" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
				vrHangiTus = "CmdStopFlightData" + "@" + prMsgPort + "@" + prLMFDPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
				vrHangiTus = "CmdStopDED" + "@" + prMsgPort + "@" + prDEDPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
				vrHangiTus = "CmdStopRWR" + "@" + prMsgPort + "@" + prRWRPort + "@" + prRefreshRate;
				fnkTusGonder(vrHangiTus);
				
				ImageView imgGoodBy = (ImageView) dialog.findViewById(R.id.imgGoodBy);
				imgGoodBy.setImageResource( R.drawable.emegi_gecenler);
				dialogButton.setText("Good Bye");				
			};
			
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (prMsg == "Have a Falcon Day") 
					{
						System.exit(0);
					}
					else
					{
						dialog.dismiss();
					}	
					
				}
			});
		}// msg Sonu
		else if (prType == "settings")
		{
			final Button dialogButton = (Button) dialog.findViewById(R.id.btnSettingSave);
			final ImageButton BtnSettingsExit = (ImageButton) dialog.findViewById(R.id.BtnSettingsExit);
			
			
			final TextView txtAndroidIP = (TextView) dialog.findViewById(R.id.txtAndroidIP);
			final TextView txtServerIP = (TextView) dialog.findViewById(R.id.txtServerIP);
			final TextView txtServerPort = (TextView) dialog.findViewById(R.id.txtServerPort);
			
			final TextView txtLMFDPort = (TextView) dialog.findViewById(R.id.txtLMFDPort);
			final TextView txtRMFDPort = (TextView) dialog.findViewById(R.id.txtRMFDPort);
			final TextView txtDEDPort = (TextView) dialog.findViewById(R.id.txtDEDPort);
			
			final TextView txtMisSchPort = (TextView) dialog.findViewById(R.id.txtMisSchPort);
			final TextView txtRWRPort = (TextView) dialog.findViewById(R.id.txtRWRPort);
			final TextView txtRefreshRate = (TextView) dialog.findViewById(R.id.txtRefreshRate);
			
			txtServerIP.setText(prServerIP);
			txtServerPort.setText(prServerPort);
			txtLMFDPort.setText(prLMFDPort);
			txtRMFDPort.setText(prRMFDPort);
			txtDEDPort.setText(prDEDPort);
			txtMisSchPort.setText(prMisSchPort);
			txtRWRPort.setText(prRWRPort);
			txtRefreshRate.setText(prRefreshRate);
			
			txtAndroidIP.setText(getIPAddress(true));
			
			BtnSettingsExit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			
			// if button is clicked, close the custom dialog
						dialogButton.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								
								
							prServerIP = txtServerIP.getText().toString();
							prServerPort = txtServerPort.getText().toString();
							prMsgPort	= txtServerPort.getText().toString();
							prLMFDPort = txtLMFDPort.getText().toString();
							prRMFDPort = txtRMFDPort.getText().toString();
							prDEDPort =	txtDEDPort.getText().toString();
							prMisSchPort	= txtMisSchPort.getText().toString();
							prRWRPort	= txtRWRPort.getText().toString();							
							prRefreshRate = txtRefreshRate.getText().toString();
							
							fnkCreateXML("write");
							}
						});
			
			
		}// settings Sonu
		else if (prType == "maps")
		{
			final Button btnMapKorea = (Button) dialog.findViewById(R.id.btnMapKorea);
			final Button btnMapBalkan = (Button) dialog.findViewById(R.id.btnMapBalkan);
			final Button btnMapAegean = (Button) dialog.findViewById(R.id.btnMapAegean);
			final Button btnMapIsrail = (Button) dialog.findViewById(R.id.btnMapIsrail);
			
			btnMapKorea.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					haritalar.vrX = 0;
					haritalar.vrY = 0;
					
					haritalar.vrHangiHarita = "Korea";
					
					startActivity(new Intent("com.arifkomurculer.falconmfdmultitablet.haritalar"));
				}
			});
			
			btnMapBalkan.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					haritalar.vrX = 0;
					haritalar.vrY = 0;
					
					haritalar.vrHangiHarita = "Balkan";
					
					startActivity(new Intent("com.arifkomurculer.falconmfdmultitablet.haritalar"));

				}
			});
			
			
			btnMapAegean.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					haritalar.vrX = 0;
					haritalar.vrY = 0;
					
					haritalar.vrHangiHarita = "Aegean";
					
					startActivity(new Intent("com.arifkomurculer.falconmfdmultitablet.haritalar"));

				}
			});
			
			btnMapIsrail.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					haritalar.vrX = 0;
					haritalar.vrY = 0;
					
					haritalar.vrHangiHarita = "Israil";
					
					startActivity(new Intent("com.arifkomurculer.falconmfdmultitablet.haritalar"));

				}
			});

			
		}// settings Sonu

		

		dialog.show();
    } // fnkPopUp Sonu
    
    
    
    public void fnkGetScreen()
	{
		DisplayMetrics vrResolution = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(vrResolution);
		
		int vrHeight = vrResolution.heightPixels;
		int vrWidth = vrResolution.widthPixels;
		
				 txtWidth =(TextView)findViewById(R.id.txtWidth);
				 txtHeight =(TextView)findViewById(R.id.txtHeight);
				 
				 txtWidth.setText( "W : " + vrWidth);
				 txtHeight.setText( "H : " + vrHeight);
	} // fnkGetScreen Sonu

    	public String BitMapToString(Bitmap bitmap)
    	{
	        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
	        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
	        byte [] b=baos.toByteArray();
	        String temp=Base64.encodeToString(b, Base64.DEFAULT);
	        return temp;
    	} // BitMapToString Sonu
    	
    	public Bitmap StringToBitMap(String encodedString)
    	{
    	     try{
    	       byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
    	       Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
    	       return bitmap;
    	     }catch(Exception e){
    	       e.getMessage();
    	       return null;
    	     }
    	} // StringToBitMap Sonu
    	
    	
    	//prType oto : Read yapýlýnca yaratýldý
    	public void fnkCreateXML(String prType)
    	{
//Get app path
	        
	        PackageManager m = getPackageManager();
	        String s = getPackageName();
	        try {
	            PackageInfo p = m.getPackageInfo(s, 0);
	            s = p.applicationInfo.dataDir;
	            
	            File newxmlfile = new File(s+"/connection_params.xml");
	            newxmlfile.createNewFile();
	            
	            FileOutputStream fileos = null;
	            fileos = new FileOutputStream(newxmlfile);
	            
	            XmlSerializer serializer = Xml.newSerializer();
	            
	            serializer.setOutput(fileos, "UTF-8");
                //Write <?xml declaration with encoding (if encoding not null) and standalone flag (if standalone not null)
                serializer.startDocument(null, Boolean.valueOf(true));
                //set indentation option
                serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                //start a tag called "root"
                serializer.startTag(null, "ServerInfo");
						serializer.startTag(null, "prServerIP");
		                serializer.text(prServerIP);
		                serializer.endTag(null, "prServerIP");
		                
		                serializer.startTag(null, "prServerPort");
		                serializer.text(prServerPort);
		                serializer.endTag(null, "prServerPort");
		                
		                serializer.startTag(null, "prMsgPort");
		                serializer.text(prMsgPort);
		                serializer.endTag(null, "prMsgPort");
		                
		                serializer.startTag(null, "prLMFDPort");
		                serializer.text(prLMFDPort);
		                serializer.endTag(null, "prLMFDPort");
		                
		                serializer.startTag(null, "prRMFDPort");
		                serializer.text(prRMFDPort);
		                serializer.endTag(null, "prRMFDPort");
		                
		                serializer.startTag(null, "prDEDPort");
		                serializer.text(prDEDPort);
		                serializer.endTag(null, "prDEDPort");
		                
		                serializer.startTag(null, "prMisSchPort");
		                serializer.text(prMisSchPort);
		                serializer.endTag(null, "prMisSchPort");
		                
		                serializer.startTag(null, "prRWRPort");
		                serializer.text(prRWRPort);
		                serializer.endTag(null, "prRWRPort");
		                
		                serializer.startTag(null, "prRefreshRate");
		                serializer.text(prRefreshRate);
		                serializer.endTag(null, "prRefreshRate");
                       
                serializer.endTag(null, "ServerInfo");
                serializer.endDocument();
                //write xml data into the FileOutputStream
                serializer.flush();
                //finally we close the file stream
                fileos.close();
                
                if (prType == "read")
                {
                	fnkPopUp("Falcon MFD" , "Your Connection Data Created Successfully . . ."  
	            			+ "\n" + "To Change Data Open 'Settings' Menu"	            			
	            			,"Msg" ,"Info", R.layout.frmpopup);
                	
                	return;
                }
                
                
	            fnkPopUp("Falcon MFD" , "Your Connection Data Saved Successfully . . ." 
	            		+ "\n" + "To Run Properly Please Restart Falcon MFD Program"
	            		,"Msg" ,"Info", R.layout.frmpopup);
	            
	        } catch (Exception e) {
	        	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
	        }
	        
    	} //fnkCreateXML Sonu
    	
    	public void fnkReadXML()
    	{
    		PackageManager m = getPackageManager();
	        String s = getPackageName();
	        try {
	            PackageInfo p = m.getPackageInfo(s, 0);
	            s = p.applicationInfo.dataDir;
	            
	            File newxmlfile = new File(s+"/connection_params.xml");
	            
	            if( !newxmlfile.exists())
	    		{
	            	//Default Deðerleri 
	            	 prServerIP = "192.168.0.31"; 
					 prServerPort = "21111"; 
					 prMsgPort = "21111";
					 prLMFDPort = "21112"; 
					 prRMFDPort = "21113";
					 prDEDPort = "21114";	
					 prMisSchPort = "21115";
					 prRWRPort = "21116";
					 prRefreshRate = "100";
	            	
					 //Dosya bulunamadý yaratýlacak
	            	fnkCreateXML("read");
	            	
	            	return;
	    		}
	            
	            
	            
	            //xml okuma kýsmý
	            
	            InputStream inputStream = new FileInputStream(newxmlfile);
	            
	               
	   			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(inputStream,null);
                int eventType = xpp.getEventType();
                
                while (eventType != XmlPullParser.END_DOCUMENT) 
                {
                    if(eventType == XmlPullParser.START_DOCUMENT) 
                     { 
                     }                 
                    else if(eventType == XmlPullParser.START_TAG)
                    {
                    	try 
                        {                          
                                if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prServerIP"))
                                 {
                                      eventType = xpp.next();
                                      prServerIP = xpp.getText().toString();
                                 }
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prServerPort"))
                                 {
                                      eventType = xpp.next();
                                      prServerPort = xpp.getText().toString();
                                 } 
                                
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prMsgPort"))
                                 {
                                      eventType = xpp.next();
                                      prMsgPort = xpp.getText().toString();
                                 }    	                                    
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prLMFDPort"))
                                 {
                                      eventType = xpp.next();
                                      prLMFDPort = xpp.getText().toString();
                                 }    
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prRMFDPort"))
                                 {
                                      eventType = xpp.next();
                                      prRMFDPort = xpp.getText().toString();
                                 }
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prDEDPort"))
                                 {
                                      eventType = xpp.next();
                                      prDEDPort = xpp.getText().toString();
                                 }
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prMisSchPort"))
                                 {
                                      eventType = xpp.next();
                                      prMisSchPort = xpp.getText().toString();
                                 }
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prRWRPort"))
                                 {
                                      eventType = xpp.next();
                                      prRWRPort = xpp.getText().toString();
                                 }
                                else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("prRefreshRate"))
                                 {
                                      eventType = xpp.next();
                                      prRefreshRate = xpp.getText().toString();
                                 }                                                                
                           } 
                        catch (Exception e) 
                           {
                               //e.printStackTrace();
                           }
                    }
                    
                    eventType = xpp.next();       
            }// end of while
                
                if(inputStream!=null)
     	    	   inputStream.close();
                
//                fnkPopUp("Falcon MFD" , "Your Connection Data Readed Successfully . . ."  
//            			,"Msg" ,"Info", R.layout.frmpopup);

              //xml okuma kýsmý sonu  

	        } catch (Exception e) {
	        	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
	        }
    	} // fnkReadXML Sonu
    	
    	
    	
    	// convert InputStream to String
    			private static String getStringFromInputStream(InputStream is) {
    		 
    				BufferedReader br = null;
    				StringBuilder sb = new StringBuilder();
    		 
    				String line;
    				try {
    		 
    					br = new BufferedReader(new InputStreamReader(is));
    					while ((line = br.readLine()) != null) {
    						sb.append(line);
    					}
    		 
    				} catch (IOException e) {
    					//e.printStackTrace();
    				} finally {
    					if (br != null) {
    						try {
    							br.close();
    						} catch (IOException e) {
    							//e.printStackTrace();
    						}
    					}
    				}
    		 
    				return sb.toString();
    		 
    			}    // getStringFromInputStream Sonu
    			
    			


    			 public static String getIPAddress(boolean useIPv4) {
    			        try {
    			            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
    			            for (NetworkInterface intf : interfaces) {
    			                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
    			                for (InetAddress addr : addrs) {
    			                    if (!addr.isLoopbackAddress()) {
    			                        String sAddr = addr.getHostAddress().toUpperCase();
    			                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
    			                        if (useIPv4) {
    			                            if (isIPv4) 
    			                                return sAddr;
    			                        } else {
    			                            if (!isIPv4) {
    			                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
    			                                return delim<0 ? sAddr : sAddr.substring(0, delim);
    			                            }
    			                        }
    			                    }
    			                }
    			            }
    			        } catch (Exception ex) { } // for now eat exceptions
    			        return "";
    			    } // getIPAddress Sonu

    			 public String getWifiIpAddr() {
    				   WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
    				   WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    				   int ip = wifiInfo.getIpAddress();

    				   String ipString = String.format(
    				   "%d.%d.%d.%d",
    				   (ip & 0xff),
    				   (ip >> 8 & 0xff),
    				   (ip >> 16 & 0xff),
    				   (ip >> 24 & 0xff));

    				   return ipString;
    				}
    			 

    	
    	
// Fonksiyonlar Sonu
    	
// thread Handlarýn Baþý
    			 
    			 
 private Handler threadHandlerthfnkListenErrorMessage = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
					fnkPopUp("Falcon MFD" ,(String)msg.obj  ,"Error" ,"Info", R.layout.frmpopup);
				} catch (Exception e){
			    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
			    }
		}
	}; //threadHandlerthfnkListenErrorMessage Sonu    			 
    			 
 
	
	private Handler threadHandlerthfnkListenMisSchPort = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
//			try {
//			      
////    		    								String vrGelenMsg;						
////    		    								vrGelenMsg = (String)msg.obj;
//				   
//					//fnkPopUp("Falcon MFD" ,vrGelenMsg  ,"Msg" ,"Info", R.layout.frmpopup);
//				
//				
//				mis_sch.myImgMisSch.setImageBitmap( StringToBitMap((String)msg.obj));
//				
//				//imgMFDSagIc.setImageBitmap(BitmapFactory.decodeStream((InputStream)msg.obj));
//																
//				} catch (Exception e){
//			      //throw new RuntimeException(e);
//			    	
//			    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
//			    }
			
			
			
			try {
			      // DB operation
					
				bmpMisSch = StringToBitMap((String)msg.obj);
				
				if (vrResimIndex==1)
				{
					bmpMisSch1 = bmpMisSch;
				}
				else if (vrResimIndex==2)
					{
					bmpMisSch2 = bmpMisSch;
					}
				else if (vrResimIndex==3)
					{
						bmpMisSch3 = bmpMisSch;
					}
				else if (vrResimIndex==4)
					{
						bmpMisSch4 = bmpMisSch;
					}
				else if (vrResimIndex==5)
					{
						bmpMisSch5 = bmpMisSch;
					}
				
				mis_sch.fnkSetPicture(bmpMisSch);
				
			    } catch (Exception e){
			      //throw new RuntimeException(e);
			    	

					Toast.makeText(getBaseContext(),
			    			"Hata : " + e.toString() ,
							Toast.LENGTH_SHORT).show();
			    	
			    }
			
			
		}
	}; //threadHandlerthfnkListenMisSchPort Sonu		

	
	
 private Handler threadHandlerthfnkListenDEDPort = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			try {
			      
//    		    								String vrGelenMsg;						
//    		    								vrGelenMsg = (String)msg.obj;
				   
					//fnkPopUp("Falcon MFD" ,vrGelenMsg  ,"Msg" ,"Info", R.layout.frmpopup);
				
				
				imgDEDIc.setImageBitmap( StringToBitMap((String)msg.obj));
				
				//imgMFDSagIc.setImageBitmap(BitmapFactory.decodeStream((InputStream)msg.obj));
																
				} catch (Exception e){
			      //throw new RuntimeException(e);
			    	
			    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
			    }
			
		}
	}; //threadHandlerthfnkListenDEDPort Sonu		
	
	 private Handler threadHandlerthfnkListenRWRPort = new Handler() {
			public void handleMessage(android.os.Message msg) {
				
				try {
				      
//    								String vrGelenMsg;						
//    								vrGelenMsg = (String)msg.obj;
					   
						//fnkPopUp("Falcon MFD" ,vrGelenMsg  ,"Msg" ,"Info", R.layout.frmpopup);
					
					
					imgRWRIc.setImageBitmap( StringToBitMap((String)msg.obj));
					
					//imgMFDSagIc.setImageBitmap(BitmapFactory.decodeStream((InputStream)msg.obj));
																	
					} catch (Exception e){
				      //throw new RuntimeException(e);
				    	
				    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
				    }
				
			}
		}; //threadHandlerthfnkListenRWRPort Sonu			 
    			 
	 private Handler threadHandlerthfnkListenMsgPort = new Handler() {
			public void handleMessage(android.os.Message msg) {
				
				try {				     
						
					String vrGelenMsg;
					
					vrGelenMsg = (String)msg.obj;
					
					
					String[] separated = vrGelenMsg.split("@");
					
					
					MessageName vrMsgName = MessageName.valueOf(separated[0]);
					
					switch (vrMsgName) 
					{
							case Data : 
								
																
								FlightDataTypes vrDataType = FlightDataTypes.valueOf(separated[1]);
								String uri;
								int imageResource;
								Drawable res; 
			        		
					        		switch (vrDataType) 
									{
									
							        	case SpeedBreak : 
							        									        					
										        		if (Integer.parseInt(separated[2]) == 0) //Off
															{
										        				uri = "@drawable/ab_off";
															}
										        		else 					//On
															{
										        				uri = "@drawable/ab_on";
															}
										        		
											        		imageResource = getResources().getIdentifier(uri, null, getPackageName());
															res = getResources().getDrawable(imageResource);
											        		imgBtnAB.setImageDrawable(res);
										        		
							        		 		break;
							        	case GearPos : 	//gearPos 0 : Up 1 : Down
										        		
										        		if (Integer.parseInt(separated[2]) == 0) //Off
															{
										        				uri = "@drawable/gear_up";
															}
										        		else 					//On
															{
										        				uri = "@drawable/gear_down";
															}
										        		
										        		imageResource = getResources().getIdentifier(uri, null, getPackageName());
														res = getResources().getDrawable(imageResource);
										        		imgBtnGear.setImageDrawable(res);
							        	
							        		 		break;
							        	case CurrentHeading : 
						        			txtCurrentHeading.setText("Head " + separated[2]);
						        			haritalar.fnkSetHead(separated[2]);
						        			vrCurrentHeading = separated[2]; 
						        			break;
							        	case FlareCount : 	
							        		txtDataFlrCnt.setText(separated[2]);
							        		break;
							        	case ChaffCount : 	
							        		txtDataChftCnt.setText(separated[2]);
							        		break;
							        	case TotalFuel : 	
							        		txtDataTotalFuel.setText(separated[2]);
							        		break;
							        	case FuelFlow : 	
							        		txtDataFuelFlow.setText(separated[2]);
							        		break;
							        	case kias : 	// Ownship Indicated Airspeed (Knots)
						        			txtAirSpead.setText(separated[2] + " Knt");
						        			break;
							        	case aauz : // AAU altimeter indicated altitude (new in BMS4)
						        			txtAltitute.setText(separated[2] + " Ft");							        		
						        			break;
							        	case YCoordinate : // 
							        		haritalar.fnkSetLocationY(separated[2]);							        		
						        			break;
							        	case XCoordinate : // 
							        		haritalar.fnkSetLocationX(separated[2]);							        		
						        			break;
									default:
										break;
				        		 		
									} // End Switch   
				        		 break;
			        	case Msg : 	//Normal Message
			        				//separated[1] - Info
			        				//			   - Error
			        				fnkPopUp("Falcon MFD" ,(String)msg.obj  ,"Msg" ,separated[1], R.layout.frmpopup);
			        				break;
			        	
			           
					} // End Switch   MsgType
					
										
					} catch (Exception e){
				      //throw new RuntimeException(e);				    	
				    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
				    }
				              
				
				
			}
		}; //threadHandlerthfnkListenMsgPort Sonu
    				
    	private Handler threadHandlerthfnkListenLMFDPort = new Handler() {
			public void handleMessage(android.os.Message msg) {
				
				try {
				      
//						String vrGelenMsg;						
//						vrGelenMsg = (String)msg.obj;
					   
						//fnkPopUp("Falcon MFD" ,vrGelenMsg  ,"Msg" ,"Info", R.layout.frmpopup);
					
					
					imgMFDSolIc.setImageBitmap( StringToBitMap((String)msg.obj));
					
					//imgMFDSagIc.setImageBitmap(BitmapFactory.decodeStream((InputStream)msg.obj));
																	
					} catch (Exception e){
				      //throw new RuntimeException(e);
				    	
				    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
				    }
				              
				
				
			}
		}; //threadHandlerthfnkListenLMFDPort Sonu
		
		
		private Handler threadHandlerthfnkListenRMFDPort = new Handler() {
			public void handleMessage(android.os.Message msg) {
				
				try {
				      
//						String vrGelenMsg;						
//						vrGelenMsg = (String)msg.obj;
					   
						//fnkPopUp("Falcon MFD" ,vrGelenMsg  ,"Msg" ,"Info", R.layout.frmpopup);
					
					
					imgMFDSagIc.setImageBitmap( StringToBitMap((String)msg.obj));
					
					//imgMFDSagIc.setImageBitmap(BitmapFactory.decodeStream((InputStream)msg.obj));
																	
					} catch (Exception e){
				      //throw new RuntimeException(e);
				    	
				    	fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
				    }
				              
				
				
			}
		}; //threadHandlerthfnkListenRMFDPort Sonu

		// thread Handlarýn Sonu

		
// thread classlarý Asycronus
		class ThSendKey extends AsyncTask<String, Void, String> {

		    @Override
		    protected String doInBackground(String... params) {
		    	
		    	
		    	String outMsg;
		    	Socket s;
		    	
		    	
				try {
					s = new Socket( prServerIP , Integer.parseInt( prMsgPort));
				
					//BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					//send output msg
					//String outMsg = "TCP connecting to " + TCP_SERVER_PORT + System.getProperty("line.separator");
					
					outMsg = params[0];
					
					out.write(outMsg);			
					out.flush();
					
//					Message msg = threadHandlerthfnkListenMsgPort.obtainMessage();
//   					msg.obj = params[0] + " Sended Successfully";
//   					threadHandlerthfnkListenMsgPort.sendMessage(msg);
					
					s.close();
					}  
					catch (Exception e)
						{
					    	//fnkPopUp("Falcon MFD" , e.getMessage()  ,"Msg" ,"Error", R.layout.frmpopup);
							Message msgx = threadHandlerthfnkListenMsgPort.obtainMessage();
	       					msgx.obj = e.getMessage();
	       					threadHandlerthfnkListenMsgPort.sendMessage(msgx);
					    }

		          return "Executed";
		    }      

		    @Override
		    protected void onPostExecute(String result) {
		          
		          //txt.setText(""); // txt.setText(result);
		          //might want to change "executed" for the returned string passed into onPostExecute() but that is upto you
		    }

		    @Override
		    protected void onPreExecute() {
		    }

		    @Override
		    protected void onProgressUpdate(Void... values) {
		    }
		}   
		
// thread classlarý Asycronus Sonu		
		
//ViewFlipper ler Detektör Detaylar  
		
		

class Swipeview_flipperICPSAGJS extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
							if (vrICPSAGJS < 1 )
								{
									mview_flipperICPSAGJS.showNext();
									vrICPSAGJS = vrICPSAGJS + 1;
									
									if 		(vrICPSAGJS == 1 ) //ICPSAGJS DRÝFT CO
										{
											vrHangiTus = "SimDriftCO";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrICPSAGJS == 0 )	 //NORM
										{
										//fnkPopUp("Falcon MFD" ,String.valueOf(vrICPSAGJS),"Msg" ,"Info", R.layout.frmpopup);
//											vrHangiTus = "SimDriftCO";
//											fnkTusGonder(vrHangiTus );
										}
									else if (vrICPSAGJS == -1 ) //WARN RESET
										{
											vrHangiTus = "SimWarnReset";
											fnkTusGonder(vrHangiTus );
											
											//mview_flipperICPSAGJS.showPrevious();
										}
									
								}
							return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
			            	if (vrICPSAGJS > -1 )
								{
				            		mview_flipperICPSAGJS.showPrevious();
				            		vrICPSAGJS = vrICPSAGJS - 1;
				            		
				            		if 		(vrICPSAGJS == 1 ) //ICPSAGJS DRIFT CO
									{
				            			vrHangiTus = "SimDriftCO";
										fnkTusGonder(vrHangiTus );
									}
									else if (vrICPSAGJS == 0 )	 //AP Off
										{
										vrHangiTus = "SimDriftCO";
										fnkTusGonder(vrHangiTus );										}
									else if (vrICPSAGJS == -1 ) //WARN RESET
										{
											vrHangiTus = "SimWarnReset";
											fnkTusGonder(vrHangiTus );
											
											//mview_flipperICPSAGJS.showNext();
										}
								}
								    return true;
		            }
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperICPSAGJS Son
		
		class Swipeview_flipperIcpSolUst extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
						
						vrHangiTus = "SimSymWheelUp";
						fnkTusGonder(vrHangiTus );
						
						mview_flipperIcpSolUst.showPrevious();					
						return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
		            	vrHangiTus = "SimSymWheelDn";
						fnkTusGonder(vrHangiTus );
						
		            		mview_flipperIcpSolUst.showNext();
						    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperIcpSolUst Son
		
		
		class Swipeview_flipperIcpSagUst extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
						
						vrHangiTus = "SimRetUp";
						fnkTusGonder(vrHangiTus );
						
						mview_flipperIcpSagUst.showPrevious();					
						return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
		            	vrHangiTus = "SimRetDn";
						fnkTusGonder(vrHangiTus );
		            	
	            		mview_flipperIcpSagUst.showNext();
					    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperIcpSagUst Son
		
		class Swipeview_flipperIcpSolAlt extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
						mview_flipperIcpSolAlt.showPrevious();					
						return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
		            		mview_flipperIcpSolAlt.showNext();
						    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperIcpSolAlt Son
		
		class Swipeview_flipperIcpSagAlt extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
						mview_flipperIcpSagAlt.showPrevious();					
						return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
		            		mview_flipperIcpSagAlt.showNext();
						    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperIcpSagAlt Son
		
		class Swipeview_flipperPnlYardimci extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
						mview_flipperPnlYardimci.showPrevious();						
						vrPnlYardimciTop = vrPnlYardimciTop - 5;
						pnlYardimci.setY(vrPnlYardimciTop);
						
//						RelativeLayout.LayoutParams vrLp = new RelativeLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
//						pnlYardimci.setLayoutParams(vrLp);
						
						return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
		            		mview_flipperPnlYardimci.showNext();
		            		vrPnlYardimciTop = vrPnlYardimciTop + 5;
		            		
		            		pnlYardimci.setY(vrPnlYardimciTop);
						    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperPnlYardimci Son
		
		
		class Swipeview_flipperECM extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
						if (vrECM == 0)
							{
								vrECM = 1;
								mview_flipperECM.showPrevious();	
								
								vrHangiTus = "SimECMOn";					 
								fnkTusGonder(vrHangiTus);
							}										
							return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
		            	if (vrECM == 1) 
	            			{
			            		vrECM = 0;	
				            	mview_flipperECM.showNext();
				            	
				            	vrHangiTus = "SimECMOn";					 
								fnkTusGonder(vrHangiTus);
	            			}
						    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperECM Son
		
		class Swipeview_flipperROLL extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
							if (vrROLL < 1 )
								{
									mview_flipperROLL.showNext();
									vrROLL = vrROLL + 1;
									
									if 		(vrROLL == 1 ) //ROLL HOLD
										{
											vrHangiTus = "SimLeftAPUp";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrROLL == 0 )	 //AP Off
										{
											vrHangiTus = "SimLeftAPMid";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrROLL == -1 ) //ALT HOLT
										{
											vrHangiTus = "SimLeftAPDown";
											fnkTusGonder(vrHangiTus );
										}
									
								}
							return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
			            	if (vrROLL > -1 )
								{
				            		mview_flipperROLL.showPrevious();
				            		vrROLL = vrROLL - 1;
				            		
				            		if 		(vrROLL == 1 ) //ROLL HOLD
									{
										vrHangiTus = "SimLeftAPUp";
										fnkTusGonder(vrHangiTus );
									}
									else if (vrROLL == 0 )	 //AP Off
										{
											vrHangiTus = "SimLeftAPMid";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrROLL == -1 ) //ALT HOLT
										{
											vrHangiTus = "SimLeftAPDown";
											fnkTusGonder(vrHangiTus );
										}
								}
								    return true;
		            }
		            
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperROLL Son
		
		
		class Swipeview_flipperPITCH extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && 
	                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	                //From Bottom to Top
							if (vrPITCH < 1 )
								{
									mview_flipperPITCH.showNext();
									vrPITCH = vrPITCH + 1;
									
									if 		(vrPITCH == 1 ) //PITCH HOLD
										{
											vrHangiTus = "SimRightAPUp";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrPITCH == 0 )	 //AP Off
										{
											vrHangiTus = "SimRightAPMid";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrPITCH == -1 ) //ALT HOLT
										{
											vrHangiTus = "SimRightAPDown";
											fnkTusGonder(vrHangiTus );
										}
									
								}
							return true;
		            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && 
		                        Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                //From Top to Bottom
			            	if (vrPITCH > -1 )
								{
				            		mview_flipperPITCH.showPrevious();
				            		vrPITCH = vrPITCH - 1;
				            		
				            		if 		(vrPITCH == 1 ) //PITCH HOLD
									{
										vrHangiTus = "SimRightAPUp";
										fnkTusGonder(vrHangiTus );
									}
									else if (vrPITCH == 0 )	 //AP Off
										{
											vrHangiTus = "SimRightAPMid";
											fnkTusGonder(vrHangiTus );
										}
									else if (vrPITCH == -1 ) //ALT HOLT
										{
											vrHangiTus = "SimRightAPDown";
											fnkTusGonder(vrHangiTus );
										}
								}
								    return true;
		            }
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperPITCH Son
		
		
		class Swipeview_flipperPRGM extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					
					
					//fnkPopUp("Falcon MFD" , "Have a Falcon Day"  ,"Msg" ,"Info", R.layout.frmpopup);
					
					if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						
						if (vrPRGM > 0)
						{
							mview_flipperPRGM.showPrevious();	
							vrPRGM = vrPRGM - 1;
							fnkPRGM(vrPRGM);
						}
						
						return true;						
					} 
					// left to right swipe
					else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						
						if (vrPRGM < 4)
						{
							mview_flipperPRGM.showNext();
							vrPRGM = vrPRGM + 1;
							fnkPRGM(vrPRGM);
						}
						
					    return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperPRGM Son
		
		class Swipeview_flipperMODE extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					
					
					
					
					if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {						
						
						if (vrMODE > 0)
						{
							mview_flipperMODE.showPrevious();	
							vrMODE = vrMODE - 1;
							fnkMODE(vrMODE);
						}
						
											
						return true;						
					} 
					// left to right swipe
					else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						
						if (vrMODE < 5)
						{
							mview_flipperMODE.showNext();
							vrMODE = vrMODE + 1;
							fnkMODE(vrMODE);
						}
					    return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperMODE Son
		
		class Swipeview_flipperHMCS extends SimpleOnGestureListener {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					
					
					//fnkPopUp("Falcon MFD" , "Have a Falcon Day"  ,"Msg" ,"Info", R.layout.frmpopup);
					
					if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						
						if (vrHMCS >  0 )
						{
							mview_flipperHMCS.showPrevious();	
							vrHMCS = vrHMCS -1 ;
							
							vrHangiTus = "SimHmsSymWheelDn";
							fnkTusGonder(vrHangiTus);
							//yazi.setText("OSB Nu.: " + "\n" + vrHangiTus);
						}
						return true;						
					} 
					// left to right swipe
					else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						
						if (vrHMCS <  4 )
						{
							mview_flipperHMCS.showNext();
							vrHMCS = vrHMCS + 1 ;
							
							vrHangiTus = "SimHmsSymWheelUp";
							fnkTusGonder(vrHangiTus);
						}
					    return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		} // Swipeview_flipperHMCS Son
		
		
		
//ViewFlipper ler Detektör Detaylar Sonu
		
		
		
//Class lar		
		public enum MessageName {

		    Data,
		    Msg
		  }	
		
//		public enum MessageTypes {
//
//		    Info,
//		    Error
//		  }	
		
		public enum FlightDataTypes {		    
		    SpeedBreak,
		    GearPos,
		    FlareCount,
		    FlareLow,
		    ChaffCount,
		    ChaffLow,
		    YCoordinate,
		    XCoordinate,
		    CurrentHeading,
		    RPM,
		    TotalFuel,
		    FuelFlow,
		    kias , // Ownship Indicated Airspeed (Knots) 
		    aauz //Altimater
		    
		  }	
	
//Class lar Sonu
		
		
		public void fnkGlobalData()
		 {
			//xml için
		        
		        InputStream inputStream;
		        
		       
		       try
		               {
		                    
		    	   
		    	   AssetManager assetManager = getAssets();
		    	   inputStream = assetManager.open("GlobalData.xml");
		    	   
		    	  		    	   			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		                    factory.setNamespaceAware(true);
		                    XmlPullParser xpp = factory.newPullParser();
		                    xpp.setInput(inputStream,null);
		                    int eventType = xpp.getEventType();
		                    
		                    while (eventType != XmlPullParser.END_DOCUMENT) 
		                    {
		                        if(eventType == XmlPullParser.START_DOCUMENT) 
		                         { 
		                         }                 
		                        else if(eventType == XmlPullParser.START_TAG)
		                        {
		                            
		                        	try 
		                            {                      
		                                    if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("vrPnlYardimciTop"))
		                                     {
		                                          eventType = xpp.next();
		                                          vrPnlYardimciTop = Integer.parseInt(xpp.getText().toString());
		                                     }
//		                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("Name"))
//		                                     {
//		                                          eventType = xpp.next();
//		                                          item.vrName=xpp.getText().toString();	                                          
//		                                     } 
		                                    
		                               } 
		                            catch (Exception e) 
		                               {
		                                   //e.printStackTrace();
		                               }
		                        }
		                        else if(eventType == XmlPullParser.END_TAG) 
		                        {
		                        }
		                        else if(eventType == XmlPullParser.TEXT) 
		                        {
		                        }
		                        eventType = xpp.next();       
		                }// end of while
		                    
		                    if(inputStream!=null)
		         	    	   inputStream.close();

		                    
		                    
		               }
		               catch (XmlPullParserException e) 
		               {
		                   //e.printStackTrace();
		               } 
		               catch (IOException e) 
		               {
		                 //e.printStackTrace();
		               }
		               
		        //Xml için
			 
			 
		 } //fnkGlobalData Sonu		
		
		
		public void fnkGlobalDataWrite()
		{
			try {
				
			
					File newxmlfile = new File(new File(getFilesDir().getParentFile(), "assets"), "GlobalData.xml");
					newxmlfile.createNewFile();
		            
		            FileOutputStream fileos = null;
		            fileos = new FileOutputStream(newxmlfile);
		            
		            XmlSerializer serializer = Xml.newSerializer();
		            
		            serializer.setOutput(fileos, "UTF-8");
		            //Write <?xml declaration with encoding (if encoding not null) and standalone flag (if standalone not null)
		            serializer.startDocument(null, Boolean.valueOf(true));
		            //set indentation option
		            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
		            //start a tag called "root"
		            
		            
				}
			
			catch (IOException e) 
            {
              //e.printStackTrace();
            }
		}
		
		
} // Activate Sonu
