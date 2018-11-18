package kr.or.ddit.posts.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.ServiceDaoTestConfig;
import kr.or.ddit.util.model.PageVO;

public class PostsDaoTest extends ServiceDaoTestConfig{

	private Logger logger = LoggerFactory.getLogger(PostsDaoTest.class);
	
	@Resource(name="postsDao")
	private IPostsDao postsDao;
	
	@Test
	public void selectPostsPageListTest() {
		/***Given***/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bd_id", "bd001");
		params.put("pageVO", new PageVO(1, 10));
		params.put("search_title", "");
		params.put("search_userid", "");

		
		/***When***/
		List<PostsVO> postsVOs = postsDao.selectPostsPageList(params);
		
		/***Then***/
		assertEquals(5, postsVOs.size());
	}
	
	@Test
	public void selectSearchPostsPageList() {
		/***Given***/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bd_id", "bd001");
		params.put("pageVO", new PageVO(1, 10));
		params.put("search_title", "글5");
		params.put("search_userid", "");

		/***When***/
		List<PostsVO> postsVOs = postsDao.selectPostsPageList(params);

		/***Then***/
		assertEquals(1, postsVOs.size());
	}
	
	@Test
	public void getPostsAllCountTest() {
		/***Given***/
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bd_id", "bd001");
		params.put("pageVO", new PageVO(1, 10));
		params.put("search_title", "");
		params.put("search_userid", "");

		/***When***/
		int postsCnt = postsDao.getPostsAllCount(params);

		/***Then***/
		assertEquals(5, postsCnt);
	}
	
	@Test
	public void insertPosts_new() {
		/***Given***/
		PostsVO postsVO = new PostsVO();
		postsVO.setBd_id("bd001");
		postsVO.setPs_title("게시글테스트");
		postsVO.setPs_cnt("게시글내용");
		postsVO.setPs_id2("ps001");
		postsVO.setUserid("parkjinq");

		/***When***/
		String ps_id = postsDao.insertPosts_new(postsVO);
		
		/***Then***/
		assertEquals("ps", ps_id.substring(0, 2));
	}
	
	@Test
	public void insertPosts_re() {
		/***Given***/
		PostsVO postsVO = new PostsVO();
		postsVO.setBd_id("bd001");
		postsVO.setPs_title("게시글테스트");
		postsVO.setPs_cnt("게시글내용");
		postsVO.setPs_id2("ps001");
		postsVO.setUserid("parkjinq");

		/***When***/
		String ps_id = postsDao.insertPosts_re(postsVO);
		
		/***Then***/
		assertEquals("ps", ps_id.substring(0, 2));
	}
	
	@Test
	public void selectPostsDetail() {
		/***Given***/
		String ps_id = "ps002";
		/***When***/
		PostsVO postsVO = postsDao.selectPostsDetail(ps_id);
		/***Then***/
		assertEquals("ps002", postsVO.getPs_id());
	}
	
	@Test
	public void deletePosts() {
		/***Given***/
		String ps_id = "ps001";
		
		/***When***/
		int deleteCnt = postsDao.deletePosts(ps_id);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void updatePosts() {
		/***Given***/
		PostsVO postsVO = new PostsVO();
		postsVO.setPs_id("ps001");
		postsVO.setPs_title("게시글수정테스트");
		postsVO.setPs_cnt("게시글수정내용");
		
		/***When***/
		int updateCnt = postsDao.updatePosts(postsVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}

}
