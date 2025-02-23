/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author NC PC
 */
public class StudentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("getAll")) {
            ArrayList<Student> stuList = StudentDAO.getAllStudent();
            printAllStudent(request, response, stuList);

        } else if (action.equals("searchByName")) {
            String search = request.getParameter("searchingName");
            ArrayList<Student> stuList = StudentDAO.getListStudentBySearchingName(search);
            printListStudent(request, response, stuList);
        } else if (action.equals("orderByGender")) {
            String ops = request.getParameter("gender");
            ArrayList<Student> stuList = StudentDAO.getListStudentByOrderByGender(ops);
            printListStudent(request, response, stuList);
        } else if (action.equals("add")) {
            String stuID = request.getParameter("stuId");
            String fName = request.getParameter("fName");
            String bDay = request.getParameter("bDay");
            String gender = request.getParameter("gender");
            String[] eng_raw = request.getParameterValues("English");
            String[] eng = parseEnglish(eng_raw);
            String specId = request.getParameter("specId");
            //out.print(" " + eng[0] + " " + eng[1] + " " + eng[2] + " " + eng[3] + " ");
            Student stuAdd = new Student(stuID, fName, bDay, gender, eng[0], eng[1], eng[2], eng[3], specId);
            out.print(stuAdd);
            stuAdd = StudentDAO.addingStudent(stuAdd);
            if (stuAdd != null) {
                request.getRequestDispatcher("students.html").forward(request, response);
            } else {
                out.print("  ERROR");
            }
        } else if (action.equals("view")) {
            String stuId = request.getParameter("studentId");
            Student stu = StudentDAO.getStudentBySearchingStudentID(stuId);
            ArrayList<Student> stuList = new ArrayList<>();
            stuList.add(stu);
            printListStudent(request, response, stuList);
        } else if (action.equals("edit")) {
            String stuId = request.getParameter("studentId");
            Student stu = StudentDAO.getStudentBySearchingStudentID(stuId);
            printEditInterface(request, response, stu);

        } else if (action.equals("editDone")) {
            String stuId = request.getParameter("stuId");
            String fullname = request.getParameter("fName");
            String bDay = request.getParameter("bDay");
            String gender = request.getParameter("gender");
            String[] eng_raw = request.getParameterValues("English");
            String[] eng = parseEnglish(eng_raw);
            String specId = request.getParameter("specId");
            Student stu = new Student(stuId, fullname, bDay, gender, eng[0], eng[1], eng[2], eng[3], specId);
            
            stu = StudentDAO.editingStudent(stu);
            if(stu!= null){
                out.println("Update student successfully");
                
            }
            else out.print("ERROR");
        } else if (action.equals("delete")) {
            String stuId = request.getParameter("studentId");
            stuId = StudentDAO.deletingStudent(stuId);
            if(stuId!=null) {
                out.print("Delete student successfully");
            }
            else out.print("ERROR");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void printListStudent(HttpServletRequest request, HttpServletResponse response, ArrayList<Student> stuList) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("<h1>List of Student</h1>");
        out.print("<table border=\"1\">\n"
                + "                <tr>\n"
                + "                    <th>Student ID</th>\n"
                + "                    <th>Full Name</th>\n"
                + "                    <th>Birth day</th>\n"
                + "                    <th>Gender</th>\n"
                + "                    <th>English 1</th>\n"
                + "                    <th>English 2</th>\n"
                + "                    <th>English 3</th>\n"
                + "                    <th>English 4</th>\n"
                + "                    <th>Specialization ID</th>\n"
                + "                </tr>");
        for (Student s : stuList) {
            out.print("<tr>\n"
                    + "                    <td>" + s.getStudentID() + "</td>\n"
                    + "                    <td>" + s.getFullName() + "</td>\n"
                    + "                    <td>" + s.getBirthDate() + "</td>\n"
                    + "                    <td>" + s.getGender() + "</td>\n"
                    + "                    <td>" + s.getE1() + "</td>\n"
                    + "                    <td>" + s.getE2() + "</td>\n"
                    + "                    <td>" + s.getE3() + "</td>\n"
                    + "                    <td>" + s.getE4() + "</td>\n"
                    + "                    <td>" + s.getSpecId() + "</td>\n"
                    + "                </tr>");
        }
        out.print("</table>");
    }

    protected void printAllStudent(HttpServletRequest request, HttpServletResponse response, ArrayList<Student> stuList) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("<h1>List of Student</h1>");
        out.print("<table border=\"1\">\n"
                + "                <tr>\n"
                + "                    <th>Student ID</th>\n"
                + "                    <th>Full Name</th>\n"
                + "                    <th>Birth day</th>\n"
                + "                    <th>Gender</th>\n"
                + "                    <th>English need learn</th>\n"
                + "                    <th>Specialization ID</th>\n"
                + "                    <th></th>\n"
                + "                    <th></th>\n"
                + "                    <th></th>\n"
                + "                </tr>");
        for (Student s : stuList) {
            out.print("<tr>\n"
                    + "                    <td>" + s.getStudentID() + "</td>\n"
                    + "                    <td>" + s.getFullName() + "</td>\n"
                    + "                    <td>" + s.getBirthDate() + "</td>\n"
                    + "                    <td>" + s.getGender() + "</td>\n"
                    + "                    <td>" + getPassEngLevel(s) + "</td>\n"
                    + "                    <td>" + s.getSpecId() + "</td>\n"
                    + "                    <td><input type ='button'  value='View' onclick = 'location.href=\"students?action=view&studentId=" + s.getStudentID() + "\"'></td>\n"
                    + "                    <td><input type ='button'  value='Edit' onclick = 'location.href=\"students?action=edit&studentId=" + s.getStudentID() + "\"'></td>\n"
                    + "                    <td><input type ='button'  value='Delete' onclick = 'location.href=\"students?action=delete&studentId=" + s.getStudentID() + "\"'></td>\n"
                    + "                </tr>");
        }
        out.print("</table>");
    }

    protected String getPassEngLevel(Student s) {
        String rs = "";
        ArrayList<String> e_Fix = new ArrayList<>();

        String e1 = "English 1";
        String e2 = "English 2";
        String e3 = "English 3";
        String e4 = "English 4";

        e_Fix.add(e1);
        e_Fix.add(e2);
        e_Fix.add(e3);
        e_Fix.add(e4);

        if (s.getE1().equals("Passed")) {
            e_Fix.remove(e1);
        }
        if (s.getE2().equals("Passed")) {
            e_Fix.remove(e2);
        }
        if (s.getE3().equals("Passed")) {
            e_Fix.remove(e3);
        }
        if (s.getE4().equals("Passed")) {
            e_Fix.remove(e4);
        }

        if (e_Fix.isEmpty()) {
            rs = "none";
        } else {
            for (String tmp : e_Fix) {
                rs += tmp;
                rs += "; ";
            }
        }

        return rs;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String[] parseEnglish(String[] eng_raw) {
        String[] rs = new String[4];
        if (eng_raw == null) {
            String[] raw = {"Not passed", "Not passed", "Not passed", "Not passed"};
            rs = raw;
        } else {
            String[] raw = {"Not passed", "Not passed", "Not passed", "Not passed"};

            for (int j = 0; j < eng_raw.length; j++) {
                String[] tmp = eng_raw[j].split(" ");
                int r = Integer.parseInt(tmp[tmp.length - 1]) - 1;
                raw[r] = "Passed";
            }
            rs = raw;
        }
        return rs;
    }

    private void printEditInterface(HttpServletRequest request, HttpServletResponse response, Student s) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            if (s != null) {
                out.print("<h1>EDIT ACCOUNT</h1>");
                String form = String.format("<h2>Student ID: %s </h2>", s.getStudentID());
                form += String.format(
                        """
                            <div>
                                    <form action="students" method="GET">
                                        <input type="hidden" name="action" value="editDone">
                                        <input type="hidden" name="stuId" value="%s">
                                        <table>
                                            <tr>
                                                <td>Full Name</td>
                                                <td><input type="text" name="fName" value="%s"></td>
                                            </tr>
                              """, s.getStudentID(),
                        s.getFullName());
                form += String.format("""
                                    <tr>
                                        <td>Birth day</td>
                                        <td><input type="date" name="bDay" value="%s"></td>
                                    </tr>
                                    """,
                        s.getBirthDate());
                if (s.getGender().equals("Male")) {
                    form += String.format("""
                                        <tr>
                                            <td>Gender</td>
                                            <td>
                                                <input type="radio" name="gender" checked="" value="Male"> Male
                                                <input type="radio" name="gender" value="Female"> Female
                                                <input type="radio" name="gender" value="Other"> Other
                                            </td>
                                        </tr>
                                        """, s.getGender());
                } else if (s.getGender().equals("Female")) {
                    form += String.format("""
                                        <tr>
                                            <td>Gender</td>
                                            <td>
                                                <input type="radio" name="gender" value="Male"> Male
                                                <input type="radio" name="gender" checked="" value="Female"> Female
                                                <input type="radio" name="gender" value="Other"> Other
                                            </td>
                                        </tr>
                                        """, s.getGender());
                } else {
                    form += String.format("""
                                        <tr>
                                            <td>Gender</td>
                                            <td>
                                                <input type="radio" name="gender" value="Male"> Male
                                                <input type="radio" name="gender" value="Female"> Female
                                                <input type="radio" name="gender" checked="" value="Other"> Other
                                            </td>
                                        </tr>
                                        """, s.getGender());
                }
                
                ArrayList<String> e_Fix = new ArrayList<>();

                String e1 = "English 1";
                String e2 = "English 2";
                String e3 = "English 3";
                String e4 = "English 4";

                e_Fix.add(e1);
                e_Fix.add(e2);
                e_Fix.add(e3);
                e_Fix.add(e4);

                if (s.getE1().equals("Not Passed")) {
                    e_Fix.remove(e1);
                }
                if (s.getE2().equals("Not Passed")) {
                    e_Fix.remove(e2);
                }
                if (s.getE3().equals("Not Passed")) {
                    e_Fix.remove(e3);
                }
                if (s.getE4().equals("Not Passed")) {
                    e_Fix.remove(e4);
                }
                switch (e_Fix.size()) {
                    case 1:
                        form+="""
                              <tr>
                                <td style="align-content: flex-start"><div>English</div></td> 
                                 <td>
                                                      <input type="checkbox" name="English" value="English 1" checked="">
                                                      <label>English 1</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 2">
                                                      <label>English 2</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 3">
                                                      <label>English 3</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 4">
                                                      <label>English 4</label>
                                                      <br>
                                                  </td>
                                              </tr>
                              """;
                        break;
                    case 2:
                        form+="""
                              <tr>
                                <td style="align-content: flex-start"><div>English</div></td> 
                                 <td>
                                                      <input type="checkbox" name="English" value="English 1" checked="">
                                                      <label>English 1</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 2" checked="">
                                                      <label>English 2</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 3">
                                                      <label>English 3</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 4">
                                                      <label>English 4</label>
                                                      <br>
                                                  </td>
                                              </tr>
                              """;
                        break;
                    case 3:
                        form+="""
                              <tr>
                                <td style="align-content: flex-start"><div>English</div></td> 
                                 <td>
                                                      <input type="checkbox" name="English" value="English 1" checked="">
                                                      <label>English 1</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 2" checked="">
                                                      <label>English 2</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 3" checked="">
                                                      <label>English 3</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 4">
                                                      <label>English 4</label>
                                                      <br>
                                                  </td>
                                              </tr>
                              """;
                        break;
                        case 4:
                        form+="""
                              <tr>
                                <td style="align-content: flex-start"><div>English</div></td> 
                                 <td>
                                                      <input type="checkbox" name="English" value="English 1" checked="">
                                                      <label>English 1</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 2" checked="">
                                                      <label>English 2</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 3" checked="">
                                                      <label>English 3</label>
                                                      <br>
                                                      <input type="checkbox" name="English" value="English 4" checked="">
                                                      <label>English 4</label>
                                                      <br>
                                                  </td>
                                              </tr>
                              """;
                        break;
                }
                switch (Integer.parseInt(s.getSpecId())) {
                    case 1:
                        form+="""
                              <tr>
                                                  <td>Specialization</td>
                                                  <td>
                                                      <select id="specId" name="specId">
                                                          <option value="1" selected="">SE</option>
                                                          <option value="2">IA</option>
                                                          <option value="3">AI</option>
                                                          <option value="4">IB</option>
                                                      </select>
                              
                                                  </td>
                            </tr>
                              """;
                        break;
                    case 2:
                        form+="""
                              <tr>
                                                  <td>Specialization</td>
                                                  <td>
                                                      <select id="specId" name="specId">
                                                          <option value="1">SE</option>
                                                          <option value="2" selected="">IA</option>
                                                          <option value="3">AI</option>
                                                          <option value="4">IB</option>
                                                      </select>
                              
                                                  </td>
                               </tr>
                              """;
                        break;
                    case 3:
                        form+="""
                              <tr>
                                                  <td>Specialization</td>
                                                  <td>
                                                      <select id="specId" name="specId">
                                                          <option value="1">SE</option>
                                                          <option value="2">IA</option>
                                                          <option value="3" selected="">AI</option>
                                                          <option value="4">IB</option>
                                                      </select>
                              
                                                  </td>
                           </tr>
                              """;
                        break;
                    case 4:
                        form+="""
                              <tr>
                                                  <td>Specialization</td>
                                                  <td>
                                                      <select id="specId" name="specId">
                                                          <option value="1">SE</option>
                                                          <option value="2">IA</option>
                                                          <option value="3">AI</option>
                                                          <option value="4" selected="">IB</option>
                                                      </select>
                              
                                                  </td>
                           </tr>
                              """;
                        break;
                }
                form+="""
                                  <tr><td></td><td><input type="submit" value="UPDATE"></td></tr>
                                  </table>
                              </form>
                          </div>
                      """;
                out.print(form);
            }
        }
    }
}
