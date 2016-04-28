<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<script type="text/javascript">
var context_ = "${pageContext.request.contextPath}";

var urlGetData = "${requestScope.getData}";
var urlDoDelete = "${requestScope.doDelete}";
var urlDoSave = "${requestScope.doSave}";
var urlDoUpdate = "${requestScope.doUpdate}";
</script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
        .fitem div{
            width:160px;
        }        
    </style>
	<!-- 注意匹配名,详情见mvc-servlet.xml -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/adminRes/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/adminRes/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/adminRes/easyui/themes/color.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/adminRes/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/videoadmin/common/ext.js"></script>
    <link rel="stylesheet" type="text/css" src="${pageContext.request.contextPath}/js/videoadmin/common/ext.css"/>