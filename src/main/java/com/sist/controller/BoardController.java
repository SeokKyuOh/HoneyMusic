package com.sist.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sist.board.dao.BoardDAO;
import com.sist.board.dao.BoardVO;
import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;


@Controller
public class BoardController {
	
	@Autowired							// 상단 차트순위 때문에 추가 (7/6 오석규)
	private ChartService service;	// 상단 차트순위 때문에 추가 (7/6 오석규)
	
	@Autowired	
	private BoardDAO dao;
	
	List<Daily_ChartVO> daily_list;	// 상단 차트순위 때문에 추가 (7/6 오석규)
	
	@RequestMapping("main/board_list.do")
	public String board_list(Model model, int boardType_id){//boardType_id 로 가져오기 
		/*if(page==null) page="1";
		int curPage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curPage)-(rowSize-1);
		int end=rowSize*curPage;*/
		
		List<BoardVO> list=dao.boardList(boardType_id);
		
		daily_list=service.Daily_ChartData();	// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);							// 상단 차트순위 때문에 추가 (7/6 오석규)
	
		model.addAttribute("list",list);
		//model.addAttribute("curPage", curPage);
		model.addAttribute("main_jsp", "board/board_list.jsp");
			
		return "main/main";
		
		
	}
	@RequestMapping("main/board_content.do")
	public String board_content(int board_id,Model model){
		
		BoardVO vo = dao.boardContent(board_id);
		
		System.out.println(vo.getBoard_title());
		
		daily_list=service.Daily_ChartData();				// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);		// 상단 차트순위 때문에 추가 (7/6 오석규)
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp","board/board_content.jsp");
		
		return "main/main";
		
		
	}

	
	@RequestMapping("main/board_update.do")
	public String board_update(Model model,int board_id){
		//dao.boardUpdate(board_id);
BoardVO vo = dao.boardContent(board_id);
		
		System.out.println(vo.getBoard_title());
		
		daily_list=service.Daily_ChartData();					// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);			// 상단 차트순위 때문에 추가 (7/6 오석규)
		
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "board/board_update.jsp");
		
		
		return "main/main";
		
	}
	@RequestMapping("main/board_update_ok.do")
	@ResponseBody
	public String board_update_ok(BoardVO vo){
		
		
		return "";
	}
	

	@RequestMapping("main/board_insert.do")
	public String board_insert(Model model){
		
		daily_list=service.Daily_ChartData();					// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);			// 상단 차트순위 때문에 추가 (7/6 오석규)
		
		model.addAttribute("main_jsp", "board/board_insert.jsp");
		return "main/main";
		
	}
	//insert,update(board_content),delete(board_list)
	@RequestMapping("main/board_insert_ok.do")
	public String board_insert_ok(BoardVO vo){
		System.out.println(vo.getBoardType_id());
		List<MultipartFile> list=vo.getUpload();
		if(list==null){ //업로드 안된거
			vo.setBoard_filename("");
			vo.setBoard_filesize("");
			vo.setBoard_filecount(0);
		}else{ //업로드 된거
			String strName="";
			String strSize="";
			for(MultipartFile mf:list){
				try{
					String filename=mf.getOriginalFilename();
					File file=new File("c:\\melon1\\"+filename);
					mf.transferTo(file);
					int size=(int)file.length();
					
					strName+=filename+",";
					strSize+=size+",";
				}catch(Exception ex){
					
				}
			}
			
			vo.setBoard_filename(strName.substring(0, strName.lastIndexOf(",")));
			vo.setBoard_filesize(strSize.substring(0, strSize.lastIndexOf(",")));
			vo.setBoard_filecount(list.size());
			
		}
		
		
		dao.boardInsert(vo); //업로드 후 디비저장
		
		return "redirect:/main/board_list.do"; //sendRedirect 
	}
	
	

		
}
