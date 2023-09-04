console.log("reply Module...");

var replyService = (function(){

	// 댓글 등록
	function add(reply, callback, error) {
		console.log("add reply.....");
		// JSON.stringify -> 직렬화(객체 -> 텍스트 형태로 변환)
		// ex) {name : "홍길동", ...} -> {"name" : "홍길동", ...}
		// .parse() -> 역직렬화(텍스트 형태 -> 객체)
		$.ajax({
			type : "post",
			url : "/replies/new",
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				if(callback) {
					callback(result);
				}
			},
			error : function(er) {
				if(error) {
					error(er);
				}
			}
		});
	}

	// 댓글 리스트 조회
	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;

		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data) {
				if(callback){
					//callback(data);	// 댓글 목록만 가져오는 경우
					callback(data.replyCnt, data.list);	// 댓글 숫자와 목록을 가져오는 경우
				}
			}).fail(function(err){
				if(error){
					error();
				}
			});		
	}
	
	// 댓글 삭제
	function remove(rno, replyer, callback, error){
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			data: JSON.stringify({rno: rno, replyer: replyer}),
			contentType: "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if(error){
					error(er);
				}
			}			
		});
	}
	
	// 댓글 수정
	function update(reply, callback, error) {
		console.log("RNO: " + reply.rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}	
		});
	}
	
	// 댓글 조회	
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json", function(result){
			if(callback){
				callback(result);
			}
		
		}).fail(function(xhr, status, err){
			if(error){
				error(err);
			}
		});
	}
	
	function displayTime(timeValue){
		var today = new Date();
		
		var gap = today.getTime() - timeValue;
		
		var dateObj = new Date(timeValue);
		var str = "";
		
		// 24시간이 지나지 않았으면 시간을 출력
		if(gap < (1000 * 60 * 60 * 24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
				':', (ss > 9 ? '' : '0') + ss ].join('');
		} else {	// 24시간이 지났으면 날짜를 출력
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [ (yy > 9 ? '' : '0') + yy, '/', (mm > 9 ? '' : '0') + mm,
				'/', (dd > 9 ? '' : '0') + dd ].join('');
		}	
	};
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	};
})();









