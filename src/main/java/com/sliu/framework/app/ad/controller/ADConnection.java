/**
 *  JAVA 读取AD用户信息
 *  aa00a00
 */
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

public class ADConnection {

    public ADConnection() {
    }

    public void GetADInfo() {
        String userName = "adser\\administrator"; // 用户名称
        String passwd = "Sn@ke007";
        String host = "192.168.10.27"; // AD服务器
        String port = "389"; // 端口
        String domain = "@adser.cn"; // 邮箱的后缀名
        String url = new String("ldap://" + host + ":" + port);
        String user = userName.indexOf(domain) > 0 ? userName : userName + domain;
        Hashtable HashEnv = new Hashtable();
        // String adminName ="CN=oyxiaoyuanxy,CN=Users,DC=Hebmc,DC=com";//AD的用户名
        String adminName = "xueqiang"; // 注意用户名的写法：domain\User 或
        // User@domain.com
        adminName = "adser\\administrator"; // 注意用户名的写法：domain\User 或 User@domain.com
        String adminPassword = "Sn@ke007"; // 密码
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
        HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User
        HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); // AD Password
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
        HashEnv.put(Context.PROVIDER_URL, url);
        try {
            LdapContext ctx = new InitialLdapContext(HashEnv, null);
            // 域节点
            String searchBase = "OU=合肥智圣,DC=adser,DC=com";
            // LDAP搜索过滤器类
            String searchFilter = "objectClass=User";
            // 搜索控制器
            SearchControls searchCtls = new SearchControls(); // Create the
            // 创建搜索控制器
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
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
                System.out.println("sr---------------------->"+sr.getAttributes());
                System.out.println("------------------------>"+sr.getAttributes().get(""));
                System.out.println(++rows + "************************************************");
                System.out.println(sr.getName());
                Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
                if (Attrs != null) {
                    try {
                        for (NamingEnumeration ne = Attrs.getAll(); ne
                                .hasMore();) {
                            Attribute Attr = (Attribute) ne.next();// 得到下一个属性
                            System.out.println(" AttributeID=属性名："
                                    + Attr.getID().toString());
                            // 读取属性值
                            for (NamingEnumeration e = Attr.getAll(); e
                                    .hasMore(); totalResults++) {
                                System.out.println("    AttributeValues=属性值："
                                        + e.next().toString());
                            }
                            System.out.println("---------------");
                            // 读取属性值
                            // Enumeration values = Attr.getAll();
                            // if (values != null) { // 迭代
                            // while (values.hasMoreElements()) {
                            // System.out.println("    AttributeValues=属性值："+
                            // values.nextElement());
                            // }
                            // }
                            // System.out.println("    ---------------");
                        }
                    } catch (NamingException e) {
                        System.err.println("Throw Exception : " + e);
                    }
                }
            }
            System.out.println("************************************************");
            System.out.println("用户："+rows+" 属性: " + totalResults);
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
            System.err.println("Throw Exception : " + e);
        }
    }

    public static void main(String args[]) {
        // 实例化
        ADConnection ad = new ADConnection();
        ad.GetADInfo();
    }
}
