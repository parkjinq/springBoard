package kr.or.ddit.attachments.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.attachments.dao.IAttachmentsDao;
import kr.or.ddit.attachments.model.AttachmentsVO;

@Service
public class AttachmentsService implements IAttachmentsService {

	private Logger logger = LoggerFactory.getLogger(AttachmentsService.class);
	
	@Resource(name="attachmentsDao")
	private IAttachmentsDao attachmentsDao;
	
	@Override
	public int insertAttachments(List<AttachmentsVO> attachmentsVOs) {
		int insertCnt = 0;
		logger.debug("[AttachmentsService] attachmentsVOs.size() : {}", attachmentsVOs.size());
		for(AttachmentsVO attachmentsVO : attachmentsVOs) {
			logger.debug("[AttachmentsService] attachmentsVOs : {}", attachmentsVOs);
			insertCnt += attachmentsDao.insertAttachments(attachmentsVO);
		}
		return insertCnt;
	}

	@Override
	public List<AttachmentsVO> selectAttachmentsDetail(String ps_id) {
		List<AttachmentsVO> attachmentsVOs = attachmentsDao.selectAttachmentsDetail(ps_id);
		return attachmentsVOs;
	}

	@Override
	public int deleteAttachments(String[] temp) {
		int deleteCnt = 0;
		for(String str : temp) {
			deleteCnt += attachmentsDao.deleteAttachments(str);
		}
		return deleteCnt;
	}

}