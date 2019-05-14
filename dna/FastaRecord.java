package dna;

//FastqRecord contains the defline, sequence string
public class FastaRecord implements DNARecord 
{	
	private String defline;
	private String sequence;
	//
	// Add a precondition check: throw RecordGFormatException if the 1st char of the defline is 
	// not '>'. You will have to change the ctor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	// The  constructor initializes the instance variable and throws RecordFormatException
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		this.defline = defline;
		this.sequence = sequence;
		
		//if the first letter of the defline String isn't ">" then throws the message
		//"Bad 1st char in defline in fasta record"
		
		if(!defline.substring(0,1).equals(">"))
		{
			throw new RecordFormatException("Bad 1st char in defline in fasta record");
		}
	}
	
	
	// Initialize defline and sequence from the input record. The defline should be the
	// defline of the fastq record, but with a '>' in the first position rather than a '@'.
	// If you’re not sure how to do this, look up the substring method on the String API page.
	public FastaRecord(FastqRecord fastqRec)
	{
		
		this.defline = ">" + fastqRec.getDefline().substring(1);// get the defline String
		// and replace first character by ">"
		this.sequence = fastqRec.getSequence();// get the sequence String
	}


	
	
	

	// 
	// Provide the 2 methods that satisfy the interface.
	//
	@Override
	public String getDefline() {
		return defline;
	}


	@Override
	public String getSequence() {
		return sequence;
	}
	
	
	//
	// Provide an equals() method. 
	//
	
	public int compareTo(FastaRecord that)
	{
		int compareDefline = this.defline.compareTo(that.defline);
		if(compareDefline != 0)
			return compareDefline;
		return this.sequence.compareTo(that.sequence);
	}
	// equals method check for deep equality in order defline first then sequence
	public boolean equals(Object o)
	{
		FastaRecord f = (FastaRecord)o;
		return this.compareTo(f) == 0;
	}
	
	
	
	
	

	//
	// Provide a hashCode() method that returns the sum of the hashcodes of 
	// defline and sequence.
	//
	
	public int hashCode()
	{
		return defline.hashCode() + sequence.hashCode();
	}
}
