<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
.kyuseung {
	width: 200px;
	height: 200px
}

.fileListPJ {
	display: flex
}
</style>
<!-- 다음 주소 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- jquery ui api -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- jquery ui css -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="/SE2/js/HuskyEZCreator.js"></script>

<script>
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

	$(document).ready(
			function() {
				// Editor Setting
				nhn.husky.EZCreator.createInIFrame({
					oAppRef : oEditors, // 전역변수 명과 동일해야 함.
					elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
					sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
					fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
					htParams : {
						// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseToolbar : true,
						// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,
						// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,
					}
				});

				// 전송버튼 클릭이벤트
				$("#savebutton").click(
						function() {
							if (confirm("저장하시겠습니까?")) {
								// id가 smarteditor인 textarea에 에디터에서 대입
								oEditors.getById["smarteditor"].exec(
										"UPDATE_CONTENTS_FIELD", []);

								// 이부분에 에디터 validation 검증
								if (validation()) {
									$("#frm").submit();
								}
							}
						})

				//추가가능한 첨부파일 개수 
				var index = 4;

				$(".addFile").on("click", function() {
					//기존 첨부파일 개수를 제외하고 input file타입 생성
					//클론으로 복사하고
					//파일 id name
					//버튼 아이디 네임 수정, 버큰은 del버튼으로 변경

					if (index > 0) {
						var tr = document.createElement('tr');
						tr.id = "att" + index;

						var td1 = document.createElement('td');
						var td2 = document.createElement('td');
						var div = document.createElement('div');
						div.className = "fileListPJ";

						var file = document.createElement('input')
						file.type = "file";
						// 				file.name = "file" + index;
						// 				file.id = "file" + index;
						// 				console.log("file" + index);
						for (var i = 1; i <= index; i++) {
							var id = "#file" + i;
							if ($(id).length > 0) {

							} else {
								console.log("사용 가능한 아이디 : file" + i);
// 								file.name = "file" + i;
								file.name = "file";
								file.id = "file" + i;
							}
						}

						// 				for(var i = 1; i <= index; i++){
						// 					var id = "#file" + i;
						// 					if(document.getElementById('id')){
						// 					if($('id').length > 0){

						// 					} else {
						// 						console.log("사용 가능한 아이디 : file" + i );
						// 						file.name = "file" + i;
						// 						file.id = "file" + i;
						// 					}
						// 				}

						var del = document.createElement('input')
						del.type = "button";
						del.className = "btn btn-default delFile" + index;
						del.id = "delFile" + index;
						del.value = "-";
						del.onclick = function() {
							delFile(tr)
						}

						div.appendChild(file);
						div.appendChild(del);
						td2.appendChild(div);

						tr.appendChild(td1);
						tr.appendChild(td2);

						$(this).parent().parent().parent().after(tr);

						index--;
					}

					console.log("추가 후 추가 가능한 첨부파일 수index : " + index);
				});

				//첨부파일 삭제
				function delFile(tr) {
					tr.remove();
					index++;
					console.log("삭제 후 추가 가능한 첨부파일 수index : " + index);
				}
			});

	// 필수값 Check
	function validation() {
		var contents = $.trim(oEditors[0].getContents());
		if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	}
</script>

<form id="frm" action="/posts/postsInsert" method="post"
	class="form-horizontal" role="form" enctype="multipart/form-data">
	<input type="hidden" name="bd_id" value="${bd_id}"> <input
		type="hidden" name="ps_id2" value="${ps_id2}"> <input
		type="hidden" name="groupid" value="${groupid}">
		<input type="hidden" name="userid" value="${loginUserVO.userId}">
	<table class="table table-striped" id="tableTag">
		<tr>
			<th>제목</th>
			<td><input type="text" id="ps_title" name="ps_title"></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>
				<!-- =============================================================================== -->

				<%-- 								<%@ include file="/SE2/index.jsp" %> --%> <textarea
					name="ps_cnt" id="smarteditor" rows="10" cols="100"
					style="width: 766px; height: 412px;"></textarea> <!-- =============================================================================== -->
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<div class="fileListPJ">
					<input multiple="multiple" type="file" id="file5" name="file" size="40px"> <input
						type="button" value="+" class="btn btn-default addFile">
				</div>
<!-- 				<div class="fileListPJ"> -->
<!-- 					<input type="file" id="file5" name="file5" size="40px"> <input -->
<!-- 						type="button" value="+" class="btn btn-default addFile"> -->
<!-- 				</div> -->
			</td>
		</tr>
	</table>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="button" id="savebutton" class="btn btn-default">저장</button>
			<button type="reset" class="btn btn-default">취소</button>
		</div>
	</div>
</form>

