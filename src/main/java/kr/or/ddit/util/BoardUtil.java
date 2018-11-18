package kr.or.ddit.util;

import kr.or.ddit.util.model.PageVO;

public class BoardUtil {
	
	public static int postsPageCnt(int postsCnt, PageVO pageVO) {
		
		int pageCnt = (int) (Math.ceil((double)postsCnt / pageVO.getPageSize()));
		
		return pageCnt;
	}
	
}
