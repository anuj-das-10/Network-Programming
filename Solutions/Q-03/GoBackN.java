// Contributed by - Anuj Das ( GC University, Silchar - @ Department of Computer Science )

// 3. Simulate and implement Go-Back-N Sliding Window Protocol.

import java.util.Arrays;

class GoBackN {
    // window size
    private static final int WINDOW_SIZE = 4;

    // number of frames to send
    private static final int NUM_FRAMES = 10;

    public static void main(String[] args) {
        // initialize the array of frames to be sent
        int[] frames = new int[NUM_FRAMES];
        for (int i = 0; i < NUM_FRAMES; i++) {
            frames[i] = i;
        }

        // simulate sending the frames
        int base = 0;
        int nextSeqNum = 0;
        while (base < NUM_FRAMES) {
            // send a window of frames
            for (int i = base; i < base + WINDOW_SIZE && i < NUM_FRAMES; i++) {
                sendFrame(frames[i], nextSeqNum);
                nextSeqNum++;
            }

            // receive an acknowledgement
            int ack = receiveAck();

            // if the acknowledgement is not within the current window, go back to the last successful frame
            if (ack < base || ack >= base + WINDOW_SIZE) {
                System.out.println("Received invalid ack, going back to frame " + base);
                nextSeqNum = base;
            } else {
                // otherwise, slide the window forward
                base = ack + 1;
            }
        }
    }

    private static void sendFrame(int frame, int seqNum) {
        System.out.println("Sending frame " + frame + " with sequence number " + seqNum);
    }

    private static int receiveAck() {
        // in a real implementation, this would receive an acknowledgement from the receiver
        // for the purpose of this simulation, we will randomly generate an acknowledgement
        int ack = (int) (Math.random() * NUM_FRAMES);
        System.out.println("Received ack for frame: " + ack);
        return ack;
    }
}
