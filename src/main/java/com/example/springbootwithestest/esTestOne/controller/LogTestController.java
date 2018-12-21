package com.example.springbootwithestest.esTestOne.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author lcb
 * @date 2018/11/27 18:20
 * common-logging 是apache的通用日志接口，用户可以自由选择第三方的日志组件作为具体实现，像log4j，或者jdk自带的logging
 * 通过common-logging会通过动态查找机制，在程序运行时自动找出真正使用的日志库，通常使用common-logging配合log4j使用。
 * 使用common-logging的好处是代码依赖common-loggin而非log4j，避免了和具体的日志方案直接耦合，在有必要时，可以更改日志实现的第三方库
 */
@Controller
@RequestMapping("/logTest")
public class LogTestController {
    // 直接使用log4j的logger
   //private static Logger logger = Logger.getLogger(LogTestController.class.getName());
    /*
    直接使用log4j2实现
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
     */
    //private Logger logger = LogManager.getLogger(LogTestController.class);
    /*
     使用slfj接口，去查找实现类
     import org.slf4j.Logger;
     import org.slf4j.LoggerFactory;
     */
   //private Logger logger = LoggerFactory.getLogger(LogTestController.class.getName());

    /*
    import org.apache.commons.logging.Log;
    import org.apache.commons.logging.LogFactory;
     */
    private Log logger = LogFactory.getLog(LogTestController.class.getName());
    @RequestMapping("/testLog")
    @ResponseBody
    public String testLog(){
        logger.trace("trace 测试");
        logger.info("info 测试");
        logger.error("error 测试");
        logger.debug("debug 测试");
        System.out.println("info 测试");
        System.out.println("error 测试");
        return "{\"msg\":\"测试成功\"}";
    }
}
