package kr.or.ddit.comments.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.comments.dao.ICommentsDao;
import kr.or.ddit.comments.model.CommentsVO;

@Service
public class CommentsService implements ICommentsService {

	@Resource(name="commentsDao")
	private ICommentsDao commentsDao;
	
	@Override
	public List<CommentsVO> selectCommentsDetail(String ps_id) {
		List<CommentsVO> commentsVOs = commentsDao.selectCommentsDetail(ps_id);
		return commentsVOs;
	}

	@Override
	public int insertComments(CommentsVO commentsVO) {
		int insertCnt = commentsDao.insertComments(commentsVO);
		return insertCnt;
	}

	@Override
	public int deleteComments(String cm_id) {
		int deleteCnt = commentsDao.deleteComments(cm_id);
		return deleteCnt;
	}

}
