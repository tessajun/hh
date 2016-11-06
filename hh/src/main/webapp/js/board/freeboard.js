$("#prevBtn").click(function(event) {
	pageNo--;
	ajaxFreeBoardList();
});

$("#nextBtn").click(function(event) {
	pageNo++;
	ajaxFreeBoardList();
});

// 글로벌 변수 = window 프로퍼티 
var pageNo = 1, /* window.pageNo */
    pageLength = 12; /* window.pageLength */

function ajaxFreeBoardList() {
	$.getJSON(serverAddr + "/freeboard/list.json", {"pageNo": pageNo, "length": pageLength}, function(obj) {
		var result = obj.jsonResult 
		console.log(result.data)
		if (result.state != "success") {
	    	 alert("서버에서 데이터를 가져오는데 실패했습니다.")
	    	 return
	    }
		
		
	    var template = Handlebars.compile($("#frTemplateText").html())	
	    $("#freeboardTable tbody").html(template(result.data))
	    
	    $(document.body).on('click', '.freeForm', function(event) {
		    window.location.href = serverAddr + "/html/board/freeboardForm.html?no=" + $(this).attr("data-no1")
	    })
	    
	    // 현재 페이지 번호를 span 태그에 출력한다.
	    pageNo = result.data.pageNo;
	    totalPage = result.data.totalPage;
	    $('#pageNo').text(pageNo);
	    
	    // 페이지 번호가 1이면 [이전] 버튼을 비활성화시킨다.
	    if (pageNo <= 1) {
	    	$('#prevBtn').attr('disabled', true);
	    } else {
	    	$('#prevBtn').removeAttr('disabled');
	    } 
	    
	    // 페이지 번호가 마지막 페이지라면 [다음] 버튼을 비활성화시킨다.
	    if (pageNo >= totalPage) {
	    	$('#nextBtn').attr('disabled', true);
	    } else {
	    	$('#nextBtn').removeAttr('disabled');
	    }
    })
}





