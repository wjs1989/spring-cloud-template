# **模块说明**
    -spring-cloud-template
        -common-template
           -domain-common-template
                -domain-common
                -domain-common-start
                -global-exception-handler
                -model-common
           -remote-common-template
                -user-remote 
           -resource-common-template 
                -event-monitor
                -oauth2-resource
        -module-template
            -module-eureka
            -module-oauth2
            -module-produce
            -module-seckill
            -module-zuul

## FQA
打包顺序    
1、屏蔽最外层pom.xml    
    `    <modules>    
        <!-- <module>module-template</module>-->    
             <module>popular-common-parent</module> 
         </modules> 
`       
    
    install spring-cloud-template
    成功后在放开注释，然后各个项目打包 
