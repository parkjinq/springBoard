package kr.or.ddit.posts.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.posts.model.PostsVO;

@Repository
public class PostsDao implements IPostsDao {
	
	private Logger logger = LoggerFactory.getLogger(PostsDao.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<PostsVO> selectPostsPageList(Map<String, Object> params) {
		List<PostsVO> postsVOs = template.selectList("posts.selectPostsPageList", params);
		return postsVOs;
	}

	@Override
	public int getPostsAllCount(Map<String, Object> params) {
		logger.debug("params : {}", params);
		int postsCnt = template.selectOne("posts.getPostsAllCount", params);
		return postsCnt;
	}

	@Override
	public String insertPosts_new(PostsVO postsVO) {
		int insertCnt = template.insert("posts.insertPosts_new", postsVO);
		logger.debug("insertPosts_new : {}", insertCnt);
		logger.debug("postsVO.getPs_id() : {}", postsVO.getPs_id());
		String str = "00" + postsVO.getPs_id();
		str = str.substring(postsVO.getPs_id().length() - 1);
		return "ps" + str;
	}

	@Override
	public String insertPosts_re(PostsVO postsVO) {
		int insertCnt = template.insert("posts.insertPosts_re", postsVO);
		logger.debug("insertPosts_re : {}", insertCnt);
		String str = "00" + postsVO.getPs_id();
		str = str.substring(postsVO.getPs_id().length() - 1);
		return "ps" + str;
	}
	
	@Override
	public PostsVO selectPostsDetail(String ps_id) {
		PostsVO postsVO = template.selectOne("posts.selectPostsDetail", ps_id);
		return postsVO;
	}

	@Override
	public int deletePosts(String ps_id) {
		int deleteCnt = template.update("posts.deletePosts", ps_id);
		return deleteCnt;
	}

	@Override
	public int updatePosts(PostsVO postsVO) {
		int updateCnt = template.update("posts.updatePosts", postsVO);
		return updateCnt;
	}

}
