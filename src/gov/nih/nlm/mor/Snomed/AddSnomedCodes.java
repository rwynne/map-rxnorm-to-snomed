///SCRAP


package gov.nih.nlm.mor.Snomed;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import gov.nih.nlm.mor.RxNorm.RxNormSCD;

public class AddSnomedCodes implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5311623968177638835L;
	public TreeMap<Integer, RxNormSCD> map = new TreeMap<Integer, RxNormSCD>();
	public ConcurrentHashMap<Integer, RxNormSCD> myMap = null;
	private int count = 0;
	private boolean isWorking = true;
	
	
	public AddSnomedCodes(TreeMap<Integer, RxNormSCD> scdMap) {
		this.map = scdMap;
		B obj = new B();
		EventListener mListener = new A();
		obj.registerEventListener(mListener);

		
		
//		for( Integer i : map.keySet() ) {
//			myMap.put(i, map.get(i));  //deep copy, redundant type
//		}

		
		
		obj.doStuff();
		
		isWorking = false;
	}
	
	public AddSnomedCodes() {
		
	}
	
	interface EventListener { 
		  
	    // this can be any type of method 
	    void onEvent(); 
	    
	} 
	  
	class B { 
	  
	    private EventListener mListener; // listener field 
	  
	    // setting the listener 
	    public void registerEventListener(EventListener mListener) 
	    { 
	        this.mListener = mListener; 
	    } 
	  
	    // My Asynchronous task 
	    public void doStuff() 
	    { 
			
	        // An Async task always executes in new thread 
			for( Integer i : map.keySet() ) {
				++count;
				RxNormSCD scd = map.get(i);
				if( count % 20 == 0 ) {
					try {
						System.out.println("SCD concepts ran: " + count);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("I was trying to sleep, but you woke me up on: " + scd.getName() + " : " + scd.getCui());
						e.printStackTrace();
					}
				}
				new Thread(new Runnable() { 
					public void run() 
					{ 
		  
						// perform any operation 
						
							scd.setSnomedCodes();
						myMap.put(i, scd);  //the key already exists duh, put!
		  
						// check if listener is registered. 
						if (mListener != null) { 
		  
							// invoke the callback method of class A 
							mListener.onEvent(); 
						} 
					} 
				}).start(); 
			}
			
			isWorking = false;
	    } 
	  
	    // Driver Program 
//	    public static void main(String[] args) 
//	    { 
//	  
//	        B obj = new B(); 
//	        EventListener mListener = new A(); 
//	        obj.registerEventListener(mListener); 
//	        obj.doStuff(); 
//	    } 
	} 
	  
	class A implements EventListener { 
	  
	    @Override
	    public void onEvent() 
	    { 
	    	System.out.println("Performing callback after Asynchronous Task"); 
	        // perform some routine operation 
	    } 
	    // some class A methods 
	} 
	
	public TreeMap<Integer, RxNormSCD> getSCDMap() {
		TreeMap<Integer, RxNormSCD> scdMap = new TreeMap<Integer, RxNormSCD>();
		for( Integer i : myMap.keySet() ) {
			scdMap.put(i, myMap.get(i));			
		}
		return scdMap;
	}
	
	public boolean isWorking() {
		return this.isWorking;
	}

}
