
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author NC PC
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/Accounts"})
public class AccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ArrayList<Account> accounts = new ArrayList<>();
        if (action == null) {
            accounts = AccountDAO.getAccounts();
            printAccounts(request, response, accounts);
        } else if (action.equals("filter")) {
            String roleId = request.getParameter("roleId");
            if (roleId == null) {
                out.print("Role ID is null");
            }
            accounts = AccountDAO.getAccountByRoleId(Integer.parseInt(roleId));
            if (accounts.isEmpty()) {
                out.print("Accounts is null");
            }
            printAccounts(request, response, accounts);
        } else if (action.equals("getAll")) {
            accounts = AccountDAO.getAccounts();
            printAccounts(request, response, accounts);
        } else if (action.equals("view")) {
            String accountId = request.getParameter("accountId");
            Account rs = AccountDAO.getAccountId(accountId);
            if (rs != null) {
                printAccount(request, response, rs);
            } else {
                out.print("Error");
            }
        } else if (action.equals("edit")) {
            String accountId = request.getParameter("accountId");
            Account rs = AccountDAO.getAccountId(accountId);
            printEditInterface(request, response, rs);
        } else if (action.equals("editDone")) {
            Account tmp = new Account(request.getParameter("accountId"), request.getParameter("pass"), Integer.parseInt(request.getParameter("choice")));
            tmp = AccountDAO.editingAcount(tmp);
            
            if(tmp!=null) {
                printAccount(request, response, tmp);
            }
            else {
                out.print("Error");
            }
        } else if(action.equals("delete")) {
            String accountId = request.getParameter("accountId");
            Account ac = AccountDAO.getAccountId(accountId);
            ac = AccountDAO.deletingAcount(ac);
            if(ac!=null) out.print("Delete account successfully !");
            else out.print("ERROR !");
        } else if (action.equals("add")) {
            String acountID = request.getParameter("user");
            String password = request.getParameter("pass");
            String roleId_raw = request.getParameter("choice");
            int roleId = Integer.parseInt(roleId_raw);
            Account ac = new Account(acountID, password, roleId);
            ac = AccountDAO.addingAcount(ac);
            if (ac != null) {
                out.print("<h1>Adding Account successfully</h1>");
                ArrayList<Account> a = new ArrayList<>();
                accounts = AccountDAO.getAccounts();
                printAccounts(request, response, a);     
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void printAccounts(HttpServletRequest request, HttpServletResponse response, ArrayList<Account> accounts) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            if (!accounts.isEmpty()) {
                out.println("<h1>List account</h1>");
                out.print("<br><table border=\"1\">\n"
                        + "                <tr>\n"
                        + "                    <th>Account Id</th>\n"
                        + "                    <th>Password</th>\n"
                        + "                    <th>Role Id</th>\n"
                        + "                    <th></th>\n"
                        + "                    <th></th>\n"
                        + "                    <th></th>\n"
                        + "                </tr>\n");
                for (Account account : accounts) {
                    out.println("                <tr>\n"
                            + "                    <td> " + account.getAccountId() + " </td>\n"
                            + "                    <td> " + printPassword(account.getPassword()) + " </td>\n"
                            + "                    <td> " + account.getRoleId() + " </td>\n"
                            + "                    <td><input type ='button'  value='View' onclick = 'location.href=\"Accounts?action=view&accountId=" + account.getAccountId() + "\"'></td>\n"
                            + "                    <td><input type ='button'  value='Edit' onclick = 'location.href=\"Accounts?action=edit&accountId=" + account.getAccountId() + "\"'></td>\n"
                            + "                    <td><input type ='button'  value='Delete' onclick = 'location.href=\"Accounts?action=delete&accountId=" + account.getAccountId() + "\"'></td>\n"
                            + "                </tr>\n");
                }
                out.print("</table>");
            }
        }
    }

    protected void printAccount(HttpServletRequest request, HttpServletResponse response, Account account) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>List account</h1>");
            out.print("<br><table border=\"1\">\n");

            out.println("    <tr>\n"
                    + "        <td><b>Account Id</b></td>\n"
                    + "        <td> " + account.getAccountId() + " </td>\n"
                    + "    </tr>\n");

            out.println("    <tr>\n"
                    + "        <td><b>Password</b></td>\n"
                    + "        <td> " + account.getPassword() + " </td>\n"
                    + "    </tr>\n");
            String role_id = "";
            if (account.getRoleId() == 1) {
                role_id += "ADMIN";
            } else {
                role_id += "Student";
            }
            out.println("    <tr>\n"
                    + "        <td><b>Role</b></td>\n"
                    + "        <td> " + role_id + " </td>\n"
                    + "    </tr>\n");

            out.print("        </table>");
        }

    }

    protected String printPassword(String pw) {
        String rs = "";

        for (int i = 0; i < pw.length(); i++) {
            rs += "*";
        }

        return rs;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void printEditInterface(HttpServletRequest request, HttpServletResponse response, Account ac) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            if (ac != null) {
                out.print("<h1>EDIT ACCOUNT</h1>");
                String form = String.format("<h2>Account ID: %s </h2>", ac.getAccountId());
                form += String.format("<div>\n"
                        + "            <form action=\"Accounts\" method=\"GET\">\n"
                        + "                <input type=\"hidden\" name=\"action\" value=\"editDone\">\n"
                        + "                <input type=\"hidden\" name=\"accountId\" value=\"%s\">\n"
                        + "                <table>\n"
                        + "                    <tr>\n"
                        + "                        <td>Password</td>\n"
                        + "                        <td><input type=\"text\" name=\"pass\" value=\"%s\"></td>\n"
                        + "                    </tr>\n"
                        + "                    ",ac.getAccountId(), ac.getPassword());
                if (ac.getRoleId() == 1) {
                    form += "<tr>\n"
                            + "                        <td></td>\n"
                            + "                        <td>\n"
                            + "                            <select id = \"choice\" name = \"choice\">\n"
                            + "                                <option value=\"1\" selected=\"\">Admin</option>\n"
                            + "                                <option value=\"2\">Student</option>\n"
                            + "                            </select>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                    <tr>\n"
                            + "                        <td></td>\n"
                            + "                        <td><input type=\"submit\" value=\"ADD\"></td>\n"
                            + "                    </tr>\n"
                            + "                </table>";
                } else {
                    form += "<tr>\n"
                            + "                        <td></td>\n"
                            + "                        <td>\n"
                            + "                            <select id = \"choice\" name = \"choice\">\n"
                            + "                                <option value=\"1\">Admin</option>\n"
                            + "                                <option value=\"2\" selected=\"\">Student</option>\n"
                            + "                            </select>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                    <tr>\n"
                            + "                        <td></td>\n"
                            + "                        <td><input type=\"submit\" value=\"SAVE\"></td>\n"
                            + "                    </tr>\n"
                            + "                </table>";
                }
                form += "    </form>\n"
                        + "        </div>";
                out.print(form);
            }
        }
    }

}
