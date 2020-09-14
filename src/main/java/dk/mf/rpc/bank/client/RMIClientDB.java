package dk.mf.rpc.bank.client;
/**
 *
 * @author Dora Di
 */

import dk.mf.rpc.bank.server.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.Naming;

public class RMIClientDB
{
    public static void main(String args[])throws Exception
    {
        //File path to files to process
        String filePath = "src/main/resources/files/";
        String fileName = "bank_example.json";
        String file = filePath + fileName;

        //File path to save report files
        String reportFilePath = "src/main/resources/logs_and_reports/";
        String reportFileName = "report.txt";
        String reportFile = reportFilePath + reportFileName;
        File fileWriter = new File(reportFile);

        // name =  rmi:// + ServerIP +  /EngineName;
        String remoteEngine = "rmi://localhost/Bank";

        // Create local stub, lookup in the registry searching for the remote engine - the interface with the methods we want to use remotely
        BankInterface stub = (BankInterface) Naming.lookup(remoteEngine);

        byte[] report = stub.transactionFileByFilenameAndGetReport(file);

        try {
            OutputStream os = new FileOutputStream(fileWriter);
            os.write(report);
            os.close();
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }
  
} 
