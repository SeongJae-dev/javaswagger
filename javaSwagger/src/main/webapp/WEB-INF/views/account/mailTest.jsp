<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Insert title here</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var time = 179;
            setInterval(function () {
                var m = time / 60;
                if (m >= 2) {
                    m = 2
                } else if (m >= 1) {
                    m = 1
                } else {
                    m = 0
                }

                var s = time % 60;
                $("#second").html(m + "��" + s + "��");
                time -= 1;
            }, 1000);

            setTimeout(function () {
                $("input").remove();
                $("<p></p>").html("�������� �ð� 3���� ��� �������ϴ�.").appendTo("body");
                $("#second").remove;
            }, 180000);
        });
    </script>
</head>
<body>
<h3>���� ����</h3>

<form action="confirm.do">
    �ڵ��ȣ �Է� : <input type="number" name="input">&nbsp;<span id="second"></span>
    <input type="hidden" name="code" value="${code }">
    <input type="submit" value="�ڵ��ȣ �����ϱ�">
</form>

</body>
</html>