package main;


public class HidePass implements Runnable {
	 private boolean stop;

	// Constructor Method: displays prompt for user
		// Parameters: 
		//		prompt: string, prompt for user
		// Returns: Void
	 
	   public HidePass(String prompt) {
	       System.out.print(prompt);
	   }

	   /**
	    * Begin masking...display asterisks (*)
	    */
	   @Override
	public void run () {
	      stop = true;
	      while (stop) {
	         System.out.print("\010*");
	     try {
	        Thread.currentThread();
	        Thread.sleep(1);
	         } catch(InterruptedException ie) {
	            ie.printStackTrace();
	         }
	      }
	   }

	   /**
	    * Instruct the thread to stop masking
	    */
	   public void stopMasking() {
	      this.stop = false;
	   }
}
