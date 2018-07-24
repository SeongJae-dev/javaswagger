package javaa.swagger.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import javaa.swagger.dao.PostDao;
import javaa.swagger.vo.PostVo;

@Controller
public class PostController {

	@Autowired
	PostDao dao;

	public void setDao(PostDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping("listPost.do")
	public ModelAndView listPost(@RequestParam(value="user_id") String user_id) {
		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("user_id", user_id);
		List<PostVo> list = null;
		mav.addObject("list", dao.readPost(map));
		return mav;
	}
	
	@RequestMapping("detailPost.do")
	public ModelAndView detailPost(@RequestParam(value="post_no") String post_no) {
		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("post_no", post_no);
		PostVo pv = dao.detailPost(map);
		mav.addObject("pv",pv);
		return mav;
	}
	
//	**** AJAX ��� �ʿ�� ���� MAV��� �޼��� ����� �� �༮ �ּ� Ǯ� ��� ****
//	@RequestMapping(value="listPost.do", produces="text/plain;charset=utf-8")
//	@ResponseBody
//	public String listPost(@RequestParam(value="user_id") String user_id) {
//		ArrayList<PostVo> list = new ArrayList<PostVo>();
//		
//		System.out.println(user_id);
//		HashMap map = new HashMap();
//		map.put("user_id",user_id);
//		List<PostVo> listt = dao.readPost(map);
//		for(PostVo p : listt) {
//			list.add(p);
//		}
//		String str = "";
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			str = mapper.writeValueAsString(list);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
//		return str;
//	}
//	
	
	
//	**** AJAX ��� �ʿ�� ���� MAV��� �޼��� ����� �� �༮ �ּ� Ǯ� ��� ****
//	@RequestMapping(value="listPost.do", produces="text/plain;charset=utf-8")
//	@ResponseBody
//	public String detailPost(@RequestParam(value="post_no") String post_no) {
//		PostVo pv;
//		HashMap map = new HashMap();
//		map.put("post_no",post_no);
//		pv = dao.detailPost(map);
//		String str = "";
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			str = mapper.writeValueAsString(pv);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
//		return str;
//	}
	
	
	
	@RequestMapping(value="/board/insertPost.do",method=RequestMethod.POST)
	public ModelAndView insertPost(PostVo pv, HttpServletRequest request) {
		MultipartFile multi = pv.getUploadFile();
		String path = request.getRealPath("resources/img");
		System.out.println(path);
		if(multi != null) {
			try {
				String fname = multi.getOriginalFilename();
				byte data[] = multi.getBytes();
				pv.setPost_fname(fname);
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		HashMap map = new HashMap();
		ModelAndView mav = new ModelAndView();
		map.put("pv", pv);
		mav.addObject("tof", dao.newPost(map)); // ��ȯ�� ��� ó���ұ��?
		return mav;
	}
	
	@RequestMapping(value="/board/insertPost.do",method=RequestMethod.GET)
	public void insertPostForm() {
		
	}
	
	@RequestMapping("deletePost.do")
	public ModelAndView deletePost(String post_no) {
		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("post_no", post_no);
		mav.addObject("tof", dao.deletePost(map));
		return mav;
	}
	
	@RequestMapping(value="updatePost.do", method=RequestMethod.POST)
	public ModelAndView updatePost(PostVo pv) {
		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("pv", pv);
		mav.addObject("tof", dao.updatePost(map));
		return mav;
	}
	
	@RequestMapping(value="updatePost.do",method=RequestMethod.GET)
	public ModelAndView updatePostForm(String post_no) {
		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("post_no", post_no);
		PostVo pv = dao.detailPost(map);
		mav.addObject("pv", pv);
		return mav;
	}
}
