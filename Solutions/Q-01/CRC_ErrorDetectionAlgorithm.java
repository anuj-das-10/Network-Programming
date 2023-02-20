// Contributed by - Anuj Das ( GC University, Silchar - @ Department of Computer Science )

// 1. Simulate Cyclic Redundancy Check (CRC) Error Detection Algorithm for Noisy Channel.

import java.util.Scanner;

class CRC_ErrorDetectionAlgorithm {
// It returns the Remainder (i.e., CRC Check Bits)
	public static String xorOperation(String modified_message, String polynomial) {			
		int size_in_bits = 16;
		int shift = modified_message.length() - polynomial.length();
        while(shift >= 0) {
            modified_message = Integer.toBinaryString(Integer.parseInt(modified_message,2)^(Integer.parseInt(polynomial,2)<<shift));
            shift = modified_message.length() - polynomial.length();
        }

        if(modified_message.length() < size_in_bits) {
            while(modified_message.length() != size_in_bits) {
                modified_message = "0" + modified_message;
            }
        }
        // System.out.println(modified_message);
        return modified_message;
	}

// It generates the encoded data/message to be transmitted (i.e., by adding CRC Check Bits to the Original  Message)
	public static String generateCRC_CheckBits(String modified_message, String polynomial) {			
		String remainder = xorOperation(modified_message,polynomial);
		remainder = remainder.substring(remainder.length() - modified_message.length());
		
        int CRC_CheckBits[] = new int[modified_message.length()];
		for(int i = 0; i < modified_message.length(); i++) {
			CRC_CheckBits[i] = Character.getNumericValue(modified_message.charAt(i)) + Character.getNumericValue(remainder.charAt(i));
		}
		
		String msgToBeTransmitted = "";
		for(int i = 0; i < CRC_CheckBits.length; i++) {
			msgToBeTransmitted = msgToBeTransmitted.concat(Integer.toString(CRC_CheckBits[i]));
		}
		// System.out.print(msgToBeTransmitted);
		return msgToBeTransmitted;
	}

// If CRC generator is of 'n' bits then it appends 'n-1' zeroes at the end of the original data/message and returns the modified data/message
	public static String modifyMessage(String message, String polynomial) {
		for(int i = 0; i < polynomial.length() - 1; i++) {
			message += "0";
		}
		return message;
	}
	
// Passing Through Noisy Channel
	public static String noisyChannel(String msgToBeTransmitted) {
		StringBuilder noise = new StringBuilder(msgToBeTransmitted);
		noise.setCharAt(msgToBeTransmitted.length()/2 , '1');
		String noisyData = noise.toString();
		return noisyData;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Data(Message) to be Encrypted:  ");		// Original Message
		String message = sc.next();		    // Test --> 1010000	, 100100111				

		System.out.print("Enter the CRC Generator(Polynomial):  ");			// CRC Generator 
		String polynomial = sc.next();		// Test -->  1001	

		String modified_message = modifyMessage(message,polynomial);
		System.out.println("Modified Data(Message):  " + modified_message);
		
		String msgToBeTransmitted = generateCRC_CheckBits(modified_message, polynomial);
		System.out.println("\nData(Message) is ready to be Transmitted:  " + msgToBeTransmitted);

		String send_data = noisyChannel(msgToBeTransmitted);
		System.out.println("Data(Message) Transmitted through Noisy Channel:  "+send_data);

		String checkAllZeroes = xorOperation(send_data,polynomial);

// It checks if the remainder contains only zeroes  --> If it contains only zeros then the data/message is accepted else considered as error in Transmission 
		for(int i = 0; i < checkAllZeroes.length(); i++) {
			if(checkAllZeroes.charAt(i) == '1') {
				System.out.println("\nError in Transmission!\n");
				return;
			}
		}

		System.out.println("\nData(Message) Transmitted Successfully!\n");
	}
}