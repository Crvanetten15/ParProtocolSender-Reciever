/*
 * Description given:
 * 
 */

import java.io.*; 
  
public class ParSender extends TransportLayer{ 
    public static final int RECEIVER_PORT = 9888;
    public static final int SENDER_PORT = 9887;

    public ParSender(LossyChannel lc) {
		super(lc);
    }

    public void run() {
		byte nextPacketToSend = 0;
		Packet packet = new Packet();	
		byte[] msgToSend = getMessageToSend();

		if(null == msgToSend)
			return;
		
		while(true) {
			// To be completed for task#2
			// populate the packet fields

			sendToLossyChannel(packet);
			m_wakeup = false;

			// To be completed for task#2
			// start timer for retransmission

			int event = waitForEvent();
			if(EVENT_PACKET_ARRIVAL == event) {
			packet = receiveFromLossyChannel();

			// TODO task#2
			// PAR protocol implementation: sender side

			
			}
		}
    }
    

    // TODO task#4
    //
    // We get message to send from stdin
    byte[] getMessageToSend() {
	System.out.println("Please enter a message to send: ");
	try {
	    BufferedReader inFromUser = 
		new BufferedReader(new InputStreamReader(System.in)); 
	    String sentence = inFromUser.readLine(); 
	    if(null == sentence)
		System.exit(1);
	    System.out.println("Sending: "+sentence);
	    
	    return sentence.getBytes();         
	} catch(Exception e) {
	    System.out.println("IO error: "+e);
	    return null;
	}
    }

    public static void main(String args[]) throws Exception { 
	LossyChannel lc = new LossyChannel(SENDER_PORT, RECEIVER_PORT);
	ParSender sender = new ParSender(lc);
	lc.setTransportLayer(sender);
	sender.run();
    } 
} 