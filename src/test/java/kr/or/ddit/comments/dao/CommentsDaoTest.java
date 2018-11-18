package kr.or.ddit.comments.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class CommentsDaoTest extends ServiceDaoTestConfig {

	@Resource(name="commentsDao")
	private ICommentsDao commentsDao;
	
	@Test
	public void selectCommentsDetailTest() {
		/***Given***/
		String ps_id = "ps001";

		/***When***/
		List<CommentsVO> commentsVOs = commentsDao.selectCommentsDetail(ps_id);

		/***Then***/
		assertEquals(4, commentsVOs.size());
	}
	
	@Test
	public void insertCommentsTest() {
		/***Given***/
		CommentsVO commentsVO = new CommentsVO();
		commentsVO.setPs_id("ps001");
		commentsVO.setUserid("parkjinq");
		commentsVO.setCm_cnt("댓글내용");
		
		/***When***/
		int insertCnt = commentsDao.insertComments(commentsVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteCommentsTest() {
		/***Given***/
		String cm_id = "cm001";
		
		/***When***/
		int deleteCnt = commentsDao.deleteComments(cm_id);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
