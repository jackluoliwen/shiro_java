package com.sudojava.shiro.nativeRealm;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;



public class RealmDataSource {

    private MysqlDataSource dataSource;
    private DefaultSecurityManager securityManager;
    private JdbcRealm jdbcRealm;

    public RealmDataSource() {
        try {

            dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setServerName("localhost");
            dataSource.setLoginTimeout(2);
            dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
            jdbcRealm = new JdbcRealm();
            jdbcRealm.setDataSource(dataSource);
            jdbcRealm.setPermissionsLookupEnabled(true);
            //验证
            String authentication_sql = "select password from users where username = ?";
            jdbcRealm.setAuthenticationQuery(authentication_sql);
            //验证角色
            String user_roles_sql = "select a.role_name from user_roles a,users b where a.users_id = b.id  and  b.username = ?";
            jdbcRealm.setUserRolesQuery(user_roles_sql);
            String permission_sql = "select a.permission FROM roles_permissions a,user_roles b  where b.role_id = a.user_roles_role_id and b.role_name = ?";
            jdbcRealm.setPermissionsQuery(permission_sql);
            securityManager = new DefaultSecurityManager(jdbcRealm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultSecurityManager getSecurityManager() {
        return securityManager;
    }

    /**
     * @return
     */
    public MysqlDataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }
        return null;
    }


}
