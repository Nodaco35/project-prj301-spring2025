package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Account;

public class AccountDAO {

    public static Account getAccountByUserAndPassword(String userName, String password) {
        DBContext db = DBContext.getInstance();
        Account account = null;
        try {
            String sql = """
                         SELECT *
                         FROM Accounts
                         WHERE accountId = ? AND password = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getString("accountId"));
                account.setPassword(rs.getString("password"));
                account.setRoleId(rs.getInt("roleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

        public static Account getAccountId(String accountId) {
        DBContext db = DBContext.getInstance();
        Account account = null;
        try {
            String sql = """
                         SELECT *
                         FROM Accounts
                         WHERE accountId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, accountId);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getString("accountId"));
                account.setPassword(rs.getString("password"));
                account.setRoleId(rs.getInt("roleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    
    public static ArrayList<Account> getAccounts() {
        DBContext db = DBContext.getInstance();
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = """
                        select *
                        from Accounts
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                        rs.getString("accountId"),
                        rs.getString("password"),
                        rs.getInt("roleId")
                );
                accounts.add(account);
            }
        } catch (Exception e) {
            return null;
        }
        if (accounts.isEmpty()) {
            return null;
        } else {
            return accounts;
        }
    }

    public static ArrayList<Account> getAccountByRoleId(int roleId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            String sql = """
                        select *
                        from Accounts
                        where roleId = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, roleId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                        rs.getString("accountId"),
                        rs.getString("password"),
                        rs.getInt("roleId")
                );
                accounts.add(account);
            }
        } catch (Exception e) {
            return accounts;
        }
        return accounts;
    }
    
    public static Account addingAcount(Account account) {
        DBContext db = DBContext.getInstance();
        int n=0;
        
        try {
            String sql = """
                         INSERT INTO [dbo].[Accounts]
                                    ([accountId]
                                    ,[password]
                                    ,[roleId])
                              VALUES
                                    (?,?,?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, account.getAccountId());
            statment.setString(2, account.getPassword());
            statment.setInt(3, account.getRoleId());
            n = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return account;
    }
    
    public static Account editingAcount(Account account) {
        DBContext db = DBContext.getInstance();
        int n=0;
        
        try {
            String sql = """
                         UPDATE [dbo].[Accounts]
                            SET [password] = ?
                               ,[roleId] = ?
                          WHERE [accountId] = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, account.getPassword());
            statment.setInt(2, account.getRoleId());
            statment.setString(3, account.getAccountId());
            n = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return account;
    }
    
    public static Account deletingAcount(Account account) {
        DBContext db = DBContext.getInstance();
        int n=0;
        
        try {
            String sql = """
                         Delete from [dbo].[Accounts]
                          WHERE [accountId] = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, account.getAccountId());
            n = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return account;
    }
}
