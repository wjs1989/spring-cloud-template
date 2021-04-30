package com.wjs.produce;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.*;

public class Test1 {
    private static boolean stop;

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

//        Thread thread = new Thread(() -> {
//            int i = 0;
//            while (!stop) {
//                i++;
//                print();
//            }
//
//            System.out.println("结束");
//        }, "t1");
//
//        thread.start();
//
//        Thread.sleep(1000);
//        stop = true;

        // BeanDefinitionRegistryPostProcessor
//        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        ProxyGenerator.generateProxyClass(
//                "d://proxy0",new Class[]{BaseService.class}, Modifier.PUBLIC );


//        Method[] declaredMethods = DefaultBaseService1.class.getDeclaredMethods();
//        for (int i = 0; i < declaredMethods.length; i++) {
//            System.out.println(declaredMethods[i]);
//        }

//        System.out.println(getName("a"));

//        Map<Hello, String> identityHashMap2 = new IdentityHashMap<>();
//        Hello hello1 = new Hello();
//        identityHashMap2.put(hello1, "1");
//        System.out.println(identityHashMap2.get(hello1)); //1

//        Hello hello = new Hello();
//        Generic<Hello> result = new Generic<>();
//        result.setData(hello);
//
//        Hello data = result.getData();


//        String cmd ="mysqldump -h10.204.125.132 -uroot -p123456 -P36542 aa>/mysqldata/xian.sql;";
//        Runtime.getRuntime().exec(cmd);


//        ServiceLoader<BaseService1> load = ServiceLoader.load(BaseService1.class);
//        Iterator<BaseService1> iterator = load.iterator();
//        while (iterator.hasNext()) {
//            BaseService1 next = iterator.next();
//            next.doService(111);
//
//        }
        // new Thread(()->{System.out.println(1);});
        //
        //  String userHome = System.getProperty("user.dir");
        //  System.out.println(userHome);

        // List<String> list = Arrays.asList("1","2","3");
        //
        // String join = String.join(",", list);
        // System.out.println(join);

        // String json = "{\"scriptString\":\"Object.assign(JForm,{ // 下拉框选择改变事件 context 上下文(this) form 表单  selectFocusAfter: function(context, form, callback) {     context.scriptCommonRequest({       \\\"queryType\\\": 2,       \\\"houseType\\\": 2       },\\\"/formDesignerApi/park/assetHouse/selectList\\\").then(res => {                      callback(true,res.data);       }).catch(err => {       })  },  // 下拉框选择改变事件 context 上下文(this) form 表单   selectChangeAfter: function(context, form, callback) {   callback(false)  }});\"}";

        // String json = "{\"customUrl\":\"\",\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"dataType\":\"\",\"width\":\"100%\",\"options\":{\"bg\":\"\"},\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"list\":[{\"dataType\":\"\",\"type\":\"FdTemp\",\"list\":[{\"columns\":[],\"dataType\":\"\",\"type\":\"FdTitle\",\"customUrl\":\"\",\"gutter\":10,\"titleName\":\"企业账号管理\",\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"titleButton\":[],\"justify\":\"\",\"width\":\"\",\"name\":\"标题组件\",\"options\":{\"labelWidth\":120},\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"key\":\"1604309465000_62122\",\"height\":\"\"},{\"searchList\":[{\"_flag\":\"out\",\"selectData\":[{\"label\":\"选项1\",\"value\":\"aaa\"},{\"label\":\"选项2\",\"value\":\"bbb\"},{\"label\":\"选项3\",\"value\":\"ccc\"}],\"labelWidth\":\"120\",\"label\":\"企业名称：\",\"type\":\"input\",\"dictType\":\"localData\",\"field\":\"user_t,name,varchar\",\"options\":[],\"cascaderData\":[{\"children\":[{\"children\":[{\"label\":\"三级 1-1-1\",\"value\":\"三级 1-1-1\"}],\"label\":\"二级 1-1\",\"value\":\"二级 1-1\"}],\"label\":\"一级 1\",\"value\":\"一级 1\"},{\"children\":[{\"children\":[{\"label\":\"三级 2-1-1\",\"value\":\"三级 2-1-1\"}],\"label\":\"二级 2-1\",\"value\":\"二级 2-1\"},{\"children\":[{\"label\":\"三级 2-2-1\",\"value\":\"三级 2-2-1\"}],\"label\":\"二级 2-2\",\"value\":\"二级 2-2\"}],\"label\":\"一级 2\",\"value\":\"一级 2\"},{\"children\":[{\"children\":[{\"label\":\"三级 3-1-1\",\"value\":\"三级 3-1-1\"}],\"label\":\"二级 3-1\",\"value\":\"二级 3-1\"},{\"children\":[{\"label\":\"三级 3-2-1\",\"value\":\"三级 3-2-1\"}],\"label\":\"二级 3-2\",\"value\":\"二级 3-2\"}],\"label\":\"一级 3\",\"value\":\"一级 3\"}],\"placeholder\":\"请输入\",\"value\":\"\",\"dataPlace\":\"localData\",\"isSetPrompt\":true}],\"columns\":[],\"dataType\":\"\",\"scriptString\":\"\\n\\n\\nObject.assign(JForm,{\\n  // 提交后事件 context 上下文(this) button 按钮信息 request 请求参数 response 响应参数\\n  submitAfter: function(context, button, request, response, callback) {\\n      console.log(response)\\n      response.data.records.forEach(item => {\\n          item[\\\"user_t,password,varchar\\\"] = \\\"123456\\\";\\n      })\\n       callback(true)\\n      \\n  }\\n});\\n\\n\\n\",\"type\":\"FdSearch\",\"searchTitle\":\"TitleName\",\"searchActivation\":0,\"customUrl\":\"\",\"gutter\":10,\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"justify\":\"\",\"width\":\"\",\"name\":\"搜索组件\",\"options\":{\"hiddenParams\":[{\"relational\":\"in\",\"value\":\"4\",\"key\":\"user_t,user_type,int\"},{\"value\":\"\",\"key\":\"\"}],\"labelWidth\":120},\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"key\":\"1603768844000_11087\",\"height\":\"\"},{\"columns\":[{\"tableField\":\"user_t,name,varchar\",\"width\":\"\",\"label\":\"企业名称\",\"fontSetting\":[{\"fontBgColor\":\"\",\"fieldValue\":\"\",\"fontColor\":\"\"}]},{\"tableField\":\"user_t,username,varchar\",\"width\":\"\",\"label\":\"登陆账号\",\"fontSetting\":[{\"fontBgColor\":\"\",\"fieldValue\":\"\",\"fontColor\":\"\"}]},{\"tableField\":\"user_t,password,varchar\",\"width\":\"\",\"label\":\"初始密码\",\"fontSetting\":[{\"fontBgColor\":\"\",\"fieldValue\":\"\",\"fontColor\":\"\"}]}],\"dataType\":\"\",\"index\":3,\"type\":\"FdTable\",\"customUrl\":\"\",\"gutter\":10,\"tableOperateBut\":[{\"authCode\":false,\"showOptions\":{\"show\":true,\"value\":\"\",\"key\":\"\"},\"labelWidth\":\"130\",\"eventType\":[{\"name\":\"添加\",\"value\":\"add\"},{\"name\":\"编辑\",\"value\":\"edit\"},{\"name\":\"删除\",\"value\":\"delete\"},{\"name\":\"查看\",\"value\":\"view\"},{\"name\":\"接口请求\",\"value\":\"api\"},{\"name\":\"工作流请求\",\"value\":\"workflow\"},{\"name\":\"路由跳转\",\"value\":\"route\"},{\"name\":\"表单弹框\",\"value\":\"dialog\"}],\"buttonList\":[{\"authCode\":\"\",\"eventList\":{},\"dialogEventShow\":false,\"method\":\"POST\",\"eventType\":\"route\",\"params\":[],\"type\":\"api\",\"url\":\"/formDesignerApi/park/operator/resetPassword\",\"processDefinitionKey\":\"\",\"showOption\":{\"show\":true,\"value\":\"\",\"key\":\"\"},\"eventParams\":[],\"theme\":\"primary\",\"text\":\"确定\",\"event\":\"\"}],\"type\":\"dialog\",\"params\":[],\"url\":\"\",\"dialogCustomBut\":false,\"isShowDialog\":false,\"prop\":\"\",\"name\":\"\",\"theme\":\"text\",\"formList\":[{\"setCustomApiParam\":[],\"radioData\":[{\"label\":\"xx1\",\"value\":\"选项1\"},{\"label\":\"xx2\",\"value\":\"选项2\"}],\"selectData\":[{\"label\":\"选项1\",\"value\":\"aaa\"},{\"label\":\"选项2\",\"value\":\"bbb\"},{\"label\":\"选项3\",\"value\":\"ccc\"}],\"customApi\":\"\",\"index\":0,\"labelWidth\":\"130\",\"rules\":[],\"label\":\"您确定要重置选中的企业密码?\",\"type\":\"\",\"rows\":1,\"customApiList\":[],\"dictType\":\"\",\"customRules\":[{\"flag\":\"\",\"pattern\":\"\",\"trigger\":\"change\",\"message\":\"请输入XXX\",\"title\":\"标题\"}],\"field\":\"\",\"prop\":\"\",\"options\":[],\"cascaderData\":[{\"children\":[{\"children\":[{\"label\":\"三级 1-1-1\",\"value\":\"三级 1-1-1\"}],\"label\":\"二级 1-1\",\"value\":\"二级 1-1\"}],\"label\":\"一级 1\",\"value\":\"一级 1\"},{\"children\":[{\"children\":[{\"label\":\"三级 2-1-1\",\"value\":\"三级 2-1-1\"}],\"label\":\"二级 2-1\",\"value\":\"二级 2-1\"},{\"children\":[{\"label\":\"三级 2-2-1\",\"value\":\"三级 2-2-1\"}],\"label\":\"二级 2-2\",\"value\":\"二级 2-2\"}],\"label\":\"一级 2\",\"value\":\"一级 2\"},{\"children\":[{\"children\":[{\"label\":\"三级 3-1-1\",\"value\":\"三级 3-1-1\"}],\"label\":\"二级 3-1\",\"value\":\"二级 3-1\"},{\"children\":[{\"label\":\"三级 3-2-1\",\"value\":\"三级 3-2-1\"}],\"label\":\"二级 3-2\",\"value\":\"二级 3-2\"}],\"label\":\"一级 3\",\"value\":\"一级 3\"}],\"disabled\":false,\"placeholder\":\"请输入关键字\",\"defaultValueType\":\"\",\"checkboxData\":[{\"label\":\"xx1\",\"value\":\"选项1\"},{\"label\":\"xx2\",\"value\":\"选项2\"}],\"value\":[],\"dataPlace\":\"localData\",\"isSetPrompt\":true}],\"text\":\"重置密码\",\"place\":\"inside\",\"power\":false,\"id\":\"1320931909261344769\",\"key\":\"1617932967589_24772\"}],\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"justify\":\"\",\"tableUpdate\":0,\"width\":\"\",\"name\":\"表格组件\",\"options\":{\"labelWidth\":120},\"tableList\":[{\"index\":1,\"id\":\"1\"}],\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"key\":\"1603768844000_22333\",\"tableTitle\":\"数据列表\",\"height\":\"\",\"tableOperate\":{\"width\":100,\"isSerialNumber\":true,\"serialNumWidth\":\"60\",\"switch\":\"show\"}},{\"columns\":[],\"dataType\":\"\",\"paging\":{\"layout\":\"total,sizes,prev,pager,next\",\"total\":1,\"sizes\":[10,20,50,100],\"size\":10,\"currentPage\":1,\"version\":\"v1\"},\"type\":\"FdPaging\",\"customUrl\":\"\",\"gutter\":10,\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"justify\":\"\",\"width\":\"\",\"name\":\"分页组件\",\"options\":{\"labelWidth\":120},\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"key\":\"1603768844000_4461\",\"height\":\"\"}],\"customUrl\":\"\",\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"width\":\"\",\"name\":\"liebiao\",\"options\":{\"treeTemp\":{\"searchList\":[{\"selectData\":[{\"label\":\"选项1\",\"value\":\"aaa\"},{\"label\":\"选项2\",\"value\":\"bbb\"},{\"label\":\"选项3\",\"value\":\"ccc\"}],\"field\":\"\",\"options\":[],\"cascaderData\":[{\"children\":[{\"children\":[{\"label\":\"三级 1-1-1\",\"value\":\"三级 1-1-1\"}],\"label\":\"二级 1-1\",\"value\":\"二级 1-1\"}],\"label\":\"一级 1\",\"value\":\"一级 1\"},{\"children\":[{\"children\":[{\"label\":\"三级 2-1-1\",\"value\":\"三级 2-1-1\"}],\"label\":\"二级 2-1\",\"value\":\"二级 2-1\"},{\"children\":[{\"label\":\"三级 2-2-1\",\"value\":\"三级 2-2-1\"}],\"label\":\"二级 2-2\",\"value\":\"二级 2-2\"}],\"label\":\"一级 2\",\"value\":\"一级 2\"},{\"children\":[{\"children\":[{\"label\":\"三级 3-1-1\",\"value\":\"三级 3-1-1\"}],\"label\":\"二级 3-1\",\"value\":\"二级 3-1\"},{\"children\":[{\"label\":\"三级 3-2-1\",\"value\":\"三级 3-2-1\"}],\"label\":\"二级 3-2\",\"value\":\"二级 3-2\"}],\"label\":\"一级 3\",\"value\":\"一级 3\"}],\"labelWidth\":\"120\",\"label\":\"TitleName\",\"placeholder\":\"请输入关键字\",\"type\":\"input\",\"value\":\"\",\"dataPlace\":\"localData\",\"isSetPrompt\":true,\"dictType\":\"localData\"}],\"columns\":[],\"dataType\":\"\",\"type\":\"FdSearch\",\"searchTitle\":\"TitleName\",\"searchActivation\":0,\"customUrl\":\"\",\"gutter\":10,\"cssClass\":\"\",\"isLocalData\":1,\"apiUrl\":\"\",\"justify\":\"\",\"width\":\"\",\"name\":\"搜索组件\",\"options\":{\"hiddenParams\":[{\"value\":\"\",\"key\":\"\"}]},\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"key\":\"1603768844000_11087\",\"height\":\"\"}},\"styles\":{\"border\":\"none\",\"paddingRight\":\"0px\",\"marginLeft\":\"0px\",\"marginRight\":\"0px\",\"paddingBottom\":\"0px\",\"top\":\"0px\",\"left\":\"0px\",\"marginBottom\":\"0px\",\"position\":\"relative\",\"paddingTop\":\"0px\",\"opacity\":1,\"paddingLeft\":\"0px\",\"marginTop\":\"0px\",\"zIndex\":\"auto\"},\"key\":\"1603768930000_42193\",\"height\":\"\"}],\"key\":\"1603768844000_26748\",\"height\":\"100%\"}";
        //
        //
        // String s = StringEscapeUtils.escapeJava(json);
        // Map<String, Object> layout1 = (Map) JSONObject.parseObject(s);
        // System.out.println(layout1);

        List<String> indexList = new ArrayList<>();
        indexList.add("indexList1");
        indexList.add("indexList2");

        String[] strings = indexList.toArray(new String[0]);
        System.out.println(strings);
    }

    public static class Hello {

        public final String a = "AAA";

        static {
            System.out.println("Hello");
        }

        private Integer id;

        private String name;

        private Integer age;

        private String email;
    }

    private static void threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10,
                1L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10)) {

            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("beforeExecute->" + Thread.currentThread().getName());
            }
        };

        //threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("threadName->" + Thread.currentThread().getName() + finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void fixed() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            System.out.println(111);
        });
    }

    public static void print() {
        // System.out.println("----------------");

//        synchronized (Test1.class){
//            int i = 0;
//        }


    }

//    void executorTest() {
//        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
//
//        scheduled.schedule(() -> {
//            System.out.println("-------------");
//        }, 5, TimeUnit.SECONDS);
//
//        scheduled.execute(new Test1()::print);
//    }


    public static <T> T getName(T t) {
        return t;
    }
}
