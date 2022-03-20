<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--errors配列がnullじゃない、つまり０以外の配列番号が存在する場合はエラーを表示する--%>>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <%--拡張for文で、error変数にerrors配列内の値を順番に代入し、すべてのerrorを表示する--%>>
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="title">タイトル</label><br />

<input type="text" name="title" value="${message.title}" />
<br /><br />

<label for="content">メッセージ</label><br />
<input type="text" name="content" value="${message.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>