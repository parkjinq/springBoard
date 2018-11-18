package kr.or.ddit.comments.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.comments.model.CommentsVO;

@Repository
public class CommentsDao implements ICommentsDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<CommentsVO> selectCommentsDetail(String ps_id) {
		List<CommentsVO> commentsVOs = template.selectList("comments.selectCommentsDetail", ps_id);
		return commentsVOs;
	}

	@Override
	public int insertComments(CommentsVO commentsVO) {
		int insertCnt = template.insert("comments.insertComments", commentsVO);
		return insertCnt;
	}

	@Override
	public int deleteComments(String cm_id) {
		int deleteCnt = template.update("comments.updateComments", cm_id);
		return deleteCnt;
	}

}
