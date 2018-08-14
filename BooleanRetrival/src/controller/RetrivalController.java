package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BooleanModel.BoolRetrivalModel;
import BooleanModel.Document;
import net.sf.json.JSONArray;

public class RetrivalController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("进入IndexController!");
		resp.setContentType("text/text;charset=utf-8");
		//String docDir = "D:\\eclipse-workspace\\BooleanRetrival\\dataset";
		String dataDir = this.getServletContext().getRealPath("/dataset");
		String[] terms = req.getParameterValues("terms");
		String[] operators = req.getParameterValues("operators");
		//ArrayList<String> nameResults = new ArrayList<String>();
		//ArrayList<String> contentResults = new ArrayList<String>();
		TreeMap<String, String> results = new TreeMap<String, String>();
		
		boolean isChinese = true;
		BoolRetrivalModel br = new BoolRetrivalModel();
		Document document = new Document();
	//	document.fetchDocuments(docDir, isChinese);//处理文件//
		String initDir = this.getServletContext().getRealPath("/lib");
		document.fetchDocuments(dataDir, isChinese, initDir);//处理文件//
		TreeMap<Integer, ArrayList<String>>documents =document.getDocuments();
		HashMap<Integer, String> docID_Name = document.getDocID_Name();
		HashMap<Integer, String> docID_Content = document.getDocID_Contents();
		br.buildInvertedIndex(documents);//建立倒排索引
		TreeMap<String, ArrayList<Integer>> invertedIndex= br.getInvertedIndex();
		ArrayList<Integer>ResultIDs = br.boolRetrival(terms, operators);
		if(null==ResultIDs) {//没有结果
			resp.getWriter().println(0);
		}else {
			for (int i = 0; i < ResultIDs.size(); i++) {
				int id = ResultIDs.get(i);
				//nameResults.add(docID_Name.get(id));
				//contentResults.add(docID_Content.get(id));
				results.put(docID_Name.get(id), docID_Content.get(id));
			} 
		if(results.isEmpty()) {//没有结果
			resp.getWriter().println(0);
		}else {
			JSONArray jsonArray =JSONArray.fromObject(results);
			System.out.println("jsonArray是： "+jsonArray);
			resp.getWriter().println(jsonArray);
		}
		System.out.println("检索结果是："+results);	
			//document.writeDocuments();//写入文件
			//br.writeIndex();//倒排索引写入文件
			
			//JSONArray jsonArray = new JSONArray();
			//jsonArray.add(nameResults);
			//jsonArray.add(nameResults);
			
		}

		
		
		
		
		
		
		
		
	}
}
