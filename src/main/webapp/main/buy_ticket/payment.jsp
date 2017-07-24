<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결제</title>
<script type="text/javascript">
$(document).ready(function() {
	$('#sel1').change(function() {
		//alert($(this).val());
		
		if($(this).val() == "account") {
			$('#account').show();
			$('#deposit').hide();
			$('#card').hide();
		} else if($(this).val() == "deposit") {
			$('#account').hide();
			$('#card').hide();
		} else if($(this).val() == "card") {
			$('#account').hide();
			$('#deposit').hide();
			$('#card').show();
		}
		
	});
	
	$('#sub1').click(function() {
		//alert("click");
		
		var ch = $.checkForm();
		
		if(ch == false) {
			alert("fail");
			return;
		}
		else {
			alert("submit");
			$('#form1').submit();			
		}
	});
	$('#sub2').click(function() {
		//alert("click");
		
		var ch = $.checkForm();
		
		if(ch == false) {
			alert("fail");
			return;
		} 
		else {
			alert("submit");
			$('#form1').submit();			
		}
	});
	$('#sub3').click(function() {
		//alert("click");
		
		var ch = $.checkForm();
		
		if(ch == false) {
			alert("fail");
			return;
		} 
		else {
			alert("submit");
			$('#form1').submit();			
		}
	});

	
	$.checkForm = function() {
		//alert("call");
		if($('#deposit').css("display") != "none") {
			alert("depositclick");
			if($('#sel3').val() == 'st3') {
				alert("은행을 선택해 주세요");
				$('#sel3').focus();
				return false;
			} else if($('#musr').val() == '') {
				alert("예금주를 입력하세요.");
				$('#musr').focus();
				return false;
			}
		} else if($('#account').css("display") != "none"){
			alert("accountclick");
			if($('#sel2').val() == "st2") {
				alert("은행을 선택해 주세요");
				$('#sel2').focus();
				return false;
			} else if($('#num1').val() == '') {
				alert("첫번째 번호를 입력하세요.");
				$('#num1').focus();
				return false;
			} else if($('#num2').val() == '') {
				alert("두번째 번호를 입력하세요.");
				$('#num2').focus();
				return false;
			} else if($('#num3').val() == '') {
				alert("세번째 번호를 입력하세요.");
				$('#num3').focus();
				return false;
			} else if($('#pwd').val() == '') {
				alert("비밀번호를 입력하세요.");
				$('#num4').focus();
				return false;
			} else if($('#usr').val() == '') {
				alert("예금주를 입력하세요.");
				$('#usr').focus();
				return false;
			}
		} else if ($('#card').css("display") != "none") {
			alert("cardclick");
			if($('#n1').val() == '') {
				alert("첫번째 번호를 입력하세요.");
				$('#n1').focus();
				return false;
			} else if($('#n2').val() == '') {
				alert("두번째 번호를 입력하세요.");
				$('#n2').focus();
				return false;
			} else if($('#n3').val() == '') {
				alert("세번째 번호를 입력하세요.");
				$('#n3').focus();
				return false;
			} else if($('#n4').val() == '') {
				alert("네번째 번호를 입력하세요.");
				$('#n4').focus();
				return false;
			} else if($('#e1').val() == '') {
				alert("유효기간의 월을 입력하세요.");
				$('#e1').focus();
				return false;
			} else if($('#e2').val() == '') {
				alert("유효기간의 년을 입력하세요.");
				$('#e2').focus();
				return false;
			} else if($('#cpwd').val() == '') {
				alert("비밀번호를 입력하세요.");
				$('#cpwd').focus();
				return false;
			} else if($('#csv').val() == '') {
				alert("csv 번호를 입력하세요.");
				$('#csv').focus();
				return false;
			}
		}
	};
	
	
	
});

</script>
</head>
<body>
	<section id="contentSection" style="margin: auto;">
	<div class="container" style="width: 90%;margin: auto;">
		<h2 align="center">결제 선택</h2><br>
		<!-- 내가 선택한 이용권 출력하기 -->
		<div class="row" style="width: 80%;margin: auto;">
        	<div class="span12">
    			<ul class="thumbnails" style="list-style:none;">
                	<li class="span5 clearfix">
                  		<div class="thumbnail clearfix">
                    		<img src="images/${name }.png" class="pull-left span2 clearfix" style="margin-top:10px;float: left;" width="200px">
                    		<div class="caption" class="pull-left" style="float: left;margin-left: 40px;margin-top: -20px">
                     			<h3 style="color: #2b2b2b">${name }</h3>
                      			<b>가격: ${price} 원</b>
                    		</div>
                  		</div>
                	</li>  
            	</ul>
       	 	</div>	
        </div>	
		<div style="margin: auto;text-align: center;">해당 이용권을 결제할 수단을 선택해주세요.</div>
		<form method="post" action="payment_ok.do" id="form1">
			<input type="hidden" value="${id}" name="id">
			<input type="hidden" value="${type}" name="type">
			<div class="form-group" style="width:80%;margin: auto;">
				<!-- <label for="sel1">Select list (select one):</label> -->
				<select class="form-control" id="sel1">
					<option value='st1'>-----선택-----</option>
	        		<option value="deposit">무통장 입금</option>
					<option value="account">실시간 계좌이체</option>
	        		<option value="card">카드 결제</option>
	      		</select>
	   		</div>
	   		<hr>
	   		
	   		<div id="account" style="display: none;width:80%;margin: auto;" class="form-group"> 
				<p>실시간 계좌이체</p>
				<select class="form-control" id="sel2">
		   			<option value='st2'>---선택---</option>
					<option value='SC제일은행'>SC제일</option>
			       	<option value='국민은행'>국민</option>
			       	<option value='NH'>NH</option>
			       	<option value='신한은행'>신한</option>
			       	<option value='우리은행'>우리</option>
			      	<option value='우체국'>우체국</option>
			       	<option value='하나은행'>하나</option>
		       	</select>
		       	<br>
		       	<div class="form-group row">
     				<div class="col-xs-2">
     					<label for="ex1">계좌번호 입력</label>
        				<input class="form-control" id="num1" type="text" placeholder="첫 자리 번호 입력"> 
      				</div>
      				<div class="col-xs-2">
      					<label for="ex1">&nbsp;&nbsp;</label>
        				<input class="form-control" id="num2" type="text" placeholder="두번째 자리 번호 입력"> 
      				</div>
      				<div class="col-xs-2">
      					<label for="ex1">&nbsp;&nbsp;</label>
        				<input class="form-control" id="num3" type="text" placeholder="마지막 자리 번호 입력"> 
      				</div>
    			</div>
				<br>
				<div class="form-group">
  					<label for="pwd">계좌 비밀번호:</label>
  					<input type="password" class="form-control" id="pwd">
				</div>
				<br>
				<div class="form-group">
				  <label for="usr">예금주:</label>
				  <input type="text" class="form-control" id="usr">
				</div>
				<br>
	   			<button type="button" class="btn btn-default" style="background-color: #B5D7BB; color:white;" id="sub1">확인</button>
	   		</div>
	   		
	   		<div id="deposit" style="display: none;width:80%;margin: auto;" class="form-group">
	   			<p>무통장 입금</p>
	   			<select class="form-control" id="sel3">
		   			<option value='st3'>---선택---</option>
					<option value='국민'>국민은행 : 152568-45-962457</option>
			       	<option value='신한'>신한은행 : 312-475-513476</option>
			       	<option value='하나'>하나은행 : 166-216570-86429</option>
		       	</select>
		       	<br>
				<div class="form-group">
				  <label for="usr">예금주:</label>
				  <input type="text" class="form-control" id="musr">
				</div>
				<br>
	   			<button type="button" class="btn btn-default" style="background-color: #B5D7BB; color:white;" id="sub2">확인</button>
	   		</div>
	   		
	   		<div id="card" style="display: none;width:80%;margin: auto;" class="form-group">
	   			<p>카드 결제</p>
	   			<div class="form-group row">
     				<div class="col-xs-2">
     					<label for="ex1">카드 번호 입력</label>
        				<input class="form-control" id="n1" type="text" placeholder="첫번째 번호 입력"> 
      				</div>
      				<div class="col-xs-2">
      					<label for="ex1">&nbsp;&nbsp;</label>
        				<input class="form-control" id="n2" type="text" placeholder="두번째 번호 입력"> 
      				</div>
      				<div class="col-xs-2">
      					<label for="ex1">&nbsp;&nbsp;</label>
        				<input class="form-control" id="n3" type="text" placeholder="세번째 번호 입력"> 
      				</div>
      				<div class="col-xs-2">
      					<label for="ex1">&nbsp;&nbsp;</label>
        				<input class="form-control" id="n4" type="text" placeholder="마지막 번호 입력"> 
      				</div>
    			</div>
				<br>
				<div class="form-group row">
     				<div class="col-xs-2">
     					<label for="ex1">카드 유효기간</label>
        				<input class="form-control" id="e1" type="text" placeholder="월"> 
      				</div>
      				<div class="col-xs-2">
      					<label for="ex1">&nbsp;&nbsp;</label>
        				<input class="form-control" id="e2" type="text" placeholder="년"> 
      				</div>
    			</div>
    			<br>
				<div class="form-group">
  					<label for="pwd">결제 비밀번호:</label>
  					<input type="password" class="form-control" id="cpwd">
				</div>
				<br>
				<div class="form-group">
				  <label for="usr">csv 번호:</label>
				  <input type="text" class="form-control" id="csv">
				</div>
				<br>
	   			<button type="button" class="btn btn-default" style="background-color: #B5D7BB; color:white;" id="sub3">확인</button>
	   		</div>
	  	</form>
	</div>
	</section>
</body>
</html>