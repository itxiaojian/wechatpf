package com.sliu.framework.app.ad.controller;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
public class ADOperTest {
    public ADOperTest() {
    }
    public String GetADInfo(String name ) {

        String userName = name; // 用户名称
        if(userName==null){
            userName = "";
        }
        String company = "";
        String host = "192.168.10.27"; // AD服务器
        String port = "389"; // 端口
        String url = new String("ldap://" + host + ":" + port);
        Hashtable HashEnv = new Hashtable();
        // String adminName ="CN=oyxiaoyuanxy,CN=Users,DC=Hebmc,DC=com";
        // AD的用户名
//        String adminName = "adser\\administrator"; // 注意用户名的写法：domain\User
//        String adminPassword = "Sn@ke007"; // 密码
        String adminName = "adser\\yzhang"; // 注意用户名的写法：domain\User
        String adminPassword = "hfzs.2015"; // 密码
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
        HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User
        HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); // AD Password
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
        HashEnv.put(Context.PROVIDER_URL, url);
        try {
            LdapContext ctx = new InitialLdapContext(HashEnv, null);
            // 域节点
            String searchBase = "DC=adser,DC=com";
            // LDAP搜索过滤器类
            String searchFilter = "objectClass=User";
            // 搜索控制器
            SearchControls searchCtls = new SearchControls(); // Create the
            // search
            // controls
            // 创建搜索控制器
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
            // the
            // search
            // scope
            // 设置搜索范围
            // searchCtls.setSearchScope(SearchControls.OBJECT_SCOPE);
            // Specify the search scope 设置搜索范围
			// String returnedAtts[] = { "memberOf", "distinguishedName",
			// "Pwd-Last-Set", "User-Password", "cn" };// 定制返回属性
//            String returnedAtts[] = { "company","name","mobile","url" };// 定制返回属性
            String returnedAtts[] = { "memberOf","url", "whenChanged", "employeeID",
                    "name", "userPrincipalName", "physicalDeliveryOfficeName",
                    "departmentNumber", "telephoneNumber", "homePhone",
                    "mobile", "department", "sAMAccountName", "whenChanged",
                    "mail","memberOf", "distinguishedName", "Pwd-Last-Set", "User-Password", "cn" }; // 定制返回属性

            searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
            // 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
            NamingEnumeration answer = ctx.search(searchBase, searchFilter,
                    searchCtls);// Search for objects using the filter
            // 初始化搜索结果数为0
            int totalResults = 0;// Specify the attributes to return
            int rows = 0;
            while (answer.hasMoreElements()) {// 遍历结果集
                SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN
                System.out.println("-------sr1------->"+sr.getAttributes());
                System.out.println("-------sr2------->"+sr.getAttributes().get("distinguishedName").get(0));
                System.out.println(++rows + "************************************************");
                String dn = sr.getName();
                System.out.println("-----------dn--------->"+dn);
                String match = dn.split("CN=")[1].split(",")[0];//返回格式一般是CN=ptyh,OU=专卖
                String match1 = dn.split(",")[1];
                System.out.println("-----------match1------>"+match1);
                if(match1 != null){
                	String bmmc = match1.replace("OU=", "");
                	System.out.println("-------bmmc----->"+bmmc);
                }
//                if(userName.equals(match)){
//                    Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
//                    if (Attrs != null) {
//                        try {
//                            for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
//                                Attribute Attr = (Attribute) ne.next();// 得到下一个属性
//                                System.out.println(" AttributeID=属性名："+ Attr.getID().toString());
//                                // 读取属性值
//                                for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {
//                                    company =  e.next().toString();
//                                    System.out.println("    AttributeValues=属性值：" + company);
//                                }
//                                System.out.println("    ---------------");
//
//                            }
//                        } catch (NamingException e) {
//                            System.err.println("Throw Exception : " + e);
//                        }
//                    }//if
//                }
            }//while
            System.out.println("************************************************");
            System.out.println("Number: " + totalResults);
            ctx.close();
        } catch (NamingException e) {
        	System.out.println("登录失败了");
//            e.printStackTrace();
//            System.err.println("Throw Exception : " + e);
        }
        return company;
    }
    public static void main(String args[]){
        // 实例化
        ADOperTest ad = new ADOperTest();
        String username = "何书查";

        ad.GetADInfo(username);
    }
}
