package kr.or.ddit.attachments.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class AttachmentsDaoTest extends ServiceDaoTestConfig {

	@Resource(name="attachmentsDao")
	IAttachmentsDao attachmentsDao;
	
	@Test
	public void insertAttachmentsTest() {
		/***Given***/
		AttachmentsVO attachmentsVO = new AttachmentsVO();
		attachmentsVO.setPs_id("ps001");
		attachmentsVO.setAtt_path("insertTest");
		attachmentsVO.setAtt_originname("insertTest.jpg");
	
		/***When***/
		int insertCnt = attachmentsDao.insertAttachments(attachmentsVO);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void selectAttachmentsDetailTest() {
		/***Given***/
		String ps_id = "ps001";
		
		/***When***/
		List<AttachmentsVO> attachmentsVOs = attachmentsDao.selectAttachmentsDetail(ps_id);
		
		/***Then***/
		assertEquals(3, attachmentsVOs.size());
	}
	
	@Test
	public void deleteAttachmentsTest() {
		/***Given***/
		String str = "att001";
		
		/***When***/
		int deleteCnt = attachmentsDao.deleteAttachments(str);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
