package com.arifkomurculer.falconmfdmultitablet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.arifkomurculer.falconmfdmultitablet.MainActivity.FlightDataTypes;
import com.arifkomurculer.falconmfdmultitablet.MainActivity.MessageName;
import com.arifkomurculer.falconmfdmultitablet.MainActivity.ThSendKey;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class mis_sch extends Activity implements OnTouchListener {
	
	
	static float vrXloc; //Haritanýn X deðeri
    float vrXLocFark; //Týklanan Noktadan Uzaklýðý X
    static float vrYloc; //Haritanýn Y deðeri
    float vrYLocFark; //Týklanan Noktadan Uzaklýðý Y
    static AbsoluteLayout pnlDis;
    AbsoluteLayout pnlMisSch;
    TextView textView1;
    
    static ImageView myImgMisSch;
	
	private String vrBtn ;
	private String vrIP ;
	private String vrHangiTus;
	
//	private String vrServerPort;
//	private int vrSolPort;
//	private int vrMsgPort;
//	private int vrMisSchPort;
//	private int vrSagPort;
	
	private boolean vrScreenShoot;
	private int vrRefreshRate;
	private int vrResimIndex;
	
	TextView lblIndex;
	private CheckBox chkIndex;
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mis_sch);
        
  
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
        
        
        pnlMisSch = (AbsoluteLayout)findViewById(R.id.pnlMisSch);
        pnlDis = (AbsoluteLayout)findViewById(R.id.pnlResim);
        
        
        
        chkIndex= (CheckBox) findViewById(R.id.chkLatestImage);
		//Get app path
        
        PackageManager m = getPackageManager();
        String s = getPackageName();
        try {
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
        } catch (NameNotFoundException e) {
            Log.w("yourtag", "Error Package name not found ", e);
        }
		
		
       
		ImageButton btnClose =(ImageButton)findViewById(R.id.btnCloseSch);
		ImageButton btnGetMisSch =(ImageButton)findViewById(R.id.btnGetMisSch);

		ImageButton btnSol = (ImageButton)findViewById(R.id.imgBtnSol);
		ImageButton btnSag = (ImageButton)findViewById(R.id.imgBtnSag);

		lblIndex =(TextView)findViewById(R.id.lblIndex);
		textView1 =(TextView)findViewById(R.id.textView1);
		vrResimIndex = MainActivity.vrResimIndex;

		lblIndex.setText(String.valueOf(MainActivity.vrResimIndex) + "/5" );




		btnSol.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				if (vrResimIndex == 5) 
				{
					vrResimIndex =  1 ;
				}
				else 
				{
					vrResimIndex = vrResimIndex + 1 ;
				}
				
				chkIndex.setText("Get Latest " + vrResimIndex + ". Image");
				
				MainActivity.vrResimIndex = vrResimIndex;
				lblIndex.setText(String.valueOf(vrResimIndex) + "/5"  );
				
				
				
				try {
					
					if (vrResimIndex==1)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch1);
						}
					else if (vrResimIndex==2)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch2);
						}
					else if (vrResimIndex==3)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch3);
						}
					else if (vrResimIndex==4)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch4);
						}
					else if (vrResimIndex==5)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch5);
						}
					
				    } catch (Exception e){
				      //throw new RuntimeException(e);
				    	myImgMisSch.setImageBitmap(null);
				    }
				
				/*
				Toast.makeText(getBaseContext(),
		    			String.valueOf(vrResimIndex)	,
						Toast.LENGTH_SHORT).show();
				*/
						
			}
		});


		btnSag.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				if (vrResimIndex == 1) 
				{
					vrResimIndex =  5 ;
				}
				else 
				{
					vrResimIndex = vrResimIndex - 1 ;
				}
				
				
				chkIndex.setText("Get Latest " + vrResimIndex + ". Image");
				
				MainActivity.vrResimIndex = vrResimIndex;
				lblIndex.setText(String.valueOf(vrResimIndex) + "/5"  );
				
				
				try {
					
					if (vrResimIndex==1)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch1);
						}
					else if (vrResimIndex==2)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch2);
						}
					else if (vrResimIndex==3)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch3);
						}
					else if (vrResimIndex==4)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch4);
						}
					else if (vrResimIndex==5)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch5);
						}
					
				    } catch (Exception e){
				      //throw new RuntimeException(e);
				    	myImgMisSch.setImageBitmap(null);
				    }
				
				/*
				Toast.makeText(getBaseContext(),
		    			String.valueOf(vrResimIndex)	,
						Toast.LENGTH_SHORT).show();
				*/
						
				
			}
		});


		btnClose.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//thListeCameCommand.stop();
				
				
		        mis_sch.this.finish();
		        
			}
		});


		// Ana Resmi Aldýðý Yer


			myImgMisSch = (ImageView) findViewById(R.id.imgMisSch);


		btnGetMisSch.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if ( chkIndex.isChecked() ) 
				{
					fnkTusGonder("CmdMisSch" + vrResimIndex );
				}
				else
				{
					fnkTusGonder("CmdMisSch");
					
					
				}
				
				
						
			}
		});

				
				
				
				try {
					
					
					if (vrResimIndex==1)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch1);
						}
					else if (vrResimIndex==2)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch2);
						}
					else if (vrResimIndex==3)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch3);
						}
					else if (vrResimIndex==4)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch4);
						}
					else if (vrResimIndex==5)
						{
							myImgMisSch.setImageBitmap(MainActivity.bmpMisSch5);
						}
					
					
				    } catch (Exception e){
				      //throw new RuntimeException(e);
				    }
				

        
        
        
        pnlMisSch .setOnTouchListener(this);
        
	} //onreate Sonu
	
	

	@Override
	 
	 public boolean onTouch(View view, MotionEvent motionevent) {
	 
	  int action = motionevent.getAction() & MotionEvent.ACTION_MASK;
	 
	  
	  if (view.getId()==R.id.pnlMisSch )
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
	

	// thread classlarý Asycronus
			class ThSendKey extends AsyncTask<String, Void, String> {

			    @Override
			    protected String doInBackground(String... params) {
			    	
			    	
			    	String outMsg;
			    	Socket s;
			    	
			    	
					try {
						s = new Socket( MainActivity.prServerIP , Integer.parseInt(MainActivity.prMsgPort));
					
						//BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
						//send output msg
						//String outMsg = "TCP connecting to " + TCP_SERVER_PORT + System.getProperty("line.separator");
						
						outMsg = params[0] + "@" +  MainActivity.prMisSchPort;
						
						out.write(outMsg);			
						out.flush();
						
						Message msg = threadHandlerthfnkListenMsgPort.obtainMessage();
	   					msg.obj = params[0] + " Sended Successfully";
	   					threadHandlerthfnkListenMsgPort.sendMessage(msg);
						
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


			 public void fnkTusGonder(String btn )
			    {
			    	//fnkPopUp("Falcon MFD" , btn + " Pressed"  ,"Msg" ,"Info", R.layout.frmpopup);
			    	//runTcpClient(btn , IP );
			    	//Çalýþan Hali
			    	//vrBtn = btn;
			    	
			    	//ThSendKey.start();
			    	new ThSendKey().execute(btn);    	
			    }
			


			 private Handler threadHandlerthfnkListenMsgPort = new Handler() {
					public void handleMessage(android.os.Message msg) {
						
						try {				     
								
							String vrGelenMsg;
							
							vrGelenMsg = (String)msg.obj;
							
							
							String[] separated = vrGelenMsg.split("@");
							
							
//							Toast.makeText(getBaseContext(), vrGelenMsg,
//									Toast.LENGTH_SHORT).show();
							
							//textView1.setText(vrGelenMsg);
												
							} catch (Exception e){
						      //throw new RuntimeException(e);				    	
								Toast.makeText(getBaseContext(), e.getMessage(),
										Toast.LENGTH_SHORT).show(); 
								//textView1.setText(e.getMessage());
						    }
						              
						
						
					}
				}; //threadHandlerthfnkListenMsgPort Sonu



				public static  void fnkSetPicture( Bitmap prBmp)
				{
				 try {
						myImgMisSch.setImageBitmap(prBmp);
					    } catch (Exception e){
					      //throw new RuntimeException(e);
					    	
					    }	
				 
				}

}
