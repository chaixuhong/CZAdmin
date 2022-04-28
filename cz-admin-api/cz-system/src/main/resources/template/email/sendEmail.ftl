<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        .body {
            font-family: SimSun, Microsoft YaHei, Times New Roman, Verdana, Arial, Helvetica, sans-serif;
            color: #000;
        }

        .container {
            height: auto;
            width: 820px;
            margin: 0 auto;
            margin-top: 20px;
            border: 1px solid #eee;
        }

        .container .container_box {
            padding: 10px 10px 0 10px;
        }

        .footer {
            margin: 0 auto;
            width: 800px;
            margin-top: 30px;
            border-top: 1px solid #DA251D;
        }

        .footer_text {
            text-align: center;
            font-size: 12px;
            padding: 20px;
        }

        .preview_box img {
            width: 100%;
        }

        .preview_box p {
            margin: 10px 0;
        }

        .preview_box p,
        .preview_box li {
            white-space: pre-wrap; /* 保留空格 */
        }

        .preview_box blockquote {
            border-left: 8px solid #d0e5f2;
            padding: 10px 10px;
            margin: 10px 0;
            background-color: #f1f1f1;
        }

        .preview_box code {
            font-family: monospace;
            background-color: #eee;
            padding: 3px;
            border-radius: 3px;
        }

        .preview_box pre > code {
            display: block;
            padding: 10px;
        }

        .preview_box table {
            border-collapse: collapse;
        }

        .preview_box table tr {
            box-sizing: border-box;
            margin: 0;
            outline: none;
            padding: 0;
        }

        .preview_box td,
        .preview_box th {
            border: 1px solid #ccc;
            min-width: 50px;
            height: 20px;
            line-height: 1.5;
            padding: 3px 5px;
        }

        .preview_box th {
            background-color: #f5f2f0;
        }

        .preview_box ul,
        .preview_box ol {
            padding-left: 20px;
        }

        .preview_box input[type='checkbox'] {
            margin-right: 5px;
        }
    </style>
</head>
<body class="body">
<div class="container">
    <div class="container_box">
        <div class="preview_box">${content}</div>
        <div class="footer"></div>
        <div class="footer_text">
            Copyright &copy;${.now?string("yyyy")} CZ-Admin 后台管理系统 All Rights Reserved.
        </div>
    </div>
</div>
</body>
</html>
