package kr.or.ddit.posts.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Commit;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.ServiceDaoTestConfig;
import kr.or.ddit.util.model.PageVO;

public class PostsServiceTest extends ServiceDaoTestConfig {

	@Resource(name="postsService")
	private IPostsService postsService;
	
	@Test
	public void selectPostsPageListTest() {
		/***Given***/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bd_id", "bd001");
		params.put("pageVO", new PageVO(1, 10));
		params.put("search_title", "");
		params.put("search_userid", "");
		
		/***When***/
		Map<String, Object> resultMap = postsService.selectPostsPageList(params);

		/***Then***/
		assertEquals(3, resultMap.size());
	}
	
	@Commit
	@Test
	public void insertPostsTest() {
		/***Given***/
		PostsVO postsVO = new PostsVO();
		postsVO.setBd_id("bd001");
		postsVO.setPs_title("게시글테스트_insertTest");
		postsVO.setPs_cnt("게시글내용_insert");
		postsVO.setUserid("parkjinq");
		postsVO.setPs_id2("");
		List<AttachmentsVO> attachmentsVOs = new ArrayList<AttachmentsVO>();
		AttachmentsVO attachmentsVO = new AttachmentsVO();
		attachmentsVO.setAtt_originname("test_insert.jpg");
		attachmentsVO.setAtt_path("t-e-s-t_insert.jpg");
		attachmentsVOs.add(attachmentsVO);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postsVO", postsVO);
		params.put("attachmentsVOs", attachmentsVOs);
		
		/***When***/
		int insertCnt = postsService.insertPosts(params);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void selectPostsDetailTest() {
		/***Given***/
		String ps_id = "ps001";
		
		/***When***/
		Map<String, Object> resultMap = postsService.selectPostsDetail(ps_id);
		
		/***Then***/
		assertEquals(3, resultMap.size());
	}
	@Test
	public void deletePostsTest() {
		/***Given***/
		String ps_id = "ps001";
		
		/***When***/
		int deleteCnt = postsService.deletePosts(ps_id);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	@Test
	public void updatePostsTest() {
		/***Given***/
		PostsVO postsVO = new PostsVO();
		postsVO.setPs_id("ps001");
		postsVO.setPs_title("게시글테스트_update");
		postsVO.setPs_cnt("게시글내용_update");
		List<AttachmentsVO> attachmentsVOs = new ArrayList<AttachmentsVO>();
		AttachmentsVO attachmentsVO = new AttachmentsVO();
		attachmentsVO.setPs_id("ps001");
		attachmentsVO.setAtt_originname("test_update.jpg");
		attachmentsVO.setAtt_path("t-e-s-t_update.jpg");
		attachmentsVOs.add(attachmentsVO);
		String temp = "att001;att002;";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postsVO", postsVO);
		params.put("attachmentsVOs", attachmentsVOs);
		params.put("temp", temp);
		
		/***When***/
		int updateCnt = postsService.updatePosts(params);
		
		/***Then***/
		assertEquals(4, updateCnt);
	}
	
}
