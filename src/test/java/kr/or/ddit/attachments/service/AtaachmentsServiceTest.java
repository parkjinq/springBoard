package kr.or.ddit.attachments.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class AtaachmentsServiceTest extends ServiceDaoTestConfig {

	@Resource(name="attachmentsService")
	private IAttachmentsService attachmentsService;
	
	@Test
	public void insertAttachmentsTest() {
		/***Given***/
		List<AttachmentsVO> attachmentsVOs = new ArrayList<AttachmentsVO>();
		AttachmentsVO attachmentsVO1 = new AttachmentsVO();
		attachmentsVO1.setAtt_originname("insertTest1.jps");
		attachmentsVO1.setAtt_path("i-n-s-e-r-t-T-e-s-t-1.jpg");
		attachmentsVO1.setPs_id("ps001");
		AttachmentsVO attachmentsVO2 = new AttachmentsVO();
		attachmentsVO2.setAtt_originname("insertTest2.jps");
		attachmentsVO2.setAtt_path("i-n-s-e-r-t-T-e-s-t-2.jpg");
		attachmentsVO2.setPs_id("ps001");
		attachmentsVOs.add(attachmentsVO1);
		attachmentsVOs.add(attachmentsVO2);

		/***When***/
		int insertCnt = attachmentsService.insertAttachments(attachmentsVOs);

		/***Then***/
		assertEquals(2, insertCnt);
	}
	
	@Test
	public void selectAttachmentsDetailTest() {
		/***Given***/
		String ps_id = "ps001";
		
		/***When***/
		List<AttachmentsVO> attachmentsVOs = attachmentsService.selectAttachmentsDetail(ps_id);
		
		/***Then***/
		assertEquals(3, attachmentsVOs.size());
	}
	
	@Test
	public void deleteAttachmentsTest() {
		/***Given***/
		String[] temp = {"att001", "att002"};
		
		/***When***/
		int deleteCnt = attachmentsService.deleteAttachments(temp);
		
		/***Then***/
		assertEquals(2, deleteCnt);
	}

}
