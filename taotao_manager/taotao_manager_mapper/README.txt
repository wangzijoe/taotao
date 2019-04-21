Dao层：
    使用Mybatis框架，创建SqlMapConfig.xml
    创建一个applicationDao.xml
    1、配置数据源
    2、需要让spring容器管理 SqlSessionFactory.xml，单例的存在
    3、讲mapper代理对象，放入spring容器中，使用扫描包的方式，加载代理对象

整合内容	                    对应工程
Pojo	                    Taotao-mangaer-pojo
Mapper映射文件	            Taotao-mangaer-mapper
Mapper接口	                Taotao-mangaer-mapper
sqlmapConfig.xml	        Taotao-manager-web
applicationContext-dao.xml	Taotao-manager-web
