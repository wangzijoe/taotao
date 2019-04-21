Service层：   
    所有的实现类都放到spring容器中管理。由spring创建数据库连接池，并有spring管理实务。
    1、事物管理
    2、讲Service实现类对象放到spring容器中进行管理
整合内容	                        对应工程
Service接口及实现类	            Taotao-mangaer-service
applicationContext-service.xml	Taotao-manager-web
applicationContext-trans.xml	Taotao-manager-web

