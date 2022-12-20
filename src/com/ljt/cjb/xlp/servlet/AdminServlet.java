package com.ljt.cjb.xlp.servlet;

//跳转到servlet：response.sendRedirect()

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ljt.cjb.xlp.dao.DaoFactory;
import com.ljt.cjb.xlp.entity.Global;
import com.ljt.cjb.xlp.entity.Teacher;
import com.ljt.cjb.xlp.utils.MD5;
import com.ljt.cjb.xlp.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;

import com.ljt.cjb.xlp.utils.PathUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println(method);
		if("list".equals(method)) {//管理员列出老师
			this.list(request, response);
		}else if("add".equals(method)) {//管理员添加老师
			this.add(request, response);
		}else if("update".equals(method)) {//管理员修改老师
			this.update(request, response);
		}else if("delete".equals(method)) {//管理员删除老师
			this.delete(request, response);
		}else if("resetPwd".equals(method)) {//管理员重置老师密码
			this.resetPwd(request, response);
		}else if("updatePwd".equals(method)) {//管理员和老师修改自己密码
			this.updatePwd(request, response);
		}else if("updateGlobal".equals(method)) {//管理员修改全局变量
			this.updateGlobal(request, response);
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String t_id = request.getParameter("t_id");
		System.out.println("47:"+t_id);
		try {
			DaoFactory.getInstance().getTeacherDao().delete(t_id);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"admin?method=list&msg=Delete user sucessful!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		String t_id = request.getParameter("t_id");
		String t_pwd = request.getParameter("t_pwd");
		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		teacher.setT_pwd(MD5.getMD5(t_pwd));
		System.out.println("62:"+t_id+" "+t_pwd);
		try {
			DaoFactory.getInstance().getTeacherDao().resetPwd(teacher);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"admin?method=list&msg=Reset password sucessful!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String t_id = request.getParameter("id");
		String t_pwd = request.getParameter("t_pwd");
		String pwd = request.getParameter("pwd");
		String newPwd = request.getParameter("newPwd");
		String newPwd2 = request.getParameter("newPwd2");
		System.out.println("pwd: "+ pwd+"  "+t_pwd+" "+MD5.getMD5(pwd));
		
		if(!MD5.getMD5(pwd).equals(t_pwd)) {
			response.sendRedirect("page/Admin/pwd.jsp?msg=Please input correct old password...");
		}else if(!newPwd.equals(newPwd2)) {
			response.sendRedirect("page/Admin/pwd.jsp?msg=Please enter the same new password twice...");
		}else {
			Teacher teacher = new Teacher();
			teacher.setT_id(t_id);
			teacher.setT_pwd(MD5.getMD5(newPwd));
			System.out.println("62:"+t_id+" "+newPwd);
			try {
				DaoFactory.getInstance().getTeacherDao().resetPwd(teacher);
				//直接重定向到列表页面
				response.sendRedirect("page/Admin/pwd.jsp?msg=Update password sucessful!");
			} catch (Exception e) {
				e.printStackTrace();
				}
			}
		}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("update");
		String t_id = request.getParameter("t_id");
		String t_name = request.getParameter("t_name");
		Integer t_isadmin = getIntParameter(request,"t_isadmin");
		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		teacher.setT_isadmin(t_isadmin);
		teacher.setT_name(t_name);
		System.out.println("78:"+t_name);
		System.out.println("78:"+t_id);
		System.out.println("78:"+t_isadmin);

		try {
			DaoFactory.getInstance().getTeacherDao().update(teacher);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"admin?method=list&msg=Update sucessful!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {//js已经验证不为空
		String t_id = request.getParameter("t_id");
		String t_pwd = "123456";//默认密码
		String t_name = request.getParameter("t_name");
		Integer t_isadmin = getIntParameter(request, "t_isadmin");
		
		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		teacher.setT_isadmin(t_isadmin);
		teacher.setT_name(t_name);
		teacher.setT_pwd(MD5.getMD5(t_pwd));
		try {
			DaoFactory.getInstance().getTeacherDao().add(teacher);
			//直接重定向到列表页面
			System.out.println(PathUtils.getBasePath(request)+"admin?method=list");
			response.sendRedirect(PathUtils.getBasePath(request)+"admin?method=list&msg=Add user sucessful!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("======AdminServlet list========");
		//当前页码
		Integer pageNo = getIntParameter(request, "pageNo");
		//查询条件
		String t_id = request.getParameter("t_id");
		String t_name = request.getParameter("t_name");
		Integer t_isadmin = getIntParameter(request, "t_isadmin");
		
		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		teacher.setT_name(t_name);
		teacher.setT_isadmin(t_isadmin);
//		System.out.println()
		//构造了一个pageInfo对象
		PageInfo<Teacher> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = DaoFactory.getInstance().getTeacherDao().list(teacher,pageInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			request.setAttribute("pageInfo", pageInfo);
			//回写到页面
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("page/Admin/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Integer getIntParameter(HttpServletRequest request,String name) {
		if(StringUtils.isNoneBlank(request.getParameter(name))) {
			return Integer.parseInt(request.getParameter(name));
		}else {
			return null;
		}
	}
	private void updateGlobal(HttpServletRequest request, HttpServletResponse response) {
		Integer task_interval = getIntParameter(request, "task_interval");
		Integer record_number = getIntParameter(request, "record_number");
		Integer max_advance_time = getIntParameter(request, "max_advance_time");
		Integer min_file_size = getIntParameter(request, "min_file_size");
		Integer max_file_size = getIntParameter(request, "max_file_size");
		Integer teacher_can_clean= 0;
		if(request.getParameter("teacher_can_clean")!=null && request.getParameter("teacher_can_clean").equals("on")) {
			teacher_can_clean= 1;
		}

		Global global = new Global();
		global.setTask_interval(task_interval);
		global.setRecord_number(record_number);
		global.setMax_advance_time(max_advance_time);
		global.setMin_file_size(min_file_size);
		global.setMax_file_size(max_file_size);
		global.setTeacher_can_clean(teacher_can_clean);
		
		try {
			System.out.println("updatGlobal");
			HttpSession  session = request.getSession();
			session.setAttribute("global",global);
			DaoFactory.getInstance().getGlobalDao().update(global);
			System.out.println("updateGlobal sucessful");
			response.sendRedirect(PathUtils.getBasePath(request)+"page/Admin/showGlobal.jsp?msg=Update global sucessful!");
		} catch (Exception e) {
		}
	}
}
