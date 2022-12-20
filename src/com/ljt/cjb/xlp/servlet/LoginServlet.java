package com.ljt.cjb.xlp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ljt.cjb.xlp.entity.Global;
import com.ljt.cjb.xlp.entity.Student;
import com.ljt.cjb.xlp.entity.Teacher;
import com.ljt.cjb.xlp.dao.DaoFactory;
import com.ljt.cjb.xlp.utils.MD5;

@WebServlet(name="login",urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String type = req.getParameter("type");
		System.out.println("123456");
		HttpSession  session = req.getSession();
		session.setAttribute("type", type);
		//用户名或密码有空
		try {
			Global global = DaoFactory.getInstance().getGlobalDao().getGlobal();
			session.setAttribute("global", global);
			System.out.println("123456");
			
			if ("0".equals(type)) {// 学生
				Student student = DaoFactory.getInstance().getStudentDao().login(uname, pwd);
				if(student != null) {//登录成功
					session.setAttribute("user", student);
					resp.sendRedirect("page/Student/index.jsp");
				}else {//登录失败
					req.setAttribute("error", "用户名或密码错误");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			} else if ("2".equals(type)){// 管理员
				Teacher teacher = DaoFactory.getInstance().getTeacherDao().login(uname, MD5.getMD5(pwd));
				if(teacher != null && teacher.getT_isadmin()==1) {//查询到该用户
					session.setAttribute("user", teacher);
					resp.sendRedirect("page/Admin/index.jsp");
				}else {//登录失败
					req.setAttribute("error", "用户名或密码错误");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}	
			}else {//老师
				Teacher teacher = DaoFactory.getInstance().getTeacherDao().login(uname, MD5.getMD5(pwd));
				if(teacher != null) {//查询到该用户
					session.setAttribute("user", teacher);
					resp.sendRedirect("page/Teacher/index.jsp");
				}else {//登录失败
					req.setAttribute("error", "用户名或密码错误");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
