package dimesVisGui;
import java.io.IOException;

import dimesSqlBasics.Connector;
import dimesSqlBasics.DimesDbOperationsMain;



public class visualizationMain {
	public static void main (String [] args) {
		
	Details details = visualizationStartGui.startMenu();
	
//	System.out.println("first view choice - "+details.getFirstRadioButton()+"\n\n"+
//									"second view choice - "+details.getSecondRadioButton()+"\n\n"+
//									"source ip - "+details.getSourceIp()[0]+":"+details.getSourceIp()[1]+":"+details.getSourceIp()[2]+":"+details.getSourceIp()[3]+"\n\n"+
//									"time measurement method - "+details.getTimeChoiceRadioButton()+"\n\n"+
//									"date of measurement - "+details.getDate()[0]+":"+details.getDate()[1]+":"+details.getDate()[2]+"\n\n");
	try {
		DimesDbOperationsMain.startDimesDbOperations(details);
	} catch (IOException e) {
		System.out.println("Something went wrong in Ariel's code");
		e.printStackTrace();
	}
	
	}
}
