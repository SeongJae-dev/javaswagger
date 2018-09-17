<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="node_modules/jquery.fakeloader/dist/jquery.fakeloader.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<!--��Ʈ ��Ʈ�� CDN  -->

<title>Ajax �ε��� �����ϱ�</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script><!-- �׻� �ֽŹ����� JQuery�� ��밡���ϴ�. -->
 
<script type="text/javascript" language="javascript">
 
$(document).ready(function(){
	
   $('#Progress_Loading').hide(); //ù ���۽� �ε��ٸ� �����ش�.
})
.ajaxStart(function(){
	$('#Progress_Loading').show(); //ajax����� �ε��ٸ� �����ش�.
})
.ajaxStop(function(){
	$('#Progress_Loading').hide(); //ajax����� �ε��ٸ� �����ش�.
});
 
$(document).ready(function(){
	
    $.ajax({
        type : "GET", //���۹���� �����Ѵ� (POST,GET)
        url : "data/bigData",//ȣ�� URL�� �����Ѵ�. GET����ϰ�� �ڿ� �Ķ�Ƽ�͸� �ٿ��� ����ص��ȴ�.
        dataType : "text",//ȣ���� �������� �����̴�. xml,json,html,text���� ���� ����� ����� �� �ִ�.
        error : function(){
            alert('��Ž���!!');
        },
        success : function(Parse_data){
        	
            $("#Parse_Area").html(Parse_data); //div�� �޾ƿ� ���� �ִ´�.
            alert("��ŵ����� �� : " + Parse_data);
        }
         
    });
}); 
 
</script>

<div id ="Progress_Loading"><!-- �ε��� -->
<img src="resources/image/loading1.gif"/>
</div>

<style type = "text/css"> <!-- �ε��ٽ�Ÿ�� -->
body
{
 text-align: center;
 margin: 0 auto;
}
#Progress_Loading
{
 position: absolute;
 left: 50%;
 top: 50%;
 background: #ffffff;
}
</style>
    <div id="Parse_Area"></div> <!--�޾ƿ� �����͵��� �� div -->
<body>
 
</head>

</html>


<!-- 


 -->