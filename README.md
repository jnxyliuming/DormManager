# DormManager
基于JavaWeb使用jsp+servlet的宿舍管理项目，这是改编b站的一个视频跟随的项目，由于时间紧，为了应付期末考试后半程代码是自己跟着感觉来的  
├─src  
│  └─com  
│      └─lm   #根据自己的喜好来，一般是公司域名倒写  
│          └─dorm  # 项目名  
│              ├─bean # 存放实体类
│              ├─dao   # 持久层，对数据的操作
│              ├─service  # 服务层  
│              ├─servlet   # 具体的功能，存放servlet文件 
│              └─util   # 工具包，存放数据库的操作工具  
└─web  
    ├─image   
    └─WEB-INF  
        ├─classes  
        │  ├─com  
        │  │  └─lm  
        │  │	└─dorm  
        │  │		  ├─bean   
        │  │		  ├─dao  
        │  │		  ├─service  
        │  │		  ├─servlet  
        │  │ 		  └─util  
        │  └─META-INF  
        ├─jsp  # jsp文件  
        └─lib  # jar  
1.把sql文件导入到数据库当中  
2.把src下的c3p0.xml文件配置改为自己的数据库(数据库用户名、密码)，不同的数据库版本驱动也不同  

