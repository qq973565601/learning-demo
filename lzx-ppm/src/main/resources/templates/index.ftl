<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <#--CSS-->
    <link type="text/css" rel="stylesheet" href="../lib/layui-v2.6.8/css/layui.css"/>
    <link type="text/css" rel="stylesheet" href="../css/index.css"/>
</head>
<body>
<div class="main">
    <input type="hidden" id="uploadFileName" name="uploadFileName"/>
    <input type="button" class="diy-button-upload" id="fileUploadButton" value="文件上传"/>
    <!--会显URL-->
    <p id="fileUploadUrl" class="diy-img-show"></p>
</div>
<#--JS-->
<script language="JavaScript" type="text/javascript" src="../lib/layui-v2.6.8/layui.js"></script>
<script language="JavaScript" type="text/javascript">
    // 预加载模块
    layui.use(['jquery','layer','upload'], function () {
        // 定义
        const $ = layui.jquery,
            layer = layui.layer,
            upload = layui.upload;

        // 文件上传
        upload.render({
            // 绑定元素
            elem: '#fileUploadButton',
            // 上传接口
            url: '../api/video-upload',
            // 设定文件域的字段名，注意与后台接受参数要一致
            field: 'file',
            // 指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
            accept: 'file',
            // 选择上传文件类型
            // acceptMime: 'image/*',
            // 允许上传的文件后缀
            // exts: 'jpg|png|jpeg',
            // 设置文件最大可允许上传的大小，单位 KB。不支持ie8/9
            // size: 1*1024,
            // 文件提交上传前的回调
            before: function(){
                // layer.msg提示，可有显示文字
                // layer.msg('上传中，请稍等...',{icon: 16,shade: 0.5,time: false});
                // layer.load提示，加载的风格，支持0-2
                layer.load(2, {shadeClose:false,shade: [0.5, '#000']});
            },
            done: function(res){
                // print
                console.info(res);
                // 关闭动画
                layer.closeAll();
                // 上传完毕回调
                if(res.code==0){
                    let upload_video_index=layer.alert(res.msg,{title:'',closeBtn:false,btn:['知道了']},function () {
                        // 回显URL
                        $("#fileUploadUrl").html(res.data.url);
                        // 关闭
                        layer.close(upload_video_index);
                    });
                }
                else{
                    // 消息
                    layer.alert(res.msg,{title:'',closeBtn:false,btn:['知道了']});
                }
            },
            error: function(upload){
                // 请求异常回调
                console.info(upload);
            }
        });
    });
</script>
</body>
</html>