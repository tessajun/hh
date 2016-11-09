$("#logoBtn").click(function(event) {
	window.location.href = serverAddr + "/html/index.html"
});

$("#myinfo").click(function(event) {
	window.location.href = serverAddr + "/html/auth/myinfo.html"
});

//load함수를 이용하여 core스크립트의 로딩이 완료된 후, 우편번호 서비스를 실행합니다.
daum.postcode.load(function(){
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.
		}
	})/* .open(); */
});



//div 동적 추가
function add_item() {
	// pre_set 에 있는 내용을 읽어와서 처리..
	var div = document.createElement('div');
	div.innerHTML = document.getElementById('pre_set').innerHTML;
	document.getElementById('field').appendChild(div);
}

function remove_item(obj) {
	// obj.parentNode 를 이용하여 삭제
	document.getElementById('field').removeChild(obj.parentNode);
}



//우편번호 API
function DaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullAddr = ''; // 최종 주소 변수
			var extraAddr = ''; // 조합형 주소 변수

			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				fullAddr = data.roadAddress;

			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				fullAddr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
			if (data.userSelectedType === 'R') {
				//법정동명이 있을 경우 추가한다.
				if (data.bname !== '') {
					extraAddr += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if (data.buildingName !== '') {
					extraAddr += (extraAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')'
						: '');
			}

			console.log(document.getElementById('address').value);
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
			document.getElementById('address').value = fullAddr;

			// 커서를 상세주소 필드로 이동한다.
			document.getElementById('address2').focus();
		}
	}).open();
}