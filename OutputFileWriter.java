import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {
FileWriter output;
	
	public OutputFileWriter()
	{
		try {
			output = new FileWriter("output.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}          
	}
	
	public void writeMoveToFile(String move)
	{
		if(output != null)
		{
			try {
				output.write(move);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeFile()
	{
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
