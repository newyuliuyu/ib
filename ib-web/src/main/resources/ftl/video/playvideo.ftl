<!DOCTYPE html>
<html lang="en">
<head>
    <title>发送成绩通知</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" sizes="32x32" href="../resources/images/favicon.png">
    <style type="text/css">

    </style>
</head>

<body style="background-color: #1b1e21;">
<div style="text-align: center;padding: 10%;">
<#if video??>
    <video src="${video.videoUrl}" width="500" height="400" controls="controls" controlsList="nodownload">
        改浏览器不支持视屏播放
    </video>
<#else>
<span style="color: white;font-size: 30px;">
没有找到视屏文件
</span>
</#if>
</div>
</body>
</html>
