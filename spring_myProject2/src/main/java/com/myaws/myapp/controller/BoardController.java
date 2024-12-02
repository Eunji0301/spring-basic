package com.myaws.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.PageMaker;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;
import com.myaws.myapp.util.MediaUtils;
import com.myaws.myapp.util.UploadFileUtiles;
import com.myaws.myapp.util.UserIp;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired(required = false)
	private BoardService boardService;

	@Autowired(required = false)
	private PageMaker pm;

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Autowired(required = false)
	private UserIp userIp;
	
	@RequestMapping(value = "boardList.aws")
	public String boardList(SearchCriteria scri, Model model) {
		logger.info("boardList 들어옴");

		int cnt = boardService.boardTotalCount(scri);
		pm.setScri(scri);
		pm.setTotalCount(cnt);

		ArrayList<BoardVo> blist = boardService.boardSelectAll(scri);
		model.addAttribute("blist", blist);
		model.addAttribute("pm", pm);

		String path = "WEB-INF/board/boardList";

		return path;
	}
	
	@RequestMapping(value = "boardWrite.aws")
	public String boardWrite(SearchCriteria scri, Model model) {
		logger.info("boardWrite 들어옴");

		String path = "WEB-INF/board/boardWrite";

		return path;
	}
	
	@RequestMapping(value = "boardWriteAction.aws")
	public String boardWriteAction(BoardVo bv, @RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("boardWriteAction 들어옴");

		MultipartFile file = attachfile;
		String uploadedFileName = "";

		if (!file.getOriginalFilename().equals("")) {
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}

		String boardWriterType = bv.getBoardWriterType(); // P, D
		String ip = userIp.getUserIp(request);
		
		// pidx or didx 설정
		if("P".equals(boardWriterType)) {
			String pidx = request.getSession().getAttribute("pidx").toString();
			int pidx_int = Integer.parseInt(pidx);
			bv.setPidx(pidx_int); // BoardVo에 pidx 설정
			bv.setDidx(0);
		} else if("D".equals(boardWriterType)) {
			String didx = request.getSession().getAttribute("didx").toString();
			int didx_int = Integer.parseInt(didx);
			bv.setDidx(didx_int); // BoardVo에 didx 설정
			bv.setPidx(0);
		}
	    
		bv.setUploadedFilename(uploadedFileName);
		bv.setBoardIp(ip);
		
		int value = boardService.boardInsert(bv);
		String path = "";
		if (value == 2) {
			path = "redirect:/board/boardList.aws";
		} else {
			rttr.addFlashAttribute("msg", "입력이 잘못되었습니다.");
			path = "redirect:/board/boardWrite.aws";
		}

		return path;
	}
	
	@RequestMapping(value = "boardContent.aws")
	public String boardContent(@RequestParam("bidx") int bidx, Model model) {
		logger.info("boardContent 들어옴");

		boardService.boardViewCntUpdate(bidx);

		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);

		String path = "WEB-INF/board/boardContent";
		return path;
	}
	
	@RequestMapping(value = "/displayFile.aws", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(@RequestParam("fileName") String fileName,
			@RequestParam(value = "down", defaultValue = "0") int down) {
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); // 확장자
			MediaType mType = MediaUtils.getMediaType(formatName); // 확장자를 꺼내 MediaUtils 클래스에 담아 무슨 확장자인지 알 수 있게

			HttpHeaders headers = new HttpHeaders(); // HttpHeader 객체 사용 후 생성, 헤더에 데이터 담아 패킷 형태로 보냄

			in = new FileInputStream(uploadPath + fileName); // 해당되는 위치의 파일 읽어들임

			if (mType != null) { // jpeg, gif, png에 해당되면
				if (down == 1) {
					fileName = fileName.substring(fileName.indexOf("_") + 1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					headers.add("Content-Disposition",
							"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

				} else {
					headers.setContentType(mType);
				}

			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + // 화면에 뿌리지 않고 다운로드
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	@ResponseBody
	@RequestMapping(value = "boardRecom.aws", method = RequestMethod.GET)
	public JSONObject boardRecom(@RequestParam("bidx") int bidx) {
		int value = boardService.boardRecomUpdate(bidx);

		JSONObject js = new JSONObject();
		js.put("recom", value);
		return js;
	}
	
	@RequestMapping(value = "boardModify.aws")
	public String boardModify(@RequestParam("bidx") int bidx, Model model) {
		System.out.println("boardModify 들어옴");
		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);
		String path = "WEB-INF/board/boardModify";
		return path;
	}
	
	@RequestMapping(value = "boardModifyAction.aws")
	public String boardModifyAction(BoardVo bv, @RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("boardModifyAction 들어옴");

		int value = 0;

		// 파일 업로드를 하고 update를 위한 service를 만든다.
		MultipartFile file = attachfile;
		String uploadedFileName = "";

		if (!file.getOriginalFilename().equals("")) {
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}
		
		
		String pidx = (String) request.getSession().getAttribute("pidx");
		String didx = (String) request.getSession().getAttribute("didx");
		
		
		if(pidx != null) {
			int pidx_int = Integer.parseInt(pidx);
			bv.setPidx(pidx_int);
		} else if(didx != null) {
			int didx_int = Integer.parseInt(didx);
			bv.setDidx(didx_int);
		}
		
		String ip = userIp.getUserIp(request);
		
		bv.setUploadedFilename(uploadedFileName); // filename 컬럼 값으로 넣으려고
		bv.setBoardIp(ip);
		
		value = boardService.boardUpdate(bv);

		String path = "";
		if (value == 0) {
			rttr.addFlashAttribute("msg", "글이 수정되지 않았습니다.");
			path = "redirect:/board/boardModify.aws?bidx=" + bv.getBidx();
		} else {
			path = "redirect:/board/boardContent.aws?bidx=" + bv.getBidx();
		}

		return path;
	}
	
	@RequestMapping(value = "boardDelete.aws")
	public String boardDelete(@RequestParam("bidx") int bidx, Model model) {
		model.addAttribute("bidx", bidx);
		String path = "WEB-INF/board/boardDelete";
		return path;
	}
	
	@RequestMapping(value = "boardDeleteAction.aws", method = RequestMethod.POST)
	public String boardDelete(@RequestParam("bidx") int bidx, @RequestParam("password") String password, HttpSession session) {
		Integer pidx = null;
		Integer didx = null;
		
		if(session.getAttribute("pidx") != null) {
			pidx = Integer.parseInt(session.getAttribute("pidx").toString());
		}
		if(session.getAttribute("didx") != null) {
			pidx = Integer.parseInt(session.getAttribute("didx").toString());
		}
		
		int value = boardService.boardDelete(bidx, pidx, didx, password);

		boardService.boardDelete(bidx, pidx, didx, password);
		String path = "redirect:/board/boardList.aws";

		if (value == 0) {
			path = "redirect:/board/boardDelete.aws?bidx=" + bidx;
		}
		return path;
	}
	
	@RequestMapping(value = "boardReply.aws")
	public String boardReply(@RequestParam("bidx") int bidx, Model model) {
		logger.info("boardReply 들어옴");
		
		BoardVo bv = boardService.boardSelectOne(bidx);
		
		model.addAttribute("bv", bv);
		
		String path = "WEB-INF/board/boardReply";
		return path;
	}
}
