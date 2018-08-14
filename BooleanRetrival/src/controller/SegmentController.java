package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BooleanModel.Document;

public class SegmentController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //System.out.println("lib路径"+this.getServletContext().getRealPath("/lib"));
		
		resp.setContentType("text/text;charset=utf-8");
		String fileName = req.getParameter("fileName");
	    boolean isChinese = true;
		Document document = new Document();
		//document.FetchDocuments("data_train", isChinese);//处理文件//
		//document.fetchDocuments("D:\\eclipse-workspace\\BooleanRetrival\\dataset", isChinese);
		String initDir = this.getServletContext().getRealPath("/lib");
		String dataDir = this.getServletContext().getRealPath("/dataset");
		document.fetchDocuments(dataDir, isChinese,initDir);
		String segments = document.getSegments().get(fileName);
		System.out.println("segments=="+segments);
		resp.getWriter().println(segments);
		
		

	}
}
