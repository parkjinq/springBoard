package kr.or.ddit.posts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.attachments.service.IAttachmentsService;
import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.posts.dao.IPostsDao;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.BoardUtil;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.PageVO;

@Service
public class PostsService implements IPostsService {

	private Logger logger = LoggerFactory.getLogger(PostsService.class);

	@Resource(name = "postsDao")
	private IPostsDao postsDao;

	@Resource(name = "attachmentsService")
	private IAttachmentsService attachmentsService;

	@Resource(name = "commentsService")
	private ICommentsService commentsService;

	@Override
	public Map<String, Object> selectPostsPageList(Map<String, Object> params) {

		int postsCnt = postsDao.getPostsAllCount(params);
		PageVO pageVO = (PageVO) params.get("pageVO");
		List<PostsVO> postsVOs = postsDao.selectPostsPageList(params);

		int pageCnt = BoardUtil.postsPageCnt(postsCnt, pageVO);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postsVOs", postsVOs);
		resultMap.put("pageCnt", pageCnt);
		resultMap.put("pageVO", pageVO);

		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insertPosts(Map<String, Object> params) {
		PostsVO postsVO = (PostsVO) params.get("postsVO");
		List<AttachmentsVO> attachmentsVOs = (List<AttachmentsVO>) params.get("attachmentsVOs");
		String ps_id = "".equals(postsVO.getPs_id2()) || postsVO.getPs_id2() == null  ? postsDao.insertPosts_new(postsVO) : postsDao.insertPosts_re(postsVO);
		logger.debug("[PostsService] (insertPosts)ps_id : {}", ps_id);

		int insertCnt = 0;
		for (AttachmentsVO attachmentsVO : attachmentsVOs) {
			attachmentsVO.setPs_id(ps_id);
			logger.debug("attachmentsVO : {}", attachmentsVO);
		}
		insertCnt = attachmentsService.insertAttachments(attachmentsVOs);
		return insertCnt;
	}

	@Override
	public Map<String, Object> selectPostsDetail(String ps_id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		PostsVO postsVO = postsDao.selectPostsDetail(ps_id);
		List<AttachmentsVO> attachmentsVOs = attachmentsService.selectAttachmentsDetail(ps_id);
		List<CommentsVO> commentsVOs = commentsService.selectCommentsDetail(ps_id);

		resultMap.put("postsVO", postsVO);
		resultMap.put("attachmentsVOs", attachmentsVOs);
		resultMap.put("commentsVOs", commentsVOs);

		return resultMap;
	}

	@Override
	public int deletePosts(String ps_id) {
		int deleteCnt = postsDao.deletePosts(ps_id);
		return deleteCnt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updatePosts(Map<String, Object> params) {
		PostsVO postsVO = (PostsVO) params.get("postsVO");
		List<AttachmentsVO> attachmentsVOs = (List<AttachmentsVO>) params.get("attachmentsVOs");
		int updateCnt = postsDao.updatePosts(postsVO);
		// 첨부파일 삭제
		String[] temp = FileUtil.tempUtil((String) params.get("temp"));
		updateCnt += attachmentsService.deleteAttachments(temp);
		// 첨부파일 추가
		for (AttachmentsVO attachmentsVO : attachmentsVOs) {
			attachmentsVO.setPs_id(postsVO.getPs_id());
		}
		updateCnt += attachmentsService.insertAttachments(attachmentsVOs);

		return updateCnt;
	}
}
