package demo.com.test.controller;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.com.test.javaShell.CommandStreamGobbler;

@RestController
public class JavaShell {
	@RequestMapping("/java/shelltest")
	public void javashell(){
		InputStreamReader stdISR = null;  
        InputStreamReader errISR = null;  
        Process process = null; 
        String param1 = " 我是参数1 " ;
        String param2 = " 我是参数2 " ;
        
        String command = "/home/yanjiapan/javaShell/test.sh"+param1+param2;  
        try {  
            process = Runtime.getRuntime().exec(command);  
  
            CommandStreamGobbler errorGobbler = new CommandStreamGobbler(process.getErrorStream(), command, "ERR");  
            CommandStreamGobbler outputGobbler = new CommandStreamGobbler(process.getInputStream(), command, "STD");  
  
            errorGobbler.start();  
            // 必须先等待错误输出ready再建立标准输出  
            while (!errorGobbler.isReady()) {  
                Thread.sleep(10);  
            }  
            outputGobbler.start();  
            while (!outputGobbler.isReady()) {  
                Thread.sleep(10);  
            }  
  
            int exitValue = process.waitFor();  
        } catch (IOException | InterruptedException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (stdISR != null) {  
                    stdISR.close();  
                }  
                if (errISR != null) {  
                    errISR.close();  
                }  
                if (process != null) {  
                    process.destroy();  
                }  
            } catch (IOException e) {  
                System.out.println("正式执行命令：" + command + "有IO异常");  
            }  
        }  
	}
}
