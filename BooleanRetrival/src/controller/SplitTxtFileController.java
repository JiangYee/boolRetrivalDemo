package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BooleanModel.Document;
import utilities.NLPIR;

public class SplitTxtFileController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/text;charset=utf-8");
		String contents = req.getParameter("fileContents");
		System.out.println(contents);
		String initDir = this.getServletContext().getRealPath("/lib");
		NLPIR.init(initDir);
		String splitResult = NLPIR.paragraphProcess(contents.toString(),0);
		System.out.println("splitResult=="+splitResult);
		resp.getWriter().println(splitResult);
	}
}
