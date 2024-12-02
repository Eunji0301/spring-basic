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
		logger.info("boardList ����");

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
		logger.info("boardWrite ����");

		String path = "WEB-INF/board/boardWrite";

		return path;
	}
	
	@RequestMapping(value = "boardWriteAction.aws")
	public String boardWriteAction(BoardVo bv, @RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("boardWriteAction ����");

		MultipartFile file = attachfile;
		String uploadedFileName = "";

		if (!file.getOriginalFilename().equals("")) {
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}

		String boardWriterType = bv.getBoardWriterType(); // P, D
		String ip = userIp.getUserIp(request);
		
		// pidx or didx ����
		if("P".equals(boardWriterType)) {
			String pidx = request.getSession().getAttribute("pidx").toString();
			int pidx_int = Integer.parseInt(pidx);
			bv.setPidx(pidx_int); // BoardVo�� pidx ����
			bv.setDidx(0);
		} else if("D".equals(boardWriterType)) {
			String didx = request.getSession().getAttribute("didx").toString();
			int didx_int = Integer.parseInt(didx);
			bv.setDidx(didx_int); // BoardVo�� didx ����
			bv.setPidx(0);
		}
	    
		bv.setUploadedFilename(uploadedFileName);
		bv.setBoardIp(ip);
		
		int value = boardService.boardInsert(bv);
		String path = "";
		if (value == 2) {
			path = "redirect:/board/boardList.aws";
		} else {
			rttr.addFlashAttribute("msg", "�Է��� �߸��Ǿ����ϴ�.");
			path = "redirect:/board/boardWrite.aws";
		}

		return path;
	}
	
	@RequestMapping(value = "boardContent.aws")
	public String boardContent(@RequestParam("bidx") int bidx, Model model) {
		logger.info("boardContent ����");

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
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); // Ȯ����
			MediaType mType = MediaUtils.getMediaType(formatName); // Ȯ���ڸ� ���� MediaUtils Ŭ������ ��� ���� Ȯ�������� �� �� �ְ�

			HttpHeaders headers = new HttpHeaders(); // HttpHeader ��ü ��� �� ����, ����� ������ ��� ��Ŷ ���·� ����

			in = new FileInputStream(uploadPath + fileName); // �ش�Ǵ� ��ġ�� ���� �о����

			if (mType != null) { // jpeg, gif, png�� �ش�Ǹ�
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
				headers.add("Content-Disposition", "attachment; filename=\"" + // ȭ�鿡 �Ѹ��� �ʰ� �ٿ�ε�
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
		System.out.println("boardModify ����");
		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);
		String path = "WEB-INF/board/boardModify";
		return path;
	}
	
	@RequestMapping(value = "boardModifyAction.aws")
	public String boardModifyAction(BoardVo bv, @RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("boardModifyAction ����");

		int value = 0;

		// ���� ���ε带 �ϰ� update�� ���� service�� �����.
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
		
		bv.setUploadedFilename(uploadedFileName); // filename �÷� ������ ��������
		bv.setBoardIp(ip);
		
		value = boardService.boardUpdate(bv);

		String path = "";
		if (value == 0) {
			rttr.addFlashAttribute("msg", "���� �������� �ʾҽ��ϴ�.");
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
		logger.info("boardReply ����");
		
		BoardVo bv = boardService.boardSelectOne(bidx);
		
		model.addAttribute("bv", bv);
		
		String path = "WEB-INF/board/boardReply";
		return path;
	}
}
