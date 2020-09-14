package dk.mf.rpc.bank.server;

/**
 *
 * @author Dora Di
 */
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dk.mf.rpc.bank.service.files.*;
import com.google.gson.Gson;

@RestController
public class BankImplementation extends UnicastRemoteObject implements BankInterface
{
    public static String url = "jdbc:h2:file:C:/users/morte/Documents/Dokumentation/1. Semester (Software Udvikling)/3. System Integration (SI)/Assignments/Week 37 - Lektion 2/Git/Assignment2-2_Week37_RPC_RMI_DB/src/main/resources/db/morten";
    public static String user = "";
    public static String password = "";
    public static String driver = "org.h2.Driver";

    BankImplementation()throws RemoteException{}

    public byte[] processTransaction(String json) throws SQLException {
        Transaction transaction = new Gson().fromJson(json, Transaction.class);

        Connection con = null;
        Statement statement = null;
        List<Transaction> transactions = new ArrayList();

        try
        {
            Class.forName(driver);
            con=DriverManager.getConnection(url, user, password);
            statement = con.createStatement();
            statement.executeUpdate("INSERT INTO Transactions_accounts (accnum, type, amount) VALUES (" + transaction.getAccountnumber() + "," + "'" + transaction.getType() + "'" + "," + transaction.getAmount() + ");");

            PreparedStatement ps=con.prepareStatement("select * from Transactions_accounts ;");
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Transaction t = new Transaction();
                t.setId(rs.getInt(1));
                t.setAccountnumber(rs.getInt(2));
                t.setType(rs.getString(3));
                t.setAmount(rs.getDouble(4));
                transactions.add(t);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Goodbye!");
        return String.valueOf(transactions.size()).getBytes();
    }

    @Override
    public byte[] transactionFileByFilenameAndGetReport(String filename) throws IOException, SQLException {
        String encodedJSON = new Encoder().encodeJSONFile(filename);
        return processTransaction(new Decoder().decodeJSONFile(encodedJSON));
    }
}  



