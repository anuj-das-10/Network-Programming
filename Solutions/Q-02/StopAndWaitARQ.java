

/*
 
This program simulates the stop-and-wait protocol for a noisy channel by sending messages, receiving ACKs or corrupted messages, and
handling timeouts. In a real implementation, you would send the messages and receive ACKs through a network connection, and use a timer 
to handle timeouts. However, this program uses simulated methods to send messages, start timers, and receive messages to make it easier 
to test and understand the stop-and-wait protocol.

*/
import java.util.Random;

class StopAndWaitARQ {
  // Constants for the program
  private static final int MESSAGE_LENGTH = 10;
  private static final int TIMEOUT = 1000;
  private static final double ERROR_RATE = 0.1;

  // Variables for the program
  private int sequenceNumber;
  private boolean waitingForAck;
  private boolean corrupted;

  public StopAndWaitARQ() {
    // Initialize variables
    sequenceNumber = 0;
    waitingForAck = false;
    corrupted = false;
  }

  public void send() {
    // Set the waiting flag and send the message
    waitingForAck = true;
    System.out.println("Sending message with sequence number:  " + sequenceNumber);
    sendMessage(sequenceNumber);

    // Start the timeout timer
    startTimer(TIMEOUT);
  }

  public void receive(int receivedSeqNum) {
    // If the message is corrupted, ignore it
    if (isCorrupted()) {
      return;
    }

    // If we are waiting for an ACK and the received message is the ACK we were expecting,
    // clear the waiting flag and send the next message
    if (waitingForAck && receivedSeqNum == sequenceNumber) {
      waitingForAck = false;
      sequenceNumber = (sequenceNumber + 1) % 2;
      send();
    }
  }

  public void timeout() {
    // If the timeout occurs while we are waiting for an ACK, resend the message
    if (waitingForAck) {
      send();
    }
  }

  private void sendMessage(int seqNum) {
    // Simulate sending the message over the channel
    // In a real implementation, you would send the message through a network connection
    System.out.println("Simulating sending message with sequence number:  " + seqNum);
  }

  private void startTimer(int timeout) {
    // Simulate starting the timeout timer
    // In a real implementation, you would start a timer that would call the timeout() method
    // after the specified number of milliseconds
    System.out.println("Simulating starting timeout timer for " + timeout + "ms");
  }

  private boolean isCorrupted() {
    // Simulate a noisy channel by randomly corrupting some of the messages
    Random rand = new Random();
    double r = rand.nextDouble();
    if (r < ERROR_RATE) {
      System.out.println("Message Corrupted!");
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    StopAndWaitARQ sender = new StopAndWaitARQ();
    sender.send();

    // Simulate receiving messages in a separate thread
    Thread receiver = new Thread(() -> {
      // Simulate receiving messages and sending ACKs
      Random rand = new Random();
      while (true) {
        try {
          // Sleep for a random amount of time before receiving the next message
          Thread.sleep(rand.nextInt(2 * TIMEOUT));

          // Simulate receiving a message
          int seqNum = rand.nextInt(2);
          System.out.println("Received message with sequence number:  " + seqNum);
          sender.receive(seqNum);
          } catch (InterruptedException e) {
          e.printStackTrace();
          }
          }
          });
          receiver.start();

          // Simulate timeouts in a separate thread
Thread timer = new Thread(() -> {
    // Simulate timeouts
    Random rand = new Random();
    while (true) {
      try {
        // Sleep for a random amount of time before the next timeout
        Thread.sleep(rand.nextInt(2 * TIMEOUT));
  
        // Call the timeout method
        sender.timeout();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  });
  timer.start();
  
  }
  }
  

  
