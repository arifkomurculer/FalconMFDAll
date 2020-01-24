package com.arifkomurculer.falconmfdmultitablet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class haritalar extends Activity implements OnTouchListener {
   
	
	
	static	ArrayList<Item>vrAirBases=new ArrayList<Item>();
	static String vrHangiHarita;
	static float vrX =0 ;
	static float vrY =0;
	static float vrHead = 0;
	
	static boolean vrXortala;
	static boolean vrYortala;
	
	static TextView txtX ;
	static TextView txtY ;
    static TextView txtHead ;
    
    static TextView lblTwr;
	TextView lblTcnCh;
	TextView lblTcnRng;
	TextView lblILS;
	TextView lblRWY;
	TextView lblElev;
	TextView lblCoor;
	TextView lblAirBase;
	
	static ImageView imgF16;
	
	
	
    AbsoluteLayout pnlHaritalar;
    static AbsoluteLayout pnlDis;
    
    static float vrXloc; //Haritanýn X deðeri
    float vrXLocFark; //Týklanan Noktadan Uzaklýðý X
    static float vrYloc; //Haritanýn Y deðeri
    float vrYLocFark; //Týklanan Noktadan Uzaklýðý Y
	int vrHeight ;
	int vrWidth ;
	
	Button btnAirBaseClose;
	AbsoluteLayout pnlAirBaseInfo;
	int vrXAirBaseCoord;
	int vrYAirBaseCoord;
	
	//ImageView imgHaritaKapat;
	
	
	
    
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.haritalar);
        
        
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
        
        vrXortala =false;
        vrYortala =false;
        
        vrAirBases.clear();
        
        pnlHaritalar = (AbsoluteLayout)findViewById(R.id.pnlHaritalar);
           
        
        btnAirBaseClose = (Button)findViewById(R.id.btnAirBaseClose);
        
        pnlAirBaseInfo 	= (AbsoluteLayout)findViewById(R.id.pnlAirBaseInfo);
        
		        btnAirBaseClose.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						pnlAirBaseInfo.setVisibility(View.INVISIBLE);
					}
				});
		
		      //Korea AirBases
		        if (vrHangiHarita == "Korea")
		        {
		        	imgF16 = (ImageView)findViewById(R.id.imgF16Korea);
		        	 	pnlDis = (AbsoluteLayout)findViewById(R.id.pnlKoreaDis);
		        	 	pnlDis.setVisibility(View.VISIBLE);
		        	lblTwr =(TextView)findViewById(R.id.lblTwr);
		    		lblTcnCh =(TextView)findViewById(R.id.lblTcnCh);
		    		lblTcnRng =(TextView)findViewById(R.id.lblTcnRng);
		    		lblILS =(TextView)findViewById(R.id.lblILS);
		    		lblRWY =(TextView)findViewById(R.id.lblRWY);
		    		lblElev =(TextView)findViewById(R.id.lblElev);
		    		lblCoor =(TextView)findViewById(R.id.lblCoor);
		        	
		    		lblAirBase =(TextView)findViewById(R.id.lblAirBase);
		    		
		    		
		    		ImageView imgHarita1;
		    		imgHarita1 = (ImageView)findViewById(R.id.imgKorea1);		    		
		    		imgHarita1.setBackgroundResource(R.drawable.map_korea1);
		    		
		    		ImageView imgHarita2;
		    		imgHarita2 = (ImageView)findViewById(R.id.imgKorea2);		    		
		    		imgHarita2.setBackgroundResource(R.drawable.map_korea2);
		    		
		    		ImageView imgHarita3;
		    		imgHarita3 = (ImageView)findViewById(R.id.imgKorea3);		    		
		    		imgHarita3.setBackgroundResource(R.drawable.map_korea3);
		    		
		    		ImageView imgHarita4;
		    		imgHarita4 = (ImageView)findViewById(R.id.imgKorea4);		    		
		    		imgHarita4.setBackgroundResource(R.drawable.map_korea4);
		    		
		    		ImageView imgHarita5;
		    		imgHarita5 = (ImageView)findViewById(R.id.imgKorea5);		    		
		    		imgHarita5.setBackgroundResource(R.drawable.map_korea5);
		    		
		    		ImageView imgHarita6;
		    		imgHarita6 = (ImageView)findViewById(R.id.imgKorea6);		    		
		    		imgHarita6.setBackgroundResource(R.drawable.map_korea6);
		    		
		    		ImageView imgHarita7;
		    		imgHarita7 = (ImageView)findViewById(R.id.imgKorea7);		    		
		    		imgHarita7.setBackgroundResource(R.drawable.map_korea7);
		    		
		    		ImageView imgHarita8;
		    		imgHarita8 = (ImageView)findViewById(R.id.imgKorea8);		    		
		    		imgHarita8.setBackgroundResource(R.drawable.map_korea8);
		    		
		    		ImageView imgHarita9;
		    		imgHarita9 = (ImageView)findViewById(R.id.imgKorea9);		    		
		    		imgHarita9.setBackgroundResource(R.drawable.map_korea9);
		    		
		    		
		        	
		        	
		        	fnkAirBases("korea.xml");
		        	String vrId;
		        	
		        	AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(30,30,1 * 2 ,1 * 2);
		        	
		        	
		        	ImageButton btnKorea_1 =(ImageButton)findViewById(R.id.btnKorea_1);		        	        			        	
		        	fnkSetAirBaseCoor(1);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_1.setLayoutParams(vrLp);
					btnKorea_1.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(1);
						}
					}); 
					
					ImageButton btnKorea_2 =(ImageButton)findViewById(R.id.btnKorea_2);		        	        			        	
		        	fnkSetAirBaseCoor(2);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_2.setLayoutParams(vrLp);
					btnKorea_2.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(2);
						}
					}); 
					
					ImageButton btnKorea_3 =(ImageButton)findViewById(R.id.btnKorea_3);		        	        			        	
		        	fnkSetAirBaseCoor(3);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_3.setLayoutParams(vrLp);
					btnKorea_3.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(3);
						}
					}); 
					
					ImageButton btnKorea_4 =(ImageButton)findViewById(R.id.btnKorea_4);		        	        			        	
		        	fnkSetAirBaseCoor(4);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_4.setLayoutParams(vrLp);
					btnKorea_4.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(4);
						}
					}); 
					
					ImageButton btnKorea_5 =(ImageButton)findViewById(R.id.btnKorea_5);		        	        			        	
		        	fnkSetAirBaseCoor(5);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_5.setLayoutParams(vrLp);
					btnKorea_5.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(5);
						}
					}); 
					
					ImageButton btnKorea_6 =(ImageButton)findViewById(R.id.btnKorea_6);		        	        			        	
		        	fnkSetAirBaseCoor(6);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_6.setLayoutParams(vrLp);
					btnKorea_6.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(6);
						}
					}); 
					
					ImageButton btnKorea_7 =(ImageButton)findViewById(R.id.btnKorea_7);		        	        			        	
		        	fnkSetAirBaseCoor(7);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_7.setLayoutParams(vrLp);
					btnKorea_7.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(7);
						}
					}); 
					
					ImageButton btnKorea_8 =(ImageButton)findViewById(R.id.btnKorea_8);		        	        			        	
		        	fnkSetAirBaseCoor(8);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_8.setLayoutParams(vrLp);
					btnKorea_8.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(8);
						}
					}); 
					
					ImageButton btnKorea_9 =(ImageButton)findViewById(R.id.btnKorea_9);		        	        			        	
		        	fnkSetAirBaseCoor(9);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_9.setLayoutParams(vrLp);
					btnKorea_9.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(9);
						}
					}); 
					
					ImageButton btnKorea_10 =(ImageButton)findViewById(R.id.btnKorea_10);		        	        			        	
		        	fnkSetAirBaseCoor(10);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_10.setLayoutParams(vrLp);
					btnKorea_10.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(10);
						}
					}); 
					
					ImageButton btnKorea_11 =(ImageButton)findViewById(R.id.btnKorea_11);		        	        			        	
		        	fnkSetAirBaseCoor(11);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_11.setLayoutParams(vrLp);
					btnKorea_11.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(11);
						}
					}); 
					
					ImageButton btnKorea_12 =(ImageButton)findViewById(R.id.btnKorea_12);		        	        			        	
		        	fnkSetAirBaseCoor(12);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_12.setLayoutParams(vrLp);
					btnKorea_12.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(12);
						}
					}); 
					
					ImageButton btnKorea_13 =(ImageButton)findViewById(R.id.btnKorea_13);		        	        			        	
		        	fnkSetAirBaseCoor(13);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_13.setLayoutParams(vrLp);
					btnKorea_13.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(13);
						}
					}); 
					
					ImageButton btnKorea_14 =(ImageButton)findViewById(R.id.btnKorea_14);		        	        			        	
		        	fnkSetAirBaseCoor(14);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_14.setLayoutParams(vrLp);
					btnKorea_14.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(14);
						}
					}); 
					
					ImageButton btnKorea_15 =(ImageButton)findViewById(R.id.btnKorea_15);		        	        			        	
		        	fnkSetAirBaseCoor(15);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_15.setLayoutParams(vrLp);
					btnKorea_15.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(15);
						}
					}); 

					ImageButton btnKorea_16 =(ImageButton)findViewById(R.id.btnKorea_16);		        	        			        	
		        	fnkSetAirBaseCoor(16);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_16.setLayoutParams(vrLp);
					btnKorea_16.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(16);
						}
					}); 
					
					ImageButton btnKorea_17 =(ImageButton)findViewById(R.id.btnKorea_17);		        	        			        	
		        	fnkSetAirBaseCoor(17);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_17.setLayoutParams(vrLp);
					btnKorea_17.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(17);
						}
					}); 
					
					ImageButton btnKorea_18 =(ImageButton)findViewById(R.id.btnKorea_18);		        	        			        	
		        	fnkSetAirBaseCoor(18);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_18.setLayoutParams(vrLp);
					btnKorea_18.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(18);
						}
					}); 
					
					ImageButton btnKorea_19 =(ImageButton)findViewById(R.id.btnKorea_19);		        	        			        	
		        	fnkSetAirBaseCoor(19);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_19.setLayoutParams(vrLp);
					btnKorea_19.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(19);
						}
					}); 
					
					
					
					ImageButton btnKorea_20 =(ImageButton)findViewById(R.id.btnKorea_20);		        	        			        	
		        	fnkSetAirBaseCoor(20);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_20.setLayoutParams(vrLp);
					btnKorea_20.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(20);
						}
					}); 
					
					ImageButton btnKorea_21 =(ImageButton)findViewById(R.id.btnKorea_21);		        	        			        	
		        	fnkSetAirBaseCoor(21);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_21.setLayoutParams(vrLp);
					btnKorea_21.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(21);
						}
					}); 
					
					ImageButton btnKorea_22 =(ImageButton)findViewById(R.id.btnKorea_22);		        	        			        	
		        	fnkSetAirBaseCoor(22);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_22.setLayoutParams(vrLp);
					btnKorea_22.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(22);
						}
					}); 
					
					ImageButton btnKorea_23 =(ImageButton)findViewById(R.id.btnKorea_23);		        	        			        	
		        	fnkSetAirBaseCoor(23);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_23.setLayoutParams(vrLp);
					btnKorea_23.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(23);
						}
					}); 
					
					ImageButton btnKorea_24 =(ImageButton)findViewById(R.id.btnKorea_24);		        	        			        	
		        	fnkSetAirBaseCoor(24);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_24.setLayoutParams(vrLp);
					btnKorea_24.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(24);
						}
					}); 
					
					ImageButton btnKorea_25 =(ImageButton)findViewById(R.id.btnKorea_25);		        	        			        	
		        	fnkSetAirBaseCoor(25);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_25.setLayoutParams(vrLp);
					btnKorea_25.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(25);
						}
					}); 

					ImageButton btnKorea_26 =(ImageButton)findViewById(R.id.btnKorea_26);		        	        			        	
		        	fnkSetAirBaseCoor(26);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_26.setLayoutParams(vrLp);
					btnKorea_26.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(26);
						}
					}); 
					
					ImageButton btnKorea_27 =(ImageButton)findViewById(R.id.btnKorea_27);		        	        			        	
		        	fnkSetAirBaseCoor(27);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_27.setLayoutParams(vrLp);
					btnKorea_27.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(27);
						}
					}); 
					
					ImageButton btnKorea_28 =(ImageButton)findViewById(R.id.btnKorea_28);		        	        			        	
		        	fnkSetAirBaseCoor(28);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_28.setLayoutParams(vrLp);
					btnKorea_28.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(28);
						}
					}); 
					
					ImageButton btnKorea_29 =(ImageButton)findViewById(R.id.btnKorea_29);		        	        			        	
		        	fnkSetAirBaseCoor(29);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_29.setLayoutParams(vrLp);
					btnKorea_29.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(29);
						}
					}); 
					
					
					ImageButton btnKorea_30 =(ImageButton)findViewById(R.id.btnKorea_30);		        	        			        	
		        	fnkSetAirBaseCoor(30);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_30.setLayoutParams(vrLp);
					btnKorea_30.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(30);
						}
					}); 
					
					ImageButton btnKorea_31 =(ImageButton)findViewById(R.id.btnKorea_31);		        	        			        	
		        	fnkSetAirBaseCoor(31);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_31.setLayoutParams(vrLp);
					btnKorea_31.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(31);
						}
					}); 
					
					ImageButton btnKorea_32 =(ImageButton)findViewById(R.id.btnKorea_32);		        	        			        	
		        	fnkSetAirBaseCoor(32);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_32.setLayoutParams(vrLp);
					btnKorea_32.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(32);
						}
					}); 
					
					ImageButton btnKorea_33 =(ImageButton)findViewById(R.id.btnKorea_33);		        	        			        	
		        	fnkSetAirBaseCoor(33);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_33.setLayoutParams(vrLp);
					btnKorea_33.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(33);
						}
					}); 
					
					ImageButton btnKorea_34 =(ImageButton)findViewById(R.id.btnKorea_34);		        	        			        	
		        	fnkSetAirBaseCoor(34);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_34.setLayoutParams(vrLp);
					btnKorea_34.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(34);
						}
					}); 
					
					ImageButton btnKorea_35 =(ImageButton)findViewById(R.id.btnKorea_35);		        	        			        	
		        	fnkSetAirBaseCoor(35);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_35.setLayoutParams(vrLp);
					btnKorea_35.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(35);
						}
					}); 

					ImageButton btnKorea_36 =(ImageButton)findViewById(R.id.btnKorea_36);		        	        			        	
		        	fnkSetAirBaseCoor(36);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_36.setLayoutParams(vrLp);
					btnKorea_36.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(36);
						}
					}); 
					
					ImageButton btnKorea_37 =(ImageButton)findViewById(R.id.btnKorea_37);		        	        			        	
		        	fnkSetAirBaseCoor(37);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_37.setLayoutParams(vrLp);
					btnKorea_37.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(37);
						}
					}); 
					
					ImageButton btnKorea_38 =(ImageButton)findViewById(R.id.btnKorea_38);		        	        			        	
		        	fnkSetAirBaseCoor(38);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_38.setLayoutParams(vrLp);
					btnKorea_38.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(38);
						}
					}); 
					
					ImageButton btnKorea_39 =(ImageButton)findViewById(R.id.btnKorea_39);		        	        			        	
		        	fnkSetAirBaseCoor(39);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_39.setLayoutParams(vrLp);
					btnKorea_39.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(39);
						}
					}); 
					
					
					ImageButton btnKorea_40 =(ImageButton)findViewById(R.id.btnKorea_40);		        	        			        	
		        	fnkSetAirBaseCoor(40);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_40.setLayoutParams(vrLp);
					btnKorea_40.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(40);
						}
					}); 
					
					ImageButton btnKorea_41 =(ImageButton)findViewById(R.id.btnKorea_41);		        	        			        	
		        	fnkSetAirBaseCoor(41);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_41.setLayoutParams(vrLp);
					btnKorea_41.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(41);
						}
					}); 
					
					ImageButton btnKorea_42 =(ImageButton)findViewById(R.id.btnKorea_42);		        	        			        	
		        	fnkSetAirBaseCoor(42);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_42.setLayoutParams(vrLp);
					btnKorea_42.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(42);
						}
					}); 
					
					ImageButton btnKorea_43 =(ImageButton)findViewById(R.id.btnKorea_43);		        	        			        	
		        	fnkSetAirBaseCoor(43);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_43.setLayoutParams(vrLp);
					btnKorea_43.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(43);
						}
					}); 
					
					ImageButton btnKorea_44 =(ImageButton)findViewById(R.id.btnKorea_44);		        	        			        	
		        	fnkSetAirBaseCoor(44);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_44.setLayoutParams(vrLp);
					btnKorea_44.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(44);
						}
					}); 
					
					ImageButton btnKorea_45 =(ImageButton)findViewById(R.id.btnKorea_45);		        	        			        	
		        	fnkSetAirBaseCoor(45);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_45.setLayoutParams(vrLp);
					btnKorea_45.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(45);
						}
					}); 

					ImageButton btnKorea_46 =(ImageButton)findViewById(R.id.btnKorea_46);		        	        			        	
		        	fnkSetAirBaseCoor(46);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_46.setLayoutParams(vrLp);
					btnKorea_46.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(46);
						}
					}); 
					
					ImageButton btnKorea_47 =(ImageButton)findViewById(R.id.btnKorea_47);		        	        			        	
		        	fnkSetAirBaseCoor(47);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_47.setLayoutParams(vrLp);
					btnKorea_47.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(47);
						}
					}); 
					
					ImageButton btnKorea_48 =(ImageButton)findViewById(R.id.btnKorea_48);		        	        			        	
		        	fnkSetAirBaseCoor(48);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_48.setLayoutParams(vrLp);
					btnKorea_48.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(48);
						}
					}); 
					
					ImageButton btnKorea_49 =(ImageButton)findViewById(R.id.btnKorea_49);		        	        			        	
		        	fnkSetAirBaseCoor(49);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_49.setLayoutParams(vrLp);
					btnKorea_49.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(49);
						}
					}); 
					
					
					ImageButton btnKorea_50 =(ImageButton)findViewById(R.id.btnKorea_50);		        	        			        	
		        	fnkSetAirBaseCoor(50);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_50.setLayoutParams(vrLp);
					btnKorea_50.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(50);
						}
					}); 
					
					ImageButton btnKorea_51 =(ImageButton)findViewById(R.id.btnKorea_51);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_51.setLayoutParams(vrLp);
					btnKorea_51.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(51);
						}
					}); 
					
					ImageButton btnKorea_52 =(ImageButton)findViewById(R.id.btnKorea_52);		        	        			        	
		        	fnkSetAirBaseCoor(52);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_52.setLayoutParams(vrLp);
					btnKorea_52.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(52);
						}
					}); 
					
					ImageButton btnKorea_53 =(ImageButton)findViewById(R.id.btnKorea_53);		        	        			        	
		        	fnkSetAirBaseCoor(53);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_53.setLayoutParams(vrLp);
					btnKorea_53.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(53);
						}
					}); 
					
					ImageButton btnKorea_54 =(ImageButton)findViewById(R.id.btnKorea_54);		        	        			        	
		        	fnkSetAirBaseCoor(54);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_54.setLayoutParams(vrLp);
					btnKorea_54.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(54);
						}
					}); 
					
					ImageButton btnKorea_55 =(ImageButton)findViewById(R.id.btnKorea_55);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_55.setLayoutParams(vrLp);
					btnKorea_55.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(55);
						}
					}); 

					ImageButton btnKorea_56 =(ImageButton)findViewById(R.id.btnKorea_56);		        	        			        	
		        	fnkSetAirBaseCoor(56);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_56.setLayoutParams(vrLp);
					btnKorea_56.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(56);
						}
					}); 
					
					ImageButton btnKorea_57 =(ImageButton)findViewById(R.id.btnKorea_57);		        	        			        	
		        	fnkSetAirBaseCoor(57);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_57.setLayoutParams(vrLp);
					btnKorea_57.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(57);
						}
					}); 
					
					ImageButton btnKorea_58 =(ImageButton)findViewById(R.id.btnKorea_58);		        	        			        	
		        	fnkSetAirBaseCoor(58);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_58.setLayoutParams(vrLp);
					btnKorea_58.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(58);
						}
					}); 
					
					ImageButton btnKorea_59 =(ImageButton)findViewById(R.id.btnKorea_59);		        	        			        	
		        	fnkSetAirBaseCoor(59);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_59.setLayoutParams(vrLp);
					btnKorea_59.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(59);
						}
					}); 
					
					
					ImageButton btnKorea_60 =(ImageButton)findViewById(R.id.btnKorea_60);		        	        			        	
		        	fnkSetAirBaseCoor(60);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_60.setLayoutParams(vrLp);
					btnKorea_60.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(60);
						}
					}); 
					
					ImageButton btnKorea_61 =(ImageButton)findViewById(R.id.btnKorea_61);		        	        			        	
		        	fnkSetAirBaseCoor(61);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_61.setLayoutParams(vrLp);
					btnKorea_61.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(61);
						}
					}); 
					
					ImageButton btnKorea_62 =(ImageButton)findViewById(R.id.btnKorea_62);		        	        			        	
		        	fnkSetAirBaseCoor(62);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_62.setLayoutParams(vrLp);
					btnKorea_62.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(62);
						}
					}); 
					
					ImageButton btnKorea_63 =(ImageButton)findViewById(R.id.btnKorea_63);		        	        			        	
		        	fnkSetAirBaseCoor(63);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_63.setLayoutParams(vrLp);
					btnKorea_63.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(63);
						}
					}); 
					
					ImageButton btnKorea_64 =(ImageButton)findViewById(R.id.btnKorea_64);		        	        			        	
		        	fnkSetAirBaseCoor(64);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_64.setLayoutParams(vrLp);
					btnKorea_64.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(64);
						}
					}); 
					
					ImageButton btnKorea_65 =(ImageButton)findViewById(R.id.btnKorea_65);		        	        			        	
		        	fnkSetAirBaseCoor(65);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_65.setLayoutParams(vrLp);
					btnKorea_65.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(65);
						}
					}); 

					ImageButton btnKorea_66 =(ImageButton)findViewById(R.id.btnKorea_66);		        	        			        	
		        	fnkSetAirBaseCoor(66);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_66.setLayoutParams(vrLp);
					btnKorea_66.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(66);
						}
					}); 
					
					ImageButton btnKorea_67 =(ImageButton)findViewById(R.id.btnKorea_67);		        	        			        	
		        	fnkSetAirBaseCoor(67);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_67.setLayoutParams(vrLp);
					btnKorea_67.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(67);
						}
					}); 
					
					ImageButton btnKorea_68 =(ImageButton)findViewById(R.id.btnKorea_68);		        	        			        	
		        	fnkSetAirBaseCoor(68);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_68.setLayoutParams(vrLp);
					btnKorea_68.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(68);
						}
					}); 
					
					ImageButton btnKorea_69 =(ImageButton)findViewById(R.id.btnKorea_69);		        	        			        	
		        	fnkSetAirBaseCoor(69);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_69.setLayoutParams(vrLp);
					btnKorea_69.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(69);
						}
					}); 
					
					ImageButton btnKorea_70 =(ImageButton)findViewById(R.id.btnKorea_70);		        	        			        	
		        	fnkSetAirBaseCoor(70);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_70.setLayoutParams(vrLp);
					btnKorea_70.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(70);
						}
					}); 
					
					ImageButton btnKorea_71 =(ImageButton)findViewById(R.id.btnKorea_71);		        	        			        	
		        	fnkSetAirBaseCoor(71);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_71.setLayoutParams(vrLp);
					btnKorea_71.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(71);
						}
					}); 
					
					ImageButton btnKorea_72 =(ImageButton)findViewById(R.id.btnKorea_72);		        	        			        	
		        	fnkSetAirBaseCoor(72);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_72.setLayoutParams(vrLp);
					btnKorea_72.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(72);
						}
					}); 
					
					ImageButton btnKorea_73 =(ImageButton)findViewById(R.id.btnKorea_73);		        	        			        	
		        	fnkSetAirBaseCoor(73);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_73.setLayoutParams(vrLp);
					btnKorea_73.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(73);
						}
					}); 
					
					ImageButton btnKorea_74 =(ImageButton)findViewById(R.id.btnKorea_74);		        	        			        	
		        	fnkSetAirBaseCoor(74);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_74.setLayoutParams(vrLp);
					btnKorea_74.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(74);
						}
					}); 
					
					ImageButton btnKorea_75 =(ImageButton)findViewById(R.id.btnKorea_75);		        	        			        	
		        	fnkSetAirBaseCoor(75);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_75.setLayoutParams(vrLp);
					btnKorea_75.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(75);
						}
					}); 

					ImageButton btnKorea_76 =(ImageButton)findViewById(R.id.btnKorea_76);		        	        			        	
		        	fnkSetAirBaseCoor(76);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_76.setLayoutParams(vrLp);
					btnKorea_76.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(76);
						}
					}); 
					
					ImageButton btnKorea_77 =(ImageButton)findViewById(R.id.btnKorea_77);		        	        			        	
		        	fnkSetAirBaseCoor(77);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_77.setLayoutParams(vrLp);
					btnKorea_77.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(77);
						}
					}); 
					
					ImageButton btnKorea_78 =(ImageButton)findViewById(R.id.btnKorea_78);		        	        			        	
		        	fnkSetAirBaseCoor(78);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_78.setLayoutParams(vrLp);
					btnKorea_78.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(78);
						}
					}); 
					
					ImageButton btnKorea_79 =(ImageButton)findViewById(R.id.btnKorea_79);		        	        			        	
		        	fnkSetAirBaseCoor(79);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_79.setLayoutParams(vrLp);
					btnKorea_79.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(79);
						}
					}); 
					
					ImageButton btnKorea_80 =(ImageButton)findViewById(R.id.btnKorea_80);		        	        			        	
		        	fnkSetAirBaseCoor(80);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_80.setLayoutParams(vrLp);
					btnKorea_80.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(80);
						}
					}); 
					
					ImageButton btnKorea_81 =(ImageButton)findViewById(R.id.btnKorea_81);		        	        			        	
		        	fnkSetAirBaseCoor(81);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_81.setLayoutParams(vrLp);
					btnKorea_81.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(81);
						}
					}); 
					
					ImageButton btnKorea_82 =(ImageButton)findViewById(R.id.btnKorea_82);		        	        			        	
		        	fnkSetAirBaseCoor(82);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_82.setLayoutParams(vrLp);
					btnKorea_82.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(82);
						}
					}); 
					
					ImageButton btnKorea_83 =(ImageButton)findViewById(R.id.btnKorea_83);		        	        			        	
		        	fnkSetAirBaseCoor(83);	
					
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnKorea_83.setLayoutParams(vrLp);
					btnKorea_83.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(83);
							
						}
					}); 
					
		        } // Korea Sonu
		        
		        
		      //Balkan AirBases
		        if (vrHangiHarita == "Balkan")
		        {
		        	imgF16 = (ImageView)findViewById(R.id.imgF16Balkan);
		        		pnlDis = (AbsoluteLayout)findViewById(R.id.pnlBalkanDis);
		        		pnlDis.setVisibility(View.VISIBLE);
		        	lblTwr =(TextView)findViewById(R.id.lblTwr);
		    		lblTcnCh =(TextView)findViewById(R.id.lblTcnCh);
		    		lblTcnRng =(TextView)findViewById(R.id.lblTcnRng);
		    		lblILS =(TextView)findViewById(R.id.lblILS);
		    		lblRWY =(TextView)findViewById(R.id.lblRWY);
		    		lblElev =(TextView)findViewById(R.id.lblElev);
		    		lblCoor =(TextView)findViewById(R.id.lblCoor);
		        	
		    		lblAirBase =(TextView)findViewById(R.id.lblAirBase);
		    		
		    		ImageView imgHarita1;
		    		imgHarita1 = (ImageView)findViewById(R.id.imgBalkan1);		    		
		    		imgHarita1.setBackgroundResource(R.drawable.map_balkan1);
		    		
		    		ImageView imgHarita2;
		    		imgHarita2 = (ImageView)findViewById(R.id.imgBalkan2);		    		
		    		imgHarita2.setBackgroundResource(R.drawable.map_balkan2);
		    		
		    		ImageView imgHarita3;
		    		imgHarita3 = (ImageView)findViewById(R.id.imgBalkan3);		    		
		    		imgHarita3.setBackgroundResource(R.drawable.map_balkan3);
		    		
		    		ImageView imgHarita4;
		    		imgHarita4 = (ImageView)findViewById(R.id.imgBalkan4);		    		
		    		imgHarita4.setBackgroundResource(R.drawable.map_balkan4);
		    		
		    		ImageView imgHarita5;
		    		imgHarita5 = (ImageView)findViewById(R.id.imgBalkan5);		    		
		    		imgHarita5.setBackgroundResource(R.drawable.map_balkan5);
		    		
		    		ImageView imgHarita6;
		    		imgHarita6 = (ImageView)findViewById(R.id.imgBalkan6);		    		
		    		imgHarita6.setBackgroundResource(R.drawable.map_balkan6);
		    		
		    		ImageView imgHarita7;
		    		imgHarita7 = (ImageView)findViewById(R.id.imgBalkan7);		    		
		    		imgHarita7.setBackgroundResource(R.drawable.map_balkan7);
		    		
		    		ImageView imgHarita8;
		    		imgHarita8 = (ImageView)findViewById(R.id.imgBalkan8);		    		
		    		imgHarita8.setBackgroundResource(R.drawable.map_balkan8);
		    		
		    		ImageView imgHarita9;
		    		imgHarita9 = (ImageView)findViewById(R.id.imgBalkan9);		    		
		    		imgHarita9.setBackgroundResource(R.drawable.map_balkan9);
		    		
		    		
		    		
		        	
		        	
		        	fnkAirBases("balkan.xml");
		        	String vrId;
		        	
		        	AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(30,30,1 * 2 ,1 * 2);
		        	
		        	
		        	ImageButton btnBalkan_1 =(ImageButton)findViewById(R.id.btnBalkan_1);		        	        			        	
		        	fnkSetAirBaseCoor(1);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_1.setLayoutParams(vrLp);
					btnBalkan_1.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(1);
						}
					}); 
					
					ImageButton btnBalkan_2 =(ImageButton)findViewById(R.id.btnBalkan_2);		        	        			        	
		        	fnkSetAirBaseCoor(2);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_2.setLayoutParams(vrLp);
					btnBalkan_2.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(2);
						}
					}); 
					
					ImageButton btnBalkan_3 =(ImageButton)findViewById(R.id.btnBalkan_3);		        	        			        	
		        	fnkSetAirBaseCoor(3);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_3.setLayoutParams(vrLp);
					btnBalkan_3.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(3);
						}
					}); 
					
					ImageButton btnBalkan_4 =(ImageButton)findViewById(R.id.btnBalkan_4);		        	        			        	
		        	fnkSetAirBaseCoor(4);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_4.setLayoutParams(vrLp);
					btnBalkan_4.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(4);
						}
					}); 
					
					ImageButton btnBalkan_5 =(ImageButton)findViewById(R.id.btnBalkan_5);		        	        			        	
		        	fnkSetAirBaseCoor(5);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_5.setLayoutParams(vrLp);
					btnBalkan_5.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(5);
						}
					}); 
					
					ImageButton btnBalkan_6 =(ImageButton)findViewById(R.id.btnBalkan_6);		        	        			        	
		        	fnkSetAirBaseCoor(6);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_6.setLayoutParams(vrLp);
					btnBalkan_6.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(6);
						}
					}); 
					
					ImageButton btnBalkan_7 =(ImageButton)findViewById(R.id.btnBalkan_7);		        	        			        	
		        	fnkSetAirBaseCoor(7);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_7.setLayoutParams(vrLp);
					btnBalkan_7.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(7);
						}
					}); 
					
					ImageButton btnBalkan_8 =(ImageButton)findViewById(R.id.btnBalkan_8);		        	        			        	
		        	fnkSetAirBaseCoor(8);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_8.setLayoutParams(vrLp);
					btnBalkan_8.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(8);
						}
					}); 
					
					ImageButton btnBalkan_9 =(ImageButton)findViewById(R.id.btnBalkan_9);		        	        			        	
		        	fnkSetAirBaseCoor(9);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_9.setLayoutParams(vrLp);
					btnBalkan_9.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(9);
						}
					}); 
					
					ImageButton btnBalkan_10 =(ImageButton)findViewById(R.id.btnBalkan_10);		        	        			        	
		        	fnkSetAirBaseCoor(10);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_10.setLayoutParams(vrLp);
					btnBalkan_10.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(10);
						}
					}); 
					
					ImageButton btnBalkan_11 =(ImageButton)findViewById(R.id.btnBalkan_11);		        	        			        	
		        	fnkSetAirBaseCoor(11);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_11.setLayoutParams(vrLp);
					btnBalkan_11.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(11);
						}
					}); 
					
					ImageButton btnBalkan_12 =(ImageButton)findViewById(R.id.btnBalkan_12);		        	        			        	
		        	fnkSetAirBaseCoor(12);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_12.setLayoutParams(vrLp);
					btnBalkan_12.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(12);
						}
					}); 
					
					ImageButton btnBalkan_13 =(ImageButton)findViewById(R.id.btnBalkan_13);		        	        			        	
		        	fnkSetAirBaseCoor(13);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_13.setLayoutParams(vrLp);
					btnBalkan_13.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(13);
						}
					}); 
					
					ImageButton btnBalkan_14 =(ImageButton)findViewById(R.id.btnBalkan_14);		        	        			        	
		        	fnkSetAirBaseCoor(14);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_14.setLayoutParams(vrLp);
					btnBalkan_14.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(14);
						}
					}); 
					
					ImageButton btnBalkan_15 =(ImageButton)findViewById(R.id.btnBalkan_15);		        	        			        	
		        	fnkSetAirBaseCoor(15);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_15.setLayoutParams(vrLp);
					btnBalkan_15.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(15);
						}
					}); 

					ImageButton btnBalkan_16 =(ImageButton)findViewById(R.id.btnBalkan_16);		        	        			        	
		        	fnkSetAirBaseCoor(16);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_16.setLayoutParams(vrLp);
					btnBalkan_16.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(16);
						}
					}); 
					
					ImageButton btnBalkan_17 =(ImageButton)findViewById(R.id.btnBalkan_17);		        	        			        	
		        	fnkSetAirBaseCoor(17);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_17.setLayoutParams(vrLp);
					btnBalkan_17.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(17);
						}
					}); 
					
					ImageButton btnBalkan_18 =(ImageButton)findViewById(R.id.btnBalkan_18);		        	        			        	
		        	fnkSetAirBaseCoor(18);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_18.setLayoutParams(vrLp);
					btnBalkan_18.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(18);
						}
					}); 
					
					ImageButton btnBalkan_19 =(ImageButton)findViewById(R.id.btnBalkan_19);		        	        			        	
		        	fnkSetAirBaseCoor(19);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_19.setLayoutParams(vrLp);
					btnBalkan_19.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(19);
						}
					}); 
					
					
					
					ImageButton btnBalkan_20 =(ImageButton)findViewById(R.id.btnBalkan_20);		        	        			        	
		        	fnkSetAirBaseCoor(20);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_20.setLayoutParams(vrLp);
					btnBalkan_20.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(20);
						}
					}); 
					
					ImageButton btnBalkan_21 =(ImageButton)findViewById(R.id.btnBalkan_21);		        	        			        	
		        	fnkSetAirBaseCoor(21);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_21.setLayoutParams(vrLp);
					btnBalkan_21.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(21);
						}
					}); 
					
					ImageButton btnBalkan_22 =(ImageButton)findViewById(R.id.btnBalkan_22);		        	        			        	
		        	fnkSetAirBaseCoor(22);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_22.setLayoutParams(vrLp);
					btnBalkan_22.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(22);
						}
					}); 
					
					ImageButton btnBalkan_23 =(ImageButton)findViewById(R.id.btnBalkan_23);		        	        			        	
		        	fnkSetAirBaseCoor(23);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_23.setLayoutParams(vrLp);
					btnBalkan_23.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(23);
						}
					}); 
					
					ImageButton btnBalkan_24 =(ImageButton)findViewById(R.id.btnBalkan_24);		        	        			        	
		        	fnkSetAirBaseCoor(24);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_24.setLayoutParams(vrLp);
					btnBalkan_24.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(24);
						}
					}); 
					
					ImageButton btnBalkan_25 =(ImageButton)findViewById(R.id.btnBalkan_25);		        	        			        	
		        	fnkSetAirBaseCoor(25);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_25.setLayoutParams(vrLp);
					btnBalkan_25.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(25);
						}
					}); 

					ImageButton btnBalkan_26 =(ImageButton)findViewById(R.id.btnBalkan_26);		        	        			        	
		        	fnkSetAirBaseCoor(26);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_26.setLayoutParams(vrLp);
					btnBalkan_26.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(26);
						}
					}); 
					
					ImageButton btnBalkan_27 =(ImageButton)findViewById(R.id.btnBalkan_27);		        	        			        	
		        	fnkSetAirBaseCoor(27);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_27.setLayoutParams(vrLp);
					btnBalkan_27.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(27);
						}
					}); 
					
					ImageButton btnBalkan_28 =(ImageButton)findViewById(R.id.btnBalkan_28);		        	        			        	
		        	fnkSetAirBaseCoor(28);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_28.setLayoutParams(vrLp);
					btnBalkan_28.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(28);
						}
					}); 
					
					ImageButton btnBalkan_29 =(ImageButton)findViewById(R.id.btnBalkan_29);		        	        			        	
		        	fnkSetAirBaseCoor(29);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_29.setLayoutParams(vrLp);
					btnBalkan_29.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(29);
						}
					}); 
					
					
					ImageButton btnBalkan_30 =(ImageButton)findViewById(R.id.btnBalkan_30);		        	        			        	
		        	fnkSetAirBaseCoor(30);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_30.setLayoutParams(vrLp);
					btnBalkan_30.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(30);
						}
					}); 
					
					ImageButton btnBalkan_31 =(ImageButton)findViewById(R.id.btnBalkan_31);		        	        			        	
		        	fnkSetAirBaseCoor(31);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_31.setLayoutParams(vrLp);
					btnBalkan_31.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(31);
						}
					}); 
					
					ImageButton btnBalkan_32 =(ImageButton)findViewById(R.id.btnBalkan_32);		        	        			        	
		        	fnkSetAirBaseCoor(32);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_32.setLayoutParams(vrLp);
					btnBalkan_32.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(32);
						}
					}); 
					
					ImageButton btnBalkan_33 =(ImageButton)findViewById(R.id.btnBalkan_33);		        	        			        	
		        	fnkSetAirBaseCoor(33);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_33.setLayoutParams(vrLp);
					btnBalkan_33.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(33);
						}
					}); 
					
					ImageButton btnBalkan_34 =(ImageButton)findViewById(R.id.btnBalkan_34);		        	        			        	
		        	fnkSetAirBaseCoor(34);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_34.setLayoutParams(vrLp);
					btnBalkan_34.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(34);
						}
					}); 
					
					ImageButton btnBalkan_35 =(ImageButton)findViewById(R.id.btnBalkan_35);		        	        			        	
		        	fnkSetAirBaseCoor(35);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_35.setLayoutParams(vrLp);
					btnBalkan_35.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(35);
						}
					}); 

					ImageButton btnBalkan_36 =(ImageButton)findViewById(R.id.btnBalkan_36);		        	        			        	
		        	fnkSetAirBaseCoor(36);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_36.setLayoutParams(vrLp);
					btnBalkan_36.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(36);
						}
					}); 
					
					ImageButton btnBalkan_37 =(ImageButton)findViewById(R.id.btnBalkan_37);		        	        			        	
		        	fnkSetAirBaseCoor(37);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_37.setLayoutParams(vrLp);
					btnBalkan_37.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(37);
						}
					}); 
					
					ImageButton btnBalkan_38 =(ImageButton)findViewById(R.id.btnBalkan_38);		        	        			        	
		        	fnkSetAirBaseCoor(38);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_38.setLayoutParams(vrLp);
					btnBalkan_38.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(38);
						}
					}); 
					
					ImageButton btnBalkan_39 =(ImageButton)findViewById(R.id.btnBalkan_39);		        	        			        	
		        	fnkSetAirBaseCoor(39);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_39.setLayoutParams(vrLp);
					btnBalkan_39.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(39);
						}
					}); 
					
					
					ImageButton btnBalkan_40 =(ImageButton)findViewById(R.id.btnBalkan_40);		        	        			        	
		        	fnkSetAirBaseCoor(40);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_40.setLayoutParams(vrLp);
					btnBalkan_40.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(40);
						}
					}); 
					
					ImageButton btnBalkan_41 =(ImageButton)findViewById(R.id.btnBalkan_41);		        	        			        	
		        	fnkSetAirBaseCoor(41);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_41.setLayoutParams(vrLp);
					btnBalkan_41.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(41);
						}
					}); 
					
					ImageButton btnBalkan_42 =(ImageButton)findViewById(R.id.btnBalkan_42);		        	        			        	
		        	fnkSetAirBaseCoor(42);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_42.setLayoutParams(vrLp);
					btnBalkan_42.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(42);
						}
					}); 
					
					ImageButton btnBalkan_43 =(ImageButton)findViewById(R.id.btnBalkan_43);		        	        			        	
		        	fnkSetAirBaseCoor(43);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_43.setLayoutParams(vrLp);
					btnBalkan_43.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(43);
						}
					}); 
					
					ImageButton btnBalkan_44 =(ImageButton)findViewById(R.id.btnBalkan_44);		        	        			        	
		        	fnkSetAirBaseCoor(44);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_44.setLayoutParams(vrLp);
					btnBalkan_44.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(44);
						}
					}); 
					
					ImageButton btnBalkan_45 =(ImageButton)findViewById(R.id.btnBalkan_45);		        	        			        	
		        	fnkSetAirBaseCoor(45);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_45.setLayoutParams(vrLp);
					btnBalkan_45.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(45);
						}
					}); 

					ImageButton btnBalkan_46 =(ImageButton)findViewById(R.id.btnBalkan_46);		        	        			        	
		        	fnkSetAirBaseCoor(46);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_46.setLayoutParams(vrLp);
					btnBalkan_46.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(46);
						}
					}); 
					
					ImageButton btnBalkan_47 =(ImageButton)findViewById(R.id.btnBalkan_47);		        	        			        	
		        	fnkSetAirBaseCoor(47);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_47.setLayoutParams(vrLp);
					btnBalkan_47.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(47);
						}
					}); 
					
					ImageButton btnBalkan_48 =(ImageButton)findViewById(R.id.btnBalkan_48);		        	        			        	
		        	fnkSetAirBaseCoor(48);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_48.setLayoutParams(vrLp);
					btnBalkan_48.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(48);
						}
					}); 
					
					ImageButton btnBalkan_49 =(ImageButton)findViewById(R.id.btnBalkan_49);		        	        			        	
		        	fnkSetAirBaseCoor(49);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_49.setLayoutParams(vrLp);
					btnBalkan_49.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(49);
						}
					}); 
					
					
					ImageButton btnBalkan_50 =(ImageButton)findViewById(R.id.btnBalkan_50);		        	        			        	
		        	fnkSetAirBaseCoor(50);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_50.setLayoutParams(vrLp);
					btnBalkan_50.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(50);
						}
					}); 
					
					ImageButton btnBalkan_51 =(ImageButton)findViewById(R.id.btnBalkan_51);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_51.setLayoutParams(vrLp);
					btnBalkan_51.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(51);
						}
					}); 
					
					ImageButton btnBalkan_52 =(ImageButton)findViewById(R.id.btnBalkan_52);		        	        			        	
		        	fnkSetAirBaseCoor(52);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_52.setLayoutParams(vrLp);
					btnBalkan_52.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(52);
						}
					}); 
					
					ImageButton btnBalkan_53 =(ImageButton)findViewById(R.id.btnBalkan_53);		        	        			        	
		        	fnkSetAirBaseCoor(53);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_53.setLayoutParams(vrLp);
					btnBalkan_53.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(53);
						}
					}); 
					
					ImageButton btnBalkan_54 =(ImageButton)findViewById(R.id.btnBalkan_54);		        	        			        	
		        	fnkSetAirBaseCoor(54);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_54.setLayoutParams(vrLp);
					btnBalkan_54.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(54);
						}
					}); 
					
					ImageButton btnBalkan_55 =(ImageButton)findViewById(R.id.btnBalkan_55);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_55.setLayoutParams(vrLp);
					btnBalkan_55.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(55);
						}
					}); 

					ImageButton btnBalkan_56 =(ImageButton)findViewById(R.id.btnBalkan_56);		        	        			        	
		        	fnkSetAirBaseCoor(56);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_56.setLayoutParams(vrLp);
					btnBalkan_56.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(56);
						}
					}); 
					
					ImageButton btnBalkan_57 =(ImageButton)findViewById(R.id.btnBalkan_57);		        	        			        	
		        	fnkSetAirBaseCoor(57);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_57.setLayoutParams(vrLp);
					btnBalkan_57.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(57);
						}
					}); 
					
					ImageButton btnBalkan_58 =(ImageButton)findViewById(R.id.btnBalkan_58);		        	        			        	
		        	fnkSetAirBaseCoor(58);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_58.setLayoutParams(vrLp);
					btnBalkan_58.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(58);
						}
					}); 
					
					ImageButton btnBalkan_59 =(ImageButton)findViewById(R.id.btnBalkan_59);		        	        			        	
		        	fnkSetAirBaseCoor(59);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_59.setLayoutParams(vrLp);
					btnBalkan_59.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(59);
						}
					}); 
					
					
					ImageButton btnBalkan_60 =(ImageButton)findViewById(R.id.btnBalkan_60);		        	        			        	
		        	fnkSetAirBaseCoor(60);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_60.setLayoutParams(vrLp);
					btnBalkan_60.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(60);
						}
					}); 
					
					ImageButton btnBalkan_61 =(ImageButton)findViewById(R.id.btnBalkan_61);		        	        			        	
		        	fnkSetAirBaseCoor(61);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_61.setLayoutParams(vrLp);
					btnBalkan_61.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(61);
						}
					}); 
					
					ImageButton btnBalkan_62 =(ImageButton)findViewById(R.id.btnBalkan_62);		        	        			        	
		        	fnkSetAirBaseCoor(62);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_62.setLayoutParams(vrLp);
					btnBalkan_62.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(62);
						}
					}); 
					
					ImageButton btnBalkan_63 =(ImageButton)findViewById(R.id.btnBalkan_63);		        	        			        	
		        	fnkSetAirBaseCoor(63);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_63.setLayoutParams(vrLp);
					btnBalkan_63.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(63);
						}
					}); 
					
					ImageButton btnBalkan_64 =(ImageButton)findViewById(R.id.btnBalkan_64);		        	        			        	
		        	fnkSetAirBaseCoor(64);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_64.setLayoutParams(vrLp);
					btnBalkan_64.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(64);
						}
					}); 
					
					ImageButton btnBalkan_65 =(ImageButton)findViewById(R.id.btnBalkan_65);		        	        			        	
		        	fnkSetAirBaseCoor(65);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_65.setLayoutParams(vrLp);
					btnBalkan_65.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(65);
						}
					}); 

					ImageButton btnBalkan_66 =(ImageButton)findViewById(R.id.btnBalkan_66);		        	        			        	
		        	fnkSetAirBaseCoor(66);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_66.setLayoutParams(vrLp);
					btnBalkan_66.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(66);
						}
					}); 
					
					ImageButton btnBalkan_67 =(ImageButton)findViewById(R.id.btnBalkan_67);		        	        			        	
		        	fnkSetAirBaseCoor(67);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_67.setLayoutParams(vrLp);
					btnBalkan_67.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(67);
						}
					}); 
					
					ImageButton btnBalkan_68 =(ImageButton)findViewById(R.id.btnBalkan_68);		        	        			        	
		        	fnkSetAirBaseCoor(68);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_68.setLayoutParams(vrLp);
					btnBalkan_68.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(68);
						}
					}); 
					
					ImageButton btnBalkan_69 =(ImageButton)findViewById(R.id.btnBalkan_69);		        	        			        	
		        	fnkSetAirBaseCoor(69);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_69.setLayoutParams(vrLp);
					btnBalkan_69.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(69);
						}
					}); 
					
					ImageButton btnBalkan_70 =(ImageButton)findViewById(R.id.btnBalkan_70);		        	        			        	
		        	fnkSetAirBaseCoor(70);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_70.setLayoutParams(vrLp);
					btnBalkan_70.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(70);
						}
					}); 
					
					ImageButton btnBalkan_71 =(ImageButton)findViewById(R.id.btnBalkan_71);		        	        			        	
		        	fnkSetAirBaseCoor(71);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_71.setLayoutParams(vrLp);
					btnBalkan_71.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(71);
						}
					}); 
					
					ImageButton btnBalkan_72 =(ImageButton)findViewById(R.id.btnBalkan_72);		        	        			        	
		        	fnkSetAirBaseCoor(72);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_72.setLayoutParams(vrLp);
					btnBalkan_72.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(72);
						}
					}); 
					
					ImageButton btnBalkan_73 =(ImageButton)findViewById(R.id.btnBalkan_73);		        	        			        	
		        	fnkSetAirBaseCoor(73);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_73.setLayoutParams(vrLp);
					btnBalkan_73.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(73);
						}
					}); 
					
					ImageButton btnBalkan_74 =(ImageButton)findViewById(R.id.btnBalkan_74);		        	        			        	
		        	fnkSetAirBaseCoor(74);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_74.setLayoutParams(vrLp);
					btnBalkan_74.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(74);
						}
					}); 
					
					ImageButton btnBalkan_75 =(ImageButton)findViewById(R.id.btnBalkan_75);		        	        			        	
		        	fnkSetAirBaseCoor(75);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_75.setLayoutParams(vrLp);
					btnBalkan_75.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(75);
						}
					}); 

					ImageButton btnBalkan_76 =(ImageButton)findViewById(R.id.btnBalkan_76);		        	        			        	
		        	fnkSetAirBaseCoor(76);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_76.setLayoutParams(vrLp);
					btnBalkan_76.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(76);
						}
					}); 
					
					ImageButton btnBalkan_77 =(ImageButton)findViewById(R.id.btnBalkan_77);		        	        			        	
		        	fnkSetAirBaseCoor(77);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_77.setLayoutParams(vrLp);
					btnBalkan_77.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(77);
						}
					}); 
					
					ImageButton btnBalkan_78 =(ImageButton)findViewById(R.id.btnBalkan_78);		        	        			        	
		        	fnkSetAirBaseCoor(78);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_78.setLayoutParams(vrLp);
					btnBalkan_78.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(78);
						}
					}); 
					
					ImageButton btnBalkan_79 =(ImageButton)findViewById(R.id.btnBalkan_79);		        	        			        	
		        	fnkSetAirBaseCoor(79);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_79.setLayoutParams(vrLp);
					btnBalkan_79.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(79);
						}
					}); 
					

					ImageButton btnBalkan_80 =(ImageButton)findViewById(R.id.btnBalkan_80);		        	        			        	
		        	fnkSetAirBaseCoor(80);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_80.setLayoutParams(vrLp);
					btnBalkan_80.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(80);
						}
					}); 
					
					ImageButton btnBalkan_81 =(ImageButton)findViewById(R.id.btnBalkan_81);		        	        			        	
		        	fnkSetAirBaseCoor(81);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_81.setLayoutParams(vrLp);
					btnBalkan_81.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(81);
						}
					}); 
					
					ImageButton btnBalkan_82 =(ImageButton)findViewById(R.id.btnBalkan_82);		        	        			        	
		        	fnkSetAirBaseCoor(82);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_82.setLayoutParams(vrLp);
					btnBalkan_82.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(82);
						}
					}); 
					
					ImageButton btnBalkan_83 =(ImageButton)findViewById(R.id.btnBalkan_83);		        	        			        	
		        	fnkSetAirBaseCoor(83);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_83.setLayoutParams(vrLp);
					btnBalkan_83.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(83);
						}
					}); 
					
					ImageButton btnBalkan_84 =(ImageButton)findViewById(R.id.btnBalkan_84);		        	        			        	
		        	fnkSetAirBaseCoor(84);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_84.setLayoutParams(vrLp);
					btnBalkan_84.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(84);
						}
					}); 
					
					ImageButton btnBalkan_85 =(ImageButton)findViewById(R.id.btnBalkan_85);		        	        			        	
		        	fnkSetAirBaseCoor(85);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_85.setLayoutParams(vrLp);
					btnBalkan_85.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(85);
						}
					}); 

					ImageButton btnBalkan_86 =(ImageButton)findViewById(R.id.btnBalkan_86);		        	        			        	
		        	fnkSetAirBaseCoor(86);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_86.setLayoutParams(vrLp);
					btnBalkan_86.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(86);
						}
					}); 
					
					ImageButton btnBalkan_87 =(ImageButton)findViewById(R.id.btnBalkan_87);		        	        			        	
		        	fnkSetAirBaseCoor(87);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_87.setLayoutParams(vrLp);
					btnBalkan_87.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(87);
						}
					}); 
					
					ImageButton btnBalkan_88 =(ImageButton)findViewById(R.id.btnBalkan_88);		        	        			        	
		        	fnkSetAirBaseCoor(88);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_88.setLayoutParams(vrLp);
					btnBalkan_88.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(88);
						}
					}); 
					
					ImageButton btnBalkan_89 =(ImageButton)findViewById(R.id.btnBalkan_89);		        	        			        	
		        	fnkSetAirBaseCoor(89);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_89.setLayoutParams(vrLp);
					btnBalkan_89.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(89);
						}
					}); 
					
					
					ImageButton btnBalkan_90 =(ImageButton)findViewById(R.id.btnBalkan_90);		        	        			        	
		        	fnkSetAirBaseCoor(90);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_90.setLayoutParams(vrLp);
					btnBalkan_90.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(90);
						}
					}); 
					
					ImageButton btnBalkan_91 =(ImageButton)findViewById(R.id.btnBalkan_91);		        	        			        	
		        	fnkSetAirBaseCoor(91);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_91.setLayoutParams(vrLp);
					btnBalkan_91.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(91);
						}
					}); 
					
					ImageButton btnBalkan_92 =(ImageButton)findViewById(R.id.btnBalkan_92);		        	        			        	
		        	fnkSetAirBaseCoor(92);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_92.setLayoutParams(vrLp);
					btnBalkan_92.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(92);
						}
					}); 
					
					ImageButton btnBalkan_93 =(ImageButton)findViewById(R.id.btnBalkan_93);		        	        			        	
		        	fnkSetAirBaseCoor(93);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_93.setLayoutParams(vrLp);
					btnBalkan_93.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(93);
						}
					}); 
					
					ImageButton btnBalkan_94 =(ImageButton)findViewById(R.id.btnBalkan_94);		        	        			        	
		        	fnkSetAirBaseCoor(94);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_94.setLayoutParams(vrLp);
					btnBalkan_94.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(94);
						}
					}); 
					
					ImageButton btnBalkan_95 =(ImageButton)findViewById(R.id.btnBalkan_95);		        	        			        	
		        	fnkSetAirBaseCoor(95);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_95.setLayoutParams(vrLp);
					btnBalkan_95.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(95);
						}
					}); 

					ImageButton btnBalkan_96 =(ImageButton)findViewById(R.id.btnBalkan_96);		        	        			        	
		        	fnkSetAirBaseCoor(96);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_96.setLayoutParams(vrLp);
					btnBalkan_96.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(96);
						}
					}); 
					
					ImageButton btnBalkan_97 =(ImageButton)findViewById(R.id.btnBalkan_97);		        	        			        	
		        	fnkSetAirBaseCoor(97);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_97.setLayoutParams(vrLp);
					btnBalkan_97.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(97);
						}
					}); 
					
					ImageButton btnBalkan_98 =(ImageButton)findViewById(R.id.btnBalkan_98);		        	        			        	
		        	fnkSetAirBaseCoor(98);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_98.setLayoutParams(vrLp);
					btnBalkan_98.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(98);
						}
					}); 
					
					ImageButton btnBalkan_99 =(ImageButton)findViewById(R.id.btnBalkan_99);		        	        			        	
		        	fnkSetAirBaseCoor(99);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_99.setLayoutParams(vrLp);
					btnBalkan_99.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(99);
						}
					}); 
						
					ImageButton btnBalkan_100 =(ImageButton)findViewById(R.id.btnBalkan_100);		        	        			        	
		        	fnkSetAirBaseCoor(100);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_100.setLayoutParams(vrLp);
					btnBalkan_100.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(100);
						}
					}); 
					
					ImageButton btnBalkan_101 =(ImageButton)findViewById(R.id.btnBalkan_101);		        	        			        	
		        	fnkSetAirBaseCoor(101);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_101.setLayoutParams(vrLp);
					btnBalkan_101.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(101);
						}
					}); 
					
					ImageButton btnBalkan_102 =(ImageButton)findViewById(R.id.btnBalkan_102);		        	        			        	
		        	fnkSetAirBaseCoor(102);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_102.setLayoutParams(vrLp);
					btnBalkan_102.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(102);
						}
					}); 
					
					ImageButton btnBalkan_103 =(ImageButton)findViewById(R.id.btnBalkan_103);		        	        			        	
		        	fnkSetAirBaseCoor(103);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_103.setLayoutParams(vrLp);
					btnBalkan_103.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(103);
						}
					}); 
					
					ImageButton btnBalkan_104 =(ImageButton)findViewById(R.id.btnBalkan_104);		        	        			        	
		        	fnkSetAirBaseCoor(104);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_104.setLayoutParams(vrLp);
					btnBalkan_104.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(104);
						}
					}); 
					
					ImageButton btnBalkan_105 =(ImageButton)findViewById(R.id.btnBalkan_105);		        	        			        	
		        	fnkSetAirBaseCoor(105);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_105.setLayoutParams(vrLp);
					btnBalkan_105.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(105);
						}
					}); 

					ImageButton btnBalkan_106 =(ImageButton)findViewById(R.id.btnBalkan_106);		        	        			        	
		        	fnkSetAirBaseCoor(106);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_106.setLayoutParams(vrLp);
					btnBalkan_106.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(106);
						}
					}); 
					
					ImageButton btnBalkan_107 =(ImageButton)findViewById(R.id.btnBalkan_107);		        	        			        	
		        	fnkSetAirBaseCoor(107);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_107.setLayoutParams(vrLp);
					btnBalkan_107.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(107);
						}
					}); 
					
					ImageButton btnBalkan_108 =(ImageButton)findViewById(R.id.btnBalkan_108);		        	        			        	
		        	fnkSetAirBaseCoor(108);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_108.setLayoutParams(vrLp);
					btnBalkan_108.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(108);
						}
					}); 
					
					ImageButton btnBalkan_109 =(ImageButton)findViewById(R.id.btnBalkan_109);		        	        			        	
		        	fnkSetAirBaseCoor(109);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_109.setLayoutParams(vrLp);
					btnBalkan_109.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(109);
						}
					}); 
					
					ImageButton btnBalkan_110 =(ImageButton)findViewById(R.id.btnBalkan_110);		        	        			        	
		        	fnkSetAirBaseCoor(110);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_110.setLayoutParams(vrLp);
					btnBalkan_110.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(110);
						}
					}); 
					
					ImageButton btnBalkan_111 =(ImageButton)findViewById(R.id.btnBalkan_111);		        	        			        	
		        	fnkSetAirBaseCoor(111);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnBalkan_111.setLayoutParams(vrLp);
					btnBalkan_111.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(111);
						}
					}); 

					
					
		        } // Balkan Sonu
		        
		        
			      //Israil AirBases
		        if (vrHangiHarita == "Israil")
		        {
		        	imgF16 = (ImageView)findViewById(R.id.imgF16Israil);
		        		pnlDis = (AbsoluteLayout)findViewById(R.id.pnlIsrailDis);
		        		pnlDis.setVisibility(View.VISIBLE);
		        	lblTwr =(TextView)findViewById(R.id.lblTwr);
		    		lblTcnCh =(TextView)findViewById(R.id.lblTcnCh);
		    		lblTcnRng =(TextView)findViewById(R.id.lblTcnRng);
		    		lblILS =(TextView)findViewById(R.id.lblILS);
		    		lblRWY =(TextView)findViewById(R.id.lblRWY);
		    		lblElev =(TextView)findViewById(R.id.lblElev);
		    		lblCoor =(TextView)findViewById(R.id.lblCoor);
		        	
		    		lblAirBase =(TextView)findViewById(R.id.lblAirBase);
		    		
		    		ImageView imgHarita1;
		    		imgHarita1 = (ImageView)findViewById(R.id.imgIsrail1);		    		
		    		imgHarita1.setBackgroundResource(R.drawable.map_israil1);
		    		
		    		ImageView imgHarita2;
		    		imgHarita2 = (ImageView)findViewById(R.id.imgIsrail2);		    		
		    		imgHarita2.setBackgroundResource(R.drawable.map_israil2);
		    		
		    		ImageView imgHarita3;
		    		imgHarita3 = (ImageView)findViewById(R.id.imgIsrail3);		    		
		    		imgHarita3.setBackgroundResource(R.drawable.map_israil3);
		    		
		    		ImageView imgHarita4;
		    		imgHarita4 = (ImageView)findViewById(R.id.imgIsrail4);		    		
		    		imgHarita4.setBackgroundResource(R.drawable.map_israil4);
		    		
		    		ImageView imgHarita5;
		    		imgHarita5 = (ImageView)findViewById(R.id.imgIsrail5);		    		
		    		imgHarita5.setBackgroundResource(R.drawable.map_israil5);
		    		
		    		ImageView imgHarita6;
		    		imgHarita6 = (ImageView)findViewById(R.id.imgIsrail6);		    		
		    		imgHarita6.setBackgroundResource(R.drawable.map_israil6);
		    		
		    		ImageView imgHarita7;
		    		imgHarita7 = (ImageView)findViewById(R.id.imgIsrail7);		    		
		    		imgHarita7.setBackgroundResource(R.drawable.map_israil7);
		    		
		    		ImageView imgHarita8;
		    		imgHarita8 = (ImageView)findViewById(R.id.imgIsrail8);		    		
		    		imgHarita8.setBackgroundResource(R.drawable.map_israil8);
		    		
		    		ImageView imgHarita9;
		    		imgHarita9 = (ImageView)findViewById(R.id.imgIsrail9);		    		
		    		imgHarita9.setBackgroundResource(R.drawable.map_israil9);
		        	
		        	
		        	fnkAirBases("israil.xml");
		        	String vrId;
		        	
		        	AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(30,30,1 * 2 ,1 * 2);
		        	
		        	
		        	ImageButton btnIsrail_1 =(ImageButton)findViewById(R.id.btnIsrail_1);		        	        			        	
		        	fnkSetAirBaseCoor(1);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_1.setLayoutParams(vrLp);
					btnIsrail_1.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(1);
						}
					}); 
					
					ImageButton btnIsrail_2 =(ImageButton)findViewById(R.id.btnIsrail_2);		        	        			        	
		        	fnkSetAirBaseCoor(2);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_2.setLayoutParams(vrLp);
					btnIsrail_2.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(2);
						}
					}); 
					
					ImageButton btnIsrail_3 =(ImageButton)findViewById(R.id.btnIsrail_3);		        	        			        	
		        	fnkSetAirBaseCoor(3);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_3.setLayoutParams(vrLp);
					btnIsrail_3.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(3);
						}
					}); 
					
					ImageButton btnIsrail_4 =(ImageButton)findViewById(R.id.btnIsrail_4);		        	        			        	
		        	fnkSetAirBaseCoor(4);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_4.setLayoutParams(vrLp);
					btnIsrail_4.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(4);
						}
					}); 
					
					ImageButton btnIsrail_5 =(ImageButton)findViewById(R.id.btnIsrail_5);		        	        			        	
		        	fnkSetAirBaseCoor(5);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_5.setLayoutParams(vrLp);
					btnIsrail_5.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(5);
						}
					}); 
					
					ImageButton btnIsrail_6 =(ImageButton)findViewById(R.id.btnIsrail_6);		        	        			        	
		        	fnkSetAirBaseCoor(6);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_6.setLayoutParams(vrLp);
					btnIsrail_6.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(6);
						}
					}); 
					
					ImageButton btnIsrail_7 =(ImageButton)findViewById(R.id.btnIsrail_7);		        	        			        	
		        	fnkSetAirBaseCoor(7);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_7.setLayoutParams(vrLp);
					btnIsrail_7.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(7);
						}
					}); 
					
					ImageButton btnIsrail_8 =(ImageButton)findViewById(R.id.btnIsrail_8);		        	        			        	
		        	fnkSetAirBaseCoor(8);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_8.setLayoutParams(vrLp);
					btnIsrail_8.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(8);
						}
					}); 
					
					ImageButton btnIsrail_9 =(ImageButton)findViewById(R.id.btnIsrail_9);		        	        			        	
		        	fnkSetAirBaseCoor(9);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_9.setLayoutParams(vrLp);
					btnIsrail_9.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(9);
						}
					}); 
					
					ImageButton btnIsrail_10 =(ImageButton)findViewById(R.id.btnIsrail_10);		        	        			        	
		        	fnkSetAirBaseCoor(10);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_10.setLayoutParams(vrLp);
					btnIsrail_10.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(10);
						}
					}); 
					
					ImageButton btnIsrail_11 =(ImageButton)findViewById(R.id.btnIsrail_11);		        	        			        	
		        	fnkSetAirBaseCoor(11);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_11.setLayoutParams(vrLp);
					btnIsrail_11.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(11);
						}
					}); 
					
					ImageButton btnIsrail_12 =(ImageButton)findViewById(R.id.btnIsrail_12);		        	        			        	
		        	fnkSetAirBaseCoor(12);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_12.setLayoutParams(vrLp);
					btnIsrail_12.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(12);
						}
					}); 
					
					ImageButton btnIsrail_13 =(ImageButton)findViewById(R.id.btnIsrail_13);		        	        			        	
		        	fnkSetAirBaseCoor(13);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_13.setLayoutParams(vrLp);
					btnIsrail_13.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(13);
						}
					}); 
					
					ImageButton btnIsrail_14 =(ImageButton)findViewById(R.id.btnIsrail_14);		        	        			        	
		        	fnkSetAirBaseCoor(14);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_14.setLayoutParams(vrLp);
					btnIsrail_14.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(14);
						}
					}); 
					
					ImageButton btnIsrail_15 =(ImageButton)findViewById(R.id.btnIsrail_15);		        	        			        	
		        	fnkSetAirBaseCoor(15);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_15.setLayoutParams(vrLp);
					btnIsrail_15.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(15);
						}
					}); 

					ImageButton btnIsrail_16 =(ImageButton)findViewById(R.id.btnIsrail_16);		        	        			        	
		        	fnkSetAirBaseCoor(16);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_16.setLayoutParams(vrLp);
					btnIsrail_16.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(16);
						}
					}); 
					
					ImageButton btnIsrail_17 =(ImageButton)findViewById(R.id.btnIsrail_17);		        	        			        	
		        	fnkSetAirBaseCoor(17);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_17.setLayoutParams(vrLp);
					btnIsrail_17.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(17);
						}
					}); 
					
					ImageButton btnIsrail_18 =(ImageButton)findViewById(R.id.btnIsrail_18);		        	        			        	
		        	fnkSetAirBaseCoor(18);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_18.setLayoutParams(vrLp);
					btnIsrail_18.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(18);
						}
					}); 
					
					ImageButton btnIsrail_19 =(ImageButton)findViewById(R.id.btnIsrail_19);		        	        			        	
		        	fnkSetAirBaseCoor(19);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_19.setLayoutParams(vrLp);
					btnIsrail_19.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(19);
						}
					}); 
					
					
					
					ImageButton btnIsrail_20 =(ImageButton)findViewById(R.id.btnIsrail_20);		        	        			        	
		        	fnkSetAirBaseCoor(20);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_20.setLayoutParams(vrLp);
					btnIsrail_20.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(20);
						}
					}); 
					
					ImageButton btnIsrail_21 =(ImageButton)findViewById(R.id.btnIsrail_21);		        	        			        	
		        	fnkSetAirBaseCoor(21);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_21.setLayoutParams(vrLp);
					btnIsrail_21.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(21);
						}
					}); 
					
					ImageButton btnIsrail_22 =(ImageButton)findViewById(R.id.btnIsrail_22);		        	        			        	
		        	fnkSetAirBaseCoor(22);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_22.setLayoutParams(vrLp);
					btnIsrail_22.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(22);
						}
					}); 
					
					ImageButton btnIsrail_23 =(ImageButton)findViewById(R.id.btnIsrail_23);		        	        			        	
		        	fnkSetAirBaseCoor(23);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_23.setLayoutParams(vrLp);
					btnIsrail_23.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(23);
						}
					}); 
					
					ImageButton btnIsrail_24 =(ImageButton)findViewById(R.id.btnIsrail_24);		        	        			        	
		        	fnkSetAirBaseCoor(24);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_24.setLayoutParams(vrLp);
					btnIsrail_24.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(24);
						}
					}); 
					
					ImageButton btnIsrail_25 =(ImageButton)findViewById(R.id.btnIsrail_25);		        	        			        	
		        	fnkSetAirBaseCoor(25);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_25.setLayoutParams(vrLp);
					btnIsrail_25.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(25);
						}
					}); 

					ImageButton btnIsrail_26 =(ImageButton)findViewById(R.id.btnIsrail_26);		        	        			        	
		        	fnkSetAirBaseCoor(26);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_26.setLayoutParams(vrLp);
					btnIsrail_26.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(26);
						}
					}); 
					
					ImageButton btnIsrail_27 =(ImageButton)findViewById(R.id.btnIsrail_27);		        	        			        	
		        	fnkSetAirBaseCoor(27);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_27.setLayoutParams(vrLp);
					btnIsrail_27.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(27);
						}
					}); 
					
					ImageButton btnIsrail_28 =(ImageButton)findViewById(R.id.btnIsrail_28);		        	        			        	
		        	fnkSetAirBaseCoor(28);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_28.setLayoutParams(vrLp);
					btnIsrail_28.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(28);
						}
					}); 
					
					ImageButton btnIsrail_29 =(ImageButton)findViewById(R.id.btnIsrail_29);		        	        			        	
		        	fnkSetAirBaseCoor(29);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_29.setLayoutParams(vrLp);
					btnIsrail_29.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(29);
						}
					}); 
					
					
					ImageButton btnIsrail_30 =(ImageButton)findViewById(R.id.btnIsrail_30);		        	        			        	
		        	fnkSetAirBaseCoor(30);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_30.setLayoutParams(vrLp);
					btnIsrail_30.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(30);
						}
					}); 
					
					ImageButton btnIsrail_31 =(ImageButton)findViewById(R.id.btnIsrail_31);		        	        			        	
		        	fnkSetAirBaseCoor(31);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_31.setLayoutParams(vrLp);
					btnIsrail_31.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(31);
						}
					}); 
					
					ImageButton btnIsrail_32 =(ImageButton)findViewById(R.id.btnIsrail_32);		        	        			        	
		        	fnkSetAirBaseCoor(32);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_32.setLayoutParams(vrLp);
					btnIsrail_32.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(32);
						}
					}); 
					
					ImageButton btnIsrail_33 =(ImageButton)findViewById(R.id.btnIsrail_33);		        	        			        	
		        	fnkSetAirBaseCoor(33);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_33.setLayoutParams(vrLp);
					btnIsrail_33.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(33);
						}
					}); 
					
					ImageButton btnIsrail_34 =(ImageButton)findViewById(R.id.btnIsrail_34);		        	        			        	
		        	fnkSetAirBaseCoor(34);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_34.setLayoutParams(vrLp);
					btnIsrail_34.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(34);
						}
					}); 
					
					ImageButton btnIsrail_35 =(ImageButton)findViewById(R.id.btnIsrail_35);		        	        			        	
		        	fnkSetAirBaseCoor(35);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_35.setLayoutParams(vrLp);
					btnIsrail_35.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(35);
						}
					}); 

					ImageButton btnIsrail_36 =(ImageButton)findViewById(R.id.btnIsrail_36);		        	        			        	
		        	fnkSetAirBaseCoor(36);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_36.setLayoutParams(vrLp);
					btnIsrail_36.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(36);
						}
					}); 
					
					ImageButton btnIsrail_37 =(ImageButton)findViewById(R.id.btnIsrail_37);		        	        			        	
		        	fnkSetAirBaseCoor(37);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_37.setLayoutParams(vrLp);
					btnIsrail_37.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(37);
						}
					}); 
					
					ImageButton btnIsrail_38 =(ImageButton)findViewById(R.id.btnIsrail_38);		        	        			        	
		        	fnkSetAirBaseCoor(38);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_38.setLayoutParams(vrLp);
					btnIsrail_38.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(38);
						}
					}); 
					
					ImageButton btnIsrail_39 =(ImageButton)findViewById(R.id.btnIsrail_39);		        	        			        	
		        	fnkSetAirBaseCoor(39);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_39.setLayoutParams(vrLp);
					btnIsrail_39.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(39);
						}
					}); 
					
					
					ImageButton btnIsrail_40 =(ImageButton)findViewById(R.id.btnIsrail_40);		        	        			        	
		        	fnkSetAirBaseCoor(40);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_40.setLayoutParams(vrLp);
					btnIsrail_40.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(40);
						}
					}); 
					
					ImageButton btnIsrail_41 =(ImageButton)findViewById(R.id.btnIsrail_41);		        	        			        	
		        	fnkSetAirBaseCoor(41);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_41.setLayoutParams(vrLp);
					btnIsrail_41.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(41);
						}
					}); 
					
					ImageButton btnIsrail_42 =(ImageButton)findViewById(R.id.btnIsrail_42);		        	        			        	
		        	fnkSetAirBaseCoor(42);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_42.setLayoutParams(vrLp);
					btnIsrail_42.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(42);
						}
					}); 
					
					ImageButton btnIsrail_43 =(ImageButton)findViewById(R.id.btnIsrail_43);		        	        			        	
		        	fnkSetAirBaseCoor(43);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_43.setLayoutParams(vrLp);
					btnIsrail_43.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(43);
						}
					}); 
					
					ImageButton btnIsrail_44 =(ImageButton)findViewById(R.id.btnIsrail_44);		        	        			        	
		        	fnkSetAirBaseCoor(44);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_44.setLayoutParams(vrLp);
					btnIsrail_44.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(44);
						}
					}); 
					
					ImageButton btnIsrail_45 =(ImageButton)findViewById(R.id.btnIsrail_45);		        	        			        	
		        	fnkSetAirBaseCoor(45);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_45.setLayoutParams(vrLp);
					btnIsrail_45.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(45);
						}
					}); 

					ImageButton btnIsrail_46 =(ImageButton)findViewById(R.id.btnIsrail_46);		        	        			        	
		        	fnkSetAirBaseCoor(46);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_46.setLayoutParams(vrLp);
					btnIsrail_46.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(46);
						}
					}); 
					
					ImageButton btnIsrail_47 =(ImageButton)findViewById(R.id.btnIsrail_47);		        	        			        	
		        	fnkSetAirBaseCoor(47);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_47.setLayoutParams(vrLp);
					btnIsrail_47.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(47);
						}
					}); 
					
					ImageButton btnIsrail_48 =(ImageButton)findViewById(R.id.btnIsrail_48);		        	        			        	
		        	fnkSetAirBaseCoor(48);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_48.setLayoutParams(vrLp);
					btnIsrail_48.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(48);
						}
					}); 
					
					ImageButton btnIsrail_49 =(ImageButton)findViewById(R.id.btnIsrail_49);		        	        			        	
		        	fnkSetAirBaseCoor(49);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_49.setLayoutParams(vrLp);
					btnIsrail_49.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(49);
						}
					}); 
					
					
					ImageButton btnIsrail_50 =(ImageButton)findViewById(R.id.btnIsrail_50);		        	        			        	
		        	fnkSetAirBaseCoor(50);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_50.setLayoutParams(vrLp);
					btnIsrail_50.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(50);
						}
					}); 
					
					ImageButton btnIsrail_51 =(ImageButton)findViewById(R.id.btnIsrail_51);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_51.setLayoutParams(vrLp);
					btnIsrail_51.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(51);
						}
					}); 
					
					ImageButton btnIsrail_52 =(ImageButton)findViewById(R.id.btnIsrail_52);		        	        			        	
		        	fnkSetAirBaseCoor(52);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_52.setLayoutParams(vrLp);
					btnIsrail_52.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(52);
						}
					}); 
					
					ImageButton btnIsrail_53 =(ImageButton)findViewById(R.id.btnIsrail_53);		        	        			        	
		        	fnkSetAirBaseCoor(53);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_53.setLayoutParams(vrLp);
					btnIsrail_53.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(53);
						}
					}); 
					
					ImageButton btnIsrail_54 =(ImageButton)findViewById(R.id.btnIsrail_54);		        	        			        	
		        	fnkSetAirBaseCoor(54);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_54.setLayoutParams(vrLp);
					btnIsrail_54.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(54);
						}
					}); 
					
					ImageButton btnIsrail_55 =(ImageButton)findViewById(R.id.btnIsrail_55);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_55.setLayoutParams(vrLp);
					btnIsrail_55.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(55);
						}
					}); 

					ImageButton btnIsrail_56 =(ImageButton)findViewById(R.id.btnIsrail_56);		        	        			        	
		        	fnkSetAirBaseCoor(56);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_56.setLayoutParams(vrLp);
					btnIsrail_56.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(56);
						}
					}); 
					
					ImageButton btnIsrail_57 =(ImageButton)findViewById(R.id.btnIsrail_57);		        	        			        	
		        	fnkSetAirBaseCoor(57);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_57.setLayoutParams(vrLp);
					btnIsrail_57.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(57);
						}
					}); 
					
					ImageButton btnIsrail_58 =(ImageButton)findViewById(R.id.btnIsrail_58);		        	        			        	
		        	fnkSetAirBaseCoor(58);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_58.setLayoutParams(vrLp);
					btnIsrail_58.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(58);
						}
					}); 
					
					ImageButton btnIsrail_59 =(ImageButton)findViewById(R.id.btnIsrail_59);		        	        			        	
		        	fnkSetAirBaseCoor(59);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_59.setLayoutParams(vrLp);
					btnIsrail_59.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(59);
						}
					}); 
					
					
					ImageButton btnIsrail_60 =(ImageButton)findViewById(R.id.btnIsrail_60);		        	        			        	
		        	fnkSetAirBaseCoor(60);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_60.setLayoutParams(vrLp);
					btnIsrail_60.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(60);
						}
					}); 
					
					ImageButton btnIsrail_61 =(ImageButton)findViewById(R.id.btnIsrail_61);		        	        			        	
		        	fnkSetAirBaseCoor(61);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_61.setLayoutParams(vrLp);
					btnIsrail_61.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(61);
						}
					}); 
					
					ImageButton btnIsrail_62 =(ImageButton)findViewById(R.id.btnIsrail_62);		        	        			        	
		        	fnkSetAirBaseCoor(62);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_62.setLayoutParams(vrLp);
					btnIsrail_62.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(62);
						}
					}); 
					
					ImageButton btnIsrail_63 =(ImageButton)findViewById(R.id.btnIsrail_63);		        	        			        	
		        	fnkSetAirBaseCoor(63);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_63.setLayoutParams(vrLp);
					btnIsrail_63.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(63);
						}
					}); 
					
					ImageButton btnIsrail_64 =(ImageButton)findViewById(R.id.btnIsrail_64);		        	        			        	
		        	fnkSetAirBaseCoor(64);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_64.setLayoutParams(vrLp);
					btnIsrail_64.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(64);
						}
					}); 
					
					ImageButton btnIsrail_65 =(ImageButton)findViewById(R.id.btnIsrail_65);		        	        			        	
		        	fnkSetAirBaseCoor(65);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_65.setLayoutParams(vrLp);
					btnIsrail_65.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(65);
						}
					}); 

					ImageButton btnIsrail_66 =(ImageButton)findViewById(R.id.btnIsrail_66);		        	        			        	
		        	fnkSetAirBaseCoor(66);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_66.setLayoutParams(vrLp);
					btnIsrail_66.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(66);
						}
					}); 
					
					ImageButton btnIsrail_67 =(ImageButton)findViewById(R.id.btnIsrail_67);		        	        			        	
		        	fnkSetAirBaseCoor(67);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_67.setLayoutParams(vrLp);
					btnIsrail_67.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(67);
						}
					}); 
					
					ImageButton btnIsrail_68 =(ImageButton)findViewById(R.id.btnIsrail_68);		        	        			        	
		        	fnkSetAirBaseCoor(68);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_68.setLayoutParams(vrLp);
					btnIsrail_68.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(68);
						}
					}); 
					
					ImageButton btnIsrail_69 =(ImageButton)findViewById(R.id.btnIsrail_69);		        	        			        	
		        	fnkSetAirBaseCoor(69);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_69.setLayoutParams(vrLp);
					btnIsrail_69.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(69);
						}
					}); 
					
					ImageButton btnIsrail_70 =(ImageButton)findViewById(R.id.btnIsrail_70);		        	        			        	
		        	fnkSetAirBaseCoor(70);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_70.setLayoutParams(vrLp);
					btnIsrail_70.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(70);
						}
					}); 
					
					ImageButton btnIsrail_71 =(ImageButton)findViewById(R.id.btnIsrail_71);		        	        			        	
		        	fnkSetAirBaseCoor(71);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_71.setLayoutParams(vrLp);
					btnIsrail_71.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(71);
						}
					}); 
					
					ImageButton btnIsrail_72 =(ImageButton)findViewById(R.id.btnIsrail_72);		        	        			        	
		        	fnkSetAirBaseCoor(72);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_72.setLayoutParams(vrLp);
					btnIsrail_72.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(72);
						}
					}); 
					
					ImageButton btnIsrail_73 =(ImageButton)findViewById(R.id.btnIsrail_73);		        	        			        	
		        	fnkSetAirBaseCoor(73);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_73.setLayoutParams(vrLp);
					btnIsrail_73.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(73);
						}
					}); 
					
					ImageButton btnIsrail_74 =(ImageButton)findViewById(R.id.btnIsrail_74);		        	        			        	
		        	fnkSetAirBaseCoor(74);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_74.setLayoutParams(vrLp);
					btnIsrail_74.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(74);
						}
					}); 
					
					ImageButton btnIsrail_75 =(ImageButton)findViewById(R.id.btnIsrail_75);		        	        			        	
		        	fnkSetAirBaseCoor(75);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_75.setLayoutParams(vrLp);
					btnIsrail_75.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(75);
						}
					}); 

					ImageButton btnIsrail_76 =(ImageButton)findViewById(R.id.btnIsrail_76);		        	        			        	
		        	fnkSetAirBaseCoor(76);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_76.setLayoutParams(vrLp);
					btnIsrail_76.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(76);
						}
					}); 
					
					ImageButton btnIsrail_77 =(ImageButton)findViewById(R.id.btnIsrail_77);		        	        			        	
		        	fnkSetAirBaseCoor(77);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_77.setLayoutParams(vrLp);
					btnIsrail_77.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(77);
						}
					}); 
					
					ImageButton btnIsrail_78 =(ImageButton)findViewById(R.id.btnIsrail_78);		        	        			        	
		        	fnkSetAirBaseCoor(78);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_78.setLayoutParams(vrLp);
					btnIsrail_78.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(78);
						}
					}); 
					
					ImageButton btnIsrail_79 =(ImageButton)findViewById(R.id.btnIsrail_79);		        	        			        	
		        	fnkSetAirBaseCoor(79);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_79.setLayoutParams(vrLp);
					btnIsrail_79.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(79);
						}
					}); 
					

					ImageButton btnIsrail_80 =(ImageButton)findViewById(R.id.btnIsrail_80);		        	        			        	
		        	fnkSetAirBaseCoor(80);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_80.setLayoutParams(vrLp);
					btnIsrail_80.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(80);
						}
					}); 
					
					ImageButton btnIsrail_81 =(ImageButton)findViewById(R.id.btnIsrail_81);		        	        			        	
		        	fnkSetAirBaseCoor(81);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_81.setLayoutParams(vrLp);
					btnIsrail_81.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(81);
						}
					}); 
					
					ImageButton btnIsrail_82 =(ImageButton)findViewById(R.id.btnIsrail_82);		        	        			        	
		        	fnkSetAirBaseCoor(82);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_82.setLayoutParams(vrLp);
					btnIsrail_82.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(82);
						}
					}); 
					
					ImageButton btnIsrail_83 =(ImageButton)findViewById(R.id.btnIsrail_83);		        	        			        	
		        	fnkSetAirBaseCoor(83);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_83.setLayoutParams(vrLp);
					btnIsrail_83.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(83);
						}
					}); 
					
					ImageButton btnIsrail_84 =(ImageButton)findViewById(R.id.btnIsrail_84);		        	        			        	
		        	fnkSetAirBaseCoor(84);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_84.setLayoutParams(vrLp);
					btnIsrail_84.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(84);
						}
					}); 
					
					ImageButton btnIsrail_85 =(ImageButton)findViewById(R.id.btnIsrail_85);		        	        			        	
		        	fnkSetAirBaseCoor(85);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_85.setLayoutParams(vrLp);
					btnIsrail_85.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(85);
						}
					}); 

					ImageButton btnIsrail_86 =(ImageButton)findViewById(R.id.btnIsrail_86);		        	        			        	
		        	fnkSetAirBaseCoor(86);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_86.setLayoutParams(vrLp);
					btnIsrail_86.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(86);
						}
					}); 
					
					ImageButton btnIsrail_87 =(ImageButton)findViewById(R.id.btnIsrail_87);		        	        			        	
		        	fnkSetAirBaseCoor(87);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_87.setLayoutParams(vrLp);
					btnIsrail_87.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(87);
						}
					}); 
					
					ImageButton btnIsrail_88 =(ImageButton)findViewById(R.id.btnIsrail_88);		        	        			        	
		        	fnkSetAirBaseCoor(88);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_88.setLayoutParams(vrLp);
					btnIsrail_88.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(88);
						}
					}); 
					
					ImageButton btnIsrail_89 =(ImageButton)findViewById(R.id.btnIsrail_89);		        	        			        	
		        	fnkSetAirBaseCoor(89);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_89.setLayoutParams(vrLp);
					btnIsrail_89.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(89);
						}
					}); 
					
					
					ImageButton btnIsrail_90 =(ImageButton)findViewById(R.id.btnIsrail_90);		        	        			        	
		        	fnkSetAirBaseCoor(90);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_90.setLayoutParams(vrLp);
					btnIsrail_90.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(90);
						}
					}); 
					
					ImageButton btnIsrail_91 =(ImageButton)findViewById(R.id.btnIsrail_91);		        	        			        	
		        	fnkSetAirBaseCoor(91);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_91.setLayoutParams(vrLp);
					btnIsrail_91.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(91);
						}
					}); 
					
					ImageButton btnIsrail_92 =(ImageButton)findViewById(R.id.btnIsrail_92);		        	        			        	
		        	fnkSetAirBaseCoor(92);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_92.setLayoutParams(vrLp);
					btnIsrail_92.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(92);
						}
					}); 
					
					ImageButton btnIsrail_93 =(ImageButton)findViewById(R.id.btnIsrail_93);		        	        			        	
		        	fnkSetAirBaseCoor(93);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_93.setLayoutParams(vrLp);
					btnIsrail_93.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(93);
						}
					}); 
					
					ImageButton btnIsrail_94 =(ImageButton)findViewById(R.id.btnIsrail_94);		        	        			        	
		        	fnkSetAirBaseCoor(94);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_94.setLayoutParams(vrLp);
					btnIsrail_94.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(94);
						}
					}); 
					
					ImageButton btnIsrail_95 =(ImageButton)findViewById(R.id.btnIsrail_95);		        	        			        	
		        	fnkSetAirBaseCoor(95);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_95.setLayoutParams(vrLp);
					btnIsrail_95.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(95);
						}
					}); 

					ImageButton btnIsrail_96 =(ImageButton)findViewById(R.id.btnIsrail_96);		        	        			        	
		        	fnkSetAirBaseCoor(96);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_96.setLayoutParams(vrLp);
					btnIsrail_96.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(96);
						}
					}); 
					
					ImageButton btnIsrail_97 =(ImageButton)findViewById(R.id.btnIsrail_97);		        	        			        	
		        	fnkSetAirBaseCoor(97);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_97.setLayoutParams(vrLp);
					btnIsrail_97.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(97);
						}
					}); 
					
					ImageButton btnIsrail_98 =(ImageButton)findViewById(R.id.btnIsrail_98);		        	        			        	
		        	fnkSetAirBaseCoor(98);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_98.setLayoutParams(vrLp);
					btnIsrail_98.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(98);
						}
					}); 
					
					ImageButton btnIsrail_99 =(ImageButton)findViewById(R.id.btnIsrail_99);		        	        			        	
		        	fnkSetAirBaseCoor(99);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnIsrail_99.setLayoutParams(vrLp);
					btnIsrail_99.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(99);
						}
					}); 
						
					
		        } // Israil Sonu
		        
		        
		      //Aegean AirBases
		        if (vrHangiHarita == "Aegean")
		        {
		        	imgF16 = (ImageView)findViewById(R.id.imgF16Aegean);
		        	 	pnlDis = (AbsoluteLayout)findViewById(R.id.pnlAegeanDis);
		        	 	pnlDis.setVisibility(View.VISIBLE);
		        	lblTwr =(TextView)findViewById(R.id.lblTwr);
		    		lblTcnCh =(TextView)findViewById(R.id.lblTcnCh);
		    		lblTcnRng =(TextView)findViewById(R.id.lblTcnRng);
		    		lblILS =(TextView)findViewById(R.id.lblILS);
		    		lblRWY =(TextView)findViewById(R.id.lblRWY);
		    		lblElev =(TextView)findViewById(R.id.lblElev);
		    		lblCoor =(TextView)findViewById(R.id.lblCoor);
		        	
		    		lblAirBase =(TextView)findViewById(R.id.lblAirBase);
		    		
		    		ImageView imgHarita1;
		    		imgHarita1 = (ImageView)findViewById(R.id.imgAegean1);		    		
		    		imgHarita1.setBackgroundResource(R.drawable.map_aegean1);
		    		
		    		ImageView imgHarita2;
		    		imgHarita2 = (ImageView)findViewById(R.id.imgAegean2);		    		
		    		imgHarita2.setBackgroundResource(R.drawable.map_aegean2);
		    		
		    		ImageView imgHarita3;
		    		imgHarita3 = (ImageView)findViewById(R.id.imgAegean3);		    		
		    		imgHarita3.setBackgroundResource(R.drawable.map_aegean3);
		    		
		    		ImageView imgHarita4;
		    		imgHarita4 = (ImageView)findViewById(R.id.imgAegean4);		    		
		    		imgHarita4.setBackgroundResource(R.drawable.map_aegean4);
		    		
		    		ImageView imgHarita5;
		    		imgHarita5 = (ImageView)findViewById(R.id.imgAegean5);		    		
		    		imgHarita5.setBackgroundResource(R.drawable.map_aegean5);
		    		
		    		ImageView imgHarita6;
		    		imgHarita6 = (ImageView)findViewById(R.id.imgAegean6);		    		
		    		imgHarita6.setBackgroundResource(R.drawable.map_aegean6);
		    		
		    		ImageView imgHarita7;
		    		imgHarita7 = (ImageView)findViewById(R.id.imgAegean7);		    		
		    		imgHarita7.setBackgroundResource(R.drawable.map_aegean7);
		    		
		    		ImageView imgHarita8;
		    		imgHarita8 = (ImageView)findViewById(R.id.imgAegean8);		    		
		    		imgHarita8.setBackgroundResource(R.drawable.map_aegean8);
		    		
		    		ImageView imgHarita9;
		    		imgHarita9 = (ImageView)findViewById(R.id.imgAegean9);		    		
		    		imgHarita9.setBackgroundResource(R.drawable.map_aegean9);
		        	
		        	
		        	fnkAirBases("aegean.xml");
		        	String vrId;
		        	
		        	AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(30,30,1 * 2 ,1 * 2);
		        	
		        	
		        	ImageButton btnAegean_1 =(ImageButton)findViewById(R.id.btnAegean_1);		        	        			        	
		        	fnkSetAirBaseCoor(1);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_1.setLayoutParams(vrLp);
					btnAegean_1.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(1);
						}
					}); 
					
					ImageButton btnAegean_2 =(ImageButton)findViewById(R.id.btnAegean_2);		        	        			        	
		        	fnkSetAirBaseCoor(2);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_2.setLayoutParams(vrLp);
					btnAegean_2.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(2);
						}
					}); 
					
					ImageButton btnAegean_3 =(ImageButton)findViewById(R.id.btnAegean_3);		        	        			        	
		        	fnkSetAirBaseCoor(3);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_3.setLayoutParams(vrLp);
					btnAegean_3.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(3);
						}
					}); 
					
					ImageButton btnAegean_4 =(ImageButton)findViewById(R.id.btnAegean_4);		        	        			        	
		        	fnkSetAirBaseCoor(4);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_4.setLayoutParams(vrLp);
					btnAegean_4.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(4);
						}
					}); 
					
					ImageButton btnAegean_5 =(ImageButton)findViewById(R.id.btnAegean_5);		        	        			        	
		        	fnkSetAirBaseCoor(5);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_5.setLayoutParams(vrLp);
					btnAegean_5.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(5);
						}
					}); 
					
					ImageButton btnAegean_6 =(ImageButton)findViewById(R.id.btnAegean_6);		        	        			        	
		        	fnkSetAirBaseCoor(6);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_6.setLayoutParams(vrLp);
					btnAegean_6.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(6);
						}
					}); 
					
					ImageButton btnAegean_7 =(ImageButton)findViewById(R.id.btnAegean_7);		        	        			        	
		        	fnkSetAirBaseCoor(7);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_7.setLayoutParams(vrLp);
					btnAegean_7.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(7);
						}
					}); 
					
					ImageButton btnAegean_8 =(ImageButton)findViewById(R.id.btnAegean_8);		        	        			        	
		        	fnkSetAirBaseCoor(8);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_8.setLayoutParams(vrLp);
					btnAegean_8.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(8);
						}
					}); 
					
					ImageButton btnAegean_9 =(ImageButton)findViewById(R.id.btnAegean_9);		        	        			        	
		        	fnkSetAirBaseCoor(9);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_9.setLayoutParams(vrLp);
					btnAegean_9.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(9);
						}
					}); 
					
					ImageButton btnAegean_10 =(ImageButton)findViewById(R.id.btnAegean_10);		        	        			        	
		        	fnkSetAirBaseCoor(10);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_10.setLayoutParams(vrLp);
					btnAegean_10.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(10);
						}
					}); 
					
					ImageButton btnAegean_11 =(ImageButton)findViewById(R.id.btnAegean_11);		        	        			        	
		        	fnkSetAirBaseCoor(11);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_11.setLayoutParams(vrLp);
					btnAegean_11.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(11);
						}
					}); 
					
					ImageButton btnAegean_12 =(ImageButton)findViewById(R.id.btnAegean_12);		        	        			        	
		        	fnkSetAirBaseCoor(12);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_12.setLayoutParams(vrLp);
					btnAegean_12.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(12);
						}
					}); 
					
					ImageButton btnAegean_13 =(ImageButton)findViewById(R.id.btnAegean_13);		        	        			        	
		        	fnkSetAirBaseCoor(13);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_13.setLayoutParams(vrLp);
					btnAegean_13.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(13);
						}
					}); 
					
					ImageButton btnAegean_14 =(ImageButton)findViewById(R.id.btnAegean_14);		        	        			        	
		        	fnkSetAirBaseCoor(14);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_14.setLayoutParams(vrLp);
					btnAegean_14.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(14);
						}
					}); 
					
					ImageButton btnAegean_15 =(ImageButton)findViewById(R.id.btnAegean_15);		        	        			        	
		        	fnkSetAirBaseCoor(15);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_15.setLayoutParams(vrLp);
					btnAegean_15.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(15);
						}
					}); 

					ImageButton btnAegean_16 =(ImageButton)findViewById(R.id.btnAegean_16);		        	        			        	
		        	fnkSetAirBaseCoor(16);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_16.setLayoutParams(vrLp);
					btnAegean_16.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(16);
						}
					}); 
					
					ImageButton btnAegean_17 =(ImageButton)findViewById(R.id.btnAegean_17);		        	        			        	
		        	fnkSetAirBaseCoor(17);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_17.setLayoutParams(vrLp);
					btnAegean_17.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(17);
						}
					}); 
					
					ImageButton btnAegean_18 =(ImageButton)findViewById(R.id.btnAegean_18);		        	        			        	
		        	fnkSetAirBaseCoor(18);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_18.setLayoutParams(vrLp);
					btnAegean_18.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(18);
						}
					}); 
					
					ImageButton btnAegean_19 =(ImageButton)findViewById(R.id.btnAegean_19);		        	        			        	
		        	fnkSetAirBaseCoor(19);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_19.setLayoutParams(vrLp);
					btnAegean_19.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(19);
						}
					}); 
					
					
					
					ImageButton btnAegean_20 =(ImageButton)findViewById(R.id.btnAegean_20);		        	        			        	
		        	fnkSetAirBaseCoor(20);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_20.setLayoutParams(vrLp);
					btnAegean_20.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(20);
						}
					}); 
					
					ImageButton btnAegean_21 =(ImageButton)findViewById(R.id.btnAegean_21);		        	        			        	
		        	fnkSetAirBaseCoor(21);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_21.setLayoutParams(vrLp);
					btnAegean_21.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(21);
						}
					}); 
					
					ImageButton btnAegean_22 =(ImageButton)findViewById(R.id.btnAegean_22);		        	        			        	
		        	fnkSetAirBaseCoor(22);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_22.setLayoutParams(vrLp);
					btnAegean_22.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(22);
						}
					}); 
					
					ImageButton btnAegean_23 =(ImageButton)findViewById(R.id.btnAegean_23);		        	        			        	
		        	fnkSetAirBaseCoor(23);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_23.setLayoutParams(vrLp);
					btnAegean_23.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(23);
						}
					}); 
					
					ImageButton btnAegean_24 =(ImageButton)findViewById(R.id.btnAegean_24);		        	        			        	
		        	fnkSetAirBaseCoor(24);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_24.setLayoutParams(vrLp);
					btnAegean_24.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(24);
						}
					}); 
					
					ImageButton btnAegean_25 =(ImageButton)findViewById(R.id.btnAegean_25);		        	        			        	
		        	fnkSetAirBaseCoor(25);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_25.setLayoutParams(vrLp);
					btnAegean_25.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(25);
						}
					}); 

					ImageButton btnAegean_26 =(ImageButton)findViewById(R.id.btnAegean_26);		        	        			        	
		        	fnkSetAirBaseCoor(26);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_26.setLayoutParams(vrLp);
					btnAegean_26.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(26);
						}
					}); 
					
					ImageButton btnAegean_27 =(ImageButton)findViewById(R.id.btnAegean_27);		        	        			        	
		        	fnkSetAirBaseCoor(27);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_27.setLayoutParams(vrLp);
					btnAegean_27.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(27);
						}
					}); 
					
					ImageButton btnAegean_28 =(ImageButton)findViewById(R.id.btnAegean_28);		        	        			        	
		        	fnkSetAirBaseCoor(28);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_28.setLayoutParams(vrLp);
					btnAegean_28.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(28);
						}
					}); 
					
					ImageButton btnAegean_29 =(ImageButton)findViewById(R.id.btnAegean_29);		        	        			        	
		        	fnkSetAirBaseCoor(29);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_29.setLayoutParams(vrLp);
					btnAegean_29.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(29);
						}
					}); 
					
					
					ImageButton btnAegean_30 =(ImageButton)findViewById(R.id.btnAegean_30);		        	        			        	
		        	fnkSetAirBaseCoor(30);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_30.setLayoutParams(vrLp);
					btnAegean_30.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(30);
						}
					}); 
					
					ImageButton btnAegean_31 =(ImageButton)findViewById(R.id.btnAegean_31);		        	        			        	
		        	fnkSetAirBaseCoor(31);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_31.setLayoutParams(vrLp);
					btnAegean_31.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(31);
						}
					}); 
					
					ImageButton btnAegean_32 =(ImageButton)findViewById(R.id.btnAegean_32);		        	        			        	
		        	fnkSetAirBaseCoor(32);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_32.setLayoutParams(vrLp);
					btnAegean_32.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(32);
						}
					}); 
					
					ImageButton btnAegean_33 =(ImageButton)findViewById(R.id.btnAegean_33);		        	        			        	
		        	fnkSetAirBaseCoor(33);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_33.setLayoutParams(vrLp);
					btnAegean_33.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(33);
						}
					}); 
					
					ImageButton btnAegean_34 =(ImageButton)findViewById(R.id.btnAegean_34);		        	        			        	
		        	fnkSetAirBaseCoor(34);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_34.setLayoutParams(vrLp);
					btnAegean_34.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(34);
						}
					}); 
					
					ImageButton btnAegean_35 =(ImageButton)findViewById(R.id.btnAegean_35);		        	        			        	
		        	fnkSetAirBaseCoor(35);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_35.setLayoutParams(vrLp);
					btnAegean_35.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(35);
						}
					}); 

					ImageButton btnAegean_36 =(ImageButton)findViewById(R.id.btnAegean_36);		        	        			        	
		        	fnkSetAirBaseCoor(36);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_36.setLayoutParams(vrLp);
					btnAegean_36.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(36);
						}
					}); 
					
					ImageButton btnAegean_37 =(ImageButton)findViewById(R.id.btnAegean_37);		        	        			        	
		        	fnkSetAirBaseCoor(37);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_37.setLayoutParams(vrLp);
					btnAegean_37.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(37);
						}
					}); 
					
					ImageButton btnAegean_38 =(ImageButton)findViewById(R.id.btnAegean_38);		        	        			        	
		        	fnkSetAirBaseCoor(38);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_38.setLayoutParams(vrLp);
					btnAegean_38.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(38);
						}
					}); 
					
					ImageButton btnAegean_39 =(ImageButton)findViewById(R.id.btnAegean_39);		        	        			        	
		        	fnkSetAirBaseCoor(39);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_39.setLayoutParams(vrLp);
					btnAegean_39.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(39);
						}
					}); 
					
					
					ImageButton btnAegean_40 =(ImageButton)findViewById(R.id.btnAegean_40);		        	        			        	
		        	fnkSetAirBaseCoor(40);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_40.setLayoutParams(vrLp);
					btnAegean_40.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(40);
						}
					}); 
					
					ImageButton btnAegean_41 =(ImageButton)findViewById(R.id.btnAegean_41);		        	        			        	
		        	fnkSetAirBaseCoor(41);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_41.setLayoutParams(vrLp);
					btnAegean_41.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(41);
						}
					}); 
					
					ImageButton btnAegean_42 =(ImageButton)findViewById(R.id.btnAegean_42);		        	        			        	
		        	fnkSetAirBaseCoor(42);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_42.setLayoutParams(vrLp);
					btnAegean_42.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(42);
						}
					}); 
					
					ImageButton btnAegean_43 =(ImageButton)findViewById(R.id.btnAegean_43);		        	        			        	
		        	fnkSetAirBaseCoor(43);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_43.setLayoutParams(vrLp);
					btnAegean_43.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(43);
						}
					}); 
					
					ImageButton btnAegean_44 =(ImageButton)findViewById(R.id.btnAegean_44);		        	        			        	
		        	fnkSetAirBaseCoor(44);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_44.setLayoutParams(vrLp);
					btnAegean_44.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(44);
						}
					}); 
					
					ImageButton btnAegean_45 =(ImageButton)findViewById(R.id.btnAegean_45);		        	        			        	
		        	fnkSetAirBaseCoor(45);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_45.setLayoutParams(vrLp);
					btnAegean_45.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(45);
						}
					}); 

					ImageButton btnAegean_46 =(ImageButton)findViewById(R.id.btnAegean_46);		        	        			        	
		        	fnkSetAirBaseCoor(46);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_46.setLayoutParams(vrLp);
					btnAegean_46.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(46);
						}
					}); 
					
					ImageButton btnAegean_47 =(ImageButton)findViewById(R.id.btnAegean_47);		        	        			        	
		        	fnkSetAirBaseCoor(47);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_47.setLayoutParams(vrLp);
					btnAegean_47.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(47);
						}
					}); 
					
					ImageButton btnAegean_48 =(ImageButton)findViewById(R.id.btnAegean_48);		        	        			        	
		        	fnkSetAirBaseCoor(48);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_48.setLayoutParams(vrLp);
					btnAegean_48.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(48);
						}
					}); 
					
					ImageButton btnAegean_49 =(ImageButton)findViewById(R.id.btnAegean_49);		        	        			        	
		        	fnkSetAirBaseCoor(49);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_49.setLayoutParams(vrLp);
					btnAegean_49.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(49);
						}
					}); 
					
					
					ImageButton btnAegean_50 =(ImageButton)findViewById(R.id.btnAegean_50);		        	        			        	
		        	fnkSetAirBaseCoor(50);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_50.setLayoutParams(vrLp);
					btnAegean_50.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(50);
						}
					}); 
					
					ImageButton btnAegean_51 =(ImageButton)findViewById(R.id.btnAegean_51);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_51.setLayoutParams(vrLp);
					btnAegean_51.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(51);
						}
					}); 
					
					ImageButton btnAegean_52 =(ImageButton)findViewById(R.id.btnAegean_52);		        	        			        	
		        	fnkSetAirBaseCoor(52);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_52.setLayoutParams(vrLp);
					btnAegean_52.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(52);
						}
					}); 
					
					ImageButton btnAegean_53 =(ImageButton)findViewById(R.id.btnAegean_53);		        	        			        	
		        	fnkSetAirBaseCoor(53);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_53.setLayoutParams(vrLp);
					btnAegean_53.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(53);
						}
					}); 
					
					ImageButton btnAegean_54 =(ImageButton)findViewById(R.id.btnAegean_54);		        	        			        	
		        	fnkSetAirBaseCoor(54);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_54.setLayoutParams(vrLp);
					btnAegean_54.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(54);
						}
					}); 
					
					ImageButton btnAegean_55 =(ImageButton)findViewById(R.id.btnAegean_55);		        	        			        	
		        	fnkSetAirBaseCoor(55);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_55.setLayoutParams(vrLp);
					btnAegean_55.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(55);
						}
					}); 

					ImageButton btnAegean_56 =(ImageButton)findViewById(R.id.btnAegean_56);		        	        			        	
		        	fnkSetAirBaseCoor(56);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_56.setLayoutParams(vrLp);
					btnAegean_56.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(56);
						}
					}); 
					
					ImageButton btnAegean_57 =(ImageButton)findViewById(R.id.btnAegean_57);		        	        			        	
		        	fnkSetAirBaseCoor(57);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_57.setLayoutParams(vrLp);
					btnAegean_57.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(57);
						}
					}); 
					
					ImageButton btnAegean_58 =(ImageButton)findViewById(R.id.btnAegean_58);		        	        			        	
		        	fnkSetAirBaseCoor(58);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_58.setLayoutParams(vrLp);
					btnAegean_58.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(58);
						}
					}); 
					
					ImageButton btnAegean_59 =(ImageButton)findViewById(R.id.btnAegean_59);		        	        			        	
		        	fnkSetAirBaseCoor(59);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_59.setLayoutParams(vrLp);
					btnAegean_59.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(59);
						}
					}); 
					
					
					ImageButton btnAegean_60 =(ImageButton)findViewById(R.id.btnAegean_60);		        	        			        	
		        	fnkSetAirBaseCoor(60);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_60.setLayoutParams(vrLp);
					btnAegean_60.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(60);
						}
					}); 
					
					ImageButton btnAegean_61 =(ImageButton)findViewById(R.id.btnAegean_61);		        	        			        	
		        	fnkSetAirBaseCoor(61);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_61.setLayoutParams(vrLp);
					btnAegean_61.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(61);
						}
					}); 
					
					ImageButton btnAegean_62 =(ImageButton)findViewById(R.id.btnAegean_62);		        	        			        	
		        	fnkSetAirBaseCoor(62);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_62.setLayoutParams(vrLp);
					btnAegean_62.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(62);
						}
					}); 
					
					ImageButton btnAegean_63 =(ImageButton)findViewById(R.id.btnAegean_63);		        	        			        	
		        	fnkSetAirBaseCoor(63);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_63.setLayoutParams(vrLp);
					btnAegean_63.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(63);
						}
					}); 
					
					ImageButton btnAegean_64 =(ImageButton)findViewById(R.id.btnAegean_64);		        	        			        	
		        	fnkSetAirBaseCoor(64);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_64.setLayoutParams(vrLp);
					btnAegean_64.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(64);
						}
					}); 
					
					ImageButton btnAegean_65 =(ImageButton)findViewById(R.id.btnAegean_65);		        	        			        	
		        	fnkSetAirBaseCoor(65);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_65.setLayoutParams(vrLp);
					btnAegean_65.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(65);
						}
					}); 

					ImageButton btnAegean_66 =(ImageButton)findViewById(R.id.btnAegean_66);		        	        			        	
		        	fnkSetAirBaseCoor(66);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_66.setLayoutParams(vrLp);
					btnAegean_66.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(66);
						}
					}); 
					
					ImageButton btnAegean_67 =(ImageButton)findViewById(R.id.btnAegean_67);		        	        			        	
		        	fnkSetAirBaseCoor(67);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_67.setLayoutParams(vrLp);
					btnAegean_67.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(67);
						}
					}); 
					
					ImageButton btnAegean_68 =(ImageButton)findViewById(R.id.btnAegean_68);		        	        			        	
		        	fnkSetAirBaseCoor(68);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_68.setLayoutParams(vrLp);
					btnAegean_68.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(68);
						}
					}); 
					
					ImageButton btnAegean_69 =(ImageButton)findViewById(R.id.btnAegean_69);		        	        			        	
		        	fnkSetAirBaseCoor(69);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_69.setLayoutParams(vrLp);
					btnAegean_69.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(69);
						}
					}); 
					
					ImageButton btnAegean_70 =(ImageButton)findViewById(R.id.btnAegean_70);		        	        			        	
		        	fnkSetAirBaseCoor(70);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_70.setLayoutParams(vrLp);
					btnAegean_70.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(70);
						}
					}); 
					
					ImageButton btnAegean_71 =(ImageButton)findViewById(R.id.btnAegean_71);		        	        			        	
		        	fnkSetAirBaseCoor(71);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_71.setLayoutParams(vrLp);
					btnAegean_71.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(71);
						}
					}); 
					
					ImageButton btnAegean_72 =(ImageButton)findViewById(R.id.btnAegean_72);		        	        			        	
		        	fnkSetAirBaseCoor(72);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_72.setLayoutParams(vrLp);
					btnAegean_72.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(72);
						}
					}); 
					
					ImageButton btnAegean_73 =(ImageButton)findViewById(R.id.btnAegean_73);		        	        			        	
		        	fnkSetAirBaseCoor(73);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_73.setLayoutParams(vrLp);
					btnAegean_73.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(73);
						}
					}); 
					
					ImageButton btnAegean_74 =(ImageButton)findViewById(R.id.btnAegean_74);		        	        			        	
		        	fnkSetAirBaseCoor(74);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_74.setLayoutParams(vrLp);
					btnAegean_74.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(74);
						}
					}); 
					
					ImageButton btnAegean_75 =(ImageButton)findViewById(R.id.btnAegean_75);		        	        			        	
		        	fnkSetAirBaseCoor(75);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_75.setLayoutParams(vrLp);
					btnAegean_75.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(75);
						}
					}); 

					ImageButton btnAegean_76 =(ImageButton)findViewById(R.id.btnAegean_76);		        	        			        	
		        	fnkSetAirBaseCoor(76);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_76.setLayoutParams(vrLp);
					btnAegean_76.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(76);
						}
					}); 
					
					ImageButton btnAegean_77 =(ImageButton)findViewById(R.id.btnAegean_77);		        	        			        	
		        	fnkSetAirBaseCoor(77);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_77.setLayoutParams(vrLp);
					btnAegean_77.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(77);
						}
					}); 
					
					ImageButton btnAegean_78 =(ImageButton)findViewById(R.id.btnAegean_78);		        	        			        	
		        	fnkSetAirBaseCoor(78);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_78.setLayoutParams(vrLp);
					btnAegean_78.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(78);
						}
					}); 
					
					ImageButton btnAegean_79 =(ImageButton)findViewById(R.id.btnAegean_79);		        	        			        	
		        	fnkSetAirBaseCoor(79);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_79.setLayoutParams(vrLp);
					btnAegean_79.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(79);
						}
					}); 
					
					ImageButton btnAegean_80 =(ImageButton)findViewById(R.id.btnAegean_80);		        	        			        	
		        	fnkSetAirBaseCoor(80);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_80.setLayoutParams(vrLp);
					btnAegean_80.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(80);
						}
					}); 
					
					ImageButton btnAegean_81 =(ImageButton)findViewById(R.id.btnAegean_81);		        	        			        	
		        	fnkSetAirBaseCoor(81);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_81.setLayoutParams(vrLp);
					btnAegean_81.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(81);
						}
					}); 
					
					ImageButton btnAegean_82 =(ImageButton)findViewById(R.id.btnAegean_82);		        	        			        	
		        	fnkSetAirBaseCoor(82);	
		        	
		        	//lblILS.setText( vrXAirBaseCoord);
		        	vrLp = new AbsoluteLayout.LayoutParams(30,30,vrXAirBaseCoord  ,vrYAirBaseCoord );
					btnAegean_82.setLayoutParams(vrLp);
					btnAegean_82.setOnClickListener(new View.OnClickListener() {				
						public void onClick(View v) {
							// TODO Auto-generated method stub
							fnkGetAirBaseInfo(82);
						}
					}); 
					
		        } // Aegean Sonu

        
        pnlHaritalar.setOnTouchListener(this);
        fnkSetHead(MainActivity.vrCurrentHeading);
        
        

//		 AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(4096,4096, -1 * (int) Math.ceil(imgF16.getX()) + -600 ,  -1 * (int) Math.ceil(imgF16.getY()) + -600);
//		 pnlKoreaDis.setLayoutParams(vrLp);
		  
		 
        
	} // OnCreate Sonu
	
	
	public void fnkGetScreen()
	{
		DisplayMetrics vrResolution = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(vrResolution);
		
		vrHeight = vrResolution.heightPixels;
		vrWidth = vrResolution.widthPixels;
		
	} // fnkGetScreen Sonu
	
	
	@Override
	 
	 public boolean onTouch(View view, MotionEvent motionevent) {
	 
	  int action = motionevent.getAction() & MotionEvent.ACTION_MASK;
	 
	  
	  if (view.getId()==R.id.pnlHaritalar )
		{
			
			switch(action) 
			{
			     case MotionEvent.ACTION_DOWN : 
			     		{
			 
				    //Log.d("CV","Pointer Down ");
					   vrXLocFark = motionevent.getX() - pnlDis.getX();
					   vrYLocFark = motionevent.getY() - pnlDis.getY();
				    break;
			 
			     		}
			 
				   case MotionEvent.ACTION_MOVE : 
				   		{
				 
					    //Log.d("CV","Pointer Move ");
						
							
							vrXloc = motionevent.getX() - vrXLocFark;
							vrYloc = motionevent.getY() - vrYLocFark;
							
							if ((int) Math.ceil(vrXloc) > 0)
							{
								vrXloc = 0;
							}	
							
							if ((int) Math.ceil(vrYloc) > 0)
							{
								vrYloc = 0;
							}
							
							
//							lblTwr.setText(Float.toString( vrXloc));
//							lblCoor.setText(Float.toString( vrYloc));
							
							AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(4096,4096, (int) Math.ceil(vrXloc) ,(int) Math.ceil(vrYloc));
							pnlDis.setLayoutParams(vrLp);
							
					    break;
					 
					   }
				   case MotionEvent.ACTION_UP : 
				   	   {
				 
					    //Log.d("CV", "Pointer up");
					    break;
					 
					   }  		 
		   }
	
			return true;
		}
		else
		{
			return false;
		}
	  
	  
	 }
	

	public void fnkGetAirBaseInfo(int prID)
	{
		
		
		lblTwr.setText("");
	    lblCoor.setText("");
	    lblElev.setText("");
	    lblRWY.setText("");
	    lblRWY.setText("");
	    lblTcnCh.setText("");
	    lblTcnRng.setText("");
	    lblAirBase.setText("");
	    
		
		if (vrAirBases.isEmpty()==false )
		{
			Item itemRes;
		    itemRes = vrAirBases.get(prID-1);
		    
		    /*
		    String string = itemRes.vrGPSCoor;
		    String[] parts = string.split("-");
		    String part1 = parts[0]; // 004
		    String part2 = parts[1]; // 034556
		    */
		    
		    lblTwr.setText(itemRes.vrTwr);
		    lblCoor.setText(fnkEnterEkle(itemRes.vrGPSCoor));		    
		    lblElev.setText(itemRes.vrElv);
		    lblILS.setText(fnkEnterEkle(itemRes.vrIls));
		    lblRWY.setText(fnkEnterEkle(itemRes.vrRwy));
		    lblTcnCh.setText(fnkEnterEkle(itemRes.vrTcnCh));
		    lblTcnRng.setText(itemRes.vrTcnRng);		    
		    lblAirBase.setText(itemRes.vrName);
		    //lblAirBase.setText(itemRes.vrXCoor + "-" + itemRes.vrXCoor);
		}
		
		
		pnlAirBaseInfo.setVisibility(View.VISIBLE);
		
	}


	public String fnkEnterEkle(String prString)
	{
		String vrResult = prString;
		
		String string = prString;
	        
	    String part1 = ""; 
	    String part2 = "";
	    String part3 = "";
	    String part4 = "";
	    
	    try
	    {
	    	String[] parts = string.split("-");
	    	
	    	part1 = parts[0]; // 
		    part2 = parts[1]; // 
		    part3 = parts[2]; //
		    part4 = parts[3]; //
		    
		    vrResult =  part1 +	"\n" + part2 +	"\n" + part3 +	"\n" + part4;
	    } 
	    catch (Exception e) {
	    	 try
		 	    {
		 	    	String[] parts = string.split("-");
		 	    	
		 	    	part1 = parts[0]; // 
		 		    part2 = parts[1]; // 
		 		    part3 = parts[2]; //
		 		    
		 		    vrResult =  part1 +	"\n" + part2 +	"\n" + part3 ;
		 	    } 
		 	    catch (Exception e1) {
		 	    	try
			 	    {
			 	    	String[] parts = string.split("-");
			 	    	
			 	    	part1 = parts[0]; // 
			 		    part2 = parts[1]; // 
			 		    vrResult =  part1 +	"\n" + part2; 
			 	    } 
			 	    catch (Exception e2) {
			 	    	//vrResult = "Sýkýntý Var";
			 		}

		 		}

		}
	    
	    
	    /* 
	    System.out.println("Kömürcü - 1" + part1);
	    System.out.println("Kömürcü - 2" + part2);
	    System.out.println("Kömürcü - 3" + part3);
	    System.out.println("Kömürcü - 4" + part4);
	    */

		return vrResult;
	}
	
	
	class Item
	{
	   String vrPK;
	   String vrName;
	   int vrXCoor;
	   int vrYCoor;
	   String vrTwr;
	   String vrTcnCh;
	   String vrTcnRng;
	   String vrIls;
	   String vrRwy;
	   String vrElv;
	   String vrGPSCoor;
	}



	public void fnkSetAirBaseCoor(int prID)
	{
		vrXAirBaseCoord = 1;
		vrYAirBaseCoord = 1;
		
		
		if (vrAirBases.isEmpty()==false )
		{
			Item itemRes;
		    itemRes = vrAirBases.get(prID-1);
		    vrXAirBaseCoord = itemRes.vrXCoor * 2 ; 
		    vrYAirBaseCoord = itemRes.vrYCoor  * 2;
		}
		
	}




	public void fnkAirBases(String prAirbase)
	 {
		//xml için
	        
	        InputStream inputStream;
	        
	       
	       try
	               {
	                    
	    	   
	    	   AssetManager assetManager = getAssets();
	    	   inputStream = assetManager.open(prAirbase);
	    	   
	    			         
		       Item item=new Item();
		       
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
	                                    if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("PK"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrPK=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("Name"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrName=xpp.getText().toString();	                                          
	                                     } 
	                                    
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("XCoor"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrXCoor=Integer.parseInt(xpp.getText().toString());
	                                     }    	                                    
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("YCoor"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrYCoor=Integer.parseInt(xpp.getText().toString());
	                                     }    
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("Twr"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrTwr=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("TcnCh"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrTcnCh=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("TcnRng"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrTcnRng=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("Ils"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrIls=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("Rwy"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrRwy=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("Elv"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrElv=xpp.getText().toString();
	                                     }
	                                    else if(xpp.getName()!=null&& xpp.getName().equalsIgnoreCase("GPSCoor"))
	                                     {
	                                          eventType = xpp.next();
	                                          item.vrGPSCoor=xpp.getText().toString();
	                                     }
	                                    
	                               } 
	                            catch (Exception e) 
	                               {
	                                   //e.printStackTrace();
	                               }
	                        }
	                        else if(eventType == XmlPullParser.END_TAG) 
	                        {
	                        	
	                        	
	                        	
	                        	if(xpp.getName()!=null && xpp.getName().equalsIgnoreCase("AirportData"))
	                             {
	                                  
	                        		eventType = xpp.next();  
	                        			vrAirBases.add(item);
	                        			/*
	                        			MainActivity.fnkAddBalkanAirBase(item.vrPK,
	                        											 item.vrName,
	                        											 item.vrXCoor,
	                        											 item.vrYCoor,
	                        											 item.vrTwr,
	                        											 item.vrTcnCh,
	                        											 item.vrTcnRng,
	                        											 item.vrIls,
	                        											 item.vrRwy,
	                        											 item.vrElv,
	                        											 item.vrGPSCoor);
	                        			*/
	                                  //System.out.println("Kömürcü" + item.vrPK + " - " + item.vrName );
	                                  item=new Item();
	                                  
	                                  
	                             }
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
	               
	       	
	       	/*
	       	 //Ýstenilen Satýrý Almak Ýçin 
	       Item itemRes=new Item();
	       itemRes = items.get(1);
	       
	       System.out.println("Komurcu" +itemRes.vrName);
	 	    */    
	       
	        
	        //Xml için
		 
		 
	 } //fnkAirBases Sonu
	


	public static  void fnkSetLocationY(  String prCoorY  )
	{
	 try {
		 imgF16.setY(Integer.parseInt(prCoorY) - 30);
		 
		 if (vrYortala == false) 
		 {
			 AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(4096,4096, -1 * (int) Math.ceil(imgF16.getX())  ,  -1 * (int) Math.ceil(imgF16.getY()) );
			 pnlDis.setLayoutParams(vrLp);
			 
			 vrYortala = true;
		 }
		 
	    } catch (Exception e){
	      //throw new RuntimeException(e);	    	
	    }	
	 
	}
 
 
 public static  void fnkSetLocationX( String prCoorX )
	{
	 try {
			
		 imgF16.setX(Integer.parseInt(prCoorX)-13);		 
		 
		 if (vrXortala == false) 
		 {
			 AbsoluteLayout.LayoutParams vrLp = new AbsoluteLayout.LayoutParams(4096,4096, -1 * (int) Math.ceil(imgF16.getX()) + 200  ,  -1 * (int) Math.ceil(imgF16.getY())+200 );
			 pnlDis.setLayoutParams(vrLp);
			 
			 vrXortala = true;
		 }
		 
		} catch (Exception e){
		  //throw new RuntimeException(e);
			
		}	
	}

 
	public static  void fnkSetHead(String prHead )
	{
	 try {
			
		 
		 //Baþ Hesaplanacak
		 
		 imgF16.setRotation(Integer.parseInt(prHead));
		 			 
		 vrHead = Integer.parseInt(prHead);
		 			 
		    } catch (Exception e){
		      //throw new RuntimeException(e);
		    	
		    }	
	 
	}



}