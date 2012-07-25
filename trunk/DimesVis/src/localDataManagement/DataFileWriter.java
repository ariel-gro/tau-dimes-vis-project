package localDataManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataFileWriter
{
	private File			matlabInput	= null;
	private FileWriter		fstream		= null;
	private BufferedWriter	bwriter		= null;

	public DataFileWriter(String path) throws IOException
	{
		this.matlabInput = new File(path);
		this.fstream = new FileWriter(matlabInput);
		this.bwriter = new BufferedWriter(fstream);
	}

	public void writeFullDataToFile(SourceData sd, int firstRadioButton, int secondRadioButton) throws IOException
	{
		writeSourceData(sd);
		writeAllDestData(sd);
		writeOptions(firstRadioButton, secondRadioButton);
	}

	public void closeDataFileWriter() throws IOException
	{
		this.bwriter.close();
		this.fstream.close();
	}

	private void writeSourceData(SourceData sd) throws IOException
	{
		bwriter.write("" + sd.getNumOfTargets()); // num of targets
		bwriter.newLine();
		bwriter.write("" + sd.getSourceLatitude()); // source latitude
		bwriter.newLine();
		bwriter.write("" + sd.getSourceLongitude()); // source longitude
		bwriter.newLine();
	}

	private void writeAllDestData(SourceData sd) throws IOException
	{
		TargetData td = null;
		Long[] allSeqNums = sd.getAllSequenceNumbers();
		String dstNums = "";
		String dstNames = "";
		String dstLats = "";
		String dstLongs = "";
		String dstTimes = "";
		for (int i = 0; i < sd.getNumOfTargets(); i++)
		{
			td = sd.getTarget(allSeqNums[i]);

			dstNames += td.getTargetIpAsString();
			dstNums += (i+1) + "";
			if (i < allSeqNums.length - 1)
			{
				dstNames += ", ";
				dstNums += ", ";
			}
			dstLats += td.getTargetLatitude() + " ";
			dstLongs += td.getTargetLongitude() + " ";
			dstTimes += td.getMeasuredTime() + " ";
		}

		bwriter.write(dstNums);
		bwriter.newLine();
		bwriter.write(dstNames);
		bwriter.newLine();
		bwriter.write(dstLats);
		bwriter.newLine();
		bwriter.write(dstLongs);
		bwriter.newLine();
		bwriter.write(dstTimes);
		bwriter.newLine();
	}
	
	private void writeOptions(int firstRadioButton, int secondRadioButton) throws IOException
	{
		bwriter.write(firstRadioButton+"");
		bwriter.newLine();
		bwriter.write(secondRadioButton+"");
	}
}