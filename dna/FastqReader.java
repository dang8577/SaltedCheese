package dna;

import java.io.*;


//
// Reads lines from a BufferedReader and builds a FastqRecord.
//



public class FastqReader 
{

	private BufferedReader theBufferedReader;

	
	public FastqReader(BufferedReader theBufferedReader)
	{
		this.theBufferedReader = theBufferedReader;
	}
	// Returns next record in the file, or null if EOF (end-of-file).
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		// Read the defline from the BufferedReader. Return null if you read null, 
		// indicating end of file.

		boolean done = false;

		//
		
				while(!done)
				{	
					String defline = theBufferedReader.readLine(); 
					System.out.println(defline); // read the first line
					if(defline != null) // if the first line is not null then read next 3 line
					{
						try {
						String sequence = theBufferedReader.readLine(); // read the second line(sequence String)
						theBufferedReader.readLine(); // skip 3rd line (the line contains "+" only)
						String qual = theBufferedReader.readLine(); // read the forth line(quality String)
						FastqRecord f = new FastqRecord(defline, sequence, qual); // create a fastq record which contains Strings: defline, sequence, quality
						if(f.qualityIsLow()) // check for the quality if it's low then throw exception message "quality is low."
						{
							throw new RecordFormatException("quality is low.");
							//faw.writeRecord(fastareq);
						}
						if(f.getSequence() == null) //check for the sequence if it's null then throw exception message ""Sequence is null"
						{
							throw new RecordFormatException("Sequence is null");
						}
	
	
						return f; // if all the conditions are good then return fasq record
					}
						catch(RecordFormatException e)  // if the first line is null then throw the RecordFormatException message
						{
							throw e;
						}
					}
					else return null;
				}
			
				
		return null;

		//		String firstLine = theBufferedReader.readLine();
		//		
		//		if(firstLine == null)
		//		{
		//			return null;
		//		}
		//		
		//		String secondLine = theBufferedReader.readLine();
		//		theBufferedReader.readLine();
		//		String forthLine = theBufferedReader.readLine();
		//		return new FastqRecord(firstLine,secondLine,forthLine );


		/*	else
		{
			String secondLine = theBufferedReader.readLine();
			String thirdLine = theBufferedReader.readLine();
			String forthLine = theBufferedReader.readLine();


			for(int i = 0; i < secondLine.length(); i++)
			{
				if(!(secondLine.substring(i,i+1).equals("A") && secondLine.substring(i,i+1).equals("C")
						&& secondLine.substring(i,i+1).equals("G") && secondLine.substring(i,i+1).equals("T")))
				{
					throw new RecordFormatException("Bad letter in the sequence (shoule be only A,C,T or G)");
				}
			}

			for(int j = 0; j < thirdLine.length(); j++)
			{
				if(!thirdLine.substring(j,j+1).equals("+"))
				{
					throw new RecordFormatException("It should be + signs for whole line.");
				}
			}
			FastqRecord f = new FastqRecord(firstLine,secondLine,forthLine );
			if(forthLine.length() != secondLine.length() || f.qualityIsLow())
			{
				throw new RecordFormatException("The length is not good or quality is low");
			}


			return f;
		}

		// Read the next 3 lines from the buffered reader. Construct and return
		// a FastqRecord.
		 * 
		 */

	}


}
