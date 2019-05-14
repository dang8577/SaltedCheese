package dna;

import java.io.*;
import java.util.*;

// FileConverter class converts records from fasq file to records with the fasta form to fasta file

public class FileConverter 
{
	private File fastq;
	private File fasta;

	public FileConverter(File fastq,File fasta)
	{
		this.fastq = fastq;
		this.fasta = fasta;
	}

	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.
	//
	public void convert(File fastq, File fasta) throws IOException{


		// Build chain of readers.

		if(fastq.isFile())
		{
			FileReader fr = new FileReader(fastq);
			BufferedReader br = new BufferedReader(fr);
			FastqReader fqr = new FastqReader(br);
			
			
			

			// Build chain of writers.
			FileWriter fw = new FileWriter(fasta);
			PrintWriter pw = new PrintWriter(fw);
			FastaWriter faw = new FastaWriter(pw);
		

		// Read, translate, write.
			boolean done = false;
			
			while (!done) {

				try {
					
						FastqRecord validRecord = fqr.readRecord(); // create a new fastqRecord 
						if(validRecord == null) // if the record is null then the loop stops
						{
							done = true;
						}
						else
						{
						
						FastaRecord fastaRecord = new FastaRecord(validRecord);// if there are records then create a new fastaRecord
						faw.writeRecord(fastaRecord);						  // then convert the fasq records to fasta records to fasta file
						}
					}
				 catch (RecordFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			

		


		// Close fr, br, fw, and pw in reverse order of creation.
		pw.close();
		fw.close();
		br.close();
		fr.close();
		}

	}

	// Main method reads and converts the fastq file.

	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW5.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW5.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert(fastq, fasta);
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
