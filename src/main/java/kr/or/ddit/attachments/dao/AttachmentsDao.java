package kr.or.ddit.attachments.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachments.model.AttachmentsVO;

@Repository
public class AttachmentsDao implements IAttachmentsDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public int insertAttachments(AttachmentsVO attachmentsVO) {
		int insertCnt = template.insert("attachments.insertAttachments", attachmentsVO);
		return insertCnt;
	}

	@Override
	public List<AttachmentsVO> selectAttachmentsDetail(String ps_id) {
		List<AttachmentsVO> attachmentsVOs = template.selectList("attachments.selectAttachmentsDetail", ps_id);
		return attachmentsVOs;
	}

	@Override
	public int deleteAttachments(String str) {
		int deleteCnt = template.delete("attachments.deleteAttachments", str);
		return deleteCnt;
	}

}



































