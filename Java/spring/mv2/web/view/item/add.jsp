<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<style>
   #main_center {
      margin: 0 20px;
      width: 760px;
      height: 480px;
      background: white;
   }
</style>
<script>
$(document).ready(function(){
	$('#r_bt').click(function(){
		var c=confirm('등록하시겠습니까?');
		if(c==true){
			$('#item_register').attr({
				'method':'post',
				'action':'itemaddimpl.do'
			});
			$('#item_register').submit();
		}
	});
});
//바디와 바디사이가 화면에 호출된다음 펑션호출

</script>
<div id="main_center">
   <h1>Item Add</h1>
   <form id="item_register">
   ID<input type="text" name="id" id="id"><br>
   NAME<input type="text" name="name" id="name"><br>
   PRICE<input type="text" name="price" id="price"><br>
   <input type="button" value="REGISTER" id="r_bt">
   </form>
</div>

