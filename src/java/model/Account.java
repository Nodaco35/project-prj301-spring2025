package model;

/**
 *
 * @author admin
 */
public class Account {

    private String accountId;
    private String password;
    private int roleId;

    public Account() {
    }

    public Account(String accountId, String password, int roleId) {
        this.accountId = accountId;
        this.password = password;
        this.roleId = roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{"
                + "accountId="
                + accountId
                + ", password="
                + password
                + ", roleId="
                + roleId
                + '}';
    }

}
