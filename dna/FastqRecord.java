package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//


public class FastqRecord implements DNARecord 
{
	private String defline;
	private String sequence;
	private String quality;
	//
	// Add a precondition check: throw RecordFormatException if the 1st char of the defline is 
	// not '@'. You will have to change the ctor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	// Constructor will throw the exception if the string defline doesn't start with "@".
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		this.defline = defline;
		this.sequence = sequence;
		this.quality = quality;

		// if the first letter of defline String is not "@" then throw the exception with
		// message "Bad 1st char in defline in fastq record"
		if(!defline.substring(0,1).equals("@"))
		{
			throw new RecordFormatException("Bad 1st char in defline in fastq record");
		}


	}

	//
	// Provide an equals() method that checks for deep equality of all 3 instance variables. 
	// When checking string variables, be sure to do it like this:  
	//              this.defline.equals(that.defline) 
	// and not like this:  
	//              this.defline == that.defline
	//
	
	// Check for deep equality according to order : defline first then sequence then quality.
	public int compareTo(FastqRecord that)
	{
		if(this.defline.equals(that.defline))
		{
			if(this.sequence.equals(that.sequence))
			{
				return this.quality.compareTo(that.quality);
			}
			
			return this.sequence.compareTo(that.sequence);
		}
		
		return this.defline.compareTo(that.defline);
	}
	
	// The equals method that checks for deep equality of all 3 instance variables.
	public boolean equals(Object o)
	{
		FastqRecord f = (FastqRecord)o;
		return this.compareTo(f) == 0;
	}

	//
	// Complete this. Return true if quality contains at least one '!' char
	// or at least one ‘#’ char.
	//
	// Check for the quality of the Fastq record if the record contain at least "!" char 
	// or "#" then the quality is low.
	
	public boolean qualityIsLow()
	{
		if(quality.contains("!") || quality.contains("#"))
		{
			return true;
		}
		System.out.println(quality);
		return false;
	}


	//
	// Complete this. Return the sum of the hash codes of defline, sequence, and quality.
	//
	public int hashCode()
	{
		return defline.hashCode() + sequence.hashCode() + quality.hashCode();
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
}
