<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>gt-node-sdk-demo</title>
    <style>
        body {
            margin: 50px 0;
            text-align: center;
            font-family: "PingFangSC-Regular", "Open Sans", Arial, "Hiragino Sans GB", "Microsoft YaHei", "STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
        }
        .inp {
            border: 1px solid #cccccc;
            border-radius: 2px;
            padding: 0 10px;
            width: 278px;
            height: 40px;
            font-size: 18px;
        }
        .btn {
            border: 1px solid #cccccc;
            border-radius: 2px;
            width: 100px;
            height: 40px;
            font-size: 16px;
            color: #666;
            cursor: pointer;
            background: white linear-gradient(180deg, #ffffff 0%, #f3f3f3 100%);
        }
        .btn:hover {
            background: white linear-gradient(0deg, #ffffff 0%, #f3f3f3 100%)
        }
        #captcha1,
        #captcha2 {
            width: 300px;
            display: inline-block;
        }
        .show {
            display: block;
        }
        .hide {
            display: none;
        }
        #notice1,
        #notice2 {
            color: red;
        }
        label {
            vertical-align: top;
            display: inline-block;
            width: 80px;
            text-align: right;
        }
        #wait1, #wait2 {
            text-align: left;
            color: #666;
            margin: 0;
        }
    </style>
</head>
<body>
<h1>极验验证SDKDemo</h1>
<hr>
		<form id=FORM1 name=FORM1 action="https://83.252.46.200:11491/servlet/NewB2cMerPayReqServlet" method="post" >
			<font face='Arial' size='4' color='white'>商户订单数据签名页面</font>
			<table width="98%"  border="1">
				<tr>
					<td width="9%">接口名称</td>
					<td width="91%"><INPUT ID="interfaceName" NAME="interfaceName" TYPE="text" value="ICBC_PERBANK_B2C" size="120" ></td>
				</tr>
				<tr>
					<td width="9%">接口版本号</td>
					<td width="91%"><INPUT ID="interfaceVersion" NAME="interfaceVersion" TYPE="text" value="1.0.0.11" size="120"></td>
				</tr>
				<tr>
					<td width="9%">接口数据</td>
					<td width="91%"><textarea ID="tranData" name="tranData" cols="120" rows="5"></textarea>
				</tr>
				<tr>
					<td width="9%">签名数据</td>
					<td width="91%"><INPUT ID="merSignMsg" NAME="merSignMsg" TYPE="text" size="120" value="">
				</tr>
				<tr>
					<td width="9%">证书数据</td>
					<td width="91%"><INPUT ID="merCert" NAME="merCert" TYPE="text" size="120" value="">
				</tr>
			</table>
			<table>
				<tr>
					<td><INPUT TYPE="submit" value=" 提 交 订 单 "></td>
					<td><INPUT  type="button" value=" 返 回 修 改 " onClick="self.history.back();"></td>
				</tr>
			</table>
		</form>

<!-- 注意，验证码本身是不需要 jquery 库，此处使用 jquery 仅为了在 demo 使用，减少代码量 -->
<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.js"></script>

<!-- 引入 gt.js，既可以使用其中提供的 initGeetest 初始化函数 -->
<script src="gt.js"></script>

<script>
    var handler1 = function (captchaObj) {
        $("#submit1").click(function (e) {
            var result = captchaObj.getValidate();
            if (!result) {
                $("#notice1").show();
                setTimeout(function () {
                    $("#notice1").hide();
                }, 2000);
                e.preventDefault();
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha1");
        captchaObj.onReady(function () {
            $("#wait1").hide();
        });
        
    };
    $.ajax({
        url: "/init?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 调用 initGeetest 初始化参数
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "float", // 产品形式，包括：float，popup
                width: "100%"
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handler1);
        }
    });
</script>

</body>
</html>