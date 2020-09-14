package dk.mf.rpc.bank.server;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;

public interface BankInterface extends Remote
{
    byte[] transactionFileByFilenameAndGetReport(String filename) throws IOException, SQLException;
}
